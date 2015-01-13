package com.wineaccess.command.search.importer;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;
@XmlRootElement
public class ImporterSearchVO extends ImporterSearchCriteriaVO {

	private List<ImporterModelVO> importersModel;

	public ImporterSearchVO() {
	}

	public ImporterSearchVO(ImporterSearchCriteriaVO importerVO,
			List<ImporterModelVO> importersModel) {
		this.importersModel = importersModel;
		BeanUtils.copyProperties(importerVO, this);
	}
	
	public List<ImporterModelVO> getImportersModel() {
		return importersModel;
	}

	public void setImportersModel(List<ImporterModelVO> importersModel) {
		this.importersModel = importersModel;
	}
}
