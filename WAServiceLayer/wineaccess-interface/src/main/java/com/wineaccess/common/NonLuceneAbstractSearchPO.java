package com.wineaccess.common;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

public abstract class NonLuceneAbstractSearchPO extends BasePO {

	private String keyword = "";
	@Pattern(regexp = RegExConstants.OFFSET, message = "INVALID_PARAM_ERROR_103")
	private String offSet = "1";
	@Pattern(regexp = RegExConstants.LIMIT, message = "INVALID_PARAM_ERROR_102")
	private String limit = "10";
	protected String sortBy = "id";
	@Pattern(regexp = RegExConstants.SORT_ORDER, message = "INVALID_PARAM_ERROR_101")
	private String sortOrder = "0";
	
	private List<String> exclusions = new ArrayList<String>();
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	public String getOffSet() {
		return offSet;
	}

	public void setOffSet(String offSet) {
		this.offSet = offSet;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	@XmlElementWrapper(name="exclusionList")
	public List<String> getExclusions() {
		return exclusions;
	}

	public void setExclusions(List<String> exclusions) {
		this.exclusions = exclusions;
	}
}
