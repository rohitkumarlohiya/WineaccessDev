package com.wineaccess.warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.common.MasterDataModel;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CityRepository;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.StateRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;

public class WarehouseAdapterHelper {

	private static Log logger = LogFactory.getLog(WarehouseAdapterHelper.class);
	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";

	/**
	 * 
	 * @return -adds the importer details to database. If everything works fine then returns a success message: "Importer added successfully."
	 */
	public static  Map<String, Object> addWarehouse(AddWarehousePO addWarehousePO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		try{
			final String warehouseName = addWarehousePO.getWarehouseName();
			final String addAddress1 = addWarehousePO.getAddressLine1();
			final String addAddress2 = addWarehousePO.getAddressLine2();
			final String cityId = addWarehousePO.getCityId();
			final String emailId = addWarehousePO.getEmailId();
			final String faxNumber = addWarehousePO.getFaxNumber();
			final String firstName = addWarehousePO.getFirstName();
			final String lastName = addWarehousePO.getLastName();
			final String phoneNumber = addWarehousePO.getPhone();
			final String stateId = addWarehousePO.getStateId();
			final String zipCode = addWarehousePO.getZipcode();
			final String freightRegion = addWarehousePO.getFreightRegion();
			final String isNonWSTransportCarrier = addWarehousePO.getIsNonWSTransportCarrier();

			StateModel state = null;
			CityModel city = null;
			AddWarehouseVO addWarehouseVO = new AddWarehouseVO();
			WarehouseDetails warehouseDetails = new WarehouseDetails();
			MasterData masterData = null;
			
			MasterData carrier = null;
			
			//check if isNonWSTransportCarrier is true then carrierId is required.
			if(Boolean.parseBoolean(isNonWSTransportCarrier))
			{
				if(addWarehousePO.getCarrierId() == null)
				{
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_CARRIER_REQUIRED, SystemErrorCode.WAREHOUSE_ERROR_CARRIER_REQUIRED_TEXT, 200);
				}
				else
				{
					carrier = MasterDataRepository.getMasterDataById(Long.parseLong(addWarehousePO.getCarrierId()));
					if(null == carrier){
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_CARRIER_NOT_FOUND, SystemErrorCode.WAREHOUSE_ERROR_CARRIER_NOT_FOUND_TEXT, 200);
					}
				}
			}

			WarehouseModel existingWarehouse = null;
			if(isResponseNull(response)){
				//Checking if any warehouse with the same name already exists in the database.
				existingWarehouse = WarehouseRepository.getWarehouseByName(warehouseName);
				if(null != existingWarehouse) {
	
					//Checking if the record is in deleted state or not. Deleted record can be overrided whereas, non deleted record can not be overrided.
					if(!existingWarehouse.getIsDeleted()){
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_117, SystemErrorCode.WAREHOUSE_ERROR_117_TEXT, 200);
					}
				}
			}

			if(isResponseNull(response)){
				List<CityModel> cityList = CityRepository.getById(Long.parseLong(cityId));
				if(null == cityList || cityList.size() < 1){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_118, SystemErrorCode.WAREHOUSE_ERROR_118_TEXT, 200);
				} else {
					city = cityList.get(0);
				}
			}

			if(isResponseNull(response)){
				List<StateModel> stateList = StateRepository.getById(Long.parseLong(stateId));
				if(null == stateList || stateList.size() < 1){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_119, SystemErrorCode.WAREHOUSE_ERROR_119_TEXT, 200);
				} else {
					state = stateList.get(0);
				}
			}

			if(isResponseNull(response)){
				masterData = MasterDataRepository.getMasterDataById(Long.parseLong(freightRegion));
				if(null == masterData){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_129, SystemErrorCode.WAREHOUSE_ERROR_129_TEXT, 200);
				}
			}

			if(isResponseNull(response)){
				if(null != existingWarehouse) {
					//Override existing record.
					//Copying data from PO to DO
					existingWarehouse.setAddressLine1(addAddress1);
					existingWarehouse.setAddressLine2(addAddress2);
					existingWarehouse.setCityId(city);
					existingWarehouse.setEmailId(emailId);
					existingWarehouse.setFaxNumber(faxNumber);
					existingWarehouse.setFirstName(firstName);
					existingWarehouse.setLastName(lastName);
					existingWarehouse.setName(warehouseName);
					existingWarehouse.setPhone(phoneNumber);
					existingWarehouse.setStateId(state);
					existingWarehouse.setZipcode(zipCode);
					existingWarehouse.setIsDeleted(false);
					existingWarehouse.setUniqueAddressHash(convertTomd5(existingWarehouse));
					existingWarehouse.setFreightRegion(masterData);
					existingWarehouse.setIsNonWSTransportCarrier(Boolean.parseBoolean(isNonWSTransportCarrier));
					existingWarehouse.setCarrier(carrier);
					

					WarehouseRepository.update(existingWarehouse);

					addWarehouseVO = getAddWarehouseResponse(existingWarehouse, warehouseDetails, city, state, addWarehouseVO);
					response = new com.wineaccess.response.SuccessResponse(addWarehouseVO, 200);
				} else{
					//Create new record.
					//Copying data from PO to DO
					WarehouseModel newWarehouse = new WarehouseModel();
					newWarehouse.setAddressLine1(addAddress1);
					newWarehouse.setAddressLine2(addAddress2);
					newWarehouse.setCityId(city);
					newWarehouse.setEmailId(emailId);
					newWarehouse.setFaxNumber(faxNumber);
					newWarehouse.setFirstName(firstName);
					newWarehouse.setLastName(lastName);
					newWarehouse.setName(warehouseName);
					newWarehouse.setPhone(phoneNumber);
					newWarehouse.setStateId(state);
					newWarehouse.setZipcode(zipCode);
					newWarehouse.setIsDeleted(false);
					newWarehouse.setUniqueAddressHash(convertTomd5(newWarehouse));
					newWarehouse.setFreightRegion(masterData);
					newWarehouse.setIsNonWSTransportCarrier(Boolean.parseBoolean(isNonWSTransportCarrier));
					newWarehouse.setCarrier(carrier);

					WarehouseRepository.save(newWarehouse);

					addWarehouseVO = getAddWarehouseResponse(newWarehouse, warehouseDetails, city, state, addWarehouseVO);
					response = new com.wineaccess.response.SuccessResponse(addWarehouseVO, 200);

				}
			}
		}
		catch(Exception e){

			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_116, SystemErrorCode.WAREHOUSE_ERROR_116_TEXT, 200);
		}
		finally{
			if(isResponseNull(response)){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_116, SystemErrorCode.WAREHOUSE_ERROR_116_TEXT, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}

	/**
	 * 
	 * @return -adds the importer details to database. If everything works fine then returns a success message: "Importer added successfully."
	 */
	public static  Map<String, Object> updateWarehouse(UpdateWarehousePO updateWarehousePO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		try{
			final String warehouseId = updateWarehousePO.getWarehouseId();
			final String warehouseName = updateWarehousePO.getWarehouseName();
			final String addAddress1 = updateWarehousePO.getAddressLine1();
			final String addAddress2 = updateWarehousePO.getAddressLine2();
			final String cityId = updateWarehousePO.getCityId();
			final String emailId = updateWarehousePO.getEmailId();
			final String faxNumber = updateWarehousePO.getFaxNumber();
			final String firstName = updateWarehousePO.getFirstName();
			final String lastName = updateWarehousePO.getLastName();
			final String phoneNumber = updateWarehousePO.getPhone();
			final String stateId = updateWarehousePO.getStateId();
			final String zipCode = updateWarehousePO.getZipcode();
			final String freightRegion = updateWarehousePO.getFreightRegion();
			final String isNonWSTransportCarrier = updateWarehousePO.getIsNonWSTransportCarrier();

			StateModel state = null;
			CityModel city = null;
			WarehouseModel existingWarehouse = null;
			UpdateWarehouseVO updateWarehouseVO = new UpdateWarehouseVO();
			WarehouseDetails warehouseDetails = new WarehouseDetails();
			MasterData masterData = null;
			
			MasterData carrier = null;
			
			//check if isNonWSTransportCarrier is true then carrierId is required.
			if(Boolean.parseBoolean(isNonWSTransportCarrier))
			{
				if(updateWarehousePO.getCarrierId() == null)
				{
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_CARRIER_REQUIRED, SystemErrorCode.WAREHOUSE_ERROR_CARRIER_REQUIRED_TEXT, 200);
				}
				else
				{
					carrier = MasterDataRepository.getMasterDataById(Long.parseLong(updateWarehousePO.getCarrierId()));
					if(null == carrier){
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_CARRIER_NOT_FOUND, SystemErrorCode.WAREHOUSE_ERROR_CARRIER_NOT_FOUND_TEXT, 200);
					}
				}
			}			

			WarehouseModel warehouseModel = null;
			if(isResponseNull(response)){
				warehouseModel = WarehouseRepository.getWarehouseById(Long.parseLong(warehouseId));
				if(warehouseModel == null) {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_122, SystemErrorCode.WAREHOUSE_ERROR_122_TEXT, 200);
				}
			}

			if(isResponseNull(response)){

				//Checking if any warehouse with the same name already exists in the database.
				existingWarehouse = WarehouseRepository.getWarehouseByName(warehouseName);
				if(null != existingWarehouse) {
					//Record Exists with the same name.
					if(existingWarehouse.getId() != warehouseModel.getId()){
						//Separate Record exist with the same name.
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_117, SystemErrorCode.WAREHOUSE_ERROR_117_TEXT, 200);
					} 				
				} else {
					//Record does not exists with the same name.
					warehouseModel.setName(updateWarehousePO.getWarehouseName());
				}
			}

			if(isResponseNull(response)){
				List<CityModel> cityList = CityRepository.getById(Long.parseLong(cityId));
				if(null == cityList || cityList.size() < 1){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_118, SystemErrorCode.WAREHOUSE_ERROR_118_TEXT, 200);
				} else {
					city = cityList.get(0);
				}
			}

			if(isResponseNull(response)){
				List<StateModel> stateList = StateRepository.getById(Long.parseLong(stateId));
				if(null == stateList || stateList.size() < 1){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_119, SystemErrorCode.WAREHOUSE_ERROR_119_TEXT, 200);
				} else {
					state = stateList.get(0);
				}
			}

			if(isResponseNull(response)){
				masterData = MasterDataRepository.getMasterDataById(Long.parseLong(freightRegion));
				if(null == masterData){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_129, SystemErrorCode.WAREHOUSE_ERROR_129_TEXT, 200);
				}
			}

			if(isResponseNull(response)){
				//Override existing record.
				//Copying data from PO to DO
				warehouseModel.setAddressLine1(addAddress1);
				if(null != addAddress2){
					warehouseModel.setAddressLine2(addAddress2);	
				}					
				warehouseModel.setCityId(city);
				if(null != emailId){
					warehouseModel.setEmailId(emailId);	
				}				
				
					warehouseModel.setFaxNumber(faxNumber);	
				
				
					warehouseModel.setFirstName(firstName);	
				
				
					warehouseModel.setLastName(lastName);
				
				
					warehouseModel.setPhone(phoneNumber);
				
				warehouseModel.setName(warehouseName);
				warehouseModel.setStateId(state);
				warehouseModel.setZipcode(zipCode);
				warehouseModel.setUniqueAddressHash(convertTomd5(warehouseModel));
				warehouseModel.setFreightRegion(masterData);
				warehouseModel.setIsNonWSTransportCarrier(Boolean.parseBoolean(isNonWSTransportCarrier));
				warehouseModel.setCarrier(carrier);

				WarehouseRepository.update(warehouseModel);

				updateWarehouseVO = getUpdateWarehouseResponse(warehouseModel, warehouseDetails, city, state, updateWarehouseVO);
				response = new com.wineaccess.response.SuccessResponse(updateWarehouseVO, 200);
			} 
		}
		catch(Exception e){

			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_123, SystemErrorCode.WAREHOUSE_ERROR_123_TEXT, 200);
		}
		finally{
			if(isResponseNull(response)){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_123, SystemErrorCode.WAREHOUSE_ERROR_123_TEXT, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}

	/**
	 * 
	 * @return -adds the importer details to database. If everything works fine then returns a success message: "Importer added successfully."
	 */
	public static  Map<String, Object> viewWarehouse(ViewWarehousePO viewWarehousePO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		try{
			final String warehouseId = viewWarehousePO.getWarehouseId();

			WarehouseModel warehouse = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(warehouseId));
			if(warehouse == null){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_125, SystemErrorCode.WAREHOUSE_ERROR_125_TEXT, 200);
			} else{
				WarehouseDetails warehouseDetails = new WarehouseDetails();
				//Getting city data
				CityModel city = warehouse.getCityId();

				//Getting state data
				StateModel state = warehouse.getStateId();

				String [] toBeIgnoredValues = {"cityId","stateId", "freightRegion","carrier"};
				BeanUtils.copyProperties(warehouse,warehouseDetails,toBeIgnoredValues);

				Map<String,String> cityMap = new HashMap<String, String>(); 
				cityMap.put("id", city.getId().toString());
				cityMap.put("name", city.getCityName());
				warehouseDetails.setCityId(cityMap);

				Map<String,String> stateMap = new HashMap<String, String>();
				stateMap.put("id", state.getId().toString());
				stateMap.put("name", state.getStateName());
				warehouseDetails.setStateId(stateMap);

				MasterDataModel masterDataModel = new MasterDataModel();
				masterDataModel.setId(warehouse.getFreightRegion().getId());
				masterDataModel.setMasterDataName(warehouse.getFreightRegion().getName());
				masterDataModel.setMasterDataTypeName(warehouse.getFreightRegion().getMasterDataType().getName());

				warehouseDetails.setFreightRegion(masterDataModel);		
				
				MasterDataModel carrier = null;
				if(warehouse.getCarrier() != null)
				{
					carrier = new MasterDataModel();
					carrier.setId(warehouse.getCarrier().getId());
					carrier.setMasterDataName(warehouse.getCarrier().getName());
					carrier.setMasterDataTypeName(warehouse.getCarrier().getMasterDataType().getName());
				}
				warehouseDetails.setCarrier(carrier);

				response = new com.wineaccess.response.SuccessResponse(warehouseDetails, 200);
			}
		}
		catch(Exception e){

			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_124, SystemErrorCode.WAREHOUSE_ERROR_124_TEXT, 200);
		}
		finally{
			if(isResponseNull(response)){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_124, SystemErrorCode.WAREHOUSE_ERROR_124_TEXT, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}

	/**
	 * 
	 * @return -adds the importer details to database. If everything works fine then returns a success message: "Importer added successfully."
	 */
	public static  Map<String, Object> deleteWarehouse(DeleteWarehousePO deleteWarehousePO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		try{
			if(deleteWarehousePO == null){

				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_126, SystemErrorCode.WAREHOUSE_ERROR_126_TEXT, 200);
			}

			if(isResponseNull(response)){
				List<Long> warehouseList = deleteWarehousePO.getWarehouseIds();

				Boolean isForceDelete = false;
				if(null != deleteWarehousePO.getIsForceDelete()){
					isForceDelete = Boolean.parseBoolean(deleteWarehousePO.getIsForceDelete());
				}

				BulkDeleteModel<WarehouseModel> bulkDeleteModel = WarehouseRepository.delete(warehouseList,isForceDelete);

				/**
				 * Getting the list of deleted list and setting it to VO
				 * */

				DeleteVO<WarehouseDetails> warehouseCustomModelsForDependency = new DeleteVO<WarehouseDetails>();
				DeleteVO<WarehouseDetails> warehouseCustomModelsForCanBeDeleted = new DeleteVO<WarehouseDetails>();

				List<WarehouseDetails> deleteList = new ArrayList<WarehouseDetails>();
				List<WarehouseDetails> dependencyList = new ArrayList<WarehouseDetails>();

				List<WarehouseModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

				for(WarehouseModel warehouse: canBeDeletedList)
				{						
					WarehouseDetails warehouseDetails = new WarehouseDetails(); 

					try
					{
						deleteList.add(getWarehouseDetails(warehouse,warehouseDetails));
					}		
					catch(Exception e){
						logger.error("Error while copying warehouse list to VO." + e);
					}						
				}

				warehouseCustomModelsForCanBeDeleted.setElements(deleteList);


				/**
				 * Getting the list of non deleted list and setting it to VO
				 * */
				List<WarehouseModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

				for(WarehouseModel warehouse: canNotBeDeletedList)
				{						
					WarehouseDetails warehouseDetails = new WarehouseDetails(); 

					try
					{
						dependencyList.add(getWarehouseDetails(warehouse,warehouseDetails));
					}		
					catch(Exception e){
						logger.error("Error while copying warehouse list to VO." + e);
					}						
				}

				warehouseCustomModelsForDependency.setElements(dependencyList);

				/**
				 * Getting the list of non existing list and setting it to VO
				 * */
				List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();

				DeleteWarehouseVO warehouseBulkDeleteVO = new DeleteWarehouseVO();
				warehouseBulkDeleteVO.setNonExistsList(nonExistingList);
				warehouseBulkDeleteVO.setFailureList(warehouseCustomModelsForDependency);
				warehouseBulkDeleteVO.setSuccessList(warehouseCustomModelsForCanBeDeleted);	

				response = new com.wineaccess.response.SuccessResponse(warehouseBulkDeleteVO, 200);
			}
		}
		catch(Exception e){

			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_131, SystemErrorCode.WAREHOUSE_ERROR_131_TEXT, 200);
		}
		finally{
			if(isResponseNull(response)){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.WAREHOUSE_ERROR_131, SystemErrorCode.WAREHOUSE_ERROR_131_TEXT, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}


	private static Boolean isResponseNull(Response response){

		Boolean validatedResponse = true;	
		if(null != response){
			validatedResponse = false;
		}
		return validatedResponse;
	}

	private static AddWarehouseVO getAddWarehouseResponse(WarehouseModel warehouseModel, WarehouseDetails warehouseDetails, CityModel city, StateModel state, AddWarehouseVO addWarehouseVO){
		String [] toBeIgnoredValues = {"cityId","stateId", "freightRegion","carrier"};
		BeanUtils.copyProperties(warehouseModel,warehouseDetails,toBeIgnoredValues);

		Map<String,String> cityMap = new HashMap<String, String>(); 
		cityMap.put("id", city.getId().toString());
		cityMap.put("name", city.getCityName());
		warehouseDetails.setCityId(cityMap);

		Map<String,String> stateMap = new HashMap<String, String>();
		stateMap.put("id", state.getId().toString());
		stateMap.put("name", state.getStateName());
		warehouseDetails.setStateId(stateMap);

		MasterDataModel masterDataModel = new MasterDataModel();
		masterDataModel.setId(warehouseModel.getFreightRegion().getId());
		masterDataModel.setMasterDataName(warehouseModel.getFreightRegion().getName());
		masterDataModel.setMasterDataTypeName(warehouseModel.getFreightRegion().getMasterDataType().getName());

		warehouseDetails.setFreightRegion(masterDataModel);
		
		
		MasterDataModel carrier = null;
		if(warehouseModel.getCarrier() != null)
		{
			carrier = new MasterDataModel();
			carrier.setId(warehouseModel.getCarrier().getId());
			carrier.setMasterDataName(warehouseModel.getCarrier().getName());
			carrier.setMasterDataTypeName(warehouseModel.getCarrier().getMasterDataType().getName());
		}
		warehouseDetails.setCarrier(carrier);

		addWarehouseVO.setWarehouseDetails(warehouseDetails);
		addWarehouseVO.setMessage("New warehouse added successfully.");
		return addWarehouseVO;
	}

	private static UpdateWarehouseVO getUpdateWarehouseResponse(WarehouseModel warehouseModel, WarehouseDetails warehouseDetails, CityModel city, StateModel state, UpdateWarehouseVO updateWarehouseVO){
		String [] toBeIgnoredValues = {"cityId","stateId", "freightRegion","carrier"};
		BeanUtils.copyProperties(warehouseModel,warehouseDetails,toBeIgnoredValues);

		Map<String,String> cityMap = new HashMap<String, String>(); 
		cityMap.put("id", city.getId().toString());
		cityMap.put("name", city.getCityName());
		warehouseDetails.setCityId(cityMap);

		Map<String,String> stateMap = new HashMap<String, String>();
		stateMap.put("id", state.getId().toString());
		stateMap.put("name", state.getStateName());
		warehouseDetails.setStateId(stateMap);

		MasterDataModel masterDataModel = new MasterDataModel();
		masterDataModel.setId(warehouseModel.getFreightRegion().getId());
		masterDataModel.setMasterDataName(warehouseModel.getFreightRegion().getName());
		masterDataModel.setMasterDataTypeName(warehouseModel.getFreightRegion().getMasterDataType().getName());

		warehouseDetails.setFreightRegion(masterDataModel);	
		
		MasterDataModel carrier = null;
		if(warehouseModel.getCarrier() != null)
		{
			carrier = new MasterDataModel();
			carrier.setId(warehouseModel.getCarrier().getId());
			carrier.setMasterDataName(warehouseModel.getCarrier().getName());
			carrier.setMasterDataTypeName(warehouseModel.getCarrier().getMasterDataType().getName());
		}
		warehouseDetails.setCarrier(carrier);

		updateWarehouseVO.setWarehouseId(warehouseModel.getId());
		updateWarehouseVO.setWarehouseDetails(warehouseDetails);
		updateWarehouseVO.setMessage("Warehouse updated successfully.");
		return updateWarehouseVO;
	}

	private static String convertTomd5(WarehouseModel warehouse){
		String addressLine2 = warehouse.getAddressLine2();
		if(null == warehouse.getAddressLine2()){
			addressLine2 = "";
		}
		String md5Value = ApplicationUtils.generateUniqueHash(warehouse.getAddressLine1(), addressLine2, warehouse.getCityId(), warehouse.getStateId(), warehouse.getZipcode());

		return md5Value;
	}

	private static WarehouseDetails getWarehouseDetails(WarehouseModel warehouse, WarehouseDetails warehouseDetails) {

		String [] toBeIgnoredValues = {"cityId","stateId", "freightRegion","carrier"};
		BeanUtils.copyProperties(warehouse,warehouseDetails,toBeIgnoredValues);		

		Map<String,String> city = new HashMap<String,String>();
		city.put("id", warehouse.getCityId().getId().toString());
		city.put("name", warehouse.getCityId().getCityName());

		Map<String,String> state = new HashMap<String,String>();
		state.put("id", warehouse.getStateId().getId().toString());
		state.put("name", warehouse.getStateId().getStateName());

		MasterDataModel masterDataModel = new MasterDataModel();
		masterDataModel.setId(warehouse.getFreightRegion().getId());
		masterDataModel.setMasterDataName(warehouse.getFreightRegion().getName());
		masterDataModel.setMasterDataTypeName(warehouse.getFreightRegion().getMasterDataType().getName());

		warehouseDetails.setFreightRegion(masterDataModel);		
		
		MasterDataModel carrier = null;
		if(warehouse.getCarrier() != null)
		{
			carrier = new MasterDataModel();
			carrier.setId(warehouse.getCarrier().getId());
			carrier.setMasterDataName(warehouse.getCarrier().getName());
			carrier.setMasterDataTypeName(warehouse.getCarrier().getMasterDataType().getName());
		}
		warehouseDetails.setCarrier(carrier);
		
		warehouseDetails.setCityId(city);
		warehouseDetails.setStateId(state);

		return warehouseDetails;
	}

	/**
	 * this method is used to list the warehouse
	 * @param warehousePO
	 * @return Map the output map
	 */
	public static Map<String, Object> generateWarehouseList(final ListWarehousePO warehousePO) {

		logger.info("listing the warehouses");

		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		List<Long> exclusions = new ArrayList<Long>();
		for (String s : warehousePO.getExclusions()) {
			try {
				exclusions.add(Long.parseLong(s));
			} catch (NumberFormatException e) {
				logger.error("number format exception occurs during exclusion list");
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.LIST_WAREHOUSE_ERROR_105, SystemErrorCode.LIST_WAREHOUSE_ERROR_105_TXT, 200);
			}
		}
		if(response == null){
			final List<WarehouseModel> warehouseModels = WarehouseRepository.getAllByOffsetLimit(Integer.parseInt(warehousePO.getOffSet()) - 1,
					Integer.parseInt(warehousePO.getLimit()),warehousePO.getSortBy(),Integer.parseInt(warehousePO.getSortOrder()),exclusions, warehousePO.getKeyword());
			int totalCount = 0;
			final ListWarehouseVO listingVO = new ListWarehouseVO();
			listingVO.setKeyword(warehousePO.getKeyword());
			listingVO.setLimit(Integer.parseInt(warehousePO.getLimit()));
			listingVO.setOffSet(Integer.parseInt(warehousePO.getOffSet()));

			List<WarehouseListDetails> warehouseDetails = new ArrayList<WarehouseListDetails>();
			if (warehouseModels != null) {
				listingVO.setCount(WarehouseRepository.countRecords(warehousePO.getSortBy(),warehousePO.getKeyword()));
				totalCount = WarehouseRepository.getTotalCount();
				for (WarehouseModel model : warehouseModels) {
					WarehouseListDetails details = new WarehouseListDetails();
					CityModel city = model.getCityId();

					// Getting state data
					StateModel state = model.getStateId();

					String[] toBeIgnoredValues = { "cityId", "stateId" , "freightRegion","carrier"};
					BeanUtils.copyProperties(model, details, toBeIgnoredValues);

					Map<String, String> cityMap = new HashMap<String, String>();
					cityMap.put("id", city.getId().toString());
					cityMap.put("name", city.getCityName());
					cityMap.put("code", city.getCityCode());
					details.setCityId(cityMap);

					Map<String, String> stateMap = new HashMap<String, String>();
					stateMap.put("id", state.getId().toString());
					stateMap.put("name", state.getStateName());
					stateMap.put("code", state.getStateCode());
					details.setStateId(stateMap);

					MasterDataModel masterDataModel = new MasterDataModel();
					masterDataModel.setId(model.getFreightRegion().getId());
					masterDataModel.setMasterDataName(model.getFreightRegion().getName());
					masterDataModel.setMasterDataTypeName(model.getFreightRegion().getMasterDataType().getName());

					details.setFreightRegion(masterDataModel);	
					
					MasterDataModel carrier = null;
					if(model.getCarrier() != null)
					{
						carrier = new MasterDataModel();
						carrier.setId(model.getCarrier().getId());
						carrier.setMasterDataName(model.getCarrier().getName());
						carrier.setMasterDataTypeName(model.getCarrier().getMasterDataType().getName());
					}
					details.setCarrier(carrier);

					int wineryCount = WarehouseRepository.getWineryCount(model.getId());
					details.setWineryCount(wineryCount);
					warehouseDetails.add(details);
				}

			}
			listingVO.setTotalRecordCount(totalCount);
			if("wineryCount".equals(warehousePO.getSortBy())){
				Collections.sort(warehouseDetails,new Comparator<WarehouseListDetails>() {
					public int compare(WarehouseListDetails o1,WarehouseListDetails o2) {
						if(Integer.parseInt(warehousePO.getSortOrder()) == 1){
							return o1.getWineryCount() - o2.getWineryCount();
						}else{
							return o2.getWineryCount() - o1.getWineryCount();
						}
					}
				});
			}
			listingVO.setWarehouseList(warehouseDetails);

			response = new com.wineaccess.response.SuccessResponse(listingVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit listing the warehouses");

		return output;
	}
}
