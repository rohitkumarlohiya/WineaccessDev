package com.wineaccess.security.masterdatatype;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataTypeGetByIdVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8198999952608749903L;
	private MasterDataTypeCustomModel masterDataType;

	public MasterDataTypeCustomModel getMasterDataType() {
		return masterDataType;
	}

	public void setMasterDataType(MasterDataTypeCustomModel masterDataType) {
		this.masterDataType = masterDataType;
	}

	public MasterDataTypeGetByIdVO(MasterDataTypeCustomModel masterDataType) {
		super();
		this.masterDataType = masterDataType;
	}

	public MasterDataTypeGetByIdVO() {
		super();
	}

	
	
	
	
	

}
