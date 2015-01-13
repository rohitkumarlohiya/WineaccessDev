package com.wineaccess.wineOWS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * update VO of wine historical ows data 
 */
@XmlRootElement
@XmlType(name = "updateWineOws")
public class UpdateWineOwsVO implements Serializable {

	private static final long serialVersionUID = -2378966350295268676L;

	private long id;
	private long wineId;
	private long productId;
	private String message;
	
	public UpdateWineOwsVO(){
		
	}
	
	public UpdateWineOwsVO(String message){
		this.message = message;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWineId() {
		return wineId;
	}

	public void setWineId(long wineId) {
		this.wineId = wineId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	
}
