package com.wineaccess.command.api.access.code;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

@XmlRootElement
public class APIAccessCodePO extends BasePO {
	
	@NotBlank(message = "ERROR_LOGIN_EMPTY_EMAIL")
	@Email(message = "ERROR_INVALID_EMAIL")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
