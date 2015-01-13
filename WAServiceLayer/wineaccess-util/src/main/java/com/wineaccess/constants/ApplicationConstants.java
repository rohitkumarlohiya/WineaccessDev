package com.wineaccess.constants;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface ApplicationConstants {

	String FINAL_RESPONSE = "FINAL-RESPONSE";

	public enum ROLES {
		ROLE_SUPER_ADMIN, ROLE_ADMIN, ROLE_RETAIL_USER, ROLE_CUSTOMER_SUPPORT
	}
	public enum PROCESSDEF{

		ACTIVITY_LOG("UserActivityLogProcessDefinition"),
		USER_PROFILE("UserProfileProcessDefinition"),
		COUNTRY_LIST("CountryListProcessDefinition"),
		STATE_LIST("StateListProcessDefinition"),
		CITY_LIST("CityListProcessDefinition"),
		USER_NORMAL_SEARCH("UserNormalSearchDefinition"),
		USER_ADVANCED_SEARCH("UserAdvanceSearchDefinition"),
		LOGIN_HISTORY("LoginHistoryProcessDefinition"),
		LOGOUT_HISTORY("LogoutProcessDefinition"),
		MASTER_DATA_NORMAL_SEARCH("MasterDataNormalSearchDefinition"),
		MASTER_DATA_TYPE_NORMAL_SEARCH("MasterDataTypeNormalSearchDefinition"),
		MASTER_DATA_TYPE("MasterDataTypeProcessDefinition"),
		USER_ROLE("UserRoleProcessDefinition"),
		NEWS_LETTER("NewsletterProcessDefinition"),
		USER_MANAGEMENT("UserManagementProcessDefinition"),
		REGISTRATION_SSO("RegistrationSSOProcessDefinition"),
		USER_COMMENTS("UserCommentsProcessDefinition"),
		EMAIL_TEMPLATE_TYPE("EmailTemplateTypeProcessDefinition"),
		EMAIL_TEMPLATE("EmailTemplateProcessDefinition"),
		USER_EMAIL_LOG("UserEmailLogProcessDefinition"),
		USER_ACTIVATION("UserActivationProcessDefinition"),
		RESPONSYS("ResponsysProcessDefinition"),
		RESEND_ACTIVATION_MAIL("ResendActivationMailProcessDefinition"),
		SUBSCRIBE_USER("SubscribeUserProcessDefinition"),
		IMPORTER("ImporterProcessDefinition"),
		CONTACT_DETAILS("ContactDetailsProcessDefinition"),
		WI_ADDRESS("WineryImporterProcessDefinition"),
		WINERY("WineryProcessDefinition"),
		WINE("WineProcessDefinition"),
		WINE_LOGISTIC("WineLogisticProcessDefinition"),
		WINERY_OWS("WineryOWSProcessDefinition"),
		WINE_PERMIT("WinePermitProcessDefinition"),
		WINERY_PERMIT("WineryPermitProcessDefinition"),
		WINERY_LICENSE_DETAIL("WineryLicenseDetailProcessDefinition"),
		WINE_OWS("WineOWSProcessDefinition"),
		WINE_LICENSE_DETAIL("WineLicenseDetailProcessDefinition"),
		REQUISITION("RequisitionProcessDefinition"),
		WAREHOUSE("WarehouseProcessDefinition"),
		DISTRIBUTION_CENTRE("DistributionCentreProcessDefinition"),
		SAMPLER("SamplerProcessDefinition"),
		SAMPLER_SEARCH("SamplerSearchProcessDefinition"),
		SAMPLER_ADV_SEARCH("SamplerAdvSearchProcessDefinition");

		String processDefinitionName;

		PROCESSDEF(){

		}

		PROCESSDEF(String processDefinationName){
			this.processDefinitionName = processDefinationName;

		}

		public String getProcessDefinationName(){
			return processDefinitionName;
		}


	}

	public enum RESPONSECODES{
		SUCCESSCODE(200);


		Integer responseCodes;

		RESPONSECODES(){

		}

		RESPONSECODES(Integer responseCodes){
			this.responseCodes = responseCodes;

		}

		public Integer getResponseCodes(){
			return responseCodes;
		}


	}
}
