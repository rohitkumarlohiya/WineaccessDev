package com.wineaccess.orchestration.workflow.process.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jyoti.yadav@globallogic.com
 */
@SuppressWarnings("serial")
public class ProcessContext implements Serializable {
	
	public static String PROCESS_CONTEXT = "ProcessContext";

	private String processName;
	private String versionId;
	private String requestId;
	private String currentTask;
	private String nextTask;
	private Map<String, Object> contextParameters = new HashMap<String, Object>();
	
	public ProcessContext() {
	}
	
	public ProcessContext(String processName, String versionId, String requestId, String currentTask, String nextTask) {
		this.processName = processName;
		this.versionId = versionId;
		this.requestId = requestId;
		this.currentTask = currentTask;
		this.nextTask = nextTask;
	}
	
	/** 
	 * The  method will return the name of the workflow.
	 * @return
	 */
	public String getProcessName() {
		return processName;
	}
	
	/**
	 * Return the request identifier for which this workflow has be started.
	 * @return
	 */
	public String getRequestId() {
		return requestId;
	}
	
	/**
	 * The method will return the name of the currently task to be executed or already executed by this task. 
	 * @return
	 */
	public String getCurrentTask() {
		return currentTask;
	}

	public Map<String, Object> getContextParameters() {
		return contextParameters;
	}

	public void setContextParameters(Map<String, Object> contextParameters) {
		this.contextParameters = contextParameters;
	}
	
	/**
	 * The method will return the version identifier of the workflow.
	 * @return
	 */
	public String getVersionId() {
		return versionId;
	}
	
	/**
	 * The method will return the name of the next task to be executed after this <code>getCurrentTask()</code>
	 * @return
	 */
	public String getNextTask() {
		return nextTask;
	}

	public void setNextTask(String nextTask) {
		this.nextTask = nextTask;
	}
}
