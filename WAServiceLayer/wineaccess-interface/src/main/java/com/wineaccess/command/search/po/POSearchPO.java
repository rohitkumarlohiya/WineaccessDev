package com.wineaccess.command.search.po;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="poSearch")
public class POSearchPO extends AbstractSearchPO {

	private String searchBy = "";
	@Pattern(regexp=RegExConstants.STATUS_TYPE,message="LIST_PO_ERROR_101")
	private String statusType = "";

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
}
