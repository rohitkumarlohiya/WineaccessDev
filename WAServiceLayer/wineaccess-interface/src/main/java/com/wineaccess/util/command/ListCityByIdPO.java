package com.wineaccess.util.command;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="city")
public class ListCityByIdPO extends BasePO {

	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "CITY_ERROR_NOT_LONG")
	private String cityId;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
