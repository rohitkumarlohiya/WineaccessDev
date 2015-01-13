package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "samplerWine")
public class EditSamplerWineVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String samplerId;
	private String productId;
	private String message;
	public String getSamplerId() {
		return samplerId;
	}
	public void setSamplerId(String samplerId) {
		this.samplerId = samplerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
