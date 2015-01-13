package com.wineaccess.job.master.location;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "state")
public class StatePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4561298370335879636L;
	
	private String stateName;
	private String stateNameSort;
	private String stateCode;
	private CityListPO cities;
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateNameSort() {
		return stateNameSort;
	}
	public void setStateNameSort(String stateNameSort) {
		this.stateNameSort = stateNameSort;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public CityListPO getCities() {
		return cities;
	}
	public void setCities(CityListPO cities) {
		this.cities = cities;
	} 

}
