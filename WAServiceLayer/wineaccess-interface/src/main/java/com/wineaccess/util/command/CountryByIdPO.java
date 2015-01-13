package com.wineaccess.util.command;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="country")
public class CountryByIdPO extends BasePO {

	private String countryId;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
}
