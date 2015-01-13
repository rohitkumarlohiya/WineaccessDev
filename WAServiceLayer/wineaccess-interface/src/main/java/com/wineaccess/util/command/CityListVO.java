package com.wineaccess.util.command;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CityListVO implements Serializable {
	
	private static final long serialVersionUID = 2447308669831397125L;

	@XmlElementWrapper(name = "cityList")
	@XmlElement(name = "city")
	private List<City> cities = null;
	
	public CityListVO() {
		super();
	}

	public CityListVO(List<City> cities) {
		super();
		this.cities = cities;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

