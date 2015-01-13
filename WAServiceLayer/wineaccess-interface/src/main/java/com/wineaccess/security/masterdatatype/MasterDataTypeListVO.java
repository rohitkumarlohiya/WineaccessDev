package com.wineaccess.security.masterdatatype;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataTypeListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7422047571902348522L;
	List<MasterDataTypeCustomModel> masterDataTypes;

	public List<MasterDataTypeCustomModel> getMasterDataTypes() {
		return masterDataTypes;
	}

	public void setMasterDataTypes(List<MasterDataTypeCustomModel> masterDataTypes) {
		this.masterDataTypes = masterDataTypes;
	}

	public MasterDataTypeListVO(List<MasterDataTypeCustomModel> masterDataTypes) {
		super();
		this.masterDataTypes = masterDataTypes;
	}

	public MasterDataTypeListVO() {
		
	}
	
	

}
