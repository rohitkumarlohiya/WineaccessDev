package com.wineaccess.commad.search.users;

import java.io.Serializable;
import java.util.List;

public class UserAdavanceSearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;

	private int offSet;

	private int limit;
	
	private int totalRecordCount;

	private List<SearchCriteriaPO> searchCriterias;

	private List<UserModelVO> userModels;

	public UserAdavanceSearchVO() {
	}
	
	public UserAdavanceSearchVO(int count, int offSet, int limit, List<UserModelVO> userModels, List<SearchCriteriaPO> searchCriterias) {
		this.count = count;
		this.offSet = offSet;
		this.limit = limit;
		this.userModels = userModels;
		this.searchCriterias = searchCriterias;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}

	public List<UserModelVO> getUserModels() {
		return userModels;
	}

	public void setUserModels(List<UserModelVO> userModels) {
		this.userModels = userModels;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
}
