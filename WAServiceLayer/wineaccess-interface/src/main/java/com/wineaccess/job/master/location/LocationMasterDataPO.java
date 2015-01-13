package com.wineaccess.job.master.location;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "locationMasterData")
public class LocationMasterDataPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3469490866030800372L;
	
	private CountryListPO countryList;
	private MasterCityDataPO masterCityData;
	
	public CountryListPO getCountryList() {
		return countryList;
	}
	public void setCountryList(CountryListPO countryList) {
		this.countryList = countryList;
	}
	public MasterCityDataPO getMasterCityData() {
		return masterCityData;
	}
	public void setMasterCityData(MasterCityDataPO masterCityData) {
		this.masterCityData = masterCityData;
	}
	

}
