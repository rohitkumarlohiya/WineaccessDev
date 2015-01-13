package com.wineaccess.security.masterdata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataDeleteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136000901263869690L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MasterDataDeleteVO(String message) {
		super();
		this.message = message;
	}

	public MasterDataDeleteVO() {
		super();
	}
	
	
}
