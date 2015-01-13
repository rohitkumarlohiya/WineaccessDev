package com.wineaccess.importer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.util.command.Country;

/**
 * @author gaurav.agarwal1
 * 
 * it will show the details of Importer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso(ImporterWinery.class)
public class ImporterDetails implements Serializable {

	private static final long serialVersionUID = 4247186778886629003L;

	private String name;
	private Long importerId;
	private String contact;
	private String email;
	private String phone;
	private Country region;
	private Map<String,String> waContact;
	private Map<String,String> freightRegion;
	private String importerUrl;
	private String accountingCustomerNumber;
	private Map<String,String> sourcingStatus;
	private boolean status;
	
	@XmlElementWrapper(name="wineries")
	private List<ImporterWinery> wineries;
	
	private Map<String,String> warehouse;
	
	
	
	public Map<String, String> getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Map<String, String> warehouse) {
		this.warehouse = warehouse;
	}

	public ImporterDetails() {
		super();
		wineries = new ArrayList<ImporterWinery>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getImporterId() {
		return importerId;
	}

	public void setImporterId(Long importerId) {
		this.importerId = importerId;
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

	public String getImporterUrl() {
		return importerUrl;
	}

	public void setImporterUrl(String importerUrl) {
		this.importerUrl = importerUrl;
	}

	public Country getRegion() {
		return region;
	}

	public void setRegion(Country region) {
		this.region = region;
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

	public List<ImporterWinery> getWineries() {
		return wineries;
	}

	public void setWineries(List<ImporterWinery> wineries) {
		this.wineries = wineries;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}


