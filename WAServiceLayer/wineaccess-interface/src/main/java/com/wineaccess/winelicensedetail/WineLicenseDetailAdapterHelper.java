package com.wineaccess.winelicensedetail;

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
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineLicenseDetailModel;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.response.Response;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.winerylicensedetail.WineryLicenseDetailRepository;

/**
 * This Class is used to perform the edit and view WineLicenseDetail
 * operation
 * 
 * @author rohit.lohiya
 * 
 */
public class WineLicenseDetailAdapterHelper {

	// purpose of this logger variable is to log the error messages in log file.
	private static Log logger = LogFactory
			.getLog(WineLicenseDetailAdapterHelper.class);

	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";

	/**
	 * This method is used to update the WineLicenseDetail in the database.
	 * 
	 * @param wineLicenseDetailUpdatePO
	 *            is used to take the input in this PO.
	 * @return output map containing response
	 */
	public static Map<String, Object> updateWineLicenseDetail(
			final WineLicenseDetailUpdatePO wineLicenseDetailUpdatePO) {

		logger.info("start updateWineLicenseDetail method");

		String errorMsg = StringUtils.EMPTY;

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

		Response response = null;

		try {

			MasterData caLicenseType = null;
			WineModel wine = null;
			WineLicenseDetailModel wineLicenseDetailModel = null;

			
			if (wineLicenseDetailUpdatePO.getCaLicenseTypeId() != null && !wineLicenseDetailUpdatePO.getCaLicenseTypeId().isEmpty()) {
				caLicenseType = MasterDataRepository.getMasterDataById(Long
						.parseLong(wineLicenseDetailUpdatePO
								.getCaLicenseTypeId()));
				if (caLicenseType == null) {
					// caLicenseType not exist
					response = ApplicationUtils
							.errorMessageGenerate(

									SystemErrorCode.UPDATE_WINE_LICENSE_INVALID_CA_LICENSE_TYPE,
									SystemErrorCode.UPDATE_WINE_LICENSE_INVALID_CA_LICENSE_TYPE_TEXT,
									SUCCESS_CODE);

					logger.error("CA License Type not exist");
				}
			}

			ProductItemModel productModel = ProductItemRepository.getProductItemById(Long.parseLong(wineLicenseDetailUpdatePO.getProductId()));
			if(productModel == null){
				response = ApplicationUtils
						.errorMessageGenerate(

								SystemErrorCode.UPDATE_WINE_PRODUCT_NOT_EXISTS,
								SystemErrorCode.UPDATE_WINE_PRODUCT_NOT_EXISTS_TEXT,
								SUCCESS_CODE);

				logger.error("productId not exist");
			}
			
			if (response == null) {
				wine = WineRepository.getWineById(productModel.getItemId());
				if (wine == null) {
					// wine not exist
					response = ApplicationUtils
							.errorMessageGenerate(

									SystemErrorCode.UPDATE_WINE_LICENSE_INVALID_WINE,
									SystemErrorCode.UPDATE_WINE_LICENSE_INVALID_WINE_TEXT,
									SUCCESS_CODE);

					logger.error("wine not exist");
				}
			}

			if (response == null) {

				wineLicenseDetailModel = WineLicenseDetailRepository
						.getWineLicenseDetailByWine(wine);

				if (wineLicenseDetailModel == null) {

					WineryLicenseDetailModel wineryLicenseDetailModel = WineryLicenseDetailRepository
							.getWineryLicenseDetailByWinery(wine.getWineryId());

					if (wineryLicenseDetailModel == null) {
						// WineLicenseDetail not exist
						response = ApplicationUtils
								.errorMessageGenerate(

										SystemErrorCode.UPDATE_WINE_LICENSE_INVALID_WINE_LICENSE_ID,
										SystemErrorCode.UPDATE_WINE_LICENSE_INVALID_WINE_LICENSE_ID_TEXT,
										SUCCESS_CODE);

						logger.error("Wine License detail not exist");
					} else {
						wineLicenseDetailModel = new WineLicenseDetailModel();

						wineLicenseDetailModel
								.setCaLicenseType(wineryLicenseDetailModel
										.getCaLicenseType());
						wineLicenseDetailModel.setWine(wine);
						wineLicenseDetailModel
								.setContractExecuted(wineryLicenseDetailModel
										.getContractExecuted());
						wineLicenseDetailModel
								.setShipCompliant(wineryLicenseDetailModel
										.getShipCompliant());
						wineLicenseDetailModel
								.setShipEscrowNo(wineryLicenseDetailModel
										.getShipEscrowNo());

						WineLicenseDetailRepository
								.save(wineLicenseDetailModel);

						WineLicenseDetailVO wineLicenseDetailVO = new WineLicenseDetailVO(
								SystemErrorCode.UPDATE_WINE_LICENSE_SUCCESS_TEXT);

						BeanUtils.copyProperties(wineLicenseDetailVO,
								wineLicenseDetailModel);

						wineLicenseDetailVO.setProductId(productModel.getId());
						wineLicenseDetailVO.setWineId(wineLicenseDetailModel
								.getWine().getId());

						response = new com.wineaccess.response.SuccessResponse(
								wineLicenseDetailVO, SUCCESS_CODE);
					}
				}
			}

			if (response == null) {

				
					wineLicenseDetailModel.setCaLicenseType(caLicenseType);
				
				wineLicenseDetailModel.setWine(wine);

				if (wineLicenseDetailUpdatePO.getContractExecuted() != null) {
					wineLicenseDetailModel.setContractExecuted(Boolean
							.parseBoolean(wineLicenseDetailUpdatePO
									.getContractExecuted()));
				}

				if (wineLicenseDetailUpdatePO.getShipCompliant() != null) {
					wineLicenseDetailModel.setShipCompliant(Boolean
							.parseBoolean(wineLicenseDetailUpdatePO
									.getShipCompliant()));
				}

				if (wineLicenseDetailUpdatePO.getShipEscrowNo() != null) {
					wineLicenseDetailModel
							.setShipEscrowNo(wineLicenseDetailUpdatePO
									.getShipEscrowNo());
				}

				if (wineLicenseDetailUpdatePO.getShipCompliantProductKey() != null) {
					wineLicenseDetailModel
							.setShipCompliantProductKey(wineLicenseDetailUpdatePO
									.getShipCompliantProductKey());
				}

				if (wineLicenseDetailUpdatePO.getPriceToRetailer() != null) {
					wineLicenseDetailModel.setPriceToRetailer(Double
							.parseDouble(wineLicenseDetailUpdatePO
									.getPriceToRetailer()));
				}

				if (wineLicenseDetailUpdatePO.getColaNumber() != null) {
					wineLicenseDetailModel
							.setColaNumber(wineLicenseDetailUpdatePO
									.getColaNumber());
				}

				WineLicenseDetailRepository.update(wineLicenseDetailModel);

				WineLicenseDetailVO wineLicenseDetailVO = new WineLicenseDetailVO(
						SystemErrorCode.UPDATE_WINE_LICENSE_SUCCESS_TEXT);

				BeanUtils.copyProperties(wineLicenseDetailVO,
						wineLicenseDetailModel);

				wineLicenseDetailVO.setProductId(productModel.getId());
				wineLicenseDetailVO.setWineId(wineLicenseDetailModel.getWine()
						.getId());

				response = new com.wineaccess.response.SuccessResponse(
						wineLicenseDetailVO, SUCCESS_CODE);
			}

		} catch (Exception e) {

			errorMsg = e.getCause().getMessage();
		}

		if (errorMsg.contains("uk_wine_id")) {
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.UPDATE_WINE_LICENSE_ALREADY_EXISTS,
					SystemErrorCode.UPDATE_WINE_LICENSE_ALREADY_EXISTS_TEXT,
					SUCCESS_CODE);

			logger.error("wine license detail already exists");
		}

		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit updateWineLicenseDetail method");

		return output;
	}

	/**
	 * This method is used to view the detail of wine license
	 * 
	 * @param wineLicenseDetailViewPO
	 *            is used to take input in this PO
	 * @return output map containing response
	 */
	public static Map<String, Object> viewWineLicenseDetail(
			final WineLicenseDetailViewPO wineLicenseDetailViewPO) {

		logger.info("start viewWineLicenseDetail method");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

		Response response = null;
		WineLicenseDetailModel wineLicenseDetailModel = null;
		ProductItemModel productModel = ProductItemRepository.getProductItemById(Long.parseLong(wineLicenseDetailViewPO.getProductId()));
		if(productModel == null){
			response = ApplicationUtils.errorMessageGenerate(

					SystemErrorCode.VIEW_WINE_LICENSE_INVALID_PRODUCT,
							SystemErrorCode.VIEW_WINE_LICENSE_INVALID_PRODUCT_TEXT,
							SUCCESS_CODE);

					logger.error("productId not exist");
		} 
		
		WineModel wine = null;
		if(response == null){
			wine = WineRepository.getWineById(productModel.getItemId());
			if (wine == null) {
				// wine not exist
				response = ApplicationUtils.errorMessageGenerate(

				SystemErrorCode.VIEW_WINE_LICENSE_INVALID_WINE,
						SystemErrorCode.VIEW_WINE_LICENSE_INVALID_WINE_TEXT,
						SUCCESS_CODE);

				logger.error("wine not exist");
			}	
		}
		

		if (response == null) {

			wineLicenseDetailModel = WineLicenseDetailRepository
					.getWineLicenseDetailByWine(wine);

			if (wineLicenseDetailModel == null) {

				WineryLicenseDetailModel wineryLicenseDetailModel = WineryLicenseDetailRepository
						.getWineryLicenseDetailByWinery(wine.getWineryId());

				if (wineryLicenseDetailModel == null) {
					// WineLicenseDetail not exist
					response = ApplicationUtils
							.errorMessageGenerate(

									SystemErrorCode.NO_ENTITY_FOUND,
									SystemErrorCode.NO_ENTITY_FOUND_TEXT,
									SUCCESS_CODE);

					logger.error("Wine License detail not exist");
				} else {
					wineLicenseDetailModel = new WineLicenseDetailModel();

					wineLicenseDetailModel
							.setCaLicenseType(wineryLicenseDetailModel
									.getCaLicenseType());
					wineLicenseDetailModel.setWine(wine);
					wineLicenseDetailModel
							.setContractExecuted(wineryLicenseDetailModel
									.getContractExecuted());
					wineLicenseDetailModel
							.setShipCompliant(wineryLicenseDetailModel
									.getShipCompliant());
					wineLicenseDetailModel
							.setShipEscrowNo(wineryLicenseDetailModel
									.getShipEscrowNo());

					WineLicenseDetailRepository.save(wineLicenseDetailModel);
				}
			}
		}

		if (response == null) {

			WineLicenseDetailViewVO wineLicenseDetailViewVO = new WineLicenseDetailViewVO();

			try {
				BeanUtils.copyProperties(wineLicenseDetailViewVO,
						wineLicenseDetailModel);
				wineLicenseDetailViewVO.setProductId(productModel.getId());
			} catch (IllegalAccessException e) {
				logger.error(
						"Error in copying value using bean util "
								+ e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.error(
						"Error in copying value using bean util "
								+ e.getMessage(), e);
			}

			wineLicenseDetailViewVO.setWineId(wineLicenseDetailModel.getWine()
					.getId());

			if (wineLicenseDetailModel.getCaLicenseType() != null) {
				wineLicenseDetailViewVO
						.setCaLicenseTypeId(wineLicenseDetailModel
								.getCaLicenseType().getId());
			}

			response = new com.wineaccess.response.SuccessResponse(
					wineLicenseDetailViewVO, SUCCESS_CODE);

		}

		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit viewWineLicenseDetail method");

		return output;
	}

}