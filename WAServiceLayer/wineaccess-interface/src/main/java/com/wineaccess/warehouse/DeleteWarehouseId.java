package com.wineaccess.warehouse;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.wineaccess.application.constants.RegExConstants;

public class DeleteWarehouseId {

	@NotNull(message="WAREHOUSE120")
	@Pattern(regexp = RegExConstants.DIGITS, message="WAREHOUSE121")
	private String warehouseId;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
}
