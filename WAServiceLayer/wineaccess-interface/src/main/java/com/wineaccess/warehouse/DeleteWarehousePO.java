package com.wineaccess.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="warehouse")
public class DeleteWarehousePO extends BasePO implements Serializable {

	@Valid
	@NotNull(message="WAREHOUSE120")
	@Size(min = 1,message = "WAREHOUSE121")
	private List<Long> warehouseIds = new ArrayList<Long>();
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WAREHOUSE127")
	private String isForceDelete;
	
	public List<Long> getWarehouseIds() {
		return warehouseIds;
	}

	public void setWarehouseIds(List<Long> warehouseIds) {
		this.warehouseIds = warehouseIds;
	}

	public String getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}
	
}
