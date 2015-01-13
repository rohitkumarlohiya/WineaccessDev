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
@XmlRootElement(name="updateAddress")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserEditAddressModelAtomicPO extends UserAddressModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	private Long userId;

	@Override
	@NotNull(message="USER119")
	public String getAddressId() {
		return super.getAddressId();
	}

	@NotNull(message="USER118")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
