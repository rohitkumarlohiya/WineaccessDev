package com.wineaccess.wine;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.common.DateRangeCriteriaPO;
import com.wineaccess.common.RangeSearchCriteriaPO;

public class WineAdvanceSearchCriteriaPO implements Serializable {

	private List<SearchCriteriaPO> searchCriterias;

	private RangeSearchCriteriaPO<Float> userRatingRange;

	private RangeSearchCriteriaPO<BigDecimal> revenueRange;

	private DateRangeCriteriaPO offerDateRange;

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
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

	public RangeSearchCriteriaPO<Float> getUserRatingRange() {
		return userRatingRange;
	}

	public void setUserRatingRange(RangeSearchCriteriaPO<Float> userRatingRange) {
		this.userRatingRange = userRatingRange;
	}


}

