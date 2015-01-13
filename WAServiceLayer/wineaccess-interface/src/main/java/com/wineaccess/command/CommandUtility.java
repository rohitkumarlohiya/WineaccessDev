package com.wineaccess.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.command.ApplicationCommand.AppCommand;
import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.constants.ApplicationConstants.ROLES;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.orchestration.commad.context.RequestContext;
import com.wineaccess.orchestration.commad.context.RequestHeader;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.pipeline.PipelineManager;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.ValidationFailedError;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.security.login.LoginPO;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;

public abstract class CommandUtility {

	@Context
	private HttpHeaders headers;
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectMapper objectMapperWithDateFormat = new ObjectMapper();
	
	public String getWineaccessToken() {
		List<String> wineaccessToken = headers.getRequestHeader("WINEACCESS_TOKEN");
		
		if (wineaccessToken == null || wineaccessToken.isEmpty()) {
			return null;
		} 
		return wineaccessToken.get(0);
	}
	
	public String getAPIAccessCode() {
		List<String> wineaccessToken = headers.getRequestHeader("API_ACCESS_CODE");
		
		if (wineaccessToken == null || wineaccessToken.isEmpty()) {
			return null;
		} 
		return wineaccessToken.get(0);
	}

	public javax.ws.rs.core.Response validateApiKeyAndVersion(String apiKey,
			String apiVersion, String apiAccessCode, String commandName,
			String postParameter) {
		try {
			if (!getDataRepositoryManager().validateApiKeyVersion(apiKey,
					apiVersion)) {
				return javax.ws.rs.core.Response.ok(
						prepareFailureApiKeyResponse()).build();
			}
			
			if (AppCommand.Logout.name().equals(commandName)) {
				String token = getWineaccessToken();
				if(token != null){
					TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
					WineAccessUserDetails wineAccessUserDetails = tokenUtils.getUserFromToken(token);
					UserModel userModel = UserRepository.getByUserId(wineAccessUserDetails.getUserId());
					boolean isSuperAdmin = false;
					for (UserRoles userRole : userModel.getUserRoles()) {
						if (userRole.getRoleName().equals(
								ROLES.ROLE_SUPER_ADMIN.name())) {
							isSuperAdmin = true;
							break;
						}
					}
					if (!isSuperAdmin) {
						if (apiAccessCode == null || apiAccessCode.isEmpty()) {
							return ApplicationUtils.generateFailureResponse(
									SystemErrorCode.INVALID_API_ACCESSS,
									SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
						} else if (!getDataRepositoryManager()
								.validateApiAccessCode(apiAccessCode)) {
							return ApplicationUtils.generateFailureResponse(
									SystemErrorCode.INVALID_API_ACCESSS,
									SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
						}
					}else{
						return null;
					}
				}
			}
			
			if (AppCommand.Login.name().equals(commandName)) {
				LoginPO loginPO = (LoginPO) getObject(postParameter,
						LoginPO.class);

				UserModel userModel = UserRepository.getByUserNamePassword(
						loginPO.getUsername(),
						ApplicationUtils.getSHAHex(loginPO.getPassword()));
				if (userModel == null) {
					
					userModel = UserRepository.getByUserName(loginPO.getUsername());
					if (userModel != null && userModel.getIsDeleted()) {
						return javax.ws.rs.core.Response.ok(
								ApplicationUtils.errorMessageGenerate(
										SystemErrorCode.USER_DELETED,
										SystemErrorCode.USER_DELETED_TEXT,
										200)).build();
					}
					return javax.ws.rs.core.Response.ok(
							ApplicationUtils.errorMessageGenerate(
									SystemErrorCode.INVALID_USER_PASSWORD,
									SystemErrorCode.INVALID_USER_PASSWORD_TEXT,
									200)).build();
				}

				boolean isSuperAdmin = false;
				for (UserRoles userRole : userModel.getUserRoles()) {
					if (userRole.getRoleName().equals(
							ROLES.ROLE_SUPER_ADMIN.name())) {
						isSuperAdmin = true;
						break;
					}
				}
				if (!isSuperAdmin) {
					if (apiAccessCode == null || apiAccessCode.isEmpty()) {
						return ApplicationUtils.generateFailureResponse(
								SystemErrorCode.INVALID_API_ACCESSS,
								SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
					} else if (!getDataRepositoryManager()
							.validateApiAccessCode(apiAccessCode)) {
						return ApplicationUtils.generateFailureResponse(
								SystemErrorCode.INVALID_API_ACCESSS,
								SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
					}
				}
			} else if (!getDataRepositoryManager().validateApiAccessCode(
					apiAccessCode)) {
				return javax.ws.rs.core.Response.ok(
						prepareFailureApiKeyResponse()).build();
			}
			return null;
		} catch (Exception ex) {
			if (ex instanceof ValidationFailedError) {
				return javax.ws.rs.core.Response.ok(((ValidationFailedError) ex).getFailedResponse()).build();
			}
			Set<WineaccessError> errors = new HashSet<WineaccessError>();
			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			WineaccessError error = new WineaccessError("SYSTEM-ERROR",
					"System Error");
			errors.add(error);
			failedResponse.setErrors(errors);
			return javax.ws.rs.core.Response.ok(failedResponse).build();
		}
	}

	public boolean validateApiKeyAndVersion(String apiKey, String apiVersion) {
		List<String> apiAccessCode = headers
				.getRequestHeader("API_ACCESS_CODE");
		if (apiAccessCode == null || apiAccessCode.isEmpty()) {
			return false;
		} else if (!getDataRepositoryManager().validateApiAccessCode(
				apiAccessCode.get(0))) {
			return false;
		}
		return getDataRepositoryManager().validateApiKeyVersion(apiKey,
				apiVersion);
	}

	public javax.ws.rs.core.Response validateApiKeyAndVersion(String apiKey,
			String apiVersion, BasePO basePO) {
		if (!getDataRepositoryManager().validateApiKeyVersion(apiKey,
				apiVersion)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse())
					.build();
		}
		List<String> errors = basePO.validate();
		if (!errors.isEmpty()) {
			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			for (String error : errors) {

				WineaccessError wineError = new WineaccessError(
						error.split("\\n")[0], error.split("\\n")[1]);
				failedResponse.addError(wineError);
			}
			return javax.ws.rs.core.Response.ok(failedResponse).build();
		}

		if (basePO instanceof LoginPO) {
			UserModel userModel = UserRepository.getByUserNamePassword(
					((LoginPO) basePO).getUsername(), ApplicationUtils
							.getSHAHex(((LoginPO) basePO).getPassword()));

			if (userModel == null) {
				return javax.ws.rs.core.Response
						.ok(ApplicationUtils
								.errorMessageGenerate(
										SystemErrorCode.INVALID_USER_PASSWORD,
										SystemErrorCode.INVALID_USER_PASSWORD_TEXT,
										200)).build();
			}
			boolean isSuperAdmin = false;
			for (UserRoles userRole : userModel.getUserRoles()) {
				if (userRole.getRoleName()
						.equals(ROLES.ROLE_SUPER_ADMIN.name())) {
					isSuperAdmin = true;
					break;
				}
			}
			if (!isSuperAdmin) {
				List<String> apiAccessCode = headers
						.getRequestHeader("API_ACCESS_CODE");

				if (apiAccessCode == null || apiAccessCode.isEmpty()) {
					return ApplicationUtils.generateFailureResponse(
							SystemErrorCode.INVALID_API_ACCESSS,
							SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
				} else if (!getDataRepositoryManager().validateApiAccessCode(
						apiAccessCode.get(0))) {
					return ApplicationUtils.generateFailureResponse(
							SystemErrorCode.INVALID_API_ACCESSS,
							SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
				}
			}
		} else {
			List<String> apiAccessCode = headers
					.getRequestHeader("API_ACCESS_CODE");

			if (apiAccessCode == null || apiAccessCode.isEmpty()) {
				return ApplicationUtils.generateFailureResponse(
						SystemErrorCode.INVALID_API_ACCESSS,
						SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
			} else if (!getDataRepositoryManager().validateApiAccessCode(
					apiAccessCode.get(0))) {
				return ApplicationUtils.generateFailureResponse(
						SystemErrorCode.INVALID_API_ACCESSS,
						SystemErrorCode.INVALID_API_ACCESSS_TEXT, 200);
			}
		}
		return null;
	}

	public Response prepareFailureApiKeyResponse() {
		WineaccessError error = new WineaccessError(
				SystemErrorCode.INVALID_API_KEY_VERSION,
				SystemErrorCode.INVALID_API_KEY_VERSION_TEXT);

		Response failedResponse = new FailureResponse();
		failedResponse.setStatus(200);
		failedResponse.addError(error);
		return failedResponse;
	}

	/**
	 * Return pipeline manager.
	 * 
	 * @return
	 */
	public PipelineManager getPipelineManager() {
		return (PipelineManager) CoreBeanFactory.getBean(PropertyholderUtils
				.getStringProperty(ComponentConstants.PIPELINE_MANAGER));
	}

	public javax.ws.rs.core.Response processRequest(final String apiKey,
			final String version, final Map<String, Object> inputParameters,
			final String processDefinitionName, final APIEvent apiEvent) {

		javax.ws.rs.core.Response response = null;
		if (!validateApiKeyAndVersion(apiKey, version)) {

			response = javax.ws.rs.core.Response.ok(
					prepareFailureApiKeyResponse()).build();
		} else {

			response = getResponse(version, inputParameters,
					processDefinitionName, apiEvent);
		}
		return response;
	}
	
	public javax.ws.rs.core.Response processRequestResponse(final String version, final String processDefinitionName, final AppCommand appCommand, String commandParameterAsString) {
		RequestContext rContext = new RequestContext(processDefinitionName, version, 	commandParameterAsString, getWineaccessToken(), getAPIAccessCode(), getAcceptType());
		getPipelineManager().process(rContext);
		com.wineaccess.response.Response adapterResponse = (com.wineaccess.response.Response) getDataRepositoryManager().getOutput(rContext.getMessageHeader().getRequestId(), ApplicationConstants.FINAL_RESPONSE);
		getDataRepositoryManager().removeRequestData(rContext.getMessageHeader().getRequestId(), appCommand.name());
		return javax.ws.rs.core.Response.ok(adapterResponse).build();
	}
	
	public javax.ws.rs.core.Response processRequestResponse(final String version, final String processDefinitionName, final AppCommand appCommand, String commandParameterAsString, String opName) {
		RequestContext rContext = new RequestContext(processDefinitionName, version, 	commandParameterAsString, getWineaccessToken(), getAPIAccessCode(), getAcceptType(), opName);
		getPipelineManager().process(rContext);
		com.wineaccess.response.Response adapterResponse = (com.wineaccess.response.Response) getDataRepositoryManager().getOutput(rContext.getMessageHeader().getRequestId(), ApplicationConstants.FINAL_RESPONSE);
		getDataRepositoryManager().removeRequestData(rContext.getMessageHeader().getRequestId(), appCommand.name());
		return javax.ws.rs.core.Response.ok(adapterResponse).build();
	}
	
	public javax.ws.rs.core.Response processRequestResponse(final String version, final String processDefinitionName, final AppCommand appCommand, String commandParameterAsString, String opName, String dateFormat) {
		RequestContext rContext = new RequestContext(processDefinitionName, version, 	commandParameterAsString, getWineaccessToken(), getAPIAccessCode(), getAcceptType(), opName,dateFormat);
		getPipelineManager().process(rContext);
		com.wineaccess.response.Response adapterResponse = (com.wineaccess.response.Response) getDataRepositoryManager().getOutput(rContext.getMessageHeader().getRequestId(), ApplicationConstants.FINAL_RESPONSE);
		getDataRepositoryManager().removeRequestData(rContext.getMessageHeader().getRequestId(), appCommand.name());
		return javax.ws.rs.core.Response.ok(adapterResponse).build();
	}
	
	public javax.ws.rs.core.Response processRequest(final String version, final Map<String, Object> inputParameters, final String processDefinitionName, final APIEvent apiEvent) {
		return getResponse(version, inputParameters, processDefinitionName, apiEvent);
	}

	public javax.ws.rs.core.Response processRequest(final String apiKey,
			final String version, final Map<String, Object> inputParameters,
			final String processDefinitionName, final APIEvent apiEvent,
			BasePO basePO) {

		javax.ws.rs.core.Response response = null;
		response = validateApiKeyAndVersion(apiKey, version, basePO);
		if (response == null) {

			response = getResponse(version, inputParameters,
					processDefinitionName, apiEvent);
		}
		return response;
	}

	private javax.ws.rs.core.Response getResponse(final String version,
			final Map<String, Object> inputParameters,
			final String processDefinitionName, final APIEvent apiEvent) {
		javax.ws.rs.core.Response response;
		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader,
				processDefinitionName, version, new HashMap<String, Object>());

		getDataRepositoryManager().addInput(
				rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		com.wineaccess.response.Response adapterResponse = (com.wineaccess.response.Response) getDataRepositoryManager()
				.getOutput(rHeader.getRequestId(),
						ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),
				apiEvent);

		response = javax.ws.rs.core.Response.ok(adapterResponse).build();
		return response;
	}
	
	public BasePO getObjectWithDateFormat(String postParameter, Class claz, String dateFormat) throws Exception, ValidationFailedError {
		return ObjectMapperUtils.getObjectWithDateFormat(getAcceptType(), postParameter, claz, dateFormat);
	}

	public BasePO getObject(String postParameter, Class claz) throws Exception, ValidationFailedError {
		return ObjectMapperUtils.getObject(getAcceptType(), postParameter, claz);
	}

	private String getAcceptType() {
		MediaType accepts = headers.getMediaType();
		if (accepts != null && accepts.getSubtype().equals("xml")) {
			return "application/xml";
		} else {
			return "application/json";
		}
	}
	
	/**
	 * Return the data repository manager interface.
	 * @return
	 */
	public DataRepositoryManager getDataRepositoryManager() {
		return (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
	}
	
	public  UserModel getCurrentUserDetails(){
		String token = getWineaccessToken();
		if(token != null){
			TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
			WineAccessUserDetails wineAccessUserDetails = tokenUtils.getUserFromToken(token);
			return UserRepository.getByUserId(wineAccessUserDetails.getUserId());
		}else{
			return null;
		}
			
}
}
