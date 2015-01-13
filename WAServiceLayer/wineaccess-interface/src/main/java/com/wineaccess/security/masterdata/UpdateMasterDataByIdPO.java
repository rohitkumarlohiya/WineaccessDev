package com.wineaccess.security.masterdata;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateMasterDataByIdPO extends MasterDataPO {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
