package com.wineaccess.orders.requisition;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewRequisition")
public class ViewRequisitionVO implements Serializable {

	private static final long serialVersionUID = -5559985126024128028L;

	private long id;
	private String type;
	private String status;
	private LocationAddressModel vendor;
	private LocationAddressModel wineLocation;
	private LocationAddressModel shipTo;
	private RequisitionKeyMetrics keyMetrics;
	
	private int winesCount;
	private int qtyBottles;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date submittedDate;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date estimatedPickupDate;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date actualPickupDate;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date expectedArrivalDate;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date actualArrivalDate;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date estimatedShippingDate;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date actualShippingDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocationAddressModel getVendor() {
		return vendor;
	}

	public void setVendor(LocationAddressModel vendor) {
		this.vendor = vendor;
	}

	public LocationAddressModel getWineLocation() {
		return wineLocation;
	}

	public void setWineLocation(LocationAddressModel wineLocation) {
		this.wineLocation = wineLocation;
	}

	public LocationAddressModel getShipTo() {
		return shipTo;
	}

	public void setShipTo(LocationAddressModel shipTo) {
		this.shipTo = shipTo;
	}

	public RequisitionKeyMetrics getKeyMetrics() {
		return keyMetrics;
	}

	public void setKeyMetrics(RequisitionKeyMetrics keyMetrics) {
		this.keyMetrics = keyMetrics;
	}

	public int getWinesCount() {
		return winesCount;
	}

	public void setWinesCount(int winesCount) {
		this.winesCount = winesCount;
	}

	public int getQtyBottles() {
		return qtyBottles;
	}

	public void setQtyBottles(int qtyBottles) {
		this.qtyBottles = qtyBottles;
	}

	public Date getEstimatedPickupDate() {
		return estimatedPickupDate;
	}

	public void setEstimatedPickupDate(Date estimatedPickupDate) {
		this.estimatedPickupDate = estimatedPickupDate;
	}

	public Date getActualPickupDate() {
		return actualPickupDate;
	}

	public void setActualPickupDate(Date actualPickupDate) {
		this.actualPickupDate = actualPickupDate;
	}

	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public Date getActualArrivalDate() {
		return actualArrivalDate;
	}

	public void setActualArrivalDate(Date actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}

	public Date getEstimatedShippingDate() {
		return estimatedShippingDate;
	}

	public void setEstimatedShippingDate(Date estimatedShippingDate) {
		this.estimatedShippingDate = estimatedShippingDate;
	}

	public Date getActualShippingDate() {
		return actualShippingDate;
	}

	public void setActualShippingDate(Date actualShippingDate) {
		this.actualShippingDate = actualShippingDate;
	}

}
