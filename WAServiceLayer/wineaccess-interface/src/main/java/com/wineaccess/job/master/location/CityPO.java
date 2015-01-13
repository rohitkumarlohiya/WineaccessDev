package com.wineaccess.job.master.location;

import java.io.Serializable;

public class CityPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -237216148885266873L;
	
	private String cityName;
	private String cityCode;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
