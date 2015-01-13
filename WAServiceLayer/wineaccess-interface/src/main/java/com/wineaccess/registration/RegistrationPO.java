package com.wineaccess.registration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;


/**
 * @author abhishek.sharma1
 * @since 30/06/2014
 * Parameter class for SSO sign up
 *
 */
@XmlRootElement
public class RegistrationPO extends BasePO{ 
	private static final long serialVersionUID = -7060983258610066202L;
	
	@NotBlank(message = "SSO101")
	@NotNull(message = "SSO102")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="SSO112")
	private String firstName;
	@NotBlank(message = "SSO103")
	@NotNull(message = "SSO104")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="SSO113")
	private String lastName;
	@NotBlank(message = "SSO105")
	@NotNull(message = "SSO106")
	@Email(message = "SSO107")
	private String email;
	private String gender;
	private String zipcode;
	private String state;
	private String accessToken;
	@NotBlank(message = "SSO108")
	@NotNull(message = "SSO109")
	private String ssoSource;
	@NotBlank(message = "SSO110")
	@NotNull(message = "SSO111")
	private String ssoId;
	private String ipAddress;
	private String browser;
	private String operatingSystem;
	private String platform;
	private Boolean isOverrideDelEntry;
  
	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getBrowser() {
		return browser;
	}


	public void setBrowser(String browser) {
		this.browser = browser;
	}


	public String getOperatingSystem() {
		return operatingSystem;
	}


	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}


	
	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	/*public String getSalutation() {
		return salutation;
	}


	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}*/


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getSsoSource() {
		return ssoSource;
	}


	public void setSsoSource(String ssoSource) {
		this.ssoSource = ssoSource;
	}

	public String getSsoId() {
		return ssoId;
	}


	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getSSOsource() {
		return ssoSource;
	}


	public void setSSOsource(String sSOsource) {
		ssoSource = sSOsource;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Boolean getIsOverrideDelEntry() {
		return isOverrideDelEntry;
	}


	public void setIsOverrideDelEntry(Boolean isOverrideDelEntry) {
		this.isOverrideDelEntry = isOverrideDelEntry;
	}
	
	
}
