package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;
import com.wineaccess.service.user.preference.NewsletterList;

/**
 * @author abhishek.sharma1
 *PO class for user management related requests
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserManagementPO extends BasePO implements Serializable  { 
	private static final long serialVersionUID = -7060983258610066202L;

	@XmlElement
	@JsonIgnore
	private List<UserAddressModel> address;    

	@XmlElement
	@JsonIgnore
	private List<CreditCardModel> creditCard;
	
	@XmlElement
	private List<NewsletterList> newsletterList;

	private String userId;
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="USER104")
	private String firstName;
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="USER105")
	private String lastName;
	@Email(message="USER133")
	private String email;
	private String salutation;
	private String gender;
	private String zipCode;
	private Long stateId;
	private Long countryId;
	private Boolean isReceivedNewsletter;
	private String phone;
	private Boolean isOverrideDelEntry;
	private String regStatus;
	private String regSource;
	private String emailType;
	private String url;


	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<UserAddressModel> getAddress() {
		return address;
	}
	public void setAddress(List<UserAddressModel> address) {
		this.address = address;
	}

	public List<CreditCardModel> getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(List<CreditCardModel> creditCard) {
		this.creditCard = creditCard;
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

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipcode) {
		this.zipCode = zipcode;
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
	public Boolean getIsReceivedNewsletter() {
		return isReceivedNewsletter;
	}
	public void setIsReceivedNewsletter(Boolean isReceivedNewsletter) {
		this.isReceivedNewsletter = isReceivedNewsletter;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getIsOverrideDelEntry() {
		return isOverrideDelEntry;
	}
	public void setIsOverrideDelEntry(Boolean isOverrideDelEntry) {
		this.isOverrideDelEntry = isOverrideDelEntry;
	}
	public String getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	public String getRegSource() {
		return regSource;
	}
	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}
	public List<NewsletterList> getNewsletterList() {
		return newsletterList;
	}
	public void setNewsletterList(List<NewsletterList> newsletterList) {
		this.newsletterList = newsletterList;
	}
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
	
}
