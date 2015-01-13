package com.wineaccess.wine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterAddressModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.repositories.WineryImporterContactRepository;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.warehouse.WarehouseModel;
import com.wineaccess.warehouse.WarehouseRepository;
import com.wineaccess.winery.WineryRepository;
import com.wineaccess.wineryimporter.ContactDetails;

/**
 * This Class is used to perform the add ,edit and view wine operation
 * 
 * @author rohit.lohiya
 * 
 */
public class WineAdapterHelper {

    // purpose of this logger variable is to log the error messages in log file.
    private static Log logger = LogFactory.getLog(WineAdapterHelper.class);

    public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();

    public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";
    public static final String ID = "id";
    public static final String NAME = "name";

    /**
     * This method is used to add the wine in the database.
     * 
     * @param winePO this is used to take the input.
     * @return output map containing response
     */
    public static Map<String, Object> addWine(final WinePO winePO) {

	logger.info("start addWine method");

	String errorMsg = StringUtils.EMPTY;

	final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

	Response response = null;

	try {
	    MasterData wineStyle = null;
	    MasterData vintage = null;
	    MasterData varietal = null;
	    MasterData bottleInMl = null;
	    MasterData bottlesPerBox = null;			
	    WineryModel winery = null;
	    MasterData wineSourcingId = null;
	    ImporterModel importer = null;

	    wineStyle = MasterDataRepository.getMasterDataById(Long
		    .parseLong(winePO.getWineStyleId()));
	    if (wineStyle == null) {
		// wine style not exist
		response = ApplicationUtils.errorMessageGenerate(

			SystemErrorCode.INVALID_WINE_STYLE,
			SystemErrorCode.INVALID_WINE_STYLE_TEXT, SUCCESS_CODE);

		logger.error("wine style not exist");
	    }

	    if (response == null) {
		vintage = MasterDataRepository.getMasterDataById(Long
			.parseLong(winePO.getVintageId()));
		if (vintage == null) {
		    // vintage not exist
		    response = ApplicationUtils.errorMessageGenerate(

			    SystemErrorCode.INVALID_VINTAGE,
			    SystemErrorCode.INVALID_VINTAGE_TEXT, SUCCESS_CODE);

		    logger.error("vintage not exist");
		}
	    }

	    if (response == null) {
		varietal = MasterDataRepository.getMasterDataById(Long
			.parseLong(winePO.getVarietalId()));
		if (varietal == null) {
		    // varietal not exist
		    response = ApplicationUtils
			    .errorMessageGenerate(

				    SystemErrorCode.INVALID_VARIETAL,
				    SystemErrorCode.INVALID_VARIETAL_TEXT,
				    SUCCESS_CODE);

		    logger.error("varietal not exist");
		}
	    }

	    if (response == null) {
		bottleInMl = MasterDataRepository.getMasterDataById(Long
			.parseLong(winePO.getBottleInMlId()));
		if (bottleInMl == null) {
		    // bottleInMlId not exist
		    response = ApplicationUtils.errorMessageGenerate(

			    SystemErrorCode.INVALID_BOTTLE_IN_ML,
			    SystemErrorCode.INVALID_BOTTLE_IN_ML_TEXT,
			    SUCCESS_CODE);

		    logger.error("bottleInMlId not exist");
		}
	    }

	    if (response == null) {

		if(winePO.getBottlesPerBoxId() != null)
		{
		    if(!winePO.getBottlesPerBoxId().isEmpty())
		    {
			bottlesPerBox = MasterDataRepository.getMasterDataById(Long
				.parseLong(winePO.getBottlesPerBoxId()));
			if (bottlesPerBox == null) {
			    // bottlesPerBox not exist
			    response = ApplicationUtils.errorMessageGenerate(

				    SystemErrorCode.INVALID_BOTTLES_PER_BOX,
				    SystemErrorCode.INVALID_BOTTLES_PER_BOX_TEXT,
				    SUCCESS_CODE);

			    logger.error("bottlesPerBox not exist");
			}
		    }
		}
	    }

	    if (response == null) {
		winery = WineryRepository.getWineryById(Long.parseLong(winePO
			.getWineryId()));
		if (winery == null) {
		    // winery not exist
		    response = ApplicationUtils.errorMessageGenerate(

			    SystemErrorCode.INVALID_WINERY,
			    SystemErrorCode.INVALID_WINERY_TEXT, SUCCESS_CODE);

		    logger.error("winery not exist");
		}
	    }

	    if (response == null)
	    {
		if(winePO.getWineSourcingId() != null)
		{
		    if(!winePO.getWineSourcingId().isEmpty())
		    {
			wineSourcingId = MasterDataRepository.getMasterDataById(Long
				.parseLong(winePO.getWineSourcingId()));
			if (wineSourcingId == null) {
			    // wineSourcingId not exist
			    response = ApplicationUtils
				    .errorMessageGenerate(

					    SystemErrorCode.INVALID_SOURCING,
					    SystemErrorCode.INVALID_SOURCING_TEXT,
					    SUCCESS_CODE);

			    logger.error("wineSourcingId not exist");
			}
		    }									
		}
	    }

	    boolean isImported = false;
	    
	    if (response == null) {
	    	
			importer = winery.getActiveImporterId();		
			if(importer != null)
			{
				isImported = true;
			}
	    }
		
	    if (response == null) {
		final WineModel wineModel = new WineModel();

		String wineFullName = PropertyholderUtils
			.getStringProperty("wine.full.name");

		wineFullName = wineFullName.replace("<vintage>",
			vintage.getName() + " ");

		wineFullName = wineFullName.replace("<winery>",
			winery.getWineryName() + " ");

		wineFullName = wineFullName.replace("<winename>",
			winePO.getWineName());

		wineModel.setWineFullName(wineFullName);
		wineModel.setWineName(winePO.getWineName());
		wineModel.setVerietal(varietal);
		wineModel.setVintage(vintage);

		if (winePO.getWineShortName() != null){
		    if(!winePO.getWineShortName().isEmpty()){
			wineModel.setWineryShortName(winePO.getWineShortName());
		    }
		    else
		    {
			wineModel.setWineryShortName(null);
		    }
		}

		wineModel.setBottleInMl(bottleInMl);
		wineModel.setBottlesPerBox(bottlesPerBox); 				 
		wineModel.setIsDeleted(false);

		if (winePO.getAlcoholPercentage() != null){

		    if(!winePO.getAlcoholPercentage().isEmpty())
		    {
			wineModel.setAlcoholPercentage(Double.parseDouble(winePO.getAlcoholPercentage()));
		    }
		    else
		    {
			wineModel.setAlcoholPercentage(null);
		    }
		}


		if (winePO.getNotes() != null){

		    if(!winePO.getNotes().isEmpty())
		    {
			wineModel.setNotes(winePO.getNotes());
		    }
		    else
		    {
			wineModel.setNotes(null);
		    }
		}

		if (winePO.getWineLabel() != null){

		    if(!winePO.getWineLabel().isEmpty())
		    {
			wineModel.setWineLabel(winePO.getWineLabel());
		    }
		    else
		    {
			wineModel.setWineLabel(null);
		    }
		}

		if (winePO.getPrivateLabel() != null){

		    if(!winePO.getPrivateLabel().isEmpty())
		    {
			wineModel.setPrivateLabel(winePO.getPrivateLabel());
		    }
		    else
		    {
			wineModel.setPrivateLabel(null);
		    }
		}

		wineModel.setWineType(wineStyle);
		wineModel.setWineryId(winery);
		wineModel.setWineSourcingId(wineSourcingId);

		if (winePO.getSendToFullfillerOn() != null)
		{
		    if(!winePO.getSendToFullfillerOn().isEmpty())
		    {
			wineModel.setSendToFullfillerOn(Boolean.parseBoolean(winePO
				.getSendToFullfillerOn()));
		    }
		    else
		    {
			wineModel.setSendToFullfillerOn(null);
		    }
		}

		if (winePO.getUsaArrivalDate() != null)
		{				
		    wineModel.setUsaArrivalDate(winePO.getUsaArrivalDate());					
		}

		if (winePO.getLicenseFFPartnerId() != null)
		{
		    if(!winePO.getLicenseFFPartnerId().isEmpty())
		    {
			wineModel.setLicenseFullfillmentPartnerId(Long
				.parseLong(winePO.getLicenseFFPartnerId()));
		    }
		    else
		    {
			wineModel.setLicenseFullfillmentPartnerId(null);
		    }
		}

		if (winePO.getSellInMainStatesOnly() != null)
		{					
		    wineModel.setSellInMainStates(Boolean
			    .parseBoolean(winePO.getSellInMainStatesOnly()));

		}

		if(winePO.getNameIfNotSellInAltStates() != null)
		{
		    wineModel.setSellInAltStates(Boolean.parseBoolean(winePO.getNameIfNotSellInAltStates()));
		}
		
		wineModel.setIsImported(isImported);
		if(isImported)
		{
			wineModel.setImporterId(importer);
		}

		wineModel.setIsEnabled(Boolean.parseBoolean(winePO.getStatus()));

		MasterData masterData = MasterDataRepository.getMasterDataByTypeAndName("ProductType", "Wine");
		if(masterData != null)
		{		
			if(isImported)
			{
				if(importer.getWarehouseId() != null)
				{
					wineModel.setWarehouseId(importer.getWarehouseId());
				}
				else if(winery.getWarehouseId() != null)
				{
					wineModel.setWarehouseId(winery.getWarehouseId());
				}
				else
				{
					wineModel.setWarehouseId(null);
				}				
			}
			else
			{
				if(winery.getWarehouseId() != null)
				{
					wineModel.setWarehouseId(winery.getWarehouseId());
				}
				else
				{
					wineModel.setWarehouseId(null);
				}		
			}		
			
			WineryImporterContacts contactModel = null;
			
			if(importer != null){
	    		contactModel = WineryImporterContactRepository.getImporterContactById(importer.getId());
	    	}else{
	    		contactModel = WineryImporterContactRepository.getWineryContactById(winery.getId());
	    	}
			wineModel.setContactId(contactModel);
		    WineRepository.save(wineModel);	
		    
		    WineRepository.updateWCAndActiveWCInWinery(winery.getId());
		    
		    if(isImported)
	    	{
		    	WineRepository.updateWCAndActiveWCInImporter(importer.getId());
	    	}

		    ProductItemModel productItemModel = new ProductItemModel();
		    productItemModel.setProductId(masterData.getId());
		    productItemModel.setItemId(wineModel.getId());
		    ProductItemRepository.save(productItemModel);

		    wineModel.setProduct(productItemModel);
		    WineRepository.update(wineModel);

		    WineVO wineVO = new WineVO(
			    SystemErrorCode.WINE_ADD_SUCCESS_TEXT);
		    BeanUtils.copyProperties(wineVO, wineModel);

		    wineVO.setId(wineModel.getProduct().getId());
		    wineVO.setWineId(wineModel.getId());
		    wineVO.setWineryId(wineModel.getWineryId().getId());
		    
		    if(wineModel.getWarehouseId() != null)
		    {
		    	wineVO.setWarehouseId(wineModel.getWarehouseId().getId());
		    }		   

		    response = new com.wineaccess.response.SuccessResponse(wineVO,
			    SUCCESS_CODE);
		}
		else
		{
		    response = ApplicationUtils.errorMessageGenerate(
			    SystemErrorCode.WINE_ADD_WINE_INVALID_MASTER_DATA,
			    SystemErrorCode.WINE_ADD_WINE_INVALID_MASTER_DATA_TEXT,
			    SUCCESS_CODE);

		    logger.error("Invalid Master Data ");
		}							
	    }
	} catch (Exception e) {

	    errorMsg = e.getCause().getMessage();
	}

	if (response == null) {
	    if (errorMsg.contains("uk_wine")) {
		response = ApplicationUtils.errorMessageGenerate(
			SystemErrorCode.WINE_ADD_WINE_DUPLICATE,
			SystemErrorCode.WINE_ADD_WINE_DUPLICATE_TEXT,
			SUCCESS_CODE);

		logger.error("Duplicate entry for wine name ");
	    } else {
		response = ApplicationUtils.errorMessageGenerate(
			SystemErrorCode.WINE_ADD_UNKNOWN_ERROR,
			SystemErrorCode.WINE_ADD_UNKNOWN_ERROR_TEXT,
			SUCCESS_CODE);

		logger.error("Unkonwn error ");
	    }
	}

	output.put(OUPUT_PARAM_KEY, response);

	logger.info("exit addWine method");

	return output;
    }

    /**
     * This method is used to update the wine in the database.
     * 
     * @param wineUpdatePO is used to take the input in this PO.
     * @return output map containing response
     */
    public static Map<String, Object> updateWine(final WineUpdatePO wineUpdatePO) {

	logger.info("start updateWine method");

	String errorMsg = StringUtils.EMPTY;

	final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

	Response response = null;
	// TODO add check if any order is pending for wine then wine can not be edited
	try {

	    MasterData vintage = null;
	    MasterData bottleInMl = null;
	    MasterData wineStyle = null;

	    vintage = MasterDataRepository.getMasterDataById(Long
		    .parseLong(wineUpdatePO.getVintageId()));
	    if (vintage == null) {
		// vintage not exist
		response = ApplicationUtils.errorMessageGenerate(

			SystemErrorCode.UPDATE_WINE_INVALID_VINTAGE,
			SystemErrorCode.UPDATE_WINE_INVALID_VINTAGE_TEXT,
			SUCCESS_CODE);

		logger.error("vintage not exist");
	    }

	    if (response == null) {
		bottleInMl = MasterDataRepository.getMasterDataById(Long
			.parseLong(wineUpdatePO.getBottleInMlId()));
		if (bottleInMl == null) {
		    // bottleInMlId not exist
		    response = ApplicationUtils
			    .errorMessageGenerate(

				    SystemErrorCode.UPDATE_WINE_INVALID_BOTTLE_IN_ML,
				    SystemErrorCode.UPDATE_WINE_INVALID_BOTTLE_IN_ML_TEXT,
				    SUCCESS_CODE);

		    logger.error("bottleInMlId not exist");
		}
		
		
		
	    }

	    ProductItemModel productItemModel = ProductItemRepository.getProductItemById(Long.parseLong(wineUpdatePO.getId()));
	    if(productItemModel == null)
	    {
		response = ApplicationUtils.errorMessageGenerate(

			SystemErrorCode.UPDATE_WINE_INVALID_WINE_ID,
			SystemErrorCode.UPDATE_WINE_INVALID_WINE_ID_TEXT,
			SUCCESS_CODE);

		logger.error("Product Id not exist");
	    }

	    WineModel wineModel = null;
	    if (response == null) {
		wineModel = WineRepository.getWineById(productItemModel.getItemId());
		if (wineModel == null) {
		    // Wine not exist
		    response = ApplicationUtils.errorMessageGenerate(

			    SystemErrorCode.UPDATE_WINE_INVALID_WINE_ID,
			    SystemErrorCode.UPDATE_WINE_INVALID_WINE_ID_TEXT,
			    SUCCESS_CODE);

		    logger.error("Wine Id not exist");
		}
	    }

	    if (response == null) {

		String wineFullName = PropertyholderUtils
			.getStringProperty("wine.full.name");

		wineFullName = wineFullName.replace("<vintage>",
			vintage.getName() + " ");

		wineFullName = wineFullName.replace("<winery>", wineModel
			.getWineryId().getWineryName() + " ");

		wineFullName = wineFullName.replace("<winename>",
			wineUpdatePO.getWineName());

		wineModel.setWineFullName(wineFullName);						
		wineModel.setWineName(wineUpdatePO.getWineName());
		wineModel.setVintage(vintage);
		wineModel.setBottleInMl(bottleInMl);
		wineModel.setVerietal(MasterDataRepository.getMasterDataById(Long
					.parseLong(wineUpdatePO.getVarietalId())));
		
		if (wineUpdatePO.getAlcoholPercentage() != null){

		    
			wineModel.setAlcoholPercentage(Double.parseDouble(wineUpdatePO.getAlcoholPercentage()));
		    
		   
		}
		
		if(wineUpdatePO.getWineStyleId() != null){
			
			wineStyle = MasterDataRepository.getMasterDataById(Long
					.parseLong(wineUpdatePO.getWineStyleId()));
			wineModel.setWineType(wineStyle);
		}
		if(wineUpdatePO.getStatus() != null)
		{
		    wineModel.setIsEnabled(Boolean.parseBoolean(wineUpdatePO.getStatus()));
		}				

		WineRepository.update(wineModel);

		WineVO wineVO = new WineVO(
			SystemErrorCode.WINE_UPDATE_SUCCESS_TEXT);

		BeanUtils.copyProperties(wineVO, wineModel);

		wineVO.setId(productItemModel.getId());
		wineVO.setWineId(wineModel.getId());				
		wineVO.setWineryId(wineModel.getWineryId().getId());
		
		if(wineModel.getWarehouseId() != null)
	    {
	    	wineVO.setWarehouseId(wineModel.getWarehouseId().getId());
	    }		

		response = new com.wineaccess.response.SuccessResponse(wineVO,
			SUCCESS_CODE);
	    }
	} catch (Exception e) {

	    errorMsg = e.getCause().getMessage();

	    logger.error("Exception occured during processing of update wine "
		    + e);
	}

	if (response == null) {
	    if (errorMsg.contains("uk_wine")) {

		response = ApplicationUtils.errorMessageGenerate(
			SystemErrorCode.WINE_UPDATE_WINE_DUPLICATE,
			SystemErrorCode.WINE_UPDATE_WINE_DUPLICATE_TEXT,
			SUCCESS_CODE);

		logger.error("Duplicate entry for wine name ");

	    } else {

		response = ApplicationUtils.errorMessageGenerate(
			SystemErrorCode.WINE_UPDATE_UNKNOWN_ERROR,
			SystemErrorCode.WINE_UPDATE_UNKNOWN_ERROR_TEXT,
			SUCCESS_CODE);

		logger.error("Unkonwn error ");
	    }
	}

	output.put(OUPUT_PARAM_KEY, response);

	logger.info("exit updateWine method");

	return output;
    }

    /**
     * View the wine details based on wine id
     * 
     * @param winePO the wine PO
     * @return Map the output map
     */
    public static Map<String, Object> viewWineDetails(final ViewWinePO winePO) {

	logger.info("populate the wine details and key metrics");
	Map<String, Object> output = new ConcurrentHashMap<String, Object>();
	Response response = null;

	final ViewWineVO wineVO = new ViewWineVO();

	ProductItemModel productItemModel = ProductItemRepository.getProductItemById(Long.parseLong(winePO.getId()));
	if(productItemModel == null)
	{
	    response = ApplicationUtils.errorMessageGenerate(

		    SystemErrorCode.VIEW_WINE_INVALID_WINE_ID,
		    SystemErrorCode.VIEW_WINE_INVALID_WINE_ID_TEXT,
		    SUCCESS_CODE);

	    logger.error("Product Id not exist");
	}
	
	if(response == null){
	    final WineModel wineModel = WineRepository.getWineById(productItemModel.getItemId());

	    if (wineModel != null) {

		WineDetails wineDetails = populateWineDetails(wineModel);
		wineVO.setWineDetails(wineDetails);
		wineVO.getWineDetails().setId(productItemModel.getId());

		WineKeyMetrics wineKeyMetrics = populateWineKeyMetrics();
		wineVO.setKeyMetrics(wineKeyMetrics);

		response = new com.wineaccess.response.SuccessResponse(wineVO,SUCCESS_CODE);
	    } else {
		response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_DETAILS_ERROR,
			WineaccessErrorCodes.SystemErrorCode.WINE_DETAILS_ERROR_TEXT,SUCCESS_CODE);
	    }
	}
	logger.info("generate the reponse of wine details");
	output.put(OUPUT_PARAM_KEY, response);
	return output;
    }

    /**
     * populate the Wine details from the wine model
     * 
     * @param wineModel
     *            the model of wine
     * @return the WineDetails
     */
    private static WineDetails populateWineDetails(final WineModel wineModel) {

		Map<String, String> wineStyleMap = new HashMap<String, String>();
		if (wineModel.getWineType() != null) {
		    MasterData wineStyleName = wineModel.getWineType();
		    if (wineStyleName != null) {
			wineStyleMap.put(ID, Long.toString(wineStyleName.getId()));
			wineStyleMap.put(NAME, wineStyleName.getName());
		    }
		}
	
		Map<String, String> varietalMap = new HashMap<String, String>();
		if (wineModel.getVerietal() != null) {
		    MasterData varietalName = wineModel.getVerietal();
		    if (varietalName != null) {
			varietalMap.put(ID, Long.toString(varietalName.getId()));
			varietalMap.put(NAME, varietalName.getName());
		    }
		}
	
		Map<String, String> vintageMap = new HashMap<String, String>();
		if (wineModel.getVintage() != null) {
		    MasterData vintageName = wineModel.getVintage();
		    if (vintageName != null) {
			vintageMap.put(ID, Long.toString(vintageName.getId()));
			vintageMap.put(NAME, vintageName.getName());
		    }
		}
	
		Map<String, String> sizeMap = new HashMap<String, String>();
		if (wineModel.getBottleInMl() != null) {
		    MasterData sizeInMl = wineModel.getBottleInMl();
		    if (sizeInMl != null) {
			sizeMap.put(ID, Long.toString(sizeInMl.getId()));
			sizeMap.put(NAME, sizeInMl.getName());
		    }
		}
	
		Map<String, String> importerMap = new HashMap<String, String>();
		if (wineModel.getImporterId() != null) {
		    ImporterModel importer = wineModel.getImporterId();				
		    if (importer != null) {
			importerMap.put(ID, Long.toString(importer.getId()));
			importerMap.put(NAME, importer.getImporterName());
		    }
		}
	
		Map<String, String> countryMap = new HashMap<String, String>();
		if (wineModel.getIsImported()) {
		    if(null != wineModel.getImporterId()){
			CountryModel country = wineModel.getImporterId().getCountryId();				
			if (country != null) {
			    countryMap.put(ID, Long.toString(country.getId()));
			    countryMap.put(NAME, country.getCountryName());
			    countryMap.put("code", country.getCountryCode());
			}	
		    }
		} else{
		    /*	CountryModel country = CountryRepository.getByCountryCode("US");
				if(null != country){
					countryMap.put(ID, Long.toString(country.getId()));
					countryMap.put(NAME, country.getCountryName());
					countryMap.put("code", country.getCountryCode());
				}*/
		}
	
		Map<String, String> wineSourcingMap = new HashMap<String, String>();
		if (wineModel.getWineSourcingId() != null) {
		    MasterData wineSourcing = wineModel.getWineSourcingId();			
		    if (wineSourcing != null) {
			wineSourcingMap.put(ID, Long.toString(wineSourcing.getId()));
			wineSourcingMap.put(NAME, wineSourcing.getName());
		    }
		}
	
		Map<String, String> bottlesPerBoxMap = new HashMap<String, String>();
		if (wineModel.getBottlesPerBox() != null) {
		    MasterData bottlesPerBox = wineModel.getBottlesPerBox();			
		    if (bottlesPerBox != null) {
			bottlesPerBoxMap.put(ID, Long.toString(bottlesPerBox.getId()));
			bottlesPerBoxMap.put(NAME, bottlesPerBox.getName());
		    }
		}	
	
		Map<String, String> wineryMap = new HashMap<String, String>();
		Map<String, String> waContactMap = new HashMap<String, String>();
		Map<String, String> waFreightRegionMap = new HashMap<String, String>();
		if (wineModel.getWineryId() != null) {
	
		    WineryModel wineryModel = wineModel.getWineryId();
		    if (wineryModel != null) {
			wineryMap.put(ID, Long.toString(wineryModel.getId()));
			wineryMap.put(NAME, wineryModel.getWineryName());
	
			if (wineryModel.getWaContact() != null) {
			    MasterData waContact = wineryModel.getWaContact();
			    if (waContact != null) {
				waContactMap.put(ID, Long.toString(waContact.getId()));
				waContactMap.put(NAME, waContact.getName());
			    }
			}
	
			//Map<String,String> freightMap = new HashMap<String, String>();
			 
			Long frightRegionId = null;
			 if(wineryModel.getFreightRegion() != null){
				 frightRegionId = wineryModel.getFreightRegion().getId();
			 }
			
			  if(wineryModel.getActiveImporterId() != null && wineryModel.getActiveImporterId().getFreightRegion() != null){
				  frightRegionId = wineryModel.getActiveImporterId().getFreightRegion().getId();
			  }
			if(frightRegionId != null){
			    final MasterData freight = MasterDataRepository.getMasterDataById(frightRegionId);
			    waFreightRegionMap.put(ID, freight.getId().toString());
			    waFreightRegionMap.put(NAME, freight.getName());
			}
			
			
		    }
		}
	   
		
		
		
		Map<String, String> contactMap = new HashMap<String, String>();
		if (wineModel.getContactId() != null) {
		    WineryImporterContacts contacts = wineModel.getContactId();				
		    if (contacts != null) {
			contactMap.put(ID, Long.toString(contacts.getId()));
			contactMap.put(NAME, contacts.getName());
		    }
		}
		
		Map<String, String> warehouseMap = new HashMap<String, String>();	
		if (wineModel.getWarehouseId() != null) {
		    WarehouseModel warehouseModel = wineModel.getWarehouseId();	    
		    if (warehouseModel != null) 
		    {    	
		    	warehouseMap.put(ID, Long.toString(warehouseModel.getId()));
		    	warehouseMap.put(NAME, warehouseModel.getName());	
		    }
		}
	
		WineDetails details = new WineDetails();
		details.setId(wineModel.getProduct().getId());
		details.setWineName(wineModel.getWineName());
		details.setFullWineName(wineModel.getWineFullName());
		details.setWineId(wineModel.getId());
		details.setWineStyle(wineStyleMap);
		details.setVarietal(varietalMap);
		details.setVintage(vintageMap);
		details.setSize(sizeMap);
		details.setWinery(wineryMap);
		details.setImporter(importerMap);
		details.setIsImported(wineModel.getIsImported());
		details.setCountry(countryMap);
		details.setWaContact(waContactMap);
		details.setFreightRegion(waFreightRegionMap);
		details.setWineSourcing(wineSourcingMap);
		details.setAlcoholPercentage(wineModel.getAlcoholPercentage());
		details.setBottlesPerBox(bottlesPerBoxMap);
		details.setNotes(wineModel.getNotes());
		details.setPrivateLabel(wineModel.getPrivateLabel());
		details.setSendToFullfillerOn(wineModel.getSendToFullfillerOn());
		details.setIsEnabled(wineModel.getIsEnabled());
		details.setUsaArrivalDate(wineModel.getUsaArrivalDate());
		details.setWineShortName(wineModel.getWineShortName());
		details.setWarehouse(warehouseMap);
		details.setContact(contactMap);
	
		return details;
    }

    /**
     * populate the wine key metrics
     * 
     * @return WineKeyMetrics
     */
    private static WineKeyMetrics populateWineKeyMetrics() {

	WineKeyMetrics keyMetrics = new WineKeyMetrics();
	return keyMetrics;
    }


    /**
     * Method to add/update wine logistic
     * @param addLogisticPO PO to add wine logistic 
     * @return
     */
    public static Map<String, Object> addLogistic(final AddLogisticPO addLogisticPO) {
    	
	Response response = new FailureResponse();
	response.setStatus(200);	
	final WineLogisticBasicVO addWineVO = new WineLogisticBasicVO();
	final Long wineryId = Long.parseLong(addLogisticPO.getWineryId());
	final Long productId = Long.parseLong(addLogisticPO.getProductId());
	final Long contactId = Long.parseLong(addLogisticPO.getContactId());

	final Map <String, Object> outputAddLogistic = new ConcurrentHashMap<String,Object>();
	try{
	    final WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
	    final ImporterModel importerModel = wineryModel.getActiveImporterId();
	    ProductItemModel productModel = ProductItemRepository.getProductItemById(productId);
	    WineModel wineModel = null;	    
	    WineryImporterContacts contactModel = null;
	    MasterData bottlePerBox = null;
	    WarehouseModel warehouseModel = null;

	    if(productModel != null)
	    {
			wineModel = WineRepository.getWineById(productModel.getItemId());
			if(wineModel == null)
			{
			    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_WINE,SystemErrorCode.LOGISTIC_INVALID_WINE_TEXT));			
			    logger.error("wine not exist");
			}
	    }

	    if(wineryModel == null)
	    {
			response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_WINERY,SystemErrorCode.LOGISTIC_INVALID_WINERY_TEXT));			
			logger.error("winery not exist");
	    }

	    if(productModel == null)
	    {
			response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_PRODUCT,SystemErrorCode.LOGISTIC_INVALID_PRODUCT_TEXT));			
			logger.error("product not exist");
	    }			

	    if(productModel!=null && wineModel!=null && wineryModel!=null)
	    {	    	
	    	if(addLogisticPO.getWarehouseId() != null && !(StringUtils.EMPTY).equals(addLogisticPO.getWarehouseId()))
			{				
				warehouseModel = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(addLogisticPO.getWarehouseId()));	
				if(warehouseModel == null)
				{					
					response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_WAREHOUSE,SystemErrorCode.LOGISTIC_INVALID_WAREHOUSE_TEXT));			
					logger.error("warehouse does not exist");				
				}	
			}	
	    	else
	    	{
	    		wineModel.setWarehouseId(warehouseModel);
	    	}
	    	contactModel = WineryImporterContactRepository.getContactById(Long.parseLong(addLogisticPO.getContactId()));
	    	
			//contactModel = WineryImporterContactRepository.getContactByContactIdWineryId(wineryId,contactId);
			bottlePerBox =  MasterDataRepository.getMasterDataById(Long.parseLong(addLogisticPO.getBottlePerBox()));
			final Long wineryIdFromModel = wineModel.getWineryId().getId();
			final Long wineIdFromModel = wineryModel.getId();
			if(!wineryIdFromModel.equals(wineIdFromModel))
			{
			    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_WINE_WINERY,SystemErrorCode.LOGISTIC_INVALID_WINE_WINERY_TEXT));			
			    logger.error("wine and winery combination not exist");
			}		

			if(contactModel == null)
			{
			    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_CONTACT,SystemErrorCode.LOGISTIC_INVALID_CONTACT_TEXT));			
			    logger.error("wine and winery combination not exist");
			}
	
			if(bottlePerBox==null)
			{
			    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_BOTTLE_PER_BOX,SystemErrorCode.LOGISTIC_INVALID_BOTTLE_PER_BOX_TEXT));			
			    logger.error("wine and winery combination not exist");
			}
	    }

	    if(response.getErrors().isEmpty() && wineModel != null)
	    {
		    wineModel.setWarehouseId(warehouseModel);	
			wineModel.setContactId(contactModel);
			wineModel.setIsFullCaseOnly(Boolean.valueOf(addLogisticPO.getIsFullCaseOnly()));
			wineModel.setBottleWeightInLBS(Long.parseLong(addLogisticPO.getBottleWeightInLBS()));
			wineModel.setBottlesPerBox(bottlePerBox);
			wineModel = WineRepository.update(wineModel);
			
			if(wineModel.getId()!=null)
			{ 
			    addWineVO.setWineId(wineModel.getId());
			    addWineVO.setWineryId(wineModel.getWineryId().getId());
			    addWineVO.setProductId(productModel.getId());
			    addWineVO.setMessage("Logistic for Wine Created");
			    response = new com.wineaccess.response.SuccessResponse(addWineVO, 200);
			}
			else
			{
			    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_NOT_CREATED,SystemErrorCode.LOGISTIC_NOT_CREATED_TEXT));	
			}
	    }

	}
	catch (Exception e) {
	    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_NOT_CREATED,SystemErrorCode.LOGISTIC_NOT_CREATED_TEXT));	
	    logger.error("Some error occured", e.fillInStackTrace());
	}
	outputAddLogistic.put("FINAL-RESPONSE", response);

	return outputAddLogistic;

    }

    /**
     * Method to view detail of wine logistic
     * @param viewWineLogisticPO
     * @return
     */
    public static Map<String, Object> viewLogistic(final ViewWineLogisticPO viewWineLogisticPO) 
    {
	final Map <String, Object> outputViewLogistic = new ConcurrentHashMap<String,Object>();
	Response response = new FailureResponse();
	response.setStatus(200);
	try{
	    final Long productId = Long.parseLong(viewWineLogisticPO.getProductId());
	    WineModel wineModel = null;
	    ProductItemModel productModel = ProductItemRepository.getProductItemById(productId);

	    if(productModel == null)
	    {
			response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_PRODUCT,SystemErrorCode.LOGISTIC_INVALID_PRODUCT_TEXT));			
			logger.error("productId not exist");
	    }
	    else if(productModel != null)
	    {
			wineModel = WineRepository.getWineById(productModel.getItemId());
			if(wineModel == null)
			{
			    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_INVALID_WINE,SystemErrorCode.LOGISTIC_INVALID_WINE_TEXT));			
			    logger.error("wine not exist");
			}		
			/*else if(wineModel.getWarehouseId()==null || wineModel.getContactId()==null || wineModel.getBottleWeightInLBS()==null ||wineModel.getBottlesPerBox() ==null)
			{
				response.addError(new WineaccessError(SystemErrorCode.NO_ENTITY_FOUND,SystemErrorCode.NO_ENTITY_FOUND_TEXT));			
				logger.error("wine not exist");
			}*/
			else
			{
			    ViewWineLogisticVO viewWineLogisticVO = populateVOFromDOViewLogistic(wineModel,productModel);
			    response = new com.wineaccess.response.SuccessResponse(viewWineLogisticVO, 200);	
			}
	    }
	}
	catch (Exception e) {
	    response.addError(new WineaccessError(SystemErrorCode.LOGISTIC_VIEW_ERROR,SystemErrorCode.LOGISTIC_VIEW_ERROR_TEXT));	
	    logger.error("Some error occured", e);
	}
	outputViewLogistic.put("FINAL-RESPONSE", response);
	return outputViewLogistic;
    }

    /**
     * @param wineModel
     * @return
     */
    private static ViewWineLogisticVO populateVOFromDOViewLogistic(final WineModel wineModel, final ProductItemModel productModel) {
	
    	final WineryModel wineryModel = wineModel.getWineryId();	
    	Long contactId = null;
    	WineryImporterContacts contactModel = null;
    	ImporterModel importer = wineModel.getWineryId().getActiveImporterId();
    	
    	
    	
    	
    	Map<String,String> contactMap = new HashMap<String, String>();
    	if(wineModel.getContactId() != null)
    	{
    		contactId = wineModel.getContactId().getId();		
		    contactModel = WineryImporterContactRepository.getContactById(contactId);
		    contactMap.put("id",contactId.toString());
			contactMap.put("value", contactModel.getName());
    	}
    	
    	Map<String,String> bottlePerBoxMap = new HashMap<String, String>();
    	if(wineModel.getBottlesPerBox() != null)
    	{
    		MasterData bottlePerBox = MasterDataRepository.getMasterDataById(wineModel.getBottlesPerBox().getId());
    		bottlePerBoxMap.put("id",bottlePerBox.getId().toString());
    		bottlePerBoxMap.put("value", bottlePerBox.getName());
    	}
	
		Map<String,String> freightMap = new HashMap<String, String>();
		 
		Long frightRegionId = null;
		 if(wineryModel.getFreightRegion() != null){
			 frightRegionId = wineryModel.getFreightRegion().getId();
		 }
		
		  if(importer != null && importer.getFreightRegion() != null){
			  frightRegionId = importer.getFreightRegion().getId();
		  }
		if(frightRegionId != null){
		    final MasterData freight = MasterDataRepository.getMasterDataById(frightRegionId);
		    freightMap.put(ID, freight.getId().toString());
		    freightMap.put(NAME, freight.getName());
		}
	
		Map<String, String> warehouseMap = new HashMap<String, String>();	
		if (wineModel.getWarehouseId() != null) {
		    WarehouseModel warehouseModel = wineModel.getWarehouseId();	    
		    if (warehouseModel != null) 
		    {    	
		    	warehouseMap.put(ID, Long.toString(warehouseModel.getId()));
		    	warehouseMap.put(NAME, warehouseModel.getName());	
		    }
		}
	
		final ViewWineLogisticVO viewWineLogisticVO = new ViewWineLogisticVO();
	
		if(wineModel.getBottleWeightInLBS() != null)
		{
		viewWineLogisticVO.setBottleWeightInLBS(wineModel.getBottleWeightInLBS());
		}
		viewWineLogisticVO.setIsFullCaseOnly(wineModel.getIsFullCaseOnly());
		viewWineLogisticVO.setFreight(freightMap);		
		viewWineLogisticVO.setWarehouse(warehouseMap);

		
		
		viewWineLogisticVO.setContactId(contactMap);

		viewWineLogisticVO.setWineFullName(wineModel.getWineFullName());
		viewWineLogisticVO.setWineId(wineModel.getId());
		viewWineLogisticVO.setWineryId(wineryModel.getId());

		
		
		viewWineLogisticVO.setBottlePerBox(bottlePerBoxMap);
		
		Map<String, String> waFreightRegionMap = new HashMap<String, String>();
		if (wineModel.getWineryId().getFreightRegion() != null) {
		    MasterData freightRegion = wineryModel.getFreightRegion();
		    if (freightRegion != null) {
			waFreightRegionMap.put(ID,Long.toString(freightRegion.getId()));
			waFreightRegionMap.put(NAME, freightRegion.getName());
		    }
		}
		
		viewWineLogisticVO.setFreight(waFreightRegionMap);
		viewWineLogisticVO.setProductId(productModel.getId());
		return viewWineLogisticVO;
    }

    /**
     * @param address
     * @return
     */
    public static String extractCompleteAddress(final WineryImporterAddressModel address) {

	try{

	    final StringBuilder completeAddress = new StringBuilder();
	    completeAddress.append(address.getAddressLine1());
	    completeAddress.append(" ");

	    if(address.getAddressLine2()!=null){
		completeAddress.append(address.getAddressLine2());
		completeAddress.append(" ");
	    }

	    completeAddress.append(address.getCityId().getCityName());
	    completeAddress.append(" ");

	    completeAddress.append(address.getStateId().getStateName());
	    completeAddress.append(" ");

	    completeAddress.append(address.getCountryId().getCountryName());
	    completeAddress.append(" ");

	    completeAddress.append(address.getZipcode());
	    completeAddress.append(" ");
	    return completeAddress.toString();
	}
	catch (Exception e) {
	    return null;
	}
    }
    public static String extractCompleteAddressForWarehouse(WarehouseModel address) {

	try{

	    final StringBuilder completeAddress = new StringBuilder();
	    completeAddress.append(address.getAddressLine1());
	    completeAddress.append(" ");

	    if(address.getAddressLine2()!=null){
		completeAddress.append(address.getAddressLine2());
		completeAddress.append(" ");
	    }

	    completeAddress.append(address.getCityId().getCityName());
	    completeAddress.append(" ");

	    completeAddress.append(address.getStateId().getStateName());
	    completeAddress.append(" ");

	    //completeAddress.append(address.getCountryId().getCountryName());
	    //completeAddress.append(" ");

	    completeAddress.append(address.getZipcode());
	    completeAddress.append(" ");
	    return completeAddress.toString();
	}
	catch (Exception e) {
	    return null;
	}
    }

    public static Map<String, Object> basicWineSearch(final WineBasicSearchPO wineBasicSearchPO){
	Map<String, Object> output = new ConcurrentHashMap <String, Object>();
	WineSearchDAO wineSearchDAO = (WineSearchDAO) CoreBeanFactory.getBean("wineSearchDAO");
	Response response = null;
	try {
	    response = new com.wineaccess.response.SuccessResponse(wineSearchDAO.normalSearch(wineBasicSearchPO), 200);
	} catch (Exception e) {

	    response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_DETAILS_ERROR_103,WineaccessErrorCodes.SystemErrorCode.WINE_DETAILS_ERROR_103_TEXT,SUCCESS_CODE);
	}

	output.put("FINAL-RESPONSE", response);

	return output;
    }
    
    public static Map<String, Object> advanceWineSearch(final WineAdvanceSearchPO wineAdvanceSearchPO){

	Map<String, Object> output = new ConcurrentHashMap <String, Object>();
	WineSearchDAO wineSearchDAO = (WineSearchDAO) CoreBeanFactory.getBean("wineSearchDAO");
	Response response = null;
	try {
	    response = new com.wineaccess.response.SuccessResponse(wineSearchDAO.advanceSearch(wineAdvanceSearchPO), 200);
	} catch (Exception e) {

	    response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_DETAILS_ERROR_103,WineaccessErrorCodes.SystemErrorCode.WINE_DETAILS_ERROR_103_TEXT,SUCCESS_CODE);
	}

	output.put("FINAL-RESPONSE", response);

	return output;
    }

    /**
     * This method is used to perform the delete operation of wine (Soft delete)
     * 
     * @param wineDeletePO take the input parameter for deleting the wine
     * @return Map the output map
     */
    public static Map<String, Object> deleteWine(final WineDeletePO wineDeletePO) {

	logger.info("deleting the wines based on wine ids");

	Map<String, Object> output = new ConcurrentHashMap<String, Object>();
	Response response = null;

	List<Long> productIds = wineDeletePO.getProductIds();
	Boolean isForceDelete = false;
	if (StringUtils.isNotBlank(wineDeletePO.getIsForceDelete())) {
	    isForceDelete = Boolean.parseBoolean(wineDeletePO.getIsForceDelete());
	}

	BulkDeleteModel<WineModel> bulkDeleteModel = WineRepository.delete(productIds, isForceDelete);

	DeleteVO<WineDetails> wineDetailsForDependency = new DeleteVO<WineDetails>();
	DeleteVO<WineDetails> wineDetailsForDeleted = new DeleteVO<WineDetails>();

	List<WineDetails> deleteList = new ArrayList<WineDetails>();
	List<WineDetails> dependencyList = new ArrayList<WineDetails>();

	List<WineModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

	for (WineModel wineModel : canBeDeletedList) {
	    deleteList.add(populateWineDetails(wineModel));
	}

	wineDetailsForDeleted.setElements(deleteList);

	List<WineModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

	for (WineModel wineModel : canNotBeDeletedList) {
	    dependencyList.add(populateWineDetails(wineModel));
	}

	wineDetailsForDependency.setElements(dependencyList);		

	Set<Long> set = new HashSet<Long>((List<Long>) bulkDeleteModel.getNotExistsList());
	set.addAll((List<Long>) bulkDeleteModel.getNotExistsList());
	List<Long> nonExistingList = new ArrayList<Long>(set);


	WineDeleteVO wineDeleteVO = new WineDeleteVO();
	wineDeleteVO.setNonExistsList(nonExistingList);
	wineDeleteVO.setFailureList(wineDetailsForDependency);
	wineDeleteVO.setSuccessList(wineDetailsForDeleted);

	response = new com.wineaccess.response.SuccessResponse(wineDeleteVO,SUCCESS_CODE);

	output.put(OUPUT_PARAM_KEY, response);

	logger.info("exit delete Wine method");

	return output;

    }

    /**
     * Method to enable/disable the wine
     * @param  ChangeWineStatusPO
     * @return
     */
    public static Map<String, Object> performBulkOperation(final ChangeWineStatusPO changeWineStatusPO){
	Response response = new FailureResponse();
	response.setStatus(200);	

	final Map <String, Object> outputBulkOperation = new ConcurrentHashMap<String,Object>();
	try{
	    Map<String,Boolean> dependantFieldsMap = new HashMap<String,Boolean>();
	    final String bulkOperation = changeWineStatusPO.getBulkOperation();
	    final List<Long> wineList = changeWineStatusPO.getProductId();
	    String forceUpdate = "false";
	    if(null!= wineList || wineList.isEmpty()){
		//List<Long> wineIds = ProductItemRepository.getWineIds(wineList, null);
		if(null != changeWineStatusPO.getForceUpdate() && !("").equals(changeWineStatusPO.getForceUpdate())){
		    forceUpdate = changeWineStatusPO.getForceUpdate();
		}
		dependantFieldsMap.put("isDeleted", true);
		BulkDeleteModel<WineModel> bulkDeleteModel = null;
		if(("E").equals(bulkOperation)) {
		    dependantFieldsMap.put("isEnabled", true);
		    bulkDeleteModel = WineRepository.enableDisable(wineList,  Boolean.parseBoolean(forceUpdate),dependantFieldsMap,true);	
		} else {
		    dependantFieldsMap.put("isEnabled", false);
		    bulkDeleteModel = WineRepository.enableDisable(wineList,   Boolean.parseBoolean(forceUpdate),dependantFieldsMap,false);	
		}

		DeleteVO<WineDetails> wineDetailsForDependency = new DeleteVO<WineDetails>();
		DeleteVO<WineDetails> wineDetailsForDeleted = new DeleteVO<WineDetails>();

		List<WineDetails> deleteList = new ArrayList<WineDetails>();
		List<WineDetails> dependencyList = new ArrayList<WineDetails>();

		List<WineModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

		for (WineModel wineModel : canBeDeletedList) {
		    deleteList.add(populateWineDetails(wineModel));
		}

		wineDetailsForDeleted.setElements(deleteList);

		List<WineModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

		for (WineModel wineModel : canNotBeDeletedList) {
		    dependencyList.add(populateWineDetails(wineModel));
		}

		wineDetailsForDependency.setElements(dependencyList);


		List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();

		ChangeWineStatusResponse changeWineStatusResponse = new ChangeWineStatusResponse();
		changeWineStatusResponse.setNonExistsList(nonExistingList);
		changeWineStatusResponse.setFailureList(wineDetailsForDependency);
		changeWineStatusResponse.setSuccessList(wineDetailsForDeleted);


		response = new com.wineaccess.response.SuccessResponse(changeWineStatusResponse,SUCCESS_CODE);
	    }else{
		response.addError(new WineaccessError(SystemErrorCode.WINE_PRODUCT_ID_MISSING,SystemErrorCode.WINE_PRODUCT_ID_MISSING_TEXT));	
	    }

	}
	catch (Exception e) {
	    response.addError(new WineaccessError(SystemErrorCode.WINE_UPDATE_STATUS_ERROR,SystemErrorCode.WINE_UPDATE_STATUS_ERROR_TEXT));	
	    logger.error("Error while updating the status of Wine. ", e.fillInStackTrace());
	}
	outputBulkOperation.put("FINAL-RESPONSE", response);

	return outputBulkOperation;

    } 
   
}
