package com.wineaccess.wineryimporter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;


@XmlRootElement(name="WIAddAddress")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryImporterAddressPO extends BasePO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	@NotNull(message="WI_ADDRESS_101")
	@NotBlank(message="WI_ADDRESS_101")
	private String addressLine1;	
	private String addressLine2;	

	@NotNull(message="WI_ADDRESS_102")
	private Long cityId;	

	@NotNull(message="WI_ADDRESS_103")
	private Long stateId;	
	private Long addressId;
	
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message = "WI_ADDRESS_104")
	@NotNull(message="WI_ADDRESS_104")
	private String zipcode;	
	
	private Long wineryId;
	
	private Long importerId;

	@NotNull(message="WI_ADDRESS_105")
	private String phone;

	@NotNull(message="WI_ADDRESS_106")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="WI_ADDRESS_121")
	private String firstName;

	@NotNull(message="WI_ADDRESS_107")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="WI_ADDRESS_122")
	private String lastName;

	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.ADDRESS_TYPE,message=MasterDataErrorCode.Constants.ADDRESS_TYPE)
	@NotNull(message="WI_ADDRESS_108")
	private Long addressType;	

	@NotNull(message="WI_ADDRESS_109")
	private Long countryId;	
	private Boolean isDefault;


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


	public Long getAddressType() {
		return addressType;
	}
	public void setAddressType(Long addressType) {
		this.addressType = addressType;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
}
