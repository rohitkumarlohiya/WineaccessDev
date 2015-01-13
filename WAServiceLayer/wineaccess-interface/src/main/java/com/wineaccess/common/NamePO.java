package com.wineaccess.common;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;


@XmlRootElement(name="masterDataTypeSearchRequest")
public class NamePO extends BasePO {
	
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message = "MASTERDATA45")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
