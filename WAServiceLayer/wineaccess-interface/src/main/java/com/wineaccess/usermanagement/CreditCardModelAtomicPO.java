package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;



/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="addCreditCard")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class CreditCardModelAtomicPO  extends CreditCardModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	private Long userId;
	@NotNull(message="USER125")
	@Override
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CARD_TYPE, message=MasterDataErrorCode.Constants.CARD_TYPE)
	public Long getCreditCartTypeId() {
		return super.getCreditCartTypeId();
	}

	@NotBlank(message="USER126")
	@Override
	public String getExpirationDate() {
		return super.getExpirationDate();
	}
	@NotNull(message="USER127")
	@Override
	public String getCreditCardNumber() {
		return super.getCreditCardNumber();
	}
	@NotNull(message="USER118")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@NotNull(message="USER128")
	public UserAddressModel getAddress() {
		return super.getAddress();
	}
}
