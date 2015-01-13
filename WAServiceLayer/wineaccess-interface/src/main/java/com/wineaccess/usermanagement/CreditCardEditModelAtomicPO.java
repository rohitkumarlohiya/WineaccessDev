package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="updateCreditCard")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class CreditCardEditModelAtomicPO extends CreditCardModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	private Long userId;

	@Override
	@NotNull(message="USER131")
	public String getCreditCardId() {
		return super.getCreditCardId();
	}

	@NotNull(message="USER118")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/*@NotNull(message="USER128")
	public UserAddressModel getAddress() {
		return super.getAddress();
	}*/

}
