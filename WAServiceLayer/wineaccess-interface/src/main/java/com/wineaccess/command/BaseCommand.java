package com.wineaccess.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.application.command.ApplicationCommand.AppCommand;
import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.application.contact.ContactDetailsPO;
import com.wineaccess.application.contact.ContactsDetailListingPO;
import com.wineaccess.application.contact.DeleteContactDetailsPO;
import com.wineaccess.application.contact.EditContactDetailsPO;
import com.wineaccess.application.contact.ViewContactDetailsPO;
import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.commad.search.users.UserSearchPO;
import com.wineaccess.command.api.access.code.APIAccessCodePO;
import com.wineaccess.command.api.access.code.APIAccessCodeVO;
import com.wineaccess.command.api.access.code.APIAccessDAO;
import com.wineaccess.command.search.importer.ImporterAdvanceSearchPO;
import com.wineaccess.command.search.importer.ImporterSearchPO;
import com.wineaccess.command.search.sampler.SamplerAdvSearchPO;
import com.wineaccess.command.search.sampler.SamplerSearchPO;
import com.wineaccess.command.search.winery.WineryAdvanceSearchPO;
import com.wineaccess.command.search.winery.WinerySearchPO;
import com.wineaccess.common.DetailWithDeletedData;
import com.wineaccess.common.IdentityPO;
import com.wineaccess.common.NamePO;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.constants.EnumTypes.EmailTemplateType;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateRepository;
import com.wineaccess.data.model.profile.UserProfilePasswordPO;
import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.emailtemplate.EmailTemplatePO;
import com.wineaccess.emailtemplate.EmailTemplateSearchPO;
import com.wineaccess.emailtemplate.MultipleDeleteEmailTemplatePO;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeListPO;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeSearchPO;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.registration.RegistrationPO;
import com.wineaccess.registration.RegistrationSSOHelper;
import com.wineaccess.registration.SignupHelper;
import com.wineaccess.registration.SignupPO;
import com.wineaccess.response.ExceptionHandlerUtil;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.SuccessResponse;
import com.wineaccess.response.ValidationFailedError;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.responsys.ResponsysPO;
import com.wineaccess.security.login.history.LoginHistoryByEmailPO;
import com.wineaccess.security.login.history.LoginHistoryByIdPO;
import com.wineaccess.security.masterdata.MasterDataPO;
import com.wineaccess.security.masterdata.MultipleDeleteMasterDataPO;
import com.wineaccess.security.masterdata.UpdateMasterDataByIdPO;
import com.wineaccess.security.masterdatatype.ListMasterDataTypesPO;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;
import com.wineaccess.service.newsletter.NewsletterDeletePO;
import com.wineaccess.service.newsletter.NewsletterGetByIdPO;
import com.wineaccess.service.newsletter.NewsletterPO;
import com.wineaccess.service.newsletter.NewsletterSearchPO;
import com.wineaccess.user.activation.ResendActivationMailPO;
import com.wineaccess.user.activity.log.ActivityLogHelper;
import com.wineaccess.user.activity.log.ActivityLogSearchPO;
import com.wineaccess.user.activity.log.SessionDetailsForUserBySessionPO;
import com.wineaccess.user.activity.log.SessionDetailsForUserPO;
import com.wineaccess.useremaillog.UserEmailLogsSearchPO;
import com.wineaccess.usermanagement.AddressDetailAtomicPO;
import com.wineaccess.usermanagement.CloneUserPO;
import com.wineaccess.usermanagement.CreditCardDetailAtomicPO;
import com.wineaccess.usermanagement.CreditCardEditModelAtomicPO;
import com.wineaccess.usermanagement.CreditCardModel;
import com.wineaccess.usermanagement.CreditCardModelAtomicPO;
import com.wineaccess.usermanagement.DeleteComponentPO;
import com.wineaccess.usermanagement.MergeUserPO;
import com.wineaccess.usermanagement.ModifystatusPO;
import com.wineaccess.usermanagement.UserAddressModelAtomicPO;
import com.wineaccess.usermanagement.UserEditAddressModelAtomicPO;
import com.wineaccess.usermanagement.UserManagementHelper;
import com.wineaccess.usermanagement.UserManagementPO;
import com.wineaccess.util.command.CountryByIdPO;
import com.wineaccess.util.command.ListCityByIdPO;
import com.wineaccess.util.command.StateByCountryIdPO;
import com.wineaccess.util.command.StateByIdPO;
import com.wineaccess.util.notification.EmailNotifier;
import com.wineaccess.util.notification.PlaceHolderUtil;
import com.wineaccess.wine.WineAdvanceSearchPO;
import com.wineaccess.wine.WineBasicSearchPO;

/**
 * @author jyoti.yadav@globallogic.com
 */
public abstract class BaseCommand extends CommandUtility {


    final int HTTP_ERROR_CODE = 200;
    final String PROCCESS_DEF_NAME =  ApplicationConstants.PROCESSDEF.USER_PROFILE.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_EMAIL_TEMP = ApplicationConstants.PROCESSDEF.EMAIL_TEMPLATE.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_EMAIL_TEMPLATE_TYPE = ApplicationConstants.PROCESSDEF.EMAIL_TEMPLATE_TYPE.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_LOGIN_HISTORY = ApplicationConstants.PROCESSDEF.LOGIN_HISTORY.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_RES = ApplicationConstants.PROCESSDEF.RESPONSYS.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_RESEND_EMAIL = ApplicationConstants.PROCESSDEF.RESEND_ACTIVATION_MAIL.getProcessDefinationName(); 
    final String PROCCESS_DEF_NAME_USER_ACTIVATION = ApplicationConstants.PROCESSDEF.USER_ACTIVATION.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_NEWS_LETTER = ApplicationConstants.PROCESSDEF.NEWS_LETTER.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_ACTIVITI_LOG = ApplicationConstants.PROCESSDEF.ACTIVITY_LOG.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_USER_COMMENT = ApplicationConstants.PROCESSDEF.USER_COMMENTS.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_CITY = ApplicationConstants.PROCESSDEF.CITY_LIST.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_COUNTRY = ApplicationConstants.PROCESSDEF.COUNTRY_LIST.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_STATE = ApplicationConstants.PROCESSDEF.STATE_LIST.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_EMAIL_LOG = ApplicationConstants.PROCESSDEF.USER_EMAIL_LOG.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_USER = ApplicationConstants.PROCESSDEF.USER_MANAGEMENT.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_IMPORTER = ApplicationConstants.PROCESSDEF.IMPORTER.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_CONTACT_DETAILS = ApplicationConstants.PROCESSDEF.CONTACT_DETAILS.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WI_ADDRESS = ApplicationConstants.PROCESSDEF.WI_ADDRESS.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINERY = ApplicationConstants.PROCESSDEF.WINERY.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINE = ApplicationConstants.PROCESSDEF.WINE.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINE_LOGISTIC = ApplicationConstants.PROCESSDEF.WINE_LOGISTIC.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINERY_OWS = ApplicationConstants.PROCESSDEF.WINERY_OWS.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINERY_LICENSE_DETAIL = ApplicationConstants.PROCESSDEF.WINERY_LICENSE_DETAIL.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINE_PERMIT = ApplicationConstants.PROCESSDEF.WINE_PERMIT.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINERY_PERMIT = ApplicationConstants.PROCESSDEF.WINERY_PERMIT.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINE_OWS = ApplicationConstants.PROCESSDEF.WINE_OWS.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WINE_LICENSE_DETAIL = ApplicationConstants.PROCESSDEF.WINE_LICENSE_DETAIL.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_REQUISITION = ApplicationConstants.PROCESSDEF.REQUISITION.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_WAREHOUSE = ApplicationConstants.PROCESSDEF.WAREHOUSE.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_DISTRIBUTION_CENTRE = ApplicationConstants.PROCESSDEF.DISTRIBUTION_CENTRE.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_SAMPLER = ApplicationConstants.PROCESSDEF.SAMPLER.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_SAMPLER_SEARCH = ApplicationConstants.PROCESSDEF.SAMPLER_SEARCH.getProcessDefinationName();
    final String PROCCESS_DEF_NAME_ADV_SAMPLER_SEARCH = ApplicationConstants.PROCESSDEF.SAMPLER_ADV_SEARCH.getProcessDefinationName();


    private static Log logger = LogFactory.getLog(BaseCommand.class);

    public javax.ws.rs.core.Response processRequest(String commandName, String version, String commandParameter) {
	try {
	    if (commandName.equals(AppCommand.Login.name())) {

		return processRequestResponse(version, "LoginProcessDefinition", AppCommand.Login, commandParameter);
	    } else if (commandName.equals(AppCommand.SearchMasterData.name())) {

		return processRequestResponse(version, "MasterDataNormalSearchDefinition", AppCommand.SearchMasterData, commandParameter);
	    } else if (commandName.equals(AppCommand.SearchMasterDataByTypeId.name())) {

		return processRequestResponse(version, "MasterDataNormalSearchDefinition", AppCommand.SearchMasterDataByTypeId, commandParameter);
	    } else if (commandName.equals(AppCommand.SearchMasterDataType.name())) {

		return processRequestResponse(version, "MasterDataTypeNormalSearchDefinition", AppCommand.SearchMasterDataType, commandParameter);
	    } else if (commandName.equals(AppCommand.SearchUser.name())) {

		UserSearchPO searchPO = (UserSearchPO) getObject(commandParameter, UserSearchPO.class);
		if(searchPO.getSearchType().equals("A")) {
		    return processRequestResponse(version, "UserAdvanceSearchDefinition", AppCommand.SearchUser, commandParameter);
		} else {
		    return processRequestResponse(version, "UserNormalSearchDefinition", AppCommand.SearchUser, commandParameter);
		}

	    } else if (commandName.equals(AppCommand.UpdateUserPasswordById.name())) {
	    	
	    	TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
			WineAccessUserDetails wineAccessUserDetails = tokenUtils.getUserFromToken(getWineaccessToken());
			UserProfilePasswordPO userProfilePasswordPO = (UserProfilePasswordPO) getObject(commandParameter, UserProfilePasswordPO.class);
			
			if (!(userProfilePasswordPO.getUserId() == wineAccessUserDetails.getUserId())){
				return javax.ws.rs.core.Response.ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALIDE_USER_ID_PASSED, SystemErrorCode.INVALIDE_USER_ID_PASSED_TEXT, 200)).build();
			}
	    	
	    	return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UpdateUserPasswordById, commandParameter,OperationNameEnum.UPDATEPASSWORDBYID.name());

	    } else if (commandName.equals(AppCommand.UpdateUserPasswordByEmail.name())) {

	    	return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UpdateUserPasswordByEmail, commandParameter,OperationNameEnum.UPDATEPASSWORDBYEMAIL.name());
	    } else if (commandName.equals(AppCommand.UserListById.name())) {

	    	return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UserListById, commandParameter,OperationNameEnum.LISTBYID.name());
	    } else if (commandName.equals(AppCommand.UserListByEmail.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UserListByEmail, commandParameter,OperationNameEnum.LISTBYEMAIL.name());
	    } else if (commandName.equals(AppCommand.UpdateUserDetailById.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UpdateUserDetailById, commandParameter,OperationNameEnum.UPDATEBYID.name());
	    } else if (commandName.equals(AppCommand.UpdateUserDetailByEmail.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UpdateUserDetailByEmail, commandParameter,OperationNameEnum.UPDATEBYEMAIL.name());
	    } else if (commandName.equals(AppCommand.ResetPasswordByEmail.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.ResetPasswordByEmail, commandParameter,OperationNameEnum.RESET_PASSWORD.name());
	    } else if (commandName.equals(AppCommand.ListEmailTemplate.name())) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.LIST_EMAIL_TEMPLATE);
	    } else if (commandName.equals(AppCommand.ListEmailTemplateById.name())) {
		IdentityPO emailTemplateByIdPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_BY_ID);
		inputParameters.put("id", emailTemplateByIdPO.getId());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.GET_BY_ID_EMAIL_TEMPLATE);
	    }  else if (commandName.equals(AppCommand.SearchEmailTemplate.name())) {
		EmailTemplateSearchPO searchPO = (EmailTemplateSearchPO) getObject(commandParameter, EmailTemplateSearchPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.BASIC_SEARCH_BY_KEYWORD);
		inputParameters.put("offSet", searchPO.getOffSet());
		inputParameters.put("limit", searchPO.getLimit());
		inputParameters.put("sortBy", searchPO.getSortBy());
		inputParameters.put("sortOrder", searchPO.getSortOrder());
		inputParameters.put("keyword", searchPO.getKeyword());
		inputParameters.put("templateId", searchPO.getEmailTemplateTypeId());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.SEARCH_BY_KEYWORD_EMAIL_TEMPLATE);
	    } else if (commandName.equals(AppCommand.AddEmailTemplate.name())) {
			EmailTemplatePO emailTemplatePO = (EmailTemplatePO) getObject(commandParameter, EmailTemplatePO.class);
			
			if (emailTemplatePO.getFromEmail() != null && !emailTemplatePO.getFromEmail().isEmpty()){
				if (!ValidationUtil.validateContent(emailTemplatePO.getFromEmail(), "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
					return javax.ws.rs.core.Response
				    .ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_INVALID_EMAIL, SystemErrorCode.ERROR_INVALID_EMAIL_TEXT, 200)).build();
				}
			}
			Map<String, Object> inputParameters = new HashMap<String, Object>();
			inputParameters.put("operation", OperationNameEnum.ADD);
			inputParameters.put("emailTemplatePO", emailTemplatePO);
			return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.ADD_EMAIL_TEMPLATE);
	    } else if (commandName.equals(AppCommand.UpdateEmailTemplateById.name())) {
			EmailTemplatePO emailTemplatePO = (EmailTemplatePO) getObject(commandParameter, EmailTemplatePO.class);
			
			if (emailTemplatePO.getFromEmail() != null && !emailTemplatePO.getFromEmail().isEmpty()){
				if (!ValidationUtil.validateContent(emailTemplatePO.getFromEmail(), "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
					return javax.ws.rs.core.Response
				    .ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_INVALID_EMAIL, SystemErrorCode.ERROR_INVALID_EMAIL_TEXT, 200)).build();
				}
			}
			
			Map<String, Object> inputParameters = new HashMap<String, Object>();
	
			inputParameters.put("operation", OperationNameEnum.UPDATE_BY_ID);
			inputParameters.put("id", emailTemplatePO.getEmailTemplateId());
			inputParameters.put("emailTemplatePO", emailTemplatePO);
	
			return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.EMAIL_TEMPLATE_UPDATE_BY_ID);
	    } else if (commandName.equals(AppCommand.EmailTemplateMultipleDelete.name())) {
		MultipleDeleteEmailTemplatePO multipleDeleteEmailTemplatePO = (MultipleDeleteEmailTemplatePO) getObject(commandParameter, MultipleDeleteEmailTemplatePO.class);


		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.MULTIPLEDELETE);
		inputParameters.put("multipleEmailTemplateIds",	multipleDeleteEmailTemplatePO.getMultipleEmailTemplateIds());
		inputParameters.put("confirmStatus", multipleDeleteEmailTemplatePO.getConfirmStatus());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.EMAIL_TEMPLATE_MULTIPLE_DELETE);
	    } else if (commandName.equals(AppCommand.CloneEmailTemplateById.name())) {
		EmailTemplatePO emailTemplatePO = (EmailTemplatePO) getObject(commandParameter, EmailTemplatePO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.CLONE);
		inputParameters.put("emailTemplatePO", emailTemplatePO);

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_TEMP, APIEvent.EMAIL_TEMPLATE_CLONE);
	    } else if (commandName.equals(AppCommand.ListEmailTemplateType.name())) {
		EmailTemplateTypeListPO emailTemplateTypeListPO = (EmailTemplateTypeListPO) getObject(commandParameter, EmailTemplateTypeListPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		inputParameters.put("status", emailTemplateTypeListPO.getStatus());
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_EMAIL_TEMPLATE_TYPE, APIEvent.LIST_EMAIL_TEMPLATE_TYPE);
	    } else if (commandName.equals(AppCommand.EmailTemplateTypeById.name())) {
		IdentityPO IdentityPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_BY_ID);
		inputParameters.put("id", IdentityPO.getId());
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_EMAIL_TEMPLATE_TYPE, APIEvent.GET_BY_ID_EMAIL_TEMPLATE_TYPE);
	    } else if (commandName.equals(AppCommand.EmailTemplateTypeByName.name())) {
		NamePO identityPO = (NamePO) getObject(commandParameter, NamePO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_BY_NAME);
		inputParameters.put("name", identityPO.getName());
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_EMAIL_TEMPLATE_TYPE, APIEvent.GET_BY_ID_EMAIL_TEMPLATE_TYPE);
	    } else if (commandName.equals(AppCommand.SearchEmailTemplateType.name())) {
		EmailTemplateTypeSearchPO searchPO = (EmailTemplateTypeSearchPO) getObject(commandParameter, EmailTemplateTypeSearchPO.class);
		boolean isValid = true;
		if(!(isValid
			&&		
			(ValidationUtil.validateContent(String.valueOf(searchPO.getOffSet()), "[0-9]((\\d)+)?")) 
			&&
			(ValidationUtil.validateContent(String.valueOf(searchPO.getLimit()), "[1-9]((\\d)+)?")) 
			&&
			(ValidationUtil.validateContent(String.valueOf(searchPO.getSortOrder()), "[0,1]"))
			))
		{
		    return javax.ws.rs.core.Response
			    .ok(ApplicationUtils
				    .errorMessageGenerate(
					    SystemErrorCode.EMAIL_TEMPLATE_TYPE_INVALID_PARAM,
					    SystemErrorCode.EMAIL_TEMPLATE_TYPE_INVALID_PARAM_TEXT,
					    200)).build();
		}
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.BASIC_SEARCH_BY_KEYWORD);
		//inputParameters.put("fieldName", fieldName);
		inputParameters.put("offSet", searchPO.getOffSet());
		inputParameters.put("limit", searchPO.getLimit());
		inputParameters.put("sortBy", searchPO.getSortBy());
		inputParameters.put("sortOrder", searchPO.getSortOrder());
		inputParameters.put("keyword", searchPO.getKeyword());

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_TEMPLATE_TYPE,	APIEvent.SEARCH_BY_KEYWORD_EMAIL_TEMPLATE_TYPE);
	    } else if (commandName.equals(AppCommand.SSOLogin.name())) {
		RegistrationPO registration = (RegistrationPO) getObject(commandParameter, RegistrationPO.class);
		Map <String, Object> inputParameters = RegistrationSSOHelper.populateInputParams(registration); 
		inputParameters.put("API_ACCESS_CODE", getAPIAccessCode());
		return processRequest(version,inputParameters,"RegistrationSSOProcessDefinition", APIEvent.SSO_REGISTRATION_EVENT);
	    } else if (commandName.equals(AppCommand.Signup.name())) {
		SignupPO signup = (SignupPO) getObject(commandParameter, SignupPO.class);
		Map<String, Object> inputParameters = SignupHelper.populateInputParams(signup);
		inputParameters.put("API_ACCESS_CODE", getAPIAccessCode());
		String [] property = {"firstName","lastName","email","password","zipCode","countryId"};

		boolean isValidInput = ApplicationUtils.validateMandatoryFields(signup, property, SignupPO.class);
		if(!isValidInput){
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		boolean isValidContent = ValidationUtil.validateContentFormat(signup.getPassword(), "(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]|.*\\s).*");

		if(!isValidContent) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.SIGN_UP_INVALID_PASSWORD, SystemErrorCode.SIGN_UP_INVALID_PASSWORD_TEXT, 200)).build();
		}
		return processRequest(version, inputParameters, "SignupProcessDefinition", APIEvent.SIGNUP_EVENT);
	    } else if (commandName.equals(AppCommand.Responsys.name())) {
		ResponsysPO responsysPO = (ResponsysPO) getObject(commandParameter, ResponsysPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("frequency", responsysPO.getFrequency());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_RES, APIEvent.RESPONSYS);
	    } else if (commandName.equals(AppCommand.LoginHistoryById.name())) {
		LoginHistoryByIdPO loginHistoryByIdPO = (LoginHistoryByIdPO) getObject(commandParameter, LoginHistoryByIdPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("ops", OperationNameEnum.USERID);
		inputParameters.put(ReqParamConstants.USERID, loginHistoryByIdPO.getUserId());
		inputParameters.put("offset", loginHistoryByIdPO.getOffSet());
		inputParameters.put("limit", loginHistoryByIdPO.getLimit());
		inputParameters.put("sortBy", loginHistoryByIdPO.getSortBy());
		inputParameters.put("sortOrder", loginHistoryByIdPO.getSortOrder());

		if(("").equals(loginHistoryByIdPO.getUserId()) ||  !StringUtils.isNumeric(loginHistoryByIdPO.getUserId())){
		    return ApplicationUtils.generateFailureResponse(WineaccessErrorCodes.SystemErrorCode.LOGIN_HISTORY_ERROR, WineaccessErrorCodes.SystemErrorCode.LOGIN_HISTORY_ERROR_TXT, HTTP_ERROR_CODE);
		}else{
		    return processRequest(version, inputParameters, PROCCESS_DEF_NAME_LOGIN_HISTORY, APIEvent.LOGIN_HISTORY);
		}
	    } else if (commandName.equals(AppCommand.LoginHistoryByEmail.name())) {
		LoginHistoryByEmailPO loginHistoryByEmailPO = (LoginHistoryByEmailPO) getObject(commandParameter, LoginHistoryByEmailPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("ops", OperationNameEnum.EMAIL);
		inputParameters.put(ReqParamConstants.EMAIL, loginHistoryByEmailPO.getEmail());
		inputParameters.put("offset", loginHistoryByEmailPO.getOffSet());
		inputParameters.put("limit", loginHistoryByEmailPO.getLimit());
		inputParameters.put("sortBy", loginHistoryByEmailPO.getSortBy());
		inputParameters.put("sortOrder", loginHistoryByEmailPO.getSortOrder());

		if(("").equals(loginHistoryByEmailPO.getEmail())){
		    return ApplicationUtils.generateFailureResponse(WineaccessErrorCodes.SystemErrorCode.LOGIN_HISTORY_ERROR, WineaccessErrorCodes.SystemErrorCode.LOGIN_HISTORY_ERROR_TXT, HTTP_ERROR_CODE);
		}else{
		    return processRequest(version, inputParameters, PROCCESS_DEF_NAME_LOGIN_HISTORY, APIEvent.LOGIN_HISTORY);
		}
	    } else if (commandName.equals(AppCommand.Logout.name())) {
		Map <String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("token", getWineaccessToken());
		return processRequest(version, inputParameters, "LogoutProcessDefinition", null);
	    } else if (commandName.equals(AppCommand.AddMasterData.name())) {
		MasterDataPO masterDataPO = (MasterDataPO) getObject(commandParameter, MasterDataPO.class);
		boolean isMandatoryFields = ApplicationUtils.validateMandatoryFields(masterDataPO, new String[] { "masterDataTypeName", "name" },	MasterDataPO.class);
		if (!isMandatoryFields) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_MASTER_DATA_MANDATORY_FIELDS_REQUIRED, SystemErrorCode.ADD_MASTER_DATA_MANDATORY_FIELDS_REQUIRED_TEXT, 200)).build();
		}
		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.ADD);
		inputParameters.put("masterDataPO", masterDataPO);
		return processRequest(version, inputParameters, "MasterDataProcessDefinition", APIEvent.ADD_MASTER_DATA);
	    } else if (commandName.equals(AppCommand.ListMasterData.name())) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		return processRequest(version, inputParameters, "MasterDataProcessDefinition", APIEvent.MASTER_DATA_LIST);
	    } else if (commandName.equals(AppCommand.MasterDataById.name())) {
		IdentityPO IdentityPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_BY_ID);
		inputParameters.put("id", IdentityPO.getId());
		return processRequest(version, inputParameters, "MasterDataProcessDefinition", APIEvent.MASTER_DATA_GET_BY_ID);
	    } else if (commandName.equals(AppCommand.MasterDataLastUpdated.name())) {
		IdentityPO identityPO = (IdentityPO)  getObject(commandParameter, IdentityPO.class);
		if (identityPO.getId() == null) {
		    return javax.ws.rs.core.Response
			    .ok(ApplicationUtils
				    .errorMessageGenerate(
					    SystemErrorCode.MASTER_DATA_GET_LAST_UPDATED_MANDATORY_FIELDS_REQUIRED,
					    SystemErrorCode.MASTER_DATA_GET_LAST_UPDATED_MANDATORY_FIELDS_REQUIRED_TEXT,
					    200)).build();
		}
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_LAST_UPDATED);
		inputParameters.put("masterDataTypeId", identityPO.getId());
		return processRequest(version, inputParameters,"MasterDataProcessDefinition", APIEvent.MASTER_DATA_GET_LAST_UPDATED);		
	    } else if (commandName.equals(AppCommand.UpdateMasterDataById.name())) {
		UpdateMasterDataByIdPO updateMasterDataByIdPO = (UpdateMasterDataByIdPO) getObject(commandParameter, UpdateMasterDataByIdPO.class);

		boolean isMandatoryFields = ApplicationUtils.validateMandatoryFields(
			updateMasterDataByIdPO, new String[] { "masterDataTypeName", "name" },
			MasterDataPO.class);
		if (!isMandatoryFields) {
		    return javax.ws.rs.core.Response
			    .ok(ApplicationUtils
				    .errorMessageGenerate(
					    SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED,
					    SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED_TEXT,
					    200)).build();
		}

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.UPDATE_BY_ID);
		inputParameters.put("id", updateMasterDataByIdPO.getId());
		inputParameters.put("masterDataPO", updateMasterDataByIdPO);

		return processRequest(version, inputParameters, "MasterDataProcessDefinition", APIEvent.MASTER_DATA_UPDATE_BY_ID);
	    } else if (commandName.equals(AppCommand.DeleteMasterDataById.name())) {
		IdentityPO identityPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.DELETE_BY_ID);
		inputParameters.put("id", identityPO.getId());

		return processRequest(version, inputParameters, "MasterDataProcessDefinition",
			APIEvent.MASTER_DATA_DELETE_BY_ID);
	    } else if (commandName.equals(AppCommand.MultipleDeleteMasterData.name())) {
		MultipleDeleteMasterDataPO multipleDeleteMasterDataPO = (MultipleDeleteMasterDataPO) getObject(commandParameter, MultipleDeleteMasterDataPO.class);

		/*boolean isValidFormat = ValidationUtil.validateContentFormat(multipleDeleteMasterDataPO.getMultipleMasterDataIds(), "number.separated.by.comma.regex");
				if(!isValidFormat)
				{
					return javax.ws.rs.core.Response
							.ok(ApplicationUtils
									.errorMessageGenerate(
											SystemErrorCode.MASTER_DATA_MULTIPLE_DELETE_INVALID_PARAM,
											SystemErrorCode.MASTER_DATA_MULTIPLE_DELETE_INVALID_PARAM_TEXT,
											200)).build();
				}*/

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.MULTIPLEDELETE);
		inputParameters.put("multipleMasterDataIds", multipleDeleteMasterDataPO.getMultipleMasterDataIds());
		inputParameters.put("confirmStatus", multipleDeleteMasterDataPO.getConfirmStatus());


		return processRequest(version, inputParameters, "MasterDataProcessDefinition", APIEvent.MASTER_DATA_MULTIPLE_DELETE);
	    } else if (commandName.equals(AppCommand.ListMasterDataTypes.name())) {
		ListMasterDataTypesPO listMasterDataTypesPO = (ListMasterDataTypesPO) getObject(commandParameter, ListMasterDataTypesPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);


		inputParameters.put("status", listMasterDataTypesPO.getStatus());

		return processRequest(version, inputParameters, "MasterDataTypeProcessDefinition", APIEvent.MASTER_DATA_TYPE_LIST);
	    } else if (commandName.equals(AppCommand.MasterDataTypeById.name())) {
		IdentityPO identityPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.GET_BY_ID);
		inputParameters.put("id", identityPO.getId());

		return processRequest(version, inputParameters, "MasterDataTypeProcessDefinition", APIEvent.MASTER_DATA_TYPE_GET_BY_ID);
	    } else if (commandName.equals(AppCommand.MasterDataTypeByName.name())) {
		NamePO identityPO = (NamePO) getObject(commandParameter, NamePO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.GET_BY_NAME);
		inputParameters.put("name", identityPO.getName());

		return processRequest(version, inputParameters, "MasterDataTypeProcessDefinition", APIEvent.MASTER_DATA_TYPE_GET_BY_ID);
	    }  else if (commandName.equals(AppCommand.SearchNewsLetter.name())) {
		NewsletterSearchPO searchPO = (NewsletterSearchPO) getObject(commandParameter, NewsletterSearchPO.class);
		boolean isValid = true;

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.BASIC_SEARCH_BY_KEYWORD);
		inputParameters.put("wineAccessToken", getWineaccessToken());
		inputParameters.put("offSet", searchPO.getOffSet());
		inputParameters.put("limit",searchPO.getLimit());
		inputParameters.put("sortBy", searchPO.getSortBy());
		inputParameters.put("sortOrder", searchPO.getSortOrder());
		inputParameters.put("keyword", searchPO.getKeyword());

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_NEWS_LETTER, APIEvent.SEARCH_NEWSLETTER);
	    } else if (commandName.equals(AppCommand.ListNewsLetterById.name())) {
		NewsletterGetByIdPO newsletterGetByIdPO = (NewsletterGetByIdPO) getObject(commandParameter, NewsletterGetByIdPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_BY_ID);
		inputParameters.put("wineAccessToken", getWineaccessToken());
		inputParameters.put("newsletterId", newsletterGetByIdPO.getNewsletterIds());

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_NEWS_LETTER, APIEvent.NEWSLETTER_LIST_BY_ID);
	    } else if (commandName.equals(AppCommand.AddNewsLetter.name())) {
		NewsletterPO newsletterPO = (NewsletterPO) getObjectWithDateFormat(commandParameter, NewsletterPO.class, PropertyholderUtils.getStringProperty("response.date.format"));

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.ADD);
		inputParameters.put("wineAccessToken", getWineaccessToken());
		inputParameters.put("newsletterPO", newsletterPO);
		return processRequest(version,inputParameters,PROCCESS_DEF_NAME_NEWS_LETTER, APIEvent.ADD_NEWSLETTER);

	    } else if (commandName.equals(AppCommand.UpdateNewsLetter.name())) {
		NewsletterPO newsletterPO = (NewsletterPO) getObjectWithDateFormat(commandParameter, NewsletterPO.class, PropertyholderUtils.getStringProperty("response.date.format"));
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.UPDATE);
		inputParameters.put("wineAccessToken", getWineaccessToken());
		inputParameters.put("newsletterPO", newsletterPO);
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_NEWS_LETTER, APIEvent.UPDATE_NEWSLETTER);
	    } else if (commandName.equals(AppCommand.DeleteNewsLetter.name())) {
		NewsletterDeletePO newsletterDeletePO = (NewsletterDeletePO) getObject(commandParameter, NewsletterDeletePO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.DELETE);
		inputParameters.put("wineAccessToken", getWineaccessToken());
		inputParameters.put("newsletterDeletePO", newsletterDeletePO);
		return processRequest(version,inputParameters,PROCCESS_DEF_NAME_NEWS_LETTER, APIEvent.UPDATE_NEWSLETTER);
	    } else if (commandName.equals(AppCommand.TemplatePlaceHoldersList.name())) {
		Set<String> keys = PlaceHolderUtil.listAllKeys(UserModel.class);
		com.wineaccess.response.Response response = new SuccessResponse(keys, 200);
		return javax.ws.rs.core.Response.ok(response).build();
	    } else if (commandName.equals(AppCommand.UserActivation.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_ACTIVATION, AppCommand.UserActivation, commandParameter);

	    } else if (commandName.equals(AppCommand.UserSessionSummary.name())) {
		ActivityLogSearchPO activityLogSearchPO = (ActivityLogSearchPO) getObject(commandParameter, ActivityLogSearchPO.class);

		Map <String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operationName", OperationNameEnum.USERSSESSIONSSUMMARY);
		inputParameters.put("offset", activityLogSearchPO.getOffSet());
		inputParameters.put("limit", activityLogSearchPO.getLimit());
		inputParameters.put("sortBy", activityLogSearchPO.getSortBy());
		inputParameters.put("sortOrder", activityLogSearchPO.getSortOrder());
		inputParameters.put("keyword", activityLogSearchPO.getKeyword());

		if(!ActivityLogHelper.isValidSessionSummaryRequest(String.valueOf(activityLogSearchPO.getSortBy()), Integer.parseInt(activityLogSearchPO.getSortOrder()), activityLogSearchPO.getKeyword())){
		    return ApplicationUtils.generateFailureResponse(WineaccessErrorCodes.SystemErrorCode.SESSION_SUMMARY_ERROR, WineaccessErrorCodes.SystemErrorCode.SESSION_SUMMARY_ERROR_TEXT, HTTP_ERROR_CODE);
		}
		return processRequest(version,inputParameters, PROCCESS_DEF_NAME_ACTIVITI_LOG, null);
	    } else if (commandName.equals(AppCommand.GetUserDetailForSession.name())) {
		SessionDetailsForUserPO sessionDetailsForUserPO = (SessionDetailsForUserPO) getObject(commandParameter, SessionDetailsForUserPO.class);
		Map <String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operationName", OperationNameEnum.SESSIONINFO_FOR_USER);
		inputParameters.put("userid", sessionDetailsForUserPO.getUserid());
		inputParameters.put("offset", sessionDetailsForUserPO.getOffSet());
		inputParameters.put("limit", sessionDetailsForUserPO.getLimit());
		return processRequest(version,inputParameters,PROCCESS_DEF_NAME_ACTIVITI_LOG, null);
	    } else if (commandName.equals(AppCommand.GetSessionDetailForUserBySessionId.name())) {
		SessionDetailsForUserBySessionPO sessionPO = (SessionDetailsForUserBySessionPO) getObject(commandParameter, SessionDetailsForUserBySessionPO.class);

		Map <String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operationName", OperationNameEnum.SESSIONINFO_FOR_USER_SESSION);
		inputParameters.put("userid", sessionPO.getUserid());
		inputParameters.put("sessionId", sessionPO.getSessionId());
		inputParameters.put("offset", sessionPO.getOffSet());
		inputParameters.put("limit", sessionPO.getLimit());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_ACTIVITI_LOG, null);
	    } else if (commandName.equals(AppCommand.AddComment.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_COMMENT, AppCommand.AddComment, commandParameter,OperationNameEnum.ADD.name());

	    } else if (commandName.equals(AppCommand.ViewCommentById.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_COMMENT, AppCommand.ViewCommentById, commandParameter,OperationNameEnum.GET_BY_ID.name());

	    } else if (commandName.equals(AppCommand.EditComment.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_COMMENT, AppCommand.EditComment, commandParameter,OperationNameEnum.UPDATE_BY_ID.name());

	    } else if (commandName.equals(AppCommand.ListCommentByUserId.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_COMMENT, AppCommand.ListCommentByUserId, commandParameter,OperationNameEnum.LIST.name());

	    } else if (commandName.equals(AppCommand.DeleteCommentById.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_COMMENT, AppCommand.DeleteCommentById, commandParameter,OperationNameEnum.DELETE.name());

	    } else if (commandName.equals(AppCommand.MultiDeleteCommentById.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_USER_COMMENT, AppCommand.MultiDeleteCommentById, commandParameter,OperationNameEnum.DELETE_ALL.name());

	    } else if (commandName.equals(AppCommand.ListCity.name())) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_CITY, APIEvent.CITY_LIST);
	    } else if (commandName.equals(AppCommand.ListCityById.name())) {
		ListCityByIdPO listCityByIdPO = (ListCityByIdPO) getObject(commandParameter, ListCityByIdPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LISTBYID);
		inputParameters.put("cityId", listCityByIdPO.getCityId());

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_CITY, APIEvent.CITY_LIST);
	    } else if (commandName.equals(AppCommand.ListStateById.name())) {
		StateByIdPO stateByCountryIdPO = (StateByIdPO) getObject(commandParameter, StateByIdPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LISTBYSTATEID);
		inputParameters.put("stateId", stateByCountryIdPO.getStateId());

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_CITY, APIEvent.CITY_LIST);

	    } else if (commandName.equals(AppCommand.ListCountry.name())) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_COUNTRY, APIEvent.COUNTRY_LIST);
	    } else if (commandName.equals(AppCommand.GetCountryById.name())) {
		CountryByIdPO countryByIdPO = (CountryByIdPO) getObject(commandParameter, CountryByIdPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LISTBYID);
		inputParameters.put("countryId", countryByIdPO.getCountryId());

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_COUNTRY, APIEvent.COUNTRY_LIST);
	    } else if (commandName.equals(AppCommand.ListState.name())) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_STATE, APIEvent.STATE_LIST);
	    } else if (commandName.equals(AppCommand.GetStateById.name())) {
		StateByIdPO stateByIdPO = (StateByIdPO) getObject(commandParameter, StateByIdPO.class);

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LISTBYID);
		inputParameters.put("stateId", stateByIdPO.getStateId());
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_STATE, APIEvent.STATE_LIST);
	    } else if (commandName.equals(AppCommand.GetStateByCountryId.name())) {
		StateByCountryIdPO stateByCountryIdPO = (StateByCountryIdPO) getObject(commandParameter, StateByCountryIdPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LISTBYCOUNTRYID);
		inputParameters.put("countryId", stateByCountryIdPO.getCountryId());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_STATE, APIEvent.STATE_LIST);
	    } else if (commandName.equals(AppCommand.ApiAccess.name())) {
		APIAccessCodePO apiAccessCodePO = (APIAccessCodePO) getObject(commandParameter, APIAccessCodePO.class);

		@SuppressWarnings("unchecked")
		APIAccessDAO<APIAccessCode> dao = (APIAccessDAO<APIAccessCode>) CoreBeanFactory.getBean("apiAccessDAO");

		if (dao.isEmailExist(apiAccessCodePO.getEmail())) {
		    WineaccessError error = new WineaccessError("EMAIL101", "Email Already Exist, Please Check you mail again");			
		    Response failedResponse = new FailureResponse();
		    failedResponse.addError(error);
		    return javax.ws.rs.core.Response.ok(failedResponse).build();
		}

		Random randomGenerator = new Random();

		Integer siteId = randomGenerator.nextInt(5000);
		Integer privateKey = randomGenerator.nextInt(6000);


		try {
		    APIAccessCode apiAccessCode = new APIAccessCode(CryptoUtil.encrypt(String.valueOf(siteId)), CryptoUtil.encrypt(String.valueOf(privateKey)), apiAccessCodePO.getEmail());
		    dao.create(apiAccessCode);

		    APIAccessCodeVO apiAccessCodeVO = new APIAccessCodeVO(siteId, privateKey, CryptoUtil.encrypt(siteId + "#AACCESSKEY#" + privateKey));


		    Map<String, String> attributes = new HashMap<String, String>();
		    attributes.put(EmailNotifier.EMAIL_TO, apiAccessCodePO.getEmail());
		    EmailTemplate emailTemplate = EmailTemplateRepository.getEmailTemplateByName(EmailTemplateType.REGISTRATION_MAIL.getEmailTemplateType());
		    if(emailTemplate != null){
			attributes.put(EmailNotifier.EMAIL_FROM, emailTemplate.getFromEmail());
			String body = emailTemplate.getBody()+apiAccessCodeVO.getApiAccessCode();

			try {
			    EmailNotifier.notify(emailTemplate.getSubject(), body, attributes, apiAccessCode);
			} catch (Exception e) {
			    logger.error("failed to send email -"+e.getMessage());
			    logger.error(e);
			}
		    }else{
			logger.info("email template not found,can not send the mail");
		    }

		    getDataRepositoryManager().putApiAccessCode(apiAccessCodeVO.getApiAccessCode(), apiAccessCode);
		    //System.out.println("api_access_code"+apiAccessCodeVO.getApiAccessCode());
		    Response response = new SuccessResponse(apiAccessCodeVO.getApiAccessCode(), 200);
		    return javax.ws.rs.core.Response.ok(response).build();
		} catch (Exception e) {
		    WineaccessError error = new WineaccessError("SYSERRRO", "Error, Please Contact Admin");			
		    Response failedResponse = new FailureResponse();
		    failedResponse.addError(error);
		    return javax.ws.rs.core.Response.ok(failedResponse).build();
		}
	    } else if (commandName.equals(AppCommand.ResendActivationMail.name())) {
		ResendActivationMailPO activationMailPO = (ResendActivationMailPO) getObject(commandParameter, ResendActivationMailPO.class);
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put(ReqParamConstants.EMAIL, activationMailPO.getEmail());
		inputParameters.put(ReqParamConstants.URL, activationMailPO.getUrl());

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_RESEND_EMAIL, APIEvent.RESEND_ACTIVATION_MAIL);
	    } else if (commandName.equals(AppCommand.ListUserEmailLog.name())) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_LOG, APIEvent.LIST_USER_EMAIL_LOG);
	    } else if (commandName.equals(AppCommand.ListUserEmailLogSearch.name())) {
		UserEmailLogsSearchPO userEmailLogsSearchPO = (UserEmailLogsSearchPO) getObject(commandParameter, UserEmailLogsSearchPO.class);
		boolean isValid = true;


		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation",
			OperationNameEnum.BASIC_SEARCH_BY_KEYWORD);
		//inputParameters.put("fieldName", userEmailLogsSearchPO.getFieldName());
		inputParameters.put("offSet", userEmailLogsSearchPO.getOffSet());
		inputParameters.put("limit", userEmailLogsSearchPO.getLimit());
		inputParameters.put("sortBy", userEmailLogsSearchPO.getSortBy());
		inputParameters.put("sortOrder", userEmailLogsSearchPO.getSortOrder());
		inputParameters.put("keyword", userEmailLogsSearchPO.getKeyword());

		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_EMAIL_LOG, APIEvent.SEARCH_BY_KEYWORD_USER_EMAIL_LOG);
	    }  else if (commandName.equals(AppCommand.CreateUser.name())) {
		UserManagementPO userManage = (UserManagementPO) getObjectWithDateFormat(commandParameter, UserManagementPO.class,PropertyholderUtils.getStringProperty("response.date.format.hour"));
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADD);
		inputParameters.put("UserManagementPO", userManage);
		String [] property = {"firstName","lastName","email","zipCode","countryId"};
		boolean isValidInput = UserManagementHelper.isValidUserPO(userManage,property);
		if(!isValidInput){
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		if(!UserManagementHelper.validatePhoneFormat(userManage)) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT, SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT, 200)).build();
		}
		if(userManage.getCreditCard()!=null){
		    for(CreditCardModel cardModel:userManage.getCreditCard()){
			boolean isValidExpirationDate = ValidationUtil.validateContent(cardModel.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
			if (!isValidExpirationDate) {
			    return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, 200);
			}
		    }
		}
		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.CREATE_USER);
	    } else if (commandName.equals(AppCommand.UpdateUser.name())) {
		UserManagementPO userManage = (UserManagementPO) getObjectWithDateFormat(commandParameter, UserManagementPO.class,PropertyholderUtils.getStringProperty("response.date.format.hour"));
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
		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.UPDATE_USER);
	    } else if (commandName.equals(AppCommand.CloneUser.name())) {
		CloneUserPO cloneUserPO = (CloneUserPO) getObject(commandParameter, CloneUserPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.CLONE);
		String [] property = {"toEmailId","fromEmailId"};
		boolean isValidInput = ApplicationUtils.validateMandatoryFields(cloneUserPO, property, CloneUserPO.class);
		if(!isValidInput)
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		inputParameters.put("CloneUserPO", cloneUserPO);
		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.CLONE_USER);
	    } else if (commandName.equals(AppCommand.EnableUser.name())) {
		ModifystatusPO modifystatusPO = (ModifystatusPO) getObject(commandParameter, ModifystatusPO.class);
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

		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.ENABLE_USER);
	    } else if (commandName.equals(AppCommand.DisableUser.name())) {
		ModifystatusPO modifystatusPO = (ModifystatusPO) getObject(commandParameter, ModifystatusPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DISABLEUSERS);
		inputParameters.put("currentUser", getCurrentUserDetails());
		//	List<Long> disableUserList = modifystatusPO.getDisableUsersList();
		List<Long> disableUserList = modifystatusPO.getDisableUsersList();
		if(disableUserList == null || disableUserList.isEmpty()) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		inputParameters.put("DisableUsersList", disableUserList);
		inputParameters.put("isforceDelete", modifystatusPO.getIsforceDelete());
		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.DISABLE_USER);
	    } else if (commandName.equals(AppCommand.DeleteUser.name())) {
		ModifystatusPO modifystatusPO = (ModifystatusPO) getObject(commandParameter, ModifystatusPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DELETEUSERS);
		inputParameters.put("currentUser", getCurrentUserDetails());
		List<Long> deleteUsersList = modifystatusPO.getDeleteUsersList();
		if(deleteUsersList == null || deleteUsersList.isEmpty()) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		inputParameters.put("DeleteUsersList", deleteUsersList);
		inputParameters.put("isforceDelete", modifystatusPO.getIsforceDelete());

		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.DELETE_USER);
	    } else if (commandName.equals(AppCommand.DeleteUserAddress.name())) {
		DeleteComponentPO deleteComponentPO = (DeleteComponentPO) getObject(commandParameter, DeleteComponentPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DELETEADDRESS);		
		inputParameters.put("DeleteComponentPO", deleteComponentPO);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.DELETE_ADDRESS);
	    } else if (commandName.equals(AppCommand.DeleteUserCreditCard.name())) {
		DeleteComponentPO deleteComponentPO = (DeleteComponentPO) getObject(commandParameter, DeleteComponentPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.DELETECREDITCARD);		
		inputParameters.put("DeleteComponentPO", deleteComponentPO);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.DELETE_ADDRESS);
	    } else if (commandName.equals(AppCommand.GetUserDetailById.name())) {
		DetailWithDeletedData detailWithDeletedData = (DetailWithDeletedData) getObject(commandParameter, DetailWithDeletedData.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.USERDETAIL);
		/*if(detailWithDeletedData.getId() == null)
					return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();*/
		inputParameters.put("id", detailWithDeletedData.getId());
		inputParameters.put("isShowDeletedData", detailWithDeletedData.getIsShowDeletedData());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_USER, APIEvent.USER_DETAIL);
	    }
	    else if (commandName.equals(AppCommand.GetCreditCardsOfUser.name())) {
		IdentityPO identityPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.CREDITCARDLIST);
		if(identityPO.getId() == null)
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		inputParameters.put("id", identityPO.getId());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_USER, APIEvent.USER_DETAIL);
	    }
	    else if (commandName.equals(AppCommand.GetAddressesOfUser.name())) {
		IdentityPO identityPO = (IdentityPO) getObject(commandParameter, IdentityPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADDRESSLIST);
		if(identityPO.getId() == null)
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		inputParameters.put("id", identityPO.getId());
		return processRequest(version, inputParameters, PROCCESS_DEF_NAME_USER, APIEvent.USER_DETAIL);
	    }
	    else if (commandName.equals(AppCommand.MergeUser.name())) {
		MergeUserPO mergeUserPO = (MergeUserPO) getObject(commandParameter, MergeUserPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.MERGEUSER);
		if(mergeUserPO == null || mergeUserPO.getToBeMergedUser()==null || mergeUserPO.getUserInWhichTobeMerged()==null) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		inputParameters.put("currentUser", getCurrentUserDetails());
		inputParameters.put("MergeUserPO", mergeUserPO);
		return processRequest(version, inputParameters, "UserManagementProcessDefinition", APIEvent.MERGE_USER);
	    } else if (commandName.equals(AppCommand.ResetUserPassword.name())) {
		com.wineaccess.usermanagement.ResetPasswordPO passwordPO = (com.wineaccess.usermanagement.ResetPasswordPO) getObject(commandParameter, com.wineaccess.usermanagement.ResetPasswordPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.RESET_PASSWORD);
		inputParameters.put("currentUser", getCurrentUserDetails());
		List<Long> userIds = passwordPO.getUserIds();
		if(userIds == null || userIds.isEmpty()) {
		    return javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()).build();
		}
		inputParameters.put("resetUserIds", userIds);
		inputParameters.put("isforceDelete", passwordPO.getIsforceDelete());
		inputParameters.put(ReqParamConstants.URL, passwordPO.getUrl());
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.RESET_PASSWORD_EMAIL);
	    } else if (commandName.equals(AppCommand.AddUserAddress.name())) {
		UserAddressModelAtomicPO addAddressPO = (UserAddressModelAtomicPO) getObjectWithDateFormat(commandParameter, UserAddressModelAtomicPO.class,PropertyholderUtils.getStringProperty("response.date.format.hour"));
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADD_ADDRESS);		
		inputParameters.put("AddAddressPO", addAddressPO);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.ADD_ADDRESS);
	    } else if (commandName.equals(AppCommand.UpdateUserAddress.name())) {
		UserEditAddressModelAtomicPO editAddressPO = (UserEditAddressModelAtomicPO) getObjectWithDateFormat(commandParameter, UserEditAddressModelAtomicPO.class,PropertyholderUtils.getStringProperty("response.date.format.hour"));
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.EDIT_ADDRESS);		
		inputParameters.put("EditAddressPO", editAddressPO);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.EDIT_ADDRESS);
	    } else if (commandName.equals(AppCommand.UserAddressDetail.name())) {
		AddressDetailAtomicPO addressDetailPO = (AddressDetailAtomicPO) getObject(commandParameter, AddressDetailAtomicPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADDRESS_DETAIL);		
		inputParameters.put("AddressDetailAtomicPO", addressDetailPO);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.ADDRESS_DETAIL);
	    } else if (commandName.equals(AppCommand.AddUserCreditCard.name())) {
		CreditCardModelAtomicPO addCreditCardPO = (CreditCardModelAtomicPO) getObjectWithDateFormat(commandParameter, CreditCardModelAtomicPO.class,PropertyholderUtils.getStringProperty("response.date.format.hour"));
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.ADD_CREDIT_CARD);		
		inputParameters.put("AddCreditCardPO", addCreditCardPO);
		boolean isValidContent = ValidationUtil.validateContent(addCreditCardPO.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
		if (!isValidContent) {
		    return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, HTTP_ERROR_CODE);
		} else {
		    return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.ADD_CREDIT_CARD);
		}

	    } else if (commandName.equals(AppCommand.UpdateUserCreditCard.name())) {
		CreditCardEditModelAtomicPO editCreditCardPO = (CreditCardEditModelAtomicPO) getObject(commandParameter, CreditCardEditModelAtomicPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.EDIT_CREDIT_CARD);		
		inputParameters.put("EditCreditCardPO", editCreditCardPO);
		boolean isValidContent = ValidationUtil.validateContent(editCreditCardPO.getExpirationDate(), "(0[1-9]|1[0-2])/[1-9](\\d{3})");
		if (!isValidContent) {
		    return ApplicationUtils.generateFailureResponse(SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE, SystemErrorCode.CREDIT_CARD_EXPIRATION_DATE_TEXT, HTTP_ERROR_CODE);
		} else {
		    return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.EDIT_CREDIT_CARD);
		}

	    } else if (commandName.equals(AppCommand.UserCreditCardDetail.name())) {
		CreditCardDetailAtomicPO creditCardDetailPO = (CreditCardDetailAtomicPO) getObject(commandParameter, CreditCardDetailAtomicPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();
		inputParameters.put("operation", OperationNameEnum.CREDIT_CARD_DETAIL);		
		inputParameters.put("CreditCardDetailPO", creditCardDetailPO);
		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_USER, APIEvent.CREDIT_CARD_DETAIL);
	    } else if (commandName.equals(AppCommand.AddImporter.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_IMPORTER, AppCommand.AddImporter, commandParameter,OperationNameEnum.ADD.name());

	    } else if (commandName.equals(AppCommand.UpdateImporter.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_IMPORTER, AppCommand.UpdateImporter, commandParameter,OperationNameEnum.UPDATE.name());

	    } else if (commandName.equals(AppCommand.DeleteImporter.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_IMPORTER, AppCommand.DeleteImporter, commandParameter,OperationNameEnum.DELETE.name());

	    }
	    else if (commandName.equals(AppCommand.AddWinery.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY, AppCommand.AddWinery, commandParameter,OperationNameEnum.ADD.name());

	    }
	    else if (commandName.equals(AppCommand.UpdateWinery.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY, AppCommand.UpdateWinery, commandParameter,OperationNameEnum.UPDATE_BY_ID.name());

	    }
	    else if (commandName.equals(AppCommand.DeleteWinery.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY, AppCommand.DeleteWinery, commandParameter,OperationNameEnum.DELETE.name());

	    }else if (commandName.equals(AppCommand.ViewWinery.name())) {

		logger.info("Viewing the winery details and key metrics");

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY, AppCommand.ViewWinery, commandParameter,OperationNameEnum.VIEW_DETAIL.name());

	    }else if (commandName.equals(AppCommand.ViewImporter.name())) {

		logger.info("Viewing the importer details and key metrics");

		return processRequestResponse(version, PROCCESS_DEF_NAME_IMPORTER, AppCommand.ViewImporter, commandParameter,OperationNameEnum.VIEW_DETAIL.name());

	    } else if (commandName.equals(AppCommand.AddContact.name())) {

		logger.info("Adding the contact details ");
		ContactDetailsPO contactDetailsPO = (ContactDetailsPO) getObject(commandParameter, ContactDetailsPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();

		inputParameters.put("operation", OperationNameEnum.ADD_CONTACT);		
		inputParameters.put("contactDetailsPO", contactDetailsPO);

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_CONTACT_DETAILS, APIEvent.ADD_CONTACT);

	    } else if (commandName.equals(AppCommand.UpdateContact.name())) {

		logger.info("Updating the contact details ");
		EditContactDetailsPO editContactDetailsPO = (EditContactDetailsPO) getObject(commandParameter, EditContactDetailsPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();

		inputParameters.put("operation", OperationNameEnum.UPDATE_CONTACT);		
		inputParameters.put("editContactDetailsPO", editContactDetailsPO);

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_CONTACT_DETAILS, APIEvent.UPDATE_CONTACT);

	    } else if (commandName.equals(AppCommand.ViewContactDetail.name())) {

		logger.info("Viewing the contact detail ");
		ViewContactDetailsPO viewContactDetailsPO = (ViewContactDetailsPO) getObject(commandParameter, ViewContactDetailsPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();

		inputParameters.put("operation", OperationNameEnum.VIEW_CONTACT_DETAIL);		
		inputParameters.put("viewContactDetailsPO", viewContactDetailsPO);

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_CONTACT_DETAILS, APIEvent.VIEW_CONTACT_DETAILS);

	    } else if (commandName.equals(AppCommand.ListContacts.name())) {

		logger.info("Getting list of all contacts for winery or importer");

		ContactsDetailListingPO conactListPO = (ContactsDetailListingPO) getObject(commandParameter, ContactsDetailListingPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();

		inputParameters.put("operation", OperationNameEnum.LIST_CONTACTS);		
		inputParameters.put("contactListPO", conactListPO);

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_CONTACT_DETAILS, APIEvent.LIST_CONTACTS);

	    }
	    else if (commandName.equals(AppCommand.DeleteContacts.name())) {

		logger.info("Deleting the contact details ");

		DeleteContactDetailsPO deleteContactDetailsPO = (DeleteContactDetailsPO) getObject(commandParameter, DeleteContactDetailsPO.class);
		Map <String, Object> inputParameters = new HashMap<String,Object>();

		inputParameters.put("operation", OperationNameEnum.DELETE);		
		inputParameters.put("deleteContactDetailsPO", deleteContactDetailsPO);

		return processRequest(version, inputParameters,	PROCCESS_DEF_NAME_CONTACT_DETAILS, APIEvent.DELETE_CONTACT);

	    }

	    else if (commandName.equals(AppCommand.AddWineryImporterAddress.name())) {
		return processRequestResponse(version, PROCCESS_DEF_NAME_WI_ADDRESS, AppCommand.AddWineryImporterAddress, commandParameter,OperationNameEnum.ADD_WINERY_IMPORTER_ADDRESS.name());

	    }

	    else if (commandName.equals(AppCommand.EditWineryImporterAddress.name())) {
		return processRequestResponse(version, PROCCESS_DEF_NAME_WI_ADDRESS, AppCommand.EditWineryImporterAddress, commandParameter,OperationNameEnum.EDIT_WINERY_IMPORTER_ADDRESS.name());

	    }

	    else if (commandName.equals(AppCommand.ViewWineryImporterAddress.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WI_ADDRESS, AppCommand.ViewWineryImporterAddress, commandParameter,OperationNameEnum.VIEW_WINERY_IMPORTER_ADDRESS.name());

	    }
	    else if (commandName.equals(AppCommand.DeleteWineryImporterAddress.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WI_ADDRESS, AppCommand.DeleteWineryImporterAddress, commandParameter,OperationNameEnum.DELETE_WINERY_IMPORTER_ADDRESS.name());

	    } 
	    else if (commandName.equals(AppCommand.ListWineryImporterAddress.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WI_ADDRESS, AppCommand.ListWineryImporterAddress, commandParameter,OperationNameEnum.LIST_WINERY_IMPORTER_ADDRESS.name());

	    }
	    else if (commandName.equals(AppCommand.SearchImporter.name())) {
		ImporterSearchPO importerSearchPO = (ImporterSearchPO) getObject(commandParameter, ImporterAdvanceSearchPO.class);

		if (importerSearchPO.getSearchType().equals("A")) {
		    return processRequestResponse(version, "ImporterSearchAdvance", AppCommand.SearchImporter, commandParameter);
		} else {
		    return processRequestResponse(version, "ImporterSearch", AppCommand.SearchImporter, commandParameter);
		}
	    } else if (commandName.equals(AppCommand.SearchWinery.name())) {
			WinerySearchPO importerSearchPO = (WinerySearchPO) getObjectWithDateFormat(commandParameter, WineryAdvanceSearchPO.class, PropertyholderUtils.getStringProperty("response.date.format"));
			if (importerSearchPO.getSearchType().equals("A")) {
			    return processRequestResponse(version, "WinerySearchAdvance", AppCommand.SearchWinery, commandParameter);
			} else {
			    return processRequestResponse(version, "WinerySearch", AppCommand.SearchWinery, commandParameter);
			}
	    } else if (commandName.equals(AppCommand.ForgetPasswordEmail.name())) {

		logger.info("Sending the forgot password email");

		return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.ForgetPasswordEmail, commandParameter,OperationNameEnum.FORGOT_PASSWORD_EMAIL.name());
	    } else if (commandName.equals(AppCommand.UpdateForgotPassword.name())) {

		logger.info("Update the forgot password");

		return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.UpdateForgotPassword, commandParameter,OperationNameEnum.UPDATE_FORGOT_PASSWORD.name());
	    } 
	    else if (commandName.equals(AppCommand.AddWine.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.AddWine, commandParameter,OperationNameEnum.ADD.name());								

	    }
	    else if (commandName.equals(AppCommand.UpdateWine.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.UpdateWine, commandParameter,OperationNameEnum.UPDATE_BY_ID.name());								

	    }
	    else if (commandName.equals(AppCommand.ViewWine.name())) {

		logger.info("View the wine details");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.ViewWine, commandParameter,OperationNameEnum.VIEW_DETAIL.name());

	    }
	    else if (commandName.equals(AppCommand.ListWine.name())) {

		WineBasicSearchPO wineBasicSearchPO = (WineBasicSearchPO) getObject(commandParameter, WineAdvanceSearchPO.class);

		if (wineBasicSearchPO.getSearchType().equals("A")) {
		    return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.ListWine, commandParameter,OperationNameEnum.ADVANCE_SEARCH.name());
		} else {
		    return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.ListWine, commandParameter,OperationNameEnum.BASIC_SEARCH.name());
		}
	    }

	    else if (commandName.equals(AppCommand.AddWineLogistic.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.AddWineLogistic, commandParameter,OperationNameEnum.ADD_WINE_LOGISTIC.name());								

	    }


	    else if (commandName.equals(AppCommand.ViewWineLogistic.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.ViewWineLogistic, commandParameter,OperationNameEnum.VIEW_WINE_LOGISTIC.name());								

	    }

	    else if (commandName.equals(AppCommand.ChangeWineStatus.name())) {

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.ChangeWineStatus, commandParameter,OperationNameEnum.BULK_OPERATION.name());
	    }
	    else if (commandName.equals(AppCommand.DeleteWine.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE, AppCommand.DeleteWine, commandParameter,OperationNameEnum.DELETE.name());								

	    }
	    else if (commandName.equals(AppCommand.CreateUpdateWineryOWS.name())) {

		logger.info("Create the winery ows data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY_OWS, AppCommand.CreateUpdateWineryOWS, commandParameter,OperationNameEnum.ADD_UPDATE.name());								

	    }

	    else if (commandName.equals(AppCommand.AddWinePermit.name())) {

		logger.info("Create the wine permit data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE_PERMIT, AppCommand.AddWinePermit, commandParameter,OperationNameEnum.ADD_WINE_PERMIT.name());								

	    }

	    else if (commandName.equals(AppCommand.ViewWinePermit.name())) {

		logger.info("view the wine permit data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE_PERMIT, AppCommand.ViewWinePermit, commandParameter,OperationNameEnum.VIEW_WINE_PERMIT.name());								

	    }

	    else if (commandName.equals(AppCommand.AddWineryPermit.name())) {

		logger.info("Create the wine permit data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY_PERMIT, AppCommand.AddWineryPermit, commandParameter,OperationNameEnum.ADD_WINERY_PERMIT.name());								

	    }

	    else if (commandName.equals(AppCommand.ViewWineryPermit.name())) {

		logger.info("view the wine permit data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY_PERMIT, AppCommand.ViewWineryPermit, commandParameter,OperationNameEnum.VIEW_WINERY_PERMIT.name());								

	    }

	    else if (commandName.equals(AppCommand.ViewWineryOWS.name())) {

		logger.info("View the winery ows data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY_OWS, AppCommand.ViewWineryOWS, commandParameter,OperationNameEnum.VIEW_DETAIL.name());								

	    }			
	    else if (commandName.equals(AppCommand.UpdateWineryLicenseDetail.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY_LICENSE_DETAIL, AppCommand.UpdateWineryLicenseDetail, commandParameter,OperationNameEnum.UPDATE_BY_ID.name());								

	    }
	    else if (commandName.equals(AppCommand.ViewWineryLicenseDetail.name())) {			    

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY_LICENSE_DETAIL, AppCommand.ViewWineryLicenseDetail, commandParameter,OperationNameEnum.GET_BY_ID.name());

	    }

	    else if (commandName.equals(AppCommand.UpdateWineOWS.name())) {

		logger.info("Update the wine ows data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE_OWS, AppCommand.UpdateWineOWS, commandParameter,OperationNameEnum.UPDATE.name());								

	    }
	    else if (commandName.equals(AppCommand.ViewWineOWS.name())) {

		logger.info("View the wine ows data");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE_OWS, AppCommand.ViewWineOWS, commandParameter,OperationNameEnum.VIEW_DETAIL.name());								

	    }

	    else if (commandName.equals(AppCommand.UpdateWineLicenseDetail.name())) {				

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE_LICENSE_DETAIL, AppCommand.UpdateWineLicenseDetail, commandParameter,OperationNameEnum.UPDATE_BY_ID.name());								

	    }
	    else if (commandName.equals(AppCommand.ViewWineLicenseDetail.name())) {			    

		return processRequestResponse(version, PROCCESS_DEF_NAME_WINE_LICENSE_DETAIL, AppCommand.ViewWineLicenseDetail, commandParameter,OperationNameEnum.GET_BY_ID.name());

	    } else if (commandName.equals(AppCommand.SearchRequisition.name())) {
		return processRequestResponse(version, "POSearchDefinition", AppCommand.SearchRequisition, commandParameter);
	    }

	    else if (commandName.equals(AppCommand.AddRequisitionForPOWT.name())) {

			logger.info("Add the requisition for PO/WT");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.AddRequisitionForPOWT, commandParameter,OperationNameEnum.ADD_REQ_POWT.name());
	    }
	    else if (commandName.equals(AppCommand.AddRequisitionForIT.name())) {

			logger.info("Add the requisition for IT");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.AddRequisitionForIT, commandParameter,OperationNameEnum.ADD_REQ_IT.name());
	    }
	    
	    else if (commandName.equals(AppCommand.UpdateRequisitionForPOWT.name())) {

			logger.info("Update the requisition for PO/WT");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.UpdateRequisitionForPOWT, commandParameter,OperationNameEnum.UPDATE_REQ_POWT.name());
	    }
	    else if (commandName.equals(AppCommand.UpdateRequisitionForIT.name())) {

	    	logger.info("Update the requisition for IT");
	    	return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.UpdateRequisitionForIT, commandParameter,OperationNameEnum.UPDATE_REQ_IT.name());
	    }
	    
		else if (commandName.equals(AppCommand.UpdateRequisition.name())) {

		logger.info("Update the requisition");
		return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.UpdateRequisition, commandParameter,OperationNameEnum.UPDATE.name());
	    }		else if (commandName.equals(AppCommand.ViewRequisition.name())) {

			logger.info("View the requisition");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.ViewRequisition, commandParameter,OperationNameEnum.VIEW_REQUISITION.name());
		}
	    else if (commandName.equals(AppCommand.EnableDisableWinery.name())) {

		logger.info("enable disable the winery");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WINERY, AppCommand.EnableDisableWinery, commandParameter,OperationNameEnum.ENABLEDISABLE.name());
	    }
	    else if (commandName.equals(AppCommand.AddWarehouse.name())) {

		logger.info("Create new warehouse");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WAREHOUSE, AppCommand.AddWarehouse, commandParameter,OperationNameEnum.ADD.name());								

	    }
	    else if (commandName.equals(AppCommand.UpdateWarehouse.name())) {

		logger.info("Update warehouse");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WAREHOUSE, AppCommand.UpdateWarehouse, commandParameter,OperationNameEnum.UPDATE.name());								

	    }
	    else if (commandName.equals(AppCommand.ViewWarehouse.name())) {

		logger.info("Getting warehouse details");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WAREHOUSE, AppCommand.ViewWarehouse, commandParameter,OperationNameEnum.GET_BY_ID.name());								

	    }
	    else if (commandName.equals(AppCommand.DeleteWarehouse.name())) {

		logger.info("Deleting warehouse details");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WAREHOUSE, AppCommand.DeleteWarehouse, commandParameter,OperationNameEnum.DELETE.name());								

	    }
	    else if (commandName.equals(AppCommand.AddDistributionCentre.name())) {

		logger.info("Create the distribution centre warehouses location");
		return processRequestResponse(version, PROCCESS_DEF_NAME_DISTRIBUTION_CENTRE, AppCommand.AddDistributionCentre, commandParameter,OperationNameEnum.ADD.name());								

	    }
	    else if (commandName.equals(AppCommand.AddWineToRequisition.name())) {

			logger.info("Add wine to requisition");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.AddWineToRequisition, commandParameter,OperationNameEnum.ADD_WINE_TO_REQUISTION.name());
	    }
	    else if (commandName.equals(AppCommand.EditWineToRequisition.name())) {

			logger.info("edit wine to requisition");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.EditWineToRequisition, commandParameter,OperationNameEnum.EDIT_WINE_TO_REQUISTION.name());
		}
	    else if (commandName.equals(AppCommand.RemoveWineFromRequisition.name())) {

			logger.info("Remove wine from requisition");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.RemoveWineFromRequisition, commandParameter,OperationNameEnum.REMOVE_WINE_FROM_REQUISTION.name());
	    }
	    else if (commandName.equals(AppCommand.ListWineInRequisition.name())) {

		logger.info("Listing wines in requision");
		return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.ListWineInRequisition, commandParameter,OperationNameEnum.LIST_WINE_IN_REQUISTION.name());
	    }

	    else if (commandName.equals(AppCommand.UpdateDistributionCentre.name())) {

		logger.info("Update the distribution centre warehouses location");
		return processRequestResponse(version, PROCCESS_DEF_NAME_DISTRIBUTION_CENTRE, AppCommand.UpdateDistributionCentre, commandParameter,OperationNameEnum.UPDATE.name());
	    }
	    else if (commandName.equals(AppCommand.ViewDistributionCentre.name())) {

		logger.info("View the distribution centre warehouses location");
		return processRequestResponse(version, PROCCESS_DEF_NAME_DISTRIBUTION_CENTRE, AppCommand.ViewDistributionCentre, commandParameter,OperationNameEnum.VIEW_DETAIL.name());
	    }
	    else if (commandName.equals(AppCommand.DeleteDistributionCentre.name())) {

		logger.info("delete the distribution centre warehouses location");
		return processRequestResponse(version, PROCCESS_DEF_NAME_DISTRIBUTION_CENTRE, AppCommand.DeleteDistributionCentre, commandParameter,OperationNameEnum.DELETE.name());
	    }
	    else if (commandName.equals(AppCommand.ListDistributionCentre.name())) {

		logger.info("lists the distribution centre warehouses location");
		return processRequestResponse(version, PROCCESS_DEF_NAME_DISTRIBUTION_CENTRE, AppCommand.ListDistributionCentre, commandParameter,OperationNameEnum.LIST.name());
	    }
	    else if (commandName.equals(AppCommand.ListWarehouse.name())) {

		logger.info("lists the warehouses");
		return processRequestResponse(version, PROCCESS_DEF_NAME_WAREHOUSE, AppCommand.ListWarehouse, commandParameter,OperationNameEnum.LIST.name());
	    }
	    else if (commandName.equals(AppCommand.AddSampler.name())) {

			logger.info("add the sampler");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.AddSampler, commandParameter,OperationNameEnum.ADD.name());
			
		}
	    else if (commandName.equals(AppCommand.ViewSampler.name())) {

			logger.info("view the sampler");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.ViewSampler, commandParameter,OperationNameEnum.VIEW_DETAIL.name());
			
		} else if (commandName.equals(AppCommand.SearchSampler.name())) {
			logger.info("search the sampler");
			SamplerSearchPO samplerSearchPO = (SamplerSearchPO) getObjectWithDateFormat(commandParameter, SamplerAdvSearchPO.class, PropertyholderUtils.getStringProperty("response.date.format"));
			if (samplerSearchPO.getSearchType().equals("A")) {
				return processRequestResponse(version, PROCCESS_DEF_NAME_ADV_SAMPLER_SEARCH, AppCommand.SearchSampler, commandParameter, AppCommand.SearchSampler.name());
			} else {
				return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER_SEARCH, AppCommand.SearchSampler, commandParameter, AppCommand.SearchSampler.name());
			}
			
		}
	    else if (commandName.equals(AppCommand.ViewSamplerLogisticsDetail.name())) {

			logger.info("view the sampler logistics detail");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.ViewSamplerLogisticsDetail, commandParameter,OperationNameEnum.VIEW_SAMPLER_LOGISTICS_DETAIL.name());
			
		}
	    else if (commandName.equals(AppCommand.ViewSamplerComplienceDetail.name())) {

			logger.info("view the sampler complience detail");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.ViewSamplerComplienceDetail, commandParameter,OperationNameEnum.VIEW_SAMPLER_COMPLIENCE_DETAIL.name());
			
		}
		else if (commandName.equals(AppCommand.UpdateSampler.name())) {

			logger.info("update the sampler");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.UpdateSampler, commandParameter,OperationNameEnum.UPDATE.name());
			
		}		
		else if (commandName.equals(AppCommand.EditSamplerWine.name())) {
			logger.info("Edit wines of a sampler.");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.EditSamplerWine, commandParameter,OperationNameEnum.EDIT_SAMPLER_WINE.name());
		}	
		else if (commandName.equals(AppCommand.RemoveWineFromSampler.name())) {

			logger.info("Remove wine from sampler.");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.RemoveWineFromSampler, commandParameter,OperationNameEnum.REMOVE_WINE_FROM_SAMPLER.name());
			
		}	    		
		else if (commandName.equals(AppCommand.AddSamplerProduct.name())) {

			logger.info("Add sampler product.");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.AddSamplerProduct, commandParameter,OperationNameEnum.ADD_SAMPLER_PRODUCT.name());
			
		}
		else if (commandName.equals(AppCommand.EnableDisableImporter.name())) {

			logger.info("Enable Disable the importer.");
			return processRequestResponse(version, PROCCESS_DEF_NAME_IMPORTER, AppCommand.EnableDisableImporter, commandParameter,OperationNameEnum.ENABLEDISABLE.name());
			
		}
		else if (commandName.equals(AppCommand.ListSamplerProduct.name())) {
			logger.info("List products of a sampler.");
			return processRequestResponse(version, PROCCESS_DEF_NAME_SAMPLER, AppCommand.ListSamplerProduct, commandParameter,OperationNameEnum.LIST_SAMPLER_PRODUCT.name());
		}	
		else if (commandName.equals(AppCommand.CreateUserPassword.name())) {
			logger.info("Create the user password.");
			return processRequestResponse(version, PROCCESS_DEF_NAME, AppCommand.CreateUserPassword, commandParameter,OperationNameEnum.REGISTER_USER_PASSWORD.name());
		}	
		else if (commandName.equals(AppCommand.DeleteRequisition.name())) {
			logger.info("Delete the requisition.");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.DeleteRequisition, commandParameter,OperationNameEnum.DELETE.name());
		}	
		else if (commandName.equals(AppCommand.SendEmailToWinery.name())) {
			logger.info("Send Email to winery");
			return processRequestResponse(version, PROCCESS_DEF_NAME_REQUISITION, AppCommand.SendEmailToWinery, commandParameter,OperationNameEnum.SEND_EMAIL_TO_WINERY.name());
		}	
	    
	    return null;
	} catch(ValidationFailedError ex) {
	    return javax.ws.rs.core.Response.ok(ex.getFailedResponse()).build();
	} catch(Exception ex) {
	    Response failedResponse = ExceptionHandlerUtil.analyzeJsonObjectException(ex).getFailedResponse();
	    return javax.ws.rs.core.Response.ok(failedResponse).build();
	}
    }
}
