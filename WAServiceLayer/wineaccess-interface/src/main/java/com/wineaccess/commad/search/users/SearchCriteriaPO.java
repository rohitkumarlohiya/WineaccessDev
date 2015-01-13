package com.wineaccess.commad.search.users;

import java.io.Serializable;

public class SearchCriteriaPO implements Serializable {

	private int searchCriteriaId;
	private String searchCriteriaKeyword;

	public int getSearchCriteriaId() {
		return searchCriteriaId;
	}

	public void setSearchCriteriaId(int searchCriteriaId) {
		this.searchCriteriaId = searchCriteriaId;
	}

	public String getSearchCriteriaKeyword() {
		return searchCriteriaKeyword.trim();
	}

	public void setSearchCriteriaKeyword(String searchCriteriaKeyword) {
		this.searchCriteriaKeyword = searchCriteriaKeyword;
	}
}
