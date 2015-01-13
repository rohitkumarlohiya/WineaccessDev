package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;



/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="addAddress")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserAddressModelAtomicPO extends UserAddressModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	
   private Long userId;
	

	@Override
	@NotBlank(message="USER120")
	public String getZipCode() {
		return super.getZipCode();
	}
	
	@Override
	@NotEmpty(message="USER121")
	@Pattern(regexp = RegExConstants.DIGITS, message = "USER163")
	public String getAddressType() {
		return super.getAddressType();
	}
	
	@Override
	@NotEmpty(message="USER122")
	@Pattern(regexp = RegExConstants.DIGITS, message = "USER165")
	public String getCountryId() {
		return super.getCountryId();
	}
	
	@NotNull(message="USER118")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
