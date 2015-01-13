package com.wineaccess.importer;

import java.util.Date;
import java.util.Map;

public class ImporterVO {
	
	private Long id;
	private String importerName;
	private String importerCode;
	private String notes;
	private Long defaultContactAddressId;
	private Long defaultContactBillingAddressId;
	private String importerUrl;
	private Long countryId;
	private String customerNumber;
	private String sourcingStatus;
	private Boolean isDeleted;
	private int wineCount;
	private int activeWineCount;
	private Double revenue;
	private Date lastOfferDate;
	private Map<String, String> warehouse;
	
	
	
	
	
	public Map<String, String> getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Map<String, String> warehouse) {
		this.warehouse = warehouse;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImporterName() {
		
		if(importerName != null){
			this.importerName = importerName.trim();
		}
		
		return importerName;
	}
	public void setImporterName(String importerName) {
		this.importerName = importerName;
	}
	public String getImporterCode() {
		return importerCode;
	}
	public void setImporterCode(String importerCode) {
		this.importerCode = importerCode;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Long getDefaultContactAddressId() {
		return defaultContactAddressId;
	}
	public void setDefaultContactAddressId(Long defaultContactAddressId) {
		this.defaultContactAddressId = defaultContactAddressId;
	}
	public Long getDefaultContactBillingAddressId() {
		return defaultContactBillingAddressId;
	}
	public void setDefaultContactBillingAddressId(
			Long defaultContactBillingAddressId) {
		this.defaultContactBillingAddressId = defaultContactBillingAddressId;
	}
	public String getImporterUrl() {
		return importerUrl;
	}
	public void setImporterUrl(String importerUrl) {
		this.importerUrl = importerUrl;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getSourcingStatus() {
		return sourcingStatus;
	}
	public void setSourcingStatus(String sourcingStatus) {
		this.sourcingStatus = sourcingStatus;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getWineCount() {
		return wineCount;
	}
	public void setWineCount(int wineCount) {
		this.wineCount = wineCount;
	}
	public int getActiveWineCount() {
		return activeWineCount;
	}
	public void setActiveWineCount(int activeWineCount) {
		this.activeWineCount = activeWineCount;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public Date getLastOfferDate() {
		return lastOfferDate;
	}
	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}
	
}
