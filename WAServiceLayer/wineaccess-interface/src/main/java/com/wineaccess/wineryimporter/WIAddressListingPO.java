package com.wineaccess.wineryimporter;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="addressList")
@XmlAccessorType(XmlAccessType.FIELD)
public class WIAddressListingPO extends AbstractSearchPO implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	@XmlElement(name="wineryId")
	private Long wineryId;
	@XmlElement(name="importerId")
	private Long importerId;
	private Long addressType;
	public Long getWineryId() {
		return wineryId;
	}
	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}
	public Long getImporterId() {
		return importerId;
	}
	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}
	public Long getAddressType() {
		return addressType;
	}
	public void setAddressType(Long addressType) {
		this.addressType = addressType;
	}
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_WINERY_IMPORTER_ADDRESS, message = "WI_ADDRESS_120")
	public String getSortBy() {
		return sortBy;
	}
}
