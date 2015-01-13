package com.wineaccess.orders.requisition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.common.MasterDataModel;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.EnumTypes;
import com.wineaccess.constants.EnumTypes.MasterDataEnum;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;
import com.wineaccess.constants.EnumTypes.POStatus;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.WineryImporterAddressModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.inventory.InventoryModel;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.requisition.REQLineItemsModel;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.distributioncentre.DistributionCentre;
import com.wineaccess.distributioncentre.DistributionCentreRepository;
import com.wineaccess.inventory.InventoryRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.util.notification.EmailNotifier;
import com.wineaccess.warehouse.WarehouseModel;
import com.wineaccess.warehouse.WarehouseRepository;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.winery.WineryRepository;

/**This class is used to add the requisition in the database
 * @author gaurav.agarwal1
 * 
 */
public class RequisitionHelper {

	private static Log logger = LogFactory.getLog(RequisitionHelper.class);
	public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";
	
	public static final String PO = "PO";
	public static final String WT = "WT";
	public static final String IT = "IT";
	

	/**
	 * This method is used to add the requisition type and map winery with that requisition
	 * 
	 * @param addReqisitionPO to add the requisition in database return map the output map
	 */
	public static Map<String, Object> addRequisition(final AddRequisitionPO addRequisitionPO) {

		logger.info("Start to add the requisition");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();

		Response response;
		try {
			response = new FailureResponse();
			response.setStatus(200);	

			RequisitionModel reqModel = new RequisitionModel();

			reqModel.setTypeOfREQ(EnumTypes.REQType.fetchPoType(addRequisitionPO.getRequisitionType()));

			DistributionCentre destinationDistributionCentre = DistributionCentreRepository.getById(Long.parseLong(addRequisitionPO.getDesitinationDCId()));
			if(destinationDistributionCentre == null)
			{
				response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_INVALID_DESTINATION_DC,SystemErrorCode.ADD_REQUISITION_INVALID_DESTINATION_DC_TEXT));
				logger.error("Invalid Destination DC");
			}
			else
			{
				reqModel.setDistributionCentreId(destinationDistributionCentre);
			}		

			if(EnumTypes.REQType.fetchPoType(addRequisitionPO.getRequisitionType()) == EnumTypes.REQType.IT)
			{
				AddRequisitionPOForTypeIT addRequisitionPOForTypeIT = (AddRequisitionPOForTypeIT)addRequisitionPO;

				if(addRequisitionPOForTypeIT.getSourceDCId().equals(addRequisitionPOForTypeIT.getDesitinationDCId()))
				{
					response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_SOURCE_DEST_SAME,SystemErrorCode.ADD_REQUISITION_SOURCE_DEST_SAME_TEXT));
					logger.error("Invalid Source DC");
				}
				else
				{			
					DistributionCentre sourceDistributionCentre = DistributionCentreRepository.getById(Long.parseLong(addRequisitionPOForTypeIT.getSourceDCId()));
					if(sourceDistributionCentre == null)
					{
						response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_INVALID_SOURCE_DC,SystemErrorCode.ADD_REQUISITION_INVALID_SOURCE_DC_TEXT));
						logger.error("Source DC and Destination DC can not be same");
					}
					else
					{
						reqModel.setSourceDcAddressId(sourceDistributionCentre);
					}	

					reqModel.setSourceRequisition(addRequisitionPOForTypeIT.getSourceRequisitionType());	
				}
			}
			else
			{
				AddRequisitionPOForTypePOWT addRequisitionPOForTypePOWT = (AddRequisitionPOForTypePOWT)addRequisitionPO;

				if(EnumTypes.REQType.fetchPoType(addRequisitionPO.getRequisitionType()) == EnumTypes.REQType.WT && addRequisitionPOForTypePOWT.getSourceAddressType().equals("DC"))
				{
					response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE,SystemErrorCode.ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE_TEXT));
					logger.error("sourceAddressType is not compatible with requisitionType");
				}
				else
				{
					if(addRequisitionPOForTypePOWT.getSourceAddressType().equals("DC"))
					{
						DistributionCentre distributionCentre = DistributionCentreRepository.getById(Long.parseLong(addRequisitionPOForTypePOWT.getSourceAddressId()));
						if(distributionCentre == null)
						{
							response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_DISTRIBUTION_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_DISTRIBUTION_NOT_FOUND_TEXT));
							logger.error("Distribution centre not found");
						}
						else
						{
							reqModel.setSourceDcAddressId(distributionCentre);
						}					
					}
					else
					{
						WarehouseModel warehouseModel = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(addRequisitionPOForTypePOWT.getSourceAddressId()));
						if(warehouseModel == null)
						{
							response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_WAREHOUSE_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_WAREHOUSE_NOT_FOUND_TEXT));
							logger.error("Warehouse not found");
						}
						else
						{						
							if(BooleanUtils.isTrue(warehouseModel.getIsNonWSTransportCarrier()))
							{								
								if(addRequisitionPOForTypePOWT.getInboundTransportId() == null)
								{
									response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT,SystemErrorCode.ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT_TEXT));								
									logger.error("Mandatory field inboundTransportId is required.");
								}
								else
								{
									MasterData inboundTransport = MasterDataRepository.getMasterDataById(Long.parseLong(addRequisitionPOForTypePOWT.getInboundTransportId()));
									if(inboundTransport == null)
									{
										response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_INVALID_INBOUND_TRANSPORT,SystemErrorCode.ADD_REQUISITION_INVALID_INBOUND_TRANSPORT_TEXT));
										logger.error("Invalid Inbound Transport");
									}
									else
									{
										reqModel.setInboundTransport(inboundTransport);
									}	
								}
							}							
							reqModel.setSourceWhAddressId(warehouseModel);
						}
					}
				}

				WineryModel wineryModel = WineryRepository.getWineryById(Long.parseLong(addRequisitionPOForTypePOWT.getWineryId()));
				if(wineryModel == null)
				{
					response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_WINERY_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_WINERY_NOT_FOUND_TEXT));
					logger.error("Winery not found");

				}
				else
				{
					reqModel.setWinery(wineryModel);
				}
			}	

			if(response.getErrors().isEmpty())
			{	   
				reqModel.setRevision("0");
				reqModel.setStatus(POStatus.APPROVED.toString());
				RequisitionRepository.save(reqModel);

				AddRequisitionVO addRequisitionVO = new AddRequisitionVO(reqModel.getId(),reqModel.getTypeOfREQ().toString(),SystemErrorCode.ADD_REQUISITION_SUCCESSFUL_TEXT);					
				response = new com.wineaccess.response.SuccessResponse(addRequisitionVO, 200);					
			}

		} catch (Exception e) {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_REQUISITION_INTERNAL_SERVER_ERROR,
					SystemErrorCode.ADD_REQUISITION_INTERNAL_SERVER_ERROR_TEXT,SUCCESS_CODE);
			logger.error("Internal Service Error Occured while processing request");
		}

		logger.info("Successfully added the requisition");
		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}

	/**
	 * This method is used to view the requisition details and key metrics
	 * @param viewRequisitionPO to add the requisition in database return map the output map
	 * @return Map the output map
	 */
	public static Map<String, Object> viewRequisition(final ViewRequisitionPO viewRequisitionPO) {

		logger.info("Start to view the requisition");
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		RequisitionModel requisitionModel = RequisitionRepository.findById(Long.parseLong(viewRequisitionPO.getId()));
		if (requisitionModel != null) {

			ViewRequisitionVO viewRequisitionVO = new ViewRequisitionVO();

			viewRequisitionVO.setId(requisitionModel.getId());
			viewRequisitionVO.setStatus(requisitionModel.getStatus());
			viewRequisitionVO.setType(requisitionModel.getTypeOfREQ().name());
			viewRequisitionVO.setSubmittedDate(requisitionModel.getSubmittedDate());
			viewRequisitionVO.setWinesCount(requisitionModel.getWinesCount());
			viewRequisitionVO.setQtyBottles(requisitionModel.getBottlesCount());
			viewRequisitionVO.setEstimatedPickupDate(requisitionModel.getExpectedPickupDate());
			viewRequisitionVO.setActualPickupDate(requisitionModel.getActualPickupDate());
			viewRequisitionVO.setExpectedArrivalDate(requisitionModel.getExpectedArrivalDate());
			viewRequisitionVO.setActualArrivalDate(requisitionModel.getActualArrivalDate());
			viewRequisitionVO.setEstimatedShippingDate(requisitionModel.getExpectedShippingDate());
			viewRequisitionVO.setActualShippingDate(requisitionModel.getActualShippingDate());

			WineryModel wineryModel = WineryRepository.getWineryById(requisitionModel.getWinery().getId());
			if (wineryModel != null) {
				WineryImporterAddressModel importerAddressModel = null;
				WineryImporterContacts wineryImporterContacts = null;
				if (wineryModel.getActiveImporterId() == null) {
					importerAddressModel = RequisitionRepository.getByWineryIdIsDefault(wineryModel.getId());
					wineryImporterContacts = RequisitionRepository.getContactByWineryIdIsDefault(wineryModel.getId());
				} else {
					importerAddressModel = RequisitionRepository.getByImporterIdIsDefault(wineryModel.getActiveImporterId().getId());
					wineryImporterContacts = RequisitionRepository.getContactByImporterIdIsDefault(wineryModel.getActiveImporterId().getId());
				}
				if (importerAddressModel != null && StringUtils.containsIgnoreCase(importerAddressModel.getAddressType().getName(),"Billing Address")) {
					LocationAddressModel locationAddress = new LocationAddressModel();
					String[] toBeIgnoredValues = { "cityId", "stateId","phone" };
					BeanUtils.copyProperties(importerAddressModel,locationAddress, toBeIgnoredValues);

					if (importerAddressModel.getStateId() != null) {
						Map<String, String> stateMap = new HashMap<String, String>();
						stateMap.put("id", importerAddressModel.getStateId().getId().toString());
						stateMap.put("value", importerAddressModel.getStateId().getStateName());
						locationAddress.setState(stateMap);
					}

					if (importerAddressModel.getCityId() != null) {
						Map<String, String> cityMap = new HashMap<String, String>();
						cityMap.put("id", importerAddressModel.getCityId().getId().toString());
						cityMap.put("value", importerAddressModel.getCityId().getCityName());
						locationAddress.setCity(cityMap);
					}
					locationAddress.setName(wineryModel.getWineryName());
					if (wineryImporterContacts != null && StringUtils.containsIgnoreCase(wineryImporterContacts.getContactType().getName(),"Billing Contact")) {
						locationAddress.setPhone(wineryImporterContacts.getPhone());
						locationAddress.setEmailId(wineryImporterContacts.getEmail());
						locationAddress.setFaxNumber(wineryImporterContacts.getFaxNumber());
					}
					viewRequisitionVO.setVendor(locationAddress);
				}
			}

			if (requisitionModel.getSourceWhAddressId() != null) {
				WarehouseModel warehouse = WarehouseRepository.getNonDeletedWarehouseById(requisitionModel.getSourceWhAddressId().getId());
				if (warehouse != null) {
					LocationAddressModel pickUpLocationAddressModel = new LocationAddressModel();

					String[] toBeIgnoredValues = { "cityId", "stateId" };
					BeanUtils.copyProperties(warehouse,pickUpLocationAddressModel, toBeIgnoredValues);

					Map<String, String> cityMap = new HashMap<String, String>();
					cityMap.put("id", warehouse.getCityId().getId().toString());
					cityMap.put("name", warehouse.getCityId().getCityName());
					pickUpLocationAddressModel.setCity(cityMap);

					Map<String, String> stateMap = new HashMap<String, String>();
					stateMap.put("id", warehouse.getStateId().getId().toString());
					stateMap.put("name", warehouse.getStateId().getStateName());
					pickUpLocationAddressModel.setState(stateMap);
					viewRequisitionVO.setWineLocation(pickUpLocationAddressModel);
				}
			}
			if (requisitionModel.getSourceDcAddressId() != null) {
				DistributionCentre distributionCentre = DistributionCentreRepository.getById(requisitionModel.getSourceDcAddressId().getId());
				if (distributionCentre != null) {
					LocationAddressModel locationAddress = populateDistributionCentre(distributionCentre);
					viewRequisitionVO.setWineLocation(locationAddress);
				}
			}
			if (requisitionModel.getDistributionCentreId() != null) {
				DistributionCentre distributionCentre = DistributionCentreRepository.getById(requisitionModel.getDistributionCentreId().getId());
				if (distributionCentre != null) {
					LocationAddressModel locationAddress = populateDistributionCentre(distributionCentre);
					viewRequisitionVO.setShipTo(locationAddress);
				}
			}

			RequisitionKeyMetrics keyMetrics = new RequisitionKeyMetrics();

			keyMetrics.setTotalCost(requisitionModel.getTotalPrice());
			keyMetrics.setEstimatedFreight(requisitionModel.getEstimatedFreight());

			viewRequisitionVO.setKeyMetrics(keyMetrics);
			response = new com.wineaccess.response.SuccessResponse(viewRequisitionVO, SUCCESS_CODE);

		} else {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_REQUISITION_NOT_FOUND,
					SystemErrorCode.VIEW_REQUISITION_NOT_FOUND_TEXT,SUCCESS_CODE);
		}
		logger.info("Successfully viewed the requisition");
		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}


	/**
	 * @param distributionCentre
	 * @return
	 */
	private static LocationAddressModel populateDistributionCentre(final DistributionCentre distributionCentre) {
		LocationAddressModel locationAddress = new LocationAddressModel();

		String [] toBeIgnoredValues = {"cityId","stateId"};
		BeanUtils.copyProperties(distributionCentre,locationAddress,toBeIgnoredValues);

		Map<String,String> cityMap = new HashMap<String, String>(); 
		cityMap.put("id", distributionCentre.getCityId().getId().toString());
		cityMap.put("name", distributionCentre.getCityId().getCityName());
		locationAddress.setCity(cityMap);

		Map<String,String> stateMap = new HashMap<String, String>();
		stateMap.put("id", distributionCentre.getStateId().getId().toString());
		stateMap.put("name", distributionCentre.getStateId().getStateName());
		locationAddress.setState(stateMap);
		locationAddress.setName("WS-"+distributionCentre.getStateId().getStateName());
		return locationAddress;
	}

	public static Map<String, Object> editRequisition(final EditRequisitionPO editRequisitionPO) {
		logger.info("Start to edit the requisition");
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response =  new FailureResponse();
		DistributionCentre destinationDC = null;

		try{

			//Fetching the requisition.
			RequisitionModel requisitionModel = RequisitionRepository.findById(Long.parseLong(editRequisitionPO.getRequisitionId())); 
			if(null == requisitionModel){

				response.addError(new WineaccessError(SystemErrorCode.NO_REQUISITION_FOUND,SystemErrorCode.NO_REQUISITION_FOUND_TEXT));
			} 

			if(requisitionModel != null){
				// Checking if the status is valid or not.
				if(!ApplicationUtils.isValidStatusChange(requisitionModel.getStatus(), POStatus.fetchPoStatus(Integer.parseInt(editRequisitionPO.getRequisitionStatus())))){
					response.addError(new WineaccessError(SystemErrorCode.INVALID_STATUS_CHANGE,SystemErrorCode.INVALID_STATUS_CHANGE_TEXT));
				} 

				destinationDC = DistributionCentreRepository.getById(Long.parseLong(editRequisitionPO.getDesitinationDCId()));
				if(null == destinationDC){
					response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_INVALID_DESTINATION_DC,SystemErrorCode.ADD_REQUISITION_INVALID_DESTINATION_DC_TEXT));
				}

				//If the saved requisition status is Approved, then only the requisition will be updated.
				if(requisitionModel.getStatus().equals(POStatus.APPROVED)){
					requisitionModel.setStatus(POStatus.fetchPoStatus(Integer.parseInt(editRequisitionPO.getRequisitionStatus())).toString());
					requisitionModel.setDistributionCentreId(destinationDC);
					//Checking the type of requisition
					if(EnumTypes.REQType.fetchPoType(editRequisitionPO.getRequisitionType()) == EnumTypes.REQType.IT){
						EditRequisitionPOForIT editRequisitionPOForIT = (EditRequisitionPOForIT) editRequisitionPO; 
						requisitionModel.setSourceRequisition(editRequisitionPOForIT.getSourceRequisitionType());
						DistributionCentre sourceDC = DistributionCentreRepository.getById(Long.parseLong(editRequisitionPOForIT.getSourceDCId()));
						if(null == sourceDC){
							response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_DISTRIBUTION_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_DISTRIBUTION_NOT_FOUND_TEXT));
						} else{
							//Checking if source DC address is same as destination DC.
							if(sourceDC.getId() == destinationDC.getId()){
								response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_SOURCE_DEST_SAME,SystemErrorCode.ADD_REQUISITION_SOURCE_DEST_SAME_TEXT));
							} else{
								requisitionModel.setSourceDcAddressId(sourceDC);
							}
						}

					} else{
						EditRequisitionPOForPOWT editRequisitionPOForPOWT = (EditRequisitionPOForPOWT) editRequisitionPO;
						//If the requisition type is WT and Source address type is DC, then it is error.
						if(EnumTypes.REQType.fetchPoType(editRequisitionPO.getRequisitionType()) == EnumTypes.REQType.WT && editRequisitionPOForPOWT.getSourceAddressType().equals("DC")){
							response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE,SystemErrorCode.ADD_REQUISITION_INVALID_SOURCE_ADD_TYPE_TEXT));
						} 
						else if(EnumTypes.REQType.fetchPoType(editRequisitionPO.getRequisitionType()) == EnumTypes.REQType.WT && editRequisitionPOForPOWT.getSourceAddressType().equals("WH")) {
							WineryModel wineryModel = WineryRepository.getWineryById(Long.parseLong(editRequisitionPOForPOWT.getWineryId()));
							if(wineryModel == null){
								response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_WINERY_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_WINERY_NOT_FOUND_TEXT));
							} else{
								requisitionModel.setWinery(wineryModel);
							}

							WarehouseModel warehouse = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(editRequisitionPOForPOWT.getSourceAddressId()));
							if(warehouse == null){
								response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_WAREHOUSE_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_WAREHOUSE_NOT_FOUND_TEXT));
							} else{
								requisitionModel.setSourceWhAddressId(warehouse);
								if(BooleanUtils.isTrue(warehouse.getIsNonWSTransportCarrier()) && editRequisitionPOForPOWT.getInboundTransportId() != null){

									MasterData inboundTransport = MasterDataRepository.getMasterDataById(Long.parseLong(editRequisitionPOForPOWT.getInboundTransportId()));
									requisitionModel.setInboundTransport(inboundTransport);
								} else{
									response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT,SystemErrorCode.ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT_TEXT));								
								}
							}
						} else if(EnumTypes.REQType.fetchPoType(editRequisitionPO.getRequisitionType()) == EnumTypes.REQType.PO){
							WineryModel wineryModel = WineryRepository.getWineryById(Long.parseLong(editRequisitionPOForPOWT.getWineryId()));
							if(wineryModel == null){
								response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_WINERY_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_WINERY_NOT_FOUND_TEXT));
							} else{
								requisitionModel.setWinery(wineryModel);
							}

							if(editRequisitionPOForPOWT.getSourceAddressType().equals("WH")){
								WarehouseModel warehouse = WarehouseRepository.getNonDeletedWarehouseById(Long.parseLong(editRequisitionPOForPOWT.getSourceAddressId()));
								if(warehouse == null){
									response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_WAREHOUSE_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_WAREHOUSE_NOT_FOUND_TEXT));
								} else{
									requisitionModel.setSourceWhAddressId(warehouse);
									requisitionModel.setSourceDcAddressId(null);
									if(BooleanUtils.isTrue(warehouse.getIsNonWSTransportCarrier()) && editRequisitionPOForPOWT.getInboundTransportId() != null){

										MasterData inboundTransport = MasterDataRepository.getMasterDataById(Long.parseLong(editRequisitionPOForPOWT.getInboundTransportId()));
										requisitionModel.setInboundTransport(inboundTransport);
									} else{
										response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT,SystemErrorCode.ADD_REQUISITION_REQUIRED_INBOUND_TRANSPORT_TEXT));								
									}
								}
							} else{
								DistributionCentre distributionCentre = DistributionCentreRepository.getById(Long.parseLong(editRequisitionPOForPOWT.getSourceAddressId()));
								if(distributionCentre == null){
									response.addError(new WineaccessError(SystemErrorCode.ADD_REQUISITION_DISTRIBUTION_NOT_FOUND,SystemErrorCode.ADD_REQUISITION_DISTRIBUTION_NOT_FOUND_TEXT));
								} else{
									requisitionModel.setSourceDcAddressId(distributionCentre);
									requisitionModel.setSourceWhAddressId(null);
								}
							}
						} 
					}

				} else{

					//If the saved requisition status is not Approved and status is either Released or Scheduled for Pickup or Submitted, then only dates can be modified.
					if(requisitionModel.getStatus().equals(POStatus.RELEASED) || requisitionModel.getStatus().equals(POStatus.SCHEDULED_FOR_PICKUP) || requisitionModel.getStatus().equals(POStatus.SUBMITTED)){
						//Updating the expected dates.
						if(editRequisitionPO.getExpectedPickupDate() != null){
							requisitionModel.setExpectedPickupDate(editRequisitionPO.getExpectedPickupDate());
						}

						if(editRequisitionPO.getExpectedArrivalDate() != null){
							requisitionModel.setExpectedArrivalDate(editRequisitionPO.getExpectedArrivalDate());
						}

						if(editRequisitionPO.getExpectedShippingDate() != null){
							requisitionModel.setExpectedShippingDate(editRequisitionPO.getExpectedShippingDate());
						}

						if(null != requisitionModel.getExpectedPickupDate() && null != requisitionModel.getExpectedArrivalDate()){
							if(!requisitionModel.getExpectedPickupDate().before(requisitionModel.getExpectedArrivalDate())){
								response.addError(new WineaccessError(SystemErrorCode.INVALID_EXPECTED_PICKUP_ARRIVAL_DATE,SystemErrorCode.INVALID_EXPECTED_PICKUP_ARRIVAL_DATE_TEXT));
							} 
						}

						if(null != requisitionModel.getExpectedArrivalDate() && null != requisitionModel.getExpectedShippingDate()){
							if(!requisitionModel.getExpectedArrivalDate().before(requisitionModel.getExpectedShippingDate())){
								response.addError(new WineaccessError(SystemErrorCode.INVALID_EXPECTED_ARRIVAL_SHIPPING_DATE,SystemErrorCode.INVALID_EXPECTED_ARRIVAL_SHIPPING_DATE_TEXT));
							}
						}

						if(null != requisitionModel.getExpectedPickupDate() && null != requisitionModel.getExpectedShippingDate()){
							if(!requisitionModel.getExpectedPickupDate().before(requisitionModel.getExpectedShippingDate())){
								response.addError(new WineaccessError(SystemErrorCode.INVALID_EXPECTED_PICKUP_SHIPPING_DATE,SystemErrorCode.INVALID_EXPECTED_PICKUP_SHIPPING_DATE_TEXT));
							}
						}

						//Updating the actual dates.
						if(editRequisitionPO.getActualPickupDate() != null){
							requisitionModel.setActualPickupDate(editRequisitionPO.getActualPickupDate());
						}

						if(editRequisitionPO.getActualArrivalDate() != null){
							requisitionModel.setActualArrivalDate(editRequisitionPO.getActualArrivalDate());
						}

						if(editRequisitionPO.getActualShippingDate() != null){
							requisitionModel.setActualShippingDate(editRequisitionPO.getActualShippingDate());
						}

						if(null != requisitionModel.getActualPickupDate() && null != requisitionModel.getActualArrivalDate()){
							if(!requisitionModel.getActualPickupDate().before(requisitionModel.getActualArrivalDate())){
								response.addError(new WineaccessError(SystemErrorCode.INVALID_ACTUAL_PICKUP_ARRIVAL_DATE,SystemErrorCode.INVALID_ACTUAL_PICKUP_ARRIVAL_DATE_TEXT));
							}
						}

						if(null != requisitionModel.getActualArrivalDate() && null != requisitionModel.getActualShippingDate()){
							if(!requisitionModel.getActualArrivalDate().before(requisitionModel.getActualShippingDate())){
								response.addError(new WineaccessError(SystemErrorCode.INVALID_ACTUAL_ARRIVAL_SHIPPING_DATE,SystemErrorCode.INVALID_ACTUAL_ARRIVAL_SHIPPING_DATE_TEXT));
							}
						}

						if(null != requisitionModel.getActualPickupDate() && null != requisitionModel.getActualShippingDate()){
							if(!requisitionModel.getActualArrivalDate().before(requisitionModel.getActualShippingDate())){
								response.addError(new WineaccessError(SystemErrorCode.INVALID_ACTUAL_PICKUP_SHIPPING_DATE,SystemErrorCode.INVALID_ACTUAL_PICKUP_SHIPPING_DATE_TEXT));
							}
						}
					}
				}
			}

			if(response.getErrors().isEmpty())
			{	   
				RequisitionRepository.update(requisitionModel);
				EditRequisitionVO editRequisitionVO = new EditRequisitionVO();
				editRequisitionVO.setId(requisitionModel.getId());
				editRequisitionVO.setMessage(SystemErrorCode.UPDATE_REQUISITION_SUCCESSFUL_TEXT);
				editRequisitionVO.setRequisitionType(requisitionModel.getTypeOfREQ().toString());
				response = new com.wineaccess.response.SuccessResponse(editRequisitionVO, 200);					
			}

		} catch(Exception e){
			logger.info("Unknown error occurred while updating the requisition.");
		}

		output.put(OUPUT_PARAM_KEY, response);
		logger.info("End to edit the requisition");
		return output;
	}



	/**This method will add the wine to the requisition
	 * @param addWineToRequisitionPO
	 * @return output
	 */
	public static Map<String, Object> addWineToRequisition(final AddWineToRequisitionPO addWineToRequisitionPO) {
		
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		logger.info("Start add wine to requistion to PO");
		Response response = new FailureResponse();
		
		final String requistionId = addWineToRequisitionPO.getRequistionId(); 
		final Long productId = Long.valueOf(addWineToRequisitionPO.getProductId());

		try{
			final RequisitionModel requisitionModel = RequisitionRepository.findById(Long.parseLong(requistionId));
			if(requisitionModel == null)
			{
				response.addError(new WineaccessError(SystemErrorCode.NO_REQ_FOUND,SystemErrorCode.NO_REQ_FOUND_TEXT));
				logger.error("No requistion found for the requisition Id = " +requistionId);
			}
			else 
			{
				final MasterData wineMasterData = MasterDataRepository.getMasterDataByTypeAndName(MasterDataTypeEnum.ProductType.name(), MasterDataEnum.Wine.name());
				if(wineMasterData == null){
					response.addError(new WineaccessError(SystemErrorCode.NO_WINE_FOUND_IN_PRODUCT,SystemErrorCode.NO_WINE_FOUND_IN_PRODUCT_TEXT));
					logger.error("No wine found in product item");
				}
				else{					
					
					final ProductItemModel productModel = ProductItemRepository.getProductItemById(Long.valueOf(productId));
					if(productModel == null)
				    {
						 response.addError(new WineaccessError(SystemErrorCode.ADD_WINE_TO_REQUISITION_INVALID_PRODUCT_ID,SystemErrorCode.ADD_WINE_TO_REQUISITION_INVALID_PRODUCT_ID_TEXT));
						 logger.error("Product Id not exist");
				    }
					else
					{	
						final Long wineId = productModel.getItemId();
						final WineModel wineModel = WineRepository.getWineById(wineId);
						
						if(wineModel==null)
						{
							response.addError(new WineaccessError(SystemErrorCode.NO_WINE_FOUND,SystemErrorCode.NO_WINE_FOUND_TEXT));
							logger.error("No wine found for wineId = " + wineId);
						}
						else
						{
							if(wineModel.getWineryId() != null && requisitionModel.getWinery() != null)
							{
								if(wineModel.getWineryId().getId() != requisitionModel.getWinery().getId())
								{
									response.addError(new WineaccessError(SystemErrorCode.WINE_AND_REQ_NOT_HAVING_SAME_WINERY,SystemErrorCode.WINE_AND_REQ_NOT_HAVING_SAME_WINERY_TEXT));
									logger.error("Wine(Winery Id = "+wineModel.getWineryId().getId()+") and requisition(Winery Id = "+requisitionModel.getWinery().getId()+") not having same winery");
								}
							}
						}
						
						REQLineItemsModel reqLineItemsModel = RequisitionLineItemRepository.findByIdProductId(requisitionModel.getId(), wineId);
						if(reqLineItemsModel!=null)
						{
							response.addError(new WineaccessError(SystemErrorCode.WINE_ALREADY_ADDED_IN_PO,SystemErrorCode.WINE_ALREADY_ADDED_IN_PO_TEXT));
							logger.error("wine already added in PO");
						}						
						
						if(requisitionModel != null && wineModel != null)
						{
							if(requisitionModel.getTypeOfREQ().toString().equals(PO) && !BooleanUtils.isTrue(wineModel.getIsKachina()))
							{
								response.addError(new WineaccessError(SystemErrorCode.ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE,SystemErrorCode.ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT));
								logger.error("Wine and Requistion is not compatible");
							}
							else if(requisitionModel.getTypeOfREQ().toString().equals(WT) && !BooleanUtils.isTrue(wineModel.getIsWineryTransfer()))
							{
								response.addError(new WineaccessError(SystemErrorCode.ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE,SystemErrorCode.ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT));
								logger.error("Wine and Requistion is not compatible");
							}
							else if(requisitionModel.getTypeOfREQ().toString().equals(IT))
							{									
								InventoryModel inventoryModel = InventoryRepository.getInventoryByWineId(wineModel.getId());
								if(inventoryModel == null || inventoryModel.getPhysicalInventory() < 1)
								{
									response.addError(new WineaccessError(SystemErrorCode.ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE,SystemErrorCode.ADD_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT));
									logger.error("Wine and Requistion is not compatible either invertory not exist or physical inventory less then 1");
								}								
							}
						}
	
						if(response.getErrors().isEmpty())
						{							
							final Long bottlePerBox = Long.parseLong(MasterDataRepository.getMasterDataById(Long.parseLong(addWineToRequisitionPO.getBottlePerBox())).getName());
							final Integer qtyOfBottles = Integer.valueOf(addWineToRequisitionPO.getQtyOfBottles());		
							
							reqLineItemsModel = new REQLineItemsModel();

							reqLineItemsModel.setRequisition(requisitionModel);
							reqLineItemsModel.setWine(wineModel);
							reqLineItemsModel.setQtyBottles(qtyOfBottles);
							reqLineItemsModel.setQtyBoxes(calculateQtyBoxes(qtyOfBottles,bottlePerBox));		
							reqLineItemsModel.setBottlePerBox(MasterDataRepository.getMasterDataById(Long.parseLong(addWineToRequisitionPO.getBottlePerBox())));	
							reqLineItemsModel.setBottleInMl(wineModel.getBottleInMl());	
							reqLineItemsModel.setBottlePrice(new BigDecimal(calculateBottlePrice(Double.parseDouble(addWineToRequisitionPO.getCostPerBox()), bottlePerBox)));
							reqLineItemsModel.setCostPerBox(new BigDecimal(addWineToRequisitionPO.getCostPerBox()));		
							
							RequisitionLineItemRepository.save(reqLineItemsModel);
							
							logger.info("Successfully added the wine in PO");
							AddWineToRequisitionVO addWineToRequisitionVO = new AddWineToRequisitionVO(reqLineItemsModel.getId(),productModel.getId(),requisitionModel.getId(),"Wine added to requisition");
							response = new com.wineaccess.response.SuccessResponse(addWineToRequisitionVO, SUCCESS_CODE);

							}
						}
					}
				}			
			}
			catch(Exception e) {
				logger.info("Couldn't add wine to PO");
				logger.error(e);
				response.addError(new WineaccessError(SystemErrorCode.WINE_ADDED_TO_PO_FAILURE,SystemErrorCode.WINE_ADDED_TO_PO_FAILURE_TEXT));
			}

			output.put(OUPUT_PARAM_KEY, response);
			return output;
	}
	
	/**
	 * This method will edit the wine to the requisition
	 * @param editWineToRequisitionPO
	 * @return
	 */
	public static Map<String, Object> editWineToRequisition(final EditWineToRequisitionPO editWineToRequisitionPO) {

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		logger.info("Start edit wine to requistion to PO");
		Response response = new FailureResponse();

		final Long reqLineItemId = Long.parseLong(editWineToRequisitionPO.getId());
		final String requistionId = editWineToRequisitionPO.getRequistionId();
		final Long productId = Long.valueOf(editWineToRequisitionPO.getProductId());

		try {

			final REQLineItemsModel reqLineItemsModel = RequisitionLineItemRepository.getByIdAndRequisitionId(Long.parseLong(requistionId),reqLineItemId);
			if (reqLineItemsModel == null) {
				response.addError(new WineaccessError(
						SystemErrorCode.EDIT_WINE_REQUISITION_NO_REQ_FOUND,
						SystemErrorCode.EDIT_WINE_REQUISITION_NO_REQ_FOUND_TEXT));
				logger.error("No REQLineItemsModel recored found for requistionId = "+ requistionId+ " and reqLineItemId = "+ reqLineItemId);
			} else {
				final MasterData wineMasterData = MasterDataRepository.getMasterDataByTypeAndName(MasterDataTypeEnum.ProductType.name(),MasterDataEnum.Wine.name());
				if (wineMasterData == null) {
					response.addError(new WineaccessError(
							SystemErrorCode.EDIT_WINE_REQUISITION_NO_WINE_FOUND_IN_PRODUCT,
							SystemErrorCode.EDIT_WINE_REQUISITION_NO_WINE_FOUND_IN_PRODUCT_TEXT));
					logger.error("No wine found in product item");
				} else {

					final ProductItemModel productModel = ProductItemRepository.getProductItemById(Long.valueOf(productId));
					if (productModel == null) {
						response.addError(new WineaccessError(
								SystemErrorCode.EDIT_WINE_TO_REQUISITION_INVALID_PRODUCT_ID,
								SystemErrorCode.EDIT_WINE_TO_REQUISITION_INVALID_PRODUCT_ID_TEXT));
						logger.error("Product Id not exist");
					} else {
						final RequisitionModel requisition = reqLineItemsModel.getRequisition();
						final Long wineId = productModel.getItemId();
						final WineModel wineModel = WineRepository.getWineById(wineId);

						if (wineModel == null) {
							response.addError(new WineaccessError(
									SystemErrorCode.EDIT_WINE_REQUISITION_NO_WINE_FOUND,
									SystemErrorCode.EDIT_WINE_REQUISITION_NO_WINE_FOUND_TEXT));
							logger.error("No wine found for wineId = " + wineId);
						} else {
							if (wineModel.getWineryId() != null && requisition.getWinery() != null) {
								if (wineModel.getWineryId().getId() != requisition.getWinery().getId()) {
									response.addError(new WineaccessError(
											SystemErrorCode.EDIT_WINE_REQUISITION_WINE_AND_REQ_NOT_HAVING_SAME_WINERY,
											SystemErrorCode.EDIT_WINE_REQUISITION_WINE_AND_REQ_NOT_HAVING_SAME_WINERY_TEXT));
									logger.error("Wine(Winery Id = "+ wineModel.getWineryId().getId()+ ") and requisition(Winery Id = "+ requisition.getWinery().getId()
											+ ") not having same winery");
								}
							}
						}

						if (requisition != null && wineModel != null) {
							if (requisition.getTypeOfREQ().toString().equals(PO) && BooleanUtils.isNotTrue(wineModel.getIsKachina())) {
								response.addError(new WineaccessError(
										SystemErrorCode.EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE,
										SystemErrorCode.EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT));
								logger.error("Wine and Requistion is not compatible");
							} else if (requisition.getTypeOfREQ().toString().equals(WT) && BooleanUtils.isNotTrue(wineModel.getIsWineryTransfer())) {
								response.addError(new WineaccessError(
										SystemErrorCode.EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE,
										SystemErrorCode.EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT));
								logger.error("Wine and Requistion is not compatible");
							} else if (requisition.getTypeOfREQ().toString().equals(IT)) {
								final InventoryModel inventoryModel = InventoryRepository.getInventoryByWineId(wineModel.getId());
								if (inventoryModel == null|| inventoryModel.getPhysicalInventory() < 1) {
									response.addError(new WineaccessError(
											SystemErrorCode.EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE,
											SystemErrorCode.EDIT_WINE_TO_REQUISITION_WINE_REQ_NOT_COMPATIBLE_TEXT));
									logger.error("Wine and Requistion is not compatible either invertory not exist or physical inventory less then 1");
								}
							}
						}

						if (response.getErrors().isEmpty()) {
							final Long bottlePerBox = Long.parseLong(MasterDataRepository.getMasterDataById(Long.parseLong(editWineToRequisitionPO.getBottlePerBox())).getName());
							final Integer qtyOfBottles = Integer.valueOf(editWineToRequisitionPO.getQtyOfBottles());

							reqLineItemsModel.setRequisition(requisition);
							reqLineItemsModel.setWine(wineModel);
							reqLineItemsModel.setQtyBottles(qtyOfBottles);
							reqLineItemsModel.setQtyBoxes(calculateQtyBoxes(qtyOfBottles, bottlePerBox));
							reqLineItemsModel.setBottlePerBox(MasterDataRepository.getMasterDataById(Long.parseLong(editWineToRequisitionPO.getBottlePerBox())));
							reqLineItemsModel.setBottleInMl(wineModel.getBottleInMl());
							reqLineItemsModel.setBottlePrice(new BigDecimal(calculateBottlePrice(Double.parseDouble(editWineToRequisitionPO.getCostPerBox()),bottlePerBox)));
							reqLineItemsModel.setCostPerBox(new BigDecimal(editWineToRequisitionPO.getCostPerBox()));

							RequisitionLineItemRepository.update(reqLineItemsModel);

							logger.info("Successfully edit the wine in PO");
							final AddWineToRequisitionVO addWineToRequisitionVO = new AddWineToRequisitionVO(reqLineItemsModel.getId(),productModel.getId(), requisition.getId(),
									"Wine edited to requisition");
							response = new com.wineaccess.response.SuccessResponse(addWineToRequisitionVO, SUCCESS_CODE);

						}
					}
				}
			}
		} catch (Exception e) {
			logger.info("Couldn't edit wine to PO");
			logger.error(e);
			response.addError(new WineaccessError(
					SystemErrorCode.EDIT_WINE_REQUISITION_WINE_EDITED_TO_PO_FAILURE,
					SystemErrorCode.EDIT_WINE_REQUISITION_WINE_EDITED_TO_PO_FAILURE_TEXT));
		}

		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}

	/**
	 * this method is used to remove the wines in requisition
	 * @param removeWineFromRequisitionPO
	 * @return Map the output map
	 */
	public Map<String, Object> removeWineFromRequisition(final RemoveWineFromRequisitionPO removeWineFromRequisitionPO) {

		logger.info("Start remove wine from requistion");
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		List<Long> productList = removeWineFromRequisitionPO.getProductIds();

		Boolean isForceDelete = false;
		if (StringUtils.isNotBlank(removeWineFromRequisitionPO.getIsForceDelete()))
			isForceDelete = Boolean.parseBoolean(removeWineFromRequisitionPO.getIsForceDelete());
		try {
			Map<String, String> dependentFieldsMap = new HashMap<String, String>();
			dependentFieldsMap.put("status", "APPROVED");

			BulkDeleteModel<REQLineItemsModel> bulkDeleteModel = RequisitionLineItemRepository.removeWineFromRequisition(productList, 
					Long.parseLong(removeWineFromRequisitionPO.getRequisitionId()), isForceDelete,dependentFieldsMap);

			DeleteVO<WineInRequistionResultModel> customModelsForDependency = new DeleteVO<WineInRequistionResultModel>();
			DeleteVO<WineInRequistionResultModel> customModelsForCanBeDeleted = new DeleteVO<WineInRequistionResultModel>();

			List<WineInRequistionResultModel> deleteList = new ArrayList<WineInRequistionResultModel>();
			List<WineInRequistionResultModel> dependencyList = new ArrayList<WineInRequistionResultModel>();

			List<REQLineItemsModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

			for (REQLineItemsModel itemsModel : canBeDeletedList) {
				deleteList.add(populateWineRequisitionResult(itemsModel));
			}
			customModelsForCanBeDeleted.setElements(deleteList);

			List<REQLineItemsModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

			for (REQLineItemsModel lineItemsModel : canNotBeDeletedList) {
				dependencyList.add(populateWineRequisitionResult(lineItemsModel));
			}

			customModelsForDependency.setElements(dependencyList);

			List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();

			RemoveWineFromRequisitionVO removeWineFromRequisitionVO = new RemoveWineFromRequisitionVO();
			removeWineFromRequisitionVO.setNonExistsList(nonExistingList);
			removeWineFromRequisitionVO.setFailureList(customModelsForDependency);
			removeWineFromRequisitionVO.setSuccessList(customModelsForCanBeDeleted);

			response = new com.wineaccess.response.SuccessResponse(removeWineFromRequisitionVO, SUCCESS_CODE);
		} catch (Exception e) {
			logger.error("Unknown error occured while deleting the wines from requisitions.",e);
		}
		logger.info("exit remove wine from requistion");
		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}

	
	public static Map<String, Object> deleteRequisition(final DeleteRequisitionPO deleteRequisitionPO){
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		logger.info("Start delete requisition.");
		Response response = new FailureResponse();
		response.setStatus(200);	
		
		final List<Long> requisitionList = deleteRequisitionPO.getRequisitionId(); 
		Boolean isForceDelete = false;
		Map<String, String> dependentFieldsMap = new HashMap<String, String>();
		
		try{ 
			if(StringUtils.isNotBlank(deleteRequisitionPO.getIsForceDelete())){
				isForceDelete = Boolean.parseBoolean(deleteRequisitionPO.getIsForceDelete()); 
			}
			
			dependentFieldsMap.put("status", "APPROVED");
			
			BulkDeleteModel<RequisitionModel> bulkDeleteModel = RequisitionRepository.delete(requisitionList, isForceDelete, dependentFieldsMap);
			
			DeleteVO<RequisitionDetails> requisitionDetailsForDependency = new DeleteVO<RequisitionDetails>();
			DeleteVO<RequisitionDetails> requisitionDetailsForDeleted = new DeleteVO<RequisitionDetails>();

			List<RequisitionDetails> deleteList = new ArrayList<RequisitionDetails>();
			List<RequisitionDetails> dependencyList = new ArrayList<RequisitionDetails>();

			List<RequisitionModel> canBeDeletedList = bulkDeleteModel.getDeletedList();

			for (RequisitionModel requisitionModel : canBeDeletedList) {
			    deleteList.add(populateRequisitionList(requisitionModel));
			}

			requisitionDetailsForDeleted.setElements(deleteList);

			List<RequisitionModel> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();

			for (RequisitionModel requisitionModel : canNotBeDeletedList) {
			    dependencyList.add(populateRequisitionList(requisitionModel));
			}

			requisitionDetailsForDependency.setElements(dependencyList);


			List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();

			DeleteRequisitionVO deleteRequisitionVO = new DeleteRequisitionVO();
			deleteRequisitionVO.setNonExistsList(nonExistingList);
			deleteRequisitionVO.setFailureList(requisitionDetailsForDependency);
			deleteRequisitionVO.setSuccessList(requisitionDetailsForDeleted);
			
			response = new com.wineaccess.response.SuccessResponse(deleteRequisitionVO, SUCCESS_CODE);
		} catch(Exception e){
			logger.error("Unknown error occured while deleting the requisitions.", e);
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UNKNOWN_REQUISITION_DELETE_ERROR,SystemErrorCode.UNKNOWN_REQUISITION_DELETE_ERROR_TEXT,SUCCESS_CODE);
		}
		
		logger.info("End delete requisition.");
		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}
	
	
	/**
	 * @param qtyOfBottles
	 * @param bottlePerBox
	 * @return
	 */
	private static Integer calculateQtyBoxes(Integer qtyOfBottles,Long bottlePerBox )
	{
		return (int)Math.ceil((double)qtyOfBottles/bottlePerBox);
	}

	/**
	 * @param bottlePerBox
	 * @param unitPriceInDollar
	 * @return
	 */
	private static Double calculateBottlePrice(Double costPerBox, Long bottlePerBox )
	{
		Double bottlePrice = costPerBox/bottlePerBox;
		return bottlePrice;
	}

	/**
	 * this method is used to list the wines in requisition
	 * @param listWineInRequisionPO
	 * @return Map the outputListAddress map
	 */
	public Map<String, Object> listWinesInRequistion(final ListWineInRequisitionPO listWineInRequisionPO) {
		
		logger.info("listing the wines in requisition");
		Response response = null;

		final Long requisitionId = Long.parseLong(listWineInRequisionPO.getRequisitionId()); 
		final Map<String, Object> outputListAddress = new ConcurrentHashMap<String, Object>();

		final RequisitionModel requisitionModel = RequisitionRepository.findById(requisitionId);
		if(requisitionModel == null) {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_REQ_FOUND_WINE_REQ, SystemErrorCode.NO_REQ_FOUND_WINE_REQ_TEXT, SUCCESS_CODE);
		} else {
			List<Long> exclusionsList = new ArrayList<Long>();
			for (String s : listWineInRequisionPO.getExclusions()) {
				try {
					exclusionsList.add(Long.parseLong(s));
				} catch (NumberFormatException e) {
					logger.error("number format exception occurs during exclusion list");
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.LIST_WINE_REQUISITION_ERROR_103,
							SystemErrorCode.LIST_WINE_REQUISITION_ERROR_103_TEXT, SUCCESS_CODE);
				}
			}
			if(response == null){

				List<REQLineItemsModel> reqLineItemsModels = RequisitionLineItemRepository.getWinesList(requisitionId, Integer.parseInt(listWineInRequisionPO.getSortOrder()), 
						Integer.parseInt(listWineInRequisionPO.getOffSet())-1, Integer.parseInt(listWineInRequisionPO.getLimit()),exclusionsList);
				
				int size = reqLineItemsModels.size();
				int totalCount = RequisitionLineItemRepository.countTotalRecords(requisitionId);

				List<WineInRequistionResultModel> wineInRequistionResultModels = new ArrayList<WineInRequistionResultModel>();
				for (REQLineItemsModel lineItemsModel : reqLineItemsModels) {

					final WineInRequistionResultModel wineInRequistionResultModel = populateWineRequisitionResult(lineItemsModel);

					wineInRequistionResultModels.add(wineInRequistionResultModel);
				}
				ListWineInRequisitionVO winesInReqVO = new ListWineInRequisitionVO();
				winesInReqVO.setCount(size);
				winesInReqVO.setTotalRecordCount(totalCount);

				winesInReqVO.setOffSet(Integer.parseInt(listWineInRequisionPO.getOffSet()));
				winesInReqVO.setLimit(Integer.parseInt(listWineInRequisionPO.getLimit()));

				winesInReqVO.setRequistionId(listWineInRequisionPO.getRequisitionId());
				winesInReqVO.setWineInReqDetail(wineInRequistionResultModels);
				response = new com.wineaccess.response.SuccessResponse(winesInReqVO, 200);
			}
		}
		logger.info("exit from listing wines in requisition");
		outputListAddress.put("FINAL-RESPONSE", response);
		return outputListAddress;
	}

	/**
	 * @param lineItemsModel
	 * @return
	 */
	private WineInRequistionResultModel populateWineRequisitionResult(REQLineItemsModel lineItemsModel) {
		
		final WineInRequistionResultModel wineInRequistionResultModel = new WineInRequistionResultModel();
		wineInRequistionResultModel.setWineId(lineItemsModel.getWine().getProduct().getId());
		wineInRequistionResultModel.setWineFullName(lineItemsModel.getWine().getWineFullName());
		wineInRequistionResultModel.setQtyOfBottles(lineItemsModel.getQtyBottles());
		wineInRequistionResultModel.setQtyOfBoxes(lineItemsModel.getQtyBoxes());
		
		MasterData bottlePerBoxMasterData = lineItemsModel.getBottlePerBox();
		MasterDataModel bottlePerBoxModel = new MasterDataModel();
		bottlePerBoxModel.setId(bottlePerBoxMasterData.getId());
		bottlePerBoxModel.setMasterDataName(bottlePerBoxMasterData.getName());
		bottlePerBoxModel.setMasterDataTypeName(bottlePerBoxMasterData.getMasterDataType().getName());
		wineInRequistionResultModel.setBottlePerBox(bottlePerBoxModel);
		
		MasterData bottleInMlMasterData = lineItemsModel.getBottlePerBox();
		MasterDataModel bottleInMlModel = new MasterDataModel();
		bottleInMlModel.setId(bottleInMlMasterData.getId());
		bottleInMlModel.setMasterDataName(bottleInMlMasterData.getName());
		bottleInMlModel.setMasterDataTypeName(bottleInMlMasterData.getMasterDataType().getName());
		wineInRequistionResultModel.setBottleinML(bottleInMlModel);
		
		wineInRequistionResultModel.setBottlePrice(lineItemsModel.getBottlePrice());
		wineInRequistionResultModel.setCostPerBox(lineItemsModel.getCostPerBox());
		wineInRequistionResultModel.setLineItemId(lineItemsModel.getId());
		return wineInRequistionResultModel;
	}

	
	/**
	 * 
	 * @param status - status of the requisition.
	 * @return - a boolean value indicating if the status is valid or not. 
	 */
	private static Boolean isValidStatus(String status){

		Boolean statusValue = true; 
		if(POStatus.RECEIVED.toString().equalsIgnoreCase(status) || POStatus.RELEASED.toString().equalsIgnoreCase(status) || POStatus.SCHEDULED_FOR_PICKUP.toString().equalsIgnoreCase(status)){
			statusValue = false;
		}

		return statusValue;
	}

	private static Boolean statusFound(String status){

		Boolean statusValue = false; 
		if(("").equals(status) || POStatus.APPROVED.equals(status) || POStatus.SUBMITTED.equals(status)){
			statusValue = true;
		}

		return statusValue;
	}

	/**
	 * @param addWineToRequisitionPO
	 * @return 
	 */
	private static Boolean updatePurchaseOrder(RequisitionModel poModel, REQLineItemsModel purchaseOrderLineModel) {
		Boolean successfullyUpdated = false;

		poModel.setWinesCount(poModel.getBottlesCount() - 1);
		poModel.setBottlesCount(calculateWineCounts(poModel.getBottlesCount(), purchaseOrderLineModel.getQtyBottles()));
		poModel.setTotalPrice(calculateTotalPrice(poModel.getTotalPrice(), purchaseOrderLineModel.getBottlePrice(), purchaseOrderLineModel.getQtyBottles()));
		return successfullyUpdated;

	}

	private static Integer calculateWineCounts(Integer totalBottleCount, Integer wineCount){
		Integer newCount = 0;
		newCount = totalBottleCount - wineCount;
		return newCount;
	}

	private static BigDecimal calculateTotalPrice(BigDecimal oldPrice, BigDecimal unitPrice, Integer bottleCount){

		Double totalPrice = Math.ceil(bottleCount* unitPrice.longValue());
		BigDecimal newPrice = new BigDecimal(oldPrice.doubleValue() - totalPrice.doubleValue());

		return newPrice;	
	}

	/**
	 * Safely compare two dates, null being considered "greater" than a Date
	 * @return the earliest of the two
	 */
	public static Integer least(Date firstDate, Date secondDate) {
		return firstDate == null ? 2 : (secondDate == null ? 1 : (firstDate.before(secondDate) ? 1 : 2));
	}
	
	private static RequisitionDetails populateRequisitionList(RequisitionModel requisitionModel){
		
		RequisitionDetails requisitionDetails = new RequisitionDetails();
		String[] ignoreFields = {"winery", "typeOfREQ", "sourceWhAddressId", "sourceDcAddressId", "distributionCentreId", "inboundTransport"};
		BeanUtils.copyProperties(requisitionModel, requisitionDetails, ignoreFields);
		
		if(null != requisitionModel.getWinery()){
			Map<String, String> winery = new HashMap<String, String>();
			winery.put("id",requisitionModel.getWinery().getId().toString());
			winery.put("name", requisitionModel.getWinery().getWineryName());
			winery.put("wineryCode", requisitionModel.getWinery().getWineryCode());
			requisitionDetails.getWinery().putAll(winery);
		}
		
		if(null != requisitionModel.getSourceWhAddressId()){
			Map<String, String> sourceWarehouse = new HashMap<String, String>();
			sourceWarehouse.put("id",requisitionModel.getSourceWhAddressId().getId().toString());
			sourceWarehouse.put("name", requisitionModel.getSourceWhAddressId().getName());
			requisitionDetails.setSourceWhAddressId(sourceWarehouse);
		}
		
		if(null != requisitionModel.getSourceDcAddressId()){
			Map<String, String> sourceDC = new HashMap<String, String>();
			sourceDC.put("id",requisitionModel.getSourceDcAddressId().getId().toString());
			sourceDC.put("firstName", requisitionModel.getSourceDcAddressId().getFirstName());
			sourceDC.put("lastName", requisitionModel.getSourceDcAddressId().getLastName());
			requisitionDetails.setSourceDcAddressId(sourceDC);
		}
		
		if(null != requisitionModel.getDistributionCentreId()){
			Map<String, String> destinationDC = new HashMap<String, String>();
			destinationDC.put("id",requisitionModel.getDistributionCentreId().getId().toString());
			destinationDC.put("firstName", requisitionModel.getDistributionCentreId().getFirstName());
			destinationDC.put("lastName", requisitionModel.getDistributionCentreId().getLastName());
			requisitionDetails.setDistributionCentreId(destinationDC);
		}
		
		if(null != requisitionModel.getInboundTransport()){
			requisitionDetails.setInboundTransport(requisitionModel.getInboundTransport().getName());
		}
		
		return requisitionDetails;
	}
	
	public static Map<String, Object> sendEmailToWinery(final SendEmailToWineryPO sendEmailToWineryPO) {
		
		logger.info("Start sendEmailToWinery method");

		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		
		Response response;

		response = new FailureResponse();
		response.setStatus(200);	
		
		String fileName = "POforV.pdf";
		String filePath = "E:/";	
		
		try {
			//EmailNotifier.SendEmailWithAttachment(sendEmailToWineryPO.getSubject(), sendEmailToWineryPO.getContent(), sendEmailToWineryPO.getEmail(),null,filePath,fileName);
			
			SendEmailToWineryVO sendEmailToWineryVO = new SendEmailToWineryVO(SystemErrorCode.EMAIL_SUCCESS);					
			response = new com.wineaccess.response.SuccessResponse(sendEmailToWineryVO, 200);				
			
		} catch (Exception e) {
			
			response.addError(new WineaccessError(SystemErrorCode.EMAIL_FAIL,SystemErrorCode.EMAIL_FAIL_TEXT));
			logger.error("error occured during send email with attachment in sendEmailToWinery method " + e);
		}
		
		logger.info("end sendEmailToWinery method");
		output.put(OUPUT_PARAM_KEY, response);
		return output;
	}
	
}