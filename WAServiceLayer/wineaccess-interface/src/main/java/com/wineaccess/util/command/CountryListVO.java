package com.wineaccess.util.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryListVO implements Serializable {
	
	private static final long serialVersionUID = 2447308669831397125L;

	@XmlElementWrapper(name = "countryList")
	@XmlElement(name = "country")
	private List<Country> countries = null;
	
	
	public CountryListVO(List<Country> countries) {
		super();
		this.countries = countries;
	}

	public CountryListVO(){
		countries = new ArrayList<Country>();
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
