package com.wineaccess.job.master.location;

import java.io.Serializable;
import java.util.List;

public class CityListPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 951926199524777326L;
	
	private List<String> cityCode;

	public List<String> getCityCode() {
		return cityCode;
	}

	public void setCityCode(List<String> cityCode) {
		this.cityCode = cityCode;
	}

}
