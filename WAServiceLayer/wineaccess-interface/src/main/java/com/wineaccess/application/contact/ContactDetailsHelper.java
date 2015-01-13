package com.wineaccess.application.contact;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.repositories.WineryImporterContactRepository;
import com.wineaccess.importer.ImporterRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;
import com.wineaccess.winery.WineryRepository;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class ContactDetailsHelper {

	private static Log logger = LogFactory.getLog(ContactDetailsHelper.class);


	/**
	 * 
	 * @return -adds the winery contact details to database. If everything works fine then returns a success message: "Winery Contact Detail added successfully."
	 */
	public static  Map<String, Object> addContact(ContactDetailsPO contactDetailsPO){

		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;

		WineryImporterContacts wineryImporterContacts = new WineryImporterContacts();
		try {

			switch(returnCase(contactDetailsPO)){
			case 1:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_103_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_103_ERROR_TEXT, 200);
				break;
			case 2:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_104_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_104_ERROR_TEXT, 200);
				break;
			case 3: 
				response = getWineryAddResponse(contactDetailsPO, wineryImporterContacts);
				break;
			case 4:
				response = getImporterAddResponse(contactDetailsPO, wineryImporterContacts);
				break;
			default:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_102_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_102_ERROR_TEXT, 200);
				break;
			}

		} catch (Exception e) {
			logger.error("Error in creating new Winery contact. "+e);
			if(e instanceof ConstraintViolationException)
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_101_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_101_ERROR_TEXT, 200);
			else
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_102_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_102_ERROR_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);
		return output;
	}



	/**
	 * 
	 * @return -updates the winery contact details to database. If everything works fine then returns a success message: "Winery Contact Detail updated successfully."
	 */
	public static  Map<String, Object> updateContact(EditContactDetailsPO editContactDetailsPO){

		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		WineryImporterContacts wineryImporterContacts = WineryImporterContactRepository.getContactById(editContactDetailsPO.getContactId());
		try {

			switch(returnCase(editContactDetailsPO, wineryImporterContacts)){
			case 1:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_103_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_103_ERROR_TEXT, 200);
				break;
			case 2:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_104_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_104_ERROR_TEXT, 200);
				break;
			case 3: 
				response = getWineryUpdateResponse(editContactDetailsPO, wineryImporterContacts);
				break;
			case 4:
				response = getImporterUpdateResponse(editContactDetailsPO, wineryImporterContacts);
				break;
			case 5:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR_TEXT, 200);
				break;
			default:
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_102_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_102_ERROR_TEXT, 200);
				break;
			}
		} catch (Exception e) {
			logger.error("Error in creating new Winery contact. "+e);
			if(e instanceof ConstraintViolationException)
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_101_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_101_ERROR_TEXT, 200);
			else
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_106_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_106_ERROR_TEXT, 200);
		}


		output.put("FINAL-RESPONSE", response);
		return output;
	}

	/**
	 * @return -deletes the contact details in the database. If everything works fine then returns a success message: "Importer Contact Deleted successfully."
	 */
	public static  Map<String, Object> deleteContact(DeleteContactDetailsPO deleteContactDetailsPO){

		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;

		try {
			if(null != deleteContactDetailsPO.getContacts()){

				List<Serializable> ids = new ArrayList<Serializable>();
				for(Long id: deleteContactDetailsPO.getContacts()){
					ids.add(id);
				}

				ContactDetailsDeleteVO contactDetailsDeleteVO = new ContactDetailsDeleteVO();

				BulkDeleteModel<WineryImporterContacts> contactModel = WineryImporterContactRepository.delete(ids,deleteContactDetailsPO.getIsForceDelete());
				List<WineryImporterContacts> canBeDeletedUserList = contactModel.getDeletedList();
				for(WineryImporterContacts wineryImporterContacts : canBeDeletedUserList){
					WineryContactVO wineryContactVO = new WineryContactVO();
					wineryContactVO.setContactId(wineryImporterContacts.getId());
					if(wineryImporterContacts.getContactType() != null){
						Map<String,String> contactType = new HashMap<String, String>();
						contactType.put("id", Long.toString(wineryImporterContacts.getContactType().getId()));
						contactType.put("name", wineryImporterContacts.getContactType().getName());
						wineryContactVO.setContactType(contactType);
					}
					wineryContactVO.setEmail(wineryImporterContacts.getEmail());
					wineryContactVO.setName(wineryImporterContacts.getName());
					wineryContactVO.setPhone(wineryImporterContacts.getPhone());

					contactDetailsDeleteVO.getSuccessList().add(wineryContactVO);	
				}


				List<WineryImporterContacts> canNotBeDeletedUserList = contactModel.getNotDeletedList();
				for(WineryImporterContacts wineryImporterContacts : canNotBeDeletedUserList){
					WineryContactVO wineryContactVO = new WineryContactVO();
					wineryContactVO.setContactId(wineryImporterContacts.getId());
					if(wineryImporterContacts.getContactType() != null){
						Map<String,String> contactType = new HashMap<String, String>();
						contactType.put("id", Long.toString(wineryImporterContacts.getContactType().getId()));
						contactType.put("name", wineryImporterContacts.getContactType().getName());
						wineryContactVO.setContactType(contactType);
					}
					wineryContactVO.setEmail(wineryImporterContacts.getEmail());
					wineryContactVO.setName(wineryImporterContacts.getName());
					wineryContactVO.setPhone(wineryImporterContacts.getPhone());

					contactDetailsDeleteVO.getFailureList().add(wineryContactVO);
				}

				List<Long> nonExistingUserList = (List<Long>) contactModel.getNotExistsList();

				contactDetailsDeleteVO.getNonExisting().addAll(nonExistingUserList);
				response = new com.wineaccess.response.SuccessResponse(contactDetailsDeleteVO, 200);
			}
			else {
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR_TEXT, 200);
			}
		} catch (Exception e) {
			logger.error("Error in creating new Winery contact. "+e);
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_107_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_107_ERROR_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);
		return output;
	}

	/**
	 * @return -deletes the contact details in the database. If everything works fine then returns a success message: "Importer Contact Deleted successfully."
	 */
	public static  Map<String, Object> listContacts(ContactsDetailListingPO contactListPO){

		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;

		//Code for viewing winery contacts
		try {
			List<WineryImporterContacts> wineryImporterContacts = WineryImporterContactRepository.getContactByWineryId(contactListPO.getWineryId(),0,1);
			WineryContactDetailsList wineryContactDetailsList = new WineryContactDetailsList();

			for(WineryImporterContacts wineryImporterContact : wineryImporterContacts){
				WineryContactVO wineryContactVO = new WineryContactVO();
				wineryContactVO.setContactId(wineryImporterContact.getId());
				if(wineryImporterContact.getContactType() != null){
					Map<String,String> contactType = new HashMap<String, String>();
					contactType.put("id", Long.toString(wineryImporterContact.getContactType().getId()));
					contactType.put("name", wineryImporterContact.getContactType().getName());
					wineryContactVO.setContactType(contactType);
				}
				wineryContactVO.setEmail(wineryImporterContact.getEmail());
				wineryContactVO.setName(wineryImporterContact.getName());
				wineryContactVO.setPhone(wineryImporterContact.getPhone());
				wineryContactVO.setIsDefault(wineryImporterContact.getIsDefault());

				wineryContactDetailsList.getWineryContactVOList().add(wineryContactVO);
			}

			response = new com.wineaccess.response.SuccessResponse(wineryContactDetailsList, 200);

		} catch (Exception e) {
			logger.error("Error in creating new Winery contact. "+e);
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR, "New winery contact updated successfully.", 200);
		}


		//Code for viewing importer contacts
		/*try {
		List<WineryImporterContacts> wineryImporterContacts = WineryImporterContactRepository.getContactByImporterId(importerDetailsPO.getImporterId());
		WineryContactDetailsList wineryContactDetailsList = new WineryContactDetailsList();

		for(WineryImporterContacts wineryImporterContact : wineryImporterContacts){
			WineryContactVO wineryContactVO = new WineryContactVO();
			wineryContactVO.setContactId(wineryImporterContact.getId());
			if(null != wineryImporterContact.getName())
				wineryContactVO.setContactType(wineryImporterContact.getName());
			else
				wineryContactVO.setContactType("");
			wineryContactVO.setEmail(wineryImporterContact.getEmail());
			wineryContactVO.setName(wineryImporterContact.getName());
			wineryContactVO.setPhone(wineryImporterContact.getPhone());

			wineryContactDetailsList.getWineryContactVOList().add(wineryContactVO);
		}

		response = new com.wineaccess.response.SuccessResponse(wineryContactDetailsList, 200);

	} catch (Exception e) {
		logger.error("Error in creating new Winery contact. "+e);
		response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR, "New winery contact updated successfully.", 200);
	}*/

		output.put("FINAL-RESPONSE", response);
		return output;
	}



	/**
	 * @return -deletes the contact details in the database. If everything works fine then returns a success message: "Importer Contact Deleted successfully."
	 */
	public static  Map<String, Object> viewContactDetail(ViewContactDetailsPO viewContactDetailsPO){

		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;

		try {
			WineryImporterContacts wineryImporterContacts = WineryImporterContactRepository.getContactById(viewContactDetailsPO.getContactId());

			if(null != wineryImporterContacts){
				WineryContactVO wineryContactVO = new WineryContactVO();
				wineryContactVO.setContactId(wineryImporterContacts.getId());
				
				if(wineryImporterContacts.getContactType() != null){
					Map<String,String> contactType = new HashMap<String, String>();
					contactType.put("id", Long.toString(wineryImporterContacts.getContactType().getId()));
					contactType.put("name", wineryImporterContacts.getContactType().getName());
					wineryContactVO.setContactType(contactType);
				}
				
				wineryContactVO.setEmail(wineryImporterContacts.getEmail());
				wineryContactVO.setName(wineryImporterContacts.getName());
				wineryContactVO.setPhone(wineryImporterContacts.getPhone());
				wineryContactVO.setIsDefault(wineryImporterContacts.getIsDefault());
				response = new com.wineaccess.response.SuccessResponse(wineryContactVO, 200);	
			} else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR_TEXT, 200);
			}		

		} catch (Exception e) {
			logger.error("Error in creating new Winery contact. "+e);
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_105_ERROR_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);
		return output;
	}

	private static int returnCase(ContactDetailsPO contactDetailsPO){
		int caseType = 0; 
		/**
		 * If both winery and importer are null
		 * */
		if(null == contactDetailsPO.getWineryId()&& null == contactDetailsPO.getImporterId()){
			caseType = 1;
		}
		/**
		 * If both winery and importer are not null
		 * */
		else if(null != contactDetailsPO.getWineryId() && null != contactDetailsPO.getImporterId()){
			caseType = 2;
		} 
		/**
		 * If winery is not null 
		 * */
		else if(null != contactDetailsPO.getWineryId() && !("").equals(contactDetailsPO.getWineryId())){
			caseType = 3;
		} 
		/**
		 * If importer is not null 
		 * */
		else if(null != contactDetailsPO.getImporterId() && !("").equals(contactDetailsPO.getImporterId())){
			caseType = 4;
		} 

		return caseType;
	}



	private static int returnCase(ContactDetailsPO contactDetailsPO, WineryImporterContacts wineryImporterContacts){
		int caseType = 0; 

		if(null != wineryImporterContacts){
			/**
			 * If both winery and importer are null
			 * */
			if(null == contactDetailsPO.getWineryId()&& null == contactDetailsPO.getImporterId()){
				caseType = 1;
			}
			/**
			 * If both winery and importer are not null
			 * */
			else if(null != contactDetailsPO.getWineryId() && null != contactDetailsPO.getImporterId()){
				caseType = 2;
			} 
			/**
			 * If winery is not null 
			 * */
			else if(null != contactDetailsPO.getWineryId() && !("").equals(contactDetailsPO.getWineryId())){
				caseType = 3;
			} 
			/**
			 * If importer is not null 
			 * */
			else if(null != contactDetailsPO.getImporterId() && !("").equals(contactDetailsPO.getImporterId())){
				caseType = 4;
			}
		} else {
			caseType = 5;
		}	


		return caseType;
	}

	private static Response getWineryAddResponse(ContactDetailsPO contactDetailsPO, WineryImporterContacts wineryImporterContacts){
		Response response = null;
		WineryModel wineryModel = WineryRepository.getWineryModelById(contactDetailsPO.getWineryId());
		MasterData masterData = MasterDataRepository.getMasterDataById(contactDetailsPO.getContactType());
		if(null != masterData) {
			if(null != wineryModel){
				boolean isValidUserPhone = ValidationUtil.validateContentFormat(contactDetailsPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");	
				if(!isValidUserPhone){
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR_TEXT, 200);
				}
				else{
					String [] toBeIgnoredValues = {"contactType"};
					BeanUtils.copyProperties(contactDetailsPO,wineryImporterContacts,toBeIgnoredValues);
					wineryImporterContacts.setContactType(masterData);
					WineryImporterContacts wineryDefaultContact = WineryImporterContactRepository.getWineryContactById(contactDetailsPO.getWineryId());
					if(wineryImporterContacts.getIsDefault() == null){
						wineryImporterContacts.setIsDefault(false);
					}
					
					if(null != wineryDefaultContact){
						/**
						 * If contact is set default, then updating the existing default winery contact. 
						 * */
						if(BooleanUtils.isTrue(contactDetailsPO.getIsDefault())){
							
							wineryDefaultContact.setIsDefault(false);
							WineryImporterContactRepository.update(wineryDefaultContact);
						}
					} else {
						/**
						 * If contact is created for the first time, then setting the contact default for this winery.
						 * */
						wineryImporterContacts.setIsDefault(true);
						
					}
					wineryImporterContacts.setIsDeleted(false);
					WineryImporterContactRepository.save(wineryImporterContacts);
					
					EditContactDetailsPO contactDetails = new EditContactDetailsPO();
					BeanUtils.copyProperties(contactDetailsPO, contactDetails);
					contactDetails.setContactId(wineryImporterContacts.getId());
					contactDetails.setIsDefault(wineryImporterContacts.getIsDefault());
					response = new com.wineaccess.response.SuccessResponse(contactDetails, 200);	
				}
			} else {
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_108_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_108_ERROR_TEXT, 200);
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR_TEXT, 200);
		}

		return response;
	}

	private static Response getImporterAddResponse(ContactDetailsPO contactDetailsPO, WineryImporterContacts wineryImporterContacts){

		Response response = null;
		MasterData masterData = MasterDataRepository.getMasterDataById(contactDetailsPO.getContactType());
		ImporterModel importerModel = ImporterRepository.getImporterById(contactDetailsPO.getImporterId());
		if(null != masterData) {
			if(null != importerModel){
				boolean isValidUserPhone = ValidationUtil.validateContentFormat(contactDetailsPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");	
				if(!isValidUserPhone){
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR_TEXT, 200);
				}
				else{
					String [] toBeIgnoredValues = {"contactType"};
					BeanUtils.copyProperties(contactDetailsPO,wineryImporterContacts, toBeIgnoredValues );
					wineryImporterContacts.setContactType(masterData);
					WineryImporterContacts importerDefaultContact = WineryImporterContactRepository.getImporterContactById(contactDetailsPO.getImporterId());
					if(wineryImporterContacts.getIsDefault() == null){
						wineryImporterContacts.setIsDefault(false);
					}
					
					if(null != importerDefaultContact){
						/**
						 * If contact is set default, then updating the existing default winery contact. 
						 * */
						if(BooleanUtils.isTrue(contactDetailsPO.getIsDefault())){

							importerDefaultContact.setIsDefault(false);
							WineryImporterContactRepository.update(importerDefaultContact);
						}
					} else {
						/**
						 * If contact is created for the first time, then setting the contact default for this winery.
						 * */
						wineryImporterContacts.setIsDefault(true);
					}
					wineryImporterContacts.setIsDeleted(false);
					WineryImporterContactRepository.save(wineryImporterContacts);
					EditContactDetailsPO contactDetails = new EditContactDetailsPO();
					BeanUtils.copyProperties(contactDetailsPO, contactDetails);
					contactDetails.setContactId(wineryImporterContacts.getId());
					contactDetails.setIsDefault(wineryImporterContacts.getIsDefault());
					response = new com.wineaccess.response.SuccessResponse(contactDetails, 200);	
					response = new com.wineaccess.response.SuccessResponse("New importer contact added successfully.", 200);
				}
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_110_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_110_ERROR_TEXT, 200);
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR_TEXT, 200);
		}

		return response;
	}

	private static Response getWineryUpdateResponse(EditContactDetailsPO editContactDetailsPO,WineryImporterContacts wineryImporterContacts){
		Response response = null;
		MasterData masterData = MasterDataRepository.getMasterDataById(editContactDetailsPO.getContactType());
		WineryModel wineryModel = WineryRepository.getWineryModelById(editContactDetailsPO.getWineryId());
		if(null != masterData) {
			if(null != wineryModel){
				boolean isValidUserPhone = ValidationUtil.validateContentFormat(editContactDetailsPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");	
				if(!isValidUserPhone){
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR_TEXT, 200);
				}
				else{
					wineryImporterContacts.setEmail(editContactDetailsPO.getEmail());

					if(BooleanUtils.isTrue(editContactDetailsPO.getIsDefault())){
						if(BooleanUtils.isFalse(wineryImporterContacts.getIsDefault())){
							WineryImporterContacts defaultWineryImporterContacts = WineryImporterContactRepository.getWineryContactById(editContactDetailsPO.getWineryId());
							defaultWineryImporterContacts.setIsDefault(false);
							WineryImporterContactRepository.update(defaultWineryImporterContacts);
							wineryImporterContacts.setIsDefault(true);
						}
					}	

					wineryImporterContacts.setName(editContactDetailsPO.getName());
					wineryImporterContacts.setPhone(editContactDetailsPO.getPhone());
					wineryImporterContacts.setContactType(masterData);
					WineryImporterContactRepository.update(wineryImporterContacts);
					EditContactDetailsPO contactDetails = new EditContactDetailsPO();
					BeanUtils.copyProperties(editContactDetailsPO, contactDetails);
					contactDetails.setContactId(wineryImporterContacts.getId());
					
					response = new com.wineaccess.response.SuccessResponse(contactDetails, 200);	
					//response = new com.wineaccess.response.SuccessResponse("Winery contact updated successfully.", 200);	
				}
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_108_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_108_ERROR_TEXT, 200);
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR_TEXT, 200);
		}

		return response;
	}

	private static Response getImporterUpdateResponse(EditContactDetailsPO editContactDetailsPO,WineryImporterContacts wineryImporterContacts){
		Response response = null;
		MasterData masterData = MasterDataRepository.getMasterDataById(editContactDetailsPO.getContactType());
		ImporterModel importerModel = ImporterRepository.getImporterById(editContactDetailsPO.getImporterId());
		if(null != masterData ) {
			if(null != importerModel){
				boolean isValidUserPhone = ValidationUtil.validateContentFormat(editContactDetailsPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");	
				if(!isValidUserPhone){
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_119_ERROR_TEXT, 200);
				}
				else{
					wineryImporterContacts.setEmail(editContactDetailsPO.getEmail());
					if(BooleanUtils.isTrue(editContactDetailsPO.getIsDefault())){
						if(BooleanUtils.isFalse(wineryImporterContacts.getIsDefault())){
							WineryImporterContacts defaultWineryImporterContacts = WineryImporterContactRepository.getImporterContactById(editContactDetailsPO.getImporterId());
							defaultWineryImporterContacts.setIsDefault(false);
							WineryImporterContactRepository.update(defaultWineryImporterContacts);
							wineryImporterContacts.setIsDefault(true);
						}
					}	
					wineryImporterContacts.setName(editContactDetailsPO.getName());
					wineryImporterContacts.setPhone(editContactDetailsPO.getPhone());
					wineryImporterContacts.setContactType(masterData);
					WineryImporterContactRepository.update(wineryImporterContacts);
					EditContactDetailsPO contactDetails = new EditContactDetailsPO();
					BeanUtils.copyProperties(editContactDetailsPO, contactDetails);
					contactDetails.setContactId(wineryImporterContacts.getId());
					
					response = new com.wineaccess.response.SuccessResponse(contactDetails, 200);	
				}
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_110_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_110_ERROR_TEXT, 200);
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_109_ERROR_TEXT, 200);
		}

		return response;
	}



	/**
	 * @param listContactDetailsPO
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> getContactList(ContactsDetailListingPO listContactDetailsPO) throws ParseException {
		
		Map <String, Object> outputListAddress = new HashMap<String,Object>();
		Response response = null; 
		Long wineryId = listContactDetailsPO.getWineryId();
		Long importerId = listContactDetailsPO.getImporterId();
		if(importerId==null && wineryId==null)
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_103_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_103_ERROR_TEXT, 200);
		else if(importerId!=null)
		{
			ImporterModel importerModel = ImporterRepository.getImporterById(listContactDetailsPO.getImporterId());
			if(importerModel==null)
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_110_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_110_ERROR_TEXT, 200);
		}
		else if (wineryId!=null)
		{
			WineryModel wineryModel = WineryRepository.getWineryById(listContactDetailsPO.getWineryId());
			if(wineryModel == null)
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CONTACT_108_ERROR, WineaccessErrorCodes.SystemErrorCode.CONTACT_108_ERROR_TEXT, 200);
		}
		
		
		if(response==null){
			int size = WineryImporterContactRepository.countRecordsForQuery(listContactDetailsPO.getKeyword(),listContactDetailsPO.getWineryId(), listContactDetailsPO.getImporterId(),listContactDetailsPO.getContactType());

			int totalCount = WineryImporterContactRepository.countRecordsForQuery(listContactDetailsPO.getWineryId(), listContactDetailsPO.getImporterId(),listContactDetailsPO.getContactType());


			List<WineryImporterContacts> contactList = WineryImporterContactRepository.getContactDetailList(listContactDetailsPO.getKeyword(), Integer.parseInt(listContactDetailsPO.getSortOrder()), 
					Integer.parseInt(listContactDetailsPO.getOffSet())-1, Integer.parseInt(listContactDetailsPO.getLimit()),listContactDetailsPO.getSortBy(),listContactDetailsPO.getWineryId(), listContactDetailsPO.getImporterId(),listContactDetailsPO.getContactType());

			List<WineryImporterContactModel> wineryImporterContactModel = new ArrayList<WineryImporterContactModel>();

			for (WineryImporterContacts contact : contactList) {

				WineryImporterContactModel contactDetailVO = new WineryImporterContactModel();
				contactDetailVO.setContactType(contact.getContactType().getName());
				contactDetailVO.setEmail(contact.getEmail());
				contactDetailVO.setName(contact.getName());
				contactDetailVO.setPhone(contact.getPhone());
				contactDetailVO.setId(contact.getId());
				if(BooleanUtils.isTrue(contact.getIsDefault())){
					contactDetailVO.setDefault(true);
				}
				wineryImporterContactModel.add(contactDetailVO);

			}
			ContactSearchVO contactCustomVO = new ContactSearchVO();
			contactCustomVO.setCount(size);
			contactCustomVO.setTotalRecordCount(totalCount);
			contactCustomVO.setKeyword(listContactDetailsPO.getKeyword());
			contactCustomVO.setOffSet(Integer.parseInt(listContactDetailsPO.getOffSet()));
			contactCustomVO.setLimit(Integer.parseInt(listContactDetailsPO.getLimit()));
			contactCustomVO.setContactDetail(wineryImporterContactModel);

			response = new com.wineaccess.response.SuccessResponse(contactCustomVO, 200);

		}
		outputListAddress.put("FINAL-RESPONSE", response);
		return outputListAddress;
	}
}
