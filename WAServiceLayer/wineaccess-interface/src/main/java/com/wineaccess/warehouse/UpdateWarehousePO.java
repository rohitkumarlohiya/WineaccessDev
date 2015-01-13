package com.wineaccess.warehouse;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;

@XmlRootElement(name="warehouseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UpdateWarehousePO extends AddWarehousePO {

	@NotNull(message="WAREHOUSE120")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message="WAREHOUSE121")
	private String warehouseId;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
}
