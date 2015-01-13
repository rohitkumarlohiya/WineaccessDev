package com.wineaccess.security.masterdata;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MultipleDeleteModelForDependency implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1193891956184071215L;
	private int count;
	@XmlElement(name="masterDataList")
	private List<MasterDataCustModel> masterDatas;

	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<MasterDataCustModel> getMasterDatas() {
		return masterDatas;
	}

	public void setMasterDatas(List<MasterDataCustModel> masterDatas) {
		this.masterDatas = masterDatas;
	}

	public MultipleDeleteModelForDependency() {
		super();
	}

	public MultipleDeleteModelForDependency(int count,
			List<MasterDataCustModel> masterDatas) {
		super();
		this.count = count;
		this.masterDatas = masterDatas;
	}

	

}
