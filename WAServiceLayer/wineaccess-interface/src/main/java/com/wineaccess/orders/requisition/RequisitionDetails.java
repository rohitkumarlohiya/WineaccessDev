package com.wineaccess.orders.requisition;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class RequisitionDetails implements Serializable{

	private Long id;
	private Map<String,String> winery = new HashMap<String, String>();
	private Date submittedDate;
	private String status;
	private String typeOfREQ;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date expectedPickupDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date actualPickupDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date expectedArrivalDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date actualArrivalDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date expectedShippingDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date actualShippingDate;
	private Map<String, String> sourceWhAddressId = new HashMap<String, String>();
	private Map<String, String> sourceDcAddressId = new HashMap<String, String>();
	private Map<String, String> distributionCentreId = new HashMap<String, String>();
	private String sourceRequisition;
	private String inboundTransport;
	private Boolean isDeleted;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Map<String, String> getWinery() {
		return winery;
	}
	public void setWinery(Map<String, String> winery) {
		this.winery = winery;
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
	public String getTypeOfREQ() {
		return typeOfREQ;
	}
	public void setTypeOfREQ(String typeOfREQ) {
		this.typeOfREQ = typeOfREQ;
	}
	public Date getExpectedPickupDate() {
		return expectedPickupDate;
	}
	public void setExpectedPickupDate(Date expectedPickupDate) {
		this.expectedPickupDate = expectedPickupDate;
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
	public Date getExpectedShippingDate() {
		return expectedShippingDate;
	}
	public void setExpectedShippingDate(Date expectedShippingDate) {
		this.expectedShippingDate = expectedShippingDate;
	}
	public Date getActualShippingDate() {
		return actualShippingDate;
	}
	public void setActualShippingDate(Date actualShippingDate) {
		this.actualShippingDate = actualShippingDate;
	}
	public Map<String, String> getSourceWhAddressId() {
		return sourceWhAddressId;
	}
	public void setSourceWhAddressId(Map<String, String> sourceWhAddressId) {
		this.sourceWhAddressId = sourceWhAddressId;
	}
	public Map<String, String> getSourceDcAddressId() {
		return sourceDcAddressId;
	}
	public void setSourceDcAddressId(Map<String, String> sourceDcAddressId) {
		this.sourceDcAddressId = sourceDcAddressId;
	}
	public Map<String, String> getDistributionCentreId() {
		return distributionCentreId;
	}
	public void setDistributionCentreId(Map<String, String> distributionCentreId) {
		this.distributionCentreId = distributionCentreId;
	}
	public String getSourceRequisition() {
		return sourceRequisition;
	}
	public void setSourceRequisition(String sourceRequisition) {
		this.sourceRequisition = sourceRequisition;
	}
	public String getInboundTransport() {
		return inboundTransport;
	}
	public void setInboundTransport(String inboundTransport) {
		this.inboundTransport = inboundTransport;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


}
