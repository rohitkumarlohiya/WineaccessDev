package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1 PO of add requisition
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class AddRequisitionPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "REQUISITION_ERROR_101")
	//@Pattern(regexp = RegExConstants.REQUISITION_TYPE, message = "REQUISITION_ERROR_102")
	private String requisitionType;

	@NotNull(message = "REQUISITION_ERROR_103")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "REQUISITION_ERROR_104")
	private String desitinationDCId;

	public String getRequisitionType() {
		return requisitionType;
	}

	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}

	public String getDesitinationDCId() {
		return desitinationDCId;
	}

	public void setDesitinationDCId(String desitinationDCId) {
		this.desitinationDCId = desitinationDCId;
	}

}
