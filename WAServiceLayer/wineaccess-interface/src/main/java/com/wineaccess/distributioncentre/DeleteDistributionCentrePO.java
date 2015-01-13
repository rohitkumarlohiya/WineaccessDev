package com.wineaccess.distributioncentre;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * PO for delete distribution centre
 */
@XmlRootElement(name="deleteDistributionCentre")
public class DeleteDistributionCentrePO extends BasePO implements Serializable {

	private static final long serialVersionUID = -1770759314698583594L;
	
	@Valid
	@NotNull(message = "DELETE_DISTRIBUTION_CENTRE_ERROR_124")
	@Size(min = 1,message = "DELETE_DISTRIBUTION_CENTRE_ERROR_125")
	private List<Long> ids;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "DELETE_DISTRIBUTION_CENTRE_ERROR_126")
	private String isForceDelete;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

}
