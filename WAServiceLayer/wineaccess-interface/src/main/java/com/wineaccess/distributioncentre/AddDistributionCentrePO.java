package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;

/**
 * @author gaurav.agarwal1
 * PO for add distribution centre location
 */
@XmlRootElement(name="addDistributionCentre")
public class AddDistributionCentrePO extends DistributionCentreDetails implements Serializable {

	private static final long serialVersionUID = -6867381277775359224L;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "ADD_DISTRIBUTION_CENTRE_ERROR_126")
	private String isOverrideDelEntry;

	public String getIsOverrideDelEntry() {
		return isOverrideDelEntry;
	}

	public void setIsOverrideDelEntry(String isOverrideDelEntry) {
		this.isOverrideDelEntry = isOverrideDelEntry;
	}

}
