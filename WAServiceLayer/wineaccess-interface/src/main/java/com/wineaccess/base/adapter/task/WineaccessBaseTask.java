package com.wineaccess.base.adapter.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.wineaccess.command.BasePO;
import com.wineaccess.command.ObjectMapperUtils;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.process.task.BaseTask;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.ValidationFailedError;
import com.wineaccess.response.WineaccessError;

/**
 * @author jyoti.yadav@globallogic.com
 */
public abstract class WineaccessBaseTask extends BaseTask{
	
	private Set <WineaccessError> errors = new HashSet<WineaccessError>();
	private Map <String, Object> output = new HashMap<String, Object>();
	//private Gson gson = new Gson();
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectMapper objectMapperWithDateFormat = new ObjectMapper();
	
	public String getPostParameter(ProcessContext pContext) {
		return String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("POST_PARAMETER"));
	}
	
	public String getToken(ProcessContext pContext) {
		return String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("TOKEN"));
	}
	
	public String getApiAccessCode(ProcessContext pContext) {
		return String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("API_ACCESS_CODE"));
	}
	
	public String getContentType(ProcessContext pContext) {
		return String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("CONTENT_TYPE"));
	}
	
	public String getOperationName(ProcessContext pContext) {
		return String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("OP_NAME"));
	}
	
	public String getDateFormat(ProcessContext pContext) {
		return String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("OP_NAME"));
	}
	
	public BasePO getObjectWithDateFormat(ProcessContext pContext, Class claz, String dateFormat) throws Exception, ValidationFailedError {
		return ObjectMapperUtils.getObjectWithDateFormat(getContentType(pContext), getPostParameter(pContext), claz, dateFormat);
		
	}
	
	public BasePO getObject(ProcessContext pContext, Class claz) throws Exception, ValidationFailedError {
		return ObjectMapperUtils.getObject(getContentType(pContext), getPostParameter(pContext), claz);
	}
	
	public WineaccessBaseTask(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}
	
	public Map<String, String> getHeaderParameters() {
		Map<String, String> headers = new HashMap<String, String>();
		return headers;
	}
	
	public boolean isHttpError(int statusCode) {
		if (statusCode == 401) {
			return true;
		} if (statusCode == 415) {
			return true;
		}
		return false;
	}
	
	protected void luceneQueryParsingErrorResponse(String requestId) {
		Response failedResponse = new FailureResponse();
		failedResponse.setStatus(200);
		WineaccessError error = new WineaccessError("USER503", "Invalid or Unrecognized keyword input");
		errors.add(error);
		failedResponse.setErrors(errors);
		
		output.put("FINAL-RESPONSE", failedResponse);
		getDataRepositoryManager().addOutput(requestId, output);
	}
	
	protected void apiProcessingErrorResponse(String requestId,String errorCode,String errorMessage,int statusCode) {
		Response failedResponse = new FailureResponse();
		failedResponse.setStatus(statusCode);
		WineaccessError error = new WineaccessError(errorCode, errorMessage);
		errors.add(error);
		failedResponse.setErrors(errors);
		
		output.put("FINAL-RESPONSE", failedResponse);
		getDataRepositoryManager().addOutput(requestId, output);
	}

	public Set<WineaccessError> getErrors() {
		return errors;
	}

	public void setErrors(Set<WineaccessError> errors) {
		this.errors = errors;
	}

	public Map<String, Object> getOutput() {
		return output;
	}

	public void setOutput(Map<String, Object> output) {
		this.output = output;
	}
}
