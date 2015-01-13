package com.wineaccess.user.activation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlType(name = "resendActivationMail")
public class ResendActivationMailVO implements Serializable {

	private static final long serialVersionUID = 4775359917570219205L;
	private Long userId;
	private String email;
	private String message;
	
	public ResendActivationMailVO(){
		
	}
	
	public ResendActivationMailVO(Long userId,String email,String message){
		this.userId = userId;
		this.email = email;
		this.message = message;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
