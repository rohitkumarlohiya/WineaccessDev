package com.wineaccess.model.json.generator;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;

public class UserModelJsonGenerator {

	public static UserModel generateData(int identifier) {

		
		UserModel userModel = new UserModel();

		userModel.setFirstName("First" + identifier);
		userModel.setLastName("Last" + identifier);
		userModel.setEmail(PropertyholderUtils.getStringProperty(PropertyConstants.ADMIN_EMAIL));
		userModel.setPassword(ApplicationUtils.getSHAHex("Global@123#"));
		userModel.setEnabled(true);
		userModel.setRegistered(true);
		userModel.setSalutation("Mr.");
		userModel.setGender("Male");
		userModel.setStateId(1L);
		userModel.setChannelId(1L);
		
		return userModel;
	}
	
	public static CountryModel generateCountryData(String countryName, String countryCode) {

		CountryModel countryModel = new CountryModel(countryName,countryCode);
		return countryModel;
	}
	
	public static StateModel generateStateData(String stateName, String stateCode,CountryModel countryModel) {

		StateModel stateModel = new StateModel(stateName, stateCode, countryModel);
		return stateModel;
	}
	
	public static CityModel generateCityData(String cityName, String cityCode) {

		CityModel cityModel = new CityModel(cityName,cityCode);
		return cityModel;
	}
}
