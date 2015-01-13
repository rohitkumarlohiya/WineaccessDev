package com.wineaccess.wineryOWS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlType(name = "addUpdateWineryOws")
public class AddUpdateWineryOwsVO implements Serializable {

	private static final long serialVersionUID = 1684458796140366335L;

	private long id;
	private long wineryId;
	private String message;
	
	public AddUpdateWineryOwsVO(){
		
	}
	
	public AddUpdateWineryOwsVO(String message){
		this.message = message;
	}
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
