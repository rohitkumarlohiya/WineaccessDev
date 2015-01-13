package com.wineaccess.orders.requisition;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.common.JaxbRequisitionDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EditRequisitionPO extends AddRequisitionPO {

	@NotNull(message="REQUISITION_ERROR_127")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "REQUISITION_ERROR_138")
	private String requisitionId;
	
	@NotNull(message="REQUISITION_ERROR_128")
	@Pattern(regexp = RegExConstants.REQUISITION_STATUS, message = "REQUISITION_ERROR_129")
	private String requisitionStatus;
	
	@XmlJavaTypeAdapter(JaxbRequisitionDate.class)
	private Date expectedPickupDate;
	
	@XmlJavaTypeAdapter(JaxbRequisitionDate.class)
	private Date expectedArrivalDate;

	@XmlJavaTypeAdapter(JaxbRequisitionDate.class)
	private Date expectedShippingDate;
	
	@XmlJavaTypeAdapter(JaxbRequisitionDate.class)
	private Date actualPickupDate;
	
	@XmlJavaTypeAdapter(JaxbRequisitionDate.class)
	private Date actualArrivalDate;

	@XmlJavaTypeAdapter(JaxbRequisitionDate.class)
	private Date actualShippingDate;

	public String getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getRequisitionStatus() {
		return requisitionStatus;
	}

	public void setRequisitionStatus(String requisitionStatus) {
		this.requisitionStatus = requisitionStatus;
	}

	public Date getExpectedPickupDate() {
		return expectedPickupDate;
	}

	public void setExpectedPickupDate(Date expectedPickupDate) {
		this.expectedPickupDate = expectedPickupDate;
	}

	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public Date getExpectedShippingDate() {
		return expectedShippingDate;
	}

	public void setExpectedShippingDate(Date expectedShippingDate) {
		this.expectedShippingDate = expectedShippingDate;
	}

	public Date getActualPickupDate() {
		return actualPickupDate;
	}

	public void setActualPickupDate(Date actualPickupDate) {
		this.actualPickupDate = actualPickupDate;
	}

	public Date getActualArrivalDate() {
		return actualArrivalDate;
	}

	public void setActualArrivalDate(Date actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}

	public Date getActualShippingDate() {
		return actualShippingDate;
	}

	public void setActualShippingDate(Date actualShippingDate) {
		this.actualShippingDate = actualShippingDate;
	}
	
	
}
