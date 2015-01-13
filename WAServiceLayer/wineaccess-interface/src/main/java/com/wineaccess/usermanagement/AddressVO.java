package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="address")
public class AddressVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;


	private Long userId;
	private Long addressId;
	private Long addressPrefId;
	private String message;


	public AddressVO() {
	}

	public AddressVO( Long userId, Long addressId, Long addressPrefId, String message) {
		this.userId = userId;
		this.addressId = addressId;
		this.message = message;
		this.addressPrefId = addressPrefId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getAddressPrefId() {
		return addressPrefId;
	}

	public void setAddressPrefId(Long addressPrefId) {
		this.addressPrefId = addressPrefId;
	}
	
	

}
