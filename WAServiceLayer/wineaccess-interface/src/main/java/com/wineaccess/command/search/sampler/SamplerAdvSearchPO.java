package com.wineaccess.command.search.sampler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SamplerAdvSearchPO extends SamplerSearchPO {

	private SamplerAdaSearchCriteriaPO advanceSearchCriteria;

	public SamplerAdaSearchCriteriaPO getAdvanceSearchCriteria() {
		return advanceSearchCriteria;
	}

	public void setAdvanceSearchCriteria(
			SamplerAdaSearchCriteriaPO advanceSearchCriteria) {
		this.advanceSearchCriteria = advanceSearchCriteria;
	}
}
