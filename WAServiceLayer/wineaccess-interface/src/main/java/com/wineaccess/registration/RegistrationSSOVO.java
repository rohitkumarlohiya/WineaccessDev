package com.wineaccess.registration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jyoti.yadav@globallogic.com
 */
@XmlRootElement
public class RegistrationSSOVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;

	private String tokenName = "WINEACCESS_TOKEN";
	private String tokenValue;
	private String email;
	private String firstName;
	private String lastName;
	private String message;
	private Long id;
	private Boolean existsButDeleted;


	
	public RegistrationSSOVO() {
	}

	public RegistrationSSOVO(String tokenValue, String firstName, String lastName, String salutation, String message, String email, Long id) {
		this.tokenValue = tokenValue;
		this.firstName = firstName;
		this.lastName = lastName;
		this.message = message;
		this.email = email;
		this.id = id;
	}
	
	public RegistrationSSOVO(Long id, Boolean existsButDeleted) {
		this.existsButDeleted = existsButDeleted;
		this.id = id;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
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

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getExistsButDeleted() {
		return existsButDeleted;
	}

	public void setExistsButDeleted(Boolean existsButDeleted) {
		this.existsButDeleted = existsButDeleted;
	}

}
