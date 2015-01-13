package com.wineaccess.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.AddressPreference;
import com.wineaccess.data.model.user.UserAddress;
import com.wineaccess.data.model.user.UserCreditCard;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.service.user.preference.NewsletterList;
import com.wineaccess.service.user.preference.UserPreferenceAdapterHelper;
import com.wineaccess.service.user.preference.UserPreferenceVO;


public class UserManagementAdapter extends WineaccessBaseTask {	

	private static Log logger = LogFactory.getLog(UserManagementAdapter.class);
	private static final String USER_ALREADY_EXISTS = "A user already exists";
	Map<String,Boolean> dependentFieldsMap = new HashMap<String,Boolean>();

	public UserManagementAdapter(String taskName,TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}



	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(pContext.getRequestId());
		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");
		UserModel userModel = new UserModel();
		List<UserAddress> userAddrModelList = new ArrayList<UserAddress>();
		List<UserCreditCard> userCreditCardList = new ArrayList<UserCreditCard>();
		List<NewsletterList> userNewsletterList = new ArrayList<NewsletterList>();
		UserModel userModelFromDB= null;
		Map <String, Object> output = new HashMap<String, Object>();
		UserManagementPO userManagementPO = (UserManagementPO) inputParam.get("UserManagementPO");
		if(operationNameEnum.toString().equalsIgnoreCase("ADD") || operationNameEnum.toString().equalsIgnoreCase("UPDATE")){
			userModel = UserManagementHelper.populateUserModelFromPO(inputParam);
			userAddrModelList = UserManagementHelper.populateAddrModelFromPO(inputParam);
			userCreditCardList = UserManagementHelper.populateCCModelListFromPO(inputParam);
			if(operationNameEnum.toString().equalsIgnoreCase("ADD") )
				userModelFromDB= UserRepository.getByUserName(userModel.getEmail());
			else
				userModelFromDB= UserRepository.getByUserIdDeleted(userModel.getId());

			userNewsletterList = userManagementPO.getNewsletterList();
			if(userModelFromDB!=null && BooleanUtils.isTrue(userModelFromDB.isDeleted())){
				if( BooleanUtils.isNotTrue(userManagementPO.getIsOverrideDelEntry()))
				{

					UserManagementVO userManagementVO = new UserManagementVO(userManagementPO.getFirstName(),userManagementPO.getLastName(),USER_ALREADY_EXISTS,String.valueOf(userModelFromDB.getId()), userManagementPO.getEmail()) ;
					userManagementVO.setExistsButDeleted(true);
					Response response = new com.wineaccess.response.SuccessResponse(userManagementVO, 200);

					output.put("FINAL-RESPONSE", response);
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}
				else
					userModel.setId(userModelFromDB.getId()); 
				userModel.setDeleted(false);
			}
		}




		switch (operationNameEnum) {
		case ADD:

			if(userModelFromDB != null &&  BooleanUtils.isNotTrue(userManagementPO.getIsOverrideDelEntry())){		
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.EMAIL_ID_EXISTS, SystemErrorCode.EMAIL_ID_EXISTS_TXT, 200));	
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return ;
			}

			else
			{
				userModel.setUserActivationCode(ApplicationUtils.genActivationCode(userModel.getEmail()));
				userModel.setResetCode(ApplicationUtils.genActivationCode(userModel.getEmail()));
				//String pwd = ApplicationUtils.genPasswordCreateUser();
				//userModel.setPassword(ApplicationUtils.getSHAHex(pwd));
				if(userModel.getId() == null || userModelFromDB == null){
					UserRepository.save(userModel);
				}else{
					UserRepository.update(userModel);
				}
				ApplicationUtils.cleanLucenceIndex();
				String activationURL = ApplicationUtils.generateURL(userModel,"activationURL",userManagementPO.getUrl());
				//userModel.setPassword(pwd);

				ApplicationUtils.sendMailWithURL(activationURL, userModel.getEmail(),"activationURLWithPassword",userModel);
				UserRepository.createRetailRoleForUser(userModel.getId());
				if(userAddrModelList !=null && !userAddrModelList.isEmpty()) {

					for(UserAddress userAddrModel:userAddrModelList){
						AddressPreference addressPreference = userAddrModel.getAddressPreference();
						userAddrModel.setUserId(userModel.getId());
						if(addressPreference == null)
							addressPreference = new AddressPreference();
						UserRepository.saveAddrPref(addressPreference);							
						userAddrModel.setAddressPreferenceId(addressPreference.getId());
						UserRepository.saveAddr(userAddrModel);

					}
				}
				if(userCreditCardList !=null && !userCreditCardList.isEmpty()) {
					for(UserCreditCard userCCModel:userCreditCardList){
						UserAddress userAddrModel = userCCModel.getUserAddress();
						userAddrModel.setUserId(userModel.getId());

						AddressPreference addressPreference = userAddrModel.getAddressPreference();

						if(addressPreference == null)
							addressPreference = new AddressPreference();
						UserRepository.saveAddrPref(addressPreference);							
						userAddrModel.setAddressPreferenceId(addressPreference.getId());

						UserRepository.saveAddr(userAddrModel);
						userCCModel.setUserId(userModel.getId());
						userCCModel.setUserAddressId(userAddrModel.getId());
						UserRepository.saveCreditCard(userCCModel);

					}
				}
				List<UserPreferenceVO> userPreferenceVOList = new ArrayList<UserPreferenceVO>();
				if(userNewsletterList !=null && !userNewsletterList.isEmpty()){
					userPreferenceVOList = UserPreferenceAdapterHelper.subscribeUserToNewsletter(userModel,userManagementPO);
				}

				UserManagementVO userManagementVO = new UserManagementVO(userModel.getFirstName(), userModel.getLastName(), "created successfully",String.valueOf(userModel.getId()), userModel.getEmail(),userPreferenceVOList) ;
				Response response = new com.wineaccess.response.SuccessResponse(userManagementVO, 200);

				output.put("FINAL-RESPONSE", response);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;

			}

		case UPDATE:

			if(userModelFromDB == null){

				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_USER_TO_UPDATE, SystemErrorCode.NO_USER_TO_UPDATE_TXT, 200));	
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;
			}
			else
			{
				UserModel userModelEmail= UserRepository.getByUserName(userManagementPO.getEmail());
				if(userModelEmail != null && userModelEmail.getId() != userModelFromDB.getId()){
					output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.EMAIL_ID_EXISTS, SystemErrorCode.EMAIL_ID_EXISTS_TXT, 200));	
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return ;
				}

				if(userAddrModelList !=null && !userAddrModelList.isEmpty()) {

					for(UserAddress userAddrModel:userAddrModelList){
						AddressPreference addressPreference = userAddrModel.getAddressPreference();
						if(addressPreference == null)
							addressPreference = new AddressPreference();
						addressPreference = UserRepository.saveOrUpdateAddrPref(addressPreference);
						userAddrModel.setUserId(userModel.getId());
						userAddrModel.setAddressPreferenceId(addressPreference.getId());
						userAddrModel = UserRepository.saveOrUpdateAddr(userAddrModel);
						/*}*/
					}
				}
				if(userCreditCardList !=null && !userCreditCardList.isEmpty()) {
					for(UserCreditCard userCCModel:userCreditCardList){
						UserAddress userAddrModel = userCCModel.getUserAddress();
						userAddrModel.setUserId(userModel.getId());

						AddressPreference addressPreference = userAddrModel.getAddressPreference();

						if(addressPreference == null)
							addressPreference = new AddressPreference();
						UserRepository.saveOrUpdateAddrPref(addressPreference);							
						userAddrModel.setAddressPreferenceId(addressPreference.getId());
						userAddrModel = UserRepository.saveOrUpdateAddr(userAddrModel);
						userCCModel.setUserId(userModel.getId());
						userCCModel.setUserAddressId(userAddrModel.getId());
						UserRepository.saveOrUpdateCreditCard(userCCModel);

					}
				}
				userModel.setPassword(userModelFromDB.getPassword());
				userModel.setRegistered(userModelFromDB.getRegistered());
				UserManagementPO userManagePO = (UserManagementPO) inputParam.get("UserManagementPO");
				if(!userModelFromDB.getEmail().equals(userManagePO.getEmail())){
					userModel.setUserActivationCode(ApplicationUtils.genActivationCode(userModel.getEmail()));
					userModel.setResetCode(ApplicationUtils.genActivationCode(userModel.getEmail()));
					userModel.setRegistered(false);
					userModel.setPassword(null);
					userModel = UserRepository.update(userModel);
					
					String activationURL = ApplicationUtils.generateURL(userModel,"activationURL",userManagementPO.getUrl());
					//userModel.setPassword(pwd);

					ApplicationUtils.sendMailWithURL(activationURL, userManagePO.getEmail(),"activationURLWithPassword",userModel);
					
					/*String activationURL = ApplicationUtils.generateURL(userModel,"activationURL",userManagePO.getUrl());
					ApplicationUtils.sendMailWithURL(activationURL, userModel.getEmail(),"activationURL", userModel);*/
				}else{
					userModel = UserRepository.update(userModel);
				}
				//userModel = UserRepository.update(userModel);
				List<UserPreferenceVO> userPreferenceVOList = new ArrayList<UserPreferenceVO>();
				if(userNewsletterList !=null && !userNewsletterList.isEmpty()){
					userPreferenceVOList = UserPreferenceAdapterHelper.subscribeUserToNewsletter(userModel,userManagementPO);
				}

				ApplicationUtils.cleanLucenceIndex();

				UserManagementVO userManagementVO = new UserManagementVO(userModel.getFirstName(), userModel.getLastName(), "updated successfully",String.valueOf(userModel.getId()), userModel.getEmail(),userPreferenceVOList) ;
				Response response = new com.wineaccess.response.SuccessResponse(userManagementVO, 200);

				output.put("FINAL-RESPONSE", response);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;

			}



		case ENABLEUSERS:

			List<Long> enableUsersList = (List<Long>)inputParam.get("EnableUsersList");	

			for (Object obj : enableUsersList) {
				if (obj == null) {
					output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_USERID_VALUE, SystemErrorCode.INVALID_USERID_VALUE_TEXT, 200));
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}
			}

			Boolean isforceDelete = (Boolean)inputParam.get("isforceDelete");
			dependentFieldsMap.put("isDeleted", true);
			dependentFieldsMap.put("registered", false);

			UsersDescriptionListVO userDescriptionListVO = UserManagementHelper.generateModifyStatusMultipleCondition(enableUsersList,"isEnabled", true,isforceDelete,false,null,dependentFieldsMap);

			Response enableUsersResponse = new com.wineaccess.response.SuccessResponse(userDescriptionListVO, 200);
			output.put("FINAL-RESPONSE", enableUsersResponse);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;

		case DISABLEUSERS:

			List<Long> disableUsersList = (List<Long>)inputParam.get("DisableUsersList");	

			for (Object obj : disableUsersList) {
				if (obj == null) {
					output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_USERID_VALUE, SystemErrorCode.INVALID_USERID_VALUE_TEXT, 200));
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}
			}
			isforceDelete = (Boolean)inputParam.get("isforceDelete");
			UserModel currentUser = (UserModel)inputParam.get("currentUser");
			if(disableUsersList.contains(currentUser.getId())){
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_LOGIN_USER_DISABLE, SystemErrorCode.ERROR_LOGIN_USER_DISABLE_TXT, 200));	
			}else{
				UsersDescriptionListVO disableuserDescriptionListVO = UserManagementHelper.generateModifyStatusResponse(disableUsersList,"isEnabled", false,isforceDelete,true,null);

				Response disableUsersResponse = new com.wineaccess.response.SuccessResponse(disableuserDescriptionListVO, 200);
				output.put("FINAL-RESPONSE", disableUsersResponse);
			}


			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;


		case DELETEUSERS:

			List<Long> deleteUsersList = (List<Long>)inputParam.get("DeleteUsersList");		
			isforceDelete = (Boolean)inputParam.get("isforceDelete");
			currentUser = (UserModel)inputParam.get("currentUser");
			if(deleteUsersList.contains(currentUser.getId())){
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_LOGIN_USER_DELETED, SystemErrorCode.ERROR_LOGIN_USER_DELETED_TXT, 200));	
			}else{
				UsersDescriptionListVO deleteUserVO = UserManagementHelper.generateModifyStatusResponse(deleteUsersList,"isDeleted",true,isforceDelete,true,null);

				Response deleteUsersResponse = new com.wineaccess.response.SuccessResponse(deleteUserVO, 200);
				output.put("FINAL-RESPONSE", deleteUsersResponse);
			}
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;

		case RESET_PASSWORD:

			List<Long> resetUserIds = (List<Long>)inputParam.get("resetUserIds");	

			for (Object obj : resetUserIds) {
				if (obj == null) {
					output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_USERID_VALUE, SystemErrorCode.INVALID_USERID_VALUE_TEXT, 200));
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}
			}

			isforceDelete = (Boolean)inputParam.get("isforceDelete");
			String url = (String) inputParam.get(ReqParamConstants.URL);
			currentUser = (UserModel)inputParam.get("currentUser");
			if(resetUserIds.contains(currentUser.getId())){
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_LOGIN_USER_RESET_PASSWORD, SystemErrorCode.ERROR_LOGIN_USER_RESET_PASSWORD_TXT, 200));	
			}else{

				dependentFieldsMap.put("isDeleted", true);
				dependentFieldsMap.put("isEnabled", false);
				UsersDescriptionListVO userDescriptionVO = UserManagementHelper.generateModifyStatusMultipleCondition(resetUserIds,"registered", false,isforceDelete,false,url,dependentFieldsMap);


				Response resetMailResponse = new com.wineaccess.response.SuccessResponse(userDescriptionVO, 200);
				output.put("FINAL-RESPONSE", resetMailResponse);
			}
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;

		case DELETEADDRESS:

			DeleteComponentPO deleteComponentPO = (DeleteComponentPO)inputParam.get("DeleteComponentPO");	
			Long userId = Long.valueOf(deleteComponentPO.getUserId());
			List<Long> addressIdsListFromPO = deleteComponentPO.getDeleteIdsList();
			List<Long> listOfAddressOfUser = UserRepository.findAddressIdsByUserId(userId);
			List<Long> addrPrefList = new ArrayList<Long>();

			output = UserManagementHelper.validateToBeDeletedList(addressIdsListFromPO, listOfAddressOfUser, SystemErrorCode.INVALID_ADDRESS_ID, SystemErrorCode.INVALID_ADDRESS_ID_TXT);
			if(!output.isEmpty())
			{
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;
			}
			for(Long addressIds:addressIdsListFromPO)
			{
				addrPrefList.add(UserRepository.findAddressPrefIdsByAddrId(addressIds));
			}
			List list = UserRepository.getDepenedentCCList(userId,addressIdsListFromPO);

			if(list != null && !list.isEmpty()){
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_DEPENDENT_ADDRESS_ERROR, SystemErrorCode.USER_DEPENDENT_ADDRESS_ERROR_TEXT, 200));
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;
			}
			GenericDAO<UserAddress> genericDAOAddress = (GenericDAO<UserAddress>)  CoreBeanFactory.getBean("genericDAO");
			BulkDeleteModel<UserAddress> deletedAddress= genericDAOAddress.performBulkDelete(addressIdsListFromPO,UserAddress.class, "UserAddress",null,null,null,true);
			if(deletedAddress!=null && !deletedAddress.getDeletedList().isEmpty()){
				BulkDeleteModel<UserAddress> deletedAddressPref = genericDAOAddress.performBulkDelete(addrPrefList,AddressPreference.class, "AddressPreference",null,null,null,true);
				if(deletedAddressPref== null || deletedAddressPref.getDeletedList().isEmpty())
					logger.warn("Address deleted but address Pref didn't");
			}

			DeleteComponentVO deleteComponentVO = new DeleteComponentVO(addressIdsListFromPO,"Deleted address succefully"); 
			Response deleteAddressResponse = new com.wineaccess.response.SuccessResponse(deleteComponentVO, 200);
			output.put("FINAL-RESPONSE", deleteAddressResponse);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;

		case DELETECREDITCARD:

			DeleteComponentPO deleteComponentPOCC = (DeleteComponentPO)inputParam.get("DeleteComponentPO");	
			Long userIdCC =  Long.valueOf(deleteComponentPOCC.getUserId());
			Response deleteCCResponse = new FailureResponse();
			if(UserRepository.findUserById(userIdCC)!=null){
				List<Long> ccIdsListFromPO = deleteComponentPOCC.getDeleteIdsList();
				List<Long> listOfCCsOfUser = UserRepository.findCCIdsByUserId(userIdCC);


				output = UserManagementHelper.validateToBeDeletedList(ccIdsListFromPO, listOfCCsOfUser, SystemErrorCode.INVALID_CC_ID, SystemErrorCode.INVALID_CC_ID_TXT);
				if(!output.isEmpty())
				{
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}

				UserCreditCard defaultCreditCard = UserRepository.findDefaultCreditCard(userIdCC);
				if(ccIdsListFromPO.contains(defaultCreditCard.getId())){
					output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.DEFAULT_CREDIT_CARD_DELETE_ERROR, SystemErrorCode.DEFAULT_CREDIT_CARD_DELETE_ERROR_TEXT, 200));
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}

				GenericDAO<UserAddress> genericDAOCC = (GenericDAO<UserAddress>)  CoreBeanFactory.getBean("genericDAO");
				BulkDeleteModel<UserAddress> deletedCC= genericDAOCC.performBulkDelete(ccIdsListFromPO,UserCreditCard.class, "UserCreditCard",null,null,null,true);
				if(deletedCC!=null && !deletedCC.getDeletedList().isEmpty()){
					logger.warn("Some problem occured");
				}

				DeleteComponentVO deleteComponentVOCC = new DeleteComponentVO(ccIdsListFromPO,"Deleted credit card succefully"); 
				deleteCCResponse = new com.wineaccess.response.SuccessResponse(deleteComponentVOCC, 200);
			}
			else
				deleteCCResponse = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_NOT_EXIST, SystemErrorCode.USER_NOT_EXIST_TXT, 200);
			output.put("FINAL-RESPONSE", deleteCCResponse);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;


		case USERDETAIL:

			// changes in accordance to story WA-629
			//fetch user detail according to id

			Long id = (Long)inputParam.get("id");
			String isShowDeletedData = (String)(inputParam.get("isShowDeletedData"));
			UserModel userDetailModel = null;
			if(BooleanUtils.toBoolean(isShowDeletedData))
				userDetailModel = UserRepository.getByUserIdDeleted(id);
			else
				userDetailModel = UserRepository.getByUserId(id);
			if(userDetailModel == null){		
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS, SystemErrorCode.NO_RECORD_EXISTS_TXT, 200));	
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return ;
			}
			else
			{
				List<Object[]> listOfCCs = UserRepository.findCCByUserId(userDetailModel.getId());
				if(!listOfCCs.isEmpty()){
					for(Object[] obj:listOfCCs){
						UserCreditCard userCC = (UserCreditCard)obj[0];
						UserAddress userAddress = (UserAddress)obj[1];
						//addressIdToBeExcluded.add(userAddress.getId());
						userCC.setUserAddress(userAddress);
						userCreditCardList.add(userCC);
					}
				}
				if(userCreditCardList !=null && !userCreditCardList.isEmpty()) {
					for(UserCreditCard userCCModel:userCreditCardList){
						UserAddress userAddrModel = userCCModel.getUserAddress();
						userAddrModel.setUserId(userDetailModel.getId());
						userCCModel.setUserId(userDetailModel.getId());
						userCCModel.setUserAddressId(userAddrModel.getId());

					}
				}
				userAddrModelList = UserManagementHelper.populateAddressAndAddressPref(userDetailModel.getId());
				List<UserPreferenceVO> userPreferenceVOList = UserPreferenceAdapterHelper.subscriptionDetailsById(userDetailModel.getId());
				UserDetailVO userDetailVO	= UserManagementHelper.populateVOFromDO(userDetailModel,userAddrModelList,userCreditCardList,userPreferenceVOList);
				Response userDetailResponse = new com.wineaccess.response.SuccessResponse(userDetailVO, 200);
				output.put("FINAL-RESPONSE", userDetailResponse);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;

			}


		case MERGEUSER:

			MergeUserPO mergeUserPO = (MergeUserPO)inputParam.get("MergeUserPO");
			Long toBeMergedUser = mergeUserPO.getToBeMergedUser();
			Long userInWhichToBeMerged = mergeUserPO.getUserInWhichTobeMerged();
			UserModel toBeMergedUserModel = UserRepository.getByUserIdDeleted(toBeMergedUser);
			UserModel userInWhichToBeMergedModel = UserRepository.getByUserIdDeleted(userInWhichToBeMerged);
			currentUser = (UserModel)inputParam.get("currentUser");
			if(toBeMergedUser == (currentUser.getId())){
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.ERROR_LOGIN_MERGE_USER, SystemErrorCode.ERROR_LOGIN_MERGE_USER_TXT, 200));	
			}else{
				output = UserManagementHelper.validateMergeUserIds( toBeMergedUserModel,userInWhichToBeMergedModel);
				if(!output.isEmpty())
				{
					getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
					return;
				}
				List<Long> addressIdsList = UserRepository.findAddressIdsByUserId(toBeMergedUser);
				List<Long> creditCardLists = UserRepository.findCCIdsByUserId(toBeMergedUser);

				if(!addressIdsList.isEmpty())
					System.out.println( UserRepository.updateUserIdsInAddr(addressIdsList,userInWhichToBeMerged));	

				if(!creditCardLists.isEmpty())
					System.out.println( UserRepository.updateUserIdsInCreditCard(creditCardLists,userInWhichToBeMerged));
				GenericDAO<UserModel> genericDAO = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
				List<Long> idsList = new ArrayList<Long>();
				idsList.add(toBeMergedUser);
				UserManagementHelper.generateModifyStatusResponse(idsList,"isDeleted",true,true,true,null);
				//genericDAO.performBulkDelete(idsList, UserModel.class, "UserModel","isDeleted", true,"isDeleted",true);
				ApplicationUtils.cleanLucenceIndex();
				MergeUserVO mergeUserVO = new MergeUserVO(toBeMergedUser, userInWhichToBeMerged, "user merged successfully");
				Response mergeUserResponse = new com.wineaccess.response.SuccessResponse(mergeUserVO, 200);

				output.put("FINAL-RESPONSE", mergeUserResponse);
			}
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;



		case CLONE:
			CloneUserPO cloneUserPO = (CloneUserPO) inputParam.get("CloneUserPO");
			//JAXB.marshal(cloneUserPO, System.out);
			String toEmailField = cloneUserPO.getToEmailId();
			String fromEmailField = cloneUserPO.getFromEmailId();
			userModelFromDB= UserRepository.getByUserName(fromEmailField);
			List<Long> addressIdToBeExcluded = new ArrayList<Long>();
			if(UserRepository.getByUserName(toEmailField)!=null){				
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.EMAIL_ID_EXISTS, SystemErrorCode.EMAIL_ID_EXISTS_TXT, 200));	
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;

			}
			if(userModelFromDB == null)
			{
				output.put("FINAL-RESPONSE", ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_TO_CLONE, SystemErrorCode.NO_RECORD_TO_CLONE_TXT, 200));
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;
			}
			else
			{
				userModel = new UserModel();
				Long tobeClonedId = userModelFromDB.getId();
				userModel = userModelFromDB;
				userModel.setEmail(toEmailField);
				userModel.setId(null);
				UserRepository.save(userModel);
				UserRepository.createRetailRoleForUser(userModel.getId());

				List<Object[]> listOfCCs = UserRepository.findCCByUserId(tobeClonedId);
				if(!listOfCCs.isEmpty()){
					for(Object[] obj:listOfCCs){
						UserCreditCard userCC = (UserCreditCard)obj[0];
						UserAddress userAddress = (UserAddress)obj[1];
						addressIdToBeExcluded.add(userAddress.getId());
						userCC.setUserAddress(userAddress);
						userCreditCardList.add(userCC);
					}
				}
				if(userCreditCardList !=null && !userCreditCardList.isEmpty()) {
					for(UserCreditCard userCCModel:userCreditCardList){
						UserAddress userAddrModel = userCCModel.getUserAddress();
						userAddrModel.getId();
						userAddrModel.setId(null);
						userAddrModel.setUserId(userModel.getId());
						UserRepository.saveAddr(userAddrModel);
						userCCModel.setId(null);
						userCCModel.setUserId(userModel.getId());
						userCCModel.setUserAddressId(userAddrModel.getId());
						UserRepository.saveCreditCard(userCCModel);

					}
				}
				List<Object[]> listOfAddress = UserRepository.findAddressByUserId(tobeClonedId);
				if(listOfAddress!=null && !listOfAddress.isEmpty()){
					for(Object[] obj:listOfAddress){
						UserAddress usrAddress = (UserAddress)obj[0];
						usrAddress.setAddressPreference((AddressPreference)obj[1]);

						userAddrModelList.add(usrAddress);
					}
				}

				if(userAddrModelList !=null && !userAddrModelList.isEmpty()) {

					for(UserAddress userAddrModel:userAddrModelList){
						AddressPreference addressPreference = userAddrModel.getAddressPreference();
						if(addressPreference!=null)
						{
							addressPreference.setId(null);
							UserRepository.saveAddrPref(addressPreference);
							if(!addressIdToBeExcluded.contains(userAddrModel.getId())){
								userAddrModel.setId(null);
								userAddrModel.setUserId(userModel.getId());
								userAddrModel.setAddressPreferenceId(addressPreference.getId());
								UserRepository.saveAddr(userAddrModel);
							}
						}
					}
				}
			}
			UserManagementVO userManagementVO = new UserManagementVO(String.valueOf(userModel.getId()), userModel.getEmail(),userModel.getFirstName(), userModel.getLastName(), "cloned successfully") ; ;
			Response response = new com.wineaccess.response.SuccessResponse(userManagementVO, 200);

			output.put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);

			return;



		case ADD_ADDRESS:
			Map <String, Object> outputAddAddress = new HashMap<String, Object>();			
			UserAddressModelAtomicPO userAddressModelPO = (UserAddressModelAtomicPO) inputParam.get("AddAddressPO");			
			outputAddAddress = UserManagementHelper.generateAddAddressResponse(userAddressModelPO);	
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputAddAddress);
			return;

		case EDIT_ADDRESS:
			Map <String, Object> outputEditAddress = new HashMap<String, Object>();			
			UserEditAddressModelAtomicPO userAddressEditModelPO = (UserEditAddressModelAtomicPO) inputParam.get("EditAddressPO");			
			outputEditAddress = UserManagementHelper.generateUpdateAddressResponse(userAddressEditModelPO);	
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputEditAddress);
			return;


		case ADDRESS_DETAIL:
			Map <String, Object> outputAddressDetail = new HashMap<String, Object>();			
			AddressDetailAtomicPO addressDetailAtomicPO = (AddressDetailAtomicPO) inputParam.get("AddressDetailAtomicPO");			
			Long userIdOfAddress = addressDetailAtomicPO.getUserId();
			Long addressId = addressDetailAtomicPO.getAddressId();
			outputAddressDetail = UserManagementHelper.generateAddressDetailResponse(userIdOfAddress, addressId);			
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputAddressDetail);
			return;


		case ADD_CREDIT_CARD:
			Map <String, Object> outputAddCreditCard = new HashMap<String, Object>();			
			CreditCardModelAtomicPO creditCardPO = (CreditCardModelAtomicPO) inputParam.get("AddCreditCardPO");			

			outputAddCreditCard = UserManagementHelper.generateAddCreditCardResponse(creditCardPO);	
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputAddCreditCard);
			return;

		case EDIT_CREDIT_CARD:
			Map <String, Object> outputEditCreditCard = new HashMap<String, Object>();			
			CreditCardEditModelAtomicPO editCreditCardPO = (CreditCardEditModelAtomicPO) inputParam.get("EditCreditCardPO");			
			outputEditCreditCard = UserManagementHelper.generateUpdateCreditCardResponse(editCreditCardPO);	
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputEditCreditCard);
			return;


		case CREDIT_CARD_DETAIL:
			Map <String, Object> outputCreditCardDetail = new HashMap<String, Object>();			
			CreditCardDetailAtomicPO creditCardDetail = (CreditCardDetailAtomicPO) inputParam.get("CreditCardDetailPO");			
			Long userIdOfCC = creditCardDetail.getUserId();
			Long creditcardId = creditCardDetail.getCreditCardId();
			outputCreditCardDetail = UserManagementHelper.generateCreditCardDetailResponse(userIdOfCC, creditcardId);			
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputCreditCardDetail);
			return;

		case CREDITCARDLIST:
			Map <String, Object> outputCreditCardList = new HashMap<String, Object>();			
			Long userIdForCreditCard = (Long)inputParam.get("id");				
			outputCreditCardList = UserManagementHelper.generateCreditCardListForUser(userIdForCreditCard);	
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputCreditCardList);
			break;


		case ADDRESSLIST:
			Map <String, Object> outputAddressesList = new HashMap<String, Object>();			
			Long userIdForaddress = (Long)inputParam.get("id");				
			outputAddressesList = UserManagementHelper.generateAddressesListForUser(userIdForaddress);	
			getDataRepositoryManager().addOutput(pContext.getRequestId(), outputAddressesList);
			break;

		}

	}


}
