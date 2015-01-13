package com.wineaccess.winelicensedetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "wineLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineLicenseDetailVO implements Serializable {

	private static final long serialVersionUID = 3431857750878328287L;

	private Long id;
	private Long wineId;
	private Long productId;
	private String message;

	public WineLicenseDetailVO() {
		super();
	}

	public WineLicenseDetailVO(String message) {
		super();
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}
