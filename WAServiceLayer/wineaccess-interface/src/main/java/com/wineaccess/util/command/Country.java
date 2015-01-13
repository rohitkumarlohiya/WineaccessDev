package com.wineaccess.util.command;

import java.io.Serializable;


public class Country implements Serializable {
	
	private static final long serialVersionUID = 2741338308290178586L;
	private String name;
	private Long id;
	private String countryCode;
	
	public Country(){
		
	}

	public Country(String countryCode, String countryName, Long id) {
		
		this.countryCode = countryCode;
		this.name = countryName;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	

}
