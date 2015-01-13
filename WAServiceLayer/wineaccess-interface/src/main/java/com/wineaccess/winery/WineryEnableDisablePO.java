package com.wineaccess.winery;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * PO to enable/disable winery
 */
@XmlRootElement(name="wineryEnableDisable")
public class WineryEnableDisablePO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Valid
	@NotNull(message = "ENABLE_DIASBLE_WINERY_ERROR_104")
	@ValidatingListAnnotation(message="ENABLE_DIASBLE_WINERY_ERROR_105")
	private List<Long> wineryIds;
	
	@NotNull(message="ENABLE_DIASBLE_WINERY_ERROR_101")
	@Pattern(regexp = RegExConstants.ENABLE_DISABLE_STATUS, message = "ENABLE_DIASBLE_WINERY_ERROR_102")
	private String status;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "ENABLE_DIASBLE_WINERY_ERROR_103")
	private String isForceDelete;

	public List<Long> getWineryIds() {
		return wineryIds;
	}

	public void setWineryIds(List<Long> wineryIds) {
		this.wineryIds = wineryIds;
	}

	public String getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
