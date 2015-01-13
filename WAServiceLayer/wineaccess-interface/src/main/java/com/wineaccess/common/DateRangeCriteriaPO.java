package com.wineaccess.common;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class DateRangeCriteriaPO implements Serializable {
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date fromSearchCriteriaKeyword;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date toSearchCriteriaKeyword;

	public Date getFromSearchCriteriaKeyword() {
		return fromSearchCriteriaKeyword;
	}

	public void setFromSearchCriteriaKeyword(Date fromSearchCriteriaKeyword) {
		this.fromSearchCriteriaKeyword = fromSearchCriteriaKeyword;
	}

	public Date getToSearchCriteriaKeyword() {
		return toSearchCriteriaKeyword;
	}

	public void setToSearchCriteriaKeyword(Date toSearchCriteriaKeyword) {
		this.toSearchCriteriaKeyword = toSearchCriteriaKeyword;
	}

}
