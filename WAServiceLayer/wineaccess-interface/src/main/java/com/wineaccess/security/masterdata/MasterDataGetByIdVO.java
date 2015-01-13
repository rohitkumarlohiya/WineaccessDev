package com.wineaccess.security.masterdata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataGetByIdVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1426753466984487469L;
	private MasterDataCustModel masterData;

	public MasterDataCustModel getMasterData() {
		return masterData;
	}

	public void setMasterData(MasterDataCustModel masterData) {
		this.masterData = masterData;
	}

	public MasterDataGetByIdVO(MasterDataCustModel masterData) {
		super();
		this.masterData = masterData;
	}

	public MasterDataGetByIdVO() {
		super();
	}
	
	
	

}
