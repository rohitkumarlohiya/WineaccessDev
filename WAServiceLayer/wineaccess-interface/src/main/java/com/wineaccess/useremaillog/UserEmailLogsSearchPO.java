package com.wineaccess.useremaillog;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.NonLuceneAbstractSearchPO;

@XmlRootElement
public class UserEmailLogsSearchPO extends NonLuceneAbstractSearchPO {

	
	/*@Pattern(regexp = RegExConstants.SEARCH_FIELDS_FOR_EMAIL_LOG, message = "USEREMAILLOG101")
	private String fieldName;
	
	public UserEmailLogsSearchPO() {
		setOffSet("0");
		setSortBy("userId");
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}*/
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_EMAIL_LOG, message = "USEREMAILLOG102")
	public String getSortBy() {
		if(sortBy.isEmpty()){
			sortBy = "deliveryDate";
		}
		return sortBy;
	}
	
}
