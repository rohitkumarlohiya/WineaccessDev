package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.application.validation.annotation.WineAccessEmbedded;
import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;



/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserAddressModel extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	
	
	
	private String addressId;	
	private String addressLine1;
	private String userAddressId;
	private String addressLine2;	
	private Long cityId;	
	private Long stateId;
	private String countryId;
	private String zipCode;
	private String phone;
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="USER_ADDRESS_ERROR_101")
	private String firstName;
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="USER_ADDRESS_ERROR_102")
	private String lastName;
	private String businessName;
	private String birthDate;
	
	
	private String addressType;
	private String isDefault;

	@XmlElement
	@WineAccessEmbedded
	private UserAddressPrefModel addressPref;
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
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
	public String getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
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
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public UserAddressPrefModel getAddressPref() {
		return addressPref;
	}
	public void setAddressPref(UserAddressPrefModel addressPref) {
		this.addressPref = addressPref;
	}
}
