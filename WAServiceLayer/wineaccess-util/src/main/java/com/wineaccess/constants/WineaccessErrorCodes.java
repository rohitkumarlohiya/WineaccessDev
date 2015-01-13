package com.wineaccess.constants;

import com.wineaccess.property.utils.ErrorPropertyHolderUtil;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface  WineaccessErrorCodes {

	public interface SystemErrorCode {
		
		final String ERROR_INVALID_EMAIL = "ERROR_INVALID_EMAIL";
		final String ERROR_INVALID_EMAIL_TEXT = ErrorPropertyHolderUtil.getProperty(ERROR_INVALID_EMAIL, "invalid email id");
		
		
		final String INVALIDE_USER_ID_PASSED = "INVALIDE_USER_ID_PASSED";
		final String INVALIDE_USER_ID_PASSED_TEXT = ErrorPropertyHolderUtil.getProperty(INVALIDE_USER_ID_PASSED, "Invalid user id passed, Loggrd in user is different from passed user id");
		
		final String INVALID_SEARCH_CRITERIA_ID = "INVALID_SEARCH_CRITERIA_ID";
		final String INVALID_SEARCH_CRITERIA_ID_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_SEARCH_CRITERIA_ID, "Invalid search criteria id");
		
		final String INVALID_USERID_VALUE = "INVALID_USER_ID";
		final String INVALID_USERID_VALUE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_USERID_VALUE, "USer Id value is invalid");
		
		final String INVALID_ID_VALUE = "INVALID_ID_VALUE";
		final String INVALID_ID_VALUE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_ID_VALUE, "Id value is invalid"); 
	
	
		final String INVALID_API_KEY_VERSION = "SYS101";
		final String INVALID_API_KEY_VERSION_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_API_KEY_VERSION, "Invalid API Key and Version Or Invalide Access Code"); 
	
	
		final String INVALID_PROCESS_DEF_NAME_VERSION = "SYS102";
		final String INVALID_PROCESS_DEF_NAME_VERSION_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_PROCESS_DEF_NAME_VERSION, "Invalid Process Deinition Name Or Version for the API");
	
		final String INVALID_TOKEN_ID = "SYS103";
		final String INVALID_TOKEN_ID_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_TOKEN_ID, "Invalid Token");
	
		final String REQUEST_TIMED_OUT = "SYS104";
		final String REQUEST_TIMED_OUT_TEXT = ErrorPropertyHolderUtil.getProperty(REQUEST_TIMED_OUT, "Request timed out, can not process your request...");
	
		final String INVALID_PARAMS = "SYS105";
		final String INVALID_PARAMS_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_PARAMS, "Invalid Params");
	
		final String BAD_URI = "SYS106";
		final String BAD_URI_TEXT = ErrorPropertyHolderUtil.getProperty(BAD_URI, "Bad URI");
	
		final String METHOD_NOT_ALLOWED = "SYS107";
		final String METHOD_NOT_ALLOWED_TEXT = ErrorPropertyHolderUtil.getProperty(METHOD_NOT_ALLOWED, "Method not allowed");
	
		final String SYSTEM_ERROR = "SYS108";
		final String SYSTEM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(SYSTEM_ERROR, "System API Error");
		
		final String NO_ENTITY_FOUND = "SYS109";
		final String NO_ENTITY_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(NO_ENTITY_FOUND, "No Entity found");
	
		final String INVALID_API_ACCESSS = "SYS200";
		final String INVALID_API_ACCESSS_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_API_ACCESSS, "Invalid API Access");
	
		final String INVALID_USER_PASSWORD = "USER101";
	
		final String INVALID_USER_PASSWORD_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_USER_PASSWORD, "Invalid User Name/password");
		
		final String USER_DELETED = "USER532";
	
		final String USER_DELETED_TEXT = ErrorPropertyHolderUtil.getProperty(USER_DELETED, "Deleted User, can not login");
		
		final String EMAIL_ID_EXISTS = "USER102";
		final String EMAIL_ID_EXISTS_TXT = ErrorPropertyHolderUtil.getProperty(EMAIL_ID_EXISTS, "Email Id already exists");
	
		final String USER_ALREADY_REGISTERED = "USER103";
		final String USER_ALREADY_REGISTERED_TXT = ErrorPropertyHolderUtil.getProperty(USER_ALREADY_REGISTERED, "User Already Registered");
	
		final String NO_RECORD_TO_CLONE = "USER104";
		final String NO_RECORD_TO_CLONE_TXT = ErrorPropertyHolderUtil.getProperty(NO_RECORD_TO_CLONE, "No record to clone");
	
		final String NO_RECORD_EXISTS = "USER105";
		final String NO_RECORD_EXISTS_TXT = ErrorPropertyHolderUtil.getProperty(NO_RECORD_EXISTS, "No record exists for Ids");
	
		final String NO_USER_TO_UPDATE = "USER106";
		final String NO_USER_TO_UPDATE_TXT = ErrorPropertyHolderUtil.getProperty(NO_USER_TO_UPDATE, "No user to update");
	
		final String INVALID_PHONE_NUMBER_FORMAT = "USER107";
		final String INVALID_PHONE_NUMBER_FORMAT_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_PHONE_NUMBER_FORMAT, "Invalid Phone number format.");
	
		final String DISABLED_USER = "USER108";
		final String DISABLED_USER_TEXT = ErrorPropertyHolderUtil.getProperty(DISABLED_USER, "Your Account has been deactivated.Please contact to administrator");
	
		final String DELETED_USER = "USER109";
		final String DELETED_USER_TXT = ErrorPropertyHolderUtil.getProperty(DELETED_USER, "Either one or 2 ids are deleted");
	
		final String DISABLE_USER = "USER110";
		final String DISABLE_USER_TXT = ErrorPropertyHolderUtil.getProperty(DISABLE_USER, "Either one or 2 ids are disabled");
	
		final String INVALID_ADDRESS_ID = "USER111";
		final String INVALID_ADDRESS_ID_TXT = ErrorPropertyHolderUtil.getProperty(INVALID_ADDRESS_ID, "Address Ids entered is invalid");
	
		final String INVALID_CC_ID = "USER112";
		final String INVALID_CC_ID_TXT = ErrorPropertyHolderUtil.getProperty(INVALID_ADDRESS_ID, "Credit card Ids entered is invalid");
	
	
		final String NO_RECORD_EXISTS_ID = "USER113";
		final String NO_RECORD_EXISTS_ID_TXT = ErrorPropertyHolderUtil.getProperty(NO_RECORD_EXISTS_ID, "No record exists for Id");
	
	
		final String USER_NOT_EXIST = "USER116";
		final String USER_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(USER_NOT_EXIST, "No record exist for user id.");
	
		final String ADDRESS_NOT_EXIST = "USER117";
		final String ADDRESS_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_NOT_EXIST, "No record exist for address id.");
	
		final String CC_NOT_EXIST = "USER128";
		final String CC_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_NOT_EXIST, "No record exist for credit card.");
	
		final String USER_ID_BLANK = "USER118";
		final String USER_ID_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(USER_ID_BLANK, "Mandatory field user id can not be blank.");
	
		final String ADDRESS_ID_BLANK = "USER119";
		final String ADDRESS_ID_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_ID_BLANK, "Mandatory field address id can not be blank.");
	
		final String ZIP_CODE_BLANK = "USER120";
		final String ZIP_CODE_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(ZIP_CODE_BLANK, "Mandatory field zipCode can not be blank.");
	
		final String ADDRESS_TYPE_BLANK = "USER121";
		final String ADDRESS_TYPE_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_ID_BLANK, "Mandatory field address type can not be blank.");
	
		final String COUNTRY_ID_BLANK = "USER122";
		final String COUNTRY_ID_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(COUNTRY_ID_BLANK, "Mandatory field country can not be blank.");
	
		final String ADDRESS_NOT_CREATED = "USER124";
		final String ADDRESS_NOT_CREATED_TXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_NOT_CREATED, "Address couldn't be created");
	
		final String CC_TYPE_ID_BLANK = "USER125";
		final String CC_TYPE_ID_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(CC_TYPE_ID_BLANK, "Mandatory field credit card type id can not be blank.");
	
		final String EXP_DATE_ID_BLANK = "USER126";
		final String EXP_DATE_ID_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(EXP_DATE_ID_BLANK, "Mandatory field expiry date can not be blank.");
	
		final String CC_NUMBER_BLANK = "USER127";
		final String CC_NUMBER_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(CC_NUMBER_BLANK, "Mandatory field credit card number can not be blank.");
	
		final String ADDRESS_CC_BLANK = "USER128";
		final String ADDRESS_CC_BLANK_TXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_CC_BLANK, "Mandatory field address  can not be blank.");
	
		final String CC_NOT_CREATED = "USER129";
		final String CC_NOT_CREATED_TXT = ErrorPropertyHolderUtil.getProperty(CC_NOT_CREATED, "Credit card couldn't be created");
	
		final String CC_INVALID = "USER130";
		final String CC_INVALID_TXT = ErrorPropertyHolderUtil.getProperty(CC_NOT_CREATED, "Credit card invalid");
		
		final String RESEND_ACTIVATION_ERROR = "USER150";
		final String RESEND_ACTIVATION_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(RESEND_ACTIVATION_ERROR, "can't send activation code");
		
		final String ADDRESS_NOT_BILLING = "USER151";
		final String ADDRESS_NOT_BILLING_TEXT = ErrorPropertyHolderUtil.getProperty(ADDRESS_NOT_BILLING, "address must be of billing type");
	
		final String TO_BE_MERGED_NOT_EXIST = "MERGEUSER101";
		final String TO_BE_MERGED_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(TO_BE_MERGED_NOT_EXIST, "toBeMergedUser can't be blank");
	
		final String USER_IN_WHICH_TO_BE_MERGED_NOT_EXIST = "MERGEUSER102";
		final String USER_IN_WHICH_TO_BE_MERGED_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(USER_IN_WHICH_TO_BE_MERGED_NOT_EXIST, "userInWhichTobeMerged can't be blank");
	
	
	
	
		final String COUNTRY_NOT_EXISTS = "COUNTRY101";
		final String COUNTRY_NOT_EXISTS_TXT = ErrorPropertyHolderUtil.getProperty(COUNTRY_NOT_EXISTS, "No Country Exists");
	
		final String LOGIN_HISTORY_NOT_EXISTS = "USERHISTORY101";
		final String LOGIN_HISTORY_NOT_EXISTS_TXT = ErrorPropertyHolderUtil.getProperty(LOGIN_HISTORY_NOT_EXISTS, "No Login History Found");
	
	
		final String LOGIN_HISTORY_ERROR = "USERHISTORY102";
		final String LOGIN_HISTORY_ERROR_TXT = ErrorPropertyHolderUtil.getProperty(LOGIN_HISTORY_ERROR, "Invalid Request Param");
	
	
	
		final String USER_ROLE_NAME_ALREADY_EXISTS = "USERROLE101";
		final String USER_ROLE_NAME_ALREADY_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ROLE_NAME_ALREADY_EXISTS, "User Role Name Already Exists.");
	
		final String USER_ROLE_NAME_REQUIRED = "USERROLE102";
		final String USER_ROLE_NAME_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ROLE_NAME_REQUIRED, "User Role Name Required.");	
	
		final String UPDATED_USER_ROLE_NAME_REQUIRED = "USERROLE103";
		final String UPDATED_USER_ROLE_NAME_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATED_USER_ROLE_NAME_REQUIRED, "Updated User Role Name Required.");		
	
		final String USER_ROLE_NAME_NOT_EXISTS = "USERROLE104";
		final String USER_ROLE_NAME_NOT_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ROLE_NAME_NOT_EXISTS, "User Role Name Not Exists.");
	
		final String USER_ROLE_ID_NOT_EXISTS = "USERROLE105";
		final String USER_ROLE_ID_NOT_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ROLE_ID_NOT_EXISTS, "User Role Id Not Exists.");
	
		final String USER_ROLE_ID_IS_IN_USE = "USERROLE106";
		final String USER_ROLE_ID_IS_IN_USE_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ROLE_ID_IS_IN_USE, "User Role Id Is In Use.");
	
		final String USER_ROLE_ID_REQUIRED = "USERROLE107";
		final String USER_ROLE_ID_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ROLE_ID_REQUIRED, "User Role Id Required.");
	
		final String STATUS_REQUIRED = "USERROLE108";
		final String STATUS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(STATUS_REQUIRED, "Status Required.");
	
		final String INVALID_ROLE_NAME = "USERROLE109";
		final String INVALID_ROLE_NAME_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_ROLE_NAME, "Invalid Role Name.");
	
	
		final String INVALID_ROLE_ID = "USERROLE110";
		final String INVALID_ROLE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_ROLE_NAME, "Invalid Role Id.");
	
	
		final String INVALID_UPDATED_ROLE_NAME = "USERROLE111";
		final String INVALID_UPDATED_ROLE_NAME_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_UPDATED_ROLE_NAME, "Invalid Updated Role Name.");
	
		final String PASSWORD_NOT_MATCHED = "USERPROFILE01";
		final String PASSWORD_NOT_MATCHED_TEXT = ErrorPropertyHolderUtil.getProperty(PASSWORD_NOT_MATCHED, "Password Not Matched.");
	
		final String PROFILE_NOT_UPDATED = "USERPROFILE02";
		final String PROFILE_NOT_UPDATED_TEXT = ErrorPropertyHolderUtil.getProperty(PROFILE_NOT_UPDATED, "Updating User Profile Failed.");
	
		final String NO_USER_DETAIL_FOUND = "USERPROFILE03";
		final String NO_USER_DETAIL_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(NO_USER_DETAIL_FOUND, "No User Detail Found for this User.");
	
		final String INVALID_PARAM_ERROR = "USERPROFILE123";
		final String INVALID_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_PARAM_ERROR, "Invalid Param.");
	
		final String NO_RECORD_FOUND = "MASTERDATATYPE101";
	
		final String NO_RECORD_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(NO_RECORD_FOUND, "No Record Found.");
	
	
	
		final String MASTER_DATA_TYPE_ID_REQUIRED = "MASTERDATA101";
		final String MASTER_DATA_TYPE_ID_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_TYPE_ID_REQUIRED, "masterDataTypeId Required.");
	
	
		final String MASTER_DATA_NAME_REQUIRED = "MASTERDATA102";
		final String MASTER_DATA_NAME_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_NAME_REQUIRED, "name Required.");
	
	
		final String MASTER_DATA_LABEL_REQUIRED = "MASTERDATA103";
		final String MASTER_DATA_LABEL_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_LABEL_REQUIRED, "label Required.");
	
	
		final String INVALID_MASTER_DATA_TYPE_ID = "MASTERDATA104";
		final String INVALID_MASTER_DATA_TYPE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_MASTER_DATA_TYPE_ID, "Invalid masterDataTypeId.");
	
		final String INVALID_MASTER_DATA_NAME = "MASTERDATA105";
		final String INVALID_MASTER_DATA_NAME_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_MASTER_DATA_NAME, "Invalid name.");
	
		final String INVALID_MASTER_DATA_LABEL = "MASTERDATA106";
		final String INVALID_MASTER_DATA_LABEL_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_MASTER_DATA_LABEL, "Invalid label.");
	
		final String INVALID_MASTER_DATA_ID = "MASTERDATA107";
		final String INVALID_MASTER_DATA_ID_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_MASTER_DATA_ID, "Invalid Master Data Id.");
	
	
		final String ADD_MASTER_DATA_MANDATORY_FIELDS_REQUIRED = "MANDATORYFIELDS101";
		final String ADD_MASTER_DATA_MANDATORY_FIELDS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_MASTER_DATA_MANDATORY_FIELDS_REQUIRED, "Mandatory Fields Required.");
	
		final String MASTER_DATA_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED = "MANDATORYFIELDS102";
		final String MASTER_DATA_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED, "Mandatory Fields Required.");
	
	
	
		final String ADD_MASTER_DATA_INVALID_PARAM = "INVALIDPARAM101";
		final String ADD_MASTER_DATA_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_MASTER_DATA_INVALID_PARAM, "Invalid Param.");
	
		final String MASTER_DATA_GET_BY_ID_INVALID_PARAM = "INVALIDPARAM102";
		final String MASTER_DATA_GET_BY_ID_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_GET_BY_ID_INVALID_PARAM, "Invalid Param.");
	
		final String MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM = "INVALIDPARAM103";
		final String MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM, "Invalid Param.");
	
		final String MASTER_DATA_DELETE_BY_ID_INVALID_PARAM = "INVALIDPARAM104";
		final String MASTER_DATA_DELETE_BY_ID_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_DELETE_BY_ID_INVALID_PARAM, "Invalid Param.");
	
	
		final String MASTER_DATA_DELETE_BY_ID_IN_USE = "INUSE101";
		final String MASTER_DATA_DELETE_BY_ID_IN_USE_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_DELETE_BY_ID_IN_USE, "Id Is In Use.");
	
		final String SIGN_UP_INVALID_PASSWORD = "SIGNUP101";
		final String SIGN_UP_INVALID_PASSWORD_TEXT = ErrorPropertyHolderUtil.getProperty(SIGN_UP_INVALID_PASSWORD, "Invalid password.");
	
		final String USER_PROFILE_INVALID_PASSWORD = "USERPROFILE101";
		final String USER_PROFILE_INVALID_PASSWORD_TEXT = ErrorPropertyHolderUtil.getProperty(USER_PROFILE_INVALID_PASSWORD, "Invalid password.");
	
		// Error codes added for activity log
		final String SESSION_SUMMARY_ERROR = "SESSIONSUMMARY101";
		final String SESSION_SUMMARY_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(SESSION_SUMMARY_ERROR, "Invalid Request Param");
	
		final String USER_SEARCH_INTERNAL_SERVICE_ERROR = "USERSEARCH101";
		final String USER_SEARCH_INTERNAL_SERVICE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_SEARCH_INTERNAL_SERVICE_ERROR, "Internal Service Error Occured while processing request");
		final String SESSION_SUMMARY_PROCCESSING_ERROR = "SESSIONSUMMARY102";
		final String SESSION_SUMMARY_PROCCESSING_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(SESSION_SUMMARY_PROCCESSING_ERROR, "Internal Error Occured while processing the request");
	
		final String LATEST_USER_SESSION_INFO_ERROR = "SESSIONINFOFORUSER101";
		final String LATEST_USER_SESSION_INFO_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(LATEST_USER_SESSION_INFO_ERROR, "Internal Error Occured while processing the request");
	
		final String USER_SESSION_INFO_ERROR = "SESSIONINFOFORUSER102";
		final String USER_SESSION_INFO_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_SESSION_INFO_ERROR, "Internal Error Occured while processing the request");
	
		final String USER_SESSION_INFO_ERROR_USER_ID = "SESSIONINFOFORUSER103";
		final String USER_SESSION_INFO_ERROR_USER_ID_TXT = ErrorPropertyHolderUtil.getProperty(USER_SESSION_INFO_ERROR_USER_ID, "Mandatory field user id is required.");
	
		final String USER_SESSION_INFO_ERROR_SESSION_ID = "SESSIONINFOFORUSER104";
		final String USER_SESSION_INFO_ERROR_SESSION_ID_TXT = ErrorPropertyHolderUtil.getProperty(USER_SESSION_INFO_ERROR_SESSION_ID, "Mandatory field session id is required.");
	
		final String MASTER_DATA_GET_LAST_UPDATED_INVALID_PARAM = "INVALIDPARAM105";
		final String MASTER_DATA_GET_LAST_UPDATED_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_GET_LAST_UPDATED_INVALID_PARAM, "Invalid Param.");
	
		final String MASTER_DATA_MULTIPLE_DELETE_INVALID_PARAM = "INVALIDPARAM106";
		final String MASTER_DATA_MULTIPLE_DELETE_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_MULTIPLE_DELETE_INVALID_PARAM, "Invalid Param.");
	
	
		final String MASTER_DATA_GET_LAST_UPDATED_MANDATORY_FIELDS_REQUIRED = "MANDATORYFIELDS103";
		final String MASTER_DATA_GET_LAST_UPDATED_MANDATORY_FIELDS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_GET_LAST_UPDATED_MANDATORY_FIELDS_REQUIRED, "Mandatory Fields Required.");
	
		final String MASTER_DATA_MULTIPLE_DELETE_BY_ID_IN_USE = "INUSE102";
		final String MASTER_DATA_MULTIPLE_DELETE_BY_ID_IN_USE_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_MULTIPLE_DELETE_BY_ID_IN_USE, " ids are being used.please remove the dependency first");
	
	
		final String USER_PROFILE_ERROR = "USERPROFILE102";
		final String USER_PROFILE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_PROFILE_ERROR, "No User Details Found");
	
		final String USER_NOT_FOUND_ERROR = "SESSIONINFOFORUSER103";
		final String USER_NOT_FOUND_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_NOT_FOUND_ERROR, "User Details for the user id not found.");
	
		final String NEWSLETTER_ERROR = "NEWSLETTER115";
		final String NEWSLETTER_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NEWSLETTER_ERROR, "Invalid Param.");
	
		final String NO_NEWSLETTER_FOUND_ERROR = "NEWSLETTER116";
		final String NO_NEWSLETTER_FOUND_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NO_NEWSLETTER_FOUND_ERROR, "No Newsletter Found.");
	
		final String NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR = "NEWSLETTER117";
		final String NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR, "Mandatory Field Missing.");
	
		final String USER_SEARCH_INVALID_PARAM_ERROR = "USERSEARCH102";
		final String USER_SEARCH_INVALID_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_SEARCH_INVALID_PARAM_ERROR, "Invalid Param.");
		final String ADD_MASTER_DATA_ALREADY_EXITS = "MASTERDATA108";
		final String ADD_MASTER_DATA_ALREADY_EXITS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_MASTER_DATA_ALREADY_EXITS, "Master Data Alreday Exists");
	
		final String STATE_INVALID_PARAM_ERROR = "STATE101";
		final String STATE_INVALID_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(STATE_INVALID_PARAM_ERROR, "Invalid Param.");
	
		final String STATE_MANDATORY_FIELD_ERROR = "STATE102";
		final String STATE_MANDATORY_FIELD_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(STATE_MANDATORY_FIELD_ERROR, "Mandatory field Country Id can not be blank.");
	
		final String COUNTRY_INVALID_PARAM_ERROR = "COUNTRY101";
		final String COUNTRY_INVALID_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(COUNTRY_INVALID_PARAM_ERROR, "Invalid Param.");
	
		final String CITY_INVALID_PARAM_ERROR = "CITY101";
		final String CITY_INVALID_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CITY_INVALID_PARAM_ERROR, "Invalid Param.");
	
		final String CITY_MANDATORY_FIELD_ERROR = "CITY102";
		final String CITY_MANDATORY_FIELD_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CITY_MANDATORY_FIELD_ERROR, "Mandatory field State Id can not be blank.");
		final String INVALID_MASTERDATATYPE_ID = "MASTERDATATYPE102";
		final String INVALID_MASTERDATATYPE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_MASTERDATATYPE_ID, "Master Data Type ID is invalid");
		
		
		final String EMPTY_MASTERDATATYPE_ID = "MASTERDATATYPE104";
		final String EMPTY_MASTERDATATYPE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(EMPTY_MASTERDATATYPE_ID, "Master Data Type Name can not be blank");
	
		final String NEWSLETTER_INVALID_SORT_PARAM_ERROR = "NEWSLETTER118";
		final String NEWSLETTER_INVALID_SORT_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NEWSLETTER_INVALID_SORT_PARAM_ERROR, "Invalid sort field passed.");
	
		final String NEWSLETTER_DELETE_PARAM_ERROR = "NEWSLETTER119";
		final String NEWSLETTER_DELETE_PARAM_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NEWSLETTER_DELETE_PARAM_ERROR, "Invalid Id Param Passed.");
	
		final String USER_ID_NOT_NULL = "USER114";
		final String USER_ID_NOT_NULL_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ID_NOT_NULL, "User Id cannot be null.");
	
		final String USER_ACTIVATION_CODE = "USER115";
		final String USER_ACTIVATION_CODE_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ACTIVATION_CODE, "Your URL has been expired.");
	
		final String USER_COMMENT_ID_NOT_NULL = "USERCOMMENT104";
		final String USER_COMMENT_ID_NOT_NULL_TEXT = ErrorPropertyHolderUtil.getProperty(USER_COMMENT_ID_NOT_NULL, "Comment Id can not be null.");
	
		final String USER_COMMON_ADD = "COMMONUSER101";
		final String USER_COMMON_ADD_TEXT = ErrorPropertyHolderUtil.getProperty(USER_COMMON_ADD, "Added Successfully");
	
		final String USER_COMMON_UPDATE = "COMMONUSER102";
		final String USER_COMMON_UPDATE_TEXT = ErrorPropertyHolderUtil.getProperty(USER_COMMON_UPDATE, "Updated Successfully");
	
		final String USER_COMMON_DELETE = "COMMONUSER103";
		final String USER_COMMON_DELETE_TEXT = ErrorPropertyHolderUtil.getProperty(USER_COMMON_DELETE, "Deleted Successfully");
	
		final String EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND = "EMAILTEMPLATETYPE101";
		final String EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND, "No Record Found.");
	
		final String EMAIL_TEMPLATE_GET_BY_ID_INVALID_PARAM = "EMAILTEMPLATE101";
		final String EMAIL_TEMPLATE_GET_BY_ID_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_GET_BY_ID_INVALID_PARAM, "Invalid Param.");
	
		final String ADD_EMAIL_TEMPLATE_INVALID_PARAM = "EMAILTEMPLATE102";
		final String ADD_EMAIL_TEMPLATE_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_EMAIL_TEMPLATE_INVALID_PARAM, "Invalid Param.");
	
		final String EMAIL_TEMPLATE_UPDATE_BY_ID_INVALID_PARAM = "EMAILTEMPLATE103";
		final String EMAIL_TEMPLATE_UPDATE_BY_ID_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_UPDATE_BY_ID_INVALID_PARAM, "Invalid Param.");
	
		final String EMAIL_TEMPLATE_DELETE_BY_ID_IN_USE = "EMAILTEMPLATE104";
		final String EMAIL_TEMPLATE_DELETE_BY_ID_IN_USE_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_DELETE_BY_ID_IN_USE, "Id Is In Use.");
	
		final String EMAIL_TEMPLATE_DELETE_BY_ID_INVALID_PARAM = "EMAILTEMPLATE105";
		final String EMAIL_TEMPLATE_DELETE_BY_ID_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_DELETE_BY_ID_INVALID_PARAM, "Invalid Param.");
	
		final String ADD_EMAIL_TEMPLATE_MANDATORY_FIELDS_REQUIRED = "EMAILTEMPLATE106";
		final String ADD_EMAIL_TEMPLATE_MANDATORY_FIELDS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_EMAIL_TEMPLATE_MANDATORY_FIELDS_REQUIRED, "Mandatory Fields Required.");
	
		final String EMAIL_TEMPLATE_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED = "EMAILTEMPLATE107";
		final String EMAIL_TEMPLATE_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_UPDATE_BY_ID_MANDATORY_FIELDS_REQUIRED, "Mandatory Fields Required.");
	
		final String EMAIL_TEMPLATE_MULTIPLE_DELETE_INVALID_PARAM = "EMAILTEMPLATE108";
		final String EMAIL_TEMPLATE_MULTIPLE_DELETE_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_MULTIPLE_DELETE_INVALID_PARAM, "Invalid Param.");
	
		final String EMAIL_TEMPLATE_CLONE_INVALID_PARAM = "EMAILTEMPLATE109";
		final String EMAIL_TEMPLATE_CLONE_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_CLONE_INVALID_PARAM, "Invalid Param.");
	
		final String CLONE_EMAIL_TEMPLATE_MANDATORY_FIELDS_REQUIRED = "EMAILTEMPLATE110";
		final String CLONE_EMAIL_TEMPLATE_MANDATORY_FIELDS_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(CLONE_EMAIL_TEMPLATE_MANDATORY_FIELDS_REQUIRED, "Mandatory Fields Required.");
	
		final String EMAIL_TEMPLATE_SEARCH_INVALID_PARAM = "EMAILTEMPLATE111";
		final String EMAIL_TEMPLATE_SEARCH_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_SEARCH_INVALID_PARAM, "Invalid Param.");
	
		final String EMAIL_TEMPLATE_TYPE_INVALID_PARAM = "EMAILTEMPLATETYPE102";
		final String EMAIL_TEMPLATE_TYPE_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_TYPE_INVALID_PARAM, "Invalid Param.");
	
		final String USER_ACTIVATION = "USERACTIVATION101";
		final String USER_ACTIVATION_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ACTIVATION, "Activated Successfully");
	
		final String USER_EMAIL_LOG_SEARCH_INVALID_PARAM = "USEREMAILLOG101";
		final String USER_EMAIL_LOG_SEARCH_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(USER_EMAIL_LOG_SEARCH_INVALID_PARAM, "Invalid Param.");
	
		final String USER_ACTIVATION_EMAIL = "USERACTIVATION102";
		final String USER_ACTIVATION_EMAIL_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ACTIVATION_EMAIL, "Email cannot be blank, empty or null.");
	
		final String USER_ACTIVATION_CODE_BLANK = "USERACTIVATION103";
		final String USER_ACTIVATION_CODE_BLANK_TEXT = ErrorPropertyHolderUtil.getProperty(USER_ACTIVATION_CODE_BLANK, "Activation code cannot be blank, empty or null.");
		final String EMAIL_TEMPLATE_TYPE_INVALID_ID = "EMAILTEMPLATETYPE104";;
		final String EMAIL_TEMPLATE_TYPE_INVALID_ID_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_TYPE_INVALID_ID, "Email Template ID is not valid"); ;
		final String NEWSLETTER_SEARCH_INVALID_PARAM = "NEWSLETTER120";
		final String NEWSLETTER_SEARCH_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(NEWSLETTER_SEARCH_INVALID_PARAM, "Invalid Param.");
	
		final String ADD_NEWSLETTER_INVALID_PARAM = "NEWSLETTER121";
		final String ADD_NEWSLETTER_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_NEWSLETTER_INVALID_PARAM, "Invalid Param.");
	
		final String UPDATE_NEWSLETTER_INVALID_PARAM = "NEWSLETTER122";
		final String UPDATE_NEWSLETTER_INVALID_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_NEWSLETTER_INVALID_PARAM, "Invalid Param.");
		final String USER_SEARCH_TYPE_MISSING_ERROR = "USERSEARCH103";
		final String USER_SEARCH_TYPE_MISSING_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_SEARCH_TYPE_MISSING_ERROR, "Search Type is missing or having invalid value");;
	
		final String CREDIT_CARD_EXPIRATION_DATE = "CREDITCARD101";
		final String CREDIT_CARD_EXPIRATION_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(CREDIT_CARD_EXPIRATION_DATE, "Credit Card Expiration Date format is not valid.Date Format should be MM/yyyy");
		final String ADD_EMAIL_TEMPLATE_INVALID_TYPE_ID = "EMAILTEMPLATETYPE105";
		final String ADD_EMAIL_TEMPLATE_INVALID_TYPE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_EMAIL_TEMPLATE_INVALID_TYPE_ID, "Email Template type not found");
		final String ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM = "EMAILTEMPLATE112";
		final String ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM, "Email Template already exists");
		final String UPDATE_EMAIL_TEMPLATE_INVALID_TYPE_ID = "EMAILTEMPLATETYPE106";
		final String UPDATE_EMAIL_TEMPLATE_INVALID_TYPE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_EMAIL_TEMPLATE_INVALID_TYPE_ID, "Email Template type not found");;
		final String UPDATE_EMAIL_TEMPLATE_INVALID_ID = "EMAILTEMPLATE113";
		final String UPDATE_EMAIL_TEMPLATE_INVALID_ID_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_EMAIL_TEMPLATE_INVALID_ID, "Email Template not found");
		final String ERROR_REGISTERED_USER = "USER132";
		final String ERROR_REGISTERED_USER_TXT = ErrorPropertyHolderUtil.getProperty(ERROR_REGISTERED_USER, "User is not  Activated");;
		final String WINERY_ADD_SUCCESS = "WINERY_ADD_SUCCESS";
		final String WINERY_ADD_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_SUCCESS, "Winery add successfully.");
		final String WINERY_ADD_INVALID_REGION = "WINERY_ERROR_110";
		final String WINERY_ADD_INVALID_REGION_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_INVALID_REGION, "Invalid Region");
		final String WINERY_ADD_INVALID_SOURCING_ID = "WINERY_ERROR_111";
		final String WINERY_ADD_INVALID_SOURCING_ID_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_INVALID_SOURCING_ID, "Invalid Sourcing");
		final String WINERY_ADD_INVALID_IMPORTER_ID = "WINERY_ERROR_112";
		final String WINERY_ADD_INVALID_IMPORTER_ID_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_INVALID_IMPORTER_ID, "Invalid Importer");
		final String WINERY_ADD_WINERY_NAME_DUPLICATE = "WINERY_ERROR_113";
		final String WINERY_ADD_WINERY_NAME_DUPLICATE_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_WINERY_NAME_DUPLICATE, "Duplicate Winery Name");
		final String WINERY_ADD_WINERY_CODE_DUPLICATE = "WINERY_ERROR_114";
		final String WINERY_ADD_WINERY_CODE_DUPLICATE_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_WINERY_CODE_DUPLICATE, "Duplicate Winery Code");
		final String WINERY_ADD_UNKNOWN_ERROR = "WINERY_ERROR_115";
		final String WINERY_ADD_UNKNOWN_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_UNKNOWN_ERROR, "Unknown Error");
		final String WINERY_UPDATE_SUCCESS = "WINERY_UPDATE_SUCCESS";
		final String WINERY_UPDATE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_SUCCESS, "Winery Update successfully.");
		final String WINERY_UPDATE_INVALID_REGION = "WINERY_ERROR_116";
		final String WINERY_UPDATE_INVALID_REGION_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_INVALID_REGION, "Invalid Region");
		final String WINERY_UPDATE_INVALID_SOURCING_ID = "WINERY_ERROR_117";
		final String WINERY_UPDATE_INVALID_SOURCING_ID_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_INVALID_SOURCING_ID, "Invalid Sourcing");
		final String WINERY_UPDATE_INVALID_IMPORTER_ID = "WINERY_ERROR_118";
		final String WINERY_UPDATE_INVALID_IMPORTER_ID_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_INVALID_IMPORTER_ID, "Invalid Importer");
		final String WINERY_UPDATE_WINERY_NAME_DUPLICATE = "WINERY_ERROR_119";
		final String WINERY_UPDATE_WINERY_NAME_DUPLICATE_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_WINERY_NAME_DUPLICATE, "Duplicate Winery Name");
		final String WINERY_UPDATE_WINERY_CODE_DUPLICATE = "WINERY_ERROR_120";
		final String WINERY_UPDATE_WINERY_CODE_DUPLICATE_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_WINERY_CODE_DUPLICATE, "Duplicate Winery Code");
		final String WINERY_UPDATE_UNKNOWN_ERROR = "WINERY_ERROR_121";
		final String WINERY_UPDATE_UNKNOWN_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_UNKNOWN_ERROR, "Unknown Error");
		final String WINERY_IMPORTER_ADDRESS_LINE1 = "WI_ADDRESS_101";
		final String WINERY_IMPORTER_ADDRESS_LINE1_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_LINE1, "Mandatory field address line1 can not be blank");
		final String WINERY_IMPORTER_ADDRESS_CITY = "WI_ADDRESS_102";
		final String WINERY_IMPORTER_ADDRESS_CITY_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_CITY, "Mandatory field city can not be blank");
		final String WINERY_IMPORTER_ADDRESS_STATE = "WI_ADDRESS_103";
		final String WINERY_IMPORTER_ADDRESS_STATE_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_STATE, "Mandatory field state can not be blank");
		final String WINERY_IMPORTER_ADDRESS_ZIPCODE = "WI_ADDRESS_104";
		final String WINERY_IMPORTER_ADDRESS_ZIPCODE_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_ZIPCODE, "Mandatory field zip code can not be blank");
		final String WINERY_IMPORTER_ADDRESS_PHONE = "WI_ADDRESS_105";
		final String WINERY_IMPORTER_ADDRESS_PHONE_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_PHONE, "Mandatory field phone can not be blank");
		final String WINERY_IMPORTER_ADDRESS_FIRST_NAME = "WI_ADDRESS_106";
		final String WINERY_IMPORTER_ADDRESS_FIRST_NAME_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_FIRST_NAME, "Mandatory field first name can not be blank");
		final String WINERY_IMPORTER_ADDRESS_LAST_NAME = "WI_ADDRESS_107";
		final String WINERY_IMPORTER_ADDRESS_LAST_NAME_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_LAST_NAME, "Mandatory field last name can not be blank");
		final String WINERY_IMPORTER_ADDRESS_ADDRESS_TYPE = "WI_ADDRESS_108";
		final String WINERY_IMPORTER_ADDRESS_ADDRESS_TYPE_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_ADDRESS_TYPE, "Mandatory field address type can not be blank");
		final String WINERY_IMPORTER_ADDRESS_COUNTRY = "WI_ADDRESS_109";
		final String WINERY_IMPORTER_ADDRESS_COUNTRY_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_COUNTRY, "Mandatory field country can not be blank");
		final String WINERY_IMPORTER_ADDRESS_ID = "WI_ADDRESS_110";
		final String WINERY_IMPORTER_ADDRESS_ID_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_ID, "Mandatory field address id can not be blank");
		final String WINERY_IMPORTER_IDS = "WI_ADDRESS_111";
		final String WINERY_IMPORTER_IDS_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_IDS, "Neither winery nor importer id is given");
		final String WINERY_IMPORTER_WINERY_ID_NOT_EXIST = "WI_ADDRESS_112";
		final String WINERY_IMPORTER_WINERY_ID_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_WINERY_ID_NOT_EXIST, "winery id doesn't exist");
		final String WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST = "WI_ADDRESS_113";
		final String WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST, "importer id doesn't exist");
		final String WINERY_IMPORTER_ADDRESS_TYPE_NOT_EXIST = "WI_ADDRESS_114";
		final String WINERY_IMPORTER_ADDRESS_TYPE_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_TYPE_NOT_EXIST, "address type doesn't exist");
		final String WINERY_IMPORTER_COUNTRY_NOT_EXIST = "WI_ADDRESS_115";
		final String WINERY_IMPORTER_COUNTRY_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_COUNTRY_NOT_EXIST, "country doesn't exist");
		final String WINERY_IMPORTER_CITY_NOT_EXIST = "WI_ADDRESS_116";
		final String WINERY_IMPORTER_CITY_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_CITY_NOT_EXIST, "city doesn't exist");
		final String WINERY_IMPORTER_STATE_NOT_EXIST = "WI_ADDRESS_117";
		final String WINERY_IMPORTER_STATE_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_STATE_NOT_EXIST, "state doesn't exist");
		final String WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST = "WI_ADDRESS_118";
		final String WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST, "address id doesn't exist");
		final String NO_IDS_TO_DELETE = "WI_ADDRESS_119";
		final String NO_IDS_TO_DELETE_TXT = ErrorPropertyHolderUtil.getProperty(NO_IDS_TO_DELETE, "No ids to delete");
		final String NO_RECORDS_EXIST = "WI_ADDRESS_120";
		final String NO_RECORDS_EXIST_TXT = ErrorPropertyHolderUtil.getProperty(NO_RECORDS_EXIST, "No record exist");
		final String ID_NUMERIC = "COMMON_01";
		final String ID_NUMERIC_TEXT = ErrorPropertyHolderUtil.getProperty(ID_NUMERIC, "Id should be in numeric and can not be empty or null.");
		final String CONTACT_101_ERROR = "CONTACT101";
		final String CONTACT_101_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_101_ERROR, "Contact name already exists.");
		final String CONTACT_102_ERROR = "CONTACT102";
		final String CONTACT_102_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_102_ERROR, "Failed to add new contact.");
		final String CONTACT_103_ERROR = "CONTACT103";
		final String CONTACT_103_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_103_ERROR, "Valid importerId or wineryId is required.");
		final String CONTACT_104_ERROR = "CONTACT104";
		final String CONTACT_104_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_104_ERROR, "Only one field between wineryId or importerId should be passed.");
		final String CONTACT_105_ERROR = "CONTACT105";
		final String CONTACT_105_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_105_ERROR, "No Contact Detail found.");
		final String CONTACT_106_ERROR = "CONTACT106";
		final String CONTACT_106_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_106_ERROR, "Failed to update the contact.");
		final String CONTACT_107_ERROR = "CONTACT107";
		final String CONTACT_107_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_107_ERROR, "Deleting contact(s) failed.");
		final String CONTACT_108_ERROR = "CONTACT108";
		final String CONTACT_108_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_108_ERROR, "No Winery found.");
		final String CONTACT_109_ERROR = "CONTACT109";
		final String CONTACT_109_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_109_ERROR, "No Contact Type found.");
		final String CONTACT_110_ERROR = "CONTACT110";
		final String CONTACT_110_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_110_ERROR, "No Importer found.");
		final String CONTACT_111_ERROR = "CONTACT111";
		final String CONTACT_111_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_111_ERROR, "Mandatory field contactType can not be blank.");
		final String CONTACT_112_ERROR = "CONTACT112";
		final String CONTACT_112_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_112_ERROR, "Mandatory field name can not be blank.");
		final String CONTACT_113_ERROR = "CONTACT113";
		final String CONTACT_113_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_113_ERROR, "Mandatory field phone can not be blank.");
		final String CONTACT_114_ERROR = "CONTACT114";
		final String CONTACT_114_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_114_ERROR, "Email format is incorrect.");
		final String CONTACT_115_ERROR = "CONTACT115";
		final String CONTACT_115_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_115_ERROR, "Mandatory field email can not be blank.");
		final String CONTACT_116_ERROR = "CONTACT116";
		final String CONTACT_116_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_116_ERROR, "Mandatory field isDefault can not be blank.");
		final String CONTACT_117_ERROR = "CONTACT117";
		final String CONTACT_117_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_117_ERROR, "Mandatory field isForceDelete can not be blank.");
		final String CONTACT_118_ERROR = "CONTACT118";
		final String CONTACT_118_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_118_ERROR, "Mandatory field contactId can not be blank.");
		final String CONTACT_119_ERROR = "CONTACT119";
		final String CONTACT_119_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(CONTACT_119_ERROR, "Invalid phone format;");
		final String WINERY_ADD_INVALID_ACTIVE_IMPORTER = "WINERY_ERROR_122";
		final String WINERY_ADD_INVALID_ACTIVE_IMPORTER_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_INVALID_ACTIVE_IMPORTER, "Invalid active importer");
		final String WINERY_UPDATE_INVALID_ACTIVE_IMPORTER = "WINERY_ERROR_123";
		final String WINERY_UPDATE_INVALID_ACTIVE_IMPORTER_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_INVALID_ACTIVE_IMPORTER, "Invalid active importer");
		final String WINERY_DELETE_UNKNOWN_ERROR = "WINERY_ERROR_124";
		final String WINERY_DELETE_UNKNOWN_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_DELETE_UNKNOWN_ERROR, "Unknown Error");
		final String WINERY_ADD_INVALID_WA_CONTACT = "WINERY_ERROR_128";
		final String WINERY_ADD_INVALID_WA_CONTACT_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_INVALID_WA_CONTACT, "Invalid WA Contact");
		final String WINERY_ADD_INVALID_FREIGHT_REGION = "WINERY_ERROR_129";
		final String WINERY_ADD_INVALID_FREIGHT_REGION_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_INVALID_FREIGHT_REGION, "Invalid Freight Region");
		final String WINERY_UPDATE_INVALID_WA_CONTACT = "WINERY_ERROR_130";
		final String WINERY_UPDATE_INVALID_WA_CONTACT_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_INVALID_WA_CONTACT, "Invalid WA Contact");
		final String WINERY_UPDATE_INVALID_FREIGHT_REGION = "WINERY_ERROR_131";
		final String WINERY_UPDATE_INVALID_FREIGHT_REGION_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_UPDATE_INVALID_FREIGHT_REGION, "Invalid Freight Region");
		final String WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR = "WINERY_ERROR_135";
		final String WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR, "No warehouse detail found for the warehouseId passed.");
		final String USER_DELETED_ERROR = "FORGOT_PASSWORD_01";
		final String USER_DELETED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_DELETED_ERROR, "User is deleted, can not send the email");
		final String USER_DISABLED_ERROR = "FORGOT_PASSWORD_02";
		final String USER_DISABLED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_DISABLED_ERROR, "User is disabled, can not send the email");
		final String USER_NOT_REGISTERED_ERROR = "FORGOT_PASSWORD_03";
		final String USER_NOT_REGISTERED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_NOT_REGISTERED_ERROR, "User is not registered, can not send the email");
		final String FORGOT_PASSWORD_DELETED_ERROR = "FORGOT_PASSWORD_04";
		final String FORGOT_PASSWORD_DELETED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(FORGOT_PASSWORD_DELETED_ERROR, "User is deleted, can not update the password");
		final String FORGOT_PASSWORD_DISABLED_ERROR = "FORGOT_PASSWORD_05";
		final String FORGOT_PASSWORD_DISABLED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(FORGOT_PASSWORD_DISABLED_ERROR, "User is disabled, can not update the password");
		final String FORGOT_PASSWORD_NOT_REGISTERED_ERROR = "FORGOT_PASSWORD_06";
		final String FORGOT_PASSWORD_NOT_REGISTERED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(FORGOT_PASSWORD_NOT_REGISTERED_ERROR, "User is not registered, can not update the password");
		final String SEND_MAIL = "USER_PASSWORD_01";
		final String SEND_MAIL_TEXT = ErrorPropertyHolderUtil.getProperty(SEND_MAIL, "Mail has been sent successfully to your registered id");
		final String UPDATE_PASSWORD = "USER_PASSWORD_02";
		final String UPDATE_PASSWORD_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_PASSWORD, "Password changed successfully");
		final String UPDATE_PASSWORD_101 = "UPDATE_PASSWORD_101";
		final String UPDATE_PASSWORD_TEXT_101 = ErrorPropertyHolderUtil.getProperty(UPDATE_PASSWORD_101, "Password saved successfully.");
		final String IMPORTER_101_ERROR = "IMPORTER101";
		final String IMPORTER_101_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_101_ERROR, "Mandatory field importerName is required.");
		final String IMPORTER_102_ERROR = "IMPORTER102";
		final String IMPORTER_102_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_102_ERROR, "Mandatory field countryId is required.");
		final String IMPORTER_103_ERROR = "IMPORTER103";
		final String IMPORTER_103_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_103_ERROR, "Mandatory field accountingCustNumber is required.");
		final String IMPORTER_104_ERROR = "IMPORTER104";
		final String IMPORTER_104_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_104_ERROR, "Mandatory field sourcingStatus is required.");
		final String IMPORTER_105_ERROR = "IMPORTER105";
		final String IMPORTER_105_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_105_ERROR, "Mandatory field wineryId is required.");
		final String IMPORTER_106_ERROR = "IMPORTER106";
		final String IMPORTER_106_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_106_ERROR, "Failed to add new Importer.");
		final String IMPORTER_107_ERROR = "IMPORTER107";
		final String IMPORTER_107_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_107_ERROR, "Mandatory field importerCode is required.");
		final String IMPORTER_108_ERROR = "IMPORTER108";
		final String IMPORTER_108_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_108_ERROR, "Mandatory field importerUrl is required.");
		final String IMPORTER_109_ERROR = "IMPORTER109";
		final String IMPORTER_109_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_109_ERROR, "Importer added Successfully.");
		final String IMPORTER_110_ERROR = "IMPORTER110";
		final String IMPORTER_110_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_110_ERROR, "Winery not found.");
		final String IMPORTER_111_ERROR = "IMPORTER111";
		final String IMPORTER_111_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_111_ERROR, "Country not found.");
		final String IMPORTER_112_ERROR = "IMPORTER112";
		final String IMPORTER_112_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_112_ERROR, "Sourcing status not found.");
		final String IMPORTER_113_ERROR = "IMPORTER113";
		final String IMPORTER_113_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_113_ERROR, "Error while adding an Importer.");
		final String IMPORTER_114_ERROR = "IMPORTER114";
		final String IMPORTER_114_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_114_ERROR, "Importer Name already exists.");
		final String IMPORTER_115_ERROR = "IMPORTER115";
		final String IMPORTER_115_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_115_ERROR, "Importer Code already exists.");
		final String IMPORTER_116_ERROR = "IMPORTER116";
		final String IMPORTER_116_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_116_ERROR, "Importer updated Successfully.");
		final String IMPORTER_117_ERROR = "IMPORTER117";
		final String IMPORTER_117_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_117_ERROR, "Error while updating an Importer.");
		final String IMPORTER_118_ERROR = "IMPORTER118";
		final String IMPORTER_118_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_118_ERROR, "Error while deleting importer(s).");
		final String IMPORTER_119_ERROR = "IMPORTER119";
		final String IMPORTER_119_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_119_ERROR, "Mandatory field importerId is required.");
		final String IMPORTER_122_ERROR = "IMPORTER122";
		final String IMPORTER_122_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_122_ERROR, "Invalid waContact.");
		final String IMPORTER_123_ERROR = "IMPORTER123";
		final String IMPORTER_123_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_123_ERROR, "Invalid freightRegion.");
		final String MASTER_DATA_UPDATE_BY_ID_INVALID_ID = "MASTERDATA109";
		final String MASTER_DATA_UPDATE_BY_ID_INVALID_ID_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_UPDATE_BY_ID_INVALID_ID, "Master Data Id is invalid");;
		final String MASTER_DATA_UPDATE_BY_ID_DUPLICATE_NAME = "MASTERDATA110";
		final String MASTER_DATA_UPDATE_BY_ID_DUPLICATE_NAME_TEXT = ErrorPropertyHolderUtil.getProperty(MASTER_DATA_UPDATE_BY_ID_DUPLICATE_NAME, "A Master Data with same name already exists");;
	
		final String WINE_ADD_SUCCESS = "WINE_ADD_SUCCESS";
		final String WINE_ADD_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_ADD_SUCCESS, "Wine add successfully.");
	
		final String INVALID_WINE_STYLE = "WINE_ERROR_120";
		final String INVALID_WINE_STYLE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_WINE_STYLE, "Invalid Wine Style");
	
		final String INVALID_VINTAGE = "WINE_ERROR_121";
		final String INVALID_VINTAGE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_VINTAGE, "Invalid Vintage");
	
		final String INVALID_VARIETAL = "WINE_ERROR_122";
		final String INVALID_VARIETAL_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_VARIETAL, "Invalid Varietal");
	
		final String INVALID_BOTTLE_IN_ML = "WINE_ERROR_123";
		final String INVALID_BOTTLE_IN_ML_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_BOTTLE_IN_ML, "Invalid Bottle in ml");
	
		final String INVALID_WINERY = "WINE_ERROR_124";
		final String INVALID_WINERY_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_WINERY, "Invalid Winery");
	
		final String INVALID_SOURCING = "WINE_ERROR_125";
		final String INVALID_SOURCING_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_SOURCING, "Invalid Sourcing");
	
		final String WINE_ADD_WINE_DUPLICATE = "WINE_ERROR_126";
		final String WINE_ADD_WINE_DUPLICATE_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_ADD_WINE_DUPLICATE, "Duplicate Wine");
	
		final String WINE_ADD_UNKNOWN_ERROR = "WINE_ERROR_127";
		final String WINE_ADD_UNKNOWN_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_ADD_UNKNOWN_ERROR, "Unknown Error");
		
		final String WINE_UPDATE_STATUS_ERROR = "WINE_ERROR_141";
		final String WINE_UPDATE_STATUS_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_UPDATE_STATUS_ERROR, "Error while updating the status of Wines.");
		
		final String WINE_ADD_IMPORTER_MISSING_ERROR = "WINE_ERROR_132";
		final String WINE_ADD_IMPORTER_MISSING_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_ADD_IMPORTER_MISSING_ERROR, "No importer exists for the selected Winery.");
		
		final String WINE_PRODUCT_ID_MISSING = "WINE_ERROR_151";
		final String WINE_PRODUCT_ID_MISSING_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_PRODUCT_ID_MISSING, "Mandatory field productId is required.");
		
	
		final String WINE_DETAILS_ERROR = "VIEW_WINE_102";
		final String WINE_DETAILS_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_DETAILS_ERROR, "No Wine details found for this wine id.");
	
		final String WINE_DETAILS_ERROR_103 = "VIEW_WINE_103";
		final String WINE_DETAILS_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_DETAILS_ERROR_103, "Error while getting the Wine List.");
	
		final String IMPORTER_124_ERROR = "IMPORTER124";
		final String IMPORTER_124_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_124_ERROR, "Importer not found");
	
		final String WINE_UPDATE_SUCCESS = "WINE_UPDATE_SUCCESS";
		final String WINE_UPDATE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_UPDATE_SUCCESS, "Wine update successfully.");
	
		final String UPDATE_WINE_INVALID_VINTAGE = "UPDATE_WINE_ERROR_108";
		final String UPDATE_WINE_INVALID_VINTAGE_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_INVALID_VINTAGE, "Invalid Vintage");
	
		final String UPDATE_WINE_INVALID_BOTTLE_IN_ML = "UPDATE_WINE_ERROR_109";
		final String UPDATE_WINE_INVALID_BOTTLE_IN_ML_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_INVALID_BOTTLE_IN_ML, "Invalid Bottle in ml");
	
		final String UPDATE_WINE_INVALID_WINE_ID = "UPDATE_WINE_ERROR_110";
		final String UPDATE_WINE_INVALID_WINE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_INVALID_WINE_ID, "Invalid Wine Id");
	
		final String WINE_UPDATE_WINE_DUPLICATE = "UPDATE_WINE_ERROR_111";
		final String WINE_UPDATE_WINE_DUPLICATE_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_UPDATE_WINE_DUPLICATE, "Duplicate Wine");
	
		final String WINE_UPDATE_UNKNOWN_ERROR = "UPDATE_WINE_ERROR_112";
		final String WINE_UPDATE_UNKNOWN_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_UPDATE_UNKNOWN_ERROR, "Unknown Error");
	
		final String LOGISTIC_INVALID_WINERY = "LOGISTIC_ERROR_114";
		final String LOGISTIC_INVALID_WINERY_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_WINERY, "Invalid Winery");
	
		final String LOGISTIC_INVALID_WINE = "LOGISTIC_ERROR_115";
		final String LOGISTIC_INVALID_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_WINE, "Invalid Wine");
	
		final String LOGISTIC_INVALID_WINE_WINERY = "LOGISTIC_ERROR_116";
		final String LOGISTIC_INVALID_WINE_WINERY_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_WINE_WINERY, "Invalid Wine and winery combination");
	
		final String LOGISTIC_INVALID_ADDRESS = "LOGISTIC_ERROR_117";
		final String LOGISTIC_INVALID_ADDRESS_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_ADDRESS, "Invalid addressid");
	
		final String LOGISTIC_INVALID_CONTACT = "LOGISTIC_ERROR_123";
		final String LOGISTIC_INVALID_CONTACT_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_CONTACT, "Invalid contactId");
	
		final String LOGISTIC_INVALID_BOTTLE_PER_BOX = "LOGISTIC_ERROR_118";
		final String LOGISTIC_INVALID_BOTTLE_PER_BOX_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_BOTTLE_PER_BOX, "Invalid bottle per box");
	
		final String LOGISTIC_NOT_CREATED = "LOGISTIC_ERROR_120";
		final String LOGISTIC_NOT_CREATED_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_NOT_CREATED, "Logistic couldn't be created");
	
		final String LOGISTIC_NO_RECORDS_EXIST = "LOGISTIC_ERROR_121";
		final String LOGISTIC_NO_RECORDS_EXIST_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_NO_RECORDS_EXIST, "No logistic record exist for wine");
	
		final String LOGISTIC_VIEW_ERROR = "LOGISTIC_ERROR_122";
		final String LOGISTIC_VIEW_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_VIEW_ERROR, "Some error occurred");
		
		final String LOGISTIC_INVALID_PRODUCT = "LOGISTIC_ERROR_124";
		final String LOGISTIC_INVALID_PRODUCT_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_PRODUCT, "Invalid productId");
		
		//final String PERMIT_WINE_ERROR = "WINERY_PERMIT_ERROR_115";
		//final String PERMIT_WINE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_WINE_ERROR, "No record found for wine");
		
		final String PERMIT_NO_OPTION_SELECTED_ERROR = "WINERY_PERMIT_ERROR_116";
		final String PERMIT_NO_OPTION_SELECTED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_NO_OPTION_SELECTED_ERROR, "Please select at least one of sellInMailstate or sellInAltstate");
		
		final String PERMIT_NO_OPTION_SELECTED_WINERY_LICENCES_ERROR = "WINERY_PERMIT_ERROR_117";
		final String PERMIT_NO_OPTION_SELECTED_WINERY_LICENCES_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_NO_OPTION_SELECTED_WINERY_LICENCES_ERROR, "Please select at least one of licences");
		
		final String PERMIT_NO_SELECTED_ALT_STATES_ERROR = "WINERY_PERMIT_ERROR_117";
		final String PERMIT_NO_SELECTED_ALT_STATES_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_NO_SELECTED_ALT_STATES_ERROR, "Please select at least one of permit");
		
		final String NO_WINERY_PERMITS_ERROR = "WINERY_PERMIT_ERROR_118";
		final String NO_WINERY_PERMITS_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NO_WINERY_PERMITS_ERROR, "No winery permit is given");
		
		final String PERMIT_INVALID_MASTER_DATA = "WINERY_PERMIT_ERROR_119";
		final String PERMIT_INVALID_MASTER_DATA_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_INVALID_MASTER_DATA, "Invalid winery permit");
		
		final String PERMIT_INVALID_DURATION = "WINERY_PERMIT_ERROR_120";
		final String PERMIT_INVALID_DURATION_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_INVALID_DURATION, "Invalid duration");
		
		final String PERMIT_INVALID_PERMIT_DURATION = "WINERY_PERMIT_ERROR_121";
		final String PERMIT_INVALID_PERMIT_DURATION_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_INVALID_PERMIT_DURATION, "DTC permit number & DTC permit duration is required for at least one line item");
	
		final String PERMIT_WINERY_ERROR = "WINERY_PERMIT_ERROR_122";
		final String PERMIT_WINERY_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_WINERY_ERROR, "No record found for winery");
		
		final String PERMIT_FAILURE_ERROR = "WINERY_PERMIT_ERROR_123";
		final String PERMIT_FAILURE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_FAILURE_ERROR, "Permit couldn't be created");
		
		final String PERMIT_DUPLICATE_DTC_PERMIT_NUMBER = "WINERY_PERMIT_ERROR_124";
		final String PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_DUPLICATE_DTC_PERMIT_NUMBER, "DTC permit number can't be duplicate");
		
		final String PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR = "WINERY_PERMIT_ERROR_125";
		final String PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR, "Please select only one value under permit");
		
		final String WINERY_PERMIT_ERROR_102 = "WINERY_PERMIT_ERROR_102";
		final String WINERY_PERMIT_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_PERMIT_ERROR_102, "Invalid value of wineryId");
		

		
		final String PERMIT_WINE_ERROR = "WINE_PERMIT_ERROR_122";
		final String PERMIT_WINE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_WINE_ERROR, "No record found for wine");
		
		final String PERMIT_NO_OPTION_SELECTED_ERROR_WINE = "WINE_PERMIT_ERROR_116";
		final String PERMIT_NO_OPTION_SELECTED_ERROR_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_NO_OPTION_SELECTED_ERROR_WINE, "Please select at least one of sellInMailstate or sellInAltstate");
		
		final String PERMIT_NO_OPTION_SELECTED_WINE_LICENCES_ERROR_WINE = "WINE_PERMIT_ERROR_117";
		final String PERMIT_NO_OPTION_SELECTED_WINE_LICENCES_ERROR_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_NO_OPTION_SELECTED_WINE_LICENCES_ERROR_WINE, "Please select at least one of licences");
		
		final String PERMIT_NO_SELECTED_ALT_STATES_ERROR_WINE = "WINE_PERMIT_ERROR_117";
		final String PERMIT_NO_SELECTED_ALT_STATES_ERROR_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_NO_SELECTED_ALT_STATES_ERROR_WINE, "Please select at least one of permit");
		
		final String NO_PERMIT_INVALID_MASTER_DATA_WINE = "WINE_PERMIT_ERROR_131";
		final String NO_PERMIT_INVALID_MASTER_DATA_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(NO_PERMIT_INVALID_MASTER_DATA_WINE, "Invalid wine no permit is given");
		
		final String NO_WINE_PERMITS_ERROR = "WINE_PERMIT_ERROR_118";
		final String NO_WINE_PERMITS_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(NO_WINE_PERMITS_ERROR, "No WINE permit is given");
		
		final String PERMIT_INVALID_MASTER_DATA_WINE = "WINE_PERMIT_ERROR_119";
		final String PERMIT_INVALID_MASTER_DATA_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_INVALID_MASTER_DATA_WINE, "Invalid WINE permit");
		
		final String PERMIT_INVALID_DURATION_WINE = "WINE_PERMIT_ERROR_120";
		final String PERMIT_INVALID_DURATION_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_INVALID_DURATION_WINE, "Invalid duration");
		
		final String PERMIT_INVALID_PERMIT_DURATION_WINE = "WINE_PERMIT_ERROR_121";
		final String PERMIT_INVALID_PERMIT_DURATION_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_INVALID_PERMIT_DURATION_WINE, "DTC permit number & DTC permit duration is required for at least one line item");
	
	
		
		final String PERMIT_FAILURE_ERROR_WINE = "WINE_PERMIT_ERROR_123";
		final String PERMIT_FAILURE_ERROR_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_FAILURE_ERROR_WINE, "Permit couldn't be created");
		
		final String PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_WINE = "WINE_PERMIT_ERROR_124";
		final String PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_WINE, "DTC permit number can't be duplicate");
		
		final String PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_WINE = "WINE_PERMIT_ERROR_125";
		final String PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_WINE, "Please select only one value under permit");
		
		final String WINE_PERMIT_ERROR_102 = "WINE_PERMIT_ERROR_102";
		final String WINE_PERMIT_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_PERMIT_ERROR_102, "Invalid value of product id");
		
		final String WINE_PERMIT_SELECT_VALUE_NO_PERMIT = "WINE_PERMIT_ERROR_126";
		final String WINE_PERMIT_SELECT_VALUE_NO_PERMIT_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_PERMIT_SELECT_VALUE_NO_PERMIT, "No permit is selected but no value is provided");
		
		final String WINE_PERMIT_NOT_SELECT_VALUE_NO_PERMIT = "WINE_PERMIT_ERROR_127";
		final String WINE_PERMIT_NOT_SELECT_VALUE_NO_PERMIT_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_PERMIT_NOT_SELECT_VALUE_NO_PERMIT, "No permit is not selected but value is provided");
		
		
		
		
		
		
		final String COUNTRY_NOT_FOUND = "COUNTRY_ERROR_404";
		final String COUNTRY_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(COUNTRY_NOT_FOUND, "Country not found");
		
		final String STATE_NOT_FOUND = "STATE_ERROR_404";
		final String STATE_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(STATE_NOT_FOUND, "State not found");
		
		final String CITY_NOT_FOUND = "CITY_ERROR_404";
		final String CITY_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(CITY_NOT_FOUND, "City not found");
	
		final String WINERY_OWS_ADD_SUCCESS = "WINERY_OWS_ADD_SUCCESS";
		final String WINERY_OWS_ADD_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_OWS_ADD_SUCCESS, "Winery historical Ows data added successfully.");
		
		final String WINERY_NOT_FOUND_OWS = "WINERY_OWS_ERROR_103";
		final String WINERY_NOT_FOUND_OWS_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_NOT_FOUND_OWS, "No winery exists for the wineryId.");
		
		final String WINERY_OWS_ERROR_104 = "WINERY_OWS_ERROR_104";
		final String WINERY_OWS_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_OWS_ERROR_104, "No winery historical Ows data found for this wineryId.");
		
		final String WINERY_OWS_UPDATE_SUCCESS = "WINERY_OWS_UPDATE_SUCCESS";
		final String WINERY_OWS_UPDATE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_OWS_UPDATE_SUCCESS, "Winery historical Ows data updated successfully.");
	
		final String ADD_WINERY_LICENSE_SUCCESS = "ADD_WINERY_LICENSE_SUCCESS";
		final String ADD_WINERY_LICENSE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINERY_LICENSE_SUCCESS, "Winery License details add successfully.");
		
		final String ADD_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE = "WINERY_LICENSE_DETAIL_107";
		final String ADD_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE, "Invalid CA License Type");
		
		final String ADD_WINERY_LICENSE_INVALID_WINERY = "WINERY_LICENSE_DETAIL_108";
		final String ADD_WINERY_LICENSE_INVALID_WINERY_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINERY_LICENSE_INVALID_WINERY, "Invalid Winery");
		
		final String ADD_WINERY_LICENSE_ALREADY_EXISTS = "WINERY_LICENSE_DETAIL_109";
		final String ADD_WINERY_LICENSE_ALREADY_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINERY_LICENSE_ALREADY_EXISTS, "Winery License Detail already exists");
		
		
		
		final String UPDATE_WINERY_LICENSE_SUCCESS = "UPDATE_WINERY_LICENSE_SUCCESS";
		final String UPDATE_WINERY_LICENSE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINERY_LICENSE_SUCCESS, "Winery License details updated successfully.");
		
		final String UPDATE_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE = "UPDATE_WINERY_LICENSE_DETAIL_108";
		final String UPDATE_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE, "Invalid CA License Type");
		
		final String UPDATE_WINERY_LICENSE_INVALID_WINERY = "UPDATE_WINERY_LICENSE_DETAIL_109";
		final String UPDATE_WINERY_LICENSE_INVALID_WINERY_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINERY_LICENSE_INVALID_WINERY, "Invalid Winery");
		
		final String UPDATE_WINERY_LICENSE_INVALID_WINERY_LICENSE_ID = "UPDATE_WINERY_LICENSE_DETAIL_110";
		final String UPDATE_WINERY_LICENSE_INVALID_WINERY_LICENSE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINERY_LICENSE_INVALID_WINERY_LICENSE_ID, "Invalid Winery License Id");
		
		final String UPDATE_WINERY_LICENSE_ALREADY_EXISTS = "UPDATE_WINERY_LICENSE_DETAIL_111";
		final String UPDATE_WINERY_LICENSE_ALREADY_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINERY_LICENSE_ALREADY_EXISTS, "Winery License Detail already exists");
				
		final String VIEW_WINERY_LICENSE_INVALID_WINERY_LICENSE_ID = "VIEW_WINERY_LICENSE_DETAIL_103";
		final String VIEW_WINERY_LICENSE_INVALID_WINERY_LICENSE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_WINERY_LICENSE_INVALID_WINERY_LICENSE_ID, "Invalid Winery License Id");
		
		final String VIEW_WINERY_LICENSE_INVALID_WINERY = "VIEW_WINERY_LICENSE_DETAIL_104";
		final String VIEW_WINERY_LICENSE_INVALID_WINERY_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_WINERY_LICENSE_INVALID_WINERY, "Invalid Winery");
		
		final String WINE_OWS_ERROR_103 = "WINE_OWS_ERROR_103";
		final String WINE_OWS_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_OWS_ERROR_103, "No wine historical Ows data found for this wineId.");
		
		final String WINE_OWS_ERROR_104 = "WINE_OWS_ERROR_104";
		final String WINE_OWS_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_OWS_ERROR_104, "No wine historical Ows data found for this productId.");
		
		final String WINE_OWS_UPDATE_SUCCESS = "WINE_OWS_UPDATE_SUCCESS";
		final String WINE_OWS_UPDATE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_OWS_UPDATE_SUCCESS, "Wine historical Ows data updated successfully.");
		final String ADD_NEWSLETTER_DUPLICATE_ERROR = "NEWSLETTER123";
		final String ADD_NEWSLETTER_DUPLICATE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_NEWSLETTER_DUPLICATE_ERROR, "A NewsLetter with the same name already exists");
	
		final String UPDATE_NEWSLETTER_DUPLICATE_ERROR = "NEWSLETTER124";
		final String UPDATE_NEWSLETTER_DUPLICATE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_NEWSLETTER_DUPLICATE_ERROR, "A NewsLetter with the same name already exists");
	
		final String USER_DEPENDENT_ADDRESS_ERROR = "USER134";
		final String USER_DEPENDENT_ADDRESS_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(USER_DEPENDENT_ADDRESS_ERROR, "A Credit Card is associated with address. Address can not be deleted");
	
		final String DUPLICATE_CC_ERROR = "USER135";
		final String DUPLICATE_CC_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(DUPLICATE_CC_ERROR, "A Credit Card of same number is already added for the user");
	
		final String DEFAULT_CREDIT_CARD_DELETE_ERROR = "USER136";
		final String DEFAULT_CREDIT_CARD_DELETE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(DEFAULT_CREDIT_CARD_DELETE_ERROR, "Default Credit Card can not be deleted");
	
	
	
		final String ADD_WINE_LICENSE_SUCCESS = "ADD_WINE_LICENSE_SUCCESS";
		final String ADD_WINE_LICENSE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINE_LICENSE_SUCCESS, "Wine License details add successfully.");
		
		final String ADD_WINE_LICENSE_INVALID_CA_LICENSE_TYPE = "WINE_LICENSE_DETAIL_110";
		final String ADD_WINE_LICENSE_INVALID_CA_LICENSE_TYPE_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINE_LICENSE_INVALID_CA_LICENSE_TYPE, "Invalid CA License Type");
		
		final String ADD_WINE_LICENSE_INVALID_WINE = "WINE_LICENSE_DETAIL_111";
		final String ADD_WINE_LICENSE_INVALID_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINE_LICENSE_INVALID_WINE, "Invalid Wine");
		
		final String ADD_WINE_LICENSE_ALREADY_EXISTS = "WINE_LICENSE_DETAIL_112";
		final String ADD_WINE_LICENSE_ALREADY_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_WINE_LICENSE_ALREADY_EXISTS, "Wine License Detail already exists");
		
		final String UPDATE_WINE_LICENSE_SUCCESS = "UPDATE_WINE_LICENSE_SUCCESS";
		final String UPDATE_WINE_LICENSE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_LICENSE_SUCCESS, "Wine License details updated successfully.");
		
		final String UPDATE_WINE_LICENSE_INVALID_CA_LICENSE_TYPE = "UPDATE_WINE_LICENSE_DETAIL_110";
		final String UPDATE_WINE_LICENSE_INVALID_CA_LICENSE_TYPE_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_LICENSE_INVALID_CA_LICENSE_TYPE, "Invalid CA License Type");
		
		final String UPDATE_WINE_LICENSE_INVALID_WINE = "UPDATE_WINE_LICENSE_DETAIL_111";
		final String UPDATE_WINE_LICENSE_INVALID_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_LICENSE_INVALID_WINE, "Invalid Wine");
		
		final String UPDATE_WINE_LICENSE_INVALID_WINE_LICENSE_ID = "UPDATE_WINE_LICENSE_DETAIL_112";
		final String UPDATE_WINE_LICENSE_INVALID_WINE_LICENSE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_LICENSE_INVALID_WINE_LICENSE_ID, "Wine License Detail Not exist");
		
		final String UPDATE_WINE_LICENSE_ALREADY_EXISTS = "UPDATE_WINE_LICENSE_DETAIL_113";
		final String UPDATE_WINE_LICENSE_ALREADY_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_LICENSE_ALREADY_EXISTS, "Wine License Detail already exists");
		
		final String UPDATE_WINE_PRODUCT_NOT_EXISTS = "UPDATE_WINE_LICENSE_DETAIL_114";
		final String UPDATE_WINE_PRODUCT_NOT_EXISTS_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_WINE_PRODUCT_NOT_EXISTS, "ProductId does not exists");
		
		final String VIEW_WINE_LICENSE_INVALID_WINE_LICENSE_ID = "VIEW_WINE_LICENSE_DETAIL_103";
		final String VIEW_WINE_LICENSE_INVALID_WINE_LICENSE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_WINE_LICENSE_INVALID_WINE_LICENSE_ID, "Wine License Detail Not exist");
		
		final String VIEW_WINE_LICENSE_INVALID_WINE = "VIEW_WINE_LICENSE_DETAIL_104";
		final String VIEW_WINE_LICENSE_INVALID_WINE_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_WINE_LICENSE_INVALID_WINE, "Wine does not exists");
		
		final String VIEW_WINE_LICENSE_INVALID_PRODUCT = "VIEW_WINE_LICENSE_DETAIL_105";
		final String VIEW_WINE_LICENSE_INVALID_PRODUCT_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_WINE_LICENSE_INVALID_PRODUCT, "productId does not exists");
		
		/*final String REQUISITION_ERROR_101 = "REQUISITION_ERROR_101";
		final String REQUISITION_ERROR_101_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_101, "Invalid Requisition Type");*/
		
		/*final String REQUISITION_ERROR_102 = "REQUISITION_ERROR_102";
		final String REQUISITION_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_102, "No Winery exists for the winery id");
		
		final String REQUISITION_ERROR_103 = "REQUISITION_ERROR_103";
		final String REQUISITION_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_103, "Requisition added successfully.");
		
		final String REQUISITION_ERROR_114 = "REQUISITION_ERROR_114";
		final String REQUISITION_ERROR_114_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_114, "No Requisition exists for the requisition id passed");
		
		final String REQUISITION_ERROR_115 = "REQUISITION_ERROR_115";
		final String REQUISITION_ERROR_115_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_115, "Requisition already submitted and hence the Requisition details can not be updated.");
		
		final String REQUISITION_ERROR_116 = "REQUISITION_ERROR_116";
		final String REQUISITION_ERROR_116_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_116, "Requisition updated successfully.");
		
		final String REQUISITION_ERROR_117 = "REQUISITION_ERROR_117";
		final String REQUISITION_ERROR_117_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_117, "No Distribution Center exists for the distributionCenterId passed.");
		
		final String REQUISITION_ERROR_118 = "REQUISITION_ERROR_118";
		final String REQUISITION_ERROR_118_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_118, "Requisition already submitted and hence wines can not be removed from the Requisition.");
		
		final String REQUISITION_ERROR_119 = "REQUISITION_ERROR_119";
		final String REQUISITION_ERROR_119_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_119, "No Wine exists for the productId(s) passed.");
		
		final String REQUISITION_ERROR_120 = "REQUISITION_ERROR_120";
		final String REQUISITION_ERROR_120_TEXT = ErrorPropertyHolderUtil.getProperty(REQUISITION_ERROR_120, "Unknown error occurred while removing wine from requisition.");
		*/
		final String WINE_ADD_WINE_INVALID_MASTER_DATA = "WINE_ERROR_136";
		final String WINE_ADD_WINE_INVALID_MASTER_DATA_TEXT = ErrorPropertyHolderUtil.getProperty(WINE_ADD_WINE_INVALID_MASTER_DATA, "Invalid Master Data");	
		
		final String VIEW_WINE_INVALID_WINE_ID = "VIEW_WINE_105";
		final String VIEW_WINE_INVALID_WINE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_WINE_INVALID_WINE_ID, "Invalid Wine Id");
	
		final String WAREHOUSE_ERROR_101 = "WAREHOUSE101";
		final String WAREHOUSE_ERROR_101_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_101, "Mandatory field warehouseName is required.");
		
		final String WAREHOUSE_ERROR_102 = "WAREHOUSE102";
		final String WAREHOUSE_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_102, "Mandatory field addressLine1 is required.");
		
		final String WAREHOUSE_ERROR_103 = "WAREHOUSE103";
		final String WAREHOUSE_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_103, "Mandatory field cityId is required.");
		
		final String WAREHOUSE_ERROR_104 = "WAREHOUSE104";
		final String WAREHOUSE_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_104, "Invalid cityId is passed.");
		
		final String WAREHOUSE_ERROR_105 = "WAREHOUSE105";
		final String WAREHOUSE_ERROR_105_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_105, "Mandatory field stateId is required.");
		
		final String WAREHOUSE_ERROR_106 = "WAREHOUSE106";
		final String WAREHOUSE_ERROR_106_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_106, "Invalid stateId is passed.");
		
		final String WAREHOUSE_ERROR_107 = "WAREHOUSE107";
		final String WAREHOUSE_ERROR_107_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_107, "Mandatory field zipcode is required.");
		
		final String WAREHOUSE_ERROR_108 = "WAREHOUSE108";
		final String WAREHOUSE_ERROR_108_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_108, "Mandatory field firstName is required.");
		
		final String WAREHOUSE_ERROR_109 = "WAREHOUSE109";
		final String WAREHOUSE_ERROR_109_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_109, "Mandatory field lastName is required.");
		
		final String WAREHOUSE_ERROR_110 = "WAREHOUSE110";
		final String WAREHOUSE_ERROR_110_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_110, "Mandatory field emailId is required.");
		
		final String WAREHOUSE_ERROR_111 = "WAREHOUSE111";
		final String WAREHOUSE_ERROR_111_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_111, "Invalid emailId is passed.");
		
		final String WAREHOUSE_ERROR_112 = "WAREHOUSE112";
		final String WAREHOUSE_ERROR_112_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_112, "Mandatory field phone is required.");
	
		final String WAREHOUSE_ERROR_113 = "WAREHOUSE113";
		final String WAREHOUSE_ERROR_113_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_113, "Invalid phone is passed.");
	
		final String WAREHOUSE_ERROR_114 = "WAREHOUSE114";
		final String WAREHOUSE_ERROR_114_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_114, "Invalid faxNumber is passed.");
		
		final String WAREHOUSE_ERROR_115 = "WAREHOUSE115";
		final String WAREHOUSE_ERROR_115_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_115, "Invalid overrideDeletedRecord is passed.");
		
		final String WAREHOUSE_ERROR_116 = "WAREHOUSE116";
		final String WAREHOUSE_ERROR_116_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_116, "Error occurred while adding Warehouse.");
		
		final String WAREHOUSE_ERROR_117 = "WAREHOUSE117";
		final String WAREHOUSE_ERROR_117_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_117, "Warehouse name already exists.");
		
		final String WAREHOUSE_ERROR_118 = "WAREHOUSE118";
		final String WAREHOUSE_ERROR_118_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_118, "City does not exists.");
		
		final String WAREHOUSE_ERROR_119 = "WAREHOUSE119";
		final String WAREHOUSE_ERROR_119_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_119, "State does not exists.");
		
		final String WAREHOUSE_ERROR_120 = "WAREHOUSE120";
		final String WAREHOUSE_ERROR_120_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_120, "Mandatory field warehouseId is required.");
		
		final String WAREHOUSE_ERROR_121 = "WAREHOUSE121";
		final String WAREHOUSE_ERROR_121_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_121, "Invalid warehouseId is passed.");
		
		final String WAREHOUSE_ERROR_122 = "WAREHOUSE122";
		final String WAREHOUSE_ERROR_122_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_122, "No Warehouse details found for the warehouseId passed.");
		
		final String WAREHOUSE_ERROR_123 = "WAREHOUSE123";
		final String WAREHOUSE_ERROR_123_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_123, "Error occurred while updating Warehouse.");
		
		final String WAREHOUSE_ERROR_124 = "WAREHOUSE124";
		final String WAREHOUSE_ERROR_124_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_124, "Error occurred while getting Warehouse.");
		
		final String WAREHOUSE_ERROR_125 = "WAREHOUSE125";
		final String WAREHOUSE_ERROR_125_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_125, "No record exists for warehouseId passed.");
		
		final String WAREHOUSE_ERROR_126 = "WAREHOUSE126";
		final String WAREHOUSE_ERROR_126_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_126, "Invalid param.");
		
		final String WAREHOUSE_ERROR_127 = "WAREHOUSE127";
		final String WAREHOUSE_ERROR_127_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_127, "Invalid isForceDelete is passed.");
		
		final String WAREHOUSE_ERROR_128 = "WAREHOUSE128";
		final String WAREHOUSE_ERROR_128_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_128, "Invalid freightRegion is passed.");
		
		final String WAREHOUSE_ERROR_129 = "WAREHOUSE129";
		final String WAREHOUSE_ERROR_129_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_129, "No Freight Region details found for the freightRegion passed.");
		
		final String WAREHOUSE_ERROR_130 = "WAREHOUSE130";
		final String WAREHOUSE_ERROR_130_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_130, "Mandatory field freightRegion is required.");
		
		final String WAREHOUSE_ERROR_131 = "WAREHOUSE131";
		final String WAREHOUSE_ERROR_131_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_131, "Error occurred while deleting Warehouse.");
		
		
		final String WAREHOUSE_ERROR_CARRIER_REQUIRED = "WAREHOUSE152";
		final String WAREHOUSE_ERROR_CARRIER_REQUIRED_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_CARRIER_REQUIRED, "Mandatory field carrierId is required.");
		
		final String WAREHOUSE_ERROR_CARRIER_NOT_FOUND = "WAREHOUSE153";
		final String WAREHOUSE_ERROR_CARRIER_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_CARRIER_NOT_FOUND, "Master data Carrier not found");
		
		
	
		final String INVALID_BOTTLES_PER_BOX = "WINE_ERROR_142";
		final String INVALID_BOTTLES_PER_BOX_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_BOTTLES_PER_BOX, "Invalid Bottles Per Box");
	    final String DEFAULT_ADDRESS_NOT_CHANGED = "USER137";
		final String DEFAULT_ADDRESS_NOT_CHANGED_TXT = ErrorPropertyHolderUtil.getProperty(DEFAULT_ADDRESS_NOT_CHANGED, "Please select another address as default address");
		
		final String DISTRIBUTION_CENTRE_ERROR_115 = "DISTRIBUTION_CENTRE_ERROR_115";
		final String DISTRIBUTION_CENTRE_ERROR_115_TEXT = ErrorPropertyHolderUtil.getProperty(DISTRIBUTION_CENTRE_ERROR_115, "State should be from USA.");
		
		final String ADD_DISTRIBUTION_CENTRE_ERROR_116 = "ADD_DISTRIBUTION_CENTRE_ERROR_116";
		final String ADD_DISTRIBUTION_CENTRE_ERROR_116_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_DISTRIBUTION_CENTRE_ERROR_116, "State does not exists.");
		
		final String ADD_DISTRIBUTION_CENTRE_ERROR_117 = "ADD_DISTRIBUTION_CENTRE_ERROR_117";
		final String ADD_DISTRIBUTION_CENTRE_ERROR_117_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_DISTRIBUTION_CENTRE_ERROR_117, "City does not exists.");
		
		final String DISTRIBUTION_CENTRE_ADD_SUCCESS = "DISTRIBUTION_CENTRE_ADD_SUCCESS";
		final String DISTRIBUTION_CENTRE_ADD_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(DISTRIBUTION_CENTRE_ADD_SUCCESS, "Distribution Centre added successfully.");
		
		final String NO_REQ_FOUND = "ADD_WINE_REQUISITION_ERROR_111";
		final String NO_REQ_FOUND_TEXT =  ErrorPropertyHolderUtil.getProperty(NO_REQ_FOUND, "No requistion found");
		
		final String NO_WINE_FOUND = "ADD_WINE_REQUISITION_ERROR_112";
		final String NO_WINE_FOUND_TEXT =  ErrorPropertyHolderUtil.getProperty(NO_WINE_FOUND, "No wine found");
		
		final String WINE_AND_REQ_NOT_HAVING_SAME_WINERY = "ADD_WINE_REQUISITION_ERROR_113";
		final String WINE_AND_REQ_NOT_HAVING_SAME_WINERY_TEXT =  ErrorPropertyHolderUtil.getProperty(WINE_AND_REQ_NOT_HAVING_SAME_WINERY, "Wine and requisition not having same winery");
		
		
		final String NO_WINE_FOUND_IN_PRODUCT = "ADD_WINE_REQUISITION_ERROR_114";
		final String NO_WINE_FOUND_IN_PRODUCT_TEXT =  ErrorPropertyHolderUtil.getProperty(NO_WINE_FOUND_IN_PRODUCT, "No wine found in product item");
		
		
		
		final String WINE_ADDED_TO_PO_FAILURE = "ADD_WINE_REQUISITION_ERROR_115";
		final String WINE_ADDED_TO_PO_FAILURE_TEXT =  ErrorPropertyHolderUtil.getProperty(WINE_ADDED_TO_PO_FAILURE, "Wine couldn't be added to po");
		
		final String WINE_ALREADY_ADDED_IN_PO = "ADD_WINE_REQUISITION_ERROR_116";
		final String WINE_ALREADY_ADDED_IN_PO_TEXT =  ErrorPropertyHolderUtil.getProperty(WINE_ALREADY_ADDED_IN_PO, "wine already added in PO");
		
		final String ADD_WINE_TO_REQUISITION_INVALID_PRODUCT_ID = "ADD_WINE_REQUISITION_ERROR_117";
		final String ADD_WINE_TO_REQUISITION_INVALID_PRODUCT_ID_TEXT =  ErrorPropertyHolderUtil.getProperty(ADD_WINE_TO_REQUISITION_INVALID_PRODUCT_ID, "Invalid Product Id");
		
		final String ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE = "ADD_WINE_REQUISITION_ERROR_118";
		final String ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT =  ErrorPropertyHolderUtil.getProperty(ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE, "Wine and Requistion is not compatible");
		
		
		final String EDIT_WINE_REQUISITION_NO_REQ_FOUND = "EDIT_WINE_REQUISITION_ERROR_111";
		final String EDIT_WINE_REQUISITION_NO_REQ_FOUND_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_REQUISITION_NO_REQ_FOUND, "No record found for this combination of id and requisitionId");
		
		final String EDIT_WINE_REQUISITION_NO_WINE_FOUND = "EDIT_WINE_REQUISITION_ERROR_112";
		final String EDIT_WINE_REQUISITION_NO_WINE_FOUND_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_REQUISITION_NO_WINE_FOUND, "No wine found");
		
		final String EDIT_WINE_REQUISITION_WINE_AND_REQ_NOT_HAVING_SAME_WINERY = "EDIT_WINE_REQUISITION_ERROR_113";
		final String EDIT_WINE_REQUISITION_WINE_AND_REQ_NOT_HAVING_SAME_WINERY_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_REQUISITION_WINE_AND_REQ_NOT_HAVING_SAME_WINERY, "Wine and requisition not having same winery");
		
		final String EDIT_WINE_REQUISITION_NO_WINE_FOUND_IN_PRODUCT = "EDIT_WINE_REQUISITION_ERROR_114";
		final String EDIT_WINE_REQUISITION_NO_WINE_FOUND_IN_PRODUCT_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_REQUISITION_NO_WINE_FOUND_IN_PRODUCT, "No wine found in product item");
		
		final String EDIT_WINE_REQUISITION_WINE_EDITED_TO_PO_FAILURE = "EDIT_WINE_REQUISITION_ERROR_115";
		final String EDIT_WINE_REQUISITION_WINE_EDITED_TO_PO_FAILURE_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_REQUISITION_WINE_EDITED_TO_PO_FAILURE, "Wine couldn't be EDITed to po");
		
		final String EDIT_WINE_REQUISITION_WINE_ALREADY_EDITED_IN_PO = "EDIT_WINE_REQUISITION_ERROR_116";
		final String EDIT_WINE_REQUISITION_WINE_ALREADY_EDITED_IN_PO_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_REQUISITION_WINE_ALREADY_EDITED_IN_PO, "wine already EDITed in PO");
		
		final String EDIT_WINE_TO_REQUISITION_INVALID_PRODUCT_ID = "EDIT_WINE_REQUISITION_ERROR_117";
		final String EDIT_WINE_TO_REQUISITION_INVALID_PRODUCT_ID_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_TO_REQUISITION_INVALID_PRODUCT_ID, "Invalid Product Id");
		
		final String EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE = "EDIT_WINE_REQUISITION_ERROR_118";
		final String EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT =  ErrorPropertyHolderUtil.getProperty(EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE, "Wine and Requistion is not compatible");
		
		
		
		final String DISTRIBUTION_CENTRE_UPDATE_SUCCESS = "DISTRIBUTION_CENTRE_UPDATE_SUCCESS";
		final String DISTRIBUTION_CENTRE_UPDATE_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(DISTRIBUTION_CENTRE_UPDATE_SUCCESS, "Distribution Centre updated successfully");
		
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_118 = "UPDATE_DISTRIBUTION_CENTRE_ERROR_118";
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_118_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_DISTRIBUTION_CENTRE_ERROR_118, "State does not exists.");
		
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_119 = "UPDATE_DISTRIBUTION_CENTRE_ERROR_119";
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_119_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_DISTRIBUTION_CENTRE_ERROR_119, "City does not exists.");
		
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_120 = "UPDATE_DISTRIBUTION_CENTRE_ERROR_120";
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_120_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_DISTRIBUTION_CENTRE_ERROR_120, "No record exists for the id.");
		
		final String VIEW_DISTRIBUTION_CENTRE_ERROR_123 = "VIEW_DISTRIBUTION_CENTRE_ERROR_123";
		final String VIEW_DISTRIBUTION_CENTRE_ERROR_123_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_DISTRIBUTION_CENTRE_ERROR_123, "No record exists for the id.");
			
		final String ADD_DISTRIBUTION_CENTRE_ERROR_124 = "ADD_DISTRIBUTION_CENTRE_ERROR_124";
		final String ADD_DISTRIBUTION_CENTRE_ERROR_124_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_DISTRIBUTION_CENTRE_ERROR_124, "Distribution Centre already exists.");
		
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_125 = "UPDATE_DISTRIBUTION_CENTRE_ERROR_125";
		final String UPDATE_DISTRIBUTION_CENTRE_ERROR_125_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_DISTRIBUTION_CENTRE_ERROR_125, "Distribution Centre location already exists.");
	
		final String ERROR_LOGIN_USER_DISABLE = "USER138";
		final String ERROR_LOGIN_USER_DISABLE_TXT = ErrorPropertyHolderUtil.getProperty(ERROR_LOGIN_USER_DISABLE, "Logged in user can not be disabled");
		final String ERROR_LOGIN_USER_DELETED ="USER139";
		final String ERROR_LOGIN_USER_DELETED_TXT = ErrorPropertyHolderUtil.getProperty(ERROR_LOGIN_USER_DELETED, "Logged in user can not be deleted");
		final String ERROR_LOGIN_USER_RESET_PASSWORD = "USER140";
		final String ERROR_LOGIN_USER_RESET_PASSWORD_TXT = ErrorPropertyHolderUtil.getProperty(ERROR_LOGIN_USER_RESET_PASSWORD, "Password can not be reset for logged in user");
		final String ERROR_LOGIN_MERGE_USER = "USER141";
		final String ERROR_LOGIN_MERGE_USER_TXT = ErrorPropertyHolderUtil.getProperty(ERROR_LOGIN_MERGE_USER, "Logged in user can not be merged with another user");
		
		final String LIST_WAREHOUSE_ERROR_105 = "LIST_WAREHOUSE_ERROR_105";
		final String LIST_WAREHOUSE_ERROR_105_TXT = ErrorPropertyHolderUtil.getProperty(LIST_WAREHOUSE_ERROR_105, "exclusions value should be numeric.");
		
		final String DELETE_DISTRIBUTION_CENTRE_ERROR_131 = "DELETE_DISTRIBUTION_CENTRE_ERROR_131";
		final String DELETE_DISTRIBUTION_CENTRE_ERROR_131_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_DISTRIBUTION_CENTRE_ERROR_131, "ids list value can not be null.");
		
		final String LIST_WINE_REQUISITION_ERROR_103 = "LIST_WINE_REQUISITION_ERROR_103";
		final String LIST_WINE_REQUISITION_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(LIST_WINE_REQUISITION_ERROR_103, "exclusions value should be numeric.");
		
		final String NO_REQ_FOUND_WINE_REQ = "LIST_WINE_REQUISITION_ERROR_104";
		final String NO_REQ_FOUND_WINE_REQ_TEXT =  ErrorPropertyHolderUtil.getProperty(NO_REQ_FOUND_WINE_REQ, "No requisition found");
		
		
		final String VIEW_REQUISITION_NOT_FOUND = "VIEW_REQUISITION_ERROR_103";
		final String VIEW_REQUISITION_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_REQUISITION_NOT_FOUND, "Requisition not found for this Id");
		
		final String ADD_SAMPLER_ERROR_107 = "ADD_SAMPLER_ERROR_107";
		final String ADD_SAMPLER_ERROR_107_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_ERROR_107, "Invalid Wine Id.");
		
		final String ADD_SAMPLER_ERROR_109 = "ADD_SAMPLER_ERROR_109";
		final String ADD_SAMPLER_ERROR_109_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_ERROR_109, "Sampler Name already exists.");
		
		final String ADD_SAMPLER_ERROR_110 = "ADD_SAMPLER_ERROR_110";
		final String ADD_SAMPLER_ERROR_110_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_ERROR_110, "products list elements cannot be null.");
		
		final String ADD_SAMPLER_ERROR_111 = "ADD_SAMPLER_ERROR_111";
		final String ADD_SAMPLER_ERROR_111_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_ERROR_111, "product is not of product type.");
		
		final String ADD_SAMPLER_ERROR_112 = "ADD_SAMPLER_ERROR_112";
		final String ADD_SAMPLER_ERROR_112_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_ERROR_112, "Duplicate products list elements are not allowed.productType and productId combination should be unique.");
		
		final String ADD_SAMPLER_SUCCESS = "ADD_SAMPLER_SUCCESS";
		final String ADD_SAMPLER_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_SUCCESS, "Sampler added successfully.");
		
		final String VIEW_SAMPLER_ERROR_103 = "VIEW_SAMPLER_ERROR_103";
		final String VIEW_SAMPLER_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_SAMPLER_ERROR_103, "No sampler record exists for this id.");
		
		final String EDIT_SAMPLER_WINE_ERROR_101 = "EDIT_SAMPLER_WINE_ERROR_101";
		final String EDIT_SAMPLER_WINE_ERROR_101_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_101, "Mandatory field productId is required.");
		
		final String EDIT_SAMPLER_WINE_ERROR_102 = "EDIT_SAMPLER_WINE_ERROR_102";
		final String EDIT_SAMPLER_WINE_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_102, "Invalid value of productId passed.");
		
		final String EDIT_SAMPLER_WINE_ERROR_103 = "EDIT_SAMPLER_WINE_ERROR_103";
		final String EDIT_SAMPLER_WINE_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_103, "Mandatory field quantity is required.");
		
		final String EDIT_SAMPLER_WINE_ERROR_104 = "EDIT_SAMPLER_WINE_ERROR_104";
		final String EDIT_SAMPLER_WINE_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_104, "Invalid value of quantity passed.");
		
		final String EDIT_SAMPLER_WINE_ERROR_105 = "EDIT_SAMPLER_WINE_ERROR_105";
		final String EDIT_SAMPLER_WINE_ERROR_105_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_105, "Mandatory field srpPrice is required.");
		
		final String EDIT_SAMPLER_WINE_ERROR_106 = "EDIT_SAMPLER_WINE_ERROR_106";
		final String EDIT_SAMPLER_WINE_ERROR_106_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_106, "Invalid value of srpPrice passed.");
	
		final String EDIT_SAMPLER_WINE_ERROR_107 = "EDIT_SAMPLER_WINE_ERROR_107";
		final String EDIT_SAMPLER_WINE_ERROR_107_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_107, "No product details found for the productId passed.");
		
		final String EDIT_SAMPLER_WINE_ERROR_108 = "EDIT_SAMPLER_WINE_ERROR_108";
		final String EDIT_SAMPLER_WINE_ERROR_108_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_108, "Mandatory field samplerId is required.");
		
		final String EDIT_SAMPLER_WINE_ERROR_109 = "EDIT_SAMPLER_WINE_ERROR_109";
		final String EDIT_SAMPLER_WINE_ERROR_109_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_109, "Invalid value of samplerId passed.");
		
		final String EDIT_SAMPLER_WINE_ERROR_110 = "EDIT_SAMPLER_WINE_ERROR_110";
		final String EDIT_SAMPLER_WINE_ERROR_110_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_110, "No sampler details found for the productId passed.");
		
		final String EDIT_SAMPLER_WINE_ERROR_111 = "EDIT_SAMPLER_WINE_ERROR_111";
		final String EDIT_SAMPLER_WINE_ERROR_111_TEXT = ErrorPropertyHolderUtil.getProperty(EDIT_SAMPLER_WINE_ERROR_111, "No details found for the productId and samplerId passed.");
		
		final String UPDATE_SAMPLER_SUCCESS = "UPDATE_SAMPLER_SUCCESS";
		final String UPDATE_SAMPLER_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_SAMPLER_SUCCESS, "Sampler updated successfully.");
		
		final String UPDATE_SAMPLER_ERROR_104 = "UPDATE_SAMPLER_ERROR_104";
		final String UPDATE_SAMPLER_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_SAMPLER_ERROR_104, "No sampler record exists for this id.");
		
		final String UPDATE_SAMPLER_ERROR_105 = "UPDATE_SAMPLER_ERROR_105";
		final String UPDATE_SAMPLER_ERROR_105_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_SAMPLER_ERROR_105, "sampler name can only be editable in disable state only.");
		
		final String UPDATE_SAMPLER_ERROR_106 = "UPDATE_SAMPLER_ERROR_106";
		final String UPDATE_SAMPLER_ERROR_106_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_SAMPLER_ERROR_106, "Sampler name already exists.");
	
		final String VIEW_SAMPLER_LOGISTICS_NO_SAMPLER_FOUND = "VIEW_SAMPLER_LOGISTICS_DETAIL_ERROR_103";
		final String VIEW_SAMPLER_LOGISTICS_NO_SAMPLER_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_SAMPLER_LOGISTICS_NO_SAMPLER_FOUND, "No sampler record exists for this id.");
		final String DELETE_SAMPLER_WINE_ERROR_101 = "DELETE_SAMPLER_WINE_ERROR_101";
		final String DELETE_SAMPLER_WINE_ERROR_101_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_SAMPLER_WINE_ERROR_101, "samplerId cannot be null.");
		
		final String DELETE_SAMPLER_WINE_ERROR_102 = "DELETE_SAMPLER_WINE_ERROR_102";
		final String DELETE_SAMPLER_WINE_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_SAMPLER_WINE_ERROR_102, "Invalid samplerId passed.");
		
		final String DELETE_SAMPLER_WINE_ERROR_103 = "DELETE_SAMPLER_WINE_ERROR_103";
		final String DELETE_SAMPLER_WINE_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_SAMPLER_WINE_ERROR_103, "productId cannot be null.");
		
		final String DELETE_SAMPLER_WINE_ERROR_104 = "DELETE_SAMPLER_WINE_ERROR_104";
		final String DELETE_SAMPLER_WINE_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_SAMPLER_WINE_ERROR_104, "Invalid isForceDelete passed.");
		
		final String DELETE_SAMPLER_WINE_ERROR_105 = "DELETE_SAMPLER_WINE_ERROR_105";
		final String DELETE_SAMPLER_WINE_ERROR_105_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_SAMPLER_WINE_ERROR_105, "No sampler record exists for the samplerId passed.");
		
		final String DELETE_SAMPLER_WINE_ERROR_106 = "DELETE_SAMPLER_WINE_ERROR_106";
		final String DELETE_SAMPLER_WINE_ERROR_106_TEXT = ErrorPropertyHolderUtil.getProperty(DELETE_SAMPLER_WINE_ERROR_106, "Can not delete the product(s). Sampler should have atleast two products.");		
		
		final String ADD_SAMPLER_PRODUCT_SUCCESS = "ADD_SAMPLER_PRODUCT_SUCCESS";
		final String ADD_SAMPLER_PRODUCT_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER_PRODUCT_SUCCESS, "Sampler Product added successfully.");
		
		final String ADD_SAMPLER__PRODUCT_ERROR_108 = "ADD_SAMPLER__PRODUCT_ERROR_108";
		final String ADD_SAMPLER__PRODUCT_ERROR_108_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER__PRODUCT_ERROR_108, "No record exists for this sampler id.");
		
		final String ADD_SAMPLER__PRODUCT_ERROR_109 = "ADD_SAMPLER__PRODUCT_ERROR_109";
		final String ADD_SAMPLER__PRODUCT_ERROR_109_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER__PRODUCT_ERROR_109, "Same product already exists.");
		
		final String ADD_SAMPLER__PRODUCT_ERROR_110 = "ADD_SAMPLER__PRODUCT_ERROR_110";
		final String ADD_SAMPLER__PRODUCT_ERROR_110_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER__PRODUCT_ERROR_108, "Invalid product id.");
		
		final String ADD_SAMPLER__PRODUCT_ERROR_111 = "ADD_SAMPLER__PRODUCT_ERROR_111";
		final String ADD_SAMPLER__PRODUCT_ERROR_111_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_SAMPLER__PRODUCT_ERROR_111, "Invalid value of qtyofProducts.");
		
		final String VIEW_SAMPLER_COMPLIENCE_NO_SAMPLER_FOUND = "VIEW_SAMPLER_COMPLIENCE_DETAIL_ERROR_103";
		final String VIEW_SAMPLER_COMPLIENCE_NO_SAMPLER_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_SAMPLER_COMPLIENCE_NO_SAMPLER_FOUND, "No sampler record exists for this id.");
		
		final String VIEW_SAMPLER_COMPLIENCE_DETAIL_INTERNAL_SERVICE_ERROR = "VIEW_SAMPLER_COMPLIENCE_DETAIL_ERROR_104";
		final String VIEW_SAMPLER_COMPLIENCE_DETAIL_INTERNAL_SERVICE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_SAMPLER_COMPLIENCE_DETAIL_INTERNAL_SERVICE_ERROR, "Internal Service Error Occured while processing request");
		
		final String VIEW_SAMPLER_LOGISTICS_DETAIL_INTERNAL_SERVICE_ERROR = "VIEW_SAMPLER_LOGISTICS_DETAIL_ERROR_104";
		final String VIEW_SAMPLER_LOGISTICS_DETAIL_INTERNAL_SERVICE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(VIEW_SAMPLER_LOGISTICS_DETAIL_INTERNAL_SERVICE_ERROR, "Internal Service Error Occured while processing request");	
		
		final String LIST_SAMPLER_PRODUCT_ERROR_101 = "LIST_SAMPLER_PRODUCT_ERROR_101";
		final String LIST_SAMPLER_PRODUCT_ERROR_101_TEXT = ErrorPropertyHolderUtil.getProperty(LIST_SAMPLER_PRODUCT_ERROR_101, "No sampler record exists for the samplerId passed.");
		
		final String LIST_SAMPLER_PRODUCT_ERROR_102 = "LIST_SAMPLER_PRODUCT_ERROR_102";
		final String LIST_SAMPLER_PRODUCT_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(LIST_SAMPLER_PRODUCT_ERROR_102, "Invalid sortBy passed.");
		
		final String LIST_SAMPLER_PRODUCT_ERROR_103 = "LIST_SAMPLER_PRODUCT_ERROR_103";
		final String LIST_SAMPLER_PRODUCT_ERROR_103_TEXT = ErrorPropertyHolderUtil.getProperty(LIST_SAMPLER_PRODUCT_ERROR_103, "Mandatory field samplerId is required.");
		
		final String LIST_SAMPLER_PRODUCT_ERROR_104 = "LIST_SAMPLER_PRODUCT_ERROR_104";
		final String LIST_SAMPLER_PRODUCT_ERROR_104_TEXT = ErrorPropertyHolderUtil.getProperty(LIST_SAMPLER_PRODUCT_ERROR_104, "Invalid samplerId passed.");
		
		final String LIST_SAMPLER_PRODUCT_ERROR_105 = "LIST_SAMPLER_PRODUCT_ERROR_105";
		final String LIST_SAMPLER_PRODUCT_ERROR_105_TEXT = ErrorPropertyHolderUtil.getProperty(LIST_SAMPLER_PRODUCT_ERROR_105, "Unknown error occurred while fetching the list of sampler products.");
		
		final String WAREHOUSE_ERROR_132 = "WAREHOUSE132";
		final String WAREHOUSE_ERROR_132_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_132, "Warehouse name should not be empty");
		
		final String WAREHOUSE_ERROR_133 = "WAREHOUSE133";
		final String WAREHOUSE_ERROR_133_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_133, "In Warehouse address, address line1 can not be empty");
		
		final String WAREHOUSE_ERROR_134 = "WAREHOUSE134";
		final String WAREHOUSE_ERROR_134_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_134, "In Warehouse address, zipcode can not be empty");
		
		final String WAREHOUSE_ERROR_135 = "WAREHOUSE135";
		final String WAREHOUSE_ERROR_135_TEXT = ErrorPropertyHolderUtil.getProperty(WAREHOUSE_ERROR_135, "Invalid isWineShippingAddress is passed.");
		
		final String WINERY_ERROR_136 = "WINERY136";
		final String WINERY_ERROR_136_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ERROR_136, "Winery status can not be empty");
		
		final String WINERY_ERROR_137 = "WINERY137";
		final String WINERY_ERROR_137_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ERROR_137, "Invalid Winery Status has been passed");
		
		final String WINERY_ERROR_138 = "WINERY138";
		final String WINERY_ERROR_138_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ERROR_138, "Winery status can not be empty");
		
		final String WINERY_ERROR_139 = "WINERY139";
		final String WINERY_ERROR_139_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_ERROR_139, "Invalid Winery Status has been passed");
	
		final String IMPORTER_131_ERROR = "IMPORTER131";
		final String IMPORTER_131_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(IMPORTER_131_ERROR, "Warehouse not exist");

		final String USER_PASSWORD_ERROR_101 = "USER_PASSWORD_ERROR_101";
		final String USER_PASSWORD_ERROR_101_TEXT = ErrorPropertyHolderUtil.getProperty(USER_PASSWORD_ERROR_101, "User can not be registered.");
		
		final String USER_PASSWORD_ERROR_102 = "USER_PASSWORD_ERROR_102";
		final String USER_PASSWORD_ERROR_102_TEXT = ErrorPropertyHolderUtil.getProperty(USER_PASSWORD_ERROR_102, "Password has already been created");

		final String LOGISTIC_INVALID_WAREHOUSE = "LOGISTIC_ERROR_130";
		final String LOGISTIC_INVALID_WAREHOUSE_TEXT = ErrorPropertyHolderUtil.getProperty(LOGISTIC_INVALID_WAREHOUSE, "Warehouse not exist");
		
		final String CONTACT_120_ERROR="CONTACT121";
		final String CONTACT_120_ERROR_TEXT=ErrorPropertyHolderUtil.getProperty(CONTACT_120_ERROR, "Only Owner Type Can be set to default contact");
		
		final String WINERY_DATE_ERROR = "WINERY_DATE_ERROR";
		final String WINERY_DATE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(WINERY_DATE_ERROR, "ceraCertStartDate cannot be greater than ceraCertEndDate.");
		
		final String EMAIL_TEMPLATE_CLONE_ID="EMAIL_TEMPLATE_ERROR_105";
		final String EMAIL_TEMPLATE_CLONE_ID_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_TEMPLATE_CLONE_ID, "Email Template Id is not valid");

		final String ADD_REQUISITION_INVALID_DESTINATION_DC = "REQUISITION_ERROR_117";
		final String ADD_REQUISITION_INVALID_DESTINATION_DC_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_INVALID_DESTINATION_DC, "Invalid Destination DC");
		
		final String ADD_REQUISITION_INVALID_SOURCE_DC = "REQUISITION_ERROR_118";
		final String ADD_REQUISITION_INVALID_SOURCE_DC_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_INVALID_SOURCE_DC, "Invalid Source DC");
		
		final String ADD_REQUISITION_INVALID_INBOUND_TRANSPORT = "REQUISITION_ERROR_119";
		final String ADD_REQUISITION_INVALID_INBOUND_TRANSPORT_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_INVALID_INBOUND_TRANSPORT, "Invalid Inbound Transport");
		
		final String ADD_REQUISITION_DISTRIBUTION_NOT_FOUND = "REQUISITION_ERROR_120";
		final String ADD_REQUISITION_DISTRIBUTION_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_DISTRIBUTION_NOT_FOUND, "Distribution centre not found");
		
		final String ADD_REQUISITION_WAREHOUSE_NOT_FOUND = "REQUISITION_ERROR_121";
		final String ADD_REQUISITION_WAREHOUSE_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_WAREHOUSE_NOT_FOUND, "Warehouse not found");
		
		final String ADD_REQUISITION_WINERY_NOT_FOUND = "REQUISITION_ERROR_122";
		final String ADD_REQUISITION_WINERY_NOT_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_WINERY_NOT_FOUND, "Winery not found");		
		
		final String ADD_REQUISITION_INTERNAL_SERVER_ERROR = "REQUISITION_ERROR_123";
		final String ADD_REQUISITION_INTERNAL_SERVER_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_INTERNAL_SERVER_ERROR, "Internal Service Error Occured while processing request");
		
		final String ADD_REQUISITION_SUCCESSFUL = "REQUISITION_ERROR_124";
		final String ADD_REQUISITION_SUCCESSFUL_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_SUCCESSFUL, "Requisition added successfully.");
		
		final String UPDATE_REQUISITION_SUCCESSFUL = "UPDATE_REQUISITION_SUCCESSFUL";
		final String UPDATE_REQUISITION_SUCCESSFUL_TEXT = ErrorPropertyHolderUtil.getProperty(UPDATE_REQUISITION_SUCCESSFUL, "Requisition updated successfully.");
		
		final String ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE = "REQUISITION_ERROR_125";
		final String ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE, "sourceAddressType is not compatible with requisitionType");
		
		final String ADD_REQUISITION_SOURCE_DEST_SAME = "REQUISITION_ERROR_126";
		final String ADD_REQUISITION_SOURCE_DEST_SAME_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_SOURCE_DEST_SAME, "Source DC and Destination DC can not be same");
		
		final String ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT = "REQUISITION_ERROR_111";
		final String ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT_TEXT = ErrorPropertyHolderUtil.getProperty(ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT, "Mandatory field inboundTransportId is required.");
		
		final String NO_REQUISITION_FOUND = "REQUISITION_ERROR_130";
		final String NO_REQUISITION_FOUND_TEXT = ErrorPropertyHolderUtil.getProperty(NO_REQUISITION_FOUND, "No requisition found for the requisitionId passed.");
		
		final String INVALID_STATUS_CHANGE = "REQUISITION_ERROR_131";
		final String INVALID_STATUS_CHANGE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_STATUS_CHANGE, "The status change made is invalid.");

		final String INVALID_EXPECTED_PICKUP_ARRIVAL_DATE = "REQUISITION_ERROR_132";
		final String INVALID_EXPECTED_PICKUP_ARRIVAL_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_EXPECTED_PICKUP_ARRIVAL_DATE, "expectedPickupDate cannot be greater than expectedArrivalDate.");
		
		final String INVALID_EXPECTED_ARRIVAL_SHIPPING_DATE = "REQUISITION_ERROR_133";
		final String INVALID_EXPECTED_ARRIVAL_SHIPPING_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_EXPECTED_ARRIVAL_SHIPPING_DATE, "expectedArrivalDate cannot be greater than expectedShippingDate.");
		
		final String INVALID_EXPECTED_PICKUP_SHIPPING_DATE = "REQUISITION_ERROR_134";
		final String INVALID_EXPECTED_PICKUP_SHIPPING_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_EXPECTED_PICKUP_SHIPPING_DATE, "expectedPickupDate cannot be greater than expectedShippingDate.");
		
		final String INVALID_ACTUAL_PICKUP_ARRIVAL_DATE = "REQUISITION_ERROR_135";
		final String INVALID_ACTUAL_PICKUP_ARRIVAL_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_ACTUAL_PICKUP_ARRIVAL_DATE, "actualPickupDate cannot be greater than actualArrivalDate.");
		
		final String INVALID_ACTUAL_ARRIVAL_SHIPPING_DATE = "REQUISITION_ERROR_136";
		final String INVALID_ACTUAL_ARRIVAL_SHIPPING_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_ACTUAL_ARRIVAL_SHIPPING_DATE, "actualArrivalDate cannot be greater than actualShippingDate.");
		
		final String INVALID_ACTUAL_PICKUP_SHIPPING_DATE = "REQUISITION_ERROR_137";
		final String INVALID_ACTUAL_PICKUP_SHIPPING_DATE_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_ACTUAL_PICKUP_SHIPPING_DATE, "actualPickupDate cannot be greater than actualShippingDate.");
		
		final String INVALID_REQUISITION_ID_ERROR = "REQUISITION_ERROR_138";
		final String INVALID_REQUISITION_ID_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(INVALID_REQUISITION_ID_ERROR, "Invalid value of requisitionId is passed.");
		
		final String UNKNOWN_REQUISITION_DELETE_ERROR = "REQUISITION_ERROR_141";
		final String UNKNOWN_REQUISITION_DELETE_ERROR_TEXT = ErrorPropertyHolderUtil.getProperty(UNKNOWN_REQUISITION_DELETE_ERROR, "Unknown error occurred while deleting the requisition.");
		
		final String EMAIL_SUCCESS = "SEND_EMAIL_TO_WINERY_ERROR_109";
		final String EMAIL_SUCCESS_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_SUCCESS, "Success");
		
		final String EMAIL_FAIL = "SEND_EMAIL_TO_WINERY_ERROR_110";
		final String EMAIL_FAIL_TEXT = ErrorPropertyHolderUtil.getProperty(EMAIL_FAIL, "Failed");		
		
	}
}
