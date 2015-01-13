package com.wineaccess.service.newsletter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.user.NewsLetter;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class NewsletterAdapterHelper {
	
	private static Log logger = LogFactory.getLog(NewsletterAdapterHelper.class);
	
	/**
	 * 
	 * @return -list of news letter based on id passed
	 */
	public static Map<String, Object> listNewslettersById(List<Long> newsletterIds) {	
		
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		
		if(null != newsletterIds && !newsletterIds.isEmpty()){
			/**
			 * Getting the list of newsletter based on newsletter ids.
			 * */
			List<NewsLetter> listOfNewsletter = NewsletterRepository.getById(newsletterIds);
			/**
			 * Generating the VO to pass in to response.
			 * */
			List<NewsletterCustomModel> NewsletterCustomModels = new ArrayList<NewsletterCustomModel>();
			
			if(null != listOfNewsletter && !listOfNewsletter.isEmpty()){					
			
					for(NewsLetter newsletter: listOfNewsletter)
					{						
						NewsletterCustomModel newsletterCustomModel = new NewsletterCustomModel(); 
					
						try
						{
							BeanUtils.copyProperties(newsletterCustomModel, newsletter);	
							
							newsletterCustomModel.setUserServiceModel(getUserServiceModel(newsletter.getModifiedBy()));
							
							newsletterCustomModel.setModifiedDate(newsletter.getModifiedDate());						
				
							NewsletterCustomModels.add(newsletterCustomModel);
						}		
						catch(Exception e){
							logger.error("Error while copying newsletter list to VO." + e);
						}						
					}
				
				NewsletterVOList newsletterVO = new NewsletterVOList(NewsletterCustomModels);
				response = new com.wineaccess.response.SuccessResponse(newsletterVO, 200);	
			}
			else{
				//NewsletterVOList newsletterVO = new NewsletterVOList(NewsletterCustomModels);
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR_TEXT, 200);	
			}
		}
		else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR_TEXT, 200);
		}
		output.put("FINAL-RESPONSE", response);
		return output;
	}
	
	/**
	 * 
	 * @return -adds the newsletter details to database. If everything works fine then returns a success message: "Newsletter added successfully."
	 */
	public static  Map<String, Object> addNewsletters(NewsletterPO newsletterPO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		
		String mandatoryFields[] = {"name","effDate","endDate","emailSubject","fromEmail","title","isDefault"};
		if(null != newsletterPO){
			/**
			 * Validating if all the mandatory fields are present or not.
			 * */
			if(ApplicationUtils.validateMandatoryFields(newsletterPO, mandatoryFields, NewsletterPO.class)){
				NewsLetter newsletter = new NewsLetter();
				
				List<NewsLetter> existingNewsletter = NewsletterRepository.getByName(newsletterPO.getName());
				
				if(existingNewsletter != null && existingNewsletter.size() > 0){
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.ADD_NEWSLETTER_DUPLICATE_ERROR, WineaccessErrorCodes.SystemErrorCode.ADD_NEWSLETTER_DUPLICATE_ERROR_TEXT, 200);
				}else{
					
					newsletter.setEffDate(newsletterPO.getEffDate());
					newsletter.setEmailSubject(newsletterPO.getEmailSubject());
					newsletter.setEndDate(newsletterPO.getEndDate());
					newsletter.setFromEmail(newsletterPO.getFromEmail());
					newsletter.setName(newsletterPO.getName());
					newsletter.setSubmitDate(new Date());
					newsletter.setTitle(newsletterPO.getTitle());
					newsletter.setWebName(newsletterPO.getWebName());
					
					//add for adding isDefault functionality
					newsletter.setIsDefault(newsletterPO.getIsDefault());
					
					//if duplicate name is added then it throw exception
					try {
						NewsletterRepository.save(newsletter);
						
						response = new com.wineaccess.response.SuccessResponse("Newsletter added Successfully.", 200);	
						
					} catch (Exception e) {
						
						response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.ADD_NEWSLETTER_INVALID_PARAM, WineaccessErrorCodes.SystemErrorCode.ADD_NEWSLETTER_INVALID_PARAM_TEXT, 200);
					}
					
				}
				
				
				
				
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR_TEXT, 200);
			}	
		}
		else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR_TEXT, 200);
		}
		
		output.put("FINAL-RESPONSE", response);
		return output;
		
	}
	
	/**
	 * 
	 * @return -updates the newsletter details to database. If everything works fine then returns a success message: "Newsletter updated successfully."
	 */
	public static  Map<String, Object> updateNewsletters(NewsletterPO newsletterPO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;

		String mandatoryFields[] = {"id","name","effDate","endDate","emailSubject","fromEmail","title","isDefault"};
		if(null != newsletterPO){
			/**
			 * Validating if all the mandatory fields are present or not.
			 * */
			if(ApplicationUtils.validateMandatoryFields(newsletterPO, mandatoryFields, NewsletterPO.class)){
				if(null != newsletterPO.getId() && !("").equals(newsletterPO.getId())){
					List<Long> newsletterList = new ArrayList<Long>();
					newsletterList.add(newsletterPO.getId());
					/**
					 * Getting the newsletter that is to be updated by newsletter id.
					 * */
					List<NewsLetter> listOfNewsletter = NewsletterRepository.getById(newsletterList);
					if(listOfNewsletter == null || listOfNewsletter.isEmpty()){
						response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NO_NEWSLETTER_FOUND_ERROR, WineaccessErrorCodes.SystemErrorCode.NO_NEWSLETTER_FOUND_ERROR_TEXT, 200);
					}
					else{
						List<NewsLetter> existingNewsletter = NewsletterRepository.getByName(newsletterPO.getName());
						NewsLetter newsletter = listOfNewsletter.get(0);
						if(existingNewsletter != null && existingNewsletter.size() > 0){
							if(existingNewsletter.get(0).getName().equals(newsletterPO.getName()) && existingNewsletter.get(0).getId() != newsletter.getId()){
								response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.UPDATE_NEWSLETTER_DUPLICATE_ERROR, WineaccessErrorCodes.SystemErrorCode.UPDATE_NEWSLETTER_DUPLICATE_ERROR_TEXT, 200);
							}
							
						}
							
						if(response == null){
							
							newsletter.setEffDate(newsletterPO.getEffDate());
							newsletter.setEmailSubject(newsletterPO.getEmailSubject());
							newsletter.setEndDate(newsletterPO.getEndDate());
							newsletter.setFromEmail(newsletterPO.getFromEmail());
							newsletter.setName(newsletterPO.getName());
							newsletter.setSubmitDate(new Date());
							newsletter.setTitle(newsletterPO.getTitle());
							newsletter.setWebName(newsletterPO.getWebName());
							
							//add for adding isDefault functionality
							newsletter.setIsDefault(newsletterPO.getIsDefault());
							
							//if duplicate name is added then it throw exception
							try {
								NewsletterRepository.update(newsletter);
								
								response = new com.wineaccess.response.SuccessResponse("Newsletter updated Successfully.", 200);	
							} catch (Exception e) {

								response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.UPDATE_NEWSLETTER_INVALID_PARAM, WineaccessErrorCodes.SystemErrorCode.UPDATE_NEWSLETTER_INVALID_PARAM_TEXT, 200);
							}
							
						}
						
						
						
						
					}
				}
				else{
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR_TEXT, 200);
				}	
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR_TEXT, 200);
			}	
		}
		else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_MANDATORY_FIELD_MISSING_ERROR_TEXT, 200);
		}
		
		output.put("FINAL-RESPONSE", response);
		return output;
		
	}
	
	/**
	 * 
	 * @return -deletes the newsletter details from the database. 
	 * Returns three list showing :
	 * > successList  (for the newsletters those are succesfully deleted)
	 * > failureList  (for the newsletters those were having the dependency to other table)
	 * > nonexistingList  (for the non existing newsletters)
	 */
	public static  Map<String, Object> deleteNewsletters(NewsletterDeletePO newsletterDeletePO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;

		if(null != newsletterDeletePO && null != newsletterDeletePO.getId()){
			
			String newsletterIds = newsletterDeletePO.getId();
			String[] newsletterIdArray = newsletterIds.split(",");
			/**
			 * Copying the newsletter ids to list
			 * */
			List<Serializable> newsletterList = new ArrayList<Serializable>();
			for(String newsletterIdObject : newsletterIdArray){
				if(null != newsletterIdObject && !("").equals(newsletterIdObject)){
					if(StringUtils.isNumeric(newsletterIdObject)){
						newsletterList.add(Long.parseLong(newsletterIdObject));	
					}
					else{
						response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_DELETE_PARAM_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_DELETE_PARAM_ERROR_TEXT, 200);
						output.put("FINAL-RESPONSE", response);
						return output; 
					}
				}
			}
			
			Boolean isForceDelete = false;
			if(null != newsletterDeletePO.getIsForceDelete())
				isForceDelete = newsletterDeletePO.getIsForceDelete();
			
			/**
			 * Passing the newsletter ids to delete method to delete the newsletters.
			 * */
			BulkDeleteModel<NewsLetter> bulkDeleteModel = NewsletterRepository.delete(newsletterList,isForceDelete);
			/**
			 * Getting the list of deleted list and setting it to VO
			 * */
			
			DeleteVO<NewsletterCustomModel> newsletterCustomModelsForDependency = new DeleteVO<NewsletterCustomModel>();
			DeleteVO<NewsletterCustomModel> newsletterCustomModelsForCanBeDeleted = new DeleteVO<NewsletterCustomModel>();
			
			List<NewsletterCustomModel> deleteList = new ArrayList<NewsletterCustomModel>();
			List<NewsletterCustomModel> dependencyList = new ArrayList<NewsletterCustomModel>();
			
			List<NewsLetter> canBeDeletedList = bulkDeleteModel.getDeletedList();
			
			for(NewsLetter newsletter: canBeDeletedList)
			{						
				NewsletterCustomModel newsletterCustomModel = new NewsletterCustomModel(); 
			
				try
				{
					BeanUtils.copyProperties(newsletterCustomModel, newsletter);	
					
					newsletterCustomModel.setUserServiceModel(getUserServiceModel(newsletter.getModifiedBy()));
					
					newsletterCustomModel.setModifiedDate(newsletter.getModifiedDate());						
		
					deleteList.add(newsletterCustomModel);
				}		
				catch(Exception e){
					logger.error("Error while copying newsletter list to VO." + e);
				}						
			}
			
			newsletterCustomModelsForCanBeDeleted.setElements(deleteList);
			
			
			/*List<NewsletterVO> newsletterVOList = new ArrayList<NewsletterVO>();
			for(NewsLetter newsletters: canBeDeletedList){
				NewsletterVO newsletterVO = new NewsletterVO();
				try {
					BeanUtils.copyProperties(newsletterVO, newsletters);
				} catch (Exception e) {
					logger.error("Error while copying the newsletter to list VO.");
				}
				newsletterVOList.add(newsletterVO);
			}*/
			
			/**
			 * Getting the list of non deleted list and setting it to VO
			 * */
			List<NewsLetter> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();
			/*List<NewsletterVO> nonDeletedNewsletterVOList = new ArrayList<NewsletterVO>();
			for(NewsLetter newsletters: canNotBeDeletedList){
				NewsletterVO newsletterVO = new NewsletterVO();
				try {
					BeanUtils.copyProperties(newsletterVO, newsletters);
				} catch (Exception e) {
					logger.error("Error while copying the newsletter to list VO.");
				}
				nonDeletedNewsletterVOList.add(newsletterVO);
			}*/
			
			for(NewsLetter newsletter: canNotBeDeletedList)
			{						
				NewsletterCustomModel newsletterCustomModel = new NewsletterCustomModel(); 
			
				try
				{
					BeanUtils.copyProperties(newsletterCustomModel, newsletter);	
					
					newsletterCustomModel.setUserServiceModel(getUserServiceModel(newsletter.getModifiedBy()));
					
					newsletterCustomModel.setModifiedDate(newsletter.getModifiedDate());						
		
					dependencyList.add(newsletterCustomModel);
				}		
				catch(Exception e){
					logger.error("Error while copying newsletter list to VO." + e);
				}						
			}
			
			newsletterCustomModelsForDependency.setElements(dependencyList);
			
			/**
			 * Getting the list of non existing list and setting it to VO
			 * */
			List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();
			
			NewsletterBulkDeleteVO newsletterBulkDeleteVO = new NewsletterBulkDeleteVO();
			newsletterBulkDeleteVO.setNonExistsList(nonExistingList);
			newsletterBulkDeleteVO.setFailureList(newsletterCustomModelsForDependency);
			newsletterBulkDeleteVO.setSuccessList(newsletterCustomModelsForCanBeDeleted);	
						
			response = new com.wineaccess.response.SuccessResponse(newsletterBulkDeleteVO, 200);
		}
		else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR, WineaccessErrorCodes.SystemErrorCode.NEWSLETTER_ERROR_TEXT, 200);
		}
		
		output.put("FINAL-RESPONSE", response);
		return output;
		
	}
	
	/**
	 * 
	 * @return -search for a keyword and returns the matching row of newsletter also returns list of all the newsletters if nothing is passed in keyword param.
	 */
	public static Map<String, Object> searchNewslettersByKeyword(String keyword,int offSet,int limit,String sortBy,int sortOrder) {		
		
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;	
				
		/**
		 * Getting the list of newsletters.
		 * */
		
		
		List<NewsLetter> newsletterList = NewsletterRepository
				.getByKeyword(keyword,
						sortBy,sortOrder, offSet-1, limit);
		int count = NewsletterRepository.countRecordsForQuery(keyword);
		
		List<NewsletterCustomModel> newsletterCustomModels = new ArrayList<NewsletterCustomModel>();
		
		for(NewsLetter newsletter: newsletterList)
		{						
			NewsletterCustomModel newsletterCustomModel = new NewsletterCustomModel(); 
		
			try
			{
				BeanUtils.copyProperties(newsletterCustomModel, newsletter);	
				
				newsletterCustomModel.setUserServiceModel(getUserServiceModel(newsletter.getModifiedBy()));
				
				newsletterCustomModel.setModifiedDate(newsletter.getModifiedDate());						
	
				newsletterCustomModels.add(newsletterCustomModel);
			}		
			catch(Exception e){
				logger.error("Error while copying newsletter list to VO." + e);
			}						
		}
		NewsletterSearchVO newsletterSearchVO = new NewsletterSearchVO(offSet, limit,keyword,count,NewsletterRepository.countAllRecords(),newsletterCustomModels);
		
		response = new com.wineaccess.response.SuccessResponse(newsletterSearchVO, 200);

		output.put("FINAL-RESPONSE", response);
			
		return output;
	}
	
	private static UserServiceModel getUserServiceModel(Long modifyBy) {

		UserModel userModel = UserRepository.findUserByUserId(modifyBy);
		UserServiceModel userServiceModel = null;
		if (userModel != null)
			userServiceModel = new UserServiceModel(userModel.getId(),
					userModel.getFirstName(), userModel.getLastName(),
					userModel.getEmail());

		return userServiceModel;
	}
}
