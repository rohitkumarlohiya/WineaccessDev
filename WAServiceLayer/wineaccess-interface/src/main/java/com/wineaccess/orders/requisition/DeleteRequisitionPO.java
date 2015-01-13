package com.wineaccess.orders.requisition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;


@XmlRootElement(name="deleteRequisition")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class DeleteRequisitionPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	@XmlElementWrapper(name="requisitions")
	@ValidatingListAnnotation(message="REQUISITION_ERROR_139")
	private List<Long> requisitionId = new ArrayList<Long>();

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "REQUISITION_ERROR_140")
	private String isForceDelete;


	public List<Long> getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(List<Long> requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

}