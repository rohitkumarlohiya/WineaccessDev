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
@XmlRootElement(name="addressDetail")
@XmlAccessorType(XmlAccessType.FIELD)

public class AddressDetailAtomicPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	@NotNull(message="USER118")
	@XmlElement(name="userId")
	private Long userId;
	
	@NotNull(message="USER119")
	@XmlElement(name="addressId")
	private Long addressId;

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


}
