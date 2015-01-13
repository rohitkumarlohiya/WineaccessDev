package com.wineaccess.emailtemplatetype;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateTypeCustomModel implements Serializable {

	private static final long serialVersionUID = 3839033591952734211L;
	private Long id;
	private String name;
	private String description;
	private String label;
	private boolean isActive;

	@XmlElement
	private List<EmailTemplateCustomModel> emailTemplates;

	@XmlElement(name = "modifiedBy")
	UserServiceModel userServiceModel;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date modifiedDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<EmailTemplateCustomModel> getEmailTemplates() {
		return emailTemplates;
	}

	public void setEmailTemplates(List<EmailTemplateCustomModel> emailTemplates) {
		this.emailTemplates = emailTemplates;
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

	public EmailTemplateTypeCustomModel() {
		super();
	}

	public EmailTemplateTypeCustomModel(Long id, String name,
			String description, boolean isActive,
			List<EmailTemplateCustomModel> emailTemplates,
			UserServiceModel userServiceModel, Date modifiedDate, String label) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.emailTemplates = emailTemplates;
		this.userServiceModel = userServiceModel;
		this.modifiedDate = modifiedDate;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
