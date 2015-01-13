package com.wineaccess.security.masterdata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataLastUpdatedVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8719794953914723252L;
	private MasterDataCustModel masterData;

	public MasterDataCustModel getMasterData() {
		return masterData;
	}

	public void setMasterData(MasterDataCustModel masterData) {
		this.masterData = masterData;
	}

	public MasterDataLastUpdatedVO(MasterDataCustModel masterData) {
		super();
		this.masterData = masterData;
	}

	public MasterDataLastUpdatedVO() {
		super();
	}

	
}
