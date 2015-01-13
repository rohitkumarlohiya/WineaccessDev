package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;



/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserAddressResultModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	
	
	
	private String addressId;	
	private String addressLine1;
	private String addressLine2;	
	private Long cityId;	
	private Long stateId;
	private Long countryId;
	private String zipCode;
	private String phone;
	private String firstName;
	private String lastName;
	private String businessName;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date birthDate;
	private Map<String,String> addressType;
	private boolean isDefault;

	@XmlElement
	private UserAddressPrefResultModel addressPref;
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public Map<String,String> getAddressType() {
		return addressType;
	}
	public void setAddressType(Map<String,String> addressType) {
		this.addressType = addressType;
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
	

	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipcode) {
		this.zipCode = zipcode;
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
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public UserAddressPrefResultModel getAddressPref() {
		return addressPref;
	}
	public void setAddressPref(UserAddressPrefResultModel addressPref) {
		this.addressPref = addressPref;
	}

	
}
