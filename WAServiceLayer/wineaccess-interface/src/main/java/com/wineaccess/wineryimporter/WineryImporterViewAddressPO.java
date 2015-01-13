package com.wineaccess.wineryimporter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;


@XmlRootElement(name="WIViewAddress")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryImporterViewAddressPO extends BasePO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	private Long wineryId;
	private Long importerId;
	@NotNull(message="WI_ADDRESS_110")
	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

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




}
