package com.wineaccess.wine;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wine")
public class WineAdvanceSearchPO extends WineBasicSearchPO {

	private WineAdvanceSearchCriteriaPO advanceSearchCriteria;
	

	public WineAdvanceSearchCriteriaPO getAdvanceSearchCriteria() {
		return advanceSearchCriteria;
	}

	public void setAdvanceSearchCriteria(
			WineAdvanceSearchCriteriaPO advanceSearchCriteria) {
		this.advanceSearchCriteria = advanceSearchCriteria;
	}

}