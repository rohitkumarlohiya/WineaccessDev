package com.wineaccess.command.search.importer;

import org.springframework.beans.BeanUtils;


public class ImporterSearchCriteriaVO extends ImporterSearchPO {

	private int totalRecordCount;
	
	private int count;
	
	public ImporterSearchCriteriaVO(){
	}
	
	public ImporterSearchCriteriaVO(ImporterSearchPO importerSearchPO, int totalRecordCount, int count){
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		BeanUtils.copyProperties(importerSearchPO, this);
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
