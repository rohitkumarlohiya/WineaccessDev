package com.wineaccess.warehouse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddWarehouseVO {

	private WarehouseDetails warehouseDetails;
	private String message;
	
	public WarehouseDetails getWarehouseDetails() {
		return warehouseDetails;
	}
	public void setWarehouseDetails(WarehouseDetails warehouseDetails) {
		this.warehouseDetails = warehouseDetails;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}