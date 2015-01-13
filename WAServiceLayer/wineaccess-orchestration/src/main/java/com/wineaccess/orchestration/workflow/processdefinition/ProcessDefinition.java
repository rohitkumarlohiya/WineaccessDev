package com.wineaccess.orchestration.workflow.processdefinition;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.globallogic.orch.ProcessDefinitionDocument;
import com.globallogic.orch.ProcessDefinitionDocument.ProcessDefinition.Task;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class ProcessDefinition implements Serializable {
	
	private static final long serialVersionUID = 5312204566046245847L;

	private String startTaskName;
	
	private String endTaskName;
	
	private Map<String, TaskDefinition> taskDefinitions = new HashMap<String, TaskDefinition>();
	
	public ProcessDefinition(ProcessDefinitionDocument processDefinitionDocument) {
		com.globallogic.orch.ProcessDefinitionDocument.ProcessDefinition processDefinition = processDefinitionDocument.getProcessDefinition();
		taskDefinitions.put(processDefinition.getStartState().getName(), new TaskDefinition(processDefinition.getStartState()));
		
		for (Task task : processDefinition.getTaskArray()) {
			taskDefinitions.put(task.getName().getStringValue(), new TaskDefinition(task));
		}
		
		taskDefinitions.put(processDefinition.getEndState().getName(), new TaskDefinition(processDefinition.getEndState()));
		
		startTaskName = processDefinitionDocument.getProcessDefinition().getStartState().getName();
		endTaskName = processDefinitionDocument.getProcessDefinition().getEndState().getName();
	}

	public Map<String, TaskDefinition> getTaskDefinitions() {
		return taskDefinitions;
	}

	public void setTaskDefinitions(Map<String, TaskDefinition> taskDefinitions) {
		this.taskDefinitions = taskDefinitions;
	}

	public String getStartTaskName() {
		return startTaskName;
	}

	public String getEndTaskName() {
		return endTaskName;
	}
}
