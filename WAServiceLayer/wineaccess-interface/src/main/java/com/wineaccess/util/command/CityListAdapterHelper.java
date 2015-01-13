package com.wineaccess.util.command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CityRepository;
import com.wineaccess.response.Response;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class CityListAdapterHelper { 
	

	/**
	 * 
	 * @return -list of cities in database
	 */
	public static Map<String, Object> listCity() {		
		

		List<CityModel> cityModelList = CityRepository.getAll();
		
		List<City> cityList = new ArrayList<City>();
		for(Object o: cityModelList){
			CityModel cityModel = (CityModel)o;
			City cityObject = new City();
			cityObject.setId(cityModel.getId());
			cityObject.setName(cityModel.getCityName());
			cityObject.setCityCode(cityModel.getCityCode());
			cityList.add(cityObject);
		}

		Map<String, Object> output = new HashMap<String, Object>();

		CityListVO cityListVO = new CityListVO(cityList);

		Response response = new com.wineaccess.response.SuccessResponse(
				cityListVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	/**
	 * 
	 * @return -list of city in database based on city id 
	 */
	public static Map<String, Object> listCityById(String cityId) {		
		
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		if(!StringUtils.isNumeric(cityId)){
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CITY_INVALID_PARAM_ERROR, WineaccessErrorCodes.SystemErrorCode.CITY_INVALID_PARAM_ERROR_TEXT, 200);			
		}
		else{
			List<CityModel> cityModelList = CityRepository.getById(Long.parseLong(cityId));
			
			if (cityModelList.isEmpty()) {
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CITY_NOT_FOUND, WineaccessErrorCodes.SystemErrorCode.CITY_NOT_FOUND_TEXT, 200);
			} else {
				List<City> cityList = new ArrayList<City>();
				for(Object o: cityModelList){
					CityModel cityModel = (CityModel)o;
					City cityObject = new City();
					cityObject.setId(cityModel.getId());
					cityObject.setName(cityModel.getCityName());
					cityObject.setCityCode(cityModel.getCityCode());
					cityList.add(cityObject);
				}

				CityListVO cityListVO = new CityListVO(cityList);
				response = new com.wineaccess.response.SuccessResponse(cityListVO, 200);
			}
			
		}
		
		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	/**
	 * 
	 * @return -list of city in database based on state id 
	 */
	public Map<String, Object> listCityByStateId(String stateId) {		
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		if(null != stateId){
			if(StringUtils.isNumeric(stateId)){
				List<CityModel> cityModelList = CityRepository.getByStateId(Long.parseLong(stateId));
				List<City> cityList = new ArrayList<City>();
				for(Object o: cityModelList){
					CityModel cityModel = (CityModel)o;
					City cityObject = new City();
					cityObject.setId(cityModel.getId());
					cityObject.setName(cityModel.getCityName());
					cityObject.setCityCode(cityModel.getCityCode());
					if(!isCityAlreadyAdded(cityList,cityObject)){
						cityList.add(cityObject);
					}
				}

				CityListVO cityListVO = new CityListVO(cityList);

				response = new com.wineaccess.response.SuccessResponse(
						cityListVO, 200);
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CITY_INVALID_PARAM_ERROR, WineaccessErrorCodes.SystemErrorCode.CITY_INVALID_PARAM_ERROR_TEXT, 200);
			}
		}
		else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.CITY_MANDATORY_FIELD_ERROR, WineaccessErrorCodes.SystemErrorCode.CITY_MANDATORY_FIELD_ERROR_TEXT, 200);
		}
		
		output.put("FINAL-RESPONSE", response);

		return output;
	}

	private boolean isCityAlreadyAdded(List<City> cityList, City cityObject) {
		boolean isCityAdded = false;
		if(!CollectionUtils.isEmpty(cityList)){
			for(City city :cityList){
				if(city.getName().equalsIgnoreCase(cityObject.getName())){
					isCityAdded = true;
					break;
				}
			}
		}
		return isCityAdded;
	}
}
