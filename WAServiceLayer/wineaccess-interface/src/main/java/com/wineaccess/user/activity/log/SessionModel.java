package com.wineaccess.user.activity.log;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "token","sessionStartTime", "sessionEndTime", "ipAddress", "browser","operatingSystem","platformDevice", "activityInfo"})
public class SessionModel implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4674806999820305237L;

	@XmlElement(name="sessionid")
	private Long id;

	@XmlElement(name="token")
	private String token;


	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name="session_login_time")
	private Date sessionStartTime;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name="session_logout_time")
	private Date sessionEndTime;

	
	private String ipAddress;

	
	private String browser;


	private String operatingSystem;

	
	private String platformDevice;
	@XmlElement(name="activityInfo")
	private ActivityInfo activityInfo;
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	

	public Date getSessionStartTime() {
		return sessionStartTime;
	}


	public void setSessionStartTime(Date sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}


	public Date getSessionEndTime() {
		return sessionEndTime;
	}


	public void setSessionEndTime(Date sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getPlatformDevice() {
		return platformDevice;
	}


	public void setPlatformDevice(String platformDevice) {
		this.platformDevice = platformDevice;
	}




	public ActivityInfo getActivityInfo() {
		return activityInfo;
	}


	public void setActivityInfo(ActivityInfo activityInfo) {
		this.activityInfo = activityInfo;
	}


	public SessionModel() {
		super();
		//activityList = new ArrayList<Activity>();
	}
	
	

}
