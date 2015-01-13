package com.wineaccess.winerypermit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.common.MasterDataModel;
import com.wineaccess.constants.EnumTypes;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.WineryLicenseFullfillAltStates;
import com.wineaccess.data.model.catalog.WineryLicensePermitAltStates;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.winery.WineryRepository;

/**
 * @author abhishek.sharma1
 *
 */
public class WineryPermitHelper {

    /**
     * @param winePermitPO
     * @return
     */
    private static Response response = new FailureResponse();
    private static Log logger = LogFactory.getLog(WineryPermitHelper.class);
    public  Map<String, Object> generateAddPermitResponse(WineryPermitPO wineryPermitPO)  throws PersistenceException {
	response = new FailureResponse();
	Map <String, Object> outputAddPermit = new HashMap<String,Object>();
	if(StringUtils.isNotBlank(wineryPermitPO.getWineryId())){
	    Long wineryId = Long.parseLong(wineryPermitPO.getWineryId());

	    WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
	    if(wineryModel == null)
	    {
		response.addError(new WineaccessError(SystemErrorCode.PERMIT_WINERY_ERROR,SystemErrorCode.PERMIT_WINERY_ERROR_TEXT));
	    }
	    else{
		validateWineryPermitPO(wineryPermitPO);

		if(response.getErrors().isEmpty()){
		    if(populateAndSaveDOFromPO(wineryPermitPO,wineryModel))					
			response = new com.wineaccess.response.SuccessResponse(new WineryPermitAddVO(wineryModel.getId(),"Permit created successfully"), 200);
		    else
			response.addError(new WineaccessError(SystemErrorCode.PERMIT_FAILURE_ERROR,SystemErrorCode.PERMIT_FAILURE_ERROR_TEXT));
		}
	    }
	}
	else
	    response.addError(new WineaccessError(SystemErrorCode.WINERY_PERMIT_ERROR_102,SystemErrorCode.WINERY_PERMIT_ERROR_102_TEXT));
	outputAddPermit.put("FINAL-RESPONSE", response);
	return outputAddPermit;
    }

    /**
     * @param winePermitViewPO
     * @return
     */
    public static Map<String, Object> generateViewPermitResponse(
	    WineryPermitViewPO wineryPermitViewPO) {
	response = new FailureResponse();
	Map <String, Object> outputViewPermit = new HashMap<String,Object>();
	Long wineryId = Long.parseLong(wineryPermitViewPO.getWineryId());
	WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
	if(wineryModel == null)
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_WINERY_ERROR,SystemErrorCode.PERMIT_WINERY_ERROR_TEXT));
	}
	else
	{
	    WineryPermitDetailVO wineryPermitViewVO = new WineryPermitDetailVO();

	    wineryPermitViewVO.setWineryId(wineryModel.getId());
	    wineryPermitViewVO.setIsSellInMainStates(wineryModel.getSellInMainStates());
	    /*if(BooleanUtils.isTrue(wineryModel.getSellInAltStates()))
			{*/

	    SellInAltStatesResultModel sellInAltStatesModel = new SellInAltStatesResultModel();
	    if(BooleanUtils.isTrue(wineryModel.getSellInAltStates()))
		sellInAltStatesModel.setIsSelected(true);


	    Integer optionSelected = wineryModel.getOptionSelectedAltstates();
	    if(optionSelected!=null && optionSelected.equals(0)){
		sellInAltStatesModel.setIsOptionSelectedKachinaAlt(true);

	    }

	    if(optionSelected!=null && optionSelected.equals(2)){
		sellInAltStatesModel.setOptionSelectedNoPermit(true);

	    }
	    /*else if(optionSelected.equals(1))
				{*/
	    List<PermitModelResult> permitModelResultsList = new ArrayList<PermitModelResult>();
	    OptionSelectedAltStatesResult optionSelectedAltStates = new OptionSelectedAltStatesResult();
	    List<WineryLicensePermitAltStates> wineryLicensePermitAltStates = WineryPermitRepository.findWineryLicensePermitAltStates(wineryId);

	    /*if(wineLicensePermitAltStates != null && !wineLicensePermitAltStates.isEmpty())
					{*/
	    //Set<MasterData> wineryPermitMasterDataList = MasterDataTypeAdapterHelper.getMasterDataListByMasterTypeName("Winery Licence Permit");





	    for(WineryLicensePermitAltStates permitModelFromDB: wineryLicensePermitAltStates)
	    {
		PermitModelResult permitModelResult = new PermitModelResult();
		WineryPermitModelWithMasterData wineryPermitModelWithMasterData = new WineryPermitModelWithMasterData();
		MasterDataModel masterDataModel = new MasterDataModel();
		Long masterDataForPermit = permitModelFromDB.getWineryPermit().getId();

		permitModelResult.setDtcPermitEndDate(permitModelFromDB.getDtcPermitEndDate());
		permitModelResult.setDtcPermitStartDate(permitModelFromDB.getDtcPermitStartDate());
		permitModelResult.setDtcPermitNumber(permitModelFromDB.getDtcPermitNumber());
		permitModelResult.setIsSelected(permitModelFromDB.getIsSelected());
		permitModelResult.setPermitDurationInMonths(permitModelFromDB.getDtcPermitDurationInMonths());

		masterDataModel.setId(masterDataForPermit);
		masterDataModel.setMasterDataTypeName("WineryLicencePermit");
		masterDataModel.setMasterDataName(permitModelFromDB.getWineryPermit().getName());
		wineryPermitModelWithMasterData.setMasterData(masterDataModel);
		//wineryPermitModelWithMasterData.setPermitModelResult(permitModelResult);

		permitModelResult.setWineryPermit(wineryPermitModelWithMasterData);
		permitModelResultsList.add(permitModelResult);

	    }

	    optionSelectedAltStates.setPermit(permitModelResultsList);
	    /*}*/
	    WineryLicenseFullfillAltStates fulfilModelFromDB = WineryPermitRepository.findFulfilModelByWineryId(wineryId);
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
	    /*else if()
				optionSelectedAltStates.setFulfillDirectlyNotWA(true);*/

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
	    sellInAltStatesModel.setOptionSelectedAltStates(optionSelectedAltStates);
	    Integer optionSelect = wineryModel.getOptionSelectedAltstates();
	    if(optionSelect!=null){
		if(0==optionSelect)
		{
		    sellInAltStatesModel.setIsOptionSelectedKachinaAlt(true);
		}
		else if (2==optionSelect)
		{
		    sellInAltStatesModel.setOptionSelectedNoPermit(true);
		}
	    }
	    wineryPermitViewVO.setSellInAltStates(sellInAltStatesModel);
	    /*}*/
	    response = new com.wineaccess.response.SuccessResponse(wineryPermitViewVO, 200);
	}

	outputViewPermit.put("FINAL-RESPONSE", response);
	return outputViewPermit;
    }



    /**
     * @param wineryPermitPO
     * @param wineryModel 
     * @return 
     * @throws Exception 
     */
    private static boolean populateAndSaveDOFromPO(WineryPermitPO wineryPermitPO, WineryModel wineryModel) throws PersistenceException {
	boolean isSussess = false;
	try{


	    wineryModel.setSellInMainStates(BooleanUtils.toBoolean(wineryPermitPO.getIsSellInMainStates()));
	    SellInAltStatesModel sellInAltStatesModel = wineryPermitPO.getSellInAltStates();

	    if(wineryPermitPO.getSellInAltStates()!=null){
		if(BooleanUtils.toBoolean(wineryPermitPO.getSellInAltStates().getIsSelected()))
		    wineryModel.setSellInAltStates(true);
		if(BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedKachinaAlt())){
		    wineryModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.KACHINA_ALT.getOptionSelectedaltStates());
		}
		if(sellInAltStatesModel.getOptionSelectedAltStates()!=null)
		{
		    wineryModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.PERMIT_FOR_ALT_STATES.getOptionSelectedaltStates());
		    OptionSelectedAltStates optionSelectedAltStates = sellInAltStatesModel.getOptionSelectedAltStates();
		    List<PermitModel> permitModelList = optionSelectedAltStates.getPermit();
		    if(permitModelList!=null && !permitModelList.isEmpty())
		    {
			Map<Long, Long> permitIdsMap = new ConcurrentHashMap<Long, Long>();
			List<Object[]> permitIdsList = WineryPermitRepository.findWineryPermitIdByWineryId(wineryModel.getId());
			for(Object[] ids:permitIdsList)
			{
			    permitIdsMap.put((Long)ids[0],(Long)ids[1]);
			}

			for(PermitModel permitModel: permitModelList){
			    Long masterDataId = Long.valueOf(permitModel.getMasterDataId());
			    /*if(BooleanUtils.toBoolean(permitModel.getIsSelected())){*/
			    WineryLicensePermitAltStates wineryLicensePermitAltStates = new WineryLicensePermitAltStates();
			    wineryLicensePermitAltStates.setIsSelected(BooleanUtils.toBoolean(permitModel.getIsSelected()));
			    if(!StringUtils.isEmpty(permitModel.getPermitDurationInMonths()))
				wineryLicensePermitAltStates.setDtcPermitDurationInMonths(Integer.valueOf(permitModel.getPermitDurationInMonths()));
			    if(!StringUtils.isEmpty(permitModel.getDtcPermitNumber()))
				wineryLicensePermitAltStates.setDtcPermitNumber(permitModel.getDtcPermitNumber());
			    wineryLicensePermitAltStates.setDtcPermitStartDate(permitModel.getDtcPermitStartDate());
			    wineryLicensePermitAltStates.setDtcPermitEndDate(permitModel.getDtcPermitEndDate());
			    /*	}*/
			    wineryLicensePermitAltStates.setWineryPermit(MasterDataRepository.getMasterDataById(masterDataId));
			    wineryLicensePermitAltStates.setWineryId(wineryModel);
			    wineryLicensePermitAltStates.setId(permitIdsMap.get(masterDataId));
			    WineryPermitRepository.saveWineryLicensePermitAltStates(wineryLicensePermitAltStates);

			}
			FulFillModel fulFilModel = optionSelectedAltStates.getFulfill();
			WineryLicenseFullfillAltStates wineryLicenseFullfillAltStates = new WineryLicenseFullfillAltStates();
			WineryLicenseFullfillAltStates permitModelFromDB = WineryPermitRepository.findFulfilModelByWineryId(wineryModel.getId());
			if(permitModelFromDB!=null)
			{
			    wineryLicenseFullfillAltStates.setId(permitModelFromDB.getId());
			}
			if(fulFilModel != null)
			{

			    /*if(BooleanUtils.toBoolean(fulFilModel.getIsSelected())){*/

			    wineryLicenseFullfillAltStates.setIsSelected(BooleanUtils.toBoolean(fulFilModel.getIsSelected()));
			    wineryLicenseFullfillAltStates.setEscrowContract(BooleanUtils.toBoolean(fulFilModel.getEscrowContract()));
			    wineryLicenseFullfillAltStates.setWaPlatformContract(BooleanUtils.toBoolean(fulFilModel.getWaPlatformContract()));
			    wineryLicenseFullfillAltStates.setWineryStorageContract(BooleanUtils.toBoolean(fulFilModel.getIsStorageContact()));
			    wineryLicenseFullfillAltStates.setWaWillNotFullFill(false);
			    /*}*/
			    wineryLicenseFullfillAltStates.setWineryId(wineryModel);
			    WineryPermitRepository.saveWineryFulfilModel(wineryLicenseFullfillAltStates);
			}
			else if(fulFilModel==null && BooleanUtils.toBoolean(optionSelectedAltStates.getFulfillDirectlyNotWA()))
			{

			    wineryLicenseFullfillAltStates.setWaWillNotFullFill(true);
			    //wineryLicenseFullfillAltStates.setWineryId(wineryModel);
			    WineryPermitRepository.saveWineryFulfilModel(wineryLicenseFullfillAltStates);
			}
		    }

		}

		if(sellInAltStatesModel.getOptionSelectedAltStates()==null)
		{

		    OptionSelectedAltStates optionSelectedAltStates = new OptionSelectedAltStates();

		    List<Object[]> permitIdsList = WineryPermitRepository.findWineryPermitIdByWineryId(wineryModel.getId());
		    for(Object[] ids:permitIdsList)
		    {
			WineryLicensePermitAltStates wineryLicensePermitAltStates = new WineryLicensePermitAltStates();
			wineryLicensePermitAltStates.setId((Long)ids[1]);
			wineryLicensePermitAltStates.setWineryId(wineryModel);
			wineryLicensePermitAltStates.setWineryPermit(MasterDataRepository.getMasterDataById((Long)ids[0]));
			WineryPermitRepository.saveWineryLicensePermitAltStates(wineryLicensePermitAltStates);
		    }


		    WineryLicenseFullfillAltStates wineryLicenseFullfillAltStates = new WineryLicenseFullfillAltStates();
		    WineryLicenseFullfillAltStates permitModelFromDB = WineryPermitRepository.findFulfilModelByWineryId(wineryModel.getId());
		    if(permitModelFromDB!=null)
		    {
			wineryLicenseFullfillAltStates.setId(permitModelFromDB.getId());
		    }

		    wineryLicenseFullfillAltStates.setWineryId(wineryModel);
		    WineryPermitRepository.saveWineryFulfilModel(wineryLicenseFullfillAltStates);

		}

		if(BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedNoPermit()))
		{
		    wineryModel.setOptionSelectedAltstates(EnumTypes.OptionSelectedAltStatesEnum.NO_PERMIT_FOR_ALT_STATES.getOptionSelectedaltStates());

		}
	    }
	    else
		wineryModel.setSellInAltStates(false);
	    WineryRepository.update(wineryModel);
	    isSussess = true;
	}
	catch (Exception e) {
	    logger.error("some problem occured"+e);
	    throw new PersistenceException(e);
	}
	return isSussess;
    }

    /**
     * @param wineryPermitPO
     */
    private  void validateWineryPermitPO(WineryPermitPO wineryPermitPO) {
	response = new FailureResponse();

	Boolean isSellInMainStates = BooleanUtils.toBoolean(wineryPermitPO.getIsSellInMainStates());
	SellInAltStatesModel isSellInAltStates = wineryPermitPO.getSellInAltStates();

	if(BooleanUtils.isNotTrue(isSellInMainStates) && isSellInAltStates==null){
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR,SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR_TEXT));
	}
	if(BooleanUtils.isNotTrue(isSellInMainStates) && isSellInAltStates!=null && !BooleanUtils.toBoolean(isSellInAltStates.getIsSelected())){
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR,SystemErrorCode.PERMIT_NO_OPTION_SELECTED_ERROR_TEXT));
	}
	else if(isSellInAltStates!=null)
	{
	    validateSellInAltModel(isSellInAltStates,wineryPermitPO.getWineryId());
	}


    }



    /**
     * @param sellInAltStatesModel
     * @param string 
     */
    private  void validateSellInAltModel(
	    SellInAltStatesModel sellInAltStatesModel, String wineryId) {

	Boolean isOptionSelectedKachinaAlt = BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedKachinaAlt());
	OptionSelectedAltStates optionSelectedAltStates = sellInAltStatesModel.getOptionSelectedAltStates();
	Boolean isOptionSelectedNoPermit = BooleanUtils.toBoolean(sellInAltStatesModel.getIsOptionSelectedNoPermit());
	Boolean isSelectedAltStates = BooleanUtils.toBoolean(sellInAltStatesModel.getIsSelected());

	if(BooleanUtils.isTrue(isSelectedAltStates) && BooleanUtils.isNotTrue(isOptionSelectedKachinaAlt) && optionSelectedAltStates==null && BooleanUtils.isNotTrue(isOptionSelectedNoPermit))
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_OPTION_SELECTED_WINERY_LICENCES_ERROR,SystemErrorCode.PERMIT_NO_OPTION_SELECTED_WINERY_LICENCES_ERROR_TEXT));
	}
	else if((BooleanUtils.isTrue(isOptionSelectedKachinaAlt) && optionSelectedAltStates!=null) ||
		BooleanUtils.isTrue(isOptionSelectedKachinaAlt) && BooleanUtils.isTrue(isOptionSelectedNoPermit)||
		BooleanUtils.isTrue(isOptionSelectedNoPermit) && optionSelectedAltStates!=null)
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR,SystemErrorCode.PERMIT_MORE_THAN_ONE_OPTION_SELECTED_ERROR_TEXT));
	}
	else 
	{
	    if(optionSelectedAltStates!=null) 
	    {
		validateoptionSelectedAltstates(optionSelectedAltStates,wineryId);
	    }

	}

    }

    /**
     * @param optionSelectedNoPermitList
     */
    /*	private static void validateNoPermitList(
			List<OptionSelectedNoPermit> noPermitList) {
		for(OptionSelectedNoPermit noPermitObject:noPermitList )
		{
			Long masterDataId = (noPermitObject.getMasterDataId()!=null)? Long.parseLong(noPermitObject.getMasterDataId()) : null ;
			if(masterDataId == null)
			{
				response.addError(new WineaccessError(SystemErrorCode.NO_WINERY_PERMITS_ERROR,SystemErrorCode.NO_WINERY_PERMITS_ERROR_TEXT));

			}
			else if(MasterDataRepository.getMasterDataById(masterDataId)==null)
			{
				response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_MASTER_DATA,SystemErrorCode.PERMIT_INVALID_MASTER_DATA_TEXT));

			}
		}

	}*/

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

    private static void validatePermit(
	    List<PermitModel> permit, String wineryId) {
	Map<String,String> permitNumberPOMap = new ConcurrentHashMap<String,String>();
	Set<String> dtcPermitNumberSet = new HashSet<String>();
	/*Map<String,String> permitNumberDOMap = new ConcurrentHashMap<String,String>();
	Set<String> dtcPermitNumberSetDB = new HashSet<String>();*/
	if(permit==null || permit.isEmpty())
	{
	    response.addError(new WineaccessError(SystemErrorCode.PERMIT_NO_SELECTED_ALT_STATES_ERROR,SystemErrorCode.PERMIT_NO_SELECTED_ALT_STATES_ERROR_TEXT));
	}
	else {

	    Boolean isValidWineryPermit = false;
	    for(PermitModel permitModel:permit)
	    {
		String permitdurationInMonths = permitModel.getPermitDurationInMonths();
		Date permitStartDate = permitModel.getDtcPermitStartDate();
		Date permitEndDate = permitModel.getDtcPermitEndDate();
		String dtcPermitNumber = permitModel.getDtcPermitNumber();
		Long masterDataId = (StringUtils.isNotBlank(permitModel.getMasterDataId()))? Long.parseLong(permitModel.getMasterDataId()) : null ;
		if(masterDataId == null)
		{
		    response.addError(new WineaccessError(SystemErrorCode.NO_WINERY_PERMITS_ERROR,SystemErrorCode.NO_WINERY_PERMITS_ERROR_TEXT));

		}
		else{
		    MasterData masterData = MasterDataRepository.getMasterDataById(masterDataId);
		    if(masterData==null || !masterData.getMasterDataType().getName().equals(MasterDataTypeEnum.WineryLicencePermit.name()))
		    {
			response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_MASTER_DATA,SystemErrorCode.PERMIT_INVALID_MASTER_DATA_TEXT));

		    }

		}

		if(StringUtils.isNotBlank(dtcPermitNumber) && StringUtils.isNotBlank(permitdurationInMonths) 
			&& BooleanUtils.toBoolean(permitModel.getIsSelected()))
		{
		    isValidWineryPermit = true;

		}
		if(StringUtils.isNotBlank(permitdurationInMonths) && permitStartDate!=null && permitEndDate !=null)
		{
		    Integer diff = getMonthsDifference(permitStartDate,permitEndDate);
		    if(Integer.parseInt(permitdurationInMonths) != diff)
		    {
			response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_DURATION,SystemErrorCode.PERMIT_INVALID_DURATION_TEXT));

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
		    response.addError(new WineaccessError(SystemErrorCode.PERMIT_DUPLICATE_DTC_PERMIT_NUMBER,SystemErrorCode.PERMIT_DUPLICATE_DTC_PERMIT_NUMBER_TEXT));
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
		response.addError(new WineaccessError(SystemErrorCode.PERMIT_INVALID_PERMIT_DURATION,SystemErrorCode.PERMIT_INVALID_PERMIT_DURATION_TEXT));
	    }
	}
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthsDifference(Date startDate, Date endDate) {

	Calendar startCalendar = new GregorianCalendar();
	startCalendar.setTime(startDate);
	Calendar endCalendar = new GregorianCalendar();
	endCalendar.setTime(endDate);

	int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
	BigDecimal temp = new BigDecimal(endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
	temp = temp.divide(new BigDecimal(12),2,RoundingMode.HALF_UP);
	int monthDiff = diffYear + temp.setScale(0, RoundingMode.HALF_UP).intValue();
	
	

	return monthDiff*12;
    }


}
