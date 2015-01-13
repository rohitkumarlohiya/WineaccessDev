package com.wineaccess.security.masterdata;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataListVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8579244116648352090L;
	
	List<MasterDataCustModel> masterDatas;

	public List<MasterDataCustModel> getMasterDatas() {
		return masterDatas;
	}

	public void setMasterDatas(List<MasterDataCustModel> masterDatas) {
		this.masterDatas = masterDatas;
	}

	public MasterDataListVO(List<MasterDataCustModel> masterDatas) {
		super();
		this.masterDatas = masterDatas;
	}

	public MasterDataListVO() {
		super();
	}
	
	

}
