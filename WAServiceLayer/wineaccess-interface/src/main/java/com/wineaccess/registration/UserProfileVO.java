package com.wineaccess.registration;

public class UserProfileVO extends SignupVO{
	
	private String gender;
	private Long stateId;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
}
