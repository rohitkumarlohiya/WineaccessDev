package com.wineaccess.orders.requisition;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.MasterDataModel;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="wineDetailsOfRequistion")
public class WineInRequistionResultModel {
	
	private Long wineId; 
	private String wineFullName;
	private Integer qtyOfBottles;
	private Integer qtyOfBoxes;
	private MasterDataModel bottlePerBox;
	private MasterDataModel bottleinML;
	private BigDecimal bottlePrice;
	private BigDecimal costPerBox;
	private Long lineItemId;
	
	public String getWineFullName() {
	    return wineFullName;
	}
	public void setWineFullName(String wineFullName) {
	    this.wineFullName = wineFullName;
	}
	
	public Integer getQtyOfBottles() {
	    return qtyOfBottles;
	}
	public void setQtyOfBottles(Integer qtyOfBottles) {
	    this.qtyOfBottles = qtyOfBottles;
	}
	public Integer getQtyOfBoxes() {
	    return qtyOfBoxes;
	}
	public void setQtyOfBoxes(Integer qtyOfBoxes) {
	    this.qtyOfBoxes = qtyOfBoxes;
	}
	public MasterDataModel getBottlePerBox() {
	    return bottlePerBox;
	}
	public void setBottlePerBox(MasterDataModel bottlePerBox) {
	    this.bottlePerBox = bottlePerBox;
	}
	
	public BigDecimal getCostPerBox() {
	    return costPerBox;
	}
	public void setCostPerBox(BigDecimal costPerBox) {
	    this.costPerBox = costPerBox;
	}
	public Long getLineItemId() {
	    return lineItemId;
	}
	public void setLineItemId(Long lineItemId) {
	    this.lineItemId = lineItemId;
	}
	public MasterDataModel getBottleinML() {
		return bottleinML;
	}
	public void setBottleinML(MasterDataModel bottleinML) {
		this.bottleinML = bottleinML;
	}
	public BigDecimal getBottlePrice() {
		return bottlePrice;
	}
	public void setBottlePrice(BigDecimal bottlePrice) {
		this.bottlePrice = bottlePrice;
	}
	public Long getWineId() {
		return wineId;
	}
	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}
	
}
