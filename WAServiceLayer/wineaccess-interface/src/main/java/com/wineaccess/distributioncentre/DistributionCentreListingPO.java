package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

/**
 * @author gaurav.agarwal1
 * PO for the listing of distribution centre
 */
@XmlRootElement(name="distributionCentreListing")
public class DistributionCentreListingPO extends AbstractSearchPO implements Serializable {

	private static final long serialVersionUID = 1488270507898063347L;
/*	
	@Size(min=1,message="LIST_DISTRIBUTION_CENTRE_ERROR_130")
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING,message="LIST_DISTRIBUTION_CENTRE_ERROR_129")
	private String locationId;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}*/

	

}
