package com.wineaccess.emailtemplatetype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateType;
import com.wineaccess.data.model.common.EmailTemplateTypeRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.response.Response;
import com.wineaccess.user.activity.log.UserServiceModel;

public class EmailTemplateTypeAdapterHelper {

	public static Map<String, Object> listEmailTemplateTypes(
			String status) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		List<EmailTemplateType> emailTemplateTypes = EmailTemplateTypeRepository
				.getEmailTemplateTypes(status);

		if (emailTemplateTypes != null && emailTemplateTypes.size() > 0) {

			List<EmailTemplateTypeCustomModel> emailTemplateTypeCustomModels = new ArrayList<EmailTemplateTypeCustomModel>();

			for (EmailTemplateType emailTemplateType : emailTemplateTypes) {

				List<EmailTemplateCustomModel> emailTemplateCustomModels = getEmailTemplateCustomModels(emailTemplateType);

				UserServiceModel userServiceModel = getUserServiceModel(emailTemplateType
						.getModifiedBy());

				EmailTemplateTypeCustomModel emailTemplateTypeCustomModel = getEmailTemplateTypeCustomModel(
						emailTemplateType, emailTemplateCustomModels,
						userServiceModel);

				emailTemplateTypeCustomModels.add(emailTemplateTypeCustomModel);

			}

			EmailTemplateTypeListVO emailTemplateTypeListVO = new EmailTemplateTypeListVO(
					emailTemplateTypeCustomModels);

			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateTypeListVO, 200);

		} else {

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND,
					SystemErrorCode.EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND_TEXT,
					200);
		}

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	public static Map<String, Object> getEmailTemplateTypeByName(
			String emailTemplateTypeName) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		EmailTemplateType emailTemplateType = EmailTemplateTypeRepository
				.getEmailTemplateTypeByName(emailTemplateTypeName);

		if (emailTemplateType != null) {

			List<EmailTemplateCustomModel> emailTemplateCustomModels = getEmailTemplateCustomModels(emailTemplateType);

			UserServiceModel userServiceModel = getUserServiceModel(emailTemplateType
					.getModifiedBy());

			EmailTemplateTypeCustomModel emailTemplateTypeCustomModel = getEmailTemplateTypeCustomModel(
					emailTemplateType, emailTemplateCustomModels,
					userServiceModel);

			EmailTemplateTypeGetByIdVO emailTemplateTypeGetByIdVO = new EmailTemplateTypeGetByIdVO(
					emailTemplateTypeCustomModel);

			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateTypeGetByIdVO, 200);

		} else {
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND,
					SystemErrorCode.EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND_TEXT,
					200);
		}

		output.put("FINAL-RESPONSE", response);

		return output;
	}

	public static Map<String, Object> getEmailTemplateTypeById(
			Long emailTemplateTypeId) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		EmailTemplateType emailTemplateType = EmailTemplateTypeRepository
				.getEmailTemplateTypeById(emailTemplateTypeId);

		if (emailTemplateType != null) {

			List<EmailTemplateCustomModel> emailTemplateCustomModels = getEmailTemplateCustomModels(emailTemplateType);

			UserServiceModel userServiceModel = getUserServiceModel(emailTemplateType
					.getModifiedBy());

			EmailTemplateTypeCustomModel emailTemplateTypeCustomModel = getEmailTemplateTypeCustomModel(
					emailTemplateType, emailTemplateCustomModels,
					userServiceModel);

			EmailTemplateTypeGetByIdVO emailTemplateTypeGetByIdVO = new EmailTemplateTypeGetByIdVO(
					emailTemplateTypeCustomModel);

			response = new com.wineaccess.response.SuccessResponse(
					emailTemplateTypeGetByIdVO, 200);

		} else {
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND,
					SystemErrorCode.EMAIL_TEMPLATE_TYPE_NO_RECORD_FOUND_TEXT,
					200);
		}

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	
	public static Map<String, Object> getEmailTemplateTypesByKeyword(
			final String keyword, final String sortBy,
			final int offSet, final int limit, final int sortOrder) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		List<EmailTemplateType> emailTemplateTypes = EmailTemplateTypeRepository
				.getByKeyword(keyword,
						sortBy,sortOrder, offSet-1, limit);
		int count = EmailTemplateTypeRepository.countRecordsForQuery(keyword);
		
		EmailTemplateTypeSearchVO emailTemplateTypeSearchVO = new EmailTemplateTypeSearchVO(offSet,limit,keyword,count,EmailTemplateTypeRepository.countAllRecords());

		if (emailTemplateTypes != null && !emailTemplateTypes.isEmpty()) {
			Map<Long,UserServiceModel> userModelMap = new HashMap<Long,UserServiceModel>();
			for (EmailTemplateType emailTemplateType : emailTemplateTypes) {
					EmailTemplateTypeSearch search = new EmailTemplateTypeSearch(emailTemplateType);
					
					if(userModelMap.get(emailTemplateType.getModifiedBy()) != null){
						search.setUserServiceModel(userModelMap.get(emailTemplateType.getModifiedBy()));
					}else{
						
						UserServiceModel userServiceModel = getUserServiceModel(emailTemplateType.getModifiedBy());
						userModelMap.put(emailTemplateType.getModifiedBy(), userServiceModel);
						search.setUserServiceModel(userServiceModel);
						
					}
					emailTemplateTypeSearchVO.getSearchResult().add(search);
			

			}

			

			

		}
		
		response = new com.wineaccess.response.SuccessResponse(
				emailTemplateTypeSearchVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}

	private static EmailTemplateTypeCustomModel getEmailTemplateTypeCustomModel(
			EmailTemplateType emailTemplateType,
			List<EmailTemplateCustomModel> emailTemplateCustomModels,
			UserServiceModel userServiceModel) {

		EmailTemplateTypeCustomModel emailTemplateTypeCustomModel = new EmailTemplateTypeCustomModel(
				emailTemplateType.getId(), emailTemplateType.getName(),
				emailTemplateType.getDescription(),
				emailTemplateType.isStatus(), emailTemplateCustomModels,
				userServiceModel, emailTemplateType.getModifiedDate(), emailTemplateType.getLabel());

		return emailTemplateTypeCustomModel;
	}

	private static EmailTemplateCustomModel getEmailTemplateCustomModel(
			EmailTemplate emailTemplate, UserServiceModel userServiceModel) {

		EmailTemplateCustomModel emailTemplateCustomModel = new EmailTemplateCustomModel(
				emailTemplate.getId(), emailTemplate.getName(),
				emailTemplate.getSubject(), emailTemplate.getBody(),
				userServiceModel, emailTemplate.getModifiedDate(),
				emailTemplate.getFromEmail(), emailTemplate.isActive());

		return emailTemplateCustomModel;
	}

	private static List<EmailTemplateCustomModel> getEmailTemplateCustomModels(
			EmailTemplateType emailTemplateType) {

		List<EmailTemplateCustomModel> emailTemplateCustomModels = new ArrayList<EmailTemplateCustomModel>();

		Set<EmailTemplate> emailTemplates = emailTemplateType
				.getEmailTemplates();

		for (EmailTemplate emailTemplate : emailTemplates) {

			//if (BooleanUtils.isFalse(emailTemplate.isDeleted())) {

				UserServiceModel userServiceModel = getUserServiceModel(emailTemplate
						.getModifiedBy());

				EmailTemplateCustomModel emailTemplateCustomModel = getEmailTemplateCustomModel(
						emailTemplate, userServiceModel);

				emailTemplateCustomModels.add(emailTemplateCustomModel);
			//}
		}

		return emailTemplateCustomModels;
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
