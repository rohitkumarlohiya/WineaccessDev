package com.wineaccess.data.model.profile;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name="updateForgotPassword")
public class UpdateForgotPasswordPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "USERACTIVATION102")
	private String email;
	@NotBlank(message = "RESETPASSWORD101")
	private String resetCode;
	@NotBlank(message = "RESETPASSWORD102")
	private String password;
	@NotBlank(message = "RESETPASSWORD103")
	private String confirmPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResetCode() {
		return resetCode;
	}

	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
