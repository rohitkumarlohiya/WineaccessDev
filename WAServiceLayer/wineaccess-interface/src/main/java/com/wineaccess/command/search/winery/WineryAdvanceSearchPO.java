package com.wineaccess.command.search.winery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryAdvanceSearchPO extends WinerySearchPO {

	private WineryAdvanceSearchCriteriaPO advanceSearchCriteria;
	

	

	public WineryAdvanceSearchCriteriaPO getAdvanceSearchCriteria() {
		return advanceSearchCriteria;
	}

	public void setAdvanceSearchCriteria(
			WineryAdvanceSearchCriteriaPO advanceSearchCriteria) {
		this.advanceSearchCriteria = advanceSearchCriteria;
	}

		
	

}
