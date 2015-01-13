package com.wineaccess.util.command;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="state")
public class StateByIdPO extends BasePO {
	
	@NotEmpty(message = "STATE104")
	@Pattern(regexp = RegExConstants.DIGITS, message = "STATE103")
	private String stateId;

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
}
