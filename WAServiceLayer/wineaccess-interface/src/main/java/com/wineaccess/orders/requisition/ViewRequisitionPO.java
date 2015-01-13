package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name = "viewRequisition")
public class ViewRequisitionPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 5573949530700694374L;
	
	@NotEmpty(message="VIEW_REQUISITION_ERROR_101")
	@Pattern(regexp=RegExConstants.DIGITS,message="VIEW_REQUISITION_ERROR_102")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
