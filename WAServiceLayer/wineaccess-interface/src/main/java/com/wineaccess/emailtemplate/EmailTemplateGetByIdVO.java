package com.wineaccess.emailtemplate;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateGetByIdVO implements Serializable {

	private static final long serialVersionUID = -9007111353703901500L;

	private EmailTemplateCustomModel emailTemplate;

	public EmailTemplateCustomModel getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(EmailTemplateCustomModel emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public EmailTemplateGetByIdVO(EmailTemplateCustomModel emailTemplate) {
		super();
		this.emailTemplate = emailTemplate;
	}

	public EmailTemplateGetByIdVO() {
		super();
	}

}
