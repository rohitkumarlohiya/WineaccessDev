package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlRootElement(name="viewDistributionCentre")
public class ViewDistributionCentrePO extends BasePO implements Serializable {

	private static final long serialVersionUID = -7876644666004685532L;
	
	@NotEmpty(message="VIEW_DISTRIBUTION_CENTRE_ERROR_121")
	@Pattern(regexp=RegExConstants.DIGITS,message="VIEW_DISTRIBUTION_CENTRE_ERROR_122")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
