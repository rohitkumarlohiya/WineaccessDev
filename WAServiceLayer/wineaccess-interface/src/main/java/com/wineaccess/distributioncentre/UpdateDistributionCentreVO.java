package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1 
 * VO for update distribution centre
 */
@XmlRootElement
@XmlType(name = "updateDistributionCentre")
public class UpdateDistributionCentreVO implements Serializable {

	private static final long serialVersionUID = -720167541085972744L;

	private long id;
	private String message;
	
	public UpdateDistributionCentreVO(){
		
	}
	
	public UpdateDistributionCentreVO(long id, String message){
		this.id = id;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
