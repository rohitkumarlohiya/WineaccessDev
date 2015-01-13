package com.wineaccess.wineryOWS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="viewWineryOws")
public class ViewWineryOwsVO extends WineryOwsModel implements Serializable {

	private static final long serialVersionUID = 848960625434968559L;

	private long id;
	private long wineryId;
	private String wineryName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWineryId() {
		return wineryId;
	}

	public void setWineryId(long wineryId) {
		this.wineryId = wineryId;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

}
