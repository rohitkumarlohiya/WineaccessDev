package com.wineaccess.job.master.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "masterDataType")
public class MasterDataTypeJobPO implements Serializable {
	
	private List<MasterDataTypeJobModel> addUpdateMasterDataType = new ArrayList<MasterDataTypeJobModel>();

	public List<MasterDataTypeJobModel> getAddUpdateMasterDataType() {
		return addUpdateMasterDataType;
	}

	public void setAddUpdateMasterDataType(List<MasterDataTypeJobModel> addUpdateMasterDataType) {
		this.addUpdateMasterDataType = addUpdateMasterDataType;
	}
}
