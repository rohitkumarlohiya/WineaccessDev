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
@XmlType(propOrder={"tokenUser", "totalSessions", "lastLogin", "lastLogout"})
public class UserToken implements Serializable{
	
	/**
	 * 
	 */
	




	/**
	 * 
	 */
	private static final long serialVersionUID = 6691308930819829824L;




	/**
	 * 
	 */
	


	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 
	 */
	@XmlElement(name = "userInfo")
	UserServiceModel tokenUser;
	long totalSessions;
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	Date lastLogin;
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	Date lastLogout;
	
	
	
	
	public UserServiceModel getTokenUser() {
		return tokenUser;
	}
	public void setTokenUser(UserServiceModel tokenUser) {
		this.tokenUser = tokenUser;
	}
	
	public long getTotalSessions() {
		return totalSessions;
	}
	public void setTotalSessions(long totalSessions) {
		this.totalSessions = totalSessions;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getLastLogout() {
		return lastLogout;
	}
	public void setLastLogout(Date lastLogout) {
		this.lastLogout = lastLogout;
	}
	
	
	
	

}
