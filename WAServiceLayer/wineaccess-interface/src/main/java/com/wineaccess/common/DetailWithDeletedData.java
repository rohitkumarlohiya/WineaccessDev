package com.wineaccess.common;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;

@XmlRootElement(name="detail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class DetailWithDeletedData extends IdentityPO {

@Pattern(regexp = RegExConstants.BOOLEAN, message = "DETAIL_ERROR_101")
private String isShowDeletedData;

	
	public Long getId() {
		return super.getId();
	}
	/**
	 * @return the isShowDeletedData
	 */
	public String getIsShowDeletedData() {
		return isShowDeletedData;
	}
	/**
	 * @param isShowDeletedData the isShowDeletedData to set
	 */
	public void setIsShowDeletedData(String isShowDeletedData) {
		this.isShowDeletedData = isShowDeletedData;
	}

	
}
