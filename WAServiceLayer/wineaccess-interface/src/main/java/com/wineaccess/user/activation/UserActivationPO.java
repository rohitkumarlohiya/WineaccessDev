package com.wineaccess.user.activation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name = "userActivation")
public class UserActivationPO extends BasePO implements Serializable {

	private static final long serialVersionUID = -7946946061541023184L;
	@NotBlank(message="USERACTIVATION103")
	private String activationCode;

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
