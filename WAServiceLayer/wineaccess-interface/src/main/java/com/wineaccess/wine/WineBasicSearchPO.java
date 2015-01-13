package com.wineaccess.wine;


import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="wineSearch")
public class WineBasicSearchPO extends AbstractSearchPO {
	
	@Pattern(regexp = RegExConstants.SEARCH_FIELDS_FOR_WINE, message = "WINE_ERROR_135")
	private String searchBy = "";
	@Pattern(regexp = RegExConstants.WINE_TYPE, message = "WINE_ERROR_137")
	private String typeOfWine = "";
	/** Possible value for Search Type - B For Basic and A for Advance */
	@Pattern(regexp = RegExConstants.SEARCH_TYPE, message = "INVALID_PARAM_ERROR_105")
	private String searchType = "B";

	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
	public String getTypeOfWine() {
		return typeOfWine;
	}
	public void setTypeOfWine(String typeOfWine) {
		this.typeOfWine = typeOfWine;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_WINE, message = "WINE_ERROR_134")
	public String getSortBy() {
		return sortBy;
	}
}
