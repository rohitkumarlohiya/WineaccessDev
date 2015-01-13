package com.wineaccess.command.search.winery;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.common.DateRangeCriteriaPO;
import com.wineaccess.common.RangeSearchCriteriaPO;

public class WineryAdvanceSearchCriteriaPO implements Serializable {
	
	private List<SearchCriteriaPO> searchCriterias;

	private RangeSearchCriteriaPO<Integer> wineRange;

	private RangeSearchCriteriaPO<Integer> activeWineRange;

	private RangeSearchCriteriaPO<BigDecimal> revenueRange;

	private DateRangeCriteriaPO offerDateRange;

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}

	public RangeSearchCriteriaPO<Integer> getWineRange() {
		return wineRange;
	}

	public void setWineRange(RangeSearchCriteriaPO<Integer> wineRange) {
		this.wineRange = wineRange;
	}

	public RangeSearchCriteriaPO<Integer> getActiveWineRange() {
		return activeWineRange;
	}

	public void setActiveWineRange(
			RangeSearchCriteriaPO<Integer> activeWineRange) {
		this.activeWineRange = activeWineRange;
	}

	public RangeSearchCriteriaPO<BigDecimal> getRevenueRange() {
		return revenueRange;
	}

	public void setRevenueRange(RangeSearchCriteriaPO<BigDecimal> revenueRange) {
		this.revenueRange = revenueRange;
	}

	public DateRangeCriteriaPO getOfferDateRange() {
		return offerDateRange;
	}

	public void setOfferDateRange(DateRangeCriteriaPO offerDateRange) {
		this.offerDateRange = offerDateRange;
	}

}
