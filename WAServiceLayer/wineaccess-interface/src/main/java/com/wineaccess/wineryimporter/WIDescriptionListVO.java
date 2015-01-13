package com.wineaccess.wineryimporter;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WIaddressList")
public class WIDescriptionListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElementWrapper(name="successList")
	private List<WineryImporterAddressBasicVO> successList = null;
	@XmlElementWrapper(name="failureList")
	private List<WineryImporterAddressBasicVO> failureList = null;
	@XmlElementWrapper(name="nonExistingList")
	private List<Long> nonExisting = null;
	
	public List<WineryImporterAddressBasicVO> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<WineryImporterAddressBasicVO> successList) {
		this.successList = successList;
	}
	public List<WineryImporterAddressBasicVO> getFailureList() {
		return failureList;
	}
	public void setFailureList(List<WineryImporterAddressBasicVO> failureList) {
		this.failureList = failureList;
	}
	public List<Long> getNonExisting() {
		return nonExisting;
	}
	public void setNonExisting(List<Long> nonExisting) {
		this.nonExisting = nonExisting;
	}
	
	
	
	
	
	
	
}
