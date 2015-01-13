package com.wineaccess.winery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v1.lang.BooleanUtils;
import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.StateRepository;
import com.wineaccess.importer.ImporterRepository;
import com.wineaccess.importer.ImporterWinery;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;
import com.wineaccess.util.command.State;
import com.wineaccess.warehouse.WarehouseModel;
import com.wineaccess.warehouse.WarehouseRepository;
/**
 * This class is used to perform the operations on Winery and WineryImpoter table in database 
 * @author rohit.lohiya
 *
 */
public class WineryAdapterHelper {

	//purpose of this logger variable is to log the error messages in log file.
	private static Log logger = LogFactory.getLog(WineryAdapterHelper.class);
	
	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY="FINAL-RESPONSE";
	public static final String ID="id";
	public static final String NAME="name";

	/**
	 * This method is used to add the winery and the mapping of the importer winery.
	 * @param wineryPO take the input parameter for adding the winery detail in database.
	 * @return output
	 */
	public static Map<String, Object> addWinery(final WineryPO wineryPO) {
		
		logger.info("start addWinery method");
		
		String errorMsg = "";

		Map<String, Object> output = new ConcurrentHashMap <String, Object>();
		
		Response response = null;		
		
		try {
			
			List<Long> importerIds = wineryPO.getImporterIds();			
			
			if(importerIds != null && !importerIds.isEmpty())
			{	
				if(wineryPO.getActiveImporterId() != null)
				{
					if(!importerIds.contains(wineryPO.getActiveImporterId()))
					{						
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_ADD_INVALID_ACTIVE_IMPORTER,
								SystemErrorCode.WINERY_ADD_INVALID_ACTIVE_IMPORTER_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid active importer ");						
					}	
				}
			}	
			
			WineryModel wineryModel = new WineryModel();	
			
			if(response == null)
			{			
				
				List<StateModel> stateModels = StateRepository.getById(wineryPO.getRegionId());
				
				if(stateModels != null && !stateModels.isEmpty())
				{
					wineryModel.setRegion(stateModels.get(0));	
				}
				else
				{				
					response = ApplicationUtils.errorMessageGenerate(
							SystemErrorCode.WINERY_ADD_INVALID_REGION,
							SystemErrorCode.WINERY_ADD_INVALID_REGION_TEXT, SUCCESS_CODE);
					
					logger.error("Invalid State ");				
				}
			}
			
			if(response == null)
			{
				MasterData masterData = MasterDataRepository.getMasterDataById(wineryPO.getSourcingStatusId());
				
				if(masterData != null)
				{
					wineryModel.setSourcingStatus(masterData);
				}
				else
				{				
					response = ApplicationUtils.errorMessageGenerate(
							SystemErrorCode.WINERY_ADD_INVALID_SOURCING_ID,
							SystemErrorCode.WINERY_ADD_INVALID_SOURCING_ID_TEXT, SUCCESS_CODE);
					
					logger.error("Invalid Sourcing ");					
				}
			}
				
			if(response == null)
			{
				if(wineryPO.getActiveImporterId() != null)
				{
					ImporterModel importerModel = ImporterRepository.getImporterById(wineryPO.getActiveImporterId());
				
					if(importerModel != null)
					{					
						wineryModel.setActiveImporterId(importerModel);
						importerModel.setIsEnabled(true);
						importerModel.setIsActive(true);
						ImporterRepository.update(importerModel);
					}
					else
					{					
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_ADD_INVALID_IMPORTER_ID,
								SystemErrorCode.WINERY_ADD_INVALID_IMPORTER_ID_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid importer ");						
					}
				}
			}
			
			if(response == null)
			{
				if(wineryPO.getImporterIds() != null)
				{			
					for (Long importerId : importerIds) {
						
						ImporterModel importerModelbyId = ImporterRepository.getImporterById(importerId);
						if(importerModelbyId == null)
						{						
							response = ApplicationUtils.errorMessageGenerate(
									SystemErrorCode.WINERY_ADD_INVALID_IMPORTER_ID,
									SystemErrorCode.WINERY_ADD_INVALID_IMPORTER_ID_TEXT, SUCCESS_CODE);
							
							logger.error("Invalid importer ");							
						}
					}
				}
			}
			
			if(response == null)
			{			
				if(wineryPO.getWaContact() != null) {
			
					MasterData waContact = MasterDataRepository.getMasterDataById(wineryPO.getWaContact());
					
					if(waContact != null)
					{
						wineryModel.setWaContact(waContact);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_ADD_INVALID_WA_CONTACT,
								SystemErrorCode.WINERY_ADD_INVALID_WA_CONTACT_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid WA Contact ");		
					}
				}
			}
			
			if(response == null)
			{						
				if(wineryPO.getFreightRegion() != null)
				{				
					MasterData freightRegion = MasterDataRepository.getMasterDataById(wineryPO.getFreightRegion());
					
					if(freightRegion != null)
					{
						wineryModel.setFreightRegion(freightRegion);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_ADD_INVALID_FREIGHT_REGION,
								SystemErrorCode.WINERY_ADD_INVALID_FREIGHT_REGION_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid Freight Region ");	
					}			
				}	
			}
			
			if(response == null)
			{						
				if(wineryPO.getWarehouseId() != null && !(StringUtils.EMPTY).equals(wineryPO.getWarehouseId()))
				{				
					WarehouseModel warehouseModel = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(wineryPO.getWarehouseId()));
					
					if(warehouseModel != null)
					{
						wineryModel.setWarehouseId(warehouseModel);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR,
								SystemErrorCode.WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR_TEXT, SUCCESS_CODE);
						
						logger.error("No warehouse details found ");	
					}			
				}	
			}
			
					
			if(response == null)
			{						
				wineryModel.setWineryName(wineryPO.getWineryName());
			//	wineryModel.setWineryCode(wineryPO.getWineryCode());			
				wineryModel.setIsEnabled(BooleanUtils.parseBoolean(wineryPO.getStatus()));
				wineryModel.setIsDeleted(false);	
				wineryModel.setAccountingCustomerNumber(wineryPO.getAccountingCustomerNumber());
				wineryModel.setWineryUrl(wineryPO.getWineryUrl());
				
				WineryModel wineryModelExit = WineryRepository.getWineryModelByWineryName(wineryModel.getWineryName());
				
				if (wineryModelExit != null) {
					response = ApplicationUtils.errorMessageGenerate(
							SystemErrorCode.WINERY_ADD_WINERY_NAME_DUPLICATE,
							SystemErrorCode.WINERY_ADD_WINERY_NAME_DUPLICATE_TEXT, SUCCESS_CODE);
					
					logger.error("Duplicate entry for winery name ");
					output.put(OUPUT_PARAM_KEY, response);
					return output;
				}
				
				WineryRepository.saveWinery(wineryModel,wineryPO);
				if(wineryModel.getActiveImporterId()!= null && wineryModel.getActiveImporterId().getId() != null ){
					ImporterRepository.updateWineryCount(wineryModel.getActiveImporterId().getId());
				}
					
				
				response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.WINERY_ADD_SUCCESS_TEXT, SUCCESS_CODE);
			}		
		}		
		catch (Exception e) {
			
			errorMsg = e.getCause().getMessage();
			
		}	
				
		if(errorMsg.contains("uk_winery_name"))
		{				
			WineryModel wineryModel = WineryRepository.getWineryModelByWineryName(wineryPO.getWineryName());	
			if(wineryModel.getIsDeleted())
			{
				wineryModel.setIsDeleted(false);
				WineryRepository.saveWinery(wineryModel,wineryPO);
				
				response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.WINERY_ADD_SUCCESS_TEXT, SUCCESS_CODE);
			}
			else
			{
				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.WINERY_ADD_WINERY_NAME_DUPLICATE,
						SystemErrorCode.WINERY_ADD_WINERY_NAME_DUPLICATE_TEXT, SUCCESS_CODE);
				
				logger.error("Duplicate entry for winery name ");
			}				
		}
		else if(errorMsg.contains("uk_winery_code"))
		{
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.WINERY_ADD_WINERY_CODE_DUPLICATE,
					SystemErrorCode.WINERY_ADD_WINERY_CODE_DUPLICATE_TEXT, SUCCESS_CODE);
			
			logger.error("Duplicate entry for winery code ");
		}					
			
		output.put(OUPUT_PARAM_KEY, response);
		
		logger.info("exit addWinery method");
		
		return output;
	}

	/**
	 * This method is used to update the winery based on the unique winery code
	 * @param wineryUpdatePO - take the input parameter for updating the winery and its mapping with the inporter
	 * @return output
	 */
	public static Map<String, Object> updateWinery(final WineryUpdatePO wineryUpdatePO) {
		
		logger.info("start updateWinery method");
		
		String errorMsg = "";

		Map<String, Object> output = new ConcurrentHashMap <String, Object>();

		Response response = null;	
		
		try {
			
			List<Long> importerIds = wineryUpdatePO.getImporterIds();			
			
			if(importerIds != null && !importerIds.isEmpty())
			{			
				if(wineryUpdatePO.getActiveImporterId() != null)
				{
					if(!importerIds.contains(wineryUpdatePO.getActiveImporterId()))
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_UPDATE_INVALID_ACTIVE_IMPORTER,
								SystemErrorCode.WINERY_UPDATE_INVALID_ACTIVE_IMPORTER_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid active importer ");		
					}	
				}		
			}
						
			WineryModel wineryModel = WineryRepository.getWineryById(wineryUpdatePO.getWineryId());
			
			if (wineryModel == null) {
				response = ApplicationUtils.errorMessageGenerate(
						SystemErrorCode.INVALID_WINERY,
						SystemErrorCode.INVALID_WINERY_TEXT, SUCCESS_CODE);	
				output.put(OUPUT_PARAM_KEY, response);		
				return output;
			}
			
			if(response == null)
			{				
				List<StateModel> stateModels = StateRepository.getById(wineryUpdatePO.getRegionId());
				
				if(stateModels != null && !stateModels.isEmpty())
				{
					wineryModel.setRegion(stateModels.get(0));	
				}
				else
				{
					response = ApplicationUtils.errorMessageGenerate(
							SystemErrorCode.WINERY_UPDATE_INVALID_REGION,
							SystemErrorCode.WINERY_UPDATE_INVALID_REGION_TEXT, SUCCESS_CODE);			
					
					logger.error("Invalid State ");
				}
			}
			
			if(response == null)
			{
				if(wineryUpdatePO.getSourcingStatusId() == null){
					wineryModel.setSourcingStatus(null);
				}else{
					
					MasterData masterData = MasterDataRepository.getMasterDataById(wineryUpdatePO.getSourcingStatusId());
					
					if(masterData != null)
					{
						wineryModel.setSourcingStatus(masterData);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_UPDATE_INVALID_SOURCING_ID,
								SystemErrorCode.WINERY_UPDATE_INVALID_SOURCING_ID_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid Sourcing ");
					}
					
				}
				
			}
			
			if(response == null)
			{
				if(wineryUpdatePO.getActiveImporterId() != null)
				{
					ImporterModel importerModel = ImporterRepository.getImporterById(wineryUpdatePO.getActiveImporterId());
				
					if(importerModel != null)
					{					
						wineryModel.setActiveImporterId(importerModel);
						importerModel.setIsActive(true);
						importerModel.setIsEnabled(true);
						ImporterRepository.update(importerModel);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_UPDATE_INVALID_IMPORTER_ID,
								SystemErrorCode.WINERY_UPDATE_INVALID_IMPORTER_ID_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid importer ");
					}
				}
				else
				{
					wineryModel.setActiveImporterId(null);
				}
			}
			
			if(response == null)
			{
				if(wineryUpdatePO.getImporterIds() != null)
				{
					for (Long importerId : importerIds) {
						
						ImporterModel importerModelbyId = ImporterRepository.getImporterById(importerId);
						if(importerModelbyId == null)
						{						
							response = ApplicationUtils.errorMessageGenerate(
									SystemErrorCode.WINERY_UPDATE_INVALID_IMPORTER_ID,
									SystemErrorCode.WINERY_UPDATE_INVALID_IMPORTER_ID_TEXT, SUCCESS_CODE);
							
							logger.error("Invalid importer ");
						}
					}
				}
			}
			
			if(response == null)
			{
				if(wineryUpdatePO.getWaContact() == null){
					wineryModel.setWaContact(null);
				}
				else
				{
					MasterData waContact = MasterDataRepository.getMasterDataById(wineryUpdatePO.getWaContact());
					
					if(waContact != null)
					{
						wineryModel.setWaContact(waContact);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_UPDATE_INVALID_WA_CONTACT,
								SystemErrorCode.WINERY_UPDATE_INVALID_WA_CONTACT_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid WA Contact ");	
					}
				}	
			}
			
				
			if(response == null)
			{
				if(wineryUpdatePO.getFreightRegion() == null){
					wineryModel.setFreightRegion(null);
				}
				else{				
					MasterData freightRegion = MasterDataRepository.getMasterDataById(wineryUpdatePO.getFreightRegion());
					
					if(freightRegion != null)
					{
						wineryModel.setFreightRegion(freightRegion);
					}
					else
					{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_UPDATE_INVALID_FREIGHT_REGION,
								SystemErrorCode.WINERY_UPDATE_INVALID_FREIGHT_REGION_TEXT, SUCCESS_CODE);
						
						logger.error("Invalid Freight Region ");	
					}			
				}	
			}
			
			if(response == null)
			{
				if(wineryUpdatePO.getWarehouseId() != null && !(StringUtils.EMPTY).equals(wineryUpdatePO.getWarehouseId())){
					
					WarehouseModel warehouseModel = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(wineryUpdatePO.getWarehouseId()));
					if(warehouseModel != null){
						wineryModel.setWarehouseId(warehouseModel);	
					} else{
						response = ApplicationUtils.errorMessageGenerate(
								SystemErrorCode.WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR,
								SystemErrorCode.WINERY_ADD_NO_WAREHOUSE_DETAIL_ERROR_TEXT, SUCCESS_CODE);
						
						logger.error("No warehouse details found ");	
					}	
					
				} else if(wineryUpdatePO.getWarehouseId() != null && (StringUtils.EMPTY).equals(wineryUpdatePO.getWarehouseId())){
					wineryModel.setWarehouseId(null);
				}
				
			}
			
			if(response == null)
			{
				wineryModel.setWineryName(wineryUpdatePO.getWineryName());
				//wineryModel.setWineryCode(wineryUpdatePO.getWineryCode());
				wineryModel.setIsEnabled(BooleanUtils.parseBoolean(wineryUpdatePO.getStatus()));
				wineryModel.setAccountingCustomerNumber(wineryUpdatePO.getAccountingCustomerNumber());	
				wineryModel.setWineryUrl(wineryUpdatePO.getWineryUrl());
				WineryRepository.updateWinery(wineryModel,wineryUpdatePO);
				if(wineryModel.getActiveImporterId()!= null && wineryModel.getActiveImporterId().getId() != null ){
					ImporterRepository.updateWineryCount(wineryModel.getActiveImporterId().getId());
				}
				response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.WINERY_UPDATE_SUCCESS_TEXT, SUCCESS_CODE);
			}			
			
		} 		
		catch (Exception e) {
			
			 errorMsg = e.getCause().getMessage();			
		}	
		
		if(errorMsg.contains("uk_winery_name"))
		{
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.WINERY_UPDATE_WINERY_NAME_DUPLICATE,
					SystemErrorCode.WINERY_UPDATE_WINERY_NAME_DUPLICATE_TEXT, SUCCESS_CODE);
			
			logger.error("Duplicate entry for winery name ");
		}
		else if(errorMsg.contains("uk_winery_code"))
		{
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.WINERY_UPDATE_WINERY_CODE_DUPLICATE,
					SystemErrorCode.WINERY_UPDATE_WINERY_CODE_DUPLICATE_TEXT, SUCCESS_CODE);
			
			logger.error("Duplicate entry for winery code ");
		}
		
		output.put(OUPUT_PARAM_KEY, response);		

		logger.info("exit updateWinery method");
		
		return output;

	}
	
	/**
	 * This method is used to perform the delete opertation of winery (Soft delete)
	 * @param wineryDeletePO take the input parameter for deleting the winery
	 * @return output
	 */
	public static  Map<String, Object> deleteWinery(final WineryDeletePO wineryDeletePO){
		
		logger.info("start deleteWinery method");
		
		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		try {
			List<Long> wineryList = wineryDeletePO.getWineryIds();
					
			Boolean isForceDelete = false;
			if(null != wineryDeletePO.getIsForceDelete())
				isForceDelete = wineryDeletePO.getIsForceDelete();			

			BulkDeleteModel<WineryModel> bulkDeleteModel = WineryRepository.delete(wineryList,isForceDelete);

			DeleteVO<WineryDetails> wineryDetailsCustomModelsForDependency = new DeleteVO<WineryDetails>();
			DeleteVO<WineryDetails> wineryDetailsCustomModelsForCanBeDeleted = new DeleteVO<WineryDetails>();
			
			List<WineryDetails> deleteList = new ArrayList<WineryDetails>();
			List<WineryDetails> dependencyList = new ArrayList<WineryDetails>();
			
			List<WineryModel> canBeDeletedList = bulkDeleteModel.getDeletedList();
			
			for(WineryModel wineryModel: canBeDeletedList)
			{	
					deleteList.add(populateWineryDetails(wineryModel));									
			}
			
			wineryDetailsCustomModelsForCanBeDeleted.setElements(deleteList);		
						
			List<WineryModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

			for(WineryModel wineryModel: canNotBeDeletedList)
			{						
				dependencyList.add(populateWineryDetails(wineryModel));												
			}
			
			wineryDetailsCustomModelsForDependency.setElements(dependencyList);			

			List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();
			
			WineryBulkDeleteVO wineryBulkDeleteVO = new WineryBulkDeleteVO();
			wineryBulkDeleteVO.setNonExistsList(nonExistingList);
			wineryBulkDeleteVO.setFailureList(wineryDetailsCustomModelsForDependency);
			wineryBulkDeleteVO.setSuccessList(wineryDetailsCustomModelsForCanBeDeleted);	
						
			response = new com.wineaccess.response.SuccessResponse(wineryBulkDeleteVO, SUCCESS_CODE);
			
		} catch (Exception e) {
			
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.WINERY_DELETE_UNKNOWN_ERROR,
					SystemErrorCode.WINERY_DELETE_UNKNOWN_ERROR_TEXT, SUCCESS_CODE);
			
			logger.error("Unkonwn error "+e);
		}
		
		output.put(OUPUT_PARAM_KEY, response);
		
		logger.info("exit deleteWinery method");
		
		return output;		
		
	}
	
	/**
	 *  View the winery details based on importer id
	 * @param wineryPO
	 * @return output
	 */
	public static Map<String, Object> viewWineryDetails(final ViewWineryPO wineryPO) {

		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		final ViewWineryVO wineryVO = new ViewWineryVO();
		final Long wineryId = Long.valueOf(wineryPO.getWineryId());
		final WineryModel wineryModel = WineryRepository.getWineryById(wineryId);

		if (wineryModel != null) {

			WineryDetails wineryDetails = populateWineryDetails(wineryModel);
			wineryVO.setWineryDetails(wineryDetails);

			WineryKeyMetrics wineryKeyMetrics = populateWineryKeyMetrics();
			wineryVO.setKeyMetrics(wineryKeyMetrics);

			response = new com.wineaccess.response.SuccessResponse(wineryVO,SUCCESS_CODE);
		} else {
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NO_RECORD_EXISTS_ID,WineaccessErrorCodes.SystemErrorCode.NO_RECORD_EXISTS_ID_TXT,SUCCESS_CODE);
		}

		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}

	/**
	 *  populate the Winery details from the winery model
	 * @param wineryModel
	 * @return the WineryDetails 
	 */
	private static WineryDetails populateWineryDetails(final WineryModel wineryModel) {

		WineryImporterContacts wineryContacts = WineryRepository.viewWineryContactDetails(wineryModel.getId());
		String importerName = StringUtils.EMPTY;
		if(wineryModel.getActiveImporterId() != null){
			importerName = WineryRepository.getImporterName(wineryModel.getActiveImporterId());
		}
		//String stateName = StateRepository.getNameById(wineryModel.getRegion());
		
		Map<String, String> waContactMap = new HashMap<String, String>();
		if (wineryModel.getWaContact() != null) {
			waContactMap.put(ID,Long.toString(wineryModel.getWaContact().getId()));
			waContactMap.put(NAME,WineryRepository.getWAContactFreightById(wineryModel.getWaContact().getId()));
		}

		Map<String, String> waFreightRegionMap = new HashMap<String, String>();
		if (wineryModel.getFreightRegion() != null) {
			waFreightRegionMap.put(ID,Long.toString(wineryModel.getFreightRegion().getId()));
			waFreightRegionMap.put(NAME, WineryRepository.getWAContactFreightById(wineryModel.getFreightRegion().getId()));
		}
		
		

		String contactName = StringUtils.EMPTY;
		String email = StringUtils.EMPTY;
		String phone = StringUtils.EMPTY;

		if (wineryContacts != null) {
			if (StringUtils.isNotBlank(wineryContacts.getName())) {
				contactName = wineryContacts.getName();
			}
			if (StringUtils.isNotBlank(wineryContacts.getEmail())) {
				email = wineryContacts.getEmail();
			}
			if (StringUtils.isNotBlank(wineryContacts.getPhone())) {
				phone = wineryContacts.getPhone();
			}
		}

		WineryDetails details = new WineryDetails();
		details.setName(wineryModel.getWineryName());
		details.setWineryId(wineryModel.getId());
		details.setContact(contactName);
		details.setEmail(email);
		details.setPhone(phone);
		ImporterWinery activeImporter = new ImporterWinery();
		if(wineryModel.getActiveImporterId() != null){
			
			activeImporter.setId(wineryModel.getActiveImporterId().getId());
			activeImporter.setName(wineryModel.getActiveImporterId().getImporterName());
			
		}
		details.setActiveImporter(activeImporter);
		
		//details.setImporter(importerName);
		details.setWaContact(waContactMap);
		details.setFreightRegion(waFreightRegionMap);
		details.setWineryUrl(wineryModel.getWineryUrl());
		details.setStatus(wineryModel.getIsEnabled());
		details.setAccountingCustomerNumber(wineryModel.getAccountingCustomerNumber());
		Map<String, String> sourcingStatusMap = new HashMap<String, String>();
		if (wineryModel.getSourcingStatus() != null) {
			sourcingStatusMap.put(ID,Long.toString(wineryModel.getSourcingStatus().getId()));
			sourcingStatusMap.put(NAME,wineryModel.getSourcingStatus().getName());
		}
		details.setSourcingStatus(sourcingStatusMap);
		
		StateModel state = wineryModel.getRegion();
		
		State region = new State();
		region.setId(state.getId());
		region.setName(state.getStateName());
		region.setStateCode(state.getStateCode());
		
		if(wineryModel.getWarehouseId() != null){
			Map<String, String> warehouse = new HashMap<String, String>();
			warehouse.put("id", wineryModel.getWarehouseId().getId().toString());
			warehouse.put("name",  wineryModel.getWarehouseId().getName());
			details.setWarehouse(warehouse);
		}
		
		details.setRegion(region);
		return details;
	}
	
	
	 /**
	  *  populate the winery key metrics
	  * @return WineryKeyMetrics
	  */	 
	private static WineryKeyMetrics populateWineryKeyMetrics() {

		WineryKeyMetrics keyMetrics = new WineryKeyMetrics();
		return keyMetrics;
	}
	
	/**
	 * This method is used to perform the bulk enable/disable operation of winery
	 * @param enableDisablePO take the input parameter for enabling/disabling the winery
	 * @return output
	 */
	public static  Map<String, Object> enableDisableWinery(final WineryEnableDisablePO enableDisablePO){
		
		logger.info("start enable/disable winery");
		
		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		List<Long> wineryList = enableDisablePO.getWineryIds();

		Boolean isForceDelete = false;
		if (StringUtils.isNotBlank(enableDisablePO.getIsForceDelete()))
			isForceDelete = Boolean.parseBoolean(enableDisablePO.getIsForceDelete());

		BulkDeleteModel<WineryModel> bulkDeleteModel = WineryRepository.enableDisable(wineryList,getWineryStatus(enableDisablePO.getStatus()),isForceDelete);

		DeleteVO<WineryDetails> wineryDetailsForDependency = new DeleteVO<WineryDetails>();
		DeleteVO<WineryDetails> wineryDetailsForDeleted = new DeleteVO<WineryDetails>();

		List<WineryDetails> deleteList = new ArrayList<WineryDetails>();
		List<WineryDetails> dependencyList = new ArrayList<WineryDetails>();

		List<WineryModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

		for (WineryModel wineryModel : canBeDeletedList) {
			deleteList.add(populateWineryDetails(wineryModel));
		}

		wineryDetailsForDeleted.setElements(deleteList);

		List<WineryModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

		for (WineryModel wineryModel : canNotBeDeletedList) {
			dependencyList.add(populateWineryDetails(wineryModel));
		}

		wineryDetailsForDependency.setElements(dependencyList);

		List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();

		WineryEnableDisableVO enableDisableVO = new WineryEnableDisableVO();
		enableDisableVO.setNonExistsList(nonExistingList);
		enableDisableVO.setFailureList(wineryDetailsForDependency);
		enableDisableVO.setSuccessList(wineryDetailsForDeleted);

		response = new com.wineaccess.response.SuccessResponse(enableDisableVO,SUCCESS_CODE);	
		logger.info("exit enable/disable Winery");
		
		output.put(OUPUT_PARAM_KEY, response);
		return output;		
		
	}
	
	private static Boolean getWineryStatus(String status) {
		if (status.equalsIgnoreCase("enable")) {
			return true; 
		}else{
			return false;
		}
	}
}