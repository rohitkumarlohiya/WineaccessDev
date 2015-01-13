package com.wineaccess.util.command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.CountryRepository;
import com.wineaccess.response.Response;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class CountryListAdapterHelper { 
	

	/**
	 * 
	 * @return -list of countries in database
	 */
	public static Map<String, Object> listCountry() {		
		

		List<CountryModel> countryModelList = CountryRepository.getAll();
		
		List<Country> countryList = new ArrayList<Country>();
		for(Object o: countryModelList){
			CountryModel countryModel = (CountryModel)o;
			Country countryObject = new Country();
			countryObject.setId(countryModel.getId());
			countryObject.setName(countryModel.getCountryName());
			countryObject.setCountryCode(countryModel.getCountryCode());
			countryList.add(countryObject);
		}

		Map<String, Object> output = new HashMap<String, Object>();

		CountryListVO countryListVO = new CountryListVO(countryList);

		Response response = new com.wineaccess.response.SuccessResponse(
				countryListVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	/**
	 * 
	 * @return -list of country by id in database
	 */
	public static Map<String, Object> listCountryById(String countryId) {		
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		if(!StringUtils.isNumeric(countryId)){
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.COUNTRY_INVALID_PARAM_ERROR, WineaccessErrorCodes.SystemErrorCode.COUNTRY_INVALID_PARAM_ERROR_TEXT, 200);			
		} else {
			
			List<CountryModel> countryModelList = CountryRepository.getById(Long.parseLong(countryId));
			
			if (countryModelList.isEmpty()) {
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.COUNTRY_NOT_FOUND, WineaccessErrorCodes.SystemErrorCode.COUNTRY_NOT_FOUND_TEXT, 200);
			} else {
				List<Country> countryList = new ArrayList<Country>();
				for(Object o: countryModelList){
					CountryModel countryModel = (CountryModel)o;
					Country countryObject = new Country();
					countryObject.setId(countryModel.getId());
					countryObject.setName(countryModel.getCountryName());
					countryObject.setCountryCode(countryModel.getCountryCode());
					countryList.add(countryObject);
				}

				CountryListVO countryListVO = new CountryListVO(countryList);
				response = new com.wineaccess.response.SuccessResponse(countryListVO, 200);
			}
		}
		
		output.put("FINAL-RESPONSE", response);

		return output;
	}
}

