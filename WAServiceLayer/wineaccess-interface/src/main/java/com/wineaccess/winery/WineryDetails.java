package com.wineaccess.winery;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;
import com.wineaccess.importer.ImporterWinery;
import com.wineaccess.util.command.State;

/**
 * @author gaurav.agarwal1
 * 
 * it will show the details of Winery
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@SerializedName(value = "wineryName")
	@XmlElement(name="wineryName")
	private String name;
	@SerializedName(value = "id")
	@XmlElement(name="id")
	private Long wineryId;
	private String contact;
	private String email;
	private String phone;
	
	private ImporterWinery activeImporter;
	private Map<String,String> waContact;
	private Map<String,String> freightRegion;
	private String wineryUrl;
	
	private String accountingCustomerNumber;
	private Map<String,String> sourcingStatus;
	private State region;
	private Map<String,String> warehouse;
	private boolean status;
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	

	
	

	public ImporterWinery getActiveImporter() {
		return activeImporter;
	}

	public void setActiveImporter(ImporterWinery activeImporter) {
		this.activeImporter = activeImporter;
	}

	public Map<String,String> getWaContact() {
		return waContact;
	}

	public void setWaContact(Map<String,String> waContact) {
		this.waContact = waContact;
	}

	public Map<String,String> getFreightRegion() {
		return freightRegion;
	}

	public void setFreightRegion(Map<String,String> freightRegion) {
		this.freightRegion = freightRegion;
	}

	public String getWineryUrl() {
		return wineryUrl;
	}

	public void setWineryUrl(String wineryUrl) {
		this.wineryUrl = wineryUrl;
	}

	public String getAccountingCustomerNumber() {
		return accountingCustomerNumber;
	}

	public void setAccountingCustomerNumber(String accountingCustomerNumber) {
		this.accountingCustomerNumber = accountingCustomerNumber;
	}

	public Map<String, String> getSourcingStatus() {
		return sourcingStatus;
	}

	public void setSourcingStatus(Map<String, String> sourcingStatus) {
		this.sourcingStatus = sourcingStatus;
	}

	public State getRegion() {
		return region;
	}

	public void setRegion(State region) {
		this.region = region;
	}

	public Map<String, String> getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Map<String, String> warehouse) {
		this.warehouse = warehouse;
	}

	
	
	

}