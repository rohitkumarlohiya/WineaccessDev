package com.wineaccess.orders.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WinePickUpLocationAddressModel {

	private Boolean isWarehouseAddress;

	private WarehouseModelVO warehouseAddress;

	private WineryImporterAddressModelVO wineryAddressOfWarehouseType;

	
	
	public WinePickUpLocationAddressModel() {
		super();
	}

	public Boolean getIsWarehouseAddress() {
		return isWarehouseAddress;
	}

	public void setIsWarehouseAddress(Boolean isWarehouseAddress) {
		this.isWarehouseAddress = isWarehouseAddress;
	}

	public WarehouseModelVO getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(WarehouseModelVO warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public WineryImporterAddressModelVO getWineryAddressOfWarehouseType() {
		return wineryAddressOfWarehouseType;
	}

	public void setWineryAddressOfWarehouseType(
			WineryImporterAddressModelVO wineryAddressOfWarehouseType) {
		this.wineryAddressOfWarehouseType = wineryAddressOfWarehouseType;
	}

}
