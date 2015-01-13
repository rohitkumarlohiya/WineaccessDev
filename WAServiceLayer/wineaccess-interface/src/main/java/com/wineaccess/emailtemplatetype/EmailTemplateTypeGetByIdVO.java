package com.wineaccess.emailtemplatetype;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateTypeGetByIdVO implements Serializable {

	private static final long serialVersionUID = -7066198604674710594L;
	private EmailTemplateTypeCustomModel emailTemplateType;

	public EmailTemplateTypeCustomModel getEmailTemplateType() {
		return emailTemplateType;
	}

	public void setEmailTemplateType(
			EmailTemplateTypeCustomModel emailTemplateType) {
		this.emailTemplateType = emailTemplateType;
	}

	public EmailTemplateTypeGetByIdVO() {
		super();
	}

	public EmailTemplateTypeGetByIdVO(
			EmailTemplateTypeCustomModel emailTemplateType) {
		super();
		this.emailTemplateType = emailTemplateType;
	}

}
