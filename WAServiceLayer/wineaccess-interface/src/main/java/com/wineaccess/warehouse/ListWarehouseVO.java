package com.wineaccess.warehouse;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * VO of list warehouse
 */
@XmlRootElement
@XmlType(name = "listWarehouse")
public class ListWarehouseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count;
	private int offSet;
	private int limit;
	private String keyword;
	private int totalRecordCount;
	private List<WarehouseListDetails> warehouseList;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public List<WarehouseListDetails> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<WarehouseListDetails> warehouseList) {
		this.warehouseList = warehouseList;
	}

}