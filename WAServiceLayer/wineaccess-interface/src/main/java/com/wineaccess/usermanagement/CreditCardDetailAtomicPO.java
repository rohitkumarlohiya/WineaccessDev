package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="creditCardDetail")
@XmlAccessorType(XmlAccessType.FIELD)

public class CreditCardDetailAtomicPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	@NotNull(message="USER118")
	@XmlElement(name="userId")
	private Long userId;
	
	@NotNull(message="USER131")
	@XmlElement(name="creditCardId")
	private Long creditCardId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

	


}
