package com.wineaccess.security.masterdata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement
public class MasterDataPO extends BasePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5533955929788401899L;
	private String masterDataTypeName;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMasterDataTypeName() {
		return masterDataTypeName;
	}

	public void setMasterDataTypeName(String masterDataTypeName) {
		this.masterDataTypeName = masterDataTypeName;
	}
}
