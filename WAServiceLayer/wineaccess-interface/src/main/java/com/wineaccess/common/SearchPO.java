/**
 * 
 */
package com.wineaccess.common;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.commad.search.users.SearchCriteriaPO;

/**
 * @author anurag.jain3
 * 
 */

public abstract class SearchPO extends AbstractSearchPO implements Serializable {

	protected static final long serialVersionUID = 7407102575342947119L;
	protected List<Long> searchFields;
	protected List<SearchCriteriaPO> searchCriterias;

	public SearchPO() {
		super();
		setSortOrder("0");
	}

	public List<Long> getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(List<Long> searchFields) {
		this.searchFields = searchFields;
	}

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_NEWSLETTER, message = "NEWSLETTER118")
	public String getSortBy() {
		return sortBy;
	}
}
