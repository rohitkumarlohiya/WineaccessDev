package com.wineaccess.emailtemplate;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ETMultipleDeleteModelForDependency implements Serializable {

	private static final long serialVersionUID = -1193891956184071215L;
	private int count;
	private List<EmailTemplateCustomModel> emailTemplates;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<EmailTemplateCustomModel> getEmailTemplates() {
		return emailTemplates;
	}

	public void setEmailTemplates(List<EmailTemplateCustomModel> emailTemplates) {
		this.emailTemplates = emailTemplates;
	}

	public ETMultipleDeleteModelForDependency() {
		super();
	}

	public ETMultipleDeleteModelForDependency(int count,
			List<EmailTemplateCustomModel> emailTemplates) {
		super();
		this.count = count;
		this.emailTemplates = emailTemplates;
	}

}
