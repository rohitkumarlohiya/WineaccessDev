package com.wineaccess.importer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.mchange.v1.lang.BooleanUtils;
import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.EnumTypes.ImporterErrorEnum;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.WineyImpoterModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.CountryRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;
import com.wineaccess.util.command.Country;
import com.wineaccess.warehouse.WarehouseModel;
import com.wineaccess.warehouse.WarehouseRepository;
import com.wineaccess.winery.WineryRepository;
import com.wineaccess.wineryimporter.WineryImporterRepository;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class ImporterAdapterHelper {

	private static Log logger = LogFactory.getLog(ImporterAdapterHelper.class);
	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY="FINAL-RESPONSE";
	public static final String ID="id";
	public static final String NAME="name";

	/**
	 * 
	 * @return -adds the importer details to database. If everything works fine then returns a success message: "Importer added successfully."
	 */
	public static  Map<String, Object> addImporter(AddImporterPO addImporterPO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		boolean isExists = false;
		try{

			ImporterModel importer = new ImporterModel();
			ImporterModel importer1 = ImporterRepository.getImporterByImporterName(addImporterPO.getImporterName().trim());
			if(importer1 != null && importer1.getIsDeleted() != null &&  importer1.getIsDeleted()){
				importer = importer1;
				isExists = true;
			}
			List<CountryModel> country = CountryRepository.getById(Long.valueOf(addImporterPO.getCountryId()));
			if(null == country || country.size() < 1){
				throw new ImporterCustomException(ImporterErrorEnum.COUNTRY_NOT_FOUND.getErrorType());
			}

			importer.setCountryId(country.get(0));
			importer.setCustomerNumber(addImporterPO.getAccountingCustNumber());
			//importer.setImporterCode(addImporterPO.getImporterCode());
			importer.setImporterName(addImporterPO.getImporterName());
			importer.setImporterUrl(addImporterPO.getImporterUrl());
			importer.setIsDeleted(false);
			importer.setIsEnabled(BooleanUtils.parseBoolean(addImporterPO.getStatus()));

			MasterData sourcingStatus = MasterDataRepository.getMasterDataById(Long.valueOf(addImporterPO.getSourcingStatus()));
			if(null == sourcingStatus){
				throw new ImporterCustomException(ImporterErrorEnum.SOURCING_STATUS_NOT_FOUND.getErrorType());
			}

			importer.setSourcingStatus(sourcingStatus);
			
			if(null != addImporterPO.getWaContact()){
				MasterData waContact = MasterDataRepository.getMasterDataById(addImporterPO.getWaContact());
				if(null == waContact){
					throw new ImporterCustomException(ImporterErrorEnum.WA_CONTACT_NOT_FOUND.getErrorType());
				}
				
				importer.setWaContact(waContact);
			} 
			
			if(null != addImporterPO.getFreightRegion()){
				MasterData freightRegion = MasterDataRepository.getMasterDataById(addImporterPO.getFreightRegion());
				if(null == freightRegion){
					throw new ImporterCustomException(ImporterErrorEnum.FREIGHT_REGION_NOT_FOUND.getErrorType());
				}
				
				importer.setFreightRegion(freightRegion);
			} 
			

			if(null != addImporterPO.getWineryId()){
				for(Long wineryId: addImporterPO.getWineryId()){
					WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
					if(null == wineryModel){
						throw new ImporterCustomException(ImporterErrorEnum.WINERY_NOT_FOUND.getErrorType());
					}
				}	
			}			
			
			//This code is add for mapping the warehouse with importer as suggested in mail on oct 20,2014						
			if(addImporterPO.getWarehouseId() != null && !(StringUtils.EMPTY).equals(addImporterPO.getWarehouseId()))
			{				
				WarehouseModel warehouseModel = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(addImporterPO.getWarehouseId()));
				
				if(warehouseModel != null)
				{
					importer.setWarehouseId(warehouseModel);
				}
				else
				{					
					throw new ImporterCustomException(ImporterErrorEnum.WAREHOUSE_NOT_FOUND.getErrorType());					
				}			
			}							
			
			//ImporterModel importer1 = ImporterRepository.getImporterByImporterName(addImporterPO.getImporterName().trim());
			if(importer1 != null && isExists){			
				ImporterRepository.update(importer);
			}else{
				ImporterRepository.save(importer);
			}
			
			response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.IMPORTER_109_ERROR_TEXT, 200);

			//ImporterModel importerModel = ImporterRepository.getImporterByImporterCode(importer.getImporterCode());
			mapImporterWithWinery(addImporterPO.getWineryId(),importer.getId());
			activateImporterForWinery(importer);

		}
		catch(Exception e){

			if(e instanceof ImporterCustomException){
				String exception = ((ImporterCustomException) e).getExceptionType();
				if((ImporterErrorEnum.COUNTRY_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_111_ERROR, SystemErrorCode.IMPORTER_111_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.SOURCING_STATUS_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_112_ERROR, SystemErrorCode.IMPORTER_112_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.WINERY_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_110_ERROR, SystemErrorCode.IMPORTER_110_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.WA_CONTACT_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_122_ERROR, SystemErrorCode.IMPORTER_122_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.FREIGHT_REGION_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_123_ERROR, SystemErrorCode.IMPORTER_123_ERROR_TEXT, 200);
				}
				else if((ImporterErrorEnum.WAREHOUSE_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_131_ERROR, SystemErrorCode.IMPORTER_131_ERROR_TEXT, 200);
				}				
				else {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_113_ERROR, SystemErrorCode.IMPORTER_113_ERROR_TEXT, 200);
				}
			} else if(e instanceof PersistenceException){
				if((e.getCause().getMessage()).contains("importer_constraint_3"))
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_114_ERROR,SystemErrorCode.IMPORTER_114_ERROR_TEXT , 200);
				else if((e.getCause().getMessage()).contains("importer_constraint_4"))
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_115_ERROR,SystemErrorCode.IMPORTER_115_ERROR_TEXT , 200);
			} else{
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_113_ERROR, SystemErrorCode.IMPORTER_113_ERROR_TEXT, 200);
			}
		}
		finally{
			if(null == response){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_113_ERROR, SystemErrorCode.IMPORTER_113_ERROR_TEXT, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}


	/**
	 * 
	 * @return -updates the importer details to database. If everything works fine then returns a success message: "Importer updated successfully."
	 */
	public static  Map<String, Object> updateImporter(EditImporterPO editImporterPO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		try{

			ImporterModel importer = ImporterRepository.getImporterById(editImporterPO.getImporterId());
			if(importer == null){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_124_ERROR, SystemErrorCode.IMPORTER_124_ERROR_TEXT, 200);
			}
			if(response == null){
					List<CountryModel> country = CountryRepository.getById(Long.valueOf(editImporterPO.getCountryId()));
					if(null == country || country.size() < 1){
						throw new ImporterCustomException(ImporterErrorEnum.COUNTRY_NOT_FOUND.getErrorType());
					}
		
					importer.setCountryId(country.get(0));
					importer.setCustomerNumber(editImporterPO.getAccountingCustNumber());
					//importer.setImporterCode(editImporterPO.getImporterCode());
					importer.setImporterName(editImporterPO.getImporterName());
					importer.setImporterUrl(editImporterPO.getImporterUrl());
					importer.setIsDeleted(false);
					importer.setIsEnabled(BooleanUtils.parseBoolean(editImporterPO.getStatus()));
		
					MasterData sourcingStatus = MasterDataRepository.getMasterDataById(Long.valueOf(editImporterPO.getSourcingStatus()));
					if(null == sourcingStatus){
						throw new ImporterCustomException(ImporterErrorEnum.SOURCING_STATUS_NOT_FOUND.getErrorType());
					}
		
					importer.setSourcingStatus(sourcingStatus);
		
					if(null != editImporterPO.getWaContact()){
						MasterData waContact = MasterDataRepository.getMasterDataById(editImporterPO.getWaContact());
						if(null == waContact){
							throw new ImporterCustomException(ImporterErrorEnum.WA_CONTACT_NOT_FOUND.getErrorType());
						}
						
						importer.setWaContact(waContact);
					} 
					
					if(null != editImporterPO.getFreightRegion()){
						MasterData freightRegion = MasterDataRepository.getMasterDataById(editImporterPO.getFreightRegion());
						if(null == freightRegion){
							throw new ImporterCustomException(ImporterErrorEnum.FREIGHT_REGION_NOT_FOUND.getErrorType());
						}
						
						importer.setFreightRegion(freightRegion);
					} else{
						importer.setFreightRegion(null);
					}
					
					if(null != editImporterPO.getWineryId()){
						for(Long wineryId: editImporterPO.getWineryId()){
							WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
							if(null == wineryModel){
								throw new ImporterCustomException(ImporterErrorEnum.WINERY_NOT_FOUND.getErrorType());
							}
						}	
					}	
					
					//This code is add for mapping the warehouse with importer as suggested in mail on oct 20,2014	
					if(editImporterPO.getWarehouseId() != null && !(StringUtils.EMPTY).equals(editImporterPO.getWarehouseId())){
						
						WarehouseModel warehouseModel = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(editImporterPO.getWarehouseId()));
						if(warehouseModel != null){
							importer.setWarehouseId(warehouseModel);	
						} else{
							throw new ImporterCustomException(ImporterErrorEnum.WAREHOUSE_NOT_FOUND.getErrorType());	
						}	
						
					} else if(editImporterPO.getWarehouseId() != null && (StringUtils.EMPTY).equals(editImporterPO.getWarehouseId())){
						importer.setWarehouseId(null);
					}
						
					
		
					ImporterRepository.update(importer);
					response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.IMPORTER_116_ERROR_TEXT, 200);
		
					//Getting the mappings of winery and importer
					List<WineyImpoterModel> wineryImporterModels = WineryImporterRepository.getWineyImpoterModelByImporterId(importer.getId());
					List<Long> listOfWineryIds = editImporterPO.getWineryId();
					if(null != wineryImporterModels && listOfWineryIds != null){
						for(WineyImpoterModel wineyImpoterModel : wineryImporterModels){
							if(!listOfWineryIds.contains(wineyImpoterModel.getWineryId())){
								//Previous winery removed
								wineyImpoterModel.setIsDeleted(true);
								WineryImporterRepository.save(wineyImpoterModel);
		
								//Getting the Winery model of the importer.
								WineryModel winery = WineryRepository.getWineryById(wineyImpoterModel.getWineryId());
								if(winery != null && winery.getActiveImporterId() != null && wineyImpoterModel.getImporterId() == winery.getActiveImporterId().getId()){
									winery.setActiveImporterId(null);
									WineryRepository.update(winery);
									listOfWineryIds.remove(wineyImpoterModel.getWineryId());	
								}
									
							}
						}
						
						if(editImporterPO.getWineryId().isEmpty()){
							ImporterRepository.updateWineryCount(importer.getId());
						}
						//New wineries added with the importer
						if(null != listOfWineryIds && !listOfWineryIds.isEmpty()){
							mapImporterWithWinery(listOfWineryIds,importer.getId());
							activateImporterForWinery(importer);
							
						}
					}
		
				}
		}
		catch(Exception e){

			if(e instanceof ImporterCustomException){
				String exception = ((ImporterCustomException) e).getExceptionType();
				if((ImporterErrorEnum.COUNTRY_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_111_ERROR, SystemErrorCode.IMPORTER_111_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.SOURCING_STATUS_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_112_ERROR, SystemErrorCode.IMPORTER_112_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.WINERY_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_110_ERROR, SystemErrorCode.IMPORTER_110_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.WA_CONTACT_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_122_ERROR, SystemErrorCode.IMPORTER_122_ERROR_TEXT, 200);
				} else if((ImporterErrorEnum.FREIGHT_REGION_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_123_ERROR, SystemErrorCode.IMPORTER_123_ERROR_TEXT, 200);
				} 
				else if((ImporterErrorEnum.WAREHOUSE_NOT_FOUND.getErrorType()).equals(exception)){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_131_ERROR, SystemErrorCode.IMPORTER_131_ERROR_TEXT, 200);
				}	
				else {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_117_ERROR, SystemErrorCode.IMPORTER_117_ERROR_TEXT, 200);
				}
			} else if(e instanceof PersistenceException || e instanceof DataIntegrityViolationException){
				if((e.getCause().getMessage()).contains("importer_constraint_3"))
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_114_ERROR,SystemErrorCode.IMPORTER_114_ERROR_TEXT , 200);
				else if((e.getCause().getMessage()).contains("importer_constraint_4"))
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_115_ERROR,SystemErrorCode.IMPORTER_115_ERROR_TEXT , 200);
			} else{
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_113_ERROR, SystemErrorCode.IMPORTER_113_ERROR_TEXT, 200);
			}
		}
		finally{
			if(null == response){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_113_ERROR, SystemErrorCode.IMPORTER_113_ERROR_TEXT, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}

	/**
	 * 
	 * @return -deletes the importer details from database. If everything works fine then returns a success message: "Importer deleted successfully."
	 */
	public static  Map<String, Object> deleteImporter(DeleteImporterPO deleteImporterPO){
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		try{
			Boolean isForceDelete = false;
			if(null != deleteImporterPO.getIsForceDelete() && !("").equals(deleteImporterPO.getIsForceDelete()))
				if(deleteImporterPO.getIsForceDelete() == true)
					isForceDelete = true;
			
			
			if(null != deleteImporterPO.getImporterIds()){
				BulkDeleteModel<ImporterModel> bulkDeleteModel = ImporterRepository.delete(deleteImporterPO.getImporterIds(),isForceDelete);
				DeleteImporterVO deleteImporterVO = new DeleteImporterVO();
				deleteImporterVO.setCanBeDeleted(generateResponseList(bulkDeleteModel.getDeletedList()));
				deleteImporterVO.setCanNotBeDeleted(generateResponseList(bulkDeleteModel.getNotDeletedList()));
				deleteImporterVO.setNonExisting((List<Long>)bulkDeleteModel.getNotExistsList());

				response = new com.wineaccess.response.SuccessResponse(deleteImporterVO, 200); 
			} else{
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_118_ERROR, SystemErrorCode.IMPORTER_118_ERROR_TEXT, 200);
			}
		}
		catch(Exception e){
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.IMPORTER_118_ERROR, SystemErrorCode.IMPORTER_118_ERROR_TEXT, 200);
		}

		output.put("FINAL-RESPONSE", response);
		return output;

	}


	/**
	 * View the importer details based on importer id
	 * @param importerPO
	 * @return output
	 */
	public static Map<String,Object> viewImporterDetails(final ViewImporterPO importerPO){

		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		final ViewImporterVO importerVO = new ViewImporterVO();
		final Long importerId= Long.valueOf(importerPO.getImporterId());
		final ImporterModel importerModel = ImporterRepository.getImporterById(importerId);

		if(importerModel != null){

			ImporterDetails importerDetails = populateImporterDetails(importerModel);
			importerVO.setImporterDetails(importerDetails);

			ImporterKeyMetrics importerKeyMetrics = populateImporterKeyMetrics();
			importerVO.setKeyMetrics(importerKeyMetrics);

			response = new com.wineaccess.response.SuccessResponse(importerVO, SUCCESS_CODE);

		}else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NO_RECORD_EXISTS_ID, WineaccessErrorCodes.SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, SUCCESS_CODE);
		}

		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}

	/**
	 * populate the importer details from importer model
	 * @param importerModel
	 * @return ImporterDetails
	 */
	private static ImporterDetails populateImporterDetails(final ImporterModel importerModel){

		WineryImporterContacts importerContacts = ImporterRepository.viewImporterContactDetails(importerModel.getId());
		String countryName = CountryRepository.getNameById(importerModel.getCountryId());

		Map<String,String> waContactMap = new HashMap<String, String>();
		if(importerModel.getWaContact() != null){
			waContactMap.put(ID, Long.toString(importerModel.getWaContact().getId()));
			waContactMap.put(NAME, ImporterRepository.getWAContactFreightById(importerModel.getWaContact().getId()));
		}

		Map<String,String> waFreightRegionMap = new HashMap<String, String>();
		if(importerModel.getFreightRegion() != null){
			waFreightRegionMap.put(ID, Long.toString(importerModel.getFreightRegion().getId()));
			waFreightRegionMap.put(NAME, ImporterRepository.getWAContactFreightById(importerModel.getFreightRegion().getId()));
		}
		
		String contactName = StringUtils.EMPTY;
		String email = StringUtils.EMPTY;
		String phone = StringUtils.EMPTY;

		if(importerContacts != null){
			if(StringUtils.isNotBlank(importerContacts.getName())) {
				contactName = importerContacts.getName();
			}
			if(StringUtils.isNotBlank(importerContacts.getEmail())) {
				email = importerContacts.getEmail();
			}
			if(StringUtils.isNotBlank(importerContacts.getPhone())) {
				phone = importerContacts.getPhone();
			}		 
		}	

		ImporterDetails details = new ImporterDetails();
		List<WineyImpoterModel> wineryImporterList =  WineryImporterRepository.getNonDeletedWineyImpoterModelByImporterId(importerModel.getId());
		List<Long> wineryIdList = new ArrayList<Long>();
		for(WineyImpoterModel wineryImporterModel : wineryImporterList){
			wineryIdList.add(wineryImporterModel.getWineryId());
		}
		
		
		for(Long wineryId : wineryIdList){
			for(WineryModel model : importerModel.getWineries()){
				
				
				
				if(model.getId() == wineryId){
					
					ImporterWinery importerWinery = new ImporterWinery(model.getId(), model.getWineryName());
					
					(details.getWineries()).add(importerWinery);
					break;
				}
				
			}
		}
		
		
		
		details.setName(importerModel.getImporterName());
		details.setImporterId(importerModel.getId());
		details.setContact(contactName);
		details.setEmail(email);
		details.setPhone(phone);
		//details.setRegion(countryName);
		details.setWaContact(waContactMap);
		details.setFreightRegion(waFreightRegionMap);
		details.setImporterUrl(importerModel.getImporterUrl());
		details.setStatus(importerModel.getIsEnabled());
		details.setAccountingCustomerNumber(importerModel.getCustomerNumber());
		Map<String, String> sourcingStatusMap = new HashMap<String, String>();
		if (importerModel.getSourcingStatus() != null) {
			sourcingStatusMap.put(ID,Long.toString(importerModel.getSourcingStatus().getId()));
			sourcingStatusMap.put(NAME,importerModel.getSourcingStatus().getName());
		}
		details.setSourcingStatus(sourcingStatusMap);
		
		CountryModel country = importerModel.getCountryId();
		
		Country region = new Country();
		region.setId(country.getId());
		region.setName(country.getCountryName());
		region.setCountryCode(country.getCountryCode());
		
		
		if(importerModel.getWarehouseId() != null){
			Map<String, String> warehouse = new HashMap<String, String>();
			warehouse.put("id", importerModel.getWarehouseId().getId().toString());
			warehouse.put("name",  importerModel.getWarehouseId().getName());
			details.setWarehouse(warehouse);
		}
		
		details.setRegion(region);
		
		return details;
	}

	/**
	 * populate the importer key metrics
	 * @return ImporterKeyMetrics
	 */
	private static ImporterKeyMetrics populateImporterKeyMetrics(){

		ImporterKeyMetrics keyMetrics = new ImporterKeyMetrics();
		return keyMetrics;
	}

	private static void activateImporterForWinery(ImporterModel importerModel){
		if(null != importerModel){
			//Getting all the wineries mapped with the newly created importers
			List<WineyImpoterModel> wineryImporters =  WineryImporterRepository.getWineyImpoterModelByImporterId(importerModel.getId());
			if(null != wineryImporters && !wineryImporters.isEmpty()){
				for(WineyImpoterModel wineryImporter: wineryImporters){
					//Getting all non deleted mappings of the Winery
					List<WineyImpoterModel> wineryImporterList = WineryRepository.getImporters(wineryImporter.getWineryId()); 
					if(null != wineryImporterList && wineryImporterList.size() == 1){
						WineyImpoterModel wineyImpoterModel = wineryImporterList.get(0);
						try{
							if(null != wineyImpoterModel.getImporterId() && importerModel.getId() == wineyImpoterModel.getImporterId()){
								WineryModel winery = WineryRepository.getWineryById(wineyImpoterModel.getWineryId());
								winery.setActiveImporterId(importerModel);
								WineryRepository.update(winery);


								wineryImporter.setIsActiveImpoter(true);
								WineryImporterRepository.save(wineryImporter);
							}	
						}
						catch(Exception e){
							logger.error("Error while activating the Importer for a Winery.");
						}
					}
				}	
			}			
		}
	}

	private static void mapImporterWithWinery(List<Long> wineryIds, Long importerId){
		if(null != wineryIds && null != importerId){
			for(Long wineryId : wineryIds){
				WineyImpoterModel wineryImporter = WineryRepository.getWineyImpoterModelByImporterIdAndWineryId(importerId,wineryId);
				try{
					if(null != wineryImporter){
						//Update the existing mapping
						wineryImporter.setIsDeleted(false);
						WineryImporterRepository.save(wineryImporter);
					} else{
						//Create new mapping
						WineyImpoterModel wineryImporterModel = new WineyImpoterModel();
						wineryImporterModel.setImporterId(importerId);
						wineryImporterModel.setWineryId(wineryId);
						wineryImporterModel.setIsDeleted(false);
						wineryImporterModel.setIsActiveImpoter(false);
						WineryImporterRepository.save(wineryImporterModel);
					}					
				} catch(Exception e){
					logger.error("Error while mapping importer with winery.");
				}
			}
			ImporterRepository.updateWineryCount(importerId);
		}			
	}	

	private static List<ImporterVO> generateResponseList(List<ImporterModel> importers){
		List<ImporterVO> importerVOList = new ArrayList<ImporterVO>();
		if(null != importers){
			for(ImporterModel importer: importers){
				ImporterVO importerVO = new ImporterVO();
				importerVO.setActiveWineCount(importer.getActiveWineCount());
				importerVO.setCountryId(importer.getCountryId().getId());
				importerVO.setCustomerNumber(importer.getCustomerNumber());
				importerVO.setId(importer.getId());
				importerVO.setDefaultContactAddressId(importer.getDefaultContactAddressId());
				importerVO.setDefaultContactBillingAddressId(importer.getDefaultContactBillingAddressId());
				importerVO.setImporterCode(importer.getImporterCode());
				importerVO.setImporterName(importer.getImporterName());
				importerVO.setImporterUrl(importer.getImporterUrl());
				importerVO.setIsDeleted(importer.getIsDeleted());
				importerVO.setLastOfferDate(importer.getLastOfferDate());
				importerVO.setNotes(importer.getNotes());
				importerVO.setRevenue(new Double(importer.getRevenue()));
				importerVO.setSourcingStatus(importer.getSourcingStatus().getName());
				importerVO.setWineCount(importer.getWineCount());
				
				if(null != importer.getWarehouseId()){
					Map<String,String> warehouseMap = new HashMap<String, String>();
					warehouseMap.put("id", importer.getWarehouseId().getId().toString());
					warehouseMap.put("name", importer.getWarehouseId().getName());		
					importerVO.setWarehouse(warehouseMap);
				}		
								
				importerVOList.add(importerVO);
			}
		}
		return importerVOList;
	}
	
	/**
	 * This method is used to enable disable the importer
	 * @param enableDisablePO
	 * @return Map the output map
	 */
	public static Map<String, Object> enableDisableImporter(final ImporterEnableDisablePO enableDisablePO) {

		logger.info("start enable/disable importer");

		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		List<Long> importerList = enableDisablePO.getImporterIds();

		Boolean isForceDelete = false;
		if (StringUtils.isNotBlank(enableDisablePO.getIsForceDelete()))
			isForceDelete = Boolean.parseBoolean(enableDisablePO.getIsForceDelete());

		BulkDeleteModel<ImporterModel> bulkDeleteModel = ImporterRepository.enableDisable(importerList,getImporterStatus(enableDisablePO.getStatus()),isForceDelete);

		DeleteVO<ImporterDetails> importerDetailsForDependency = new DeleteVO<ImporterDetails>();
		DeleteVO<ImporterDetails> importerDetailsForDeleted = new DeleteVO<ImporterDetails>();

		List<ImporterDetails> deleteList = new ArrayList<ImporterDetails>();
		List<ImporterDetails> dependencyList = new ArrayList<ImporterDetails>();

		List<ImporterModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

		for (ImporterModel importerModel : canBeDeletedList) {
			deleteList.add(populateImporterDetails(importerModel));
		}

		importerDetailsForDeleted.setElements(deleteList);

		List<ImporterModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

		for (ImporterModel importerModel : canNotBeDeletedList) {
			dependencyList.add(populateImporterDetails(importerModel));
		}

		importerDetailsForDependency.setElements(dependencyList);

		Set<Long> set = new HashSet<Long>((List<Long>) bulkDeleteModel.getNotExistsList());
		set.addAll((List<Long>) bulkDeleteModel.getNotExistsList());
		List<Long> nonExistingList = new ArrayList<Long>(set);

		ImporterEnableDisableVO enableDisableVO = new ImporterEnableDisableVO();
		enableDisableVO.setNonExistsList(nonExistingList);
		enableDisableVO.setFailureList(importerDetailsForDependency);
		enableDisableVO.setSuccessList(importerDetailsForDeleted);

		response = new com.wineaccess.response.SuccessResponse(enableDisableVO,SUCCESS_CODE);
		logger.info("exit enable/disable Importer");

		output.put(OUPUT_PARAM_KEY, response);
		return output;

	}
	
	private static Boolean getImporterStatus(String status) {
		if (status.equalsIgnoreCase("enable")) {
			return true; 
		}else{
			return false;
		}
	}
}
