package com.wineaccess.emailtemplate;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateAddVO implements Serializable {

	private static final long serialVersionUID = -7973269198249146286L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailTemplateAddVO(String message) {
		super();
		this.message = message;
	}

	public EmailTemplateAddVO() {
		super();
	}

}
