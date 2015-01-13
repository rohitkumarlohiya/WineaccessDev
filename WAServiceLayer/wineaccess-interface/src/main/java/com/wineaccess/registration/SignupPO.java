package com.wineaccess.registration;

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
 * 
 */
@XmlRootElement
public class SignupPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 8697174660147298550L;
	private boolean isReceivedNewsletter;
	
	@NotEmpty(message="USER154")
	@NotBlank(message="USER161")
  @Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="SIGNUP102")
	private String firstName;
	
	@NotEmpty(message="USER155")
	@NotBlank(message="USER162")
  @Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="SIGNUP103")
	private String lastName;	
	
	@NotEmpty(message="USER152")
	@Pattern(regexp=RegExConstants.EMAIL,message="USER153")
	private String email;
	
	@NotEmpty(message="USER156")
	private String salutation;
	private String gender;
	
	@NotEmpty(message="USER157")
	private String password;
	
	@NotEmpty(message="USER158")
	private String zipCode;
	
	//@NotNull(message="USER159")
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING, message="USER160")
	private Long countryId;
	
	private Boolean isOverrideDelEntry;
	private String url;
		
	public boolean isReceivedNewsletter() {
		return isReceivedNewsletter;
	}
	public void setReceivedNewsletter(boolean isReceivedNewsletter) {
		this.isReceivedNewsletter = isReceivedNewsletter;
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
	public void setEmail(String emailId) {
		this.email = emailId;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
