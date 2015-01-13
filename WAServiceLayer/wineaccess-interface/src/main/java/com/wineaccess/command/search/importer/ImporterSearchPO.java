package com.wineaccess.command.search.importer;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement
public class ImporterSearchPO extends AbstractSearchPO {

	@Pattern(regexp = RegExConstants.SEARCH_FIELDS_FOR_IMPORTER, message = "IMPORTER126")
	private String searchBy = "";
	@Pattern(regexp = RegExConstants.IMPORTER_TYPE, message = "IMPORTER130")
	private String typeOfImporter = "";
	/** Possible value for Search Type - B For Basic and A for Advance */
	@Pattern(regexp = RegExConstants.SEARCH_TYPE, message = "INVALID_PARAM_ERROR_105")
	private String searchType = "B";

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getTypeOfImporter() {
		return typeOfImporter;
	}

	public void setTypeOfImporter(String typeOfImporter) {
		this.typeOfImporter = typeOfImporter;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_IMPORTER, message = "IMPORTER125")
	public String getSortBy() {
		return sortBy;
	}
}
