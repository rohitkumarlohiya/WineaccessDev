package com.wineaccess.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3333859741694718177L;
	Long id;
	String masterDataTypeName;
	String masterDataName;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasterDataTypeName() {
		return masterDataTypeName;
	}

	public void setMasterDataTypeName(String masterDataTypeName) {
		this.masterDataTypeName = masterDataTypeName;
	}

	public String getMasterDataName() {
		return masterDataName;
	}

	public void setMasterDataName(String masterDataName) {
		this.masterDataName = masterDataName;
	}
}