package com.wineaccess.orchestration.workflow.processdefinition;

import java.io.Serializable;

import com.globallogic.orch.ProcessDefinitionDocument.ProcessDefinition.EndState;
import com.globallogic.orch.ProcessDefinitionDocument.ProcessDefinition.StartState;
import com.globallogic.orch.ProcessDefinitionDocument.ProcessDefinition.Task;
import com.wineaccess.orchestration.core.common.configuration.ServerConfiguration;
import com.wineaccess.orchestration.workflow.startup.service.ServerConfigurationConstants;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class TaskDefinition implements Serializable {
	
	private static final long serialVersionUID = -4209519927158695967L;
	
	private String actionClass;
	private String taskName;
	private String [] nextTasks = null;
	private String queueJndi = null;
	private TaskConfiguration taskConfiguration;
	
	public TaskDefinition(StartState startState) {
		
		nextTasks = new String [startState.getTransitionArray().length]; 
		
		for (int i=0; i < startState.getTransitionArray().length; i++) {
			nextTasks[i] = startState.getTransitionArray()[i].getTo();
		}
		taskName = startState.getName();
	}
	
	public TaskDefinition(EndState endState) {
		taskName = endState.getName();
	}
	
	public TaskDefinition(Task task) {
		nextTasks = new String [task.getTransitionArray().length]; 
		
		for (int i=0; i < task.getTransitionArray().length; i++) {
			nextTasks[i] = task.getTransitionArray()[i].getTo();
		}
		this.actionClass = task.getAction().getClass1();
		taskConfiguration = new TaskConfiguration(task.getConfiguration());
		taskName = task.getName().getStringValue();
	}

	public TaskConfiguration getTaskConfiguration() {
		return taskConfiguration;
	}

	public String getActionClass() {
		return actionClass;
	}

	public String getTaskName() {
		return taskName;
	}

	public String[] getNextTasks() {
		return nextTasks;
	}

	public String getQueueJndi() {
		if (queueJndi == null) {
			queueJndi = ServerConfiguration.getInstance().getComponentConfig(ServerConfigurationConstants.COMPONENT_DEFAULT_TASK_EXECUTION_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
		}
		return queueJndi;
	}
}
