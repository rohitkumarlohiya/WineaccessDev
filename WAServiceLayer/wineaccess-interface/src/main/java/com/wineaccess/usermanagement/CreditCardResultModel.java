package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType


public class CreditCardResultModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	
	private String creditCardNumber;
	private String creditCardId;	
	private String cardHolderName;
	private Map<String,String> creditCartType;
	private Boolean isDefault;
	
	private String expirationDate;
	
	private String addressId;
	@XmlElement
	private UserAddressResultModel address;


	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
		
	public Map<String, String> getCreditCartType() {
		return creditCartType;
	}
	public void setCreditCartType(Map<String, String> creditCartType) {
		this.creditCartType = creditCartType;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCreditCardId() {
		return creditCardId;
	}
	public void setCreditCardId(String creditCardId) {
		this.creditCardId = creditCardId;
	}
	public UserAddressResultModel getAddress() {
		return address;
	}
	public void setAddress(UserAddressResultModel address) {
		this.address = address;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	


}
