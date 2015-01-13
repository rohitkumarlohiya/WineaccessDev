package com.wineaccess.commad.search.users;

import java.io.Serializable;

public class RangeSearchCriteriaPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3813807196539384296L;
	

	private String fromSearchCriteriaKeyword; 
	private String toSearchCriteriaKeyword;
	
	public RangeSearchCriteriaPO() {
		super();
	} 
	
	
	
	
	public String getFromSearchCriteriaKeyword() {
		return fromSearchCriteriaKeyword;
	}
	public void setFromSearchCriteriaKeyword(String fromSearchCriteriaKeyword) {
		this.fromSearchCriteriaKeyword = fromSearchCriteriaKeyword;
	}
	public String getToSearchCriteriaKeyword() {
		return toSearchCriteriaKeyword;
	}
	public void setToSearchCriteriaKeyword(String toSearchCriteriaKeyword) {
		this.toSearchCriteriaKeyword = toSearchCriteriaKeyword;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
