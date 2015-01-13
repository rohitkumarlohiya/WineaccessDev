package com.wineaccess.wine;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;

/**
 * @author gaurav.agarwal1
 * 
 *         details of the wine
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineDetails implements Serializable {

	private static final long serialVersionUID = 543190634671866771L;

	private Long id;
	private String wineName;
	private String fullWineName;
	private Long wineId;
	private Map<String, String> wineStyle;
	private Map<String, String> vintage;
	private Map<String, String> varietal;
	private Map<String, String> size;
	private Map<String, String> winery;
	private Map<String, String> importer;
	private Map<String, String> country;
	private Map<String, String> waContact;
	private Map<String, String> freightRegion;
	private Map<String, String> wineSourcing;
	private Map<String, String> bottlesPerBox;
	private Map<String, String> contact;
	private Boolean isImported;
	private Boolean isEnabled;
	private String wineShortName;
	private Double alcoholPercentage;
	private String notes;
	private String wineLabel;
	private String privateLabel;
	private Boolean sendToFullfillerOn;
	private Map<String, String> warehouse;
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date usaArrivalDate;

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

	public String getWineName() {
		return wineName;
	}

	public void setWineName(String wineName) {
		this.wineName = wineName;
	}

	public String getFullWineName() {
		return fullWineName;
	}

	public void setFullWineName(String fullWineName) {
		this.fullWineName = fullWineName;
	}

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}

	public Map<String, String> getWineStyle() {
		return wineStyle;
	}

	public void setWineStyle(Map<String, String> wineStyle) {
		this.wineStyle = wineStyle;
	}

	public Map<String, String> getVintage() {
		return vintage;
	}

	public void setVintage(Map<String, String> vintage) {
		this.vintage = vintage;
	}

	public Map<String, String> getVarietal() {
		return varietal;
	}

	public void setVarietal(Map<String, String> varietal) {
		this.varietal = varietal;
	}

	public Map<String, String> getSize() {
		return size;
	}

	public void setSize(Map<String, String> size) {
		this.size = size;
	}

	public Map<String, String> getWinery() {
		return winery;
	}

	public void setWinery(Map<String, String> winery) {
		this.winery = winery;
	}

	public Map<String, String> getImporter() {
		return importer;
	}

	public void setImporter(Map<String, String> importer) {
		this.importer = importer;
	}

	public Map<String, String> getCountry() {
		return country;
	}

	public void setCountry(Map<String, String> country) {
		this.country = country;
	}

	public Map<String, String> getWaContact() {
		return waContact;
	}

	public void setWaContact(Map<String, String> waContact) {
		this.waContact = waContact;
	}

	public Map<String, String> getFreightRegion() {
		return freightRegion;
	}

	public void setFreightRegion(Map<String, String> freightRegion) {
		this.freightRegion = freightRegion;
	}

	public Boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public Map<String, String> getWineSourcing() {
		return wineSourcing;
	}

	public void setWineSourcing(Map<String, String> wineSourcing) {
		this.wineSourcing = wineSourcing;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean status) {
		this.isEnabled = status;
	}

	public Map<String, String> getBottlesPerBox() {
		return bottlesPerBox;
	}

	public void setBottlesPerBox(Map<String, String> bottlesPerBox) {
		this.bottlesPerBox = bottlesPerBox;
	}

	public String getWineShortName() {
		return wineShortName;
	}

	public void setWineShortName(String wineShortName) {
		this.wineShortName = wineShortName;
	}

	public Double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(Double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWineLabel() {
		return wineLabel;
	}

	public void setWineLabel(String wineLabel) {
		this.wineLabel = wineLabel;
	}

	public String getPrivateLabel() {
		return privateLabel;
	}

	public void setPrivateLabel(String privateLabel) {
		this.privateLabel = privateLabel;
	}

	public Boolean getSendToFullfillerOn() {
		return sendToFullfillerOn;
	}

	public void setSendToFullfillerOn(Boolean sendToFullfillerOn) {
		this.sendToFullfillerOn = sendToFullfillerOn;
	}

	public Date getUsaArrivalDate() {
		return usaArrivalDate;
	}

	public void setUsaArrivalDate(Date usaArrivalDate) {
		this.usaArrivalDate = usaArrivalDate;
	}

	public Map<String, String> getContact() {
		return contact;
	}

	public void setContact(Map<String, String> contact) {
		this.contact = contact;
	}
}
