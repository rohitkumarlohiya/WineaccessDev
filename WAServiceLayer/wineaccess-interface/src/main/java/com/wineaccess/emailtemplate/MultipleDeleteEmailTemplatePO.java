package com.wineaccess.emailtemplate;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement
public class MultipleDeleteEmailTemplatePO extends BasePO {
	
	@Pattern(regexp="(\\d+){1}((|,|)(\\d+))*", message="EMAILTEMPLATE108")
	private String multipleEmailTemplateIds;
	private Boolean confirmStatus;

	public String getMultipleEmailTemplateIds() {
		return multipleEmailTemplateIds;
	}

	public void setMultipleEmailTemplateIds(String multipleEmailTemplateIds) {
		this.multipleEmailTemplateIds = multipleEmailTemplateIds;
	}

	public Boolean getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Boolean confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
}
