package com.wineaccess.wine;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WinePickUpLocationAddress {

	private Long addressType;

	private Map<String, String> address;

	public Map<String, String> getAddress() {
		return address;
	}

	public void setAddress(Map<String, String> address) {
		this.address = address;
	}

	public Long getAddressType() {
		return addressType;
	}

	public void setAddressType(Long addressType) {
		this.addressType = addressType;
	}

	public WinePickUpLocationAddress(Long addressType,
			Map<String, String> address) {
		super();
		this.addressType = addressType;
		this.address = address;
	}

	public WinePickUpLocationAddress() {
		super();
	}

}
