package com.wineaccess.data.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wineaccess.application.constants.NamedQueryConstants;
import com.wineaccess.data.model.UserManagementEntityListner;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "USER_ADDRESS")
@EntityListeners(UserManagementEntityListner.class)
@NamedQueries({
	@NamedQuery(name = NamedQueryConstants.FIND_ADDR_BY_USERNAME, query = "from UserAddress ua, AddressPreference ap  where ua.addressPreferenceId=ap.id and ua.userId= :userId"),
	@NamedQuery(name = NamedQueryConstants.FIND_ADDR_BY_USERID_ADDR_ID, query = "from UserAddress ua, AddressPreference ap  where ua.addressPreferenceId=ap.id and ua.userId= :userId and ua.id= :addressId"),
	@NamedQuery(name = NamedQueryConstants.FIND_ADDR_IDS_BY_USER_ID, query = "select id from UserAddress ua  where ua.userId= :userId"),
	@NamedQuery(name = NamedQueryConstants.UPDATE_USER_IDS_ADDRESS, query = "update UserAddress ua set userId = :userId where ua.id IN :idsList"),
	@NamedQuery(name = NamedQueryConstants.FIND_ADDR_PREF_IDS_BY_ADDR_ID, query = "select addressPreferenceId from UserAddress ua  where ua.id= :addressId"),
	@NamedQuery(name = NamedQueryConstants.FIND_DEFAULT_ADDRESS, query = "from UserAddress ua  where ua.userId= :userId AND ua.addressType=:addressType AND ua.isDefault=true")
	})
public class UserAddress extends Persistent {

	private static final long serialVersionUID = -7248690572731436369L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ADDRESS_LINE1", columnDefinition = "VARCHAR(255) NULL")
	private String address1;

	@Column(name = "ADDRESS_LINE2", columnDefinition = "VARCHAR(255) NULL")
	private String address2;

	@Column(name = "CITY_ID", columnDefinition = "BIGINT(20) UNSIGNED  NULL")
	private Long cityId;

	@Column(name = "STATE_ID", columnDefinition = "BIGINT(20) UNSIGNED  NULL")
	private Long stateId;

	@Column(name = "COUNTRY_ID", columnDefinition = "BIGINT(20) UNSIGNED  NULL")
	private Long countryId;

	@Column(name = "ZIPCODE", columnDefinition = "VARCHAR(15) NULL")
	private String zipcode;

	@Column(name = "PHONE", columnDefinition = "VARCHAR(20) NULL")
	private String phone;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String firstName;

	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String lastName;

	@Column(name = "BUSINESS_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String businessName;

	@Column(name = "BIRTH_DATE", columnDefinition = "DATETIME NULL")
	private Date birthDate;

	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED NULL")
	private Long userId;

	@Column(name = "ADDRESS_PREFERENCE_ID", columnDefinition = "BIGINT(20) UNSIGNED NULL")
	private Long addressPreferenceId;

	@Column(name = "ADDRESS_TYPE", columnDefinition = "VARCHAR(20) NULL")
	private String addressType;
	
	@Column(name = "IS_DEFAULT", columnDefinition = "VARCHAR(20) NULL")
	private boolean isDefault = false;
  
	@Transient 
	private AddressPreference addressPreference;
	
	public AddressPreference getAddressPreference() {
		return addressPreference;
	}

	public void setAddressPreference(AddressPreference addressPreference) {
		this.addressPreference = addressPreference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressPreferenceId() {
		return addressPreferenceId;
	}

	public void setAddressPreferenceId(Long addressPreferenceId) {
		this.addressPreferenceId = addressPreferenceId;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	


}
