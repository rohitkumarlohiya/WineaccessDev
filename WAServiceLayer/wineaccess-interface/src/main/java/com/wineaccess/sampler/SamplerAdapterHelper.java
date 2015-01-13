package com.wineaccess.sampler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.sampler.ProductSamplerModel;
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.response.Response;
import com.wineaccess.response.SuccessResponse;
import com.wineaccess.wine.ViewWineLogisticPO;
import com.wineaccess.wine.ViewWineLogisticVO;
import com.wineaccess.wine.WineAdapterHelper;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.winelicensedetail.WineLicenseDetailAdapterHelper;
import com.wineaccess.winelicensedetail.WineLicenseDetailViewPO;
import com.wineaccess.winelicensedetail.WineLicenseDetailViewVO;
import com.wineaccess.winepermit.WinePermitDetailVO;
import com.wineaccess.winepermit.WinePermitHelper;
import com.wineaccess.winepermit.WinePermitViewPO;



/**
 * @author gaurav.agarwal1
 * 
 */
public class SamplerAdapterHelper {

	private static Log logger = LogFactory.getLog(SamplerAdapterHelper.class);
	public static final String OUTPUT_PARAM_KEY = "FINAL-RESPONSE";

	/**
	 * This method is used to add the sampler.
	 * @param samplerPO take the input parameter for adding the sampler in database.
	 * @return Map the output map
	 */
	public static Map<String, Object> addSampler(final AddSamplerPO samplerPO) {

		logger.info("add the sampler");
		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		final SamplerModel samplerName = SamplerRepository.getSamplerByName(samplerPO.getSamplerName());
		if (samplerName == null) {

			final SamplerModel samplerModel = new SamplerModel();
			samplerModel.setName(samplerPO.getSamplerName());
			samplerModel.setIsEnabled(false);

			List<ProductDetails> products = samplerPO.getProducts();
			for (ProductDetails details : products) {
				if (details == null) {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER_ERROR_110,
									SystemErrorCode.ADD_SAMPLER_ERROR_110_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}
			}
			
			if (response == null) {
				List<String> uniqueProducts = new ArrayList<String>();
				for(ProductDetails details : products){
					String productType = StringUtils.EMPTY;
					if(StringUtils.isEmpty(details.getProductType())){
						MasterData productData = MasterDataRepository.getMasterDataByTypeAndName(MasterDataTypeEnum.Constants.PRODUCT_TYPE, "Wine");
						productType = Long.toString(productData.getId());
					}else{
						productType = details.getProductType();
					}
						String checkUnique = ApplicationUtils.generateUniqueHash(details.getProductId(),productType);
						if(uniqueProducts.contains(checkUnique)){
							response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER_ERROR_112,
									SystemErrorCode.ADD_SAMPLER_ERROR_112_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
						}else{
							uniqueProducts.add(checkUnique);
						}
				}
			}
			if (response == null) {
				List<ProductDetails> detailList = new ArrayList<ProductDetails>();
				for (ProductDetails details : products) {
					String productType = StringUtils.EMPTY;
					if(StringUtils.isEmpty(details.getProductType())){
						MasterData productData = MasterDataRepository.getMasterDataByTypeAndName(MasterDataTypeEnum.Constants.PRODUCT_TYPE, "Wine");
						productType = Long.toString(productData.getId());
					}else{
						productType = details.getProductType();
					}
					ProductItemModel productItemModel = ProductItemRepository.getByIdAndProduct(Long.parseLong(details.getProductId()),
									Long.parseLong(productType));
					
					if (productItemModel != null) {
						final ProductDetails productDetails = new ProductDetails();
						productDetails.setProductId(details.getProductId());
						productDetails.setProductType(productType);
						productDetails.setQuantity(details.getQuantity());
						detailList.add(productDetails);
					} else {
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER_ERROR_111,
										SystemErrorCode.ADD_SAMPLER_ERROR_111_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					}
				}
				if (response == null) {
					SamplerRepository.save(samplerModel);
					for (ProductDetails details : detailList) {

						final ProductSamplerModel productSamplerModel = new ProductSamplerModel();
						productSamplerModel.setSamplerId(samplerModel);

						ProductItemModel itemModel = ProductItemRepository.getProductItemById(Long.parseLong(details.getProductId()));
						productSamplerModel.setProductId(itemModel);

						MasterData quantity = MasterDataRepository.getMasterDataById(Long.parseLong(details.getQuantity()));
						productSamplerModel.setQuantity(quantity);

						ProductSamplerRepository.save(productSamplerModel);
					}
					AddSamplerVO addSamplerVO = new AddSamplerVO(samplerModel.getId(),SystemErrorCode.ADD_SAMPLER_SUCCESS_TEXT);
					response = new com.wineaccess.response.SuccessResponse(addSamplerVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER_ERROR_109,
					SystemErrorCode.ADD_SAMPLER_ERROR_109_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}

		logger.info("exit from add sampler");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
	}

	/**
	 * This method is used to view the sampler details and key metrics.
	 * 
	 * @param viewSamplerPO
	 * @return Map the output map
	 */
	public static Map<String,Object> viewSampler(final ViewSamplerPO viewSamplerPO){
		Map<String,Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		final SamplerModel samplerModel = SamplerRepository.getSamplerById(Long.parseLong(viewSamplerPO.getId()));
		if(samplerModel != null){
			final ViewSamplerVO samplerVO = new ViewSamplerVO();
			samplerVO.setSamplerId(samplerModel.getId());
			samplerVO.setSamplerName(samplerModel.getName());
			
			SamplerKeyMetrics keyMetrics = populateSamplerKeyMetrics();
			samplerVO.setKeyMetrics(keyMetrics);
			
			response = new com.wineaccess.response.SuccessResponse(samplerVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			
		}else{
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_SAMPLER_ERROR_103,
					SystemErrorCode.VIEW_SAMPLER_ERROR_103_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
	}
	
	/**
	 * populate the sampler key metrics
	 * @return SamplerKeyMetrics
	 */
	private static SamplerKeyMetrics populateSamplerKeyMetrics() {

		SamplerKeyMetrics keyMetrics = new SamplerKeyMetrics();
		return keyMetrics;
	}
	
	/**
	 * This method is used to view the sampler logistics detail
	 * @param viewSamplerLogisticsDetailPO
	 * @return output map
	 */
	public static Map<String, Object> viewSamplerLogisticsDetail(final ViewSamplerLogisticsDetailPO viewSamplerLogisticsDetailPO) {
		
		logger.info("Start viewSamplerLogisticsDetail method");
		
		Map<String,Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;		
	
		try {
			ViewSamplerLogisticsDetailVO viewSamplerLogisticsDetailVO = new ViewSamplerLogisticsDetailVO();
			
			List<ViewWineLogisticVO> viewWineLogisticVOs = new ArrayList<ViewWineLogisticVO>();	
					
			Set<Long> productIds = viewSamplerLogisticsDetailPO.getProductIds();	
			Set<Long> notExistProductIds = new HashSet<Long>(productIds);		
			Set<Long> wineLogisticsNotFoundIds = new HashSet<Long>();
			
			List<ProductSamplerModel> productSamplerModels = ProductSamplerRepository.getBySamplerId(Long.parseLong(viewSamplerLogisticsDetailPO.getId()));
			
			if(productSamplerModels != null)
			{
				if(viewSamplerLogisticsDetailPO.getProductIds() == null || viewSamplerLogisticsDetailPO.getProductIds().size() < 1)
				{
					for(ProductSamplerModel productSamplerModel : productSamplerModels)
					{
						ViewWineLogisticPO viewWineLogisticPO = new ViewWineLogisticPO();
						viewWineLogisticPO.setProductId(productSamplerModel.getProductId().getId().toString());					
						
						if((WineAdapterHelper.viewLogistic(viewWineLogisticPO).get("FINAL-RESPONSE")) instanceof SuccessResponse)
						{
							viewWineLogisticVOs.add((ViewWineLogisticVO)((SuccessResponse)(WineAdapterHelper.viewLogistic(viewWineLogisticPO).get("FINAL-RESPONSE")) ).getPayload());
						}
						else
						{
							wineLogisticsNotFoundIds.add(productSamplerModel.getProductId().getId());
						}	
					}
				}
				else
				{			
					for(ProductSamplerModel productSamplerModel : productSamplerModels)
					{	
						if(productIds.contains(productSamplerModel.getProductId().getId()))				
						{					
							ViewWineLogisticPO viewWineLogisticPO = new ViewWineLogisticPO();
							viewWineLogisticPO.setProductId(productSamplerModel.getProductId().getId().toString());								
							
							if((WineAdapterHelper.viewLogistic(viewWineLogisticPO).get("FINAL-RESPONSE")) instanceof SuccessResponse)
							{
								viewWineLogisticVOs.add((ViewWineLogisticVO)((SuccessResponse)(WineAdapterHelper.viewLogistic(viewWineLogisticPO).get("FINAL-RESPONSE")) ).getPayload());
								notExistProductIds.remove(productSamplerModel.getProductId().getId());
							}
							else{
								wineLogisticsNotFoundIds.add(productSamplerModel.getProductId().getId());
								notExistProductIds.remove(productSamplerModel.getProductId().getId());
							}					
						}					
					}		
				}		
				
				viewSamplerLogisticsDetailVO.setWineLogisticsNotFoundIds(wineLogisticsNotFoundIds);
				viewSamplerLogisticsDetailVO.setNotExistIds(notExistProductIds);
				viewSamplerLogisticsDetailVO.setWineLogistics(viewWineLogisticVOs);
				response = new com.wineaccess.response.SuccessResponse(viewSamplerLogisticsDetailVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
			else
			{
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_SAMPLER_LOGISTICS_NO_SAMPLER_FOUND,
						SystemErrorCode.VIEW_SAMPLER_LOGISTICS_NO_SAMPLER_FOUND_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		} catch (Exception e) {
			
			logger.error("error occured while processing view sampler logistics detail "+e);
			
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_SAMPLER_LOGISTICS_DETAIL_INTERNAL_SERVICE_ERROR,
					SystemErrorCode.VIEW_SAMPLER_LOGISTICS_DETAIL_INTERNAL_SERVICE_ERROR_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		
		logger.info("exit from viewSamplerLogisticsDetail method");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
		
	}
	
	/**
	 * This method is used to view the sampler compliance detail
	 * @param viewSamplerComplienceDetailPO
	 * @return output map
	 */
	public static Map<String, Object> viewSamplerComplienceDetail(final ViewSamplerComplienceDetailPO viewSamplerComplienceDetailPO) {
		
		logger.info("Start viewSamplerComplienceDetail method");
		
		Map<String,Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;		
	
		try {
			ViewSamplerComplienceDetailVO viewSamplerComplienceDetailVO = new ViewSamplerComplienceDetailVO();
				
			List<ViewWineLicenseAndPermitVO> viewWineLicenseAndPermitVOs = new ArrayList<ViewWineLicenseAndPermitVO>();
					
			Set<Long> productIds = viewSamplerComplienceDetailPO.getProductIds();	
			Set<Long> notExistProductIds = new HashSet<Long>(productIds);		
			Set<Long> wineLicenseAndPermitNotFoundIds = new HashSet<Long>();
			
			List<ProductSamplerModel> productSamplerModels = ProductSamplerRepository.getBySamplerId(Long.parseLong(viewSamplerComplienceDetailPO.getId()));
			
			if(productSamplerModels != null)
			{
				if(viewSamplerComplienceDetailPO.getProductIds() == null || viewSamplerComplienceDetailPO.getProductIds().size() < 1)
				{
					for(ProductSamplerModel productSamplerModel : productSamplerModels)
					{					
						WineLicenseDetailViewPO wineLicenseDetailViewPO = new WineLicenseDetailViewPO();
						wineLicenseDetailViewPO.setProductId(productSamplerModel.getProductId().getId().toString());	
						
						WinePermitViewPO winePermitViewPO = new WinePermitViewPO();
						winePermitViewPO.setProductId(productSamplerModel.getProductId().getId().toString());
						
						if(
								(WineLicenseDetailAdapterHelper.viewWineLicenseDetail(wineLicenseDetailViewPO).get("FINAL-RESPONSE")) instanceof SuccessResponse
								&&
								(WinePermitHelper.generateViewPermitResponse(winePermitViewPO).get("FINAL-RESPONSE")) instanceof SuccessResponse							
						)
						{
							ViewWineLicenseAndPermitVO viewWineLicenseAndPermitVO = new ViewWineLicenseAndPermitVO();
						
							viewWineLicenseAndPermitVO.setWineLicenseDetail((WineLicenseDetailViewVO)((SuccessResponse)(WineLicenseDetailAdapterHelper.viewWineLicenseDetail(wineLicenseDetailViewPO).get("FINAL-RESPONSE"))).getPayload());
							
							viewWineLicenseAndPermitVO.setWinePermitDetail((WinePermitDetailVO)((SuccessResponse)(WinePermitHelper.generateViewPermitResponse(winePermitViewPO).get("FINAL-RESPONSE"))).getPayload());
							
							viewWineLicenseAndPermitVOs.add(viewWineLicenseAndPermitVO);						
						}
						else
						{
							wineLicenseAndPermitNotFoundIds.add(productSamplerModel.getProductId().getId());
						}	
					}
				}
				else
				{			
					for(ProductSamplerModel productSamplerModel : productSamplerModels)
					{	
						if(productIds.contains(productSamplerModel.getProductId().getId()))				
						{	
							
							WineLicenseDetailViewPO wineLicenseDetailViewPO = new WineLicenseDetailViewPO();
							wineLicenseDetailViewPO.setProductId(productSamplerModel.getProductId().getId().toString());	
							
							WinePermitViewPO winePermitViewPO = new WinePermitViewPO();
							winePermitViewPO.setProductId(productSamplerModel.getProductId().getId().toString());
							
							if(
									(WineLicenseDetailAdapterHelper.viewWineLicenseDetail(wineLicenseDetailViewPO).get("FINAL-RESPONSE")) instanceof SuccessResponse
									&&
									(WinePermitHelper.generateViewPermitResponse(winePermitViewPO).get("FINAL-RESPONSE")) instanceof SuccessResponse							
							)
							{
								ViewWineLicenseAndPermitVO viewWineLicenseAndPermitVO = new ViewWineLicenseAndPermitVO();
							
								viewWineLicenseAndPermitVO.setWineLicenseDetail((WineLicenseDetailViewVO)((SuccessResponse)(WineLicenseDetailAdapterHelper.viewWineLicenseDetail(wineLicenseDetailViewPO).get("FINAL-RESPONSE"))).getPayload());
								
								viewWineLicenseAndPermitVO.setWinePermitDetail((WinePermitDetailVO)((SuccessResponse)(WinePermitHelper.generateViewPermitResponse(winePermitViewPO).get("FINAL-RESPONSE"))).getPayload());
								
								viewWineLicenseAndPermitVOs.add(viewWineLicenseAndPermitVO);
								notExistProductIds.remove(productSamplerModel.getProductId().getId());
							}
							else
							{
								wineLicenseAndPermitNotFoundIds.add(productSamplerModel.getProductId().getId());
								notExistProductIds.remove(productSamplerModel.getProductId().getId());
							}												
						}					
					}		
				}		
				
				viewSamplerComplienceDetailVO.setWineLicenseAndPermitNotFoundIds(wineLicenseAndPermitNotFoundIds);
				viewSamplerComplienceDetailVO.setNotExistIds(notExistProductIds);
				viewSamplerComplienceDetailVO.setWineLicenseAndPermits(viewWineLicenseAndPermitVOs);
				response = new com.wineaccess.response.SuccessResponse(viewSamplerComplienceDetailVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
			else
			{
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_SAMPLER_COMPLIENCE_NO_SAMPLER_FOUND,
						SystemErrorCode.VIEW_SAMPLER_COMPLIENCE_NO_SAMPLER_FOUND_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		} catch (Exception e) {
			
			logger.error("error occured while processing view sampler complience detail "+e);
			
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_SAMPLER_COMPLIENCE_DETAIL_INTERNAL_SERVICE_ERROR,
					SystemErrorCode.VIEW_SAMPLER_COMPLIENCE_DETAIL_INTERNAL_SERVICE_ERROR_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		
		logger.info("exit from viewSamplerComplienceDetail method");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
		
	}
	
	/**
	 * This method is used to update the sampler.
	 * @param updateSamplerPO
	 * @return Map the output map
	 */
	public static Map<String, Object> updateSampler(UpdateSamplerPO updateSamplerPO){
		
		logger.info("update the sampler");
		Map<String,Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		SamplerModel samplerModel = SamplerRepository.getSamplerById(Long.parseLong(updateSamplerPO.getId()));
		if(samplerModel != null){
			if(!samplerModel.getIsEnabled()){
				SamplerModel samplerName = SamplerRepository.getSamplerByName(updateSamplerPO.getName());
				if(samplerName == null || samplerName.getId() == samplerModel.getId()){
					samplerModel.setName(updateSamplerPO.getName());
					SamplerRepository.update(samplerModel);
					
					UpdateSamplerVO samplerVO = new UpdateSamplerVO(samplerModel.getId(), SystemErrorCode.UPDATE_SAMPLER_SUCCESS_TEXT);
					response = new com.wineaccess.response.SuccessResponse(samplerVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}else{
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_SAMPLER_ERROR_106,
							SystemErrorCode.UPDATE_SAMPLER_ERROR_106_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}
			}else{
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_SAMPLER_ERROR_105,
						SystemErrorCode.UPDATE_SAMPLER_ERROR_105_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		}else{
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_SAMPLER_ERROR_104,
					SystemErrorCode.UPDATE_SAMPLER_ERROR_104_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		
		logger.info("exit from update sampler");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
	}
	public static Map<String, Object> editSamplerWine(final EditSamplerWinePO editSamplerWinePO) {
		
		Map<String,Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		SamplerModel samplerModel = SamplerRepository.getSamplerById(Long.parseLong(editSamplerWinePO.getSamplerId()));
		ProductItemModel productItemModel = null; 
		ProductSamplerModel productSamplerModel = null;
		if(null == samplerModel){
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.EDIT_SAMPLER_WINE_ERROR_110, SystemErrorCode.EDIT_SAMPLER_WINE_ERROR_110_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		
		if(null == response){
			productItemModel = ProductItemRepository.getProductItemById(Long.parseLong(editSamplerWinePO.getProductId()));		
			if(null == productItemModel){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.EDIT_SAMPLER_WINE_ERROR_107, SystemErrorCode.EDIT_SAMPLER_WINE_ERROR_107_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}	
		}
		
		if(null == response){
			productSamplerModel = ProductSamplerRepository.getByProductAndSampler(samplerModel.getId(), productItemModel.getId());
			if(null == productSamplerModel){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.EDIT_SAMPLER_WINE_ERROR_111, SystemErrorCode.EDIT_SAMPLER_WINE_ERROR_111_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		}

		if(null == response){
			MasterData quatityMasterData = MasterDataRepository.getMasterDataById(Long.parseLong(editSamplerWinePO.getQuantity()));
			productSamplerModel.setQuantity(quatityMasterData);
			productSamplerModel.setSrpPrice(Double.parseDouble(editSamplerWinePO.getSrpPrice()));
			ProductSamplerModel updatedProductSamplerModel = ProductSamplerRepository.update(productSamplerModel);
			
			EditSamplerWineVO editSamplerWineVO = new EditSamplerWineVO();
			editSamplerWineVO.setProductId(updatedProductSamplerModel.getProductId().getId().toString());			
			editSamplerWineVO.setSamplerId(updatedProductSamplerModel.getId().toString());
			editSamplerWineVO.setMessage("Sampler Wine details updated successfully.");
		
			//TODO - Update Sampler table for SRP price and quantity of product/wine.
			response = new com.wineaccess.response.SuccessResponse(editSamplerWineVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
		
	}
	
/**
	 * This method is used to delete the wines from sampler.
	 * @param DeleteSamplerWinePO
	 * @return Map the output map
	 */
	public static Map<String, Object> deleteWineFromSampler(DeleteSamplerWinePO deleteSamplerWinePO){
		
		logger.info("delete wine from sampler");
		Map<String,Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		try{
			final Long samplerId = Long.parseLong(deleteSamplerWinePO.getSamplerId());
			final List<Long> productIds = deleteSamplerWinePO.getProductId();
			Boolean isForceDelete = true;
			final Set<Long> productSet = new HashSet<Long>(); 
			Set<Long> productIdSet = new HashSet<Long>();
			if(null != deleteSamplerWinePO.getIsForceDelete() && !("").equals(deleteSamplerWinePO.getIsForceDelete())){
				isForceDelete = Boolean.parseBoolean(deleteSamplerWinePO.getIsForceDelete()); 
			}				
		
			SamplerModel samplerModel = SamplerRepository.getSamplerById(samplerId);
			if(null == samplerModel){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DELETE_SAMPLER_WINE_ERROR_105, SystemErrorCode.DELETE_SAMPLER_WINE_ERROR_105_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
			
			List<ProductSamplerModel> productSamplerModel = null;
			if(null == response){
				productSamplerModel = ProductSamplerRepository.getBySamplerId(samplerModel.getId());	
			}
			
			if(null == response && null != productSamplerModel){
				
				for(ProductSamplerModel productSampler : productSamplerModel){
					productIdSet.add(productSampler.getProductId().getId());
				}
				
				for(Long product: productIds){
					
					if(productIdSet.contains(product)){
						productIdSet.remove(product);
						productSet.add(product);
					}
				}
				
				if(productIdSet.size() < 2){
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DELETE_SAMPLER_WINE_ERROR_106, SystemErrorCode.DELETE_SAMPLER_WINE_ERROR_106_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());		
				}
			}
			
			if(null == response){
				BulkDeleteModel<ProductSamplerModel> bulkDeleteModel = genericDao.delete(samplerModel, productSet, productIds, isForceDelete);
				
				
				/**
				 * Getting the list of deleted list and setting it to VO
				 * */
				DeleteVO<SamplerProductDetails> customModelsForDependency = new DeleteVO<SamplerProductDetails>();
				DeleteVO<SamplerProductDetails> customModelsForCanBeDeleted = new DeleteVO<SamplerProductDetails>();



				List<SamplerProductDetails> deleteList = new ArrayList<SamplerProductDetails>();
				List<SamplerProductDetails> dependencyList = new ArrayList<SamplerProductDetails>();

				List<ProductSamplerModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

				for(ProductSamplerModel productSampler: canBeDeletedList)
				{						
					SamplerProductDetails samplerProductDetails = new SamplerProductDetails(); 
					try
					{
						samplerProductDetails.setProductId(productSampler.getProductId().getId());
						samplerProductDetails.setQuantity(productSampler.getQuantity().getName());
						samplerProductDetails.setSrpPrice(productSampler.getSrpPrice().longValue());
						deleteList.add(samplerProductDetails);
					}		
					catch(Exception e){
						logger.error("Error while copying productSampler list to VO." + e);
					}						
				}

				customModelsForCanBeDeleted.setElements(deleteList);


				/**
				 * Getting the list of non deleted list and setting it to VO
				 * */
				List<ProductSamplerModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

				for(ProductSamplerModel productSampler: canNotBeDeletedList)
				{						
					SamplerProductDetails samplerProductDetails = new SamplerProductDetails(); 

					try
					{
						samplerProductDetails.setProductId(productSampler.getProductId().getId());
						samplerProductDetails.setQuantity(productSampler.getQuantity().getName());
						samplerProductDetails.setSrpPrice(productSampler.getSrpPrice().longValue());
						dependencyList.add(samplerProductDetails);
					}		
					catch(Exception e){
						logger.error("Error while copying productSampler list to VO." + e);
					}						
				}

				customModelsForDependency.setElements(dependencyList);

				/**
				 * Getting the list of non existing list and setting it to VO
				 * */
				List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();

				DeleteSamplerProductVO deleteSamplerProductVO = new DeleteSamplerProductVO();
				deleteSamplerProductVO.setNonExistsList(nonExistingList);
				deleteSamplerProductVO.setFailureList(customModelsForDependency);
				deleteSamplerProductVO.setSuccessList(customModelsForCanBeDeleted);
				deleteSamplerProductVO.setSamplerId(samplerModel.getId());
				
				response = new com.wineaccess.response.SuccessResponse(deleteSamplerProductVO, 200);
			}
		} catch(Exception e){
			logger.error("Error while deleting wine from sampler. "+e);
		}
		
		logger.info("exit from delete wine from sampler");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
	}/**
	 * This method is used to add the sampler product. 
	 * @param productPO take the input parameter for adding the sampler in database.
	 * @return Map the output map
	 */
	public static Map<String, Object> addSamplerProduct(final AddSamplerProductPO productPO) {

		logger.info("add the sampler product");
		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		final SamplerModel samplerModel = SamplerRepository.getSamplerById(Long.parseLong(productPO.getSamplerId()));
		if (samplerModel != null) {
			ProductSamplerModel productSamplerModel = ProductSamplerRepository.getByProductAndSampler(Long.parseLong(productPO.getSamplerId()),
							Long.parseLong(productPO.getProductId()));

			if (productSamplerModel == null) {

				final ProductSamplerModel model = new ProductSamplerModel();
				model.setSamplerId(samplerModel);

				MasterData productData = MasterDataRepository.getMasterDataByTypeAndName(MasterDataTypeEnum.Constants.PRODUCT_TYPE,"Wine");
				ProductItemModel productItemModel = ProductItemRepository.getByIdAndProduct(Long.parseLong(productPO.getProductId()),productData.getId());

				if (productItemModel != null) {
					model.setProductId(productItemModel);
					MasterData quantity = MasterDataRepository.getMasterDataById(Long.parseLong(productPO.getQtyOfProducts()));

					if (quantity != null) {
						model.setQuantity(quantity);
						model.setSrpPrice(Double.parseDouble(productPO.getSrpPrice()));
						ProductSamplerRepository.save(model);
						AddSamplerProductVO samplerProductVO = new AddSamplerProductVO(model.getId(),SystemErrorCode.ADD_SAMPLER_PRODUCT_SUCCESS_TEXT);
						response = new com.wineaccess.response.SuccessResponse(samplerProductVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					} else {
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_111,
										SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_111_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					}
				} else {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_110,
									SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_110_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}
			} else {
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_109,
						SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_109_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_108,
					SystemErrorCode.ADD_SAMPLER__PRODUCT_ERROR_108_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}

		logger.info("exit from add sampler product");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
	}

	/**
	 * This method is used to list the products in a sampler.
	 * @param listSamplerProductPO take the input parameter for listing the products in sampler in database.
	 * @return Map the output map
	 */
	public static Map<String, Object> listSamplerProducts(final ListSamplerProductPO listSamplerProductPO) {
		logger.info("List the sampler product");
		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		final Long samplerId = Long.parseLong(listSamplerProductPO.getSamplerId()); 
		final int sortOrder = Integer.parseInt(listSamplerProductPO.getSortOrder());
		final String sortBy = listSamplerProductPO.getSortBy();
		final int offset = Integer.parseInt(listSamplerProductPO.getOffSet());
		final int limit = Integer.parseInt(listSamplerProductPO.getLimit());
		
		try{
			SamplerModel samplerModel = SamplerRepository.getSamplerById(samplerId);
			if(null == samplerModel){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.LIST_SAMPLER_PRODUCT_ERROR_101, SystemErrorCode.LIST_SAMPLER_PRODUCT_ERROR_101_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
			
			List<ProductSamplerModel> productSamplerList = new ArrayList<ProductSamplerModel>();
			ListSamplerProductVO listSamplerProductVO = new ListSamplerProductVO();
			List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
			if(null == response){
				productSamplerList = SamplerRepository.listSamplerProduct(samplerId, sortBy, sortOrder, offset, limit);
				int count = SamplerRepository.getSamplerProductCount(samplerId, sortOrder);
				for(ProductSamplerModel productSampler: productSamplerList){
					ProductDetail productDetail = new ProductDetail();
					WineModel wine = WineRepository.getWineById(productSampler.getProductId().getItemId());
					if(null != wine){
						productDetail.setId(productSampler.getId().toString());
						productDetail.setProductId(productSampler.getProductId().getItemId().toString());
						productDetail.setProductName(wine.getWineName());	
						productDetail.setQuantity(productSampler.getQuantity().getName());
						productDetail.setSrpPrice(productSampler.getSrpPrice().toString());
						//TODO : Logic to get the list of compliance details for product. 
					}
					
					listSamplerProductVO.getProduct().add(productDetail);
					productDetails.add(productDetail);
				}
				if(productDetails!=null && productDetails.size()>0 && "productName".equals(listSamplerProductPO.getSortBy())){
					Collections.sort(productDetails, new Comparator<ProductDetail>() {
						@Override
						public int compare(ProductDetail o1, ProductDetail o2) {
							if(Integer.parseInt(listSamplerProductPO.getSortOrder()) == 1){
								return o1.getProductName().compareTo(o2.getProductName());
							}else{
								return o2.getProductName().compareTo(o1.getProductName());
							}
						}
					});
					if(offset > 0){
						try{
							if(((offset-1) + limit) > productDetails.size()){
								productDetails = productDetails.subList(offset-1, productDetails.size());	
							} else{
								productDetails = productDetails.subList(offset-1, (offset-1) + limit);	
							}
							listSamplerProductVO.getProduct().clear();
							listSamplerProductVO.getProduct().addAll(productDetails);
						} catch(Exception e){
							logger.info("No data found for the requested offset and limit.");
							listSamplerProductVO.getProduct().clear();
						}						
					}
				}
				if(productDetails!=null && productDetails.size()>0 && "quantity".equals(listSamplerProductPO.getSortBy())){

					try{
						Collections.sort(productDetails, new Comparator<ProductDetail>() {
							@Override
							public int compare(ProductDetail o1, ProductDetail o2) {
								if(Integer.parseInt(listSamplerProductPO.getSortOrder()) == 1){
									return Integer.parseInt(o1.getQuantity())-Integer.parseInt(o2.getQuantity());
								}else{
									return Integer.parseInt(o2.getQuantity())-Integer.parseInt(o1.getQuantity());
								}
							}
						});
					} catch(Exception e){
						logger.error("Error while converting the string list to integer list." + e);
					}
					if(offset > 0){
						try{
							if(((offset-1) + limit) > productDetails.size()){
								productDetails = productDetails.subList(offset-1, productDetails.size());	
							} else{
								productDetails = productDetails.subList(offset-1, (offset-1) + limit);	
							}
							listSamplerProductVO.getProduct().clear();
							listSamplerProductVO.getProduct().addAll(productDetails);
						} catch(Exception e){
							logger.info("No data found for the requested offset and limit.");
							listSamplerProductVO.getProduct().clear();
						}
					}
				}
				
				listSamplerProductVO.setSamplerId(samplerId.toString());
				listSamplerProductVO.setLimit(limit);
				listSamplerProductVO.setOffSet(offset);
				listSamplerProductVO.setCount(count);
				response = new com.wineaccess.response.SuccessResponse(listSamplerProductVO, 200);
			}
			
		} catch(Exception e){
			
			logger.error("Error while getting the list of products in sampler.");
			if(response == null){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.LIST_SAMPLER_PRODUCT_ERROR_105, SystemErrorCode.LIST_SAMPLER_PRODUCT_ERROR_105_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		}
		
		logger.info("exit from list sampler product");
		output.put(OUTPUT_PARAM_KEY, response);
		return output;
		
	}
}
