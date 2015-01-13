package com.wineaccess.wineryimporter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WIaddress")
public class WineryImporterAddressBasicVO  implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	private Long addressId;	
	private Long wineryId;
	private Long importerId;
	private String message;
	@XmlElement(name="addressType")
	@SerializedName(value="addressType")
	private String address_Type;
	private String address;
	private String phone;

	public WineryImporterAddressBasicVO(Long addressId, Long wineryId, Long importerId, String message){
		this.addressId = addressId;
		this.wineryId = wineryId;
		this.importerId = importerId;
		this.message = message;
	}
	
	public WineryImporterAddressBasicVO(Long addressId, Long wineryId, Long importerId, String message, String addressType, String address, String phoneNumber){
		this(addressId,wineryId,importerId,message);
		
		this.address_Type = addressType;
		this.address = address;
		this.phone = phoneNumber;
		
	}
	
	public WineryImporterAddressBasicVO(){
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getAddress_Type() {
		return address_Type;
	}

	public void setAddress_Type(String address_Type) {
		this.address_Type = address_Type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phoneNumber) {
		this.phone = phoneNumber;
	}



}
