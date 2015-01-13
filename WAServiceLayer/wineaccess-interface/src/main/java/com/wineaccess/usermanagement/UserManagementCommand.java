package com.wineaccess.usermanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.command.BaseCommand;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.commad.context.RequestContext;
import com.wineaccess.orchestration.commad.context.RequestHeader;
import com.wineaccess.response.Response;



@Path("/{version}/user")
public class UserManagementCommand extends BaseCommand {	
	final String PROCCESS_DEF_NAME = ApplicationConstants.PROCESSDEF.USER_MANAGEMENT
			.getProcessDefinationName();

	/**
	 * @param apiKey
	 * @param version
	 * @param userManage
	 * @return
	 */
	@Path("/createuser")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response createUser(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, UserManagementPO userManage) {

		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADD);
		inputParameters.put("UserManagementPO", userManage);

		String [] property = {"firstName","lastName","email","zipCode","countryId"};

		boolean isValidInput = UserManagementHelper.isValidUserPO(userManage,property);

		if(!isValidInput)
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();

		if(!UserManagementHelper.validatePhoneFormat(userManage))
			return javax.ws.rs.core.Response.ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT, SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT, 200)).build();
		
		if(userManage.getCreditCard()!=null){
			for(CreditCardModel cardModel:userManage.getCreditCard()){
				boolean isValidExpirationDate = ValidationUtil.validateContent(cardModel.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
				if (!isValidExpirationDate) {
					return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, 200);
				}
			}
		}
		
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(), APIEvent.CREATE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}


	/**
	 * @param apiKey
	 * @param version
	 * @param userManage
	 * @return
	 */
	@Path("/edituser")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response updateUser(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, UserManagementPO userManage) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.UPDATE);
		inputParameters.put("UserManagementPO", userManage);


		String [] property = {"userId","countryId","email","firstName","lastName","email","zipCode"};

		boolean isValidInput = UserManagementHelper.isValidUserPO(userManage,property);

		if(!isValidInput)
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();

		if(!UserManagementHelper.validatePhoneFormat(userManage))
			return javax.ws.rs.core.Response.ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT, SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT, 200)).build();
		
		if(userManage.getCreditCard()!=null){
			for(CreditCardModel cardModel:userManage.getCreditCard()){
				boolean isValidExpirationDate = ValidationUtil.validateContent(cardModel.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
				if (!isValidExpirationDate) {
					return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, 200);
				}
			}
		}
		
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.UPDATE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	/**
	 * @param apiKey
	 * @param version
	 * @param cloneUserPO
	 * @return
	 */
	@Path("/cloneuser")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response cloneUser(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, CloneUserPO cloneUserPO) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.CLONE);
		String [] property = {"toEmailId","fromEmailId"};

		boolean isValidInput = ApplicationUtils.validateMandatoryFields(cloneUserPO, property, CloneUserPO.class);

		if(!isValidInput)
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();

		inputParameters.put("CloneUserPO", cloneUserPO);
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.CLONE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	/**
	 * @param apiKey
	 * @param version
	 * @param modifystatusPO
	 * @return
	 */
	@Path("/enableuser")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response enableUsers(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, ModifystatusPO modifystatusPO) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ENABLEUSERS);
		//	List<Long> disableUserList = modifystatusPO.getDisableUsersList();
		List<Long> enableUsersList = modifystatusPO.getEnableUsersList();
		if(enableUsersList == null || enableUsersList.isEmpty())
		{
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}

		inputParameters.put("EnableUsersList", enableUsersList);
		inputParameters.put("isforceDelete", modifystatusPO.getIsforceDelete());
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.ENABLE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	@Path("/disableuser")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response disableUsers(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, ModifystatusPO modifystatusPO) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DISABLEUSERS);
		//	List<Long> disableUserList = modifystatusPO.getDisableUsersList();
		List<Long> disableUserList = modifystatusPO.getDisableUsersList();
		if(disableUserList == null || disableUserList.isEmpty())
		{
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}

		inputParameters.put("DisableUsersList", disableUserList);
		inputParameters.put("isforceDelete", modifystatusPO.getIsforceDelete());
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.DISABLE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	/**
	 * @param apiKey
	 * @param version
	 * @param modifystatusPO
	 * @return
	 */
	@Path("/deleteuser")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response deleteUsers(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, ModifystatusPO modifystatusPO) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DELETEUSERS);
		List<Long> deleteUsersList = modifystatusPO.getDeleteUsersList();
		if(deleteUsersList == null || deleteUsersList.isEmpty())
		{
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}

		inputParameters.put("DeleteUsersList", deleteUsersList);
		inputParameters.put("isforceDelete", modifystatusPO.getIsforceDelete());
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.DELETE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	@Path("/deleteaddress")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response deleteAddress(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, DeleteComponentPO deleteComponentPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DELETEADDRESS);		

		inputParameters.put("DeleteComponentPO", deleteComponentPO);

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.DELETE_ADDRESS,deleteComponentPO);

	}

	@Path("/deletecreditcard")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response deleteCreditCard(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, DeleteComponentPO deleteComponentPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DELETECREDITCARD);		

		inputParameters.put("DeleteComponentPO", deleteComponentPO);

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.DELETE_ADDRESS,deleteComponentPO);

	}

	@Path("/userdetail")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response userDetail(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, @QueryParam("id")Long id) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.USERDETAIL);

		if(id==null)
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();


		inputParameters.put("id", id);
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);
		//JAXB.marshal(new UserManagementPO(), System.out);
		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.USER_DETAIL);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	@Path("/mergeuser")
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response mergeUser(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, MergeUserPO mergeUserPO) {


		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}

		RequestHeader rHeader = new RequestHeader();
		RequestContext rContext = new RequestContext(rHeader, "UserManagementProcessDefinition", version, new HashMap<String, Object>());

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.MERGEUSER);

		if(mergeUserPO == null || mergeUserPO.getToBeMergedUser()==null || mergeUserPO.getUserInWhichTobeMerged()==null)
		{
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}

		inputParameters.put("MergeUserPO", mergeUserPO);
		getDataRepositoryManager().addInput(rContext.getMessageHeader().getRequestId(), inputParameters);

		getPipelineManager().process(rContext);

		Response response = (Response) getDataRepositoryManager().getOutput(rHeader.getRequestId(), ApplicationConstants.FINAL_RESPONSE);

		getDataRepositoryManager().removeRequestData(rHeader.getRequestId(),APIEvent.MERGE_USER);

		return javax.ws.rs.core.Response.ok(response).build();
	}

	/**
	 * @param apiKey
	 * @param version
	 * @param modifystatusPO
	 * @return
	 */
	@Path("/resetPassword")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response resetPassword(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, ResetPasswordPO passwordPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.RESET_PASSWORD);
		List<Long> userIds = passwordPO.getUserIds();
		if(userIds == null || userIds.isEmpty())
		{
			return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		
		inputParameters.put("resetUserIds", userIds);
		inputParameters.put("isforceDelete", passwordPO.getIsforceDelete());

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.RESET_PASSWORD_EMAIL,passwordPO);
	}

	@Path("/addaddress")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response addAddressAtomic(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, UserAddressModelAtomicPO addAddressPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADD_ADDRESS);		

		inputParameters.put("AddAddressPO", addAddressPO);

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.ADD_ADDRESS,addAddressPO);

	}
	
	@Path("/updateaddress")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response updateAddressAtomic(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, UserEditAddressModelAtomicPO editAddressPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.EDIT_ADDRESS);		

		inputParameters.put("EditAddressPO", editAddressPO);

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.EDIT_ADDRESS,editAddressPO);

	}
	
	@Path("/addressdetail")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response addressDetailAtomic(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, AddressDetailAtomicPO addressDetailPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADDRESS_DETAIL);		

		inputParameters.put("AddressDetailAtomicPO", addressDetailPO);

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.ADDRESS_DETAIL,addressDetailPO);

	}
	
	@Path("/addcreditcard")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response addCCAtomic(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, CreditCardModelAtomicPO addCreditCardPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADD_CREDIT_CARD);		

		inputParameters.put("AddCreditCardPO", addCreditCardPO);
		boolean isValidContent = ValidationUtil.validateContent(addCreditCardPO.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
		if (!isValidContent) {
			return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, 200);
		} else {
			return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.ADD_CREDIT_CARD,addCreditCardPO);
		}

	}
	
	@Path("/updatecreditcard")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response updateCreditCard(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, CreditCardEditModelAtomicPO editCreditCardPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.EDIT_CREDIT_CARD);		

		inputParameters.put("EditCreditCardPO", editCreditCardPO);
		boolean isValidContent = ValidationUtil.validateContent(editCreditCardPO.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
		if (!isValidContent) {
			return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, 200);
		} else {
			return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.EDIT_CREDIT_CARD,editCreditCardPO);

		}
		
	}
	
	@Path("/creditcardetail")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response creditCardDetail(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version, CreditCardDetailAtomicPO creditCardDetailPO) {

		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.CREDIT_CARD_DETAIL);		

		inputParameters.put("CreditCardDetailPO", creditCardDetailPO);

		return processRequest(apiKey, version, inputParameters,	PROCCESS_DEF_NAME, APIEvent.CREDIT_CARD_DETAIL,creditCardDetailPO);

	}
}
