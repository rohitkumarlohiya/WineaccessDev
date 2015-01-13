package com.wineaccess.emailtemplatetype;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateTypeListVO implements Serializable {

	private static final long serialVersionUID = 4717657859882217821L;
	
	@XmlElement
	List<EmailTemplateTypeCustomModel> emailTemplateTypes;

	public List<EmailTemplateTypeCustomModel> getEmailTemplateTypes() {
		return emailTemplateTypes;
	}

	public void setEmailTemplateTypes(
			List<EmailTemplateTypeCustomModel> emailTemplateTypes) {
		this.emailTemplateTypes = emailTemplateTypes;
	}

	public EmailTemplateTypeListVO() {
		super();
	}

	public EmailTemplateTypeListVO(
			List<EmailTemplateTypeCustomModel> emailTemplateTypes) {
		super();
		this.emailTemplateTypes = emailTemplateTypes;
	}

}
