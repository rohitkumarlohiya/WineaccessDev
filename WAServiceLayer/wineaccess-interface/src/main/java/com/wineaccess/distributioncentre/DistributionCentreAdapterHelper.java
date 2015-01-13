package com.wineaccess.distributioncentre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.common.DeleteVO;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.EnumTypes;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CityRepository;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.CountryRepository;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.StateRepository;
import com.wineaccess.distributioncentre.DistributionCentre;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.response.Response;

/**
 * @author gaurav.agarwal1
 * 
 */
public class DistributionCentreAdapterHelper {

	private static Log logger = LogFactory.getLog(DistributionCentreAdapterHelper.class);
	public static final String OUPUT_PARAM_KEY = "FINAL-RESPONSE";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String CODE = "code";

	/**
	 * this method is to create the distribution centre warehouse location
	 * @param distributionCentrePO
	 * return map the output map
	 */
	public static Map<String, Object> addDistributionCentre(final AddDistributionCentrePO distributionCentrePO) {

		logger.info("creating the distribution centre warehouse location");
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		if(distributionCentrePO.getFaxNumber().isEmpty()){
			distributionCentrePO.setFaxNumber(null);
		}
		AddDistributionCentreVO distributionCentreVO;
		
		String uniqueHash = checkUniqueDistributionCentre(distributionCentrePO);
		DistributionCentre distributionCentre = DistributionCentreRepository.getByUniqueAddressHash(uniqueHash);
		if (distributionCentre != null) {
			if (BooleanUtils.isTrue(distributionCentre.getIsDeleted())) {

				Boolean isOverridedel = BooleanUtils.toBoolean(distributionCentrePO.getIsOverrideDelEntry());

				if (BooleanUtils.isFalse(isOverridedel)) {
					distributionCentreVO = new AddDistributionCentreVO(distributionCentre.getId(), true,"Distribution Centre already exists in deleted state.");
					response = new com.wineaccess.response.SuccessResponse(distributionCentreVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				} else {

					List<CityModel> cities = CityRepository.getById(Long.parseLong(distributionCentrePO.getCityId()));
					if (cities != null && !cities.isEmpty()) {
						CountryModel country = CountryRepository.getByCountryCode(EnumTypes.CountryCode.USA.toString());
						List<StateModel> states = StateRepository.getById(Long.parseLong(distributionCentrePO.getStateId()));
						if (states != null && !states.isEmpty()) {
							if (country.getId() == states.get(0).getCountry().getId()) {
								populateDistributionCentre(distributionCentrePO,distributionCentre);
								distributionCentre.setIsDeleted(false);
								distributionCentre.setStateId(states.get(0));
								distributionCentre.setCityId(cities.get(0));
								
								DistributionCentreRepository.update(distributionCentre);

								distributionCentreVO = new AddDistributionCentreVO(distributionCentre.getId(),SystemErrorCode.DISTRIBUTION_CENTRE_ADD_SUCCESS_TEXT);
								response = new com.wineaccess.response.SuccessResponse(distributionCentreVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
							} else {
								response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DISTRIBUTION_CENTRE_ERROR_115,
												SystemErrorCode.DISTRIBUTION_CENTRE_ERROR_115_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
							}
						} else {
							response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_116,
											SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_116_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
						}
					} else {
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_117,
										SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_117_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					}
				}
			} else {
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_124,
						SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_124_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		} else {

			List<CityModel> cities = CityRepository.getById(Long.parseLong(distributionCentrePO.getCityId()));
			if (cities != null && !cities.isEmpty()) {
				CountryModel country = CountryRepository.getByCountryCode(EnumTypes.CountryCode.USA.toString());
				List<StateModel> states = StateRepository.getById(Long.parseLong(distributionCentrePO.getStateId()));
				if (states != null && !states.isEmpty()) {
					if (country.getId() == states.get(0).getCountry().getId()) {
						final DistributionCentre distributionCentreLocation = new DistributionCentre();
						populateDistributionCentre(distributionCentrePO,distributionCentreLocation);
						distributionCentreLocation.setStateId(states.get(0));
						distributionCentreLocation.setCityId(cities.get(0));
						distributionCentreLocation.setUniqueAddressHash(uniqueHash);

						DistributionCentreRepository.save(distributionCentreLocation);

						distributionCentreVO = new AddDistributionCentreVO(distributionCentreLocation.getId(),SystemErrorCode.DISTRIBUTION_CENTRE_ADD_SUCCESS_TEXT);
						response = new com.wineaccess.response.SuccessResponse(distributionCentreVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					} else {
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DISTRIBUTION_CENTRE_ERROR_115,
										SystemErrorCode.DISTRIBUTION_CENTRE_ERROR_115_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					}
				} else {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_116,
									SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_116_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}
			} else {
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_117,
								SystemErrorCode.ADD_DISTRIBUTION_CENTRE_ERROR_117_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		}

		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit from creating distribution centre warehouse location");

		return output;
	}

	/**
	 * @param centreLocationDetails
	 * @param distributionCentre
	 */
	private static void populateDistributionCentre(final DistributionCentreDetails centreLocationDetails, final DistributionCentre distributionCentre) {

		distributionCentre.setFirstName(centreLocationDetails.getFirstName());
		distributionCentre.setLastName(centreLocationDetails.getLastName());
		distributionCentre.setAddressLine1(centreLocationDetails.getAddressLine1());
		distributionCentre.setAddressLine2(centreLocationDetails.getAddressLine2());
		distributionCentre.setZipcode(centreLocationDetails.getZipcode());
		distributionCentre.setEmailId(centreLocationDetails.getEmailId());
		distributionCentre.setPhone(centreLocationDetails.getPhone());
		distributionCentre.setFaxNumber(centreLocationDetails.getFaxNumber());
	}

	/**
	 * this method is to update the distribution centre warehouse location
	 * @param distributionCentrePO
	 * return map the output map
	 */
	public static Map<String, Object> updateDistributionCentre(final UpdateDistributionCentrePO distributionCentrePO) {

		logger.info("updating the distribution centre warehouse location");
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;
		
		if(distributionCentrePO.getFaxNumber().isEmpty()){
			distributionCentrePO.setFaxNumber(null);
		}
		final DistributionCentre distributionCentre = DistributionCentreRepository.getById(Long.parseLong(distributionCentrePO.getId()));
		if (distributionCentre != null) {

			List<CityModel> cities = CityRepository.getById(Long.parseLong(distributionCentrePO.getCityId()));
			if (cities != null && !cities.isEmpty()) {
				CountryModel country = CountryRepository.getByCountryCode(EnumTypes.CountryCode.USA.toString());
				List<StateModel> states = StateRepository.getById(Long.parseLong(distributionCentrePO.getStateId()));
				if (states != null && !states.isEmpty()) {
					if (country.getId() == states.get(0).getCountry().getId()) {
						populateDistributionCentre(distributionCentrePO,distributionCentre);
						distributionCentre.setStateId(states.get(0));
						distributionCentre.setCityId(cities.get(0));
						
						String uniqueHash = updateNonUniqueDistributionCentre(distributionCentre);
						DistributionCentre locationModel = DistributionCentreRepository.getByUniqueAddressHash(uniqueHash);
						if(locationModel == null || locationModel.getId() == Long.parseLong(distributionCentrePO.getId())) {
							distributionCentre.setUniqueAddressHash(uniqueHash);
							DistributionCentreRepository.update(distributionCentre);
		
							UpdateDistributionCentreVO shippingLocationVO = new UpdateDistributionCentreVO(distributionCentre.getId(),
									SystemErrorCode.DISTRIBUTION_CENTRE_UPDATE_SUCCESS_TEXT);
							response = new com.wineaccess.response.SuccessResponse(shippingLocationVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
							
						}else{
							response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_125,
									SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_125_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
						}
					} else {
						response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DISTRIBUTION_CENTRE_ERROR_115,
										SystemErrorCode.DISTRIBUTION_CENTRE_ERROR_115_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
					}
				} else {
					response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_118,
									SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_118_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
				}
			} else {
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_119,
								SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_119_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		} else {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_120,
					SystemErrorCode.UPDATE_DISTRIBUTION_CENTRE_ERROR_120_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}

		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit from updating distribution centre warehouse location");

		return output;
	}

	/**
	 * this method is to view the distribution centre warehouse location
	 * @param locationPO
	 * return map the output map
	 */
	public static Map<String, Object> viewDistributionCentre(final ViewDistributionCentrePO locationPO) {

		logger.info("view details of the distribution centre warehouse location");
		final Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		final DistributionCentre distributionCentre = DistributionCentreRepository.getById(Long.parseLong(locationPO.getId()));
		if (distributionCentre != null) {

			ViewDistributionCentreVO locationVO = populateDetailsFromModel(distributionCentre);

			response = new com.wineaccess.response.SuccessResponse(locationVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());

		} else {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.VIEW_DISTRIBUTION_CENTRE_ERROR_123,
					SystemErrorCode.VIEW_DISTRIBUTION_CENTRE_ERROR_123_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}

		output.put(OUPUT_PARAM_KEY, response);
		logger.info("exit from view details of distribution centre warehouse location");

		return output;
	}

	/**
	 * This method is used to perform the delete operation of shipping locations
	 * @param deleteLocationPO take the input parameter for deleting the locations
	 * @return Map the output map
	 */
	public static Map<String, Object> deleteDistributionCentre(final DeleteDistributionCentrePO deleteLocationPO) {

		logger.info("deleting the locations based on ids");

		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		List<Long> ids = deleteLocationPO.getIds();
		for(Long id : ids){
			if(id == null){
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DELETE_DISTRIBUTION_CENTRE_ERROR_131,
						SystemErrorCode.DELETE_DISTRIBUTION_CENTRE_ERROR_131_TEXT,RESPONSECODES.SUCCESSCODE.getResponseCodes());
			}
		}
		if(response == null){
			
			Boolean isForceDelete = false;
			if (StringUtils.isNotBlank(deleteLocationPO.getIsForceDelete()))
				isForceDelete = Boolean.parseBoolean(deleteLocationPO.getIsForceDelete());
	
			BulkDeleteModel<DistributionCentre> bulkDeleteModel = DistributionCentreRepository.delete(ids, isForceDelete);
	
			DeleteVO<ViewDistributionCentreVO> locationDetailsForDependency = new DeleteVO<ViewDistributionCentreVO>();
			DeleteVO<ViewDistributionCentreVO> locationDetailsForDeleted = new DeleteVO<ViewDistributionCentreVO>();
	
			List<ViewDistributionCentreVO> deleteList = new ArrayList<ViewDistributionCentreVO>();
			List<ViewDistributionCentreVO> dependencyList = new ArrayList<ViewDistributionCentreVO>();
	
			List<DistributionCentre> canBeDeletedList = bulkDeleteModel.getDeletedList();
	
			for (DistributionCentre performanceCentreLocation : canBeDeletedList) {
				ViewDistributionCentreVO shippingLocation = populateDetailsFromModel(performanceCentreLocation);
				deleteList.add(shippingLocation);
			}
	
			locationDetailsForDeleted.setElements(deleteList);
	
			List<DistributionCentre> canNotBeDeletedList = bulkDeleteModel.getNotDeletedList();
	
			for (DistributionCentre performanceCentreLocation : canNotBeDeletedList) {
				ViewDistributionCentreVO shippingLocation = populateDetailsFromModel(performanceCentreLocation);
				dependencyList.add(shippingLocation);
			}
	
			locationDetailsForDependency.setElements(dependencyList);
	
			List<Long> nonExistingList = (List<Long>) bulkDeleteModel.getNotExistsList();
	
			DeleteDistributionCentreVO shippingLocationVO = new DeleteDistributionCentreVO();
			shippingLocationVO.setNonExistsList(nonExistingList);
			shippingLocationVO.setFailureList(locationDetailsForDependency);
			shippingLocationVO.setSuccessList(locationDetailsForDeleted);
	
			response = new com.wineaccess.response.SuccessResponse(shippingLocationVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		}
		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit deleting the locations based on ids");

		return output;

	}

	/**
	 * populate the details from model to add in the list
	 * @param distributionCentre
	 * @return
	 */
	public static ViewDistributionCentreVO populateDetailsFromModel(final DistributionCentre distributionCentre) {

		final ViewDistributionCentreVO shippingLocation = new ViewDistributionCentreVO();

		shippingLocation.setId(distributionCentre.getId());
		shippingLocation.setFirstName(distributionCentre.getFirstName());
		shippingLocation.setLastName(distributionCentre.getLastName());
		shippingLocation.setAddressLine1(distributionCentre.getAddressLine1());
		shippingLocation.setAddressLine2(distributionCentre.getAddressLine2());

		Map<String, String> cityMap = new HashMap<String, String>();
		if (distributionCentre.getCityId() != null) {
			cityMap.put(ID, Long.toString(distributionCentre.getCityId().getId()));
			cityMap.put(NAME, distributionCentre.getCityId().getCityName());
			cityMap.put(CODE, distributionCentre.getCityId().getCityCode());
		}
		shippingLocation.setCity(cityMap);

		Map<String, String> stateMap = new HashMap<String, String>();
		if (distributionCentre.getStateId() != null) {
			stateMap.put(ID, Long.toString(distributionCentre.getStateId().getId()));
			stateMap.put(NAME, distributionCentre.getStateId().getStateName());
			stateMap.put(CODE, distributionCentre.getStateId().getStateCode());
		}
		shippingLocation.setState(stateMap);

		shippingLocation.setZipcode(distributionCentre.getZipcode());
		shippingLocation.setEmailId(distributionCentre.getEmailId());
		shippingLocation.setPhone(distributionCentre.getPhone());
		shippingLocation.setFaxNumber(distributionCentre.getFaxNumber());

		return shippingLocation;
	}

	/**
	 * this method is used to check the uniqueness of shipping location during
	 * add functionality
	 * 
	 */
	private static String checkUniqueDistributionCentre(final AddDistributionCentrePO addDistributionCentrePO) {

		String uniqueLocation = ApplicationUtils.generateUniqueHash(
				addDistributionCentrePO.getAddressLine1(),
				addDistributionCentrePO.getCityId(),
				addDistributionCentrePO.getStateId(),
				addDistributionCentrePO.getZipcode(),
				addDistributionCentrePO.getPhone(),
				addDistributionCentrePO.getEmailId());

		return uniqueLocation;
	}
	
	/**
	 * this method is used to check the uniqueness of distribution centre during update
	 * add functionality
	 * 
	 */
	private static String updateNonUniqueDistributionCentre(final DistributionCentre distributionCentre) {

		String uniqueLocation = ApplicationUtils.generateUniqueHash(
				distributionCentre.getAddressLine1(),
				distributionCentre.getCityId().getId(),
				distributionCentre.getStateId().getId(),
				distributionCentre.getZipcode(),
				distributionCentre.getPhone(),
				distributionCentre.getEmailId());

		return uniqueLocation;
	}
	
	/**
	 * this method is used to list the performance centre warehouse locations
	 * @param listingPO
	 * @return Map the output map
	 */
	public static Map<String, Object> generateListDCWarehouseLocations(DistributionCentreListingPO listingPO) {

		logger.info("listing the distribution centre warehouse locations");

		Map<String, Object> output = new ConcurrentHashMap<String, Object>();
		Response response = null;

		List<DistributionCentre> performanceCentreLocations = null;
		int totalCount = DistributionCentreRepository.getTotalCount();
		DistributionCentreListingVO listingVO = new DistributionCentreListingVO();
		listingVO.setTotalRecordCount(totalCount);
		listingVO.setKeyword(listingPO.getKeyword());
		listingVO.setLimit(Integer.parseInt(listingPO.getLimit()));
		listingVO.setOffSet(Integer.parseInt(listingPO.getOffSet()));

		List<ViewDistributionCentreVO> wsWarehouseLocationList = new ArrayList<ViewDistributionCentreVO>();

		
			performanceCentreLocations = DistributionCentreRepository.getAllByOffsetLimit(null,Integer.parseInt(listingPO.getOffSet()) - 1,
							Integer.parseInt(listingPO.getLimit()));
			if (performanceCentreLocations != null) {
				for (DistributionCentre location : performanceCentreLocations) {
					ViewDistributionCentreVO locationVO = populateDetailsFromModel(location);
					wsWarehouseLocationList.add(locationVO);
				}
			}
		

		listingVO.setCount(wsWarehouseLocationList.size());
		listingVO.setDcWarehouseLocationList(wsWarehouseLocationList);

		response = new com.wineaccess.response.SuccessResponse(listingVO,RESPONSECODES.SUCCESSCODE.getResponseCodes());
		output.put(OUPUT_PARAM_KEY, response);

		logger.info("exit listing the distribution centre warehouse locations");

		return output;
	}

}
