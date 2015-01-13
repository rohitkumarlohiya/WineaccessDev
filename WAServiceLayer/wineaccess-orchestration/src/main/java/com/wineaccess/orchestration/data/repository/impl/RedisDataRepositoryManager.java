package com.wineaccess.orchestration.data.repository.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel;

/**
 * @see DataRepositoryManager
 * @author jyoti.yadav@globallogic.com
 */
public class RedisDataRepositoryManager implements DataRepositoryManager {

	@Override
	public void addRequestHeaders(String requestId,
			Map<String, String> inputHeaders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addResponseHeaders(String requestId,
			Map<String, String> responseHeaders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, List<String>> getRequestHeaders(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<String>> getResponseHeaders(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addInput(String requestId, String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInput(String requestId, Map<String, Object> input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getInput(String requestId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInput(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOutput(String requestId, Map<String, Object> output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOutput(String requestId, String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getOutput(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getOutput(String requestId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTaskExecutionLog(String requestId,
			TaskExecutionModel taskExecutionModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTaskExecutionLog(String requestId,
			Collection<TaskExecutionModel> taskExecutionModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<TaskExecutionModel> getTaskExecutionLogByCurrentTask(
			String requestId, String currentTask, String taskStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TaskExecutionModel> getTaskExecutionLogByNextTask(
			String requestId, String nextTask, String taskStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTaskExecutionStatus(TaskExecutionModel taskExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRequestData(String requestId, APIEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addApiVersionKeyMap(String apiKey, String apiVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateApiKeyVersion(String apiKey, String apiVersion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateApiAccessCode(String apiAccessCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public APIAccessCode getApiAccessCode(String apiAccessCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putApiAccessCode(String apiAccessCode, APIAccessCode accessCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRequestData(String requestId, String commandName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cacheMasterData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMasterDataType(String masterDataTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
