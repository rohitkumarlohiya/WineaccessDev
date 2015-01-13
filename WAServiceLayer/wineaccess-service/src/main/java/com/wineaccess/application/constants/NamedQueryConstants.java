package com.wineaccess.application.constants;

public interface NamedQueryConstants {
	String GET_BY_USERNAME_PASSWORD = "getByUserNamePassword";
	String GETALL = "getAll";
	String GET_BY_USER_NAME = "getByUserName";
	String GET_USER_MODEL_BY_ID = "UserModel.getByUserId";
	String GET_USER_WITH_SSO = "getUserWithSSO";
	String GET_BY_USER_ID = "getByUserId"; 
	String FIND_ADDR_BY_USERNAME = "findAddressByUserId";	
	String FIND_ADDR_BY_USERID_ADDR_ID = "findAddressByUserIdAddrId";	
	String FIND_ADDR_PREF_BY_USERNAME = "findAddressPrefByUserId";
	String FIND_CC_BY_USERNAME = "findCCByUserId";	
	String FIND_CC_BY_USER_ID_CC_ID = "findCCByUserIdCCId";
	String FIND_ALL_USERS_BY_IDS ="findAllUsersByIds";
	String MODIFY_STATUS_BULK = "modifyStatusBulk";
	String BULK_DELETE_USERS = "bulkdeleteUsers";
	String GET_BY_USER_ID_DELETED= "getByUserIdDeleted";
	String FIND_USER_NEWSLETTER_RESPONSYS= "getNewsLetterResponsys";
	String UPDATE_USER_IDS_ADDRESS = "updateUserIdsInAddr" ;
	String UPDATE_USER_IDS_CC = "updateUserIdsInCC" ;
	String FIND_ADDR_IDS_BY_USER_ID = "findAddressIdsByUserId";
	String FIND_ADDR_PREF_IDS_BY_ADDR_ID = "findAddressPrefIdsByAddrId";
	String FIND_CC_IDS_BY_USER_ID = "findCCIdsByUserId";
	String GET_BY_USER_ID_COMMENT_ID = "getByUserIdCommentId";
	String GET_COMMENTS_BY_USER_ID = "getCommentsByUserId";
	String GET_USER_BY_ACTIVATION_CODE="getUserByActivationCode";
	String FIND_BY_USERID_BRAINTREEID="findByUseridBraintreeid";
	String FIND_BY_USERID_DEFAULT_CC="findByUseridDeafultCreditCard";
	String GET_USER_BY_ID="UserModel.getUserById";
	String FIND_CC_LIST_BY_USERID = "findCCListByUserId";	
		String FIND_DEFAULT_ADDRESS="User.Address.getDefaultAddress";
		String FIND_CC_LIST_BY_USERID_ADDRESSID="findCCIdsByUserIdAddressId";
}


