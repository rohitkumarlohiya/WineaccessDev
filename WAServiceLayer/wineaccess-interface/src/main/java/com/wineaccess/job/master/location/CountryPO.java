package com.wineaccess.job.master.location;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "country")
public class CountryPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8610831197943596403L;
	
	private String countryName;
	private String countryNameSort;
	private String countryCode;
	private List<StatePO> state;
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryNameSort() {
		return countryNameSort;
	}
	public void setCountryNameSort(String countryNameSort) {
		this.countryNameSort = countryNameSort;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public List<StatePO> getState() {
		return state;
	}
	public void setState(List<StatePO> state) {
		this.state = state;
	}
	

}
