package com.wineaccess.wineryimporter;

import javax.xml.bind.annotation.XmlType;

import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.util.command.City;
import com.wineaccess.util.command.Country;
import com.wineaccess.util.command.State;

@XmlType(name="wineryImporterAddress")
public class Address {
	
	private String addressLine1;
	private String addressLine2;
	private City city;
	private State state;
	private String zipCode;
	private Country country;
	
	public Address(){
		
	}
	
	public Address(String addressLine1, String addressLine2,
			CityModel city, StateModel state, CountryModel country,
			String zipcode) {
		
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipcode;
		if(city != null)
			this.city = new City(city.getCityCode(),city.getCityName(),city.getId());
		if(state != null)
			this.state = new State(state.getId(), state.getStateCode(), state.getStateName());
		if(country != null)
			this.country= new Country(country.getCountryCode(), country.getCountryName(), country.getId());
		
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
}