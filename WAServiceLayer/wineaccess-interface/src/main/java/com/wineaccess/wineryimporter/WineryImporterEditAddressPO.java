package com.wineaccess.wineryimporter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="WIEditAddress")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryImporterEditAddressPO extends WineryImporterAddressPO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	@NotNull(message="WI_ADDRESS_110")

	public Long getAddressId() {
		return super.getAddressId();
	}



}
