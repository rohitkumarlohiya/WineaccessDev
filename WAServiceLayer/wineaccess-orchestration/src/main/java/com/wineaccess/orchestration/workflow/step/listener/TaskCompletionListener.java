package com.wineaccess.orchestration.workflow.step.listener;

import java.util.Collection;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel.TaskStatus;
import com.wineaccess.orchestration.workflow.process.ProcessInstance;
import com.wineaccess.orchestration.workflow.process.cache.ProcessCacheManager;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * @author jyoti.yadav@globallogic.com
 */
public final class TaskCompletionListener extends CustomQueueListener {
	
	private ProcessCacheManager processCacheManager = null;
	
	
	public TaskCompletionListener() {
		processCacheManager = (ProcessCacheManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.PROCESS_CACHE_MANAGER));;
	}
	
	@Override
	public void doWork(Object qMessage) {
		
		long sTime = System.currentTimeMillis();
		ProcessContext pContext = (ProcessContext) qMessage;
		
		DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
		
		Collection <TaskExecutionModel> models = dataRepositoryManager.getTaskExecutionLogByCurrentTask(pContext.getRequestId(), pContext.getCurrentTask(), TaskExecutionModel.TaskStatus.P.name());
		
		
		/*List <TaskExecutionModel> models = TaskExecutionModelRepository.getByRequestIdAndCurrentTask(pContext.getRequestId(), pContext.getCurrentTask());*/
		
		
		if (models.size() != 1) {
			throw new RuntimeException("In Valid Data for Task Execution Model");
		}
		
		TaskExecutionModel taskExecutionModel = models.iterator().next();
		//taskExecutionModel.setTaskStatus(TaskStatus.C.name()); 
		
		dataRepositoryManager.updateTaskExecutionStatus(taskExecutionModel);
		
		//For All the next task, models.size() == 0 is zero then put on queue
		models = dataRepositoryManager.getTaskExecutionLogByNextTask(pContext.getRequestId(), pContext.getNextTask(), TaskStatus.P.name());
		//models = TaskExecutionModelRepository.getByRequestIdAndNextTask(pContext.getRequestId(), pContext.getNextTask(), TaskStatus.P.name());
		
		
		try {
			if (models.size() == 0) {
				ProcessInstance porcess = processCacheManager.getProcess(pContext.getProcessName(), pContext.getVersionId());
				porcess.executeNextAction(pContext.getCurrentTask(), pContext.getNextTask(), pContext.getRequestId(), pContext.getContextParameters());
			} else {
				//Do Nothing
			}
			long eTime = System.currentTimeMillis();
			System.out.println("PPPP ---> " + (eTime - sTime));
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}
}
