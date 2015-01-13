package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.application.validation.annotation.WineAccessEmbedded;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;



/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class CreditCardModel  extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	@XmlElement
	@WineAccessEmbedded
	private UserAddressModel address;
	private String creditCardId;	
	private String cardHolderName;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CARD_TYPE, message=MasterDataErrorCode.Constants.CARD_TYPE)
	protected Long creditCartTypeId;
	private String expirationDate;
	private String addressId;
	private Boolean isDefault = false;
	private String creditCardNumber;



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

	public Long getCreditCartTypeId() {
		return creditCartTypeId;
	}
	public void setCreditCartTypeId(Long creditCartTypeId) {
		this.creditCartTypeId = creditCartTypeId;
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
	public UserAddressModel getAddress() {
		return address;
	}
	public void setAddress(UserAddressModel address) {
		this.address = address;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


}
