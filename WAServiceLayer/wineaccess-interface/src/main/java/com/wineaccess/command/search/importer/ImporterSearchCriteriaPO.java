package com.wineaccess.command.search.importer;

import java.io.Serializable;
import java.util.List;

import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.common.DateRangeCriteriaPO;
import com.wineaccess.common.RangeSearchCriteriaPO;

@SuppressWarnings("serial")
public class ImporterSearchCriteriaPO implements Serializable {

	private List<SearchCriteriaPO> searchCriterias;

	private RangeSearchCriteriaPO<String> wineRange;

	private RangeSearchCriteriaPO<String> activeWineRange;

	private RangeSearchCriteriaPO<String> revenueRange;

	private DateRangeCriteriaPO offerDateRange;

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}

	public RangeSearchCriteriaPO<String> getWineRange() {
		return wineRange;
	}

	public void setWineRange(RangeSearchCriteriaPO<String> wineRange) {
		this.wineRange = wineRange;
	}

	public RangeSearchCriteriaPO<String> getActiveWineRange() {
		return activeWineRange;
	}

	public void setActiveWineRange(RangeSearchCriteriaPO<String> activeWineRange) {
		this.activeWineRange = activeWineRange;
	}

	public RangeSearchCriteriaPO<String> getRevenueRange() {
		return revenueRange;
	}

	public void setRevenueRange(RangeSearchCriteriaPO<String> revenueRange) {
		this.revenueRange = revenueRange;
	}

	public DateRangeCriteriaPO getOfferDateRange() {
		return offerDateRange;
	}

	public void setOfferDateRange(DateRangeCriteriaPO offerDateRange) {
		this.offerDateRange = offerDateRange;
	}
}
