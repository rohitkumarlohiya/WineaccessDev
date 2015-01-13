package com.wineaccess.emailtemplate;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="EmailTemplateCustomModel2")
public class EmailTemplateCustomModel {

	private Long id;
	private String name;
	private String subject;
	private String body;
	private Long emailTemplateTypeId;

	@XmlElement(name = "modifiedBy")
	UserServiceModel userServiceModel;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date modifiedDate;

	private String fromEmail;
	
	@XmlElement(name = "isDefault")
	private boolean isActive;

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public UserServiceModel getUserServiceModel() {
		return userServiceModel;
	}

	public void setUserServiceModel(UserServiceModel userServiceModel) {
		this.userServiceModel = userServiceModel;
	}

	

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public EmailTemplateCustomModel() {
		super();
	}

	public EmailTemplateCustomModel(Long id, String name, String subject,
			String body, Long emailTemplateTypeId,
			UserServiceModel userServiceModel, Date modifiedDate,
			String fromEmail, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.body = body;
		this.emailTemplateTypeId = emailTemplateTypeId;
		this.userServiceModel = userServiceModel;
		this.modifiedDate = modifiedDate;
		this.fromEmail = fromEmail;
		this.isActive = isActive;
	}



}
