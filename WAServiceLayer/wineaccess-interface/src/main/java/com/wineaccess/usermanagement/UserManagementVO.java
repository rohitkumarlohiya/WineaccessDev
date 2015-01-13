package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.service.user.preference.UserPreferenceVO;


/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
public class UserManagementVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;


	private String firstName;
	private String lastName;
	private String message;
	private String id;
	private String email;
	private Boolean existsButDeleted; 

	private List<UserPreferenceVO> userPreferenceVO = new ArrayList<UserPreferenceVO>();

	public UserManagementVO() {
		super();
	}

	public UserManagementVO(String firstName, String lastName, String message,
			String id, String email) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.message = message;
		this.id = id;
		this.email = email;
	}

	public UserManagementVO(String firstName, String lastName, String message,
			String id, String email,
			List<UserPreferenceVO> userPreferenceVO) {
		this(firstName,lastName,message,id,email);
		this.userPreferenceVO = userPreferenceVO;
	}


	public UserManagementVO( String id, Boolean  existsButDeleted) {
		this.id = id;
		this.existsButDeleted = existsButDeleted;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getExistsButDeleted() {
		return existsButDeleted;
	}

	public void setExistsButDeleted(Boolean existsButDeleted) {
		this.existsButDeleted = existsButDeleted;
	}
	@XmlElementWrapper(name = "newsletterList")
	@XmlElement(name = "newsletter")
	public List<UserPreferenceVO> getUserPreferenceVO() {
		return userPreferenceVO;
	}

	public void setUserPreferenceVO(List<UserPreferenceVO> userPreferenceVO) {
		this.userPreferenceVO = userPreferenceVO;
	}

}
