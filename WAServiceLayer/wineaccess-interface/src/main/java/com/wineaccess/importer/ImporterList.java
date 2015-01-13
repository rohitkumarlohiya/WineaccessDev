package com.wineaccess.importer;

import java.io.Serializable;
import java.util.Date;

public class ImporterList implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6983939165268887554L;


	private String importerName;

	
	private String importerCode;

	private String importerUrl;


	private String status;
	
	
	private Boolean isEnabled;
	
	
	private Boolean isApproved;
	
	
	private Boolean isDeleted;
	
	
	private String region;
	
	private String waContact;
	
	private int wineryCount;
	
	private int wineCount;
	
	private int activeWines;
	
	private float revenue;
	
	private Date lastOfferDate;

	public String getImporterName() {
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

	public String getImporterUrl() {
		return importerUrl;
	}

	public void setImporterUrl(String importerUrl) {
		this.importerUrl = importerUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWaContact() {
		return waContact;
	}

	public void setWaContact(String waContact) {
		this.waContact = waContact;
	}

	public int getWineryCount() {
		return wineryCount;
	}

	public void setWineryCount(int wineryCount) {
		this.wineryCount = wineryCount;
	}

	public int getWineCount() {
		return wineCount;
	}

	public void setWineCount(int wineCount) {
		this.wineCount = wineCount;
	}

	public int getActiveWines() {
		return activeWines;
	}

	public void setActiveWines(int activeWines) {
		this.activeWines = activeWines;
	}

	public float getRevenue() {
		return revenue;
	}

	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}
	
	
	
	
	
	

}
