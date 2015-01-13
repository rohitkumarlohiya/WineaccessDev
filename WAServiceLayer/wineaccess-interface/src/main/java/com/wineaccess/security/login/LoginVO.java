package com.wineaccess.security.login;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jyoti.yadav@globallogic.com
 */
@XmlRootElement
public class LoginVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;
	
	private Long id;
	private String email;
	private String tokenName = "WINEACCESS_TOKEN";
	private String tokenValue;
	private String firstName;
	private String lastName;
	private String salutation;

	public LoginVO() {
	}

	public LoginVO(Long id,String email,String tokenValue, String firstName, String lastName, String salutation) {
		this.id = id;
		this.email = email;
		this.tokenValue = tokenValue;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salutation = salutation;
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

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
