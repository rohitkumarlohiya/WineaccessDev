package com.wineaccess.orchestration.workflow.process.task;

import java.util.Map;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * The Base Task provides the basic implementation of Task. All task implementors need to extend this class to implement their functionality.
 * @author jyoti.yadav@globallogic.com
 */
public abstract class BaseTask implements Task {
	
	protected String taskName;
	protected TaskConfiguration taskConfiguration;
	private DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
	
	public BaseTask(String taskName, TaskConfiguration taskConfiguration) {
		this.taskName = taskName;
		this.taskConfiguration = taskConfiguration;
	}
	
	/**
	 * @see Task#execute(ProcessContext)
	 */
	@Override
	public void execute(ProcessContext pContext) throws Exception {
		this.doBefore(pContext);
		this.doExecute(pContext);
		this.doAfter(pContext);
	}
	
	/**
	 * 
	 * @param pContext
	 * @throws Exception
	 */
	protected abstract void doExecute(ProcessContext pContext) throws Exception;
	
	
	private void doAfter(ProcessContext pContext) {
	}
	
	
	private void doBefore(ProcessContext pContext) {
	}
	
	public String getOpenApiHostName() {
		return PropertyholderUtils.getStringProperty("openapi.host.name");
	}
	
	/**
	 * Return the error action command.
	 * @return
	 */
	public String getOnErrorAction() {
		return this.taskConfiguration.getOnErrorAction();
	}
	
	public String getTaskProperty(String name) {
		return this.taskConfiguration.getProperties().get(name);
	}
	
	public Map<String, String> getTaskProperties() {
		return this.taskConfiguration.getProperties();
	}

	public String getTaskName() {
		return taskName;
	}

	public TaskConfiguration getTaskConfiguration() {
		return taskConfiguration;
	}

	public DataRepositoryManager getDataRepositoryManager() {
		return dataRepositoryManager;
	}
	
	public void discontinue(ProcessContext pContext) {
		pContext.setNextTask("END-STATE");
	}
}
