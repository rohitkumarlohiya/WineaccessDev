/**
 * 
 */
package com.wineaccess.common;

import java.io.Serializable;

/**
 * @author anurag.jain3
 *
 */
public abstract class SearchVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2568904125798071131L;
	protected int offSet;
	protected int limit;
	protected String keyword;
	protected int totalCount;
	protected int count;
	protected T searchResult;
	
	
	
	public SearchVO(){
		super();
		offSet = 1;
		limit = 10;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getOffSet() {
		return offSet;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public T getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(T searchResult) {
		this.searchResult = searchResult;
	}
	
	
	

}
