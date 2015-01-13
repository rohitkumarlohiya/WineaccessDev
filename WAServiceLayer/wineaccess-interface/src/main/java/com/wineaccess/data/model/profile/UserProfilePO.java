package com.wineaccess.data.model.profile;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;


/**
 * @author arpit.vijayvargiya@globallogic.com
 */
@XmlRootElement
public class UserProfilePO extends BasePO implements Serializable {
	private static final long serialVersionUID = 85783916826497792L;

	@NotNull(message="USERPROFILE103")
	private Long userId;
	private String currentPassword;
	private String newPassword;
	@NotNull(message="USERPROFILE109")
	@NotBlank(message="USERPROFILE110")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="USERPROFILE124")
	private String firstName;
	@NotNull(message="USERPROFILE111")
	@NotBlank(message="USERPROFILE112")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="USERPROFILE125")
	private String lastName;
	@NotNull(message="USERPROFILE120")
	@NotBlank(message="USERPROFILE121")
	@Email(message="USERPROFILE108")
	private String email;
	//@NotNull(message="USERPROFILE113")
	//@NotBlank(message="USERPROFILE114")
	private String salutation;
	@NotNull(message="USERPROFILE115")
	@NotBlank(message="USERPROFILE116")
	private String gender;
	@NotNull(message="USERPROFILE117")
	@NotBlank(message="USERPROFILE118")
	private String zipCode;
	@NotNull(message="USERPROFILE124")
	private Long stateId;
	@NotNull(message="USERPROFILE119")
	private Long countryId;
	@NotNull(message="USERPROFILE122")
	private Boolean isReceivedNewsLetter;
	

	public UserProfilePO() {
		super();
	}


	public UserProfilePO(Long userId, String currentPassword,
			String newPassword, String firstName, String lastName,
			String email, String salutation, String gender, String zipCode,
			Long stateId, Long countryId, Boolean isReceivedNewLetter) {
		super();
		this.userId = userId;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salutation = salutation;
		this.gender = gender;
		this.zipCode = zipCode;
		this.stateId = stateId;
		this.countryId = countryId;
		this.isReceivedNewsLetter = isReceivedNewLetter;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getCurrentPassword() {
		return currentPassword;
	}


	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public Boolean getIsReceivedNewsLetter() {
		return isReceivedNewsLetter;
	}
	
	public Boolean isReceivedNewsLetter() {
		return isReceivedNewsLetter;
	}


	public void setReceivedNewsLetter(Boolean isReceivedNewsLetter) {
		this.isReceivedNewsLetter = isReceivedNewsLetter;
	}
	
	public void setIsReceivedNewsLetter(Boolean isReceivedNewsLetter) {
		this.isReceivedNewsLetter = isReceivedNewsLetter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
