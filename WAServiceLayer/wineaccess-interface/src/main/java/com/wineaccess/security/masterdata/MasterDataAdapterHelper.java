package com.wineaccess.security.masterdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.data.model.common.MasterDataTypeRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * 
 * @author rohit.lohiya
 * 
 */
public class MasterDataAdapterHelper {

	public static Map<String, Object> addMasterData(MasterDataPO masterDataPO) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;
		try {
			
			if(MasterDataRepository.isMasterDataExists(masterDataPO.getMasterDataTypeName(), masterDataPO.getName())){
				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.ADD_MASTER_DATA_ALREADY_EXITS,
						SystemErrorCode.ADD_MASTER_DATA_ALREADY_EXITS_TEXT, 200);
			}else{
				MasterDataType masterDataType = MasterDataTypeRepository
						.getMasterDataTypeByName(masterDataPO.getMasterDataTypeName());

			MasterData masterData = new MasterData();
			masterData.setMasterDataType(masterDataType);
			masterData.setName(masterDataPO.getName());

			MasterDataRepository.save(masterData);
			
		
			MasterDataRepository.updateMasterDataType(masterDataType);
			MasterDataAddVO masterDataAddVO = new MasterDataAddVO("Success");

				response = new com.wineaccess.response.SuccessResponse(
						masterDataAddVO, 200);	
			}
			
				
			

		} catch (Exception e) {

			e.printStackTrace();

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.ADD_MASTER_DATA_INVALID_PARAM,
					SystemErrorCode.ADD_MASTER_DATA_INVALID_PARAM_TEXT, 200);
		}

		finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	public static Map<String, Object> listMasterDatas() {

		Map<String, Object> output = new HashMap<String, Object>();

		List<MasterData> masterDatas = MasterDataRepository.getMasterDatas();

		List<MasterDataCustModel> masterDataCustomVOs = new ArrayList<MasterDataCustModel>();		
		
		for (MasterData masterData : masterDatas) {
			
			UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}
			
			MasterDataCustModel masterDataCustomVO = new MasterDataCustModel(
					masterData.getId(), masterData.getName(), masterData
					.getMasterDataType(),userServiceModel,masterData.getModifiedDate(), masterData.getMasterDataType().getLabel());
			masterDataCustomVOs.add(masterDataCustomVO);
		}

		MasterDataListVO masterDataListVO = new MasterDataListVO(
				masterDataCustomVOs);

		Response response = new com.wineaccess.response.SuccessResponse(
				masterDataListVO, 200);
		output.put("FINAL-RESPONSE", response);

		return output;
	}

	public static Map<String, Object> getMasterDataById(Long masterDataId) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;
		try {
			MasterData masterData = MasterDataRepository
					.getMasterDataById(masterDataId);

			UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}
			
			MasterDataCustModel masterDataCustomVO = new MasterDataCustModel(
					masterData.getId(), masterData.getName(), masterData
					.getMasterDataType(),userServiceModel,masterData.getModifiedDate(), masterData.getMasterDataType().getLabel());

			MasterDataGetByIdVO masterDataGetByIdVO = new MasterDataGetByIdVO(
					masterDataCustomVO);

			response = new com.wineaccess.response.SuccessResponse(
					masterDataGetByIdVO, 200);
			output.put("FINAL-RESPONSE", response);

		} catch (Exception e) {

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.MASTER_DATA_GET_BY_ID_INVALID_PARAM,
					SystemErrorCode.MASTER_DATA_GET_BY_ID_INVALID_PARAM_TEXT,
					200);
		} finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	public static Map<String, Object> updateMasterDataById(Long masterDataId,
			MasterDataPO masterDataPO) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		try {
			
			
			
			
			
			
			
			MasterData masterData = MasterDataRepository
					.getMasterDataById(masterDataId);

			MasterDataType masterDataType = MasterDataTypeRepository.getMasterDataTypeByName(masterDataPO.getMasterDataTypeName());
			
			if(masterData == null){
				response = ApplicationUtils
						.errorMessageGenerate(
								SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_INVALID_ID,
								SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_INVALID_ID_TEXT,
								200);
			}else{
						for(MasterData existingMasterData :masterDataType.getMasterData()){
							
							if(masterData.getId()!=existingMasterData.getId() && existingMasterData.getName().equals(masterDataPO.getName())){
								response = ApplicationUtils
										.errorMessageGenerate(
												SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_DUPLICATE_NAME,
												SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_DUPLICATE_NAME_TEXT,
												200);
							}
							
						}
			}
			
			
			if(response == null){
			
				if(masterDataType != null)
				{
					masterData.setName(masterDataPO.getName());
		
					masterData.setMasterDataType(masterDataType);
		
					MasterDataRepository.update(masterData);
					
					
					MasterDataRepository.updateMasterDataType(masterData.getMasterDataType());
		
					MasterDataUpdateVO masterDataUpdateVO = new MasterDataUpdateVO(
							"Success");
		
					response = new com.wineaccess.response.SuccessResponse(
							masterDataUpdateVO, 200);
					output.put("FINAL-RESPONSE", response);
				}
				else
				{
					response = ApplicationUtils
							.errorMessageGenerate(
									SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM,
									SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM_TEXT,
									200);
				}
			}	

		} catch (Exception e) {

			e.printStackTrace();

			response = ApplicationUtils
					.errorMessageGenerate(
							SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM,
							SystemErrorCode.MASTER_DATA_UPDATE_BY_ID_INVALID_PARAM_TEXT,
							200);

		} finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}

	public static Map<String, Object> deleteMasterDataById(Long masterDataId) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;

		MasterData masterData = MasterDataRepository
				.getMasterDataById(masterDataId);
		if (masterData != null) {
			try {
				MasterDataType masterDataType = MasterDataTypeRepository
						.getMasterDataTypeById(masterData.getMasterDataType().getId());
				
				MasterDataRepository.updateMasterDataType(masterDataType);
				MasterDataRepository.delete(masterData);
				
				

				MasterDataDeleteVO masterDataDeleteVO = new MasterDataDeleteVO(
						"Success");

				response = new com.wineaccess.response.SuccessResponse(
						masterDataDeleteVO, 200);
				output.put("FINAL-RESPONSE", response);

			} catch (Exception e) {

				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.MASTER_DATA_DELETE_BY_ID_IN_USE,
						SystemErrorCode.MASTER_DATA_DELETE_BY_ID_IN_USE_TEXT,
						200);
			} 
		} else {

			response = ApplicationUtils
					.errorMessageGenerate(
							SystemErrorCode.MASTER_DATA_DELETE_BY_ID_INVALID_PARAM,
							SystemErrorCode.MASTER_DATA_DELETE_BY_ID_INVALID_PARAM_TEXT,
							200);
		}

		output.put("FINAL-RESPONSE", response);
		return output;
	}
	
	
	
	public static Map<String, Object> getMasterDataLastUpdated(Long masterDataTypeId) {

		Map<String, Object> output = new HashMap<String, Object>();

		Response response = null;
		try {
			MasterDataType masterDataType = MasterDataTypeRepository.getMasterDataTypeById(masterDataTypeId);
			
			
			MasterData masterData = MasterDataRepository
					.getMasterDataLastUpdated(masterDataType);
			
			UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}

			MasterDataCustModel masterDataCustomVO = new MasterDataCustModel(
					masterData.getId(), masterData.getName(), masterData
					.getMasterDataType(),userServiceModel,masterData.getModifiedDate(), masterData.getMasterDataType().getLabel());

			MasterDataLastUpdatedVO masterDataLastUpdatedVO = new MasterDataLastUpdatedVO(
					masterDataCustomVO);

			response = new com.wineaccess.response.SuccessResponse(
					masterDataLastUpdatedVO, 200);
			output.put("FINAL-RESPONSE", response);

		} catch (Exception e) {

			e.printStackTrace();

			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.MASTER_DATA_GET_LAST_UPDATED_INVALID_PARAM,
					SystemErrorCode.MASTER_DATA_GET_LAST_UPDATED_INVALID_PARAM_TEXT,
					200);
		} finally {
			output.put("FINAL-RESPONSE", response);
		}

		return output;
	}
	
	public static Map<String, Object> multipleDeleteMasterData(String multipleMasterDataIds,boolean confirmStatus) {

		Map<String, Object> output = new HashMap<String, Object>();		
		
		Response response = null;
		//Set<MasterDataType> masterDataTypes = new HashSet<MasterDataType>();
		//masterDataTypes = MasterDataRepository.getMasterDataTypes(multipleMasterDataIds);
		
		
		BulkDeleteModel<MasterData> bulkDeleteModel = MasterDataRepository.multipleDeleteMasterData(multipleMasterDataIds,confirmStatus);
	
		List<MasterDataCustModel> masterDataCustModelsForDependency = new ArrayList<MasterDataCustModel>();		
		List<MasterDataCustModel> masterDataCustModelsForCanBeDeleted = new ArrayList<MasterDataCustModel>();				
				
		List<MasterData> masterDatas = bulkDeleteModel.getNotDeletedList();
		for(MasterData masterData : masterDatas)
		{
			
			UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}
			
			MasterDataCustModel masterDataCustomVO = new MasterDataCustModel(
					masterData.getId(), masterData.getName(), masterData
							.getMasterDataType(),userServiceModel,masterData.getModifiedDate(), masterData.getMasterDataType().getLabel());
			masterDataCustModelsForDependency.add(masterDataCustomVO);
		}	
				
		List<MasterData> masterDatasCanBeDeleted = bulkDeleteModel.getDeletedList();
		for(MasterData masterData : masterDatasCanBeDeleted)
		{
			
			UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}
			
			MasterDataCustModel masterDataCustomVO = new MasterDataCustModel(
					masterData.getId(), masterData.getName(), masterData
							.getMasterDataType(),userServiceModel,masterData.getModifiedDate(), masterData.getMasterDataType().getLabel());
			masterDataCustModelsForCanBeDeleted.add(masterDataCustomVO);
		}
		
		/*if(confirmStatus)
		{					
			for(MasterData masterData : masterDatasCanBeDeleted)
			{			
				MasterDataType masterDataType = masterData.getMasterDataType();
				
				MasterDataRepository.updateMasterDataType(masterDataType);
				MasterDataRepository.delete(masterData);
			}	
		}
		
		if(confirmStatus || (bulkDeleteModel.getNotDeletedList() != null && !bulkDeleteModel.getNotDeletedList().isEmpty()) || ((bulkDeleteModel.getNotExistsList() != null && !bulkDeleteModel.getNotExistsList().isEmpty()))){
			
		}*/
		MasterDataMultipleDeleteVO masterDataMultipleDeleteVO = new MasterDataMultipleDeleteVO(
				new MultipleDeleteModelForNotExists(bulkDeleteModel.getNotExistsList().size(),(List<Long>)(bulkDeleteModel.getNotExistsList())),
				new MultipleDeleteModelForDependency(bulkDeleteModel.getNotDeletedList().size(),masterDataCustModelsForDependency),
				new MultipleDeleteModelForDependency(bulkDeleteModel.getDeletedList().size(),masterDataCustModelsForCanBeDeleted));

		response = new com.wineaccess.response.SuccessResponse(masterDataMultipleDeleteVO, 200);
		
		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	
}
