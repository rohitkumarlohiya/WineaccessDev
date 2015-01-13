package com.wineaccess.commad.search.masterdata;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.AbstractSearchPO;
@XmlRootElement
public class MasterDataSearchPO extends AbstractSearchPO {
	
	private String masterDataTypeName;

	public String getMasterDataTypeName() {
		return masterDataTypeName;
	}

	public void setMasterDataTypeName(String masterDataTypeName) {
		this.masterDataTypeName = masterDataTypeName;
	}
}
