package com.wineaccess.command.search.importer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.util.command.Country;

@XmlRootElement
public class ImporterModelVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String importerName;
	private String importerCode;
	private Country region;
	private String status = "true";
	private String waContact;
	private int winery;
	private int wines;
	private int activeWines;
	private BigDecimal totalRevenue;
	private String activeStatus = "Active";
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date lastOfferDate;
	private Map<String, String> warehouse;
	
	
	
	public Map<String, String> getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Map<String, String> warehouse) {
		this.warehouse = warehouse;
	}

	public ImporterModelVO(){
	}
	
	public ImporterModelVO(ImporterModel importerModel){
		setId(importerModel.getId());
		setImporterName(importerModel.getImporterName());
		setImporterCode(importerModel.getImporterCode());
		Country country = new Country(importerModel.getCountryId().getCountryCode(),importerModel.getCountryId().getCountryName(),importerModel.getCountryId().getId());
		importerModel.getCountryId().getCountryCode();
		setRegion(country);
		setStatus(importerModel.getIsEnabled()?"true":"false");
		
		if (importerModel.getWaContact() != null) {
			setWaContact(importerModel.getWaContact().getName());
		}
		setActiveStatus(importerModel.getIsActive() ? "Active" : "InActive");
		setWinery(importerModel.getWineryCount().intValue());
		/*if(importerModel.getWineries() != null){
			setWinery(importerModel.getWineries().size());
		}else{
			setWinery(0);
		}*/
		
		setWines(importerModel.getWineCount());
		setActiveWines(importerModel.getActiveWineCount());
		setTotalRevenue(new BigDecimal(importerModel.getRevenue()));
		setLastOfferDate(importerModel.getLastOfferDate());
		
		if(null != importerModel.getWarehouseId()){
			Map<String,String> warehouseMap = new HashMap<String, String>();
			warehouseMap.put("id", importerModel.getWarehouseId().getId().toString());
			warehouseMap.put("name", importerModel.getWarehouseId().getName());		
			setWarehouse(warehouseMap);
		}		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Country getRegion() {
		return region;
	}

	public void setRegion(Country region) {
		this.region = region;
	}

	public String getWaContact() {
		return waContact;
	}

	public void setWaContact(String waContact) {
		this.waContact = waContact;
	}

	public int getWinery() {
		return winery;
	}

	public void setWinery(int winery) {
		this.winery = winery;
	}

	public int getWines() {
		return wines;
	}

	public void setWines(int wines) {
		this.wines = wines;
	}

	public int getActiveWines() {
		return activeWines;
	}

	public void setActiveWines(int activeWines) {
		this.activeWines = activeWines;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	

	
}
