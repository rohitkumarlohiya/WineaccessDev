package com.wineaccess.job.master.location;

import java.io.Serializable;
import java.util.List;

public class MasterCityDataPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139372895294573647L;
	
	private List<CityPO> city;

	public List<CityPO> getCity() {
		return city;
	}

	public void setCity(List<CityPO> city) {
		this.city = city;
	}

}
