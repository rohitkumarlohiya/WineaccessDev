package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.service.user.preference.UserPreferenceVO;


/**
 * @author abhishek.sharma1
 *PO class for user management related requests
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserDetailVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;



	private Long userId;	
	private String firstName;
	private String lastName;
	private String email;
	private String salutation;
	private String gender;
	private String zipCode;
	private Long stateId;
	private Long countryId;
	private Boolean isReceivedNewsletter;
	private Boolean isEnabled;
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date createdDate;
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	private Boolean isRegistered;
	private String phone;
	private Map<String,String> regStatus;
	private Map<String,String> emailType;
	private String regSource;
	private boolean isActCodeExist;


	@XmlElement
	private List<UserAddressResultModel> address;    

	@XmlElement
	private List<CreditCardResultModel> creditCard;
	
	@XmlElement
	private List<UserPreferenceVO> newsletterList;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public List<UserAddressResultModel> getAddress() {
		return address;
	}
	public void setAddress(List<UserAddressResultModel> address) {
		this.address = address;
	}
	public List<CreditCardResultModel> getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(List<CreditCardResultModel> creditCard) {
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
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Boolean getIsRegistered() {
		return isRegistered;
	}
	public void setIsRegistered(Boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Map<String, String> getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(Map<String, String> regStatus) {
		this.regStatus = regStatus;
	}
	public Map<String, String> getEmailType() {
		return emailType;
	}
	public void setEmailType(Map<String, String> emailType) {
		this.emailType = emailType;
	}
	public String getRegSource() {
		return regSource;
	}
	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}
	public List<UserPreferenceVO> getNewsletterList() {
		return newsletterList;
	}
	public void setNewsletterList(List<UserPreferenceVO> newsletterList) {
		this.newsletterList = newsletterList;
	}
	public boolean isActCodeExist() {
		return isActCodeExist;
	}
	public void setActCodeExist(boolean isActCodeExist) {
		this.isActCodeExist = isActCodeExist;
	}




}
