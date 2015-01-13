package com.wineaccess.commad.search.users;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;
@XmlRootElement
public class UserSearchPO extends AbstractSearchPO {

	
	private String searchBy;
	private String userType;

	/** Possible value for Search Type - B For Basic and A for Advance */
	@Pattern(regexp = RegExConstants.SEARCH_TYPE, message = "INVALID_PARAM_ERROR_105")
	private String searchType = "B";
	@XmlElement(name="advanceSearchParams")
	@SerializedName(value="advanceSearchParams")
	private UserSearchAdvancePO advanceSearchParams;

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public UserSearchAdvancePO getAdvanceSearchParams() {
		return advanceSearchParams;
	}

	public void setAdvanceSearchParams(UserSearchAdvancePO advanceSearchParams) {
		this.advanceSearchParams = advanceSearchParams;
	}

	
	
	
	
}
