package com.wineaccess.wineryimporter;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WIViewAddress")
public class WineryImporterAddressVO  implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	private Long id;
	private String addressLine1;	
	private String addressLine2;	
	private Map<String,String> cityId;	
	private Map<String,String> stateId;		
	private Map<String,String> countryId;		
	private String zipcode;	
	private Long wineryId;
	private Long importerId;
	private String phone;
	private String firstName;	
	private String lastName;	
	private Map<String,String> addressType;	
	private Boolean isDefault;


	public WineryImporterAddressVO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Map<String, String> getCityId() {
		return cityId;
	}

	public void setCityId(Map<String, String> cityId) {
		this.cityId = cityId;
	}

	public Map<String, String> getStateId() {
		return stateId;
	}

	public void setStateId(Map<String, String> stateId) {
		this.stateId = stateId;
	}

	public Map<String, String> getCountryId() {
		return countryId;
	}

	public void setCountryId(Map<String, String> countryId) {
		this.countryId = countryId;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public Long getImporterId() {
		return importerId;
	}

	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, String> getAddressType() {
		return addressType;
	}

	public void setAddressType(Map<String, String> addressType) {
		this.addressType = addressType;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}


	

}
