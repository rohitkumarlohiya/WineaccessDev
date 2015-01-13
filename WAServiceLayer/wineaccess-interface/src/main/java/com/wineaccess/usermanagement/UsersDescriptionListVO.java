package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.wineaccess.user.activity.log.UserServiceModel;

@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDescriptionListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElementWrapper(name="successList")
	private List<UserServiceModel> successList = null;
	@XmlElementWrapper(name="failureList")
	private List<UserServiceModel> failureList = null;
	@XmlElementWrapper(name="nonExistingList")
	private List<Long> nonExisting = null;
	
	
	public UsersDescriptionListVO() {
		super();
		successList = new ArrayList<UserServiceModel>();
		failureList = new ArrayList<UserServiceModel>();
		nonExisting = new ArrayList<Long>();
	}
	public UsersDescriptionListVO(List<UserServiceModel> success,
			List<UserServiceModel> failure,
			List<Long> nonExisting) {
		super();
		this.successList = success;
		this.failureList = failure;
		this.nonExisting = nonExisting;
	}
	
	public List<UserServiceModel> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<UserServiceModel> successList) {
		this.successList = successList;
	}
	public List<UserServiceModel> getFailureList() {
		return failureList;
	}
	public void setFailureList(List<UserServiceModel> failureList) {
		this.failureList = failureList;
	}
	public List<Long> getNonExisting() {
		return nonExisting;
	}
	public void setNonExisting(List<Long> nonExisting) {
		this.nonExisting = nonExisting;
	}
	
}
