package com.wineaccess.emailtemplate;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateCloneVO implements Serializable {

	private static final long serialVersionUID = 1640116662956021343L;

	/*private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailTemplateCloneVO(String message) {
		super();
		this.message = message;
	}*/
	
	private EmailTemplateCustomModel emailTemplate;
	
	

	public EmailTemplateCustomModel getEmailTemplate() {
		return emailTemplate;
	}



	public void setEmailTemplate(EmailTemplateCustomModel emailTemplate) {
		this.emailTemplate = emailTemplate;
	}



	public EmailTemplateCloneVO(EmailTemplateCustomModel emailTemplate) {
		super();
		this.emailTemplate = emailTemplate;
	}



	public EmailTemplateCloneVO() {
		super();
	}

	
}
