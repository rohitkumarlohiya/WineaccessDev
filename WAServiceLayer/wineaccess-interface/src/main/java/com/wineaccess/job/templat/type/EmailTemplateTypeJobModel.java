package com.wineaccess.job.templat.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wineaccess.emailtemplate.EmailTemplatePO;

public class EmailTemplateTypeJobModel implements Serializable {

	private String name;

	private String description;

	private String status;
	
	private String label;
	
	private List<EmailTemplatePO> addUpdateEmailTemplate = new ArrayList<EmailTemplatePO>();

	public EmailTemplateTypeJobModel() {
	}

	public EmailTemplateTypeJobModel(String name, String description,
			String status, String label) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<EmailTemplatePO> getAddUpdateEmailTemplate() {
		return addUpdateEmailTemplate;
	}

	public void setAddUpdateEmailTemplate(
			List<EmailTemplatePO> addUpdateEmailTemplate) {
		this.addUpdateEmailTemplate = addUpdateEmailTemplate;
	}
}
