package com.wineaccess.security.masterdatatype;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class MasterDataTypePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2879057461758940654L;
	
	private String name;
	private String description;
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
