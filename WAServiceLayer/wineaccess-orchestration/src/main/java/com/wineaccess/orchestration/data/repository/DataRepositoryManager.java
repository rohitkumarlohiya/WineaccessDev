package com.wineaccess.orchestration.data.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel;

/**
 * Shared data repository manager. Responsible for adding and retrieving the input/out data to the 
 * Shared repository. The example of shared repository could be MemCahce, Redis etc.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public interface DataRepositoryManager {
	
	/** Task Execution Log Interfaces */
	
	public void removeRequestData(String requestId, APIEvent event);
	public void removeRequestData(String requestId, String  commandName);
	public void addTaskExecutionLog(String requestId, TaskExecutionModel taskExecutionModel);
	public void addTaskExecutionLog(String requestId, Collection<TaskExecutionModel> taskExecutionModel);
	public Collection<TaskExecutionModel> getTaskExecutionLogByCurrentTask(String requestId, String currentTask, String taskStatus);
	public Collection<TaskExecutionModel> getTaskExecutionLogByNextTask(String requestId, String nextTask, String taskStatus);
	public void updateTaskExecutionStatus(TaskExecutionModel taskExecution);
	
	
	/** Input/Output Interfaces */
	
	public void addRequestHeaders(String requestId, Map<String, String> inputHeaders);
	public void addResponseHeaders(String requestId, Map<String, String> responseHeaders);
	public Map<String, List<String>> getRequestHeaders(String requestId);
	public Map<String, List<String>> getResponseHeaders(String requestId);
	
	/**
	 * Add the input data to the shared repository against the request id.
	 * @param requestId 
	 * @param name
	 * @param value
	 */
	public void addInput(String requestId, String name, Object value);
	
	/**
	 * Add the multiple input data to shared repository against the request id.
	 * 
	 * @param requestId
	 * @param input
	 */
	public void addInput(String requestId, Map<String, Object> input);
	
	/**
	 * The method will return the input value based on the request id and input paremeter name.
	 * 
	 * @param requestId
	 * @param name
	 * @return
	 */
	public Object getInput(String requestId, String name);
	
	/**
	 * The method will return all the input data from the shared repository.
	 * 
	 * @param requestId
	 * @return
	 */
	public Map<String, Object> getInput(String requestId);
	
	/**
	 * The method will add the output to the shared repository 
	 * @param requestId
	 * @param output
	 */
	public void addOutput(String requestId, Map<String, Object> output);
	
	/**
	 * The method will add the output to the shared repository.
	 * @param requestId
	 * @param name
	 * @param value
	 */
	public void addOutput(String requestId, String name, Object value);
	
	/**
	 * Return all the output against request id.
	 * @param requestId
	 * @return
	 */
	public Map<String, Object> getOutput(String requestId);
	
	/**
	 * Return the output against the request id and output parameter name.
	 * @param requestId
	 * @param name
	 * @return
	 */
	public Object getOutput(String requestId, String name);
	
	
	/** API Key and Version Data Repository */
	public void addApiVersionKeyMap(String apiKey, String apiVersion);
	public boolean validateApiKeyVersion(String apiKey, String apiVersion);
	public boolean validateApiAccessCode(String apiAccessCode);
	public APIAccessCode getApiAccessCode(String apiAccessCode);
	public void putApiAccessCode(String apiAccessCode, APIAccessCode accessCode);
	
	public void cacheMasterData();
	
	public String getMasterDataType(String masterDataTypeId);
}
