package com.wineaccess.job.master.location;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "countryList")
public class CountryListPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -929389209793870928L;
	
	private List<CountryPO> country;

	public List<CountryPO> getCountry() {
		return country;
	}

	public void setCountry(List<CountryPO> country) {
		this.country = country;
	}
}
