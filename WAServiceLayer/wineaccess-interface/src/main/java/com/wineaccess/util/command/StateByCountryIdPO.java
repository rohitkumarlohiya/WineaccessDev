package com.wineaccess.util.command;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="country")
public class StateByCountryIdPO extends BasePO {

	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "INVALID_COUNTRY_ID")
	private String countryId;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
}
