package com.wineaccess.wineOWS;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.WineryHistoricalOwsData;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineHistoricalOWSData;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.repositories.WineOwsDataRepository;
import com.wineaccess.data.model.repositories.WineryOwsDataRepository;
import com.wineaccess.response.Response;
import com.wineaccess.wine.WineAdapterHelper;
import com.wineaccess.wine.WineRepository;

/**
 * @author gaurav.agarwal1
 *
 */
public class WineOwsAdapterHelper {
	
	private static Log logger = LogFactory.getLog(WineAdapterHelper.class);

	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";
	
	/**
	 * this method is to view the wine historical ows data based on wineId
	 * if no wine historical ows data exists then copy winery ows data to wine ows data
	 * @param viewWineOwsPO
	 * return map the output map
	 */
	public static Map<String, Object> viewWineOwsData(final ViewWineOwsPO viewWineOwsPO) {
		logger.info("start view wine historical Ows data");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
	    final ProductItemModel productModel = ProductItemRepository.getProductItemById(Long.parseLong(viewWineOwsPO.getProductId()));
	    if(productModel == null){
	    	response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_104,
					WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_104_TEXT,SUCCESS_CODE);
	    } else {
	    	final WineHistoricalOWSData historicalOwsData =  WineOwsDataRepository.getOwsDataByWineId(productModel.getItemId());
			if(historicalOwsData != null){
				
				ViewWineOwsVO viewWineOwsVO = new ViewWineOwsVO();
				final WineModel wineModel = WineRepository.getWineById(historicalOwsData.getWineId());
				populateViewOwsVOFromPO(wineModel, historicalOwsData,viewWineOwsVO, productModel);
				
				response = new com.wineaccess.response.SuccessResponse(viewWineOwsVO, SUCCESS_CODE);

			}else if(historicalOwsData == null){
				
				final WineModel wineModel = WineRepository.getWineById(productModel.getItemId());
				if(wineModel != null){
					WineryHistoricalOwsData wineryHistoricalOwsData = null;
					if(wineModel.getWineryId() != null){
						wineryHistoricalOwsData = WineryOwsDataRepository.getOwsDataByWineryId(wineModel.getWineryId().getId());
					}
					if(wineryHistoricalOwsData != null){
						//Copy the winery historical data to wine historical data
						WineHistoricalOWSData wineHistoricalOWSData = new WineHistoricalOWSData();
						wineHistoricalOWSData.setCeraCertEndDate(wineryHistoricalOwsData.getCeraCertEndDate());
						wineHistoricalOWSData.setCeraCertNumber(wineryHistoricalOwsData.getCeraCertNumber());
						wineHistoricalOWSData.setCeraCertStartDate(wineryHistoricalOwsData.getCeraCertStartDate());
						wineHistoricalOWSData.setCeraLicenseNumber(wineryHistoricalOwsData.getCeraLicenseNumber());
						wineHistoricalOWSData.setFullFillerWineryCode(wineryHistoricalOwsData.getFullFillerWineryCode());
						wineHistoricalOWSData.setFullFillerWineryName(wineryHistoricalOwsData.getFullFillerWineryName());
						wineHistoricalOWSData.setWineId(productModel.getItemId());
						
						WineOwsDataRepository.save(wineHistoricalOWSData);
						
						ViewWineOwsVO viewWineOwsVO = new ViewWineOwsVO();
						populateViewOwsVOFromPO(wineModel, wineHistoricalOWSData,viewWineOwsVO, productModel);
						
						response = new com.wineaccess.response.SuccessResponse(viewWineOwsVO, SUCCESS_CODE);
					}else{
						response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.NO_ENTITY_FOUND,
								WineaccessErrorCodes.SystemErrorCode.NO_ENTITY_FOUND_TEXT,SUCCESS_CODE);
					}
				}else{
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_103,
							WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_103_TEXT,SUCCESS_CODE);
				}
			}
	    }
	    
		
		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit view winery historical Ows data");

		return output;
	}

	/**
	 * @param wineModel
	 * @param wineHistoricalOWSData
	 * @param viewWineOwsVO
	 */
	private static void populateViewOwsVOFromPO(WineModel wineModel,WineHistoricalOWSData wineHistoricalOWSData,final ViewWineOwsVO viewWineOwsVO, ProductItemModel productModel) {
		
		viewWineOwsVO.setId(wineHistoricalOWSData.getId());
		viewWineOwsVO.setWineId(wineHistoricalOWSData.getWineId());
		if(wineModel != null){
			viewWineOwsVO.setWineName(wineModel.getWineName());
		}
		viewWineOwsVO.setCeraCertEndDate(wineHistoricalOWSData.getCeraCertEndDate());
		viewWineOwsVO.setCeraCertNumber(wineHistoricalOWSData.getCeraCertNumber());
		viewWineOwsVO.setCeraCertStartDate(wineHistoricalOWSData.getCeraCertStartDate());
		viewWineOwsVO.setCeraLicenseNumber(wineHistoricalOWSData.getCeraLicenseNumber());
		viewWineOwsVO.setFullFillerWineryCode(wineHistoricalOWSData.getFullFillerWineryCode());
		viewWineOwsVO.setFullFillerWineryName(wineHistoricalOWSData.getFullFillerWineryName());
		viewWineOwsVO.setProductId(productModel.getId());
	}
	
	/**
	 * this method is to update the wine historical ows data based on wineId
	 * @param updateWineOwsPO
	 * return map the output map
	 */
	public static Map<String, Object> updateWineOwsData(final UpdateWineOwsPO updateWineOwsPO) {
		logger.info("start update wine historical Ows data");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		final ProductItemModel productModel = ProductItemRepository.getProductItemById(Long.parseLong(updateWineOwsPO.getProductId()));
		if(productModel == null){
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_104,
					WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_104_TEXT,SUCCESS_CODE);
		} else{
			final WineHistoricalOWSData historicalOwsData =  WineOwsDataRepository.getOwsDataByWineId(productModel.getItemId());
			if(historicalOwsData != null){
				if(updateWineOwsPO.getCeraLicenseNumber() != null){
					historicalOwsData.setCeraLicenseNumber(updateWineOwsPO.getCeraLicenseNumber());
				}
				if(updateWineOwsPO.getCeraCertNumber() != null){
					historicalOwsData.setCeraCertNumber(updateWineOwsPO.getCeraCertNumber());
				}
				if(updateWineOwsPO.getCeraCertStartDate() != null){
					historicalOwsData.setCeraCertStartDate(updateWineOwsPO.getCeraCertStartDate());
				}
				if(updateWineOwsPO.getCeraCertEndDate() != null){
					historicalOwsData.setCeraCertEndDate(updateWineOwsPO.getCeraCertEndDate());
				}
				if(updateWineOwsPO.getFullFillerWineryName() != null){
					historicalOwsData.setFullFillerWineryName(updateWineOwsPO.getFullFillerWineryName());
				}
				
				if(updateWineOwsPO.getFullFillerWineryCode() != null){
					historicalOwsData.setFullFillerWineryCode(updateWineOwsPO.getFullFillerWineryCode());
				}
				
				
				
				
				WineOwsDataRepository.update(historicalOwsData);
				
				UpdateWineOwsVO updateWineOwsVO = new UpdateWineOwsVO(SystemErrorCode.WINE_OWS_UPDATE_SUCCESS_TEXT);
				updateWineOwsVO.setId(historicalOwsData.getId());
				updateWineOwsVO.setWineId(historicalOwsData.getWineId());
				updateWineOwsVO.setProductId(productModel.getId());
				
				response = new com.wineaccess.response.SuccessResponse(updateWineOwsVO, SUCCESS_CODE);
			}else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_103,
						WineaccessErrorCodes.SystemErrorCode.WINE_OWS_ERROR_103_TEXT,SUCCESS_CODE);
			}
		}
		
		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit update winery historical Ows data");

		return output;
	}

}
