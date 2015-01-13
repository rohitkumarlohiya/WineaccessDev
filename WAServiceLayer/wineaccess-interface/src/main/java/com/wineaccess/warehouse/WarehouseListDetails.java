package com.wineaccess.warehouse;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * details of warehouse list
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="warehouseListDetails")
public class WarehouseListDetails extends WarehouseDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int wineryCount;

	public int getWineryCount() {
		return wineryCount;
	}

	public void setWineryCount(int wineryCount) {
		this.wineryCount = wineryCount;
	}

}