package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1 VO of add requisition
 */
@XmlRootElement
@XmlType(name = "addRequisition")
public class AddRequisitionVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String requisitionType;
	private String message;

	public AddRequisitionVO() {

	}

	public AddRequisitionVO(Long id, String requisitionType, String message) {
		super();
		this.id = id;
		this.requisitionType = requisitionType;
		this.message = message;
	}

	public String getRequisitionType() {
		return requisitionType;
	}

	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
