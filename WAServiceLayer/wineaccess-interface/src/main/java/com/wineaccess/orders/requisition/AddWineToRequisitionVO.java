package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author abhishek.sharma1
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addWineToRequisition")
public class AddWineToRequisitionVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long productId;
	private Long requistionId;
	private String message;

	public AddWineToRequisitionVO() {

	}

	public AddWineToRequisitionVO(Long id, Long productId, Long requistionId,
			String message) {
		this.id = id;
		this.productId = productId;
		this.requistionId = requistionId;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getRequistionId() {
		return requistionId;
	}

	public void setRequistionId(Long requistionId) {
		this.requistionId = requistionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
