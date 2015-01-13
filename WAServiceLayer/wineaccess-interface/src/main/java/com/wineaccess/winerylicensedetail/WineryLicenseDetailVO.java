package com.wineaccess.winerylicensedetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "wineryLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryLicenseDetailVO implements Serializable {

	private static final long serialVersionUID = 3431857750878328287L;

	private Long id;
	private Long wineryId;
	private String message;

	public WineryLicenseDetailVO() {
		super();
	}

	public WineryLicenseDetailVO(String message) {
		super();
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
