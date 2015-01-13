package com.wineaccess.wineryimporter;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterAddressModel;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CityRepository;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.CountryRepository;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.StateRepository;
import com.wineaccess.importer.ImporterRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.winery.WineryRepository;

/*
 * @author abhishek.sharma1
 *
 */
public class WineryImporterAddressHelper {

	private static Log logger = LogFactory.getLog(WineryImporterAddressHelper.class);
	static private Response response = new FailureResponse();

	/**
	 * @param addressPO
	 * @return
	 */
	public static Map<String, Object>  generateAddAddressResponse(WineryImporterAddressPO addressPO){

		logger.info("in add address");
		Map <String, Object> outputAddAddress = new HashMap<String,Object>();
		WineryImporterAddressModel addressDO = validateAndPopulateAddressDOFromPO(addressPO,false);

		if(response.getErrors().isEmpty() && addressDO!=null){
			WineryImporterRepository.saveAddr(addressDO);
			WineryImporterAddressBasicVO wineryImporterAddressVO = new WineryImporterAddressBasicVO(addressDO.getId(),addressDO.getWineryId(),addressDO.getImporterId(), "Address created successfully");
			response = new com.wineaccess.response.SuccessResponse(wineryImporterAddressVO, 200);
		}

		outputAddAddress.put("FINAL-RESPONSE", response);
		return outputAddAddress;
	}



	/**
	 * @param addressPO
	 * @return
	 */
	public static Map<String, Object>  generateUpdateAddressResponse(WineryImporterEditAddressPO addressPO){
		logger.info("in edit address");
		Map <String, Object> outputEditAddress = new HashMap<String,Object>();
		WineryImporterAddressModel addressDO = validateAndPopulateAddressDOFromPO(addressPO,true);

		if(response.getErrors().isEmpty() && addressDO!=null){
			addressDO.setId(addressPO.getAddressId());
			WineryImporterRepository.updateAddr(addressDO);
			WineryImporterAddressBasicVO wineryImporterAddressVO = new WineryImporterAddressBasicVO(addressDO.getId(),addressDO.getWineryId(),addressDO.getImporterId(), "Address updated successfully");
			response = new com.wineaccess.response.SuccessResponse(wineryImporterAddressVO, 200);
		}

		outputEditAddress.put("FINAL-RESPONSE", response);
		return outputEditAddress;
	}

	/**
	 * @param viewAddressPO
	 * @return
	 */
	public static Map<String, Object> generateViewAddressResponse(WineryImporterViewAddressPO viewAddressPO) {
		logger.info("in update address");
		Map <String, Object> outputViewAddress = new HashMap<String,Object>();
		response  = new FailureResponse();


		WineryImporterAddressVO addressVO = validateAndPopulateAddress(viewAddressPO);


		if(response.getErrors().isEmpty()){
			response = new com.wineaccess.response.SuccessResponse(addressVO, 200);
		}

		outputViewAddress.put("FINAL-RESPONSE", response);
		return outputViewAddress;
	}

	/**
	 * @param deleteAddressPO
	 * @return
	 */
	public static Map<String, Object> generateDeleteAddressReponse(WineryImporterDeleteAddressPO deleteAddressPO) {
		logger.info("in delete address");

		Map <String, Object> outputViewAddress = new HashMap<String,Object>();
		response = new FailureResponse();
		List<Long> deleteAddressList = deleteAddressPO.getDeleteAddressList();
		Boolean isforceDelete = deleteAddressPO.getIsforceDelete();
		try{
			if(deleteAddressList==null || deleteAddressList.isEmpty()){
				response.addError(new WineaccessError(SystemErrorCode.NO_IDS_TO_DELETE,SystemErrorCode.NO_IDS_TO_DELETE_TXT));
			}

			if(response.getErrors().isEmpty() ){
				WIDescriptionListVO userDescriptionListVO = generateModifyStatusResponse(deleteAddressList,"isDeleted", true,isforceDelete);
				response = new com.wineaccess.response.SuccessResponse(userDescriptionListVO, 200);
			}

		}
		catch (Exception e) {
			logger.error("Some error happened");
		}
		outputViewAddress.put("FINAL-RESPONSE", response);
		return outputViewAddress;
	}



	/**
	 * @param addressPO
	 * @param isEdit
	 * @return
	 */
	private static WineryImporterAddressModel validateAndPopulateAddressDOFromPO(WineryImporterAddressPO addressPO, Boolean isEdit) {

		logger.info("in validate address");
		response = new FailureResponse();
		MasterData addressTypePO = MasterDataRepository.getMasterDataById(addressPO.getAddressType());
		List<StateModel> stateModelList = StateRepository.getById(addressPO.getStateId());
		List<CityModel> cityModelList = CityRepository.getById(addressPO.getCityId());
		List<CountryModel> countryModelList = CountryRepository.getById(addressPO.getCountryId());
		WineryImporterAddressModel addressDO = new WineryImporterAddressModel();
		Long wineryId = addressPO.getWineryId();
		Long importerId = addressPO.getImporterId();
		if(isEdit)
			addressDO = WineryImporterRepository.findById(addressPO.getAddressId());
		if(addressPO.getAddressId()!=null &&  (addressDO==null || !addressPO.getAddressId().equals(addressDO.getId()))){
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST_TXT));
		}

		if(wineryId==null && importerId==null)
		{
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_IDS,SystemErrorCode.WINERY_IMPORTER_IDS_TXT));
		}
		if(wineryId!=null)
		{
			WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
			if(wineryModel== null || BooleanUtils.isTrue(wineryModel.getIsDeleted()))
				response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_WINERY_ID_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_WINERY_ID_NOT_EXIST_TXT));
		}
		else if(importerId!=null)
		{
			ImporterModel importerModel = ImporterRepository.getImporterById(importerId);
			if(importerModel== null || BooleanUtils.isTrue(importerModel.getIsDeleted()))
				response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST_TXT));
		}
		if(!StringUtils.isEmpty(addressPO.getPhone())){
			boolean isValidUserPhone = ValidationUtil.validateContentFormat(addressPO.getPhone(), "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
			if(!isValidUserPhone)
				response.addError(new WineaccessError(SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT,SystemErrorCode.INVALID_PHONE_NUMBER_FORMAT_TEXT));
		}

		if(addressTypePO==null)
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_ADDRESS_TYPE_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_ADDRESS_TYPE_NOT_EXIST_TXT));

		if(stateModelList==null || stateModelList.isEmpty())
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_STATE_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_STATE_NOT_EXIST_TXT));

		if(cityModelList==null || cityModelList.isEmpty())
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_CITY_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_CITY_NOT_EXIST_TXT));

		if(countryModelList==null || countryModelList.isEmpty())
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_COUNTRY_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_COUNTRY_NOT_EXIST_TXT));

		Boolean isAlreadyDefault = addressDO==null ? false : BooleanUtils.isTrue(addressDO.getIsDefault());
		if(response.getErrors().isEmpty()){
			if(validateIsDefault(addressPO.getIsDefault(),addressPO.getWineryId(),addressPO.getImporterId(),isAlreadyDefault))
				addressDO.setIsDefault(true);
			else
				addressDO.setIsDefault(false);
			String [] toBeIgnoredValues = {"cityId","stateId","countryId","addressType","wineryId","importerId","isDefault"};
			BeanUtils.copyProperties(addressPO,addressDO,toBeIgnoredValues);
			addressDO.setCityId(cityModelList.get(0));
			addressDO.setCountryId(countryModelList.get(0));
			addressDO.setStateId(stateModelList.get(0));
			addressDO.setAddressType(addressTypePO);
			addressDO.setWineryId(addressPO.getWineryId());
			addressDO.setImporterId(addressPO.getImporterId());

		}
		else
			addressDO = null;
		return addressDO;

	}

	/**
	 * @param viewAddressPO
	 * @return
	 */
	private static WineryImporterAddressVO validateAndPopulateAddress(
			WineryImporterViewAddressPO viewAddressPO) {

		logger.info("in validate address");
		response = new FailureResponse();
		WineryImporterAddressVO addressVO = new WineryImporterAddressVO();
		WineryImporterAddressModel wineryImporterAddressModel = null;
		Long addressId = viewAddressPO.getAddressId();
		Long wineryId = viewAddressPO.getWineryId();
		Long importerId = viewAddressPO.getImporterId();


		if(wineryId==null && importerId==null)
		{
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_IDS,SystemErrorCode.WINERY_IMPORTER_IDS_TXT));
		}
		if(wineryId!=null)
		{
			if(WineryRepository.getWineryModelById(wineryId)==null)
			{
				response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_WINERY_ID_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_WINERY_ID_NOT_EXIST_TXT));
			}
			else
				wineryImporterAddressModel = WineryImporterRepository.findByWineryIdAddrId(addressId, wineryId);	
		}
		else if(importerId!=null)
		{
			if(ImporterRepository.getImporterById(importerId)==null)
			{
				response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_IMPORTER_ID_NOT_EXIST_TXT));
			}
			else
				wineryImporterAddressModel = WineryImporterRepository.findByImporterIdAddrId(addressId, importerId);	
		}
		if(wineryImporterAddressModel==null)
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST,SystemErrorCode.WINERY_IMPORTER_ADDRESS_ID_NOT_EXIST_TXT));

		if(response.getErrors().isEmpty()){
			String [] toBeIgnoredValues = {"cityId","stateId","countryId","addressType"};
			BeanUtils.copyProperties(wineryImporterAddressModel,addressVO,toBeIgnoredValues);


			if(wineryImporterAddressModel.getStateId()!=null){
				Map<String,String> stateMap = new HashMap<String, String>();
				stateMap.put("id",wineryImporterAddressModel.getStateId().getId().toString());
				stateMap.put("value",wineryImporterAddressModel.getStateId().getStateName());
				addressVO.setStateId(stateMap);
			}

			if(wineryImporterAddressModel.getCityId()!=null){
				Map<String,String> cityMap = new HashMap<String, String>();
				cityMap.put("id",wineryImporterAddressModel.getCityId().getId().toString());
				cityMap.put("value",wineryImporterAddressModel.getCityId().getCityName());
				addressVO.setCityId(cityMap);
			}

			if(wineryImporterAddressModel.getCountryId()!=null){
				Map<String,String> countryMap = new HashMap<String, String>();
				countryMap.put("id",wineryImporterAddressModel.getCountryId().getId().toString());
				countryMap.put("value",wineryImporterAddressModel.getCountryId().getCountryName());
				addressVO.setCountryId(countryMap);
			}

			if(wineryImporterAddressModel.getAddressType()!=null){
				Map<String,String> addressTypeMap = new HashMap<String, String>();
				addressTypeMap.put("id",wineryImporterAddressModel.getAddressType().getId().toString());
				addressTypeMap.put("value",wineryImporterAddressModel.getAddressType().getName());
				addressVO.setAddressType(addressTypeMap);
			}
		}
		return addressVO;
	}


	/**
	 * @param isDefault
	 * @param wineryId
	 * @param importerId
	 * @param isAlreadyDefault
	 * @return
	 */
	private static Boolean validateIsDefault(Boolean isDefault, Long wineryId,
			Long importerId, Boolean isAlreadyDefault ) {	
		logger.info("in validateIsDefault");
		if(wineryId!=null)
		{

			List<Long> wineryImporterAddressList = WineryImporterRepository.getByWineyIdIsdefault(wineryId);
			if(wineryImporterAddressList!=null && !wineryImporterAddressList.isEmpty() )
			{

				if(BooleanUtils.isTrue(isDefault) || isAlreadyDefault){
					WineryImporterRepository.updateIsDefaultIds(wineryImporterAddressList);
				}

				else
					return false;
			}
		}

		if(importerId!=null)
		{

			List<Long> wineryImporterAddressList = WineryImporterRepository.getByImporterIdIsdefault(importerId);
			if(wineryImporterAddressList!=null && !wineryImporterAddressList.isEmpty() )
			{

				if(BooleanUtils.isTrue(isDefault)  || isAlreadyDefault){
					WineryImporterRepository.updateIsDefaultIds(wineryImporterAddressList);
				}
				else
					return false;

			}

		}


		return true;
	}

	/**
	 * @param addressList
	 * @param fieldName
	 * @param status
	 * @param isForceDelete
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static WIDescriptionListVO generateModifyStatusResponse(List<? extends Serializable> addressList, String fieldName, boolean status, Boolean isForceDelete) throws IllegalAccessException,
	InvocationTargetException {
		GenericDAO<WineryImporterAddressModel> genericDAO = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");

		BulkDeleteModel<WineryImporterAddressModel> bulkDeleteModel = null;
		try {
			bulkDeleteModel = genericDAO.performBulkDelete(addressList, WineryImporterAddressModel.class, "WineryImporterAddressModel",fieldName, status,"isDefault",isForceDelete);
		} 
		catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<WineryImporterAddressModel>) (persistenceException
						.getData());
			}
		} 

		WIDescriptionListVO wiDescriptionListVO = new WIDescriptionListVO();


		List<WineryImporterAddressModel> canBeDeletedAddrList = bulkDeleteModel.getDeletedList();
		List<WineryImporterAddressBasicVO> addressServiceModelList = new ArrayList<WineryImporterAddressBasicVO>();

		for(WineryImporterAddressModel addressModelObject: canBeDeletedAddrList){


			WineryImporterAddressBasicVO addrSeriveModel = new WineryImporterAddressBasicVO();
			BeanUtils.copyProperties( addressModelObject,addrSeriveModel);
			addrSeriveModel.setAddressId(addressModelObject.getId());
			addressServiceModelList.add(addrSeriveModel);
			String completeAddress = extractCompleteAddress(addressModelObject);

			addrSeriveModel.setAddress_Type(addressModelObject.getAddressType().getName());
			addrSeriveModel.setAddress(completeAddress);
			addrSeriveModel.setPhone(addressModelObject.getPhone());
		}
		wiDescriptionListVO.setSuccessList(addressServiceModelList);

		addressServiceModelList = new ArrayList<WineryImporterAddressBasicVO>();
		List<WineryImporterAddressModel> canNotBeDeletedUserList = bulkDeleteModel.getNotDeletedList();
		for(WineryImporterAddressModel addressModelObject: canNotBeDeletedUserList){
			WineryImporterAddressBasicVO addressServiceModel = new WineryImporterAddressBasicVO();
			BeanUtils.copyProperties( addressModelObject,addressServiceModel);
			addressServiceModel.setAddressId(addressModelObject.getId());
			addressServiceModelList.add(addressServiceModel);
			String completeAddress = extractCompleteAddress(addressModelObject);

			addressServiceModel.setAddress_Type(addressModelObject.getAddressType().getName());
			addressServiceModel.setAddress(completeAddress);
			addressServiceModel.setPhone(addressModelObject.getPhone());
		}
		wiDescriptionListVO.setFailureList(addressServiceModelList);

		wiDescriptionListVO.setNonExisting((List<Long>) bulkDeleteModel.getNotExistsList());

		return wiDescriptionListVO;
	}



	/**
	 * @param listAddressPO
	 * @return
	 */
	public static Map<String, Object> generateListAddressResponse(
			WIAddressListingPO listAddressPO) {

		Long wineryId = listAddressPO.getWineryId();
		Long importerId = listAddressPO.getImporterId();
		Map <String, Object> outputListAddress = new HashMap<String,Object>();
		response = new FailureResponse();
		WineryImporterAddressModel wineryImporterAddressModel = null;

		if(wineryId==null && importerId==null)
		{
			response.addError(new WineaccessError(SystemErrorCode.WINERY_IMPORTER_IDS,SystemErrorCode.WINERY_IMPORTER_IDS_TXT));
		}

		if(wineryId!=null)
		{
			wineryImporterAddressModel = WineryImporterRepository.findByWineryId( wineryId);	
		}

		else if(importerId!=null)
		{
			wineryImporterAddressModel = WineryImporterRepository.findByImporterId(importerId);	
		}

		if(wineryImporterAddressModel==null)
			response.addError(new WineaccessError(SystemErrorCode.NO_RECORDS_EXIST,SystemErrorCode.NO_RECORDS_EXIST_TXT));

		if(response.getErrors().isEmpty()){

			int size = WineryImporterRepository.countRecordsForQuery(listAddressPO.getKeyword(),listAddressPO.getWineryId(), listAddressPO.getImporterId(),listAddressPO.getAddressType());

			int totalCount = WineryImporterRepository.countRecordsForQuery(listAddressPO.getWineryId(), listAddressPO.getImporterId(),listAddressPO.getAddressType());


			List<WineryImporterAddressModel> addressList = WineryImporterRepository.getAddressDetailList(listAddressPO.getKeyword(), Integer.parseInt(listAddressPO.getSortOrder()), 
					Integer.parseInt(listAddressPO.getOffSet())-1, Integer.parseInt(listAddressPO.getLimit()),listAddressPO.getSortBy(),listAddressPO.getWineryId(), listAddressPO.getImporterId(),listAddressPO.getAddressType());

			List<WIAddressResultModel> wineryImporteraddressModel = new ArrayList<WIAddressResultModel>();

			for (WineryImporterAddressModel address : addressList) {


				String completeAddress = extractCompleteAddress(address);
				Address wiAddress = new Address(address.getAddressLine1(), address.getAddressLine2(), address.getCityId(),address.getStateId(), address.getCountryId(), address.getZipcode());
				WIAddressResultModel addressDetailVO = new WIAddressResultModel();
				addressDetailVO.setAddressType(address.getAddressType().getName());
				addressDetailVO.setAddress(completeAddress);
				addressDetailVO.setPhone(address.getPhone());
				addressDetailVO.setId(address.getId());
				addressDetailVO.setIsDefault(address.getIsDefault());
				addressDetailVO.setAddressDetails(wiAddress);
				ContactDetails contactDetails = new ContactDetails(address.getFirstName(), address.getLastName(), address.getPhone());
				addressDetailVO.setContactDetails(contactDetails);
				wineryImporteraddressModel.add(addressDetailVO);

			}

			WISearchVO addressListVO = new WISearchVO();
			addressListVO.setCount(size);
			addressListVO.setTotalRecordCount(totalCount);
			addressListVO.setKeyword(listAddressPO.getKeyword());
			addressListVO.setOffSet(Integer.parseInt(listAddressPO.getOffSet()));
			addressListVO.setLimit(Integer.parseInt(listAddressPO.getLimit()));

			addressListVO.setAddressDetail(wineryImporteraddressModel);
			response = new com.wineaccess.response.SuccessResponse(addressListVO, 200);

		}
		outputListAddress.put("FINAL-RESPONSE", response);
		return outputListAddress;
	}



	/**
	 * @param address
	 * @return
	 */
	private static String extractCompleteAddress(WineryImporterAddressModel address) {

		try{

			StringBuilder completeAddress = new StringBuilder();
			completeAddress.append(address.getAddressLine1());
			completeAddress.append(", ");

			if(address.getAddressLine2()!=null){
				completeAddress.append(address.getAddressLine2());
				completeAddress.append(", ");
			}

			completeAddress.append(address.getCityId().getCityName());
			completeAddress.append(", ");

			completeAddress.append(address.getStateId().getStateName());
			completeAddress.append(", ");

			completeAddress.append(address.getCountryId().getCountryName());
			completeAddress.append(" ");

			completeAddress.append(address.getZipcode());
			//completeAddress.append(", ");
			return completeAddress.toString();
		}
		catch (Exception e) {
			return null;
		}
	}



}
