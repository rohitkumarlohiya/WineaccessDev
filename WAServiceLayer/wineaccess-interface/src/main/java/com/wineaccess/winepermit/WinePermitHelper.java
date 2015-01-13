package com.wineaccess.winepermit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wineaccess.common.MasterDataModel;
import com.wineaccess.constants.EnumTypes;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.WineLicenseFullfillAltStates;
import com.wineaccess.data.model.catalog.WineLicenseNoPermit;
import com.wineaccess.data.model.catalog.WineLicensePermitAltStates;
import com.wineaccess.data.model.catalog.WineryLicenseFullfillAltStates;
import com.wineaccess.data.model.catalog.WineryLicensePermitAltStates;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.security.masterdatatype.MasterDataTypeAdapterHelper;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.winery.WineryRepository;
import com.wineaccess.winerypermit.WineryPermitHelper;
import com.wineaccess.winerypermit.WineryPermitRepository;

/**
 * @author abhishek.sharma1
 *
 */
public class WinePermitHelper {

    /**
     * @param winePermitPO
     * @return
     */
    private static Response response = new FailureResponse();
    private static Log logger = LogFactory.getLog(WinePermitHelper.class);
    public  Map<String, Object> generateAddPermitResponse(WinePermitPO winePermitPO)  throws PersistenceException {
	response = new FailureResponse();
	Map <String, Object> outputAddPermit = new HashMap<String,Object>();
	if(StringUtils.isNotBlank(winePermitPO.getProductId())){
	    Long wineId = Long.parseLong(winePermitPO.getProductId());

	    WineModel wineModel = WineRepository.getWineById(wineId);
	    if(wineModel == null)
	    {
		response.addError(new WineaccessError(SystemErrorCode.PERMIT_WINE_ERROR,SystemErrorCode.PERMIT_WINE_ERROR_TEXT));
	    }
	    else{
		validateWinePermitPO(winePermitPO);

		if(response.getErrors().isEmpty()){
		    if(populateAndSaveDOFromPO(winePermitPO,wineModel))					
			response = new com.wineaccess.response.SuccessResponse(new WinePermitAddVO(wineModel.getId(),"Permit created successfully"), 200);
		    else
			response.addError(new WineaccessError(SystemErrorCode.PERMIT_FAILURE_ERROR,SystemErrorCode.PERMIT_FAILURE_ERROR_TEXT));
		}
	    }
	}
	else
	    response.addError(new WineaccessError(SystemErrorCode.WINE_PERMIT_ERROR_102,SystemErrorCode.WINE_PERMIT_ERROR_102_TEXT));
	outputAddPermit.put("FINAL-RESPONSE", response);
	return outputAddPermit;
    }

    /**
     * @param winePermitViewPO
     * @return
     */
    public static Map<String, Object> generateViewPermitResponse(
	    WinePermitViewPO winePermitViewPO) {
	response = new FailureResponse();
	Map <String, Object> outputViewPermit = new HashMap<String,Object>();
	Long wineId = Long.parseLong(winePermitViewPO.getProductId());
	final ProductItemModel productModel = ProductItemRepository.getProductItemById(wineId);
	if(productModel == null){
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_WINE_ERROR,SystemErrorCode.PERMIT_WINE_ERROR_TEXT));
	}

	else {

	    WineModel wineModel = WineRepository.getWineById(productModel.getItemId());
	    if(wineModel == null)
	    {
		response.addError(new WineaccessError(SystemErrorCode.PERMIT_WINE_ERROR,SystemErrorCode.PERMIT_WINE_ERROR_TEXT));
	    }
	    else
	    { 
		WineryModel wineryModel = wineModel.getWineryId();
		if(BooleanUtils.isNotTrue(wineModel.getSellInAltStates()) && BooleanUtils.isNotTrue(wineModel.getSellInMainStates())
			&& (BooleanUtils.isNotFalse(wineryModel.getSellInAltStates()) || BooleanUtils.isNotFalse(wineryModel.getSellInMainStates())))
		{
		    copyFulfilModelFromWinery(wineModel);
		    copyPermitModelFromWinery(wineModel);
		    createNoPermitData(wineModel);
		    wineModel.setSellInAltStates(wineryModel.getSellInAltStates());
		    wineModel.setSellInMainStates(wineryModel.getSellInMainStates());
		    wineModel.setOptionSelectedAltstates(wineryModel.getOptionSelectedAltstates());
		    WineRepository.update(wineModel);
		}
		WinePermitDetailVO winePermitViewVO = new WinePermitDetailVO();

		winePermitViewVO.setWineId(wineModel.getId());
		winePermitViewVO.setIsSellInMainStates(wineModel.getSellInMainStates());


		SellInAltStatesResultModel sellInAltStatesModel = new SellInAltStatesResultModel();
		if(BooleanUtils.isTrue(wineModel.getSellInAltStates()))
		    sellInAltStatesModel.setIsSelected(true);


		Integer optionSelected = wineModel.getOptionSelectedAltstates();
		if(optionSelected!=null && optionSelected.equals(0)){
		    sellInAltStatesModel.setIsOptionSelectedKachinaAlt(true);

		}

		if(optionSelected!=null && optionSelected.equals(2)){

		    sellInAltStatesModel.setIsOptionSelectedNoPermit(true);

		}

		List<PermitModelResult> permitModelResultsList = new ArrayList<PermitModelResult>();
		OptionSelectedAltStatesResult optionSelectedAltStates = new OptionSelectedAltStatesResult();
		List<WineLicensePermitAltStates> wineLicensePermitAltStates = WinePermitRepository.findWineLicensePermitAltStates(wineId);


		for(WineLicensePermitAltStates permitModelFromDB: wineLicensePermitAltStates)
		{
		    PermitModelResult permitModelResult = new PermitModelResult();
		    WinePermitModelWithMasterData winePermitModelWithMasterData = new WinePermitModelWithMasterData();
		    MasterDataModel masterDataModel = new MasterDataModel();
		    Long masterDataForPermit = permitModelFromDB.getWineryPermit().getId();

		    permitModelResult.setDtcPermitEndDate(permitModelFromDB.getDtcPermitEndDate());
		    permitModelResult.setDtcPermitStartDate(permitModelFromDB.getDtcPermitStartDate());
		    permitModelResult.setDtcPermitNumber(permitModelFromDB.getDtcPermitNumber());
		    permitModelResult.setIsSelected(permitModelFromDB.getIsSelected());
		    permitModelResult.setPermitDurationInMonths(permitModelFromDB.getDtcPermitDurationInMonths());

		    masterDataModel.setId(masterDataForPermit);
		    masterDataModel.setMasterDataTypeName(EnumTypes.MasterDataTypeEnum.WineryLicencePermit.name());
		    masterDataModel.setMasterDataName(permitModelFromDB.getWineryPermit().getName());
		    winePermitModelWithMasterData.setMasterData(masterDataModel);


		    permitModelResult.setWinePermit(winePermitModelWithMasterData);
		    permitModelResultsList.add(permitModelResult);

		}

		optionSelectedAltStates.setPermit(permitModelResultsList);
		/*}*/
		WineLicenseFullfillAltStates fulfilModelFromDB = WinePermitRepository.findFulfilModelByWineId(wineId);
		FulFillModel fulfiModel = new FulFillModel();
		if(fulfilModelFromDB!=null){

		    if(BooleanUtils.isTrue(fulfilModelFromDB.getWaWillNotFullFill()))
		    {

		    }
		    fulfiModel.setIsSelected(BooleanUtils.toStringTrueFalse(fulfilModelFromDB.getIsSelected()));
		    fulfiModel.setEscrowContract(BooleanUtils.toStringTrueFalse(fulfilModelFromDB.getEscrowContract()));
		    fulfiModel.setWaPlatformContract(BooleanUtils.toStringTrueFalse(fulfilModelFromDB.getWaPlatformContract()));
		    fulfiModel.setIsStorageContact(BooleanUtils.toStringTrueFalse(fulfilModelFromDB.getWineryStorageContract()));

		    optionSelectedAltStates.setFulfillDirectlyNotWA(fulfilModelFromDB.getWaWillNotFullFill());
		}

		optionSelectedAltStates.setFulfill(fulfiModel);

		/*WineLicenseFullfillAltStates wineFullfillAltStatesModel =  WinePermitRepository.findFulfilModelByWineId(wineModel.getId());
					if(wineFullfillAltStatesModel!=null)
					{

					}
					sellInAltStatesModel.setOptionSelectedAltStates(optionSelectedAltStates);
				}
			}
			 wineFullfillAltStatesModel =  WinePermitRepository.findFulfilModelByWineId(wineModel.getId());
			if(wineFullfillAltStatesModel!=null)
			{

			}*/
		Long mappedWineryId = wineModel.getMappedWineryWithPermit();
		if(mappedWineryId!=null){
		    CustomWineryModel customWineryModel = new CustomWineryModel();

		    if(WineryRepository.getWineryById(mappedWineryId)!=null)
		    {
			customWineryModel.setWineryId(mappedWineryId);
			customWineryModel.setWineryName(WineryRepository.getWineryById(mappedWineryId).getWineryName());
			optionSelectedAltStates.setMappedWineryWithPermit(customWineryModel);
		    }
		}

		sellInAltStatesModel.setOptionSelectedAltStates(optionSelectedAltStates);
		Integer optionSelect = wineModel.getOptionSelectedAltstates();
		if(optionSelect!=null){
		    if(0==optionSelect)
		    {
			sellInAltStatesModel.setIsOptionSelectedKachinaAlt(true);
		    }

		}




		List<WineLicenseNoPermit> wineLicenseNoPermit = WinePermitRepository.findWineLicenseNoPermitAltStates(wineId);
		List<OptionSelectedNoPermitResult> optionSelectedNoPermitResult = new ArrayList<OptionSelectedNoPermitResult>();

		for(WineLicenseNoPermit wineLicenseNoPermitList: wineLicenseNoPermit)
		{
		    OptionSelectedNoPermitResult noPermitResult = new OptionSelectedNoPermitResult();
		    WinePermitModelWithMasterData winePermitModelWithMasterData = new WinePermitModelWithMasterData();
		    MasterDataModel masterDataModel = new MasterDataModel();
		    Long masterDataForNoPermit = wineLicenseNoPermitList.getWineNoPermit().getId();

		    noPermitResult.setPriceFiled(wineLicenseNoPermitList.getPriceFiled());
		    noPermitResult.setSc3TStatus(wineLicenseNoPermitList.getStatus());
		    noPermitResult.setIsSelected(wineLicenseNoPermitList.getIsSelected());
		    masterDataModel.setId(masterDataForNoPermit);
		    masterDataModel.setMasterDataTypeName(EnumTypes.MasterDataTypeEnum.WineryLicenceNoPermit.name());
		    masterDataModel.setMasterDataName(wineLicenseNoPermitList.getWineNoPermit().getName());
		    winePermitModelWithMasterData.setMasterData(masterDataModel);
		    noPermitResult.setWinePermit(winePermitModelWithMasterData);
		    optionSelectedNoPermitResult.add(noPermitResult);

		}

		sellInAltStatesModel.setOptionSelectedNoPermit(optionSelectedNoPermitResult);



		winePermitViewVO.setSellInAltStates(sellInAltStatesModel);
		/*}*/
		response = new com.wineaccess.response.SuccessResponse(winePermitViewVO, 200);
	    }
	}
	outputViewPermit.put("FINAL-RESPONSE", response);
	return outputViewPermit;
    }




    /**
     * @param wineModel
     */
    private static void createNoPermitData(WineModel wineModel) {
	try{


	    Set<MasterData> winerNOPermitMasterDataList = MasterDataTypeAdapterHelper.getMasterDataListByMasterTypeName("WineryLicenceNoPermit");

	    for(MasterData masterDataFromDB: winerNOPermitMasterDataList)
	    {
		WineLicenseNoPermit wineLicenseNoPermit = new WineLicenseNoPermit();
		wineLicenseNoPermit.setWineNoPermit(masterDataFromDB);
		wineLicenseNoPermit.setProductId(wineModel);
		WinePermitRepository.saveWineLicenseNoPermit(wineLicenseNoPermit);
	    }
	}

	catch (Exception e) {
	    logger.info("permit coudn't be created");
	    logger.error(e);
	}

    }

    /**
     * @param winePermitPO
     * @param wineModel 
     * @return 
     * @throws Exception 
     */
    private static boolean populateAndSaveDOFromPO(WinePermitPO winePermitPO, WineModel wineModel) throws PersistenceException {
	boolean isSussess = false;
	try{


	    wineModel.setSellInMainStates(BooleanUtils.toBoolean(winePermitPO.getIsSellInMainStates()));
	    SellInAltStatesModel sellInAltStatesModel = winePermitPO.getSellInAltStates();

	    if(winePermitPO.getSellInAltStates()!=null && BooleanUtils.toBoolean(winePermitPO.getSellInAltStates().getIsSelected())){
		wineModel.setSellInAltStates(true);
		if(BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedKachinaAlt())){
		    wineModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.KACHINA_ALT.getOptionSelectedaltStates());
		}
		if(sellInAltStatesModel.getOptionSelectedAltStates()!=null)
		{


		    wineModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.PERMIT_FOR_ALT_STATES.getOptionSelectedaltStates());
		    OptionSelectedAltStates optionSelectedAltStates = sellInAltStatesModel.getOptionSelectedAltStates();
		    if(StringUtils.isNotBlank(optionSelectedAltStates.getMappedWineryId()))
		    {
			WineryModel mappedWineryWithPermit = WineryRepository.getWineryById(Long.valueOf(optionSelectedAltStates.getMappedWineryId()));
			if(mappedWineryWithPermit != null){
			    wineModel.setMappedWineryWithPermit(mappedWineryWithPermit.getId());
			}
		    }

		    List<PermitModel> permitModelList = optionSelectedAltStates.getPermit();
		    if(permitModelList!=null && !permitModelList.isEmpty())
		    {
			Map<Long, Long> permitIdsMap = new ConcurrentHashMap<Long, Long>();
			List<Object[]> permitIdsList = WinePermitRepository.findWinePermitIdByWineId(wineModel.getId());
			for(Object[] ids:permitIdsList)
			{
			    permitIdsMap.put((Long)ids[0],(Long)ids[1]);
			}

			for(PermitModel permitModel: permitModelList){
			    Long masterDataId = Long.valueOf(permitModel.getMasterDataId());
			    /*if(BooleanUtils.toBoolean(permitModel.getIsSelected())){*/
			    WineLicensePermitAltStates wineLicensePermitAltStates = new WineLicensePermitAltStates();
			    wineLicensePermitAltStates.setIsSelected(BooleanUtils.toBoolean(permitModel.getIsSelected()));
			    if(!StringUtils.isEmpty(permitModel.getPermitDurationInMonths()))
				wineLicensePermitAltStates.setDtcPermitDurationInMonths(Integer.valueOf(permitModel.getPermitDurationInMonths()));
			    if(!StringUtils.isEmpty(permitModel.getDtcPermitNumber()))
				wineLicensePermitAltStates.setDtcPermitNumber(permitModel.getDtcPermitNumber());
			    wineLicensePermitAltStates.setDtcPermitStartDate(permitModel.getDtcPermitStartDate());
			    wineLicensePermitAltStates.setDtcPermitEndDate(permitModel.getDtcPermitEndDate());
			    /*	}*/
			    wineLicensePermitAltStates.setWineryPermit(MasterDataRepository.getMasterDataById(masterDataId));
			    wineLicensePermitAltStates.setWineId(wineModel);
			    wineLicensePermitAltStates.setId(permitIdsMap.get(masterDataId));
			    WinePermitRepository.saveWineLicensePermitAltStates(wineLicensePermitAltStates);

			}
			FulFillModel fulFilModel = optionSelectedAltStates.getFulfill();
			WineLicenseFullfillAltStates wineLicenseFullfillAltStates = new WineLicenseFullfillAltStates();
			WineLicenseFullfillAltStates permitModelFromDB = WinePermitRepository.findFulfilModelByWineId(wineModel.getId());
			if(permitModelFromDB!=null)
			{
			    wineLicenseFullfillAltStates.setId(permitModelFromDB.getId());
			}
			if(fulFilModel != null)
			{

			    /*if(BooleanUtils.toBoolean(fulFilModel.getIsSelected())){*/

			    wineLicenseFullfillAltStates.setIsSelected(BooleanUtils.toBoolean(fulFilModel.getIsSelected()));
			    wineLicenseFullfillAltStates.setEscrowContract(BooleanUtils.toBoolean(fulFilModel.getEscrowContract()));
			    wineLicenseFullfillAltStates.setWaPlatformContract(BooleanUtils.toBoolean(fulFilModel.getWaPlatformContract()));
			    wineLicenseFullfillAltStates.setWineryStorageContract(BooleanUtils.toBoolean(fulFilModel.getIsStorageContact()));
			    wineLicenseFullfillAltStates.setWaWillNotFullFill(false);
			    /*}*/
			    wineLicenseFullfillAltStates.setWineId(wineModel);
			    WinePermitRepository.saveWineFulfilModel(wineLicenseFullfillAltStates);
			}
			else if(fulFilModel==null && BooleanUtils.toBoolean(optionSelectedAltStates.getFulfillDirectlyNotWA()))
			{

			    wineLicenseFullfillAltStates.setWaWillNotFullFill(true);
			    //wineryLicenseFullfillAltStates.setWineryId(wineryModel);
			    WinePermitRepository.saveWineFulfilModel(wineLicenseFullfillAltStates);
			}
		    }



		}

		if(sellInAltStatesModel.getOptionSelectedAltStates()==null)
		{

		    OptionSelectedAltStates optionSelectedAltStates = new OptionSelectedAltStates();
		    if(!BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedKachinaAlt()))
		    wineModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.NO_PERMIT_FOR_ALT_STATES.getOptionSelectedaltStates());
		    List<Object[]> permitIdsList = WinePermitRepository.findWinePermitIdByWineId(wineModel.getId());
		    for(Object[] ids:permitIdsList)
		    {
			WineLicensePermitAltStates wineLicensePermitAltStates = new WineLicensePermitAltStates();
			wineLicensePermitAltStates.setId((Long)ids[1]);
			wineLicensePermitAltStates.setWineId(wineModel);
			wineLicensePermitAltStates.setWineryPermit(MasterDataRepository.getMasterDataById((Long)ids[0]));
			WinePermitRepository.saveWineLicensePermitAltStates(wineLicensePermitAltStates);
		    }


		    WineLicenseFullfillAltStates wineLicenseFullfillAltStates = new WineLicenseFullfillAltStates();
		    WineLicenseFullfillAltStates permitModelFromDB = WinePermitRepository.findFulfilModelByWineId(wineModel.getId());
		    if(permitModelFromDB!=null)
		    {
			wineLicenseFullfillAltStates.setId(permitModelFromDB.getId());
		    }

		    wineLicenseFullfillAltStates.setWineId(wineModel);
		    WinePermitRepository.saveWineFulfilModel(wineLicenseFullfillAltStates);

		    populateNoPermitData(winePermitPO, wineModel);

		}





	    }
	    else
		wineModel.setSellInAltStates(false);
	    WineRepository.update(wineModel);
	    isSussess = true;
	}
	catch (Exception e) {
	    logger.error("some problem occured"+e);
	    throw new PersistenceException(e);
	}
	return isSussess;
    }

    private static void populateNoPermitData(WinePermitPO winePermitPO, WineModel wineModel) {
	Boolean optionSelectedNoPermit = BooleanUtils.toBooleanObject(winePermitPO.getSellInAltStates().getIsSelectedNoPermit());
	Map<Long, Long> noPermitIdsMap = new ConcurrentHashMap<Long, Long>();
	List<Object[]> noPermitIdsList = WinePermitRepository.findWineNoPermitIdByWineId(wineModel.getId());
	for(Object[] ids:noPermitIdsList)
	{
	    noPermitIdsMap.put((Long)ids[0],(Long)ids[1]);
	}

	List<OptionSelectedNoPermit> noPermitList = winePermitPO.getSellInAltStates().getOptionSelectedNoPermit();
	if(CollectionUtils.isNotEmpty(noPermitList))
	{


	    for(OptionSelectedNoPermit noPermitModel: noPermitList){
		WineLicenseNoPermit wineLicenseNoPermit = new WineLicenseNoPermit();
		Long masterDataId = Long.valueOf(noPermitModel.getMasterDataId());
		wineLicenseNoPermit.setWineNoPermit(MasterDataRepository.getMasterDataById(masterDataId));
		wineLicenseNoPermit.setProductId(wineModel);
		wineLicenseNoPermit.setId(noPermitIdsMap.get(masterDataId));
		if(BooleanUtils.isTrue(optionSelectedNoPermit)){
		    wineModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.NO_PERMIT_FOR_ALT_STATES.getOptionSelectedaltStates());
		    wineLicenseNoPermit.setIsSelected(BooleanUtils.toBoolean(noPermitModel.getIsSelected()));
		    wineLicenseNoPermit.setPriceFiled(noPermitModel.getNoPermitdate());
		    wineLicenseNoPermit.setStatus(noPermitModel.getSc3TStatus());
		}
		else
		{
		    wineLicenseNoPermit.setIsSelected(false);
		    wineLicenseNoPermit.setPriceFiled(null);
		    wineLicenseNoPermit.setStatus(null);
		}


		WinePermitRepository.saveWineLicenseNoPermit(wineLicenseNoPermit);

	    }
	}
    }

    /**
     * @param wineryPermitPO
     */
    private  void validateWinePermitPO(WinePermitPO wineryPermitPO) {
	response = new FailureResponse();

	Boolean isSellInMainStates = BooleanUtils.toBoolean(wineryPermitPO.getIsSellInMainStates());
	SellInAltStatesModel isSellInAltStates = wineryPermitPO.getSellInAltStates();

	if(BooleanUtils.isNotTrue(isSellInMainStates) && isSellInAltStates==null){
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR_WINE,SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR_WINE_TEXT));
	}
	if(BooleanUtils.isNotTrue(isSellInMainStates) && isSellInAltStates!=null && !BooleanUtils.toBoolean(isSellInAltStates.getIsSelected())){
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR_WINE,SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR_WINE_TEXT));
	}
	else if(isSellInAltStates!=null)
	{
	    validateSellInAltModel(isSellInAltStates,wineryPermitPO.getProductId());
	}


    }



    /**
     * @param sellInAltStatesModel
     * @param string 
     */
    private  void validateSellInAltModel(
	    SellInAltStatesModel sellInAltStatesModel, String wineId) {

	Boolean isOptionSelectedKachinaAlt = BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedKachinaAlt());
	OptionSelectedAltStates optionSelectedAltStates = sellInAltStatesModel.getOptionSelectedAltStates();
	Boolean isOptionSelectedNoPermit = BooleanUtils.toBoolean(sellInAltStatesModel.getIsSelectedNoPermit());
	Boolean isSelectedAltStates = BooleanUtils.toBoolean(sellInAltStatesModel.getIsSelected());

	if(BooleanUtils.isTrue(isSelectedAltStates) && BooleanUtils.isNotTrue(isOptionSelectedKachinaAlt) && optionSelectedAltStates==null && BooleanUtils.isNotTrue(isOptionSelectedNoPermit))
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_OPTION_SELECTED_WINE_LICENCES_ERROR_WINE,SystemErrorCode.PERMIT_NO_OPTION_SELECTED_WINE_LICENCES_ERROR_WINE_TEXT));
	}
	else if((BooleanUtils.isTrue(isOptionSelectedKachinaAlt) && optionSelectedAltStates!=null) ||
		BooleanUtils.isTrue(isOptionSelectedKachinaAlt) && BooleanUtils.isTrue(isOptionSelectedNoPermit)||
		BooleanUtils.isTrue(isOptionSelectedNoPermit) && optionSelectedAltStates!=null)
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_WINE,SystemErrorCode.PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_WINE_TEXT));
	}
	else 
	{
	    if(optionSelectedAltStates!=null) 
	    {
		validateoptionSelectedAltstates(optionSelectedAltStates,wineId);
	    }
	    else if(sellInAltStatesModel.getOptionSelectedNoPermit()!=null)
		validateoptionSelectedNoPermit(isOptionSelectedNoPermit,sellInAltStatesModel.getOptionSelectedNoPermit(),wineId);
	    else
	    {
		if(BooleanUtils.isTrue(isOptionSelectedNoPermit))
		    response.addError(new WineaccessError(SystemErrorCode.WINE_PERMIT_SELECT_VALUE_NO_PERMIT,SystemErrorCode.WINE_PERMIT_SELECT_VALUE_NO_PERMIT_TEXT));
	    }
	}

    }




    /**
     * @param optionSelectedAltStates
     * @param wineryId 
     */
    private static  void validateoptionSelectedAltstates(
	    OptionSelectedAltStates optionSelectedAltStates, String wineryId) {
	validatePermit(optionSelectedAltStates.getPermit(),wineryId);
	validateFulFil(optionSelectedAltStates.getFulfill());
    }

    /**
     * @param optionSelectedAltStates
     */
    private  static void validateFulFil(
	    FulFillModel fulFilModel) {
	//TODO Discussion pending from Vaibhav

    }

    /**
     * @param optionSelectedNoPermit
     * @param wineId
     */
    private void validateoptionSelectedNoPermit(Boolean isOptionSelectedNoPermit,List<OptionSelectedNoPermit> optionSelectedNoPermit, String wineId) {

	int numberOfItems = optionSelectedNoPermit.size();
	if(BooleanUtils.isTrue(isOptionSelectedNoPermit) && numberOfItems==0)
	    response.addError(new WineaccessError(SystemErrorCode.WINE_PERMIT_SELECT_VALUE_NO_PERMIT,SystemErrorCode.WINE_PERMIT_SELECT_VALUE_NO_PERMIT_TEXT));

	/*else if(BooleanUtils.isFalse(isOptionSelectedNoPermit) && numberOfItems!=0)
	    response.addError(new WineaccessError(SystemErrorCode.WINE_PERMIT_NOT_SELECT_VALUE_NO_PERMIT,SystemErrorCode.WINE_PERMIT_NOT_SELECT_VALUE_NO_PERMIT_TEXT));*/
	else
	{
	    for(OptionSelectedNoPermit noPermitObject:optionSelectedNoPermit )
	    {
		Long masterDataId = (noPermitObject.getMasterDataId()!=null)? Long.parseLong(noPermitObject.getMasterDataId()) : null ;
		if(masterDataId == null)
		{
		    response.addError(new WineaccessError(SystemErrorCode.NO_WINE_PERMITS_ERROR,SystemErrorCode.NO_WINE_PERMITS_ERROR_TEXT));

		}
		else{
		    MasterData masterData = MasterDataRepository.getMasterDataById(masterDataId);
		    if(masterData==null || !masterData.getMasterDataType().getName().equals(MasterDataTypeEnum.WineryLicenceNoPermit.name()))
		    {
			response.addError(new WineaccessError(SystemErrorCode.NO_PERMIT_INVALID_MASTER_DATA_WINE,SystemErrorCode.NO_PERMIT_INVALID_MASTER_DATA_WINE_TEXT));

		    }

		}
	    }
	}

    }


    private static void validatePermit(
	    List<PermitModel> permit, String wineryId) {
	Map<String,String> permitNumberPOMap = new ConcurrentHashMap<String,String>();
	Set<String> dtcPermitNumberSet = new HashSet<String>();
	/*Map<String,String> permitNumberDOMap = new ConcurrentHashMap<String,String>();
	Set<String> dtcPermitNumberSetDB = new HashSet<String>();*/
	if(permit==null || permit.isEmpty())
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_SELECTED_ALT_STATES_ERROR_WINE,SystemErrorCode.PERMIT_NO_SELECTED_ALT_STATES_ERROR_WINE_TEXT));
	}
	else {

	    Boolean isValidWineryPermit = false;
	    for(PermitModel permitModel:permit)
	    {
		String permitdurationInMonths = permitModel.getPermitDurationInMonths();
		Date permitStartDate = permitModel.getDtcPermitStartDate();
		Date permitEndDate = permitModel.getDtcPermitEndDate();
		String dtcPermitNumber = permitModel.getDtcPermitNumber();
		Long masterDataId = (permitModel.getMasterDataId()!=null)? Long.parseLong(permitModel.getMasterDataId()) : null ;
		if(masterDataId == null)
		{
		    response.addError(new WineaccessError(SystemErrorCode.NO_WINE_PERMITS_ERROR,SystemErrorCode.NO_WINE_PERMITS_ERROR_TEXT));

		}
		else{
		    MasterData masterData = MasterDataRepository.getMasterDataById(masterDataId);
		    if(masterData==null || !masterData.getMasterDataType().getName().equals(MasterDataTypeEnum.WineryLicencePermit.name()))
		    {
			response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_MASTER_DATA_WINE,SystemErrorCode.PERMIT_INVALID_MASTER_DATA_WINE_TEXT));

		    }

		}

		if(StringUtils.isNotBlank(dtcPermitNumber) && StringUtils.isNotBlank(permitdurationInMonths) 
			&& BooleanUtils.toBoolean(permitModel.getIsSelected()))
		{
		    isValidWineryPermit = true;

		}
		if(StringUtils.isNotBlank(permitdurationInMonths) && permitStartDate!=null && permitEndDate !=null)
		{
		    Integer diff = WineryPermitHelper.getMonthsDifference(permitStartDate,permitEndDate);
		    if(Integer.parseInt(permitdurationInMonths) != diff)
		    {
			response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_DURATION_WINE,SystemErrorCode.PERMIT_INVALID_DURATION_WINE_TEXT));

		    }
		}
		if(StringUtils.isNotBlank(dtcPermitNumber) && permitModel.getMasterDataId()!=null)
		{

		    permitNumberPOMap.put(permitModel.getMasterDataId(), dtcPermitNumber);
		    dtcPermitNumberSet.add(dtcPermitNumber);
		}

	    }
	    if(!permitNumberPOMap.isEmpty())
	    {
		//Set<String> dtcPermitNumberSet = permitNumberPOMap.

		if(dtcPermitNumberSet.size()!=permitNumberPOMap.size())
		{
		    response.addError(new WineaccessError(SystemErrorCode.PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_WINE,SystemErrorCode.PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_WINE_TEXT));
		}
		/*else{
		    List<Object[]> dtcPermitNumberFromDB = WineryPermitRepository.findDTCPermitNumberByWineryId(Long.valueOf(wineryId));
		    if(dtcPermitNumberFromDB!=null && !dtcPermitNumberFromDB.isEmpty()){
			for(Object[] obj:dtcPermitNumberFromDB){
			    permitNumberDOMap.put((String)obj[0], (String)obj[1]);
			    dtcPermitNumberSetDB.add((String)obj[1]);
			}
		    }


		    if(dtcPermitNumberSetDB!=null && !dtcPermitNumberSetDB.isEmpty()){
			for(String DTCPermitNumber:dtcPermitNumberSetDB)
			{
			    if(Collections.frequency(permitNumberPOMap, DTCPermitNumber)!=0)
			    {
				response.addError(new WineaccessError(SystemErrorCode.PERMIT_DUPLICATE_DTC_PERMIT_NUMBER,SystemErrorCode.PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_TEXT));
			    }
			}
		    }
		}*/
	    }



	    if(!isValidWineryPermit)
	    {
		response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_PERMIT_DURATION_WINE,SystemErrorCode.PERMIT_INVALID_PERMIT_DURATION_WINE_TEXT));
	    }
	}
    }


    /**
     * @param wineModel
     */
    private static void copyFulfilModelFromWinery(WineModel wineModel) {


	WineryLicenseFullfillAltStates wineryLicenseFullfillAltStates = WineryPermitRepository.findFulfilModelByWineryId(wineModel.getWineryId().getId());
	if(wineryLicenseFullfillAltStates!=null)
	{
	    WineLicenseFullfillAltStates wineLicenseFullfillAltStates = new WineLicenseFullfillAltStates();
	    try {
		ConvertUtils.register(new DateConverter(null), Date.class);
		BeanUtils.copyProperties(wineLicenseFullfillAltStates, wineryLicenseFullfillAltStates);
		wineLicenseFullfillAltStates.setWineId(wineModel);
		WinePermitRepository.saveWineFulfilModel(wineLicenseFullfillAltStates);
	    } catch (IllegalAccessException | InvocationTargetException e) {
		logger.info("Fulfill kodel couldn't be copied from winery");
		logger.error(e);
	    }
	}

    }

    /**
     * @param wineModel
     */
    private static void copyPermitModelFromWinery(WineModel wineModel) {
	List<WineryLicensePermitAltStates> wineryLicensePermitAltStates = WineryPermitRepository.findWineryLicensePermitAltStates(wineModel.getWineryId().getId());
	for(WineryLicensePermitAltStates wineryLicensePermit: wineryLicensePermitAltStates)
	{
	    WineLicensePermitAltStates wineLicensePermit = new WineLicensePermitAltStates();
	    try {
		ConvertUtils.register(new DateConverter(null), Date.class);
		BeanUtils.copyProperties(wineLicensePermit, wineryLicensePermit);
		wineLicensePermit.setWineId(wineModel);
	    } catch (IllegalAccessException | InvocationTargetException e) {
		logger.info("Fulfill kodel couldn't be copied from winery");
		logger.error(e);
	    }
	    WinePermitRepository.saveWineLicensePermitAltStates(wineLicensePermit);
	}

    }



}
