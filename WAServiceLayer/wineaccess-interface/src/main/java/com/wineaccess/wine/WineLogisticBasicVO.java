package com.wineaccess.wine;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 * VO of view wine and will show the wine details and key metrics
 */
@XmlRootElement
@XmlType(name = "addWine")
public class WineLogisticBasicVO implements Serializable {

    private static final long serialVersionUID = 5666217559373995148L;


    private Long wineId;
    private Long wineryId;
    private Long productId;
    private String message;
   
	public Long getWineId() {
		return wineId;
	}
	public void setWineId(Long wineId) {
		this.wineId = wineId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

    

}
