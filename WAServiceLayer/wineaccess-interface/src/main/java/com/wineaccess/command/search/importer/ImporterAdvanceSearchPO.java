package com.wineaccess.command.search.importer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImporterAdvanceSearchPO extends ImporterSearchPO {

	private ImporterSearchCriteriaPO advanceSearchCriteria;

	public ImporterSearchCriteriaPO getAdvanceSearchCriteria() {
		return advanceSearchCriteria;
	}

	public void setAdvanceSearchCriteria(
			ImporterSearchCriteriaPO advanceSearchCriteria) {
		this.advanceSearchCriteria = advanceSearchCriteria;
	}
}
