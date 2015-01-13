package com.wineaccess.security.masterdata;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement
public class MultipleDeleteMasterDataPO extends BasePO {
        @Pattern(regexp="(\\d+){1}((|,|)(\\d+))*", message="INVALIDPARAM106")
	private String multipleMasterDataIds;
	private Boolean confirmStatus;

	public String getMultipleMasterDataIds() {
		return multipleMasterDataIds;
	}

	public void setMultipleMasterDataIds(String multipleMasterDataIds) {
		this.multipleMasterDataIds = multipleMasterDataIds;
	}

	public Boolean getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Boolean confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
}
