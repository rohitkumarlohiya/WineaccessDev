package com.wineaccess.util.command;


import java.io.Serializable;

public class City implements Serializable {
	
	private static final long serialVersionUID = 2741338308290178586L;
	private String name;
	private Long id;
	private String cityCode;
	
	public City(){
		
	}

	public City(String cityCode, String cityName, Long id) {
		name = cityName;
		this.cityCode = cityCode;
		this.id=id;
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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
