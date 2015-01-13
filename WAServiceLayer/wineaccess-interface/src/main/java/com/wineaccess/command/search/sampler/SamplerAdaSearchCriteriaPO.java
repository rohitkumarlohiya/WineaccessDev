package com.wineaccess.command.search.sampler;

import java.io.Serializable;
import java.util.List;

import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.common.DateRangeCriteriaPO;

public class SamplerAdaSearchCriteriaPO implements Serializable {

	private List<SearchCriteriaPO> searchCriterias;

	private DateRangeCriteriaPO lastOfferDateRange;

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}

	public DateRangeCriteriaPO getLastOfferDateRange() {
		return lastOfferDateRange;
	}

	public void setLastOfferDateRange(DateRangeCriteriaPO lastOfferDateRange) {
		this.lastOfferDateRange = lastOfferDateRange;
	}
}
