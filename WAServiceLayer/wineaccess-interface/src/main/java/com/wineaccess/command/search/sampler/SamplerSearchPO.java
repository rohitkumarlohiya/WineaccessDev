package com.wineaccess.command.search.sampler;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;
@XmlRootElement(name="samplerSearch")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SamplerSearchPO extends AbstractSearchPO {

	@Pattern(regexp = RegExConstants.SEARCH_FIELDS_FOR_SAMPLER, message = "SAMPLER_ERROR_131")
	private String searchBy = "";

	@Pattern(regexp = RegExConstants.SEARCH_TYPE, message = "INVALID_PARAM_ERROR_105")
	private String searchType = "B";

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}	
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_SAMPLER, message = "SAMPLER_ERROR_132")
	public String getSortBy() {
		return sortBy;
	}
}
