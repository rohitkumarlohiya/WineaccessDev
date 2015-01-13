package com.wineaccess.orchestration.workflow.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlException;

import com.globallogic.orch.ProcessDefinitionDocument;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionModel;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionRepository;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.process.task.Task;
import com.wineaccess.orchestration.workflow.processdefinition.ProcessDefinition;
import com.wineaccess.orchestration.workflow.processdefinition.TaskDefinition;
import com.wineaccess.orchestration.workflow.step.listener.TaskCompletionListener;
import com.wineaccess.orchestration.workflow.step.listener.TaskExecutionListener;
import com.wineaccess.persistence.exception.ProcessDefinitionNotFoundException;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.ExceptionHandlerUtil;
import com.wineaccess.response.Response;
import com.wineaccess.response.ValidationFailedError;
import com.wineaccess.utils.ResourceLoader;

/**
 * The Class Process is the runtime instance of Process Definition.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class ProcessInstance implements Serializable {
	
	private static final long serialVersionUID = -7423385995612126380L;
	
	private String processId;
	private ProcessDefinition processDefinition;
	private String versionId;
	private String processName;
	private TaskExecutionListener taskExecutionListener = new TaskExecutionListener();
	private TaskCompletionListener taskCompletionListener = new TaskCompletionListener();
	DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
	
	public ProcessInstance(String processName, String version) throws XmlException {
		
		List<ProcessDefinitionModel> processDefinition =  ProcessDefinitionRepository.getByNameAndVersion(processName, version);
		
		if (!(processDefinition.size() == 1)) {
			throw new ProcessDefinitionNotFoundException();
		}
		
		ProcessDefinitionModel processDefinitionModel = (ProcessDefinitionModel) processDefinition.get(0);
		ProcessDefinitionDocument processDefinitionDocument = ProcessDefinitionDocument.Factory.parse(processDefinitionModel.getXml());
		this.processDefinition = new ProcessDefinition(processDefinitionDocument);
		this.processId = String.valueOf(processDefinitionModel.getId());
		this.versionId = version;
		this.processName = processName;
	}
	
	/**
	 * @throws Exception
	 */
	public synchronized void startProcess(String requestId, Map<String, Object> contextParameters) throws Exception {
		preppareContext(requestId, this.processDefinition.getStartTaskName(), contextParameters);
	}
	
	
	private void preppareContext(String requestId, String taskName, Map<String, Object> contextParameters) {
		Collection<TaskExecutionModel> taskExecutionModels = new ArrayList<TaskExecutionModel>();
		Collection<ProcessContext> processContexts = new ArrayList<ProcessContext>();
		
		TaskDefinition taskDefinition = this.processDefinition.getTaskDefinitions().get(taskName);
		
		for (String currentTask : taskDefinition.getNextTasks()) {
			
			String [] nextTasks = this.processDefinition.getTaskDefinitions().get(currentTask).getNextTasks();
			
			String nextTask = null;
			
			if (nextTasks.length == 1) {
				nextTask = nextTasks[0];
			}
			
			ProcessContext pContext = new ProcessContext(processName, versionId, requestId, currentTask, nextTask);
			pContext.setContextParameters(contextParameters);
			processContexts.add(pContext);
			taskExecutionModels.add(new TaskExecutionModel(requestId, currentTask, nextTask));
		}
		
		DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
		dataRepositoryManager.addTaskExecutionLog(requestId, taskExecutionModels);
		
		for (ProcessContext pContext : processContexts){
			//Put Message On Task Execution Queue
			taskExecutionListener.doWork(pContext);
		}
	}
	
	/**
	 * @param lastExecutedTask
	 * @throws Exception
	 */
	public void executeNextAction(String currentTask, String nextTaskName, String initialRequestId,  Map<String, Object> contextParameters) throws Exception {
		if (nextTaskName != null  && this.getProcessDefinition().getEndTaskName().equalsIgnoreCase(nextTaskName)) {
			QueueUtil.putMessgaeOnApiCompletionQueue(initialRequestId);
			return;
		}
		
		if (nextTaskName != null) {
			
			Collection<TaskExecutionModel> taskExecutionModels = new ArrayList<TaskExecutionModel>();
			Collection<ProcessContext> processContexts = new ArrayList<ProcessContext>();
			
			String [] nextTasks = this.processDefinition.getTaskDefinitions().get(nextTaskName).getNextTasks();
			
			String nextTask = null;
			
			if (nextTasks.length == 1) {
				nextTask = nextTasks[0];
			}
			
			ProcessContext pContext = new ProcessContext(processName, versionId, initialRequestId, currentTask, nextTask);
			pContext.setContextParameters(contextParameters);
			processContexts.add(pContext);
			taskExecutionModels.add(new TaskExecutionModel(initialRequestId, currentTask, nextTask));
			
			DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
			dataRepositoryManager.addTaskExecutionLog(initialRequestId, taskExecutionModels);
			
			
			for (ProcessContext pContext1 : processContexts){
				//Put Message On Task Execution Queue
				taskExecutionListener.doWork(pContext);
			}
		} else {
			preppareContext(initialRequestId, currentTask, contextParameters);
		}
	}
	
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public synchronized void stopProcess(String requestId) throws Exception {
	}
	
	/**
	 * @param taskName
	 * @throws Exception
	 */
	public void executeTask(ProcessContext pContext) throws Exception {
		TaskDefinition taskDefinition = this.getProcessDefinition().getTaskDefinitions().get(pContext.getCurrentTask());
		Object[] constructorArgs = new Object[] { pContext.getCurrentTask(), taskDefinition.getTaskConfiguration()};
		Task task = (Task) ResourceLoader.instanciateClass(taskDefinition.getActionClass(), constructorArgs);
		try {
			task.execute(pContext);
			taskCompletionListener.doWork(pContext);
		} catch(ValidationFailedError ex) {
			Map <String, Object> output = new HashMap<String, Object>();
			output.put("FINAL-RESPONSE", ex.getFailedResponse());
			dataRepositoryManager.addOutput(pContext.getRequestId(), output);
			taskCompletionListener.doWork(pContext);
		} catch(Exception ex) {
			ex.printStackTrace();
			Response failedResponse = ExceptionHandlerUtil.analyzeJsonObjectException(ex).getFailedResponse();
			
			Map <String, Object> output = new HashMap<String, Object>();
			output.put("FINAL-RESPONSE", failedResponse);
			dataRepositoryManager.addOutput(pContext.getRequestId(), output);
			taskCompletionListener.doWork(pContext);
		}
	}

	public String getProcessId() {
		return processId;
	}

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public String getVersionId() {
		return versionId;
	}

	public String getProcessName() {
		return processName;
	}
}
