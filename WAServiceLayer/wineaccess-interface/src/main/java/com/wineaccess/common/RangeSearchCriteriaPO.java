package com.wineaccess.common;

import java.io.Serializable;

public class RangeSearchCriteriaPO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3813807196539384296L;
	

	protected T fromSearchCriteriaKeyword; 
	protected T toSearchCriteriaKeyword;
	
	public RangeSearchCriteriaPO() {
		super();
	} 
	
	
	
	
	
	public T getFromSearchCriteriaKeyword() {
		return fromSearchCriteriaKeyword;
	}





	public void setFromSearchCriteriaKeyword(T fromSearchCriteriaKeyword) {
		this.fromSearchCriteriaKeyword = fromSearchCriteriaKeyword;
	}





	public T getToSearchCriteriaKeyword() {
		return toSearchCriteriaKeyword;
	}





	public void setToSearchCriteriaKeyword(T toSearchCriteriaKeyword) {
		this.toSearchCriteriaKeyword = toSearchCriteriaKeyword;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
