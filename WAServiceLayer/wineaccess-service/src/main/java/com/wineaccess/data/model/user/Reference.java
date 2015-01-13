package com.wineaccess.data.model.user;

import com.wineaccess.data.model.WineaccessModel;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class Reference extends WineaccessModel {

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String gender;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
