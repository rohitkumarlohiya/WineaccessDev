package com.wineaccess.data.model.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.application.constants.NamedQueryConstants;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author jyoti.yadav@globalloguic.com
 */
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public static UserModel getByUserNamePassword(String email, String password) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> modelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_BY_USERNAME_PASSWORD, new String [] {"email", "password"}, email, password);
	if(modelList!= null & !modelList.isEmpty()){
	    return modelList.get(0);
	}else{
	    return null;
	}

    }

    public static List<UserModel> getAll() {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.findByNamedQuery(NamedQueryConstants.GETALL, null, null);
    }

    public static void save(UserModel userModel) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	genericDao.create(userModel);
    }
    public static UserModel getByUserName(String email) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> userModelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_BY_USER_NAME, new String [] {"email"}, email);
	if(userModelList!=null && !userModelList.isEmpty())
	    return userModelList.get(0);
	else
	    return null;
    }
    public static UserModel getByUserId(Long userId) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> userModelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_USER_MODEL_BY_ID, new String [] {"id"}, userId);
	if(userModelList!=null && !userModelList.isEmpty())
	    return userModelList.get(0);
	else
	    return null;
    }

    public static UserModel getByUserIdDeleted(Long userId) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> userModelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_BY_USER_ID_DELETED, new String [] {"id"}, userId);
	if(userModelList!=null && !userModelList.isEmpty())
	    return userModelList.get(0);
	else
	    return null;
    }


    public static UserModel update(UserModel userModel) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.update(userModel);
    }
    public static List<UserSSO> getUserWithSSO(Long id) {
	GenericDAO<UserSSO> genericDao = (GenericDAO<UserSSO>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.findByNamedQuery(NamedQueryConstants.GET_USER_WITH_SSO ,new String [] {"id"}, id);
    }

    public static void saveAddr(UserAddress userAddrModel) {
	GenericDAO<UserAddress> genericDao = (GenericDAO<UserAddress>)  CoreBeanFactory.getBean("genericDAO");
	genericDao.create(userAddrModel);
    }

    public static UserAddress saveOrUpdateAddr(UserAddress userAddrModel) {
	GenericDAO<UserAddress> genericDao = (GenericDAO<UserAddress>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.update(userAddrModel);
    }

    public static int updateUserIdsInAddr(List<Long> idsList,Long userId)
    {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	return  genericDao.executeUpdateNamedQuery(NamedQueryConstants.UPDATE_USER_IDS_ADDRESS, new String [] {"idsList","userId"}, idsList,userId);
	//return userModelList;
    }



    public static void saveAddrPref(AddressPreference addrPref) {
	GenericDAO<AddressPreference> genericDao = (GenericDAO<AddressPreference>)  CoreBeanFactory.getBean("genericDAO");
	genericDao.create(addrPref);
    }

    public static AddressPreference saveOrUpdateAddrPref(AddressPreference addrPref) {
	GenericDAO<AddressPreference> genericDao = (GenericDAO<AddressPreference>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.update(addrPref);
    }

    public static void saveCreditCard(UserCreditCard creditCard) {
	GenericDAO<UserCreditCard> genericDao = (GenericDAO<UserCreditCard>)  CoreBeanFactory.getBean("genericDAO");
	genericDao.create(creditCard);
    }

    public static UserCreditCard saveOrUpdateCreditCard(UserCreditCard creditCard) {
	GenericDAO<UserCreditCard> genericDao = (GenericDAO<UserCreditCard>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.update(creditCard);
    }

    public static int updateUserIdsInCreditCard(List<Long> idsList, Long userId)
    {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	return  genericDao.executeUpdateNamedQuery(NamedQueryConstants.UPDATE_USER_IDS_CC, new String [] {"idsList","userId"}, idsList,userId);
	//return userModelList;
    }


    public static boolean createRetailRoleForUser(Long userId) {
	GenericDAO<UserRoles> genericDAO = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
	Long roldId = null;
	List <UserRoles> roles = genericDAO.findByNamedQuery("UserRoles.getByRoleName", new String [] {"roleName"},  ApplicationConstants.ROLES.ROLE_RETAIL_USER.name());

	if(!roles.isEmpty()) {
	    UserRoles userRole = roles.get(0);
	    UserModel userModel = UserRepository.getByUserId(userId);
	    userModel.getUserRoles().add(userRole);
	    UserRepository.update(userModel);
	    return true;
	} 
	return false;
    }

    public static List<Object[]> findAddressByUserId(Long userId){
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Object[]> userAddressList =(List<Object[]>) genericDao.findByNamedQuery(NamedQueryConstants.FIND_ADDR_BY_USERNAME, new String [] {"userId"}, userId);
	return userAddressList;
    }

    public static List<Object[]> findAddressByUserIdAddressId(Long userId, Long addressId){
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Object[]> userAddressList =(List<Object[]>) genericDao.findByNamedQueryPagination(NamedQueryConstants.FIND_ADDR_BY_USERID_ADDR_ID,0,1, new String [] {"userId", "addressId"}, userId,addressId);
	return userAddressList;
    }

    public static List<Long> findAddressIdsByUserId(Long userId){
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Long> userAddressIdsList =(List<Long>) genericDao.findByNamedQuery(NamedQueryConstants.FIND_ADDR_IDS_BY_USER_ID, new String [] {"userId"}, userId);
	return userAddressIdsList;
    }

    public static Long findAddressPrefIdsByAddrId(Long addressId){
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Long> userAddressIdsList =(List<Long>) genericDao.findByNamedQuery(NamedQueryConstants.FIND_ADDR_PREF_IDS_BY_ADDR_ID, new String [] {"addressId"}, addressId);
	if(!userAddressIdsList.isEmpty())
	    return userAddressIdsList.get(0);
	return null;
    }
    /*public static List<Object[]> validateAddressByUserId(Long userId){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<Object[]> userAddressList =(List<Object[]>) genericDao.findByNamedQuery(NamedQueryConstants.VALIDATE_ADDR_BY_USERNAME, new String [] {"userId"}, userId);
		return userAddressList;
	}

	public static List<Object[]> validateCCByUserId(Long userId){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<Object[]> userAddressList =(List<Object[]>) genericDao.findByNamedQuery(NamedQueryConstants.VALIDATE_CC_BY_USERNAME, new String [] {"userId"}, userId);
		return userAddressList;
	}*/

    public static List<Object[]> findCCByUserId(Long userId){
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Object[]> userAddressList =(List<Object[]>) genericDao.findByNamedQuery(NamedQueryConstants.FIND_CC_BY_USERNAME, new String [] {"userId"}, userId);
	return userAddressList;
    }

    public static List<UserCreditCard> findCCListByUserId(Long userId){
	GenericDAO<UserCreditCard> genericDao = (GenericDAO<UserCreditCard>)  CoreBeanFactory.getBean("genericDAO");
	List<UserCreditCard> userCCList =(List<UserCreditCard>) genericDao.findByNamedQuery(NamedQueryConstants.FIND_CC_LIST_BY_USERID, new String [] {"userId"}, userId);
	return userCCList;
    }

    public static List<Long> findCCIdsByUserId(Long userId){
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Long> userAddressIdsList =(List<Long>) genericDao.findByNamedQuery(NamedQueryConstants.FIND_CC_IDS_BY_USER_ID, new String [] {"userId"}, userId);
	return userAddressIdsList;
    }

    public static List<Object[]> findCCByUserIdCCId(Long userId,
	    Long creditcardId) {
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<Object[]> userCCList =(List<Object[]>) genericDao.findByNamedQueryPagination(NamedQueryConstants.FIND_CC_BY_USER_ID_CC_ID,0,1, new String [] {"userId", "creditCardId"}, userId,creditcardId);
	return userCCList;
    }

    public static List<UserModel> findAllUsersByIds(List<Long> idsList) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> userModelList = genericDao.findByNamedQuery(NamedQueryConstants.FIND_ALL_USERS_BY_IDS, new String [] {"idsList"}, idsList);
	return userModelList;

    }

    public static List<UserModel> modifyStatusOfAllUsers(List<Long> idsList, boolean status) {

	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	int temp = genericDao.executeUpdateNamedQuery(NamedQueryConstants.MODIFY_STATUS_BULK, new String [] {"idsList","status"}, idsList, status);
	return null;	
    }

    public static List<UserModel> bulkdeleteUsers(List<Long> idsList) {

	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	int temp = genericDao.executeUpdateNamedQuery(NamedQueryConstants.BULK_DELETE_USERS, new String [] {"idsList"}, idsList);
	return null;	
    }

    public static UserModel findUserById(Long id) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	UserModel userModel = genericDao.get(UserModel.class, id);
	return userModel;
    }

    /*public static UserEmailPreferences saveOrUpdateEmailPref(UserEmailPreferences userEmailPreferences) {
		GenericDAO<UserEmailPreferences> genericDao = (GenericDAO<UserEmailPreferences>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(userEmailPreferences);
	}*/

    public static List<UserModel> findUsersNewsLetterResponsys() {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	return genericDao.findByNamedQuery(NamedQueryConstants.FIND_USER_NEWSLETTER_RESPONSYS);
    }

    public static UserModel getByActivationCode(String userActivationCode) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> userModelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_USER_BY_ACTIVATION_CODE, new String [] {"userActivationCode"}, userActivationCode);
	if(userModelList!=null && !userModelList.isEmpty())
	    return userModelList.get(0);
	else
	    return null;
    }

    public static UserCreditCard findByUserIdBraintreeUid(Long userId,String braintreeUid) {
	GenericDAO<UserCreditCard> genericDao = (GenericDAO<UserCreditCard>)  CoreBeanFactory.getBean("genericDAO");
	List<UserCreditCard> userCreditCardList =  genericDao.findByNamedQuery(NamedQueryConstants.FIND_BY_USERID_BRAINTREEID,new String [] {"userId", "braintreeUid"}, userId,braintreeUid);
	if(userCreditCardList!=null && !userCreditCardList.isEmpty())
	    return userCreditCardList.get(0);
	else
	    return null;
    }

    public static UserCreditCard findDefaultCreditCard(Long userId){

	GenericDAO<UserCreditCard> genericDao = (GenericDAO<UserCreditCard>)  CoreBeanFactory.getBean("genericDAO");
	List<UserCreditCard> userCreditCardList =  genericDao.findByNamedQuery(NamedQueryConstants.FIND_BY_USERID_DEFAULT_CC,new String [] {"userId"}, userId);
	UserCreditCard defaultCreditCard = null;
	if(userCreditCardList!=null && !userCreditCardList.isEmpty())
	    defaultCreditCard = userCreditCardList.get(0);
	return defaultCreditCard;

    }

    public static UserModel findUserByUserId(Long userId) {
	GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
	List<UserModel> userModelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_USER_BY_ID, new String [] {"id"}, userId);
	if(userModelList!=null && !userModelList.isEmpty())
	    return userModelList.get(0);
	else
	    return null;
    }

    public static UserAddress findDefaultAddress(Long userId, String addressType) {
	GenericDAO<UserAddress> genericDao = (GenericDAO<UserAddress>)  CoreBeanFactory.getBean("genericDAO");
	List<UserAddress> userAddressModelList = genericDao.findByNamedQuery(NamedQueryConstants.FIND_DEFAULT_ADDRESS, new String [] {"userId","addressType"}, userId, addressType);
	if(userAddressModelList!=null && !userAddressModelList.isEmpty())
	    return userAddressModelList.get(0);
	else
	    return null;
    }

    public static List getDepenedentCCList(Long userId, List<Long> addressList){
	GenericDAO<UserCreditCard> genericDao = (GenericDAO<UserCreditCard>)  CoreBeanFactory.getBean("genericDAO");
	List<UserCreditCard> userAddressModelList = genericDao.findByNamedQuery(NamedQueryConstants.FIND_CC_LIST_BY_USERID_ADDRESSID, new String [] {"userId","addressList"}, userId, addressList);
	return userAddressModelList;
    }

}
