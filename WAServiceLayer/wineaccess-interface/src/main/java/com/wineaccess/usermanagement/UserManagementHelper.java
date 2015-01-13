package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.EnumTypes;
import com.wineaccess.constants.EnumTypes.EmailTemplateType;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.user.AddressPreference;
import com.wineaccess.data.model.user.UserAddress;
import com.wineaccess.data.model.user.UserCreditCard;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.service.user.preference.UserPreferenceVO;
import com.wineaccess.user.activity.log.UserServiceModel;
import com.wineaccess.util.notification.EmailNotifier;



/**
 * @author abhishek.sharma1
 *
 */
public class UserManagementHelper  {	

    private static Log logger = LogFactory.getLog(UserManagementHelper.class);

    /**
     * @param usersList
     * @param fieldName
     * @param status
     * @param isForceDelete
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static UsersDescriptionListVO generateModifyStatusResponse(List<? extends Serializable> usersList, String fieldName, boolean status, Boolean isForceDelete, Boolean loggedOutUsers,String url) throws IllegalAccessException,
    InvocationTargetException {
	//DeleteUserVO deleteUserVO = new DeleteUserVO();
	GenericDAO<UserModel> genericDAO = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");

	List<String> canBeLoggedOutUserList = new ArrayList<String>();
	BulkDeleteModel<UserModel> bulkDeleteModel = null;

	try {
	    bulkDeleteModel = genericDAO.performBulkDelete(usersList, UserModel.class, "UserModel",fieldName, status,"isDeleted",isForceDelete);
	    genericDAO.refresh(usersList, UserModel.class);
	    //ApplicationUtils.cleanLucenceIndex();
	} 
	catch (Exception e) {
	    if (e instanceof PersistenceException) {
		PersistenceException persistenceException = (PersistenceException) e;
		bulkDeleteModel = (BulkDeleteModel<UserModel>) (persistenceException
			.getData());
	    }
	} 

	UsersDescriptionListVO usersDescriptionListVO = new UsersDescriptionListVO();


	List<UserModel> canBeDeletedUserList = bulkDeleteModel.getDeletedList();
	List<UserServiceModel> userServiceModelList = new ArrayList<UserServiceModel>();

	for(UserModel userModelObject: canBeDeletedUserList){
	    canBeLoggedOutUserList.add(userModelObject.getEmail());
	    //Send the email if enabling the user
	    if("isEnabled".equals(fieldName) && BooleanUtils.isTrue(status)){			
		EmailNotifier.SendEmail(EmailTemplateType.ENABLE_USER.getEmailTemplateType(), userModelObject.getEmail(), userModelObject);
	    }
	    //Send the reset password mail to the user
	    if("registered".equals(fieldName)){


		if((BooleanUtils.isTrue(isForceDelete)) || 
			(canBeDeletedUserList.size()== usersList.size())){
		    userModelObject.setResetCode(ApplicationUtils.genActivationCode(userModelObject.getEmail()));
		    userModelObject.setRegistered(false);
		    UserRepository.update(userModelObject);
		    String activationURL = ApplicationUtils.generateURL(userModelObject, "resetPassword",url);
		    ApplicationUtils.sendMailWithURL(activationURL, userModelObject.getEmail(),"resetPassword" , userModelObject);
		}


	    }
	    UserServiceModel userServiceModel = new UserServiceModel();
	    BeanUtils.copyProperties(userServiceModel, userModelObject);
	    userServiceModelList.add(userServiceModel);
	}
	usersDescriptionListVO.setSuccessList(userServiceModelList);

	userServiceModelList = new ArrayList<UserServiceModel>();
	List<UserModel> canNotBeDeletedUserList = bulkDeleteModel.getNotDeletedList();
	for(UserModel userModelObject: canNotBeDeletedUserList){
	    UserServiceModel userServiceModel = new UserServiceModel();
	    BeanUtils.copyProperties(userServiceModel, userModelObject);
	    userServiceModelList.add(userServiceModel);
	}
	usersDescriptionListVO.setFailureList(userServiceModelList);

	usersDescriptionListVO.setNonExisting((List<Long>) bulkDeleteModel.getNotExistsList());

	if(loggedOutUsers && canBeLoggedOutUserList!=null){
	    if((BooleanUtils.isTrue(isForceDelete)) || 
		    (canBeLoggedOutUserList.size()== usersList.size()))
	    {
		for(String logOutUserList:canBeLoggedOutUserList)
		{
		    TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		    tokenUtils.logoutUser(logOutUserList);
		}
		/*List<Long> loggedInUsersList = new ArrayList<Long>(); 
				List<Object[]> loggedInUserList =  TokenModelRepository.loggedInUserList(usersList);
				if(!loggedInUserList.isEmpty()){
					for(Object[] obj:loggedInUserList){
						Long userId = (Long)obj[0];
						Long count = (Long)obj[1];
						if(count>0)
							loggedInUsersList.add(userId);
					}
				}*/
	    }
	}
	return usersDescriptionListVO;
    }



    public static UsersDescriptionListVO generateModifyStatusMultipleCondition(List<? extends Serializable> usersList, String fieldName, boolean status, Boolean isForceDelete, Boolean loggedOutUsers,String url, Map<String,Boolean> dependencyFieldsNameMap) throws IllegalAccessException,
    InvocationTargetException {
	//DeleteUserVO deleteUserVO = new DeleteUserVO();
	GenericDAO<UserModel> genericDAO = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");

	List<String> canBeLoggedOutUserList = new ArrayList<String>();
	BulkDeleteModel<UserModel> bulkDeleteModel = null;

	try {
	    bulkDeleteModel = genericDAO.performBulkOpMultipleCondition(usersList, UserModel.class, "UserModel",fieldName, status,dependencyFieldsNameMap,isForceDelete);
	    genericDAO.refresh(usersList, UserModel.class);
	    //ApplicationUtils.cleanLucenceIndex();
	} 
	catch (Exception e) {
	    if (e instanceof PersistenceException) {
		PersistenceException persistenceException = (PersistenceException) e;
		bulkDeleteModel = (BulkDeleteModel<UserModel>) (persistenceException
			.getData());
	    }
	} 

	UsersDescriptionListVO usersDescriptionListVO = new UsersDescriptionListVO();


	List<UserModel> canBeDeletedUserList = bulkDeleteModel.getDeletedList();
	List<UserServiceModel> userServiceModelList = new ArrayList<UserServiceModel>();

	for(UserModel userModelObject: canBeDeletedUserList){
	    canBeLoggedOutUserList.add(userModelObject.getEmail());
	    //Send the email if enabling the user
	    if("isEnabled".equals(fieldName) && BooleanUtils.isTrue(status)){			
		EmailNotifier.SendEmail(EmailTemplateType.ENABLE_USER.getEmailTemplateType(), userModelObject.getEmail(), userModelObject);
	    }
	    //Send the reset password mail to the user
	    if("registered".equals(fieldName)){


		if((BooleanUtils.isTrue(isForceDelete)) || 
			(canBeDeletedUserList.size()== usersList.size())){
		    userModelObject.setResetCode(ApplicationUtils.genActivationCode(userModelObject.getEmail()));
		    userModelObject.setRegistered(false);
		    UserRepository.update(userModelObject);
		    String activationURL = ApplicationUtils.generateURL(userModelObject, "resetPassword",url);
		    ApplicationUtils.sendMailWithURL(activationURL, userModelObject.getEmail(),"resetPassword" , userModelObject);
		}


	    }
	    UserServiceModel userServiceModel = new UserServiceModel();
	    BeanUtils.copyProperties(userServiceModel, userModelObject);
	    userServiceModelList.add(userServiceModel);
	}
	usersDescriptionListVO.setSuccessList(userServiceModelList);

	userServiceModelList = new ArrayList<UserServiceModel>();
	List<UserModel> canNotBeDeletedUserList = bulkDeleteModel.getNotDeletedList();
	for(UserModel userModelObject: canNotBeDeletedUserList){
	    UserServiceModel userServiceModel = new UserServiceModel();
	    BeanUtils.copyProperties(userServiceModel, userModelObject);
	    userServiceModelList.add(userServiceModel);
	}
	usersDescriptionListVO.setFailureList(userServiceModelList);

	usersDescriptionListVO.setNonExisting((List<Long>) bulkDeleteModel.getNotExistsList());

	if(loggedOutUsers && canBeLoggedOutUserList!=null){
	    if((BooleanUtils.isTrue(isForceDelete)) || 
		    (canBeLoggedOutUserList.size()== usersList.size()))
	    {
		for(String logOutUserList:canBeLoggedOutUserList)
		{
		    TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		    tokenUtils.logoutUser(logOutUserList);
		}
		/*List<Long> loggedInUsersList = new ArrayList<Long>(); 
				List<Object[]> loggedInUserList =  TokenModelRepository.loggedInUserList(usersList);
				if(!loggedInUserList.isEmpty()){
					for(Object[] obj:loggedInUserList){
						Long userId = (Long)obj[0];
						Long count = (Long)obj[1];
						if(count>0)
							loggedInUsersList.add(userId);
					}
				}*/
	    }
	}
	return usersDescriptionListVO;
    }

    /**
     * @param inputParam
     * @return
     */
    public static UserModel populateUserModelFromPO(Map<String, Object> inputParam) {
	UserModel userModel = new UserModel();

	UserManagementPO userManagementPO = (UserManagementPO) inputParam.get("UserManagementPO");
	//JAXB.marshal(userManagementPO, System.out);
	if(!StringUtils.isEmpty(userManagementPO.getUserId()))
	    userModel.setId(Long.valueOf(userManagementPO.getUserId()));

	userModel.setFirstName((String) userManagementPO.getFirstName());
	userModel.setLastName((String) userManagementPO.getLastName());
	userModel.setEmail((String) userManagementPO.getEmail());
	userModel.setSalutation((String)userManagementPO.getSalutation());
	userModel.setGender((String)userManagementPO.getGender());
	userModel.setZipCode((String)userManagementPO.getZipCode());
	userModel.setCountryId((Long)userManagementPO.getCountryId());
	userModel.setStateId((Long)userManagementPO.getStateId());
	userModel.setEnabled(true);
	userModel.setReceivedNewLetter((Boolean)userManagementPO.getIsReceivedNewsletter());
	userModel.setPhone((String)userManagementPO.getPhone());
	if(userManagementPO.getRegStatus() != null){
	    userModel.setRegStatus((String)(EnumTypes.RegType.fetchRegTypeFromCode(userManagementPO.getRegStatus()))); 
	}
	userModel.setRegSource(userManagementPO.getRegSource());
	userModel.setEmailType((String)(EnumTypes.EmailType.fetchEmailTypeFromCode(userManagementPO.getEmailType()))); 

	return userModel;
    }


    /**
     * @param inputParam
     * @return
     */
    public static List<UserAddress> populateAddrModelFromPO(Map<String, Object> inputParam) {
	UserManagementPO userManagementPO = (UserManagementPO) inputParam.get("UserManagementPO");
	List<UserAddressModel> userAddrList = userManagementPO.getAddress();
	List<UserAddress> userAddrModelList = new ArrayList<UserAddress>();
	if(userAddrList!=null && !userAddrList.isEmpty())
	{
	    for(UserAddressModel userAddressPO: userAddrList)
	    {
		UserAddress userAdrmodel =populateUserAddrModelFromPO(userAddressPO);
		userAdrmodel.setAddressPreference(populateAddrPrefModelFromPO(userAddressPO.getAddressPref()));
		userAddrModelList.add(userAdrmodel);
	    }

	}
	return userAddrModelList;
    }


    /**
     * @param userAddressPO
     * @return
     */
    private static UserAddress populateUserAddrModelFromPO(UserAddressModel userAddressPO, Long... userId) {
	UserAddress userAdrmodel = new UserAddress();
	if(userAddressPO != null){
	    if(!StringUtils.isEmpty(userAddressPO.getAddressId()))
		userAdrmodel.setId(Long.valueOf(userAddressPO.getAddressId()));

	    userAdrmodel.setAddress1((String) userAddressPO.getAddressLine1());
	    userAdrmodel.setAddress2((String) userAddressPO.getAddressLine2());
	    //userAdrmodel.setAddressType((String) userAddressPO.getAddressType());

	    String birthDate = userAddressPO.getBirthDate();
	    userAdrmodel.setBirthDate(ApplicationUtils.convertDateFromString(birthDate));

	    userAdrmodel.setBusinessName((String) userAddressPO.getBusinessName());
	    userAdrmodel.setFirstName((String) userAddressPO.getFirstName());
	    userAdrmodel.setLastName((String) userAddressPO.getLastName());
	    userAdrmodel.setCityId((userAddressPO.getCityId()));
	    userAdrmodel.setStateId(userAddressPO.getStateId());
	    if(StringUtils.isNotBlank(userAddressPO.getCountryId()))
	    userAdrmodel.setCountryId(Long.valueOf(userAddressPO.getCountryId()));
	    userAdrmodel.setPhone((String)userAddressPO.getPhone());
	    userAdrmodel.setZipcode((String)userAddressPO.getZipCode());
	    if(userId != null && userId.length==1){

		UserAddress defaultAddress = UserRepository.findDefaultAddress(userId[0],getAddressType(userAddressPO.getAddressType()));
		if(BooleanUtils.isTrue(Boolean.parseBoolean(userAddressPO.getIsDefault()))){
		    userAdrmodel.setDefault(true);
		}
		if(userAdrmodel.getId() != null && defaultAddress != null && defaultAddress.getId() == userAdrmodel.getId() && defaultAddress.isDefault() && !userAdrmodel.isDefault()){
		    return null;
		}

		if(defaultAddress != null){
		    defaultAddress.setDefault(false);
		    UserRepository.saveOrUpdateAddr(defaultAddress);


		}
	    }

	    /*if(userAddressPO.getIsDefault()!=null)
				userAdrmodel.setDefault((String)(EnumTypes.DefaultAddressType.fetchDefaultAddressTypeFromCode(userAddressPO.getIsDefault())));*/
	    userAdrmodel.setAddressType((String)(EnumTypes.AddressType.fetchAddressTypeFromCode(userAddressPO.getAddressType()))); 
	    return userAdrmodel;
	}
	return userAdrmodel;
    }



    private static String getAddressType(String addressType) {

	String type = "0";

	switch(Integer.parseInt(addressType)){

	case 0:
	case 2:
	    type = EnumTypes.AddressType.fetchAddressTypeFromCode("0");
	    break;
	case 1:
	    type = EnumTypes.AddressType.fetchAddressTypeFromCode(addressType);
	    break;



	}

	return type;
    }

    /**
     * @param userAddressPrefPO
     * @return
     */
    private static AddressPreference populateAddrPrefModelFromPO(UserAddressPrefModel userAddressPrefPO) {

	if(userAddressPrefPO!=null){
	    AddressPreference addrPrefModel = new AddressPreference();
	    if(!StringUtils.isEmpty(userAddressPrefPO.getAddrPrefId()))
		addrPrefModel.setId(Long.valueOf(userAddressPrefPO.getAddrPrefId()));

	    if(userAddressPrefPO.getPrefCarrierId()!=null)
		addrPrefModel.setPrefferedCarrier(MasterDataRepository.getMasterDataById(userAddressPrefPO.getPrefCarrierId()));

	    if(userAddressPrefPO.getPrefPackageTypeId()!=null)
		addrPrefModel.setPrefferedPackageType(MasterDataRepository.getMasterDataById(userAddressPrefPO.getPrefPackageTypeId()));

	    if(userAddressPrefPO.getPrefShippingMethodId()!=null)
		addrPrefModel.setPrefferedShippingMethod(MasterDataRepository.getMasterDataById(userAddressPrefPO.getPrefShippingMethodId()));

	    addrPrefModel.setDeliveryStartTime(userAddressPrefPO.getDeliveryStarting());
	    addrPrefModel.setDeliveryEndTime(userAddressPrefPO.getDeliveryEnding());

	    if(userAddressPrefPO.getDesiredDeliveryDays()!=null)
		addrPrefModel.setDesiredDeliveryDays((String)(EnumTypes.DesiredDeliveryDays.fetchDesiredDeliveryDaysFromCode(userAddressPrefPO.getDesiredDeliveryDays())));
	    addrPrefModel.setCombineShipmentWhenAppropriate(userAddressPrefPO.getCombineShipmentWhenAppropriate());

	    return addrPrefModel;
	}
	else
	    return null;
    }

    /**
     * @param inputParam
     * @return
     */
    public static List<UserCreditCard> populateCCModelListFromPO(Map<String, Object> inputParam) {

	UserManagementPO userManagementPO = (UserManagementPO) inputParam.get("UserManagementPO");
	List<CreditCardModel> creditCaredPOList = userManagementPO.getCreditCard();
	List<UserCreditCard> creditCardList  = new ArrayList<UserCreditCard>();
	if(creditCaredPOList!=null && !creditCaredPOList.isEmpty())
	{
	    for(CreditCardModel creditCardPO: creditCaredPOList)
	    {	
		UserCreditCard creditCardModel = null;
		if(userManagementPO.getUserId()!=null){
		    creditCardModel = populateCreditCardDOFromPO(creditCardPO,userManagementPO.getUserId());
		}else{
		    creditCardModel = populateCreditCardDOFromPO(creditCardPO,"0");	
		}
		if(creditCardModel != null){
		    creditCardList.add(creditCardModel);
		}
	    }
	}
	return creditCardList;
    }

    private static UserCreditCard populateCreditCardDOFromPO(
	    CreditCardModel creditCardPO,String userId) {
	UserCreditCard creditCardModel = new UserCreditCard();
	if(!StringUtils.isEmpty(creditCardPO.getCreditCardId()))
	    creditCardModel.setId(Long.valueOf(creditCardPO.getCreditCardId()));

	if(!StringUtils.isEmpty(creditCardPO.getCardHolderName()))
	    creditCardModel.setCardHolderName(creditCardPO.getCardHolderName());

	if(creditCardPO.getCreditCartTypeId()!=null)     
	    creditCardModel.setCreditCardType(MasterDataRepository.getMasterDataById(creditCardPO.getCreditCartTypeId()));
	String expDate = creditCardPO.getExpirationDate();
	creditCardModel.setExpirationDate(expDate);
	creditCardModel.setIsDefault(creditCardPO.getIsDefault());
	UserAddress userAddress = populateUserAddrModelFromPO(creditCardPO.getAddress());
	if(creditCardPO.getAddress() != null)
	    userAddress.setAddressPreference(populateAddrPrefModelFromPO(creditCardPO.getAddress().getAddressPref()));
	creditCardModel.setUserAddress(userAddress);

	Map<String, String> braintreeMap = CreditCardValidation.createCreditCard(creditCardPO); 
	if(braintreeMap != null){
	    UserCreditCard checkCreditCard = UserRepository.findByUserIdBraintreeUid(Long.valueOf(userId), braintreeMap.get("braintreeUID"));
	    if(checkCreditCard == null){

		creditCardModel.setPgProfile(braintreeMap.get("profileId"));
		creditCardModel.setBraintreeUid(braintreeMap.get("braintreeUID"));
		return creditCardModel;
	    }else{
		CreditCardValidation.deleteCreditCard(braintreeMap.get("profileId"));
		return null;
	    }
	}
	return null;
    }

    /**
     * @param userManagementPO
     * @param property
     * @return
     */
    public static boolean isValidUserPO(UserManagementPO userManagementPO, String[]property){

	if(userManagementPO!=null)		
	{
	    if(ApplicationUtils.validateMandatoryFields(userManagementPO, property, UserManagementPO.class))
	    {
		List<UserAddressModel> userAddressModelList = userManagementPO.getAddress();
		List<CreditCardModel> userCCList = userManagementPO.getCreditCard();
		if(!validateAddress(userAddressModelList) || !validateCreditCard(userCCList))
		    return false;
	    }
	    else
		return false;
	}

	return true;

    }


    /**
     * @param userAddressModelList
     * @return
     */
    private static boolean validateAddress(List<UserAddressModel> userAddressModelList) {
	//boolean isValid = true;
	if(userAddressModelList!=null && !userAddressModelList.isEmpty()){
	    String [] addressProperty = {"zipCode","addressType","countryId"};
	    for(UserAddressModel userAddressModel :userAddressModelList)
	    {
		if(!ApplicationUtils.validateMandatoryFields(userAddressModel, addressProperty, UserAddressModel.class))						
		    return false;	
	    }
	}
	return true;
    }

    /**
     * @param userCCList
     * @return
     */
    private static boolean validateCreditCard(List<CreditCardModel> userCCList) {

	if(userCCList!=null && !userCCList.isEmpty()){
	    String [] ccProperty = {"creditCartTypeId","expirationDate"};
	    String [] ccAddressProp = {"countryId"};
	    for(CreditCardModel userCCModel :userCCList)
	    {
		if(!ApplicationUtils.validateMandatoryFields(userCCModel, ccProperty, CreditCardModel.class))
		    return false;
		if(!ApplicationUtils.validateMandatoryFields(userCCModel.getAddress(), ccAddressProp, UserAddressModel.class))
		    return false;	
	    }

	}
	return true;
    }
    /**
     * @param userModel
     * @param userAddressList
     * @param userCreditCardList
     * @return
     */
    public static UserDetailVO populateVOFromDO(UserModel userModel,List<UserAddress> userAddressList, List<UserCreditCard> userCreditCardList,List<UserPreferenceVO> userPreferenceVOList){

	UserDetailVO userDetailVO  = new UserDetailVO();
	List<UserAddressResultModel> userAddressModelList  = new ArrayList<UserAddressResultModel>();
	List<CreditCardResultModel> creditCardList  = new ArrayList<CreditCardResultModel>();
	userDetailVO.setUserId(userModel.getId());
	userDetailVO.setEmail(userModel.getEmail());
	userDetailVO.setFirstName(userModel.getFirstName());
	userDetailVO.setLastName(userModel.getLastName());
	userDetailVO.setSalutation(userModel.getSalutation());
	userDetailVO.setGender(userModel.getGender());
	userDetailVO.setZipCode(userModel.getZipCode());
	userDetailVO.setCountryId(userModel.getCountryId());
	userDetailVO.setStateId(userModel.getStateId());
	userDetailVO.setCountryId(userModel.getCountryId());
	userDetailVO.setIsReceivedNewsletter(userModel.isReceivedNewLetter());
	userDetailVO.setIsEnabled(userModel.isEnabled());
	userDetailVO.setCreatedDate(userModel.getCreatedDate());
	userDetailVO.setUpdatedDate(userModel.getModifiedDate());
	userDetailVO.setIsRegistered(userModel.getRegistered());
	userDetailVO.setPhone(userModel.getPhone());
	userDetailVO.setRegSource(userModel.getRegSource());
	if(userModel.getUserActivationCode() != null){
	    userDetailVO.setActCodeExist(true);
	}
	if(userModel.getRegStatus()!=null){
	    Map<String,String> enumRegStatusTypeMap = new HashMap<String, String>();
	    enumRegStatusTypeMap.put("id",EnumTypes.RegType.fetchRegCode(userModel.getRegStatus()));
	    enumRegStatusTypeMap.put("value",EnumTypes.RegType.valueOf(userModel.getRegStatus()).toString());
	    userDetailVO.setRegStatus(enumRegStatusTypeMap);
	}

	if(userModel.getEmailType()!=null){
	    Map<String,String> enumEmailTypeMap = new HashMap<String, String>();
	    enumEmailTypeMap.put("id",EnumTypes.EmailType.fetchEmailTypeCode(userModel.getEmailType()));
	    enumEmailTypeMap.put("value",EnumTypes.EmailType.valueOf(userModel.getEmailType()).toString());
	    userDetailVO.setEmailType(enumEmailTypeMap);
	}

	if(UserRepository.getByUserId(userModel.getCreatedBy())!=null)
	    userDetailVO.setCreatedBy(UserRepository.getByUserId(userModel.getCreatedBy()).getEmail());

	if(UserRepository.getByUserId(userModel.getModifiedBy())!=null)
	    userDetailVO.setUpdatedBy(UserRepository.getByUserId(userModel.getModifiedBy()).getEmail());

	userDetailVO.setIsRegistered(userModel.getRegistered());
	if(userAddressList!=null && !userAddressList.isEmpty())
	{
	    for(UserAddress userAddress:userAddressList)
	    {
		UserAddressResultModel userAddressModel = populateUserAddressVO(userAddress);
		userAddressModelList.add(userAddressModel);
	    }
	}
	userDetailVO.setAddress(userAddressModelList);
	if(userCreditCardList!=null && !userCreditCardList.isEmpty())
	{
	    for(UserCreditCard creditCardModel:userCreditCardList)
	    {
		CreditCardResultModel creditCardResultModel = populateCreditCardVOFromDO(creditCardModel);
		creditCardList.add(creditCardResultModel);

		//userAddressModelList.add(creditCardModel);
	    }
	}
	userDetailVO.setCreditCard(creditCardList);
	userDetailVO.setNewsletterList(userPreferenceVOList);

	return userDetailVO;

    }


    public static List<CreditCardResultModel> populateCCListVOFromDO(List<UserCreditCard> userCreditCardList){


	List<CreditCardResultModel> creditCardList  = new ArrayList<CreditCardResultModel>();


	if(userCreditCardList!=null && !userCreditCardList.isEmpty())
	{
	    for(UserCreditCard creditCardModel:userCreditCardList)
	    {
		CreditCardResultModel creditCardResultModel = populateCreditCardVOFromDO(creditCardModel);
		creditCardList.add(creditCardResultModel);

		//userAddressModelList.add(creditCardModel);
	    }
	}
	return creditCardList;
	//userDetailVO.setNewsletterList(userPreferenceVOList);



    }

    private static CreditCardResultModel populateCreditCardVOFromDO(
	    UserCreditCard creditCardModel) {
	CreditCardResultModel creditCardResultModel = new CreditCardResultModel();
	creditCardResultModel.setCreditCardId(String.valueOf(creditCardModel.getId()));
	creditCardResultModel.setCardHolderName(creditCardModel.getCardHolderName());
	creditCardResultModel.setAddressId(String.valueOf(creditCardModel.getUserAddressId()));
	creditCardResultModel.setExpirationDate(creditCardModel.getExpirationDate());

	if(creditCardModel.getCreditCardType()!=null){
	    Map<String,String> prefCreditCardMap = new HashMap<String,String>();
	    prefCreditCardMap.put("id", creditCardModel.getCreditCardType().getId().toString());
	    prefCreditCardMap.put("value", creditCardModel.getCreditCardType().getName());	
	    creditCardResultModel.setCreditCartType(prefCreditCardMap);
	}
	if(creditCardModel.getPgProfile()!=null)
	{
	    creditCardResultModel.setCreditCardNumber(CreditCardValidation.viewCreditCard(creditCardModel.getPgProfile()));
	}

	creditCardResultModel.setIsDefault(creditCardModel.getIsDefault());
	return creditCardResultModel;
    }

    public static UserAddressResultModel populateUserAddressVO(UserAddress userAddress) {
	UserAddressResultModel userAddressModel = new UserAddressResultModel();
	userAddressModel.setAddressId(String.valueOf(userAddress.getId()));
	userAddressModel.setAddressLine1(userAddress.getAddress1());
	userAddressModel.setAddressLine2(userAddress.getAddress2());
	userAddressModel.setBirthDate(userAddress.getBirthDate());
	userAddressModel.setBusinessName(userAddress.getBusinessName());
	userAddressModel.setCityId(userAddress.getCityId());
	userAddressModel.setStateId(userAddress.getStateId());
	userAddressModel.setCountryId(userAddress.getCountryId());
	userAddressModel.setFirstName(userAddress.getFirstName());
	userAddressModel.setLastName(userAddress.getLastName());



	userAddressModel.setDefault(userAddress.isDefault());


	userAddressModel.setPhone(userAddress.getPhone());

	userAddressModel.setZipCode(userAddress.getZipcode());
	if(userAddress.getAddressType()!=null){
	    Map<String,String> enumAddressTypeMap = new HashMap<String, String>();
	    enumAddressTypeMap.put("id",EnumTypes.AddressType.fetchAddressCode(userAddress.getAddressType()));
	    enumAddressTypeMap.put("value",EnumTypes.AddressType.valueOf(userAddress.getAddressType()).toString());
	    userAddressModel.setAddressType(enumAddressTypeMap);
	}
	if(userAddress.getAddressPreference()!=null)
	{
	    AddressPreference addressPreference = userAddress.getAddressPreference();
	    UserAddressPrefResultModel userAddressPrefModel = new UserAddressPrefResultModel();
	    userAddressPrefModel.setAddrPrefId(String.valueOf(addressPreference.getId()));
	    userAddressPrefModel.setCombineShipmentWhenAppropriate(addressPreference.isCombineShipmentWhenAppropriate());
	    userAddressPrefModel.setDeliveryEnding(addressPreference.getDeliveryEndTime());
	    userAddressPrefModel.setDeliveryStarting(addressPreference.getDeliveryStartTime());
	    if(addressPreference.getDesiredDeliveryDays()!=null){
		String[] desireddeliverydate = addressPreference.getDesiredDeliveryDays().split(",");

		List<DesiredDeliveryDaysModel> daysMapList = new ArrayList<DesiredDeliveryDaysModel>();
		for(String days:desireddeliverydate){

		    DesiredDeliveryDaysModel desiredDeliveryDaysModel = new DesiredDeliveryDaysModel();
		    desiredDeliveryDaysModel.setId(EnumTypes.DesiredDeliveryDays.fetchDesiredDeliveryDaysCode(days));
		    desiredDeliveryDaysModel.setValue(days);
		    daysMapList.add(desiredDeliveryDaysModel);


		}
		userAddressPrefModel.setDesiredDeliveryDays(daysMapList);
	    }


	    if(addressPreference.getPrefferedCarrier()!=null){
		Map<String,String> prefCarrierMap = new HashMap<String,String>();
		prefCarrierMap.put("id", addressPreference.getPrefferedCarrier().getId().toString());
		prefCarrierMap.put("value", addressPreference.getPrefferedCarrier().getName());
		userAddressPrefModel.setPrefCarrier(prefCarrierMap);
	    }

	    if(addressPreference.getPrefferedPackageType()!=null)	{				
		Map<String,String> prefPackageTypeMap = new HashMap<String,String>();
		prefPackageTypeMap.put("id", addressPreference.getPrefferedPackageType().getId().toString());
		prefPackageTypeMap.put("value", addressPreference.getPrefferedPackageType().getName());
		userAddressPrefModel.setPrefPackageType(prefPackageTypeMap);	
	    }

	    if(addressPreference.getPrefferedShippingMethod()!=null)	{	
		Map<String,String> prefShippingMethodMap = new HashMap<String,String>();
		prefShippingMethodMap.put("id", addressPreference.getPrefferedShippingMethod().getId().toString());
		prefShippingMethodMap.put("value", addressPreference.getPrefferedShippingMethod().getName());
		userAddressPrefModel.setPrefShippingMethod(prefShippingMethodMap);	
	    }

	    userAddressModel.setAddressPref(userAddressPrefModel);
	}
	return userAddressModel;
    }

    //Create the method to validate the phone number format
    public static boolean validatePhoneFormat(UserManagementPO userManage) {
	if(!StringUtils.isEmpty(userManage.getPhone())){
	    boolean isValidUserPhone = ValidationUtil.validateContentFormat(userManage.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");

	    if(!isValidUserPhone)
		return false;
	}
	if(userManage.getAddress()!= null){
	    for(UserAddressModel model:userManage.getAddress()){
		if(!StringUtils.isEmpty(model.getPhone())){
		    boolean isValidAddressPhone = ValidationUtil.validateContentFormat(model.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");

		    if(!isValidAddressPhone)
			return false;
		}
	    }
	}

	if(userManage.getCreditCard()!=null){
	    for(CreditCardModel cardModel:userManage.getCreditCard()){
		if(cardModel.getAddress()!=null){
		    if(!StringUtils.isEmpty(cardModel.getAddress().getPhone())){
			boolean isValidCreditCardAddressPhone = ValidationUtil.validateContentFormat(cardModel.getAddress().getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");

			if(!isValidCreditCardAddressPhone)
			    return false;
		    }
		}
	    }
	}
	return true;
    }

    public static Map<String, Object> validateMergeUserIds(UserModel toBeMergedUserModel, UserModel userInWhichToBeMergedModel) {
	Map<String, Object> output = new HashMap<String,Object>();
	if(toBeMergedUserModel== null || userInWhichToBeMergedModel==null){		
	    logger.info("No record exists");
	    output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS, SystemErrorCode.NO_RECORD_EXISTS_TXT, 200));	
	}
	else if(BooleanUtils.isTrue(toBeMergedUserModel.isDeleted()) || BooleanUtils.isTrue(userInWhichToBeMergedModel.isDeleted()))
	{
	    logger.info("Any of these user are deleted");
	    output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.DELETED_USER, SystemErrorCode.DELETED_USER_TXT, 200));

	}

	else if(BooleanUtils.isNotTrue(toBeMergedUserModel.isEnabled()) || BooleanUtils.isNotTrue(userInWhichToBeMergedModel.isEnabled()))
	{
	    logger.info("Any of these user are disabled");
	    output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.DISABLE_USER, SystemErrorCode.DISABLE_USER_TXT, 200));

	}
	else if(BooleanUtils.isNotTrue(toBeMergedUserModel.getRegistered()) || BooleanUtils.isNotTrue(userInWhichToBeMergedModel.getRegistered()))
	{
	    logger.info("Any of these user is not registered");
	    output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_REGISTERED_USER, SystemErrorCode.ERROR_REGISTERED_USER_TXT, 200));

	}
	return output;
    }

    public static Map<String, Object> validateToBeDeletedList(List<Long> listFromPO, List<Long> listFromDB , String errorCode, String errorCodeText) {
	Map<String, Object> output = new HashMap<String, Object>();
	if(!listFromDB.isEmpty()){
	    for(Long ids:listFromPO){
		if((Collections.frequency(listFromDB, ids)!=1))
		    output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(errorCode, errorCodeText, 200));
	    }
	}
	else
	    output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(errorCode, errorCodeText, 200));
	return output;
    }

    /**
     * @param userIdOfAddress
     * @param addressId
     * @return
     */
    public static Map<String, Object> generateAddressDetailResponse(Long userIdOfAddress, Long addressId) {

	UserAddressResultModel useraddressResultModel = new UserAddressResultModel();
	Map <String, Object> outputAddressDetail = new HashMap<String,Object>();
	UserModel userModelForAddressDetail = UserRepository.getByUserId(userIdOfAddress);
	Response res = new FailureResponse();
	res.setStatus(200);
	if(userModelForAddressDetail == null){
	    logger.info("No user exists");
	    res.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));

	}

	List<Object[]> listOfAddress = UserRepository.findAddressByUserIdAddressId(userIdOfAddress,addressId);

	if(listOfAddress==null || listOfAddress.isEmpty() )
	{
	    logger.info("No address exists for user");
	    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));
	}

	else
	{				
	    UserAddress usrAddress = (UserAddress)listOfAddress.get(0)[0];
	    usrAddress.setAddressPreference((AddressPreference)listOfAddress.get(0)[1]);		
	    if(!usrAddress.getId().equals(addressId))
	    {
		logger.info("No address exists for user");					
		res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));

	    }
	    else
		useraddressResultModel = UserManagementHelper.populateUserAddressVO(usrAddress);

	}
	if(res.getErrors().isEmpty()){
	    res = new com.wineaccess.response.SuccessResponse(useraddressResultModel, 200);
	}

	outputAddressDetail.put("FINAL-RESPONSE", res);
	return outputAddressDetail;
    }

    public static List<UserAddress> populateAddressAndAddressPref( Long userId) {
	List<UserAddress> userAddrModelList = new ArrayList<UserAddress>();
	List<Object[]> listOfAddress = UserRepository.findAddressByUserId(userId);
	if(listOfAddress!=null && !listOfAddress.isEmpty()){
	    for(Object[] obj:listOfAddress){
		UserAddress usrAddress = (UserAddress)obj[0];
		usrAddress.setAddressPreference((AddressPreference)obj[1]);
		userAddrModelList.add(usrAddress);
	    }
	}


	return userAddrModelList;
    }

    public static Map<String, Object> generateAddAddressResponse(UserAddressModelAtomicPO userAddressModelPO) {
	Long userId = userAddressModelPO.getUserId();
	UserModel userModel = UserRepository.getByUserId(userId);		

	Map <String, Object> outputAddAddress = new HashMap<String,Object>();
	Response res = new FailureResponse();
	res.setStatus(200);

	if(!StringUtils.isEmpty(userAddressModelPO.getPhone())){
	    boolean isValidUserPhone = ValidationUtil.validateContentFormat(userAddressModelPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
	    if(!isValidUserPhone)
		res.addError(new WineaccessError(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT,SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT));
	}

	if(userModel!=null && res.getErrors().isEmpty()){

	    userId = userModel.getId();
	    UserAddress userAddress = populateUserAddrModelFromPO(userAddressModelPO, userId);
	    AddressPreference addressPreference = populateAddrPrefModelFromPO(userAddressModelPO.getAddressPref());
	    userAddress.setUserId(userId);
	    if(addressPreference == null)
		addressPreference = new AddressPreference();

	    addressPreference = UserRepository.saveOrUpdateAddrPref(addressPreference);		
	    logger.info("Address pref created");
	    if(addressPreference!=null){
		userAddress.setAddressPreferenceId(addressPreference.getId());
		userAddress = UserRepository.saveOrUpdateAddr(userAddress);
		logger.info("Address  created");
	    }
	    if(userAddress.getId()!=null){
		AddressVO addressVO = new AddressVO(userAddressModelPO.getUserId(), userAddress.getId(), addressPreference.getId(), "address created successfully");
		res = new com.wineaccess.response.SuccessResponse(addressVO, 200);
	    }
	    else
		res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_CREATED,SystemErrorCode.ADDRESS_NOT_CREATED_TXT));
	}
	else if(userModel == null)
	    res.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));
	outputAddAddress.put("FINAL-RESPONSE", res);
	return outputAddAddress;
    }

    public static Map<String, Object> generateUpdateAddressResponse(UserEditAddressModelAtomicPO userAddressEditModelPO) {

	Long userId = userAddressEditModelPO.getUserId();
	Long addressId = Long.valueOf(userAddressEditModelPO.getAddressId());
	UserModel userModel = UserRepository.getByUserId(userId);	
	if (userModel == null){
	    Map <String, Object> outputAddAddress = new HashMap<String,Object>();
	    Response res = new FailureResponse();
	    res.setStatus(200);
	    res.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));	
	    outputAddAddress.put("FINAL-RESPONSE", res);
	    return outputAddAddress;
	}
	UserAddress usrAddress = new UserAddress();
	AddressPreference addressPreference = new AddressPreference();
	Map <String, Object> outputAddAddress = new HashMap<String,Object>();
	Response res = new FailureResponse();
	res.setStatus(200);

	if(!StringUtils.isEmpty(userAddressEditModelPO.getPhone())){
	    boolean isValidUserPhone = ValidationUtil.validateContentFormat(userAddressEditModelPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
	    if(!isValidUserPhone)
		res.addError(new WineaccessError(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT,SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT));
	}

	if(userModel!=null && res.getErrors().isEmpty()){

	    List<Object[]> listOfAddress = UserRepository.findAddressByUserIdAddressId(userId,addressId);

	    if(listOfAddress==null || listOfAddress.isEmpty() )
	    {
		logger.info("No address exists for user");
		res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));
	    }

	    else
	    {				
		usrAddress = (UserAddress)listOfAddress.get(0)[0];
		usrAddress.setAddressPreference((AddressPreference)listOfAddress.get(0)[1]);		
		if(!usrAddress.getId().equals(addressId))
		{
		    logger.info("No address exists for user");					
		    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));

		}
		else{
		    usrAddress = populateUserEditAddrModelFromPO(userAddressEditModelPO, usrAddress, userAddressEditModelPO.getUserId());
		    if(usrAddress == null){
			logger.info("Default address can not be set to not default");					
			res.addError(new WineaccessError(SystemErrorCode.DEFAULT_ADDRESS_NOT_CHANGED,SystemErrorCode.DEFAULT_ADDRESS_NOT_CHANGED_TXT));
		    }else{
			addressPreference = UserRepository.saveOrUpdateAddrPref(usrAddress.getAddressPreference());		
			logger.info("Address pref updated");
			if(addressPreference!=null){
			    usrAddress.setAddressPreferenceId(addressPreference.getId());
			    usrAddress = UserRepository.saveOrUpdateAddr(usrAddress);
			    logger.info("Address  created");
			}
			if(usrAddress.getId()!=null){
			    AddressVO addressVO = new AddressVO(usrAddress.getUserId(), usrAddress.getId(), usrAddress.getAddressPreferenceId(), "address updated successfully");
			    res = new com.wineaccess.response.SuccessResponse(addressVO, 200);
			    userModel.setModifiedBy(new Long(1));
			    UserRepository.update(userModel);
			}
			else
			    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_CREATED,SystemErrorCode.ADDRESS_NOT_CREATED_TXT));
		    }

		}

	    }
	}
	outputAddAddress.put("FINAL-RESPONSE", res);
	return outputAddAddress;

    }


    /**
     * @param creditCardPO
     * @return
     */
    public static Map<String, Object> generateAddCreditCardResponse(
	    CreditCardModelAtomicPO creditCardPO) {
	Long userId = creditCardPO.getUserId();
	UserAddressModel userAddressModelPO = creditCardPO.getAddress();
	UserModel userModel = UserRepository.getByUserId(userId);		

	Map <String, Object> outputAddAddress = new HashMap<String,Object>();
	Response res = new FailureResponse();
	res.setStatus(200);

	if(userAddressModelPO!=null && !StringUtils.isEmpty(userAddressModelPO.getPhone())){
	    boolean isValidUserPhone = ValidationUtil.validateContentFormat(userAddressModelPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
	    if(!isValidUserPhone)
		res.addError(new WineaccessError(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT,SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT));
	}

	if(userModel!=null){

	    userId = userModel.getId();
	    UserCreditCard creditCardModel = populateCreditCardDOFromPO(creditCardPO,String.valueOf(userId));
	    if(creditCardModel != null){
		UserAddress userAddress = populateUserAddrModelFromPO(creditCardPO.getAddress());
		AddressPreference addressPreference = populateAddrPrefModelFromPO(creditCardPO.getAddress().getAddressPref());
		userAddress.setUserId(userId);

		if(addressPreference == null)
		    addressPreference = new AddressPreference();
		if(userAddress.getId()!=null){

		    List<Object[]> listOfAddress = UserRepository.findAddressByUserIdAddressId(userId,userAddress.getId());

		    if(listOfAddress==null || listOfAddress.isEmpty() )
		    {
			logger.info("No address exists for user");
			res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));
		    }

		    else
		    {				
			UserAddress userAdd = (UserAddress)listOfAddress.get(0)[0];
			userAdd.setAddressPreference((AddressPreference)listOfAddress.get(0)[1]);		
			if(!userAdd.getId().equals(userAddress.getId()))
			{
			    logger.info("No address exists for user");					
			    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));

			}
			
			else if(!StringUtils.containsIgnoreCase(userAdd.getAddressType(),"Billing"))
			{
			    logger.info("address is not of biling type");					
			    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_BILLING,SystemErrorCode.ADDRESS_NOT_BILLING_TEXT));
			}
			else{

			    userAddress = userAdd;
			    addressPreference = userAdd.getAddressPreference();
			}
		    }
		}
		if(res.getErrors().isEmpty()){
		    addressPreference = UserRepository.saveOrUpdateAddrPref(addressPreference);		
		    logger.info("Address pref created");
		    if(addressPreference!=null){
			userAddress.setAddressPreferenceId(addressPreference.getId());
			userAddress = UserRepository.saveOrUpdateAddr(userAddress);
			logger.info("Address  created");
		    }
		    if(userAddress!=null)
		    {
			creditCardModel.setUserAddressId(userAddress.getId());
			creditCardModel.setUserId(userId);
			UserCreditCard defaultCreditCard = UserRepository.findDefaultCreditCard(userId);

			if(defaultCreditCard == null){
			    creditCardModel.setIsDefault(true);
			}else{
			    if(creditCardModel.getIsDefault()){
				//creditCardModel.setIsDefault(true);
				defaultCreditCard.setIsDefault(false);
				UserRepository.saveOrUpdateCreditCard(defaultCreditCard);
			    }

			}
			creditCardModel = UserRepository.saveOrUpdateCreditCard(creditCardModel);
		    }
		    if(creditCardModel.getId()!=null){
			CreditCardVO creditCardVO = new CreditCardVO(creditCardModel.getUserId(), creditCardModel.getId(),userAddress.getId(), addressPreference.getId(), "creit card created successfully");
			res = new com.wineaccess.response.SuccessResponse(creditCardVO, 200);
		    }
		    else
			res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_CREATED,SystemErrorCode.ADDRESS_NOT_CREATED_TXT));
		}
	    }
	    else
		res.addError(new WineaccessError(SystemErrorCode.DUPLICATE_CC_ERROR,SystemErrorCode.DUPLICATE_CC_ERROR_TEXT));
	}
	else if(userModel == null)
	    res.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));

	outputAddAddress.put("FINAL-RESPONSE", res);
	return outputAddAddress;
    }

    /**
     * @param editCreditCardPO
     * @return
     */
    public static Map<String, Object> generateUpdateCreditCardResponse(
	    CreditCardEditModelAtomicPO editCreditCardPO) {

	UserCreditCard creditCardDO = new UserCreditCard();
	Long userId = editCreditCardPO.getUserId();
	Long creditCardId = Long.valueOf(editCreditCardPO.getCreditCardId());
	String addressId = null;
	//UserAddressModel userAddressModelPO = editCreditCardPO.getAddress();
	UserModel userModel = UserRepository.getByUserId(userId);		
	if(editCreditCardPO.getAddress()!=null)
	    addressId = editCreditCardPO.getAddress().getAddressId();
	Map <String, Object> outputAddAddress = new HashMap<String,Object>();
	Response res = new FailureResponse();
	res.setStatus(200);



	if(userModel!=null){

	    userId = userModel.getId();

	    List<Object[]> listOfCCs = UserRepository.findCCByUserIdCCId(userId,creditCardId);

	    if(listOfCCs==null || listOfCCs.isEmpty() )
	    {
		logger.info("No address exists for user");
		res.addError(new WineaccessError(SystemErrorCode.CC_NOT_EXIST,SystemErrorCode.CC_NOT_EXIST_TXT));
	    }

	    else
	    {				
		creditCardDO  = (UserCreditCard)listOfCCs.get(0)[0];

		if(!creditCardId.equals(creditCardDO.getId()))
		{
		    logger.info("No CC exists for user");					
		    res.addError(new WineaccessError(SystemErrorCode.CC_NOT_EXIST,SystemErrorCode.CC_NOT_EXIST_TXT));

		}

		if(!StringUtils.isEmpty(addressId)){

		    List<Object[]> listOfAddress = UserRepository.findAddressByUserIdAddressId(userId,Long.valueOf(addressId));

		    if(listOfAddress==null || listOfAddress.isEmpty() )
		    {
			logger.info("No address exists for user");
			res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));
		    }


		    else{
			UserAddress userAdd = (UserAddress)listOfAddress.get(0)[0];
			userAdd.setAddressPreference((AddressPreference)listOfAddress.get(0)[1]);
			if(!userAdd.getId().equals(Long.valueOf(addressId)))
			{
			    logger.info("No address exists for user");					
			    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_EXIST,SystemErrorCode.ADDRESS_NOT_EXIST_TXT));

			}	
			else if(!StringUtils.containsIgnoreCase(userAdd.getAddressType(),"Billing"))
			{
			    logger.info("address is not of biling type");					
			    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_BILLING,SystemErrorCode.ADDRESS_NOT_BILLING_TEXT));
			}
		    }
		}
	    }

	    UserCreditCard creditCardModel = populateEditCreditCardDOFromPO(editCreditCardPO,creditCardDO);
	    //String pgProfileId = CreditCardValidation.createCreditCard(creditCardPO);
	    if(creditCardModel != null){
		String pgProfileId = creditCardModel.getPgProfile();
		if(!StringUtils.isEmpty(pgProfileId)){
		    creditCardModel.setPgProfile(pgProfileId);

		    if(res.getErrors().isEmpty()){	
			UserCreditCard defaultCreditCard = UserRepository.findDefaultCreditCard(userId);
			if(defaultCreditCard == null){
			    creditCardModel.setIsDefault(true);
			}else{
			    creditCardModel.setIsDefault(true);
			    defaultCreditCard.setIsDefault(false);
			    UserRepository.saveOrUpdateCreditCard(defaultCreditCard);
			}
			creditCardModel = UserRepository.saveOrUpdateCreditCard(creditCardModel);
			if(creditCardModel.getId()!=null){
			    CreditCardVO creditCardVO = new CreditCardVO(creditCardModel.getUserId(), creditCardModel.getId(),creditCardModel.getUserAddressId(), creditCardId, "credit card updated successfully");
			    res = new com.wineaccess.response.SuccessResponse(creditCardVO, 200);
			}
			else
			    res.addError(new WineaccessError(SystemErrorCode.ADDRESS_NOT_CREATED,SystemErrorCode.ADDRESS_NOT_CREATED_TXT));
		    }
		}else{
		    res.addError(new WineaccessError(SystemErrorCode.CC_INVALID,SystemErrorCode.CC_INVALID_TXT));
		}	
	    }else{
		res.addError(new WineaccessError(SystemErrorCode.CC_INVALID,SystemErrorCode.CC_INVALID_TXT));
	    }
	}




	else if(userModel == null)
	    res.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));

	outputAddAddress.put("FINAL-RESPONSE", res);
	return outputAddAddress;
    }



    /**
     * @param userIdOfCC
     * @param creditcardId
     * @return
     */
    public static Map<String, Object> generateCreditCardDetailResponse(
	    Long userIdOfCC, Long creditcardId) {

	CreditCardResultModel creditCardResultModel = new CreditCardResultModel();
	Map <String, Object> outputAddressDetail = new HashMap<String,Object>();
	UserModel userDO = UserRepository.getByUserId(userIdOfCC);
	Response res = new FailureResponse();
	res.setStatus(200);
	if(userDO == null){
	    logger.info("No user exists");
	    res.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));

	}

	List<Object[]> listOfCCs = UserRepository.findCCByUserIdCCId(userIdOfCC,creditcardId);

	if(listOfCCs==null || listOfCCs.isEmpty() )
	{
	    logger.info("No address exists for user");
	    res.addError(new WineaccessError(SystemErrorCode.CC_NOT_EXIST,SystemErrorCode.CC_NOT_EXIST_TXT));
	}

	else
	{				
	    UserCreditCard userCC  = (UserCreditCard)listOfCCs.get(0)[0];
	    UserAddress userAddress = (UserAddress)listOfCCs.get(0)[1];
	    userAddress.setAddressPreference((AddressPreference)listOfCCs.get(0)[2]);
	    if(!userCC.getId().equals(creditcardId))
	    {
		logger.info("No CC exists for user");					
		res.addError(new WineaccessError(SystemErrorCode.CC_NOT_EXIST,SystemErrorCode.CC_NOT_EXIST_TXT));

	    }
	    else{
		creditCardResultModel = populateCreditCardVOFromDO(userCC);
		creditCardResultModel.setAddress(populateUserAddressVO(userAddress));
	    }


	}
	if(res.getErrors().isEmpty()){
	    res = new com.wineaccess.response.SuccessResponse(creditCardResultModel, 200);
	}

	outputAddressDetail.put("FINAL-RESPONSE", res);
	return outputAddressDetail;
    }

    /**
     * @param addressPreferenceDO
     * @param addressPreferencePO
     */
    private static void populateEditAddrPref(AddressPreference addressPreferenceDO,	UserAddressPrefModel addressPreferencePO) {
	if(addressPreferencePO.getCombineShipmentWhenAppropriate()==null)
	    addressPreferenceDO.setCombineShipmentWhenAppropriate(addressPreferenceDO.isCombineShipmentWhenAppropriate());
	else
	    addressPreferenceDO.setCombineShipmentWhenAppropriate(addressPreferencePO.getCombineShipmentWhenAppropriate());

	if(addressPreferencePO.getPrefCarrierId()==null)
	    addressPreferenceDO.setPrefferedCarrier(addressPreferenceDO.getPrefferedCarrier());
	else
	    addressPreferenceDO.setPrefferedCarrier(MasterDataRepository.getMasterDataById(addressPreferencePO.getPrefCarrierId()));

	if(addressPreferencePO.getPrefShippingMethodId()==null)
	    addressPreferenceDO.setPrefferedShippingMethod(addressPreferenceDO.getPrefferedShippingMethod());
	else
	    addressPreferenceDO.setPrefferedShippingMethod(MasterDataRepository.getMasterDataById(addressPreferencePO.getPrefShippingMethodId()));

	if(addressPreferencePO.getPrefPackageTypeId()==null)
	    addressPreferenceDO.setPrefferedPackageType(addressPreferenceDO.getPrefferedPackageType());
	else
	    addressPreferenceDO.setPrefferedPackageType(MasterDataRepository.getMasterDataById(addressPreferencePO.getPrefPackageTypeId()));

	if(addressPreferencePO.getDeliveryEnding()==null)
	    addressPreferenceDO.setDeliveryEndTime(addressPreferenceDO.getDeliveryEndTime());
	else
	    addressPreferenceDO.setDeliveryEndTime(addressPreferencePO.getDeliveryEnding());

	if(addressPreferencePO.getDeliveryStarting()==null)
	    addressPreferenceDO.setDeliveryStartTime(addressPreferenceDO.getDeliveryStartTime());
	else
	    addressPreferenceDO.setDeliveryStartTime(addressPreferencePO.getDeliveryStarting());

	if(addressPreferencePO.getDesiredDeliveryDays()==null)
	    addressPreferenceDO.setDesiredDeliveryDays(addressPreferenceDO.getDesiredDeliveryDays());
	else
	    addressPreferenceDO.setDesiredDeliveryDays((String)(EnumTypes.DesiredDeliveryDays.fetchDesiredDeliveryDaysFromCode(addressPreferencePO.getDesiredDeliveryDays())));
    }

    /**
     * @param userAddressPO
     * @param userAddressDO
     * @return
     */
    private static UserAddress populateUserEditAddrModelFromPO(UserAddressModel userAddressPO, UserAddress userAddressDO, Long... userId) {
	String [] temp = {"birthDate", "deliveryStarting", "deliveryEnding","countryId"};
	String [] toBeIgnoredValues = (String[]) ArrayUtils.addAll(getNullPropertyNames(userAddressPO),temp);
	org.springframework.beans.BeanUtils.copyProperties(userAddressPO,userAddressDO,  toBeIgnoredValues);
	if(userAddressPO.getCountryId() != null){
	    userAddressDO.setCountryId(Long.parseLong(userAddressPO.getCountryId()));
	}
	AddressPreference addressPreferenceDO = userAddressDO.getAddressPreference();
	UserAddressPrefModel addressPreferencePO = userAddressPO.getAddressPref();

	if(userAddressPO != null){

	    if(StringUtils.isEmpty(userAddressPO.getAddressLine1()))
		userAddressDO.setAddress1(userAddressDO.getAddress1());
	    else
		userAddressDO.setAddress1(userAddressPO.getAddressLine1());
	    if(StringUtils.isEmpty(userAddressPO.getAddressLine2()))
		userAddressDO.setAddress2(userAddressDO.getAddress2());
	    else
		userAddressDO.setAddress2(userAddressPO.getAddressLine2());


	    if(StringUtils.isEmpty(userAddressPO.getAddressType()))
		userAddressDO.setAddressType(userAddressDO.getAddressType());
	    else
		userAddressDO.setAddressType((String)(EnumTypes.AddressType.fetchAddressTypeFromCode(userAddressPO.getAddressType())));

	    if(userId != null && userId.length==1){

		UserAddress defaultAddress = UserRepository.findDefaultAddress(userId[0],getAddressType(userAddressPO.getAddressType()));
		if(BooleanUtils.isTrue(Boolean.parseBoolean(userAddressPO.getIsDefault()))){
		    userAddressDO.setDefault(true);
		}
		if(userAddressDO.getId() != null && defaultAddress != null && defaultAddress.getId() == userAddressDO.getId() && defaultAddress.isDefault() && userAddressDO.isDefault()){
		    return null;
		}

		if(defaultAddress != null){
		    defaultAddress.setDefault(false);
		    UserRepository.saveOrUpdateAddr(defaultAddress);


		}
	    }

	    if(addressPreferencePO!=null)
	    {
		populateEditAddrPref(addressPreferenceDO, addressPreferencePO);

	    }

	}

	String birthDate = userAddressPO.getBirthDate();
	if(birthDate != null)
	    userAddressDO.setBirthDate(ApplicationUtils.convertDateFromString(birthDate));

	return userAddressDO;

    }


    /**
     * @param editCreditCardPO
     * @param creditCardDO
     * @return
     */
    private static UserCreditCard populateEditCreditCardDOFromPO(
	    CreditCardEditModelAtomicPO editCreditCardPO, UserCreditCard creditCardDO) {
	String [] temp = {"expirationDate"};
	String [] toBeIgnoredValues = (String[]) ArrayUtils.addAll(getNullPropertyNames(editCreditCardPO),temp);
	org.springframework.beans.BeanUtils.copyProperties(editCreditCardPO,creditCardDO,  toBeIgnoredValues);
	if(editCreditCardPO.getAddress()!=null && !StringUtils.isEmpty(editCreditCardPO.getAddress().getAddressId()))
	    creditCardDO.setUserAddressId(Long.valueOf(editCreditCardPO.getAddress().getAddressId()));
	if(StringUtils.isEmpty(editCreditCardPO.getExpirationDate()))
	    creditCardDO.setExpirationDate(creditCardDO.getExpirationDate());
	else
	    creditCardDO.setExpirationDate(editCreditCardPO.getExpirationDate());

	if(editCreditCardPO.getCreditCartTypeId()==null)     
	    creditCardDO.setCreditCardType(creditCardDO.getCreditCardType());
	else
	    creditCardDO.setCreditCardType(MasterDataRepository.getMasterDataById(editCreditCardPO.getCreditCartTypeId()));

	/*Map<String, String> braintreeMap = CreditCardValidation.createCreditCard(editCreditCardPO);
		if(braintreeMap != null){*/
	/*UserCreditCard checkCreditCard = UserRepository.findByUserIdBraintreeUid(editCreditCardPO.getUserId(), braintreeMap.get("braintreeUID"));
			if(checkCreditCard == null){*/
	String braintreeUid = CreditCardValidation.editCreditCard(editCreditCardPO, creditCardDO.getPgProfile());
	creditCardDO.setBraintreeUid(braintreeUid);
	//CreditCardValidation.deleteCreditCardByCustomer(braintreeMap.get("customerId"));
	return creditCardDO;
	/*}else if(checkCreditCard != null && checkCreditCard.getId() == creditCardDO.getId()) {
				CreditCardValidation.editCreditCard(editCreditCardPO, creditCardDO.getPgProfile());
				CreditCardValidation.deleteCreditCardByCustomer(braintreeMap.get("customerId"));
				return creditCardDO;
			}else {
				CreditCardValidation.deleteCreditCardByCustomer(braintreeMap.get("customerId"));
				return null;
			}*/
	/*}*/

    }


    private static String[] getNullPropertyNames (Object source) {
	final BeanWrapper src = new BeanWrapperImpl(source);
	java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	Set<String> emptyNames = new HashSet<String>();
	for(java.beans.PropertyDescriptor pd : pds) {
	    Object srcValue = src.getPropertyValue(pd.getName());
	    if (srcValue == null) emptyNames.add(pd.getName());
	}
	String[] result = new String[emptyNames.size()];
	return emptyNames.toArray(result);
    }

    /**
     * Method to return credit card lists for user
     * @param userIdForCreditCard
     * @return
     */

    public static Map<String, Object> generateCreditCardListForUser(
	    final Long userId) {
	final Map <String, Object> outputCreditCardList = new ConcurrentHashMap<String, Object>();		
	Response creditCardListReposnse = new FailureResponse();
	List<UserCreditCard> listOfCCs = null;
	final UserModel userDetailModelForCC = UserRepository.getByUserId(userId);
	if(userDetailModelForCC == null){	
	    creditCardListReposnse.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));
	}
	else
	{
	    listOfCCs = UserRepository.findCCListByUserId(userDetailModelForCC.getId());	
	    if(listOfCCs == null || listOfCCs.isEmpty()){	
		creditCardListReposnse.addError(new WineaccessError(SystemErrorCode.NO_RECORD_EXISTS,SystemErrorCode.NO_RECORD_EXISTS_TXT));
	    }
	    else{
		final List<CreditCardResultModel> creditCardList = UserManagementHelper.populateCCListVOFromDO(listOfCCs);
		final UserCreditCardsListVO userCardsListModel = new UserCreditCardsListVO();
		userCardsListModel.setCreditCardsList(creditCardList);
		creditCardListReposnse = new com.wineaccess.response.SuccessResponse(userCardsListModel, 200);
	    }
	}

	outputCreditCardList.put("FINAL-RESPONSE", creditCardListReposnse);
	return outputCreditCardList;
    }

    /**
     * Method to return address lists for user
     * @param userIdForCreditCard
     * @return
     */
    public static Map<String, Object> generateAddressesListForUser(
	    final Long userId) {

	final Map <String, Object> outputAddressesList = new ConcurrentHashMap<String, Object>();	
	Response addressListReposnse = new FailureResponse();
	final List<UserAddressResultModel> userAddressModelList  = new ArrayList<UserAddressResultModel>();
	List<UserAddress> userAddrModelList = new ArrayList<UserAddress>();
	final UserModel userModel = UserRepository.getByUserId(userId);
	if(userModel == null){	
	    addressListReposnse.addError(new WineaccessError(SystemErrorCode.USER_NOT_EXIST,SystemErrorCode.USER_NOT_EXIST_TXT));
	}
	else
	{
	    userAddrModelList = UserManagementHelper.populateAddressAndAddressPref(userId);
	    if(userAddrModelList == null || userAddrModelList.isEmpty()){	
		addressListReposnse.addError(new WineaccessError(SystemErrorCode.NO_RECORD_EXISTS,SystemErrorCode.NO_RECORD_EXISTS_TXT));
	    }
	    else
	    {
		for(final UserAddress userAddress:userAddrModelList)
		{
		    final UserAddressResultModel userAddressModel = UserManagementHelper.populateUserAddressVO(userAddress);
		    userAddressModelList.add(userAddressModel);
		}
		final UserAddressesListVO userAddressesListModel = new UserAddressesListVO();
		userAddressesListModel.setAddressesList(userAddressModelList);
		addressListReposnse =  new com.wineaccess.response.SuccessResponse(userAddressesListModel, 200);
	    }
	}
	outputAddressesList.put("FINAL-RESPONSE", addressListReposnse);
	return outputAddressesList;	
    }

    /*public static List<CreditCardResultModel> populateCCListVOFromDO(List<UserCreditCard> userCreditCardList){


		List<CreditCardResultModel> creditCardList  = new ArrayList<CreditCardResultModel>();


		if(userCreditCardList!=null && !userCreditCardList.isEmpty())
		{
			for(UserCreditCard creditCardModel:userCreditCardList)
			{
				CreditCardResultModel creditCardResultModel = populateCreditCardVOFromDO(creditCardModel);
				creditCardList.add(creditCardResultModel);

				//userAddressModelList.add(creditCardModel);
			}
		}
		return creditCardList;
		//userDetailVO.setNewsletterList(userPreferenceVOList);



	}


	private static String[] getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for(java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}*/

}
