package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;

/**
 * @author gaurav.agarwal1
 * PO of update distribution centre
 */
@XmlRootElement(name="updateDistributionCentre")
public class UpdateDistributionCentrePO extends DistributionCentreDetails implements Serializable {

	private static final long serialVersionUID = 6341709666327123478L;
	
	@NotEmpty(message="UPDATE_DISTRIBUTION_CENTRE_ERROR_121")
	@Pattern(regexp=RegExConstants.DIGITS,message="UPDATE_DISTRIBUTION_CENTRE_ERROR_122")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
