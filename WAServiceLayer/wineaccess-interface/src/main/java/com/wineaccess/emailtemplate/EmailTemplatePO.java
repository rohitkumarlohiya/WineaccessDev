package com.wineaccess.emailtemplate;

import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement
public class EmailTemplatePO extends BasePO {

	private static final long serialVersionUID = 5170604524570482977L;
	
	
	@NotEmpty(message = "EMAIL_TEMPLATE_ERROR_101")
	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "EMAIL_TEMPLATE_ERROR_106")
	private String name;
	
	//@Pattern(regexp = "@([\\w]+\\.)+[\\w]+[\\w]$", message = "EMAIL_TEMPLATE_ERROR_102")
	//@NotBlank(message = "EMAIL_TEMPLATE_ERROR_102")
	@Pattern(regexp=RegExConstants.EMAIL,message="EMAIL_TEMPLATE_ERROR_102")
	private String fromEmail;
	
	private String subject;
	private String body;

	@Min(value = 1, message = "EMAIL_TEMPLATE_ERROR_103")
	private Long emailTemplateTypeId;
	private Boolean isDefault;
	
	@Min(value = 1, message = "EMAIL_TEMPLATE_ERROR_104")
	//@NotNull(message="EMAIL_TEMPLATE_ERROR_105")
	//@Pattern(regexp = RegExConstants.DIGITS_EMPTY_STRING, message = "EMAIL_TEMPLATE_ERROR_105")
	private Long emailTemplateId;
	

	public Long getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(Long emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getEmailTemplateTypeId() {
		return emailTemplateTypeId;
	}

	public void setEmailTemplateTypeId(Long emailTemplateTypeId) {
		this.emailTemplateTypeId = emailTemplateTypeId;
	}

}
