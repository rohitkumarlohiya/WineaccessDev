package com.wineaccess.winepermit;

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
@XmlType(name="WinePermitAdd")
public class WinePermitAddVO extends BasePO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;


	private Long wineId;
	private String message;

	public WinePermitAddVO(){

	}
	public WinePermitAddVO(Long wineId, String message){
		this.wineId = wineId;
		this.message = message;
	}
	public Long getWineryId() {
		return wineId;
	}
	public void setWineryId(Long wineryId) {
		this.wineId = wineryId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
