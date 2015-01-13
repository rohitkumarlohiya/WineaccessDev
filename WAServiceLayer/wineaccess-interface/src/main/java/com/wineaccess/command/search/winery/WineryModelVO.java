package com.wineaccess.command.search.winery;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.util.command.State;

@XmlRootElement
public class WineryModelVO implements Serializable{
	
	private String wineryName;
	//private String wineryCode;
	private State wineryRegion;
	private String importerName;
	private String waContact;
	private int wines;
	private int activeWines;
	private double totalRevenue;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date lastOfferDate;
	private Long id;
	private String isEnabled = "";
	private Map<String, String> warehouse;
	
	public WineryModelVO() {
	}
	
	public WineryModelVO(WineryModel wineryModel){
		this.wineryName = wineryModel.getWineryName();
		//this.wineryCode = wineryModel.getWineryCode();
		wineryRegion = new State();
		wineryRegion.setId(wineryModel.getRegion().getId());
		wineryRegion.setName(wineryModel.getRegion().getStateName());
		wineryRegion.setStateCode(wineryModel.getRegion().getStateCode());
		
	//	this.wineryRegion = wineryModel.getRegion().getStateName();
		if (wineryModel.getActiveImporterId() != null) {
			this.importerName = wineryModel.getActiveImporterId().getImporterName();
		}
		if (wineryModel.getWaContact() != null) {
			this.waContact = wineryModel.getWaContact().getName();
		}
		this.wines = wineryModel.getWineCount();
		this.activeWines = wineryModel.getActiveWineCount();
		
			this.totalRevenue = wineryModel.getRevenue();
		
		this.lastOfferDate = wineryModel.getLastOfferDate();
		this.id=wineryModel.getId();
		this.isEnabled = wineryModel.getIsEnabled().toString();
		if(null != wineryModel.getWarehouseId()){
			Map<String,String> warehouseMap = new HashMap<String, String>();
			warehouseMap.put("id", wineryModel.getWarehouseId().getId().toString());
			warehouseMap.put("name", wineryModel.getWarehouseId().getName());
			this.warehouse = warehouseMap;
		}
	}

	public String getWineryName() {

		if(wineryName != null){
			this.wineryName = wineryName.trim();
		}
		
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	

	public State getWineryRegion() {
		return wineryRegion;
	}

	public void setWineryRegion(State wineryRegion) {
		this.wineryRegion = wineryRegion;
	}

	public String getImporterName() {
		return importerName;
	}

	public void setImporterName(String importerName) {
		this.importerName = importerName;
	}

	public String getWaContact() {
		return waContact;
	}

	public void setWaContact(String waContact) {
		this.waContact = waContact;
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

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Map<String, String> getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Map<String, String> warehouse) {
		this.warehouse = warehouse;
	}
	
	
}
