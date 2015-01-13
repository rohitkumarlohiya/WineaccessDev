package com.wineaccess.distributioncentre;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1 
 * VO of view distribution centre
 */
@XmlRootElement
@XmlType(name = "viewDistributionCentre")
public class ViewDistributionCentreVO implements Serializable {

	private static final long serialVersionUID = -2683092413159936480L;

	private Long id;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private Map<String, String> city;
	private Map<String, String> state;
	private String zipcode;
	private String phone;
	private String emailId;
	private String faxNumber;

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

	public Map<String, String> getCity() {
		return city;
	}

	public void setCity(Map<String, String> city) {
		this.city = city;
	}

	public Map<String, String> getState() {
		return state;
	}

	public void setState(Map<String, String> state) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
