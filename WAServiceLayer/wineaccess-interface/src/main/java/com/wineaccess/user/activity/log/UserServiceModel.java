package com.wineaccess.user.activity.log;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author anurag.jain3
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "firstName", "lastName", "email"})
public class UserServiceModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9065460663478144196L;
	
	
	Long id;
	String firstName;
	String lastName;
	String email;
	
	public UserServiceModel(){
		
	}
	
	
	
	
	public UserServiceModel(Long userId, String firstName, String lastName,
			String email) {
		super();
		this.id = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}




	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
