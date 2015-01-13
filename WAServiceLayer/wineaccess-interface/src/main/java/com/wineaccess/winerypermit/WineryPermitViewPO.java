package com.wineaccess.winerypermit;

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
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="WIViewPermit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryPermitViewPO extends BasePO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	@NotNull(message="WINERY_PERMIT_ERROR_101")
	@Pattern(regexp = RegExConstants.DIGITS, message = "WINERY_PERMIT_ERROR_102")
	private String wineryId;

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		this.wineryId = wineryId;
	}



	

	


}
