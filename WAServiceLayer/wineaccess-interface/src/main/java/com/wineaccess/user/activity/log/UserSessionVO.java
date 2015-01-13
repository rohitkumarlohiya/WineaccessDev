package com.wineaccess.user.activity.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"tokenUser","latestSession","otherSessions"})
public class UserSessionVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -518980128299663135L;

	/**
	 * 
	 */
	

	@XmlElement(name = "userInfo")
	UserServiceModel tokenUser;
	
	SessionModel latestSession;
	
	
	@XmlElementWrapper(name = "allSessions")
	@XmlElement(name = "UserSession")
	List<SessionModel> otherSessions;
	public UserServiceModel getTokenUser() {
		return tokenUser;
	}
	public void setTokenUser(UserServiceModel tokenUser) {
		this.tokenUser = tokenUser;
	}
	public SessionModel getLatestSession() {
		return latestSession;
	}
	public void setLatestSession(SessionModel latestSession) {
		this.latestSession = latestSession;
	}
	
	public List<SessionModel> getOtherSessions() {
		return otherSessions;
	}
	public void setOtherSessions(List<SessionModel> otherSessions) {
		this.otherSessions = otherSessions;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public UserSessionVO() {
		super();
		//activityList = new ArrayList<String>();
		otherSessions = new ArrayList<SessionModel>();
	}
	
	
	

}
