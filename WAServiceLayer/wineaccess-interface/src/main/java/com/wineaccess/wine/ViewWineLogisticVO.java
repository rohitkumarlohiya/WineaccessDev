package com.wineaccess.wine;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.wineryimporter.ContactDetails;

/**
 * @author abhishek.sharma1
 * 
 */
@XmlRootElement
@XmlType(name = "viewWineLogisticDetail")
public class ViewWineLogisticVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long wineryId;

	private String wineFullName;

	private Long wineId;

	private Long productId;

	private Map<String, String> contactId;

	private Boolean isFullCaseOnly;

	private Long bottleWeightInLBS;

	private Map<String, String> bottlePerBox;

	private Map<String, String> freight;

	private Map<String, String> warehouse;

	public Map<String, String> getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Map<String, String> warehouse) {
		this.warehouse = warehouse;
	}

	public String getWineFullName() {
		return wineFullName;
	}

	public void setWineFullName(String wineFullName) {
		this.wineFullName = wineFullName;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}

	public Map<String, String> getContactId() {
		return contactId;
	}

	public void setContactId(Map<String, String> contactId) {
		this.contactId = contactId;
	}

	public Boolean getIsFullCaseOnly() {
		return isFullCaseOnly;
	}

	public void setIsFullCaseOnly(Boolean isFullCaseOnly) {
		this.isFullCaseOnly = isFullCaseOnly;
	}

	public Long getBottleWeightInLBS() {
		return bottleWeightInLBS;
	}

	public void setBottleWeightInLBS(Long bottleWeightInLBS) {
		this.bottleWeightInLBS = bottleWeightInLBS;
	}

	public Map<String, String> getBottlePerBox() {
		return bottlePerBox;
	}

	public void setBottlePerBox(Map<String, String> bottlePerBox) {
		this.bottlePerBox = bottlePerBox;
	}

	public Map<String, String> getFreight() {
		return freight;
	}

	public void setFreight(Map<String, String> freight) {
		this.freight = freight;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
