package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * details of distribution centre
 */
@XmlRootElement
public class DistributionCentreDetails extends BasePO implements Serializable {

	private static final long serialVersionUID = -8756101975145274705L;
	
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="DISTRIBUTION_CENTRE_ERROR_116")
	private String firstName;
	
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="DISTRIBUTION_CENTRE_ERROR_117")
	private String lastName;
	
	@NotBlank(message="DISTRIBUTION_CENTRE_ERROR_103")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotEmpty(message="DISTRIBUTION_CENTRE_ERROR_104")
	@Pattern(regexp=RegExConstants.DIGITS,message="DISTRIBUTION_CENTRE_ERROR_110")
	private String cityId;
	
	@NotEmpty(message="DISTRIBUTION_CENTRE_ERROR_105")
	@Pattern(regexp=RegExConstants.DIGITS,message="DISTRIBUTION_CENTRE_ERROR_111")
	private String stateId;
	
	@NotBlank(message="DISTRIBUTION_CENTRE_ERROR_107")
	private String zipcode;
	
	@NotEmpty(message="DISTRIBUTION_CENTRE_ERROR_108")
	@Pattern(regexp=RegExConstants.PHONE_EMPTY_STRING,message="DISTRIBUTION_CENTRE_ERROR_113")
	private String phone;
	
	@NotEmpty(message="DISTRIBUTION_CENTRE_ERROR_109")
	@Pattern(regexp=RegExConstants.EMAIL,message="DISTRIBUTION_CENTRE_ERROR_114")
	private String emailId;
	
	@Pattern(regexp=RegExConstants.PHONE_EMPTY_STRING,message="DISTRIBUTION_CENTRE_ERROR_118")
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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
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

}
