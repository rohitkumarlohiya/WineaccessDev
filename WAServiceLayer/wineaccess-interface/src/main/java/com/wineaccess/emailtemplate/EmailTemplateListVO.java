package com.wineaccess.emailtemplate;

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
public class EmailTemplateListVO implements Serializable {

	private static final long serialVersionUID = -3068169115996732924L;
	
	@XmlElement
	List<EmailTemplateCustomModel> emailTemplates;

	public List<EmailTemplateCustomModel> getEmailTemplates() {
		return emailTemplates;
	}

	public void setEmailTemplates(List<EmailTemplateCustomModel> emailTemplates) {
		this.emailTemplates = emailTemplates;
	}

	public EmailTemplateListVO(List<EmailTemplateCustomModel> emailTemplates) {
		super();
		this.emailTemplates = emailTemplates;
	}

	public EmailTemplateListVO() {
		super();
	}

}
