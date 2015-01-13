package com.wineaccess.warehouse;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.MasterDataModel;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseDetails {

	private Long id;
	private String name;
	private String addressLine1;
	private String addressLine2;
	private Map<String,String> cityId = new HashMap<String, String>();
	private Map<String,String> stateId = new HashMap<String, String>();
	private String zipcode;
	private String phone;
	private Boolean isDeleted;
	private String emailId;
	private String faxNumber;
	private String firstName;
	private String lastName;
	private MasterDataModel freightRegion;
	private Boolean isNonWSTransportCarrier;
	private MasterDataModel carrier;
	
	public MasterDataModel getCarrier() {
		return carrier;
	}
	public void setCarrier(MasterDataModel carrier) {
		this.carrier = carrier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public MasterDataModel getFreightRegion() {
		return freightRegion;
	}
	public void setFreightRegion(MasterDataModel freightRegion) {
		this.freightRegion = freightRegion;
	}
	public Boolean getIsNonWSTransportCarrier() {
		return isNonWSTransportCarrier;
	}
	public void setIsNonWSTransportCarrier(Boolean isWineShippingAddress) {
		this.isNonWSTransportCarrier = isWineShippingAddress;
	}


}