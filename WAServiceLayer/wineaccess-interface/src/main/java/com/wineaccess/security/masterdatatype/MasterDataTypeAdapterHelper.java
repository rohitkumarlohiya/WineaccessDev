package com.wineaccess.security.masterdatatype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.data.model.common.MasterDataTypeRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.response.Response;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * 
 * @author rohit.lohiya
 * 
 */
public class MasterDataTypeAdapterHelper {

	public static Map<String, Object> listMasterDataTypes(String status) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		List<MasterDataType> masterDataTypes = MasterDataTypeRepository
				.getMasterDataTypes(status);

		if (masterDataTypes != null && masterDataTypes.size() > 0) {
			List<MasterDataTypeCustomModel> customListVOs = new ArrayList<MasterDataTypeCustomModel>();

			for (MasterDataType masterDataType : masterDataTypes) {

				List<MasterDataCustomModel> masterDataCustomModels = new ArrayList<MasterDataCustomModel>();

				Set<MasterData> masterDatas = masterDataType.getMasterData();

				for (MasterData masterData : masterDatas) {
					
					UserModel userModel = UserRepository.getByUserId(masterData.getModifiedBy());
					UserServiceModel userServiceModel = null;
					if(userModel != null)
					{
						userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
					}
					
					MasterDataCustomModel masterDataCustomModel = new MasterDataCustomModel(
							masterData.getId(), masterData.getName(),userServiceModel,masterData.getModifiedDate());
					masterDataCustomModels.add(masterDataCustomModel);
				}
				
				UserModel userModel = UserRepository.getByUserId(masterDataType.getModifiedBy());
				UserServiceModel userServiceModel = null;
				if(userModel != null)
				{
					userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
				}

				MasterDataTypeCustomModel masterDataTypeCustomModel = new MasterDataTypeCustomModel(
						masterDataType.getId(), masterDataType.getName(),
						masterDataType.getDescription(),
						masterDataType.isStatus(), masterDataCustomModels,userServiceModel,masterDataType.getModifiedDate(), masterDataType.getLabel());
				customListVOs.add(masterDataTypeCustomModel);
			}

			MasterDataTypeListVO masterDataTypeListVO = new MasterDataTypeListVO(
					customListVOs);

			response = new com.wineaccess.response.SuccessResponse(
					masterDataTypeListVO, 200);

		} else {

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.NO_RECORD_FOUND,
					SystemErrorCode.NO_RECORD_FOUND_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);

		return output;
	}

	public static Map<String, Object> getMasterDataTypeById(
			Long masterDataTypeId) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		MasterDataType masterDataType = MasterDataTypeRepository
				.getMasterDataTypeById(masterDataTypeId);

		if (masterDataType != null) {
			List<MasterDataCustomModel> masterDataCustomModels = new ArrayList<MasterDataCustomModel>();
			Set<MasterData> masterDatas = masterDataType.getMasterData();

			for (MasterData masterData : masterDatas) {
				
				UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
				UserServiceModel userServiceModel = null;
				if(userModel != null)
				{
					userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
				}
				
				MasterDataCustomModel masterDataCustomModel = new MasterDataCustomModel(
						masterData.getId(), masterData.getName(),userServiceModel,masterData.getModifiedDate());
				masterDataCustomModels.add(masterDataCustomModel);
			}

			UserModel userModel = UserRepository.findUserByUserId(masterDataType.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}
			
			MasterDataTypeCustomModel masterDataTypeCustomModel = new MasterDataTypeCustomModel(
					masterDataType.getId(), masterDataType.getName(),
					masterDataType.getDescription(), masterDataType.isStatus(),
					masterDataCustomModels,userServiceModel,masterDataType.getModifiedDate(), masterDataType.getLabel());

			MasterDataTypeGetByIdVO masterDataTypeGetByIdVO = new MasterDataTypeGetByIdVO(
					masterDataTypeCustomModel);

			response = new com.wineaccess.response.SuccessResponse(
					masterDataTypeGetByIdVO, 200);

		} else {

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.NO_RECORD_FOUND,
					SystemErrorCode.NO_RECORD_FOUND_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	
	public static Map<String, Object> getMasterDataTypeByName(String masterDataTypeName) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		MasterDataType masterDataType = MasterDataTypeRepository.getMasterDataTypeByName(masterDataTypeName);

		if (masterDataType != null) {
			List<MasterDataCustomModel> masterDataCustomModels = new ArrayList<MasterDataCustomModel>();
			Set<MasterData> masterDatas = masterDataType.getMasterData();

			for (MasterData masterData : masterDatas) {
				
				UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
				UserServiceModel userServiceModel = null;
				if(userModel != null)
				{
					userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
				}
				
				MasterDataCustomModel masterDataCustomModel = new MasterDataCustomModel(
						masterData.getId(), masterData.getName(),userServiceModel,masterData.getModifiedDate());
				masterDataCustomModels.add(masterDataCustomModel);
			}

			UserModel userModel = UserRepository.findUserByUserId(masterDataType.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}
			
			MasterDataTypeCustomModel masterDataTypeCustomModel = new MasterDataTypeCustomModel(
					masterDataType.getId(), masterDataType.getName(),
					masterDataType.getDescription(), masterDataType.isStatus(),
					masterDataCustomModels,userServiceModel,masterDataType.getModifiedDate(), masterDataType.getLabel());

			MasterDataTypeGetByIdVO masterDataTypeGetByIdVO = new MasterDataTypeGetByIdVO(
					masterDataTypeCustomModel);

			response = new com.wineaccess.response.SuccessResponse(
					masterDataTypeGetByIdVO, 200);

		} else {

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.NO_RECORD_FOUND,
					SystemErrorCode.NO_RECORD_FOUND_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	public static Set<MasterData> getMasterDataListByMasterTypeName(String masterDataTypeName) {

		MasterDataType masterDataType = MasterDataTypeRepository.getMasterDataTypeByName(masterDataTypeName);

		if (masterDataType != null) {
			
			return masterDataType.getMasterData();
		}
		else
			return null;
	}
}
