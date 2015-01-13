package com.wineaccess.wine;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 * PO of the delete wine and take the wine ids as parameter
 */
@XmlRootElement(name="wineDelete")
public class WineDeletePO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Valid
	@NotNull(message="WINE_ERROR_128")
	@Size(min = 1,message = "WINE_ERROR_128")
	private List<Long> productIds;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_ERROR_129")
	private String isForceDelete;

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> wineIds) {
		this.productIds = wineIds;
	}

	public String getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

}
