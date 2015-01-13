package com.wineaccess.user.activation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlType(name = "userActivation")
public class UserActivationVO implements Serializable {

	private static final long serialVersionUID = -1538429119057456566L;
	private Long id;
	private String email;
	private String message;
	
	public UserActivationVO(){
		
	}
	
	public UserActivationVO(Long userId,String email,String message){
		this.id = userId;
		this.email = email;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
