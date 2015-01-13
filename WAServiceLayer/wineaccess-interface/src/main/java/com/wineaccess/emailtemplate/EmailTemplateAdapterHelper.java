package com.wineaccess.emailtemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateRepository;
import com.wineaccess.data.model.common.EmailTemplateType;
import com.wineaccess.data.model.common.EmailTemplateTypeRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;
import com.wineaccess.user.activity.log.UserServiceModel;

public class EmailTemplateAdapterHelper {
	
	private static Log logger = LogFactory.getLog(EmailTemplateAdapterHelper.class);

	public static Map<String, Object> addEmailTemplate(
			EmailTemplatePO emailTemplatePO) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;
		try {
			EmailTemplateType emailTemplateType = EmailTemplateTypeRepository
					.getEmailTemplateTypeById(emailTemplatePO
							.getEmailTemplateTypeId());
			if(emailTemplateType == null){
				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.ADD_EMAIL_TEMPLATE_INVALID_TYPE_ID,
						SystemErrorCode.ADD_EMAIL_TEMPLATE_INVALID_TYPE_ID_TEXT, 200);
				//return response;
			}
			if(response == null){
			Set<EmailTemplate> emailTempaltes = emailTemplateType.getEmailTemplates();
			if(emailTempaltes != null &&  !emailTempaltes.isEmpty()){
				for(EmailTemplate template : emailTempaltes){
					if(template.getName().equals(emailTemplatePO.getName())){
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM,
								SystemErrorCode.ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM_TEXT, 200);
						break;
					}
				}
			}
			}
			
			if(response == null){
				EmailTemplate emailTemplate = new EmailTemplate();
				List<EmailTemplate> emTemplates = EmailTemplateRepository.getByAciveStatusAndEmailTemplateTypeId(emailTemplatePO
						.getEmailTemplateTypeId());
				
				if(!CollectionUtils.isEmpty(emTemplates)){
					if(BooleanUtils.isTrue(emailTemplatePO.getIsDefault())){
						for(EmailTemplate template : emTemplates){
							template.setActive(false);
							EmailTemplateRepository.update(template);
						}
						emailTemplate.setActive(true);
					}else{
						emailTemplate.setActive(false);
					}
					
				}else{
					emailTemplate.setActive(true);
				}
				
								
				emailTemplate.setName(emailTemplatePO.getName());
				emailTemplate.setFromEmail(emailTemplatePO.getFromEmail());
				emailTemplate.setSubject(emailTemplatePO.getSubject());
				emailTemplate.setBody(emailTemplatePO.getBody());
				emailTemplate.setEmailTemplateType(emailTemplateType);
				//emailTemplate.setActive(false);		

				EmailTemplateRepository.save(emailTemplate);
				
				EmailTemplateTypeRepository.update(emailTemplateType);			

				EmailTemplateAddVO emailTemplateAddVO = new EmailTemplateAddVO(
						"Success");

				response = new com.wineaccess.response.SuccessResponse(
						emailTemplateAddVO, 200);
			}
			

		} catch (Exception e) {

			logger.error("Error while adding a new record for email template" + e.getMessage());
			logger.error(e);

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.ADD_EMAIL_TEMPLATE_INVALID_PARAM,
					SystemErrorCode.ADD_EMAIL_TEMPLATE_INVALID_PARAM_TEXT, 200);
		}

		finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	public static Map<String, Object> updateEmailTemplateById(
			Long emailTemplateId, EmailTemplatePO emailTemplatePO) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		try {
			EmailTemplate emailTemplate = EmailTemplateRepository
					.getEmailTemplateById(emailTemplateId);

			EmailTemplateType emailTemplateType = EmailTemplateTypeRepository
					.getEmailTemplateTypeById(emailTemplatePO
							.getEmailTemplateTypeId());	
			
			if(emailTemplateType == null){
				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.UPDATE_EMAIL_TEMPLATE_INVALID_TYPE_ID,
						SystemErrorCode.UPDATE_EMAIL_TEMPLATE_INVALID_TYPE_ID_TEXT, 200);
				//return response;
			}
			
			if(response == null){
				if(emailTemplate == null){
					response = ApplicationUtils.errorMessageGenerate(
							SystemErrorCode.UPDATE_EMAIL_TEMPLATE_INVALID_ID,
							SystemErrorCode.UPDATE_EMAIL_TEMPLATE_INVALID_ID_TEXT, 200);
					//return response;
				}
				Set<EmailTemplate> emailTempaltes = emailTemplateType.getEmailTemplates();
				if(emailTempaltes != null &&  !emailTempaltes.isEmpty()){
					for(EmailTemplate template : emailTempaltes){
						if(emailTemplateId != template.getId() && template.getName().equals(emailTemplatePO.getName())){
							response = ApplicationUtils.errorMessageGenerate(
									SystemErrorCode.ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM,
									SystemErrorCode.ADD_EMAIL_TEMPLATE_DUPLICATE_PARAM_TEXT, 200);
							break;
						}
					}
				}
			}
			if(response == null){
			List<EmailTemplate> emTemplates = EmailTemplateRepository.getByAciveStatusAndEmailTemplateTypeId(emailTemplatePO
					.getEmailTemplateTypeId());
			
		
			
			if(!CollectionUtils.isEmpty(emTemplates)){
				if(BooleanUtils.isTrue(emailTemplatePO.getIsDefault())){
					for(EmailTemplate template : emTemplates){
						template.setActive(false);
						EmailTemplateRepository.update(template);
					}
					emailTemplate.setActive(true);
				}else{
					emailTemplate.setActive(false);
				}
				
			}else{
				emailTemplate.setActive(true);
			}
			
			// update content
			emailTemplate.setName(emailTemplatePO.getName());
			if(emailTemplatePO.getFromEmail() != null){
				emailTemplate.setFromEmail(emailTemplatePO.getFromEmail());
			}
			
			if(emailTemplatePO.getSubject() != null){
				emailTemplate.setSubject(emailTemplatePO.getSubject());
			}
			if(emailTemplatePO.getBody() != null){
				emailTemplate.setBody(emailTemplatePO.getBody());
			}
			/*if(emailTemplatePO.getIsDefault() != null){
				emailTemplate.setActive(emailTemplatePO.getIsDefault());
			}*/
			
			
			emailTemplate.setEmailTemplateType(emailTemplateType);
			

			EmailTemplateRepository.update(emailTemplate);
			
			EmailTemplateTypeRepository.update(emailTemplateType);		

			EmailTemplateUpdateVO emailTemplateUpdateVO = new EmailTemplateUpdateVO(
					"Success");

			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateUpdateVO, 200);
			}
			//output.put("FINAL-RESPONSE", response);

		} catch (Exception e) {

			logger.error("Error while updating record for email template" + e);

			response = ApplicationUtils
					.errorMessageGenerate(
							SystemErrorCode.EMAIL_TEMPLATE_UPDATE_BY_ID_INVALID_PARAM,
							SystemErrorCode.EMAIL_TEMPLATE_UPDATE_BY_ID_INVALID_PARAM_TEXT,
							200);

		} finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	public static Map<String, Object> cloneEmailTemplateById(
			 EmailTemplatePO emailTemplatePO) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		try {
			EmailTemplate emailTemplate = EmailTemplateRepository
					.getEmailTemplateById(emailTemplatePO.getEmailTemplateId());

			EmailTemplate emailTemplateCloneObj = new EmailTemplate();

			try {
				BeanUtils.copyProperties(emailTemplateCloneObj, emailTemplate);
			} catch (Exception e) {

			}
			
			if(!ValidationUtil.validateContent(emailTemplatePO.getEmailTemplateId().toString(), RegExConstants.DIGITS.toString())){
				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.EMAIL_TEMPLATE_CLONE_INVALID_PARAM,
						SystemErrorCode.EMAIL_TEMPLATE_CLONE_INVALID_PARAM_TEXT,
						200);
			}
			
			emailTemplateCloneObj.setId(null);
			emailTemplateCloneObj.setName(emailTemplatePO.getName());

			EmailTemplateRepository.save(emailTemplateCloneObj);
			
			EmailTemplateTypeRepository.update(emailTemplateCloneObj.getEmailTemplateType());		

			UserServiceModel userServiceModel = getUserServiceModel(emailTemplateCloneObj
					.getModifiedBy());

			EmailTemplateCustomModel emailTemplateCustomModel = getEmailTemplateCustomModel(
					emailTemplateCloneObj, userServiceModel);

			EmailTemplateCloneVO emailTemplateCloneVO = new EmailTemplateCloneVO(
					emailTemplateCustomModel);

			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateCloneVO, 200);
			output.put("FINAL-RESPONSE", response);

		} catch (Exception e) {

			logger.error("Error while cloning a record for email template" + e);

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.EMAIL_TEMPLATE_CLONE_INVALID_PARAM,
					SystemErrorCode.EMAIL_TEMPLATE_CLONE_INVALID_PARAM_TEXT,
					200);

		} finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	public static Map<String, Object> listEmailTemplates() {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		List<EmailTemplate> emailTemplates = EmailTemplateRepository
				.getEmailTemplates();

		List<EmailTemplateCustomModel> emailTemplateCustomModels = getEmailTemplateCustomModels(emailTemplates);

		EmailTemplateListVO emailTemplateListVO = new EmailTemplateListVO(
				emailTemplateCustomModels);

		response = new com.wineaccess.response.SuccessResponse(
				emailTemplateListVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}

	public static Map<String, Object> getEmailTemplateById(Long emailTemplateId) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;
		try {
			EmailTemplate emailTemplate = EmailTemplateRepository
					.getEmailTemplateById(emailTemplateId);

			UserServiceModel userServiceModel = getUserServiceModel(emailTemplate
					.getModifiedBy());

			EmailTemplateCustomModel emailTemplateCustomModel = getEmailTemplateCustomModel(
					emailTemplate, userServiceModel);

			EmailTemplateGetByIdVO emailTemplateGetByIdVO = new EmailTemplateGetByIdVO(
					emailTemplateCustomModel);

			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateGetByIdVO, 200);
			output.put("FINAL-RESPONSE", response);

		} catch (Exception e) {

			logger.error("Error while getting record for email template Id" + e);

			response = ApplicationUtils
					.errorMessageGenerate(
							SystemErrorCode.EMAIL_TEMPLATE_GET_BY_ID_INVALID_PARAM,
							SystemErrorCode.EMAIL_TEMPLATE_GET_BY_ID_INVALID_PARAM_TEXT,
							200);
		} finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	
	public static Map<String, Object> multipleDeleteEmailTemplate(
			String multipleEmailTemplateIds, boolean confirmStatus) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		BulkDeleteModel<EmailTemplate> bulkDeleteModel = EmailTemplateRepository
				.multipleDeleteEmailTemplate(multipleEmailTemplateIds,
						confirmStatus);

	
		
		
		
		DeleteVO<EmailTemplateCustomModel> emailTemplateCustomModelsForDependency = new DeleteVO<EmailTemplateCustomModel>();
		DeleteVO<EmailTemplateCustomModel> emailTemplateCustomModelsForCanBeDeleted = new DeleteVO<EmailTemplateCustomModel>();
		
		
		List<EmailTemplateCustomModel> deleteList = new ArrayList<EmailTemplateCustomModel>();
		List<EmailTemplateCustomModel> dependencyList = new ArrayList<EmailTemplateCustomModel>();
		
		List<EmailTemplate> emailTemplates = bulkDeleteModel
				.getNotDeletedList();

		for (EmailTemplate emailTemplate : emailTemplates) {

			UserServiceModel userServiceModel = getUserServiceModel(emailTemplate
					.getModifiedBy());

			EmailTemplateCustomModel emailTemplateCustomModel = new EmailTemplateCustomModel();
			try {
				emailTemplateCustomModel = getEmailTemplateCustomModel(
						emailTemplate, userServiceModel);
			} catch (EmailTemplateException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			dependencyList.add(emailTemplateCustomModel);
		}
		
		emailTemplateCustomModelsForDependency.setElements(dependencyList);

		List<EmailTemplate> emailTemplatesCanBeDeleted = bulkDeleteModel
				.getDeletedList();

		for (EmailTemplate emailTemplate : emailTemplatesCanBeDeleted) {
			UserServiceModel userServiceModel = getUserServiceModel(emailTemplate
					.getModifiedBy());

			EmailTemplateCustomModel emailTemplateCustomModel = new EmailTemplateCustomModel();
			try {
				emailTemplateCustomModel = getEmailTemplateCustomModel(
						emailTemplate, userServiceModel);
			} catch (EmailTemplateException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			deleteList.add(emailTemplateCustomModel);
		}
		
		emailTemplateCustomModelsForCanBeDeleted.setElements(deleteList);
		
		EmailTemplateBulkDeleteVO emailTemplateBulkDeleteVO = new EmailTemplateBulkDeleteVO();
		emailTemplateBulkDeleteVO.setNonExistsList((List<Long>)bulkDeleteModel.getNotExistsList());
		emailTemplateBulkDeleteVO.setFailureList(emailTemplateCustomModelsForDependency);
		emailTemplateBulkDeleteVO.setSuccessList(emailTemplateCustomModelsForCanBeDeleted);
		
		
		response = new com.wineaccess.response.SuccessResponse(
				emailTemplateBulkDeleteVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}

	private static EmailTemplateCustomModel getEmailTemplateCustomModel(
			EmailTemplate emailTemplate, UserServiceModel userServiceModel)
			throws EmailTemplateException {

		EmailTemplateCustomModel emailTemplateCustomModel = null;

		//if (BooleanUtils.isFalse(emailTemplate.isDeleted())) {

			emailTemplateCustomModel = new EmailTemplateCustomModel(
					emailTemplate.getId(), emailTemplate.getName(),
					emailTemplate.getSubject(), emailTemplate.getBody(),
					emailTemplate.getEmailTemplateType().getId(),
					userServiceModel, emailTemplate.getModifiedDate(),
					emailTemplate.getFromEmail(), emailTemplate.isActive());

		/*} else {
			throw new EmailTemplateException();
		}*/

		return emailTemplateCustomModel;

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

	private static List<EmailTemplateCustomModel> getEmailTemplateCustomModels(
			List<EmailTemplate> emailTemplates) {

		List<EmailTemplateCustomModel> emailTemplateCustomModels = new ArrayList<EmailTemplateCustomModel>();

		for (EmailTemplate emailTemplate : emailTemplates) {

			//if (BooleanUtils.isFalse(emailTemplate.isDeleted())) {

				UserServiceModel userServiceModel = getUserServiceModel(emailTemplate
						.getModifiedBy());

				EmailTemplateCustomModel emailTemplateCustomModel = null;
				try {
					emailTemplateCustomModel = getEmailTemplateCustomModel(
							emailTemplate, userServiceModel);
				} catch (EmailTemplateException e) {

				}
				emailTemplateCustomModels.add(emailTemplateCustomModel);
			//}
		}

		return emailTemplateCustomModels;
	}

	public static Map<String, Object> getEmailTemplatesByKeyword(
			String keyword, String sortBy, int offSet, int limit,
			int sortOrder, Long templateId) {
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		EmailTemplateType	emailTemplateType = EmailTemplateTypeRepository.getEmailTemplateTypeById(templateId);
		if(emailTemplateType == null){
			response = ApplicationUtils
					.errorMessageGenerate(
							SystemErrorCode.EMAIL_TEMPLATE_TYPE_INVALID_ID,
							SystemErrorCode.EMAIL_TEMPLATE_TYPE_INVALID_ID_TEXT,
							200);
		}else{
			
			
			
			
			List<EmailTemplate> emailTemplates = EmailTemplateRepository
					.getEmailTemplate(keyword, sortOrder, offSet-1, limit,sortBy, emailTemplateType);
			int totalCount = EmailTemplateRepository.countRecordsForQuery(emailTemplateType);
			int count = totalCount;
			if(keyword != null && !keyword.equals("")){
				count = EmailTemplateRepository.countRecordsForQuery(keyword, emailTemplateType);
				
			}
			
			
			EmailTemplateSearchVO emailTemplateSearchVO = new EmailTemplateSearchVO(offSet, limit,keyword,count,totalCount);
			EmailTemplateSearch emailTemplateSearch = getEmailTemplates(emailTemplateType.getId(), emailTemplateType.getName(),emailTemplateType.getLabel(),emailTemplates);
			emailTemplateSearchVO.setSearchResult(emailTemplateSearch);
			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateSearchVO, 200);
		}
		

		

	
		output.put("FINAL-RESPONSE", response);

		return output;
	}

	private static EmailTemplateSearch getEmailTemplates(Long id, String name, String label, List<EmailTemplate> emailTemplates) {
		EmailTemplateSearch emailTemplateSearch = new EmailTemplateSearch(id, name, label);
		Map<Long,UserServiceModel> userModelMap = new HashMap<Long,UserServiceModel>();
		for(EmailTemplate template:emailTemplates){
			com.wineaccess.emailtemplate.EmailTemplate responseTemplate = new com.wineaccess.emailtemplate.EmailTemplate(template.getId(),template.getName(),template.getFromEmail(), template.getSubject(),template.getModifiedDate());
		//	responseTemplate.
			if(userModelMap.get(template.getModifiedBy()) != null){
				responseTemplate.setUserServiceModel(userModelMap.get(template.getModifiedBy()));
			}else{
				
				UserServiceModel userServiceModel = getUserServiceModel(template.getModifiedBy());
				userModelMap.put(template.getModifiedBy(), userServiceModel);
				responseTemplate.setUserServiceModel(userServiceModel);
				
			}
			emailTemplateSearch.getEmailTemplates().add(responseTemplate);
		
		
		}
		return emailTemplateSearch;
	}

}
