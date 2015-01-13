package com.wineaccess.security.masterdatatype;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement
public class ListMasterDataTypesPO extends BasePO{
	
	
	private String status = "all";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
