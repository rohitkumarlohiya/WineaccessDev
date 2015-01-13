package com.wineaccess.common;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="requestParameter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class IdentityPO extends BasePO {
	
	@NotNull(message = "INVALID_ID_VALUE")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
