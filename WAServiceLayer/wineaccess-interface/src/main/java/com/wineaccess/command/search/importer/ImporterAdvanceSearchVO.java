package com.wineaccess.command.search.importer;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;

@XmlRootElement
public class ImporterAdvanceSearchVO extends ImporterAdvanceSearchPO {

	private List<ImporterModelVO> importersModel;

	private int totalRecordCount;
	private int count;
	private int winerySpecificCount;

	public ImporterAdvanceSearchVO() {
	}

	public ImporterAdvanceSearchVO(List<ImporterModelVO> importersModel,
			ImporterAdvanceSearchPO importerAdvanceSearchPO,
			int totalRecordCount, int count,int winerySpecificCount) {
		this.importersModel = importersModel;
		BeanUtils.copyProperties(importerAdvanceSearchPO, this);
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		this.winerySpecificCount = winerySpecificCount;
	}
	
	public ImporterAdvanceSearchVO(List<ImporterModelVO> importersModel,
		ImporterAdvanceSearchPO importerAdvanceSearchPO,
		int totalRecordCount, int count) {
	this.importersModel = importersModel;
	BeanUtils.copyProperties(importerAdvanceSearchPO, this);
	this.totalRecordCount = totalRecordCount;
	this.count = count;
	
}

	public List<ImporterModelVO> getImportersModel() {
		return importersModel;
	}

	public void setImportersModel(List<ImporterModelVO> importersModel) {
		this.importersModel = importersModel;
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

	public int getWinerySpecificCount() {
	    return winerySpecificCount;
	}

	public void setWinerySpecificCount(int winerySpecificCount) {
	    this.winerySpecificCount = winerySpecificCount;
	}
	
}
