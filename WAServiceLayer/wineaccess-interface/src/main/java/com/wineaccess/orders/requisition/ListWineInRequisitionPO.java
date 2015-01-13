package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.NonLuceneAbstractSearchPO;

@XmlRootElement(name="winesListinRequistion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWineInRequisitionPO extends NonLuceneAbstractSearchPO implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	
	@NotEmpty(message="LIST_WINE_REQUISITION_ERROR_101")
	@Pattern(regexp=RegExConstants.DIGITS,message="LIST_WINE_REQUISITION_ERROR_102")
	private String requisitionId;

	public String getRequisitionId() {
	    return requisitionId;
	}

	public void setRequisitionId(String requisitionId) {
	    this.requisitionId = requisitionId;
	}
	
	
	
}
