package com.wineaccess.application.contact;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContactDetailsDeleteVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name="successList")
	private List<WineryContactVO> successList = null;
	@XmlElementWrapper(name="failureList")
	private List<WineryContactVO> failureList = null;
	@XmlElementWrapper(name="nonExistingList")
	private List<Long> nonExisting = null;


	public ContactDetailsDeleteVO() {
		super();
		successList = new ArrayList<WineryContactVO>();
		failureList = new ArrayList<WineryContactVO>();
		nonExisting = new ArrayList<Long>();
	}


	public List<WineryContactVO> getSuccessList() {
		return successList;
	}


	public void setSuccessList(List<WineryContactVO> successList) {
		this.successList = successList;
	}


	public List<WineryContactVO> getFailureList() {
		return failureList;
	}


	public void setFailureList(List<WineryContactVO> failureList) {
		this.failureList = failureList;
	}


	public List<Long> getNonExisting() {
		return nonExisting;
	}


	public void setNonExisting(List<Long> nonExisting) {
		this.nonExisting = nonExisting;
	}


}
