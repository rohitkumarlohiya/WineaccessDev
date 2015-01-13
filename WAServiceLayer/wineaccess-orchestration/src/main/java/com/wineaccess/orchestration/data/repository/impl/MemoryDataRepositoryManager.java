package com.wineaccess.orchestration.data.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wineaccess.auditManager.AuditManager;
import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel;
import com.wineaccess.orchestration.workflow.model.TaskExecutionModel.TaskStatus;

/**
 * In Memory Implementation of the interface {@link DataRepositoryManager}
 * @author jyoti.yadav@glollogic.com
 *
 */
public class MemoryDataRepositoryManager implements DataRepositoryManager {
	
	private Map<String, Map<String, List<String>>> inputHeaders = new HashMap<String, Map<String, List<String>>>();
	private Map<String, Map<String, List<String>>> outputHeaders = new HashMap<String, Map<String, List<String>>>();
	private static Map <String, Map<String, Object>> input = new HashMap<String, Map<String,Object>>(); 
	private static Map <String, Map<String, Object>> output = new HashMap<String, Map<String,Object>>();
	
	/**
	 * @see DataRepositoryManager#addInput(String, String, Object)
	 */
	@Override
	public void addInput(String requestId, String name, Object value) {
		if (!input.containsKey(requestId)) {
			input.put(requestId, new HashMap<String,Object>());
		}
		input.get(requestId).put(name, value);
	}
	
	/**
	 * @see DataRepositoryManager#addInput(String, Map)
	 */
	@Override
	public void addInput(String requestId, Map<String, Object> inputParameters) {
		if (!input.containsKey(requestId)) {
			input.put(requestId, new HashMap<String,Object>());
		}
		input.get(requestId).putAll(inputParameters);
	}
	
	/**
	 * @see DataRepositoryManager#getInput(String, String)
	 */
	@Override
	public Object getInput(String requestId, String name) {
		return input.get(requestId) != null ? input.get(requestId).get(name) : null;
	}
	
	/**
	 * @see DataRepositoryManager#getInput(String)
	 */
	@Override
	public Map<String, Object> getInput(String requestId) {
		return input.get(requestId) != null ? input.get(requestId) : null;
	}
	
	/**
	 * @see DataRepositoryManager#addInput(String, Map)
	 */
	@Override
	public void addOutput(String requestId, Map<String, Object> outputParameters) {
		if (!output.containsKey(requestId)) {
			output.put(requestId, new HashMap<String,Object>());
		}
		output.get(requestId).putAll(outputParameters);
	}
	
	/**
	 * @see DataRepositoryManager#addOutput(String, String, Object)
	 */
	@Override
	public void addOutput(String requestId, String name, Object value) {
		if (!output.containsKey(requestId)) {
			output.put(requestId, new HashMap<String,Object>());
		}
		output.get(requestId).put(name, value);
	}
	
	/**
	 * @see DataRepositoryManager#getOutput(String)
	 */
	@Override
	public Map<String, Object> getOutput(String requestId) {
		return output.get(requestId) != null ? output.get(requestId) : null;
	}
	
	/**
	 * @see DataRepositoryManager#getOutput(String, String)
	 */
	@Override
	public Object getOutput(String requestId, String name) {
		return output.get(requestId) != null ? output.get(requestId).get(name) : null;
	}
	
	/**
	 * @see DataRepositoryManager#addRequestHeaders(String, Map)
	 */
	@Override
	public void addRequestHeaders(String requestId, Map<String, String> inputHeaders) {
	}
	
	/**
	 * @see DataRepositoryManager#addResponseHeaders(String, Map)
	 */
	@Override
	public void addResponseHeaders(String requestId, Map<String, String> inputHeaders) {
	}

	@Override
	public Map<String, List<String>> getRequestHeaders(String requestId) {
		return null;
	}

	@Override
	public Map<String, List<String>> getResponseHeaders(String requestId) {
		return null;
	}

	private static Map <String, Collection<TaskExecutionModel>> taskExecutionRepository = new ConcurrentHashMap<String, Collection<TaskExecutionModel>>();
	
	@Override
	public void addTaskExecutionLog(String requestId, TaskExecutionModel taskExecutionModel) {
		
		if (taskExecutionRepository.get(requestId) == null) {
			taskExecutionRepository.put(requestId, new ArrayList<TaskExecutionModel>());
		}
		taskExecutionRepository.get(requestId).add(taskExecutionModel);
	}

	@Override
	public void addTaskExecutionLog(String requestId, Collection<TaskExecutionModel> taskExecutionModel) {
		if (taskExecutionRepository.get(requestId) == null) {
			taskExecutionRepository.put(requestId, new ArrayList<TaskExecutionModel>());
		}
		taskExecutionRepository.get(requestId).addAll(taskExecutionModel);
	}

	@Override
	public Collection<TaskExecutionModel> getTaskExecutionLogByCurrentTask(String requestId, String currentTask, String taskStatus) {
		Collection<TaskExecutionModel> models = new ArrayList<TaskExecutionModel>();
		if (taskExecutionRepository.get(requestId) != null) {
			for (TaskExecutionModel tExecutionModel : taskExecutionRepository.get(requestId)) {
				if (tExecutionModel.getCurrentTask().equals(currentTask) && tExecutionModel.getTaskStatus().equals(taskStatus)) {
					models.add(tExecutionModel);
				}
			}
		}
		return models;
	}

	@Override
	public Collection<TaskExecutionModel> getTaskExecutionLogByNextTask(String requestId, String nextTask, String taskStatus) {
		Collection<TaskExecutionModel> models = new ArrayList<TaskExecutionModel>();
		if (taskExecutionRepository.get(requestId) != null) {
			for (TaskExecutionModel tExecutionModel : taskExecutionRepository.get(requestId)) {
				if (tExecutionModel.getNextTask().equals(nextTask) && tExecutionModel.getTaskStatus().equals(taskStatus)) {
					models.add(tExecutionModel);
				}
			}
		}
		return models;
	}

	@Override
	public void updateTaskExecutionStatus(TaskExecutionModel taskExecution) {
		if (taskExecutionRepository.get(taskExecution.getRequestId()) != null) {
			for (TaskExecutionModel tExecutionModel : taskExecutionRepository.get(taskExecution.getRequestId())) {
				if (tExecutionModel.getCurrentTask().equals(taskExecution.getCurrentTask()) && tExecutionModel.getTaskStatus().equals(TaskStatus.P.name())) {
					tExecutionModel.setTaskStatus(TaskStatus.C.name());
					break;
				}
			}
		}
	}

	@Override
	public void removeRequestData(String requestId, APIEvent event) {
		
		if (event != null) {
			AuditManager.doAudit(input.get(requestId), output.get(requestId), event.name());
		}
		taskExecutionRepository.remove(requestId);
		input.remove(requestId);
		output.remove(requestId);
	}
	
	@Override
	public void removeRequestData(String requestId, String commandName) {
		if (commandName != null) {
			AuditManager.doAudit(input.get(requestId), output.get(requestId), commandName);
		}
		taskExecutionRepository.remove(requestId);
		input.remove(requestId);
		output.remove(requestId);
	}
	
	private Map<String, String> apiKeyVersionRepository = new HashMap<String, String>();

	@Override
	public void addApiVersionKeyMap(String apiKey, String apiVersion) {
		apiKeyVersionRepository.put(apiKey, apiVersion);
	}

	@Override
	public boolean validateApiKeyVersion(String apiKey, String apiVersion) {
		if (apiKeyVersionRepository.get(apiKey) != null ) {
			return apiKeyVersionRepository.get(apiKey).equals(apiVersion) ? true : false;
		}
		return false;
	}
	
	private Map<String, APIAccessCode> apiAccessCodeMap = new HashMap<String, APIAccessCode>();
	
	@Override
	public boolean validateApiAccessCode(String apiAccessCode) {
		return apiAccessCodeMap.containsKey(apiAccessCode) ? true : false;
	}
	
	@Override
	public APIAccessCode getApiAccessCode(String apiAccessCode) {
		return this.apiAccessCodeMap.get(apiAccessCode);
	}
	
	@Override
	public void putApiAccessCode(String apiAccessCode, APIAccessCode accessCode) {
		this.apiAccessCodeMap.put(apiAccessCode, accessCode);
	}
	
	public static Map <String, String> masterDataTypeMap = new HashMap<String, String>();

	@Override
	public void cacheMasterData() {
		for (MasterData masterData : MasterDataRepository.getMasterDatas()) {
			masterDataTypeMap.put(String.valueOf(masterData.getId()), masterData.getMasterDataType().getName());
		}
	}

	@Override
	public String getMasterDataType(String masterDataId) {
		if (masterDataTypeMap.get(masterDataId) == null) {
			MasterData masterData = MasterDataRepository.getMasterDataById(Long.parseLong(masterDataId));
			if (masterData != null) {
				masterDataTypeMap.put(masterDataId, masterData.getMasterDataType().getName());
			}
		}
		return masterDataTypeMap.get(masterDataId);
	}
}
