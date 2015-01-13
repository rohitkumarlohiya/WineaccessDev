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

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="removeWineFromRequisition")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class RemoveWineFromRequisitionPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="REMOVE_WINE_REQUISITION_ERROR_101")
	@Pattern(regexp=RegExConstants.DIGITS, message="REMOVE_WINE_REQUISITION_ERROR_102")
	private String requisitionId;
	
	@XmlElement
	@XmlElementWrapper(name="products")
	@ValidatingListAnnotation(message="REMOVE_WINE_REQUISITION_ERROR_103")
	private List<Long> productIds = new ArrayList<Long>();
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "REMOVE_WINE_REQUISITION_ERROR_104")
	private String isForceDelete;
	
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	public String getIsForceDelete() {
		return isForceDelete;
	}
	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}
	
}