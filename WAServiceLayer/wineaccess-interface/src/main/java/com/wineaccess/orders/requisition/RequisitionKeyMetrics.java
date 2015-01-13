package com.wineaccess.orders.requisition;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * it will show the requisition key metrics
 * @author gaurav.agarwal1
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class RequisitionKeyMetrics implements Serializable {

	private static final long serialVersionUID = 7443911265987420659L;

	private BigDecimal totalCost = new BigDecimal(0);
	private BigDecimal estimatedFreight = new BigDecimal(0);

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getEstimatedFreight() {
		return estimatedFreight;
	}

	public void setEstimatedFreight(BigDecimal estimatedFreight) {
		this.estimatedFreight = estimatedFreight;
	}

}
