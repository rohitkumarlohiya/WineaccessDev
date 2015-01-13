package com.wineaccess.wineryOWS;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name="viewWineryOws")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ViewWineryOwsPO extends BasePO implements Serializable {

	private static final long serialVersionUID = -5746021568837740557L;

	@NotNull(message = "WINERY_OWS_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINERY_OWS_ERROR_102")
	private String wineryid;

	public String getWineryid() {
		return wineryid;
	}

	public void setWineryid(String wineryid) {
		this.wineryid = wineryid;
	}

}
