package com.wineaccess.orders.requisition;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;

/**
 * 
 * @author rohit.lohiya
 *
 */
@XmlRootElement(name = "addRequisition")
public class AddRequisitionPOForTypeIT extends AddRequisitionPO {

	private static final long serialVersionUID = 950740633186306795L;

	@NotNull(message = "REQUISITION_ERROR_113")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "REQUISITION_ERROR_114")
	private String sourceDCId;

	@NotNull(message = "REQUISITION_ERROR_115")
	@Pattern(regexp = RegExConstants.SOURCE_REQUISITION_TYPE, message = "REQUISITION_ERROR_116")
	private String sourceRequisitionType;

	
	@Pattern(regexp = RegExConstants.REQUISITION_TYPE_FOR_IT, message = "REQUISITION_ERROR_102")	
	public String getRequisitionType() {
		return super.getRequisitionType();
	}

	public String getSourceDCId() {
		return sourceDCId;
	}

	public void setSourceDCId(String sourceDCId) {
		this.sourceDCId = sourceDCId;
	}

	public String getSourceRequisitionType() {
		return sourceRequisitionType;
	}

	public void setSourceRequisitionType(String sourceRequisitionType) {
		this.sourceRequisitionType = sourceRequisitionType;
	}

}
