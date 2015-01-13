package com.wineaccess.job.templat.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "emailTemplateType")
public class EmailTemplateTypeJobPO implements Serializable {
	
	private List<EmailTemplateTypeJobModel> addUpdateEmailTemplateType = new ArrayList<EmailTemplateTypeJobModel>();

	public List<EmailTemplateTypeJobModel> getAddUpdateEmailTemplateType() {
		return addUpdateEmailTemplateType;
	}

	public void setAddUpdateEmailTemplateType(
			List<EmailTemplateTypeJobModel> addUpdateEmailTemplateType) {
		this.addUpdateEmailTemplateType = addUpdateEmailTemplateType;
	}

}
