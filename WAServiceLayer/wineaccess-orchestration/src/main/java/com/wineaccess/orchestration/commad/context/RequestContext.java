package com.wineaccess.orchestration.commad.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * An object that captures the basic criteria that gets passed in a request.
 * Contexts are recursive. There can be multiple sub-contexts attached to a
 * context.
 * 
 * @author jyoti.yadav@globallogic.com
 * 
 */
public class RequestContext implements Serializable {
	
	private static final long serialVersionUID = -4625153833521480616L;

	protected RequestHeader messageHeader = new RequestHeader();
	
	protected String serviceCommand;
	
	protected String versionId;
	
	protected Map<String, Object> contextParameters = new HashMap<String, Object>();
	
	public RequestContext (RequestHeader messageHeader, String serviceCommand, String version, Map<String, Object> contextParameters) {
		this.messageHeader = messageHeader;
		this.serviceCommand = serviceCommand;
		this.contextParameters = contextParameters;
		this.versionId = version;
	}
	
	
	public RequestContext (String serviceCommand, String version, String commandParameterAsString, String token, String apiAccessCode,String contentType) {
		this.messageHeader = new RequestHeader();
		this.serviceCommand = serviceCommand;
		this.versionId = version;
		
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		
		inputParameters.put("POST_PARAMETER", commandParameterAsString);
		inputParameters.put("TOKEN", token);
		inputParameters.put("API_ACCESS_CODE", apiAccessCode);
		inputParameters.put("CONTENT_TYPE", contentType);
		getDataRepositoryManager().addInput(messageHeader.getRequestId(), inputParameters);
	}
	
	
	public RequestContext (String serviceCommand, String version, String commandParameterAsString, String token, String apiAccessCode,String contentType, String opName) {
		this.messageHeader = new RequestHeader();
		this.serviceCommand = serviceCommand;
		this.versionId = version;
		
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		
		inputParameters.put("POST_PARAMETER", commandParameterAsString);
		inputParameters.put("TOKEN", token);
		inputParameters.put("API_ACCESS_CODE", apiAccessCode);
		inputParameters.put("CONTENT_TYPE", contentType);
		inputParameters.put("OP_NAME", opName);
		getDataRepositoryManager().addInput(messageHeader.getRequestId(), inputParameters);
	}
	
	public RequestContext(String serviceCommand, String version,
			String commandParameterAsString, String token,
			String apiAccessCode, String contentType, String opName,
			String dateFormat) {
		this.messageHeader = new RequestHeader();
		this.serviceCommand = serviceCommand;
		this.versionId = version;
		
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		
		inputParameters.put("POST_PARAMETER", commandParameterAsString);
		inputParameters.put("TOKEN", token);
		inputParameters.put("API_ACCESS_CODE", apiAccessCode);
		inputParameters.put("CONTENT_TYPE", contentType);
		inputParameters.put("OP_NAME", opName);
		inputParameters.put("DATE_FORMAT", dateFormat);
		getDataRepositoryManager().addInput(messageHeader.getRequestId(), inputParameters);
	}

	
	
	public DataRepositoryManager getDataRepositoryManager() {
		return (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
	}

	public RequestHeader getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(RequestHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public String getServiceCommand() {
		return serviceCommand;
	}

	public void setServiceCommand(String serviceCommand) {
		this.serviceCommand = serviceCommand;
	}

	public Map<String, Object> getContextParameters() {
		return contextParameters;
	}

	public void setContextParameters(Map<String, Object> contextParameters) {
		this.contextParameters = contextParameters;
	}

	public String getVersionId() {
		return versionId;
	}
}
