package com.wineaccess.warehouse;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="warehouse")
public class ViewWarehousePO extends BasePO implements Serializable{

	private static final long serialVersionUID = -5533955929788401899L;
	
	@NotNull(message="WAREHOUSE120")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message="WAREHOUSE121")
	private String warehouseId;

	public String getWarehouseId() {
		if(warehouseId != null){
			warehouseId = warehouseId.trim();
		}
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
}