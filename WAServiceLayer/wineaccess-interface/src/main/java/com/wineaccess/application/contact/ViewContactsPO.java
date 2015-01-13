package com.wineaccess.application.contact;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="contact")
public class ViewContactsPO extends AbstractSearchPO implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	@XmlElement(name="wineryId")
	private Long wineryId;
	@XmlElement(name="importerId")
	private Long importerId;
	public Long getWineryId() {
		return wineryId;
	}
	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}
	public Long getImporterId() {
		return importerId;
	}
	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}
		
}
