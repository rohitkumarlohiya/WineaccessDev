package com.wineaccess.distributioncentre;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1 
 * VO of add distribution centre
 */

@XmlRootElement
@XmlType(name="addDistributionCentre")
public class AddDistributionCentreVO implements Serializable {

	private static final long serialVersionUID = 6568884755829088014L;

	private long id;
	private String message;
	private boolean existsButDeleted;
	
	public AddDistributionCentreVO(){
		
	}
	
	public AddDistributionCentreVO(long id,String message){
		this.id = id;
		this.message = message;
	}
	
	public AddDistributionCentreVO(long id,boolean isExists,String message){
		this.id = id;
		this.existsButDeleted = isExists;
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

	public boolean isExistsButDeleted() {
		return existsButDeleted;
	}

	public void setExistsButDeleted(boolean existsButDeleted) {
		this.existsButDeleted = existsButDeleted;
	}

}
