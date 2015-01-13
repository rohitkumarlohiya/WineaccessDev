package com.wineaccess.wineOWS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * view VO of wine historical ows data
 */

@XmlRootElement
@XmlType(name="viewWineOws")
public class ViewWineOwsVO extends WineOwsModel implements Serializable{

	private static final long serialVersionUID = 8389893037577001872L;
	
	private long id;
	private long wineId;
	private String wineName;
	private long productId;
	
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
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
	public String getWineName() {
		return wineName;
	}
	public void setWineName(String wineName) {
		this.wineName = wineName;
	}
}
