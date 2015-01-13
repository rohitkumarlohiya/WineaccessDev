package com.wineaccess.security.login;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author jyoti.yadav@globallogic.com
 */
@XmlRootElement
public class LoginPO extends BasePO {

	private static final long serialVersionUID = 85783916826497792L;
	
	@NotBlank(message = "ERROR_LOGIN_EMPTY_EMAIL")
	@Email(message = "ERROR_INVALID_EMAIL")
	private String username;
	
	private String password;
	private String ipAddress = "1.1.1.1";
	private String browser = "Chrome";
	private String operatingSystem = "Windows";
	private String platform = "mobile";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
