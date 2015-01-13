package com.wineaccess.security.login.history;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionHistoryVO implements Serializable {

	private static final long serialVersionUID = 1641219119537104956L;
	private String browser;
	private String ipAddress;
	private String operatingSystem;
	private String platformDevice;
	private String sessionStartTime;
	private String sessionEndTime;
	
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getPlatformDevice() {
		return platformDevice;
	}

	public void setPlatformDevice(String platformDevice) {
		this.platformDevice = platformDevice;
	}

	public String getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public String getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

}
