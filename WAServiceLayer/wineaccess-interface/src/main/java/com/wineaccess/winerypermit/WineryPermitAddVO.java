package com.wineaccess.winerypermit;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;



/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryPermitAddVO extends BasePO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;


	private Long wineryId;
	private String message;

	public WineryPermitAddVO(){

	}
	public WineryPermitAddVO(Long wineryId, String message){
		this.wineryId = wineryId;
		this.message = message;
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
