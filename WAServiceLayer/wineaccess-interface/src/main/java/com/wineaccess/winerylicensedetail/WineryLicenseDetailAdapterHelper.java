package com.wineaccess.winerylicensedetail;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.WineryLicenseDetailModel;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.response.Response;
import com.wineaccess.winery.WineryRepository;

/**
 * This Class is used to perform the edit and view WineryLicenseDetail operation
 * 
 * @author rohit.lohiya
 * 
 */
public class WineryLicenseDetailAdapterHelper {

	// purpose of this logger variable is to log the error messages in log file.
	private static Log logger = LogFactory
			.getLog(WineryLicenseDetailAdapterHelper.class);

	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";

	/**
	 * This method is used to update the WineryLicenseDetail in the database.
	 * 
	 * @param wineryLicenseDetailUpdatePO
	 *            is used to take the input in this PO.
	 * @return output map containing response
	 */
	public static Map<String, Object> updateWineryLicenseDetail(
			final WineryLicenseDetailUpdatePO wineryLicenseDetailUpdatePO) {

		logger.info("start updateWineryLicenseDetail method");

		String errorMsg = StringUtils.EMPTY;

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

		Response response = null;

		try {

			MasterData caLicenseType = null;
			WineryModel winery = null;
			WineryLicenseDetailModel wineryLicenseDetailModel = null;

			if ((wineryLicenseDetailUpdatePO.getCaLicenseTypeId() != null) && !(wineryLicenseDetailUpdatePO.getCaLicenseTypeId().isEmpty())) {
				caLicenseType = MasterDataRepository.getMasterDataById(Long
						.parseLong(wineryLicenseDetailUpdatePO
								.getCaLicenseTypeId()));
				if (caLicenseType == null) {
					// caLicenseType not exist
					response = ApplicationUtils
							.errorMessageGenerate(

									SystemErrorCode.UPDATE_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE,
									SystemErrorCode.UPDATE_WINERY_LICENSE_INVALID_CA_LICENSE_TYPE_TEXT,
									SUCCESS_CODE);

					logger.error("CA License Type not exist");
				}
			}

			if (response == null
					&& wineryLicenseDetailUpdatePO.getWineryId() != null) {
				winery = WineryRepository.getWineryById(Long
						.parseLong(wineryLicenseDetailUpdatePO.getWineryId()));
				if (winery == null) {
					// winery not exist
					response = ApplicationUtils
							.errorMessageGenerate(

									SystemErrorCode.UPDATE_WINERY_LICENSE_INVALID_WINERY,
									SystemErrorCode.UPDATE_WINERY_LICENSE_INVALID_WINERY_TEXT,
									SUCCESS_CODE);

					logger.error("winery not exist");
				}
			}

			if (response == null) {

				wineryLicenseDetailModel = WineryLicenseDetailRepository
						.getWineryLicenseDetailByWinery(winery);

				if (wineryLicenseDetailModel == null) {

					wineryLicenseDetailModel = new WineryLicenseDetailModel();

					
						wineryLicenseDetailModel
								.setCaLicenseType(caLicenseType);
					

					wineryLicenseDetailModel.setWinery(winery);

					if (wineryLicenseDetailUpdatePO.getContractExecuted() != null) {
						wineryLicenseDetailModel.setContractExecuted(Boolean
								.parseBoolean(wineryLicenseDetailUpdatePO
										.getContractExecuted()));
					} else {
						wineryLicenseDetailModel.setContractExecuted(false);
					}

					if (wineryLicenseDetailUpdatePO.getShipCompliant() != null) {
						wineryLicenseDetailModel.setShipCompliant(Boolean
								.parseBoolean(wineryLicenseDetailUpdatePO
										.getShipCompliant()));
					} else {
						wineryLicenseDetailModel.setShipCompliant(false);
					}

					if (wineryLicenseDetailUpdatePO.getShipEscrowNo() != null) {
						wineryLicenseDetailModel
								.setShipEscrowNo(wineryLicenseDetailUpdatePO
										.getShipEscrowNo());
					}

					WineryLicenseDetailRepository
							.save(wineryLicenseDetailModel);

					WineryLicenseDetailVO wineryLicenseDetailVO = new WineryLicenseDetailVO(
							SystemErrorCode.UPDATE_WINERY_LICENSE_SUCCESS_TEXT);

					BeanUtils.copyProperties(wineryLicenseDetailVO,
							wineryLicenseDetailModel);

					wineryLicenseDetailVO.setWineryId(wineryLicenseDetailModel
							.getWinery().getId());

					response = new com.wineaccess.response.SuccessResponse(
							wineryLicenseDetailVO, SUCCESS_CODE);
				}
			}

			if (response == null) {

				//if (caLicenseType != null) {
					wineryLicenseDetailModel.setCaLicenseType(caLicenseType);
				//}

				wineryLicenseDetailModel.setWinery(winery);

				if (wineryLicenseDetailUpdatePO.getContractExecuted() != null) {
					wineryLicenseDetailModel.setContractExecuted(Boolean
							.parseBoolean(wineryLicenseDetailUpdatePO
									.getContractExecuted()));
				}

				if (wineryLicenseDetailUpdatePO.getShipCompliant() != null) {
					wineryLicenseDetailModel.setShipCompliant(Boolean
							.parseBoolean(wineryLicenseDetailUpdatePO
									.getShipCompliant()));
				}

				if (wineryLicenseDetailUpdatePO.getShipEscrowNo() != null) {
					wineryLicenseDetailModel
							.setShipEscrowNo(wineryLicenseDetailUpdatePO
									.getShipEscrowNo());
				}

				WineryLicenseDetailRepository.update(wineryLicenseDetailModel);

				WineryLicenseDetailVO wineryLicenseDetailVO = new WineryLicenseDetailVO(
						SystemErrorCode.UPDATE_WINERY_LICENSE_SUCCESS_TEXT);

				BeanUtils.copyProperties(wineryLicenseDetailVO,
						wineryLicenseDetailModel);

				wineryLicenseDetailVO.setWineryId(wineryLicenseDetailModel
						.getWinery().getId());

				response = new com.wineaccess.response.SuccessResponse(
						wineryLicenseDetailVO, SUCCESS_CODE);
			}

		} catch (Exception e) {
			errorMsg = e.getCause().getMessage();
		}

		if (errorMsg.contains("uk_winery_id")) {
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.UPDATE_WINERY_LICENSE_ALREADY_EXISTS,
					SystemErrorCode.UPDATE_WINERY_LICENSE_ALREADY_EXISTS_TEXT,
					SUCCESS_CODE);

			logger.error("winery license detail already exists");
		}

		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit updateWineryLicenseDetail method");

		return output;
	}

	/**
	 * This method is used to view the detail of winery license
	 * 
	 * @param wineryLicenseDetailViewPO
	 *            is used to take input in this PO
	 * @return output map containing response
	 */
	public static Map<String, Object> viewWineryLicenseDetail(
			final WineryLicenseDetailViewPO wineryLicenseDetailViewPO) {

		logger.info("start viewWineryLicenseDetail method");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

		Response response = null;
		WineryLicenseDetailModel wineryLicenseDetailModel = null;

		WineryModel winery = WineryRepository.getWineryById(Long
				.parseLong(wineryLicenseDetailViewPO.getWineryId()));
		if (winery == null) {
			// winery not exist
			response = ApplicationUtils.errorMessageGenerate(

			SystemErrorCode.VIEW_WINERY_LICENSE_INVALID_WINERY,
					SystemErrorCode.VIEW_WINERY_LICENSE_INVALID_WINERY_TEXT,
					SUCCESS_CODE);

			logger.error("winery not exist");
		}

		if (response == null) {

			wineryLicenseDetailModel = WineryLicenseDetailRepository
					.getWineryLicenseDetailByWinery(winery);

			if (wineryLicenseDetailModel == null) {
				// WineryLicenseDetail not exist
				response = ApplicationUtils
						.errorMessageGenerate(

								SystemErrorCode.NO_ENTITY_FOUND,
								SystemErrorCode.NO_ENTITY_FOUND_TEXT,
								SUCCESS_CODE);

				logger.error("Winery License detail Id not exist");
			}
		}

		if (response == null) {

			WineryLicenseDetailViewVO wineryLicenseDetailViewVO = new WineryLicenseDetailViewVO();

			try {
				BeanUtils.copyProperties(wineryLicenseDetailViewVO,
						wineryLicenseDetailModel);
			} catch (IllegalAccessException e) {
				logger.error(
						"Error in copying value using bean util "
								+ e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.error(
						"Error in copying value using bean util "
								+ e.getMessage(), e);
			}

			wineryLicenseDetailViewVO.setWineryId(wineryLicenseDetailModel
					.getWinery().getId());

			if (wineryLicenseDetailModel.getCaLicenseType() != null) {
				wineryLicenseDetailViewVO
						.setCaLicenseTypeId(wineryLicenseDetailModel
								.getCaLicenseType().getId());
			}

			response = new com.wineaccess.response.SuccessResponse(
					wineryLicenseDetailViewVO, SUCCESS_CODE);

		}

		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit viewWineryLicenseDetail method");

		return output;
	}

}