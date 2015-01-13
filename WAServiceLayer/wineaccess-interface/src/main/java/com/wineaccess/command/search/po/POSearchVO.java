package com.wineaccess.command.search.po;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.beans.BeanUtils;

@XmlRootElement
@XmlType(name="poSearch")
public class POSearchVO extends POSearchPO {
	
	private int totalRecordCount;
	private int count;
	
	private List<REQModelVO> purchageOrders;
	
	public POSearchVO(){
	}
	
	public POSearchVO(POSearchPO pOSearchPO, List<REQModelVO> purchageOrders, int totalRecordCount, int count){
		this.purchageOrders = purchageOrders;
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		BeanUtils.copyProperties(pOSearchPO, this);
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<REQModelVO> getPurchageOrders() {
		return purchageOrders;
	}

	public void setPurchageOrders(List<REQModelVO> purchageOrders) {
		this.purchageOrders = purchageOrders;
	}
}
