package com.wineaccess.wineryOWS;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.JaxbDateSerializer;

/**
 * @author gaurav.agarwal1 
 * PO of the winery historical OWS data
 */
@XmlRootElement(name = "addUpdateWineryOws")
public class AddUpdateWineryOwsPO extends WineryOwsModel implements Serializable {

	private static final long serialVersionUID = -2746771888117788545L;

	@NotNull(message = "WINERY_OWS_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS_LOGN_RANGE, message = "WINERY_OWS_ERROR_102")
	private String wineryid;

	public String getWineryid() {
		return wineryid;
	}

	public void setWineryid(String wineryid) {
		this.wineryid = wineryid;
	}

	@Override
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getCeraCertStartDate() {
		return ceraCertStartDate;
	}

	@Override
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getCeraCertEndDate() {
		return ceraCertEndDate;
	}

}
