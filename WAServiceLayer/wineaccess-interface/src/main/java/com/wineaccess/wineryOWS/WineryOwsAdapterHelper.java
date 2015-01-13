package com.wineaccess.wineryOWS;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.WineryHistoricalOwsData;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.repositories.WineryOwsDataRepository;
import com.wineaccess.response.Response;
import com.wineaccess.wine.WineAdapterHelper;
import com.wineaccess.winery.WineryRepository;

/**
 * @author gaurav.agarwal1
 * 
 */
public class WineryOwsAdapterHelper {

	private static Log logger = LogFactory.getLog(WineAdapterHelper.class);

	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";

	/**
	 * this method is to create the winery historical ows data
	 * @param addWineryOwsPO
	 * return map the output map
	 */
	public static Map<String, Object> addUpdateWineryOwsData(final AddUpdateWineryOwsPO addUpdateWineryOwsPO) {

		logger.info("start add/update winery historical Ows data");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		final WineryHistoricalOwsData historicalOwsData =  WineryOwsDataRepository.getOwsDataByWineryId(Long.parseLong(addUpdateWineryOwsPO.getWineryid()));
		if(historicalOwsData != null){
			
			if(isValidDates(addUpdateWineryOwsPO)){
				populateOwsDataModelFromPO(addUpdateWineryOwsPO,historicalOwsData);
				WineryOwsDataRepository.update(historicalOwsData);
				
				AddUpdateWineryOwsVO addUpdateWineryOwsVO = new AddUpdateWineryOwsVO(SystemErrorCode.WINERY_OWS_UPDATE_SUCCESS_TEXT);
				addUpdateWineryOwsVO.setId(historicalOwsData.getId());
				addUpdateWineryOwsVO.setWineryId(historicalOwsData.getWineryid());	
				response = new com.wineaccess.response.SuccessResponse(addUpdateWineryOwsVO, SUCCESS_CODE);
			} else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINERY_DATE_ERROR,
						WineaccessErrorCodes.SystemErrorCode.WINERY_DATE_ERROR_TEXT,SUCCESS_CODE);
			}
			
		} else {
			
			if(isValidDates(addUpdateWineryOwsPO)){
				final WineryModel wineryModel = WineryRepository.getWineryById(Long.parseLong(addUpdateWineryOwsPO.getWineryid()));
				if (wineryModel != null) {
						try {
							WineryHistoricalOwsData wineryHistoricalOwsData = new WineryHistoricalOwsData();
							populateOwsDataModelFromPO(addUpdateWineryOwsPO,wineryHistoricalOwsData);
							WineryOwsDataRepository.save(wineryHistoricalOwsData);
							
							AddUpdateWineryOwsVO addWineryOwsVO = new AddUpdateWineryOwsVO(SystemErrorCode.WINERY_OWS_ADD_SUCCESS_TEXT);
							addWineryOwsVO.setId(wineryHistoricalOwsData.getId());
							addWineryOwsVO.setWineryId(wineryHistoricalOwsData.getWineryid());
							response = new com.wineaccess.response.SuccessResponse(addWineryOwsVO, SUCCESS_CODE);

						} catch (Exception e) {
							logger.error("Some error occured "+e.getMessage(),e);
						} 
				}else{
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINERY_NOT_FOUND_OWS,
							WineaccessErrorCodes.SystemErrorCode.WINERY_NOT_FOUND_OWS_TEXT,SUCCESS_CODE);
				}	
			} else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINERY_DATE_ERROR,
						WineaccessErrorCodes.SystemErrorCode.WINERY_DATE_ERROR_TEXT,SUCCESS_CODE);
			}
			
		}
		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit add/update winery historical Ows data");

		return output;
	}

	/**
	 * @param addWineryOwsPO
	 * @param wineryHistoricalOwsData
	 */
	private static void populateOwsDataModelFromPO(final AddUpdateWineryOwsPO addWineryOwsPO,WineryHistoricalOwsData wineryHistoricalOwsData) {
		if(addWineryOwsPO.getWineryid() != null){
			wineryHistoricalOwsData.setWineryid(Long.parseLong(addWineryOwsPO.getWineryid()));
		}
		if(addWineryOwsPO.getCeraLicenseNumber() != null){
			wineryHistoricalOwsData.setCeraLicenseNumber(addWineryOwsPO.getCeraLicenseNumber());
		}
		if(addWineryOwsPO.getCeraCertNumber() != null){
			wineryHistoricalOwsData.setCeraCertNumber(addWineryOwsPO.getCeraCertNumber());
		}
		if(addWineryOwsPO.getCeraCertStartDate() != null){
			wineryHistoricalOwsData.setCeraCertStartDate(addWineryOwsPO.getCeraCertStartDate());
		}
		if(addWineryOwsPO.getCeraCertEndDate() != null){
			wineryHistoricalOwsData.setCeraCertEndDate(addWineryOwsPO.getCeraCertEndDate());
		}
		if(addWineryOwsPO.getFullFillerWineryName() != null){
			wineryHistoricalOwsData.setFullFillerWineryName(addWineryOwsPO.getFullFillerWineryName());
		}
		if(addWineryOwsPO.getFullFillerWineryCode() != null){
			wineryHistoricalOwsData.setFullFillerWineryCode(addWineryOwsPO.getFullFillerWineryCode());
		}
		
	}
	
	/**
	 * this method is to view the winery historical ows data based on wineryId
	 * @param updateWineryOwsPO
	 * return map the output map
	 */
	public static Map<String, Object> viewWineryOwsData(final ViewWineryOwsPO viewWineryOwsPO) {
		logger.info("start view winery historical Ows data");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		final WineryHistoricalOwsData historicalOwsData =  WineryOwsDataRepository.getOwsDataByWineryId(Long.parseLong(viewWineryOwsPO.getWineryid()));
		if(historicalOwsData != null){
			
			ViewWineryOwsVO viewWineryOwsVO = new ViewWineryOwsVO();
			viewWineryOwsVO.setId(historicalOwsData.getId());
			viewWineryOwsVO.setWineryId(historicalOwsData.getWineryid());
			
			WineryModel wineryModel = WineryRepository.getWineryById(historicalOwsData.getWineryid());
			if(wineryModel != null){
				viewWineryOwsVO.setWineryName(wineryModel.getWineryName());
			}
			
			viewWineryOwsVO.setCeraCertNumber(historicalOwsData.getCeraCertNumber());
			viewWineryOwsVO.setCeraLicenseNumber(historicalOwsData.getCeraLicenseNumber());
			viewWineryOwsVO.setCeraCertStartDate(historicalOwsData.getCeraCertStartDate());
			viewWineryOwsVO.setCeraCertEndDate(historicalOwsData.getCeraCertEndDate());
			viewWineryOwsVO.setFullFillerWineryCode(historicalOwsData.getFullFillerWineryCode());
			viewWineryOwsVO.setFullFillerWineryName(historicalOwsData.getFullFillerWineryName());
			response = new com.wineaccess.response.SuccessResponse(viewWineryOwsVO, SUCCESS_CODE);
		}else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NO_ENTITY_FOUND,
					WineaccessErrorCodes.SystemErrorCode.NO_ENTITY_FOUND_TEXT,SUCCESS_CODE);
		}
		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit view winery historical Ows data");

		return output;
	}
	
	private static Boolean isValidDates(AddUpdateWineryOwsPO addUpdateWineryOwsPO){
		Boolean isValid = true;
		if(null != addUpdateWineryOwsPO.getCeraCertStartDate() && null != addUpdateWineryOwsPO.getCeraCertEndDate()){
			if((addUpdateWineryOwsPO.getCeraCertStartDate()).after(addUpdateWineryOwsPO.getCeraCertEndDate())){
				isValid = false;
        	}
		}
		
		return isValid;
	}

}
