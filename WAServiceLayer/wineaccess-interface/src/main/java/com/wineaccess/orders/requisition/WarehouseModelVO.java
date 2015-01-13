package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.MasterDataModel;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WarehouseModelVO implements Serializable {

	private static final long serialVersionUID = 5550064054520579887L;

	private Long id;

	private String name;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String zipcode;

	private String phone;

	private String emailId;

	private String faxNumber;

	private String firstName;

	private String lastName;

	private String uniqueAddressHash;

	private MasterDataModel freightRegionForPO;
	
	

	public WarehouseModelVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getUniqueAddressHash() {
		return uniqueAddressHash;
	}

	public void setUniqueAddressHash(String uniqueAddressHash) {
		this.uniqueAddressHash = uniqueAddressHash;
	}

	public MasterDataModel getFreightRegionForPO() {
		return freightRegionForPO;
	}

	public void setFreightRegionForPO(MasterDataModel freightRegion) {
		this.freightRegionForPO = freightRegion;
	}

}
