package com.wineaccess.emailtemplatetype;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.data.model.common.EmailTemplateType;
import com.wineaccess.user.activity.log.UserServiceModel;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","name","description","label","isActive","emailTemplateCount","userServiceModel","modifiedDate"})

public class EmailTemplateTypeSearch implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6662990986377638154L;
	
	private Long id;
	private String name;
	private String description;
	private String label;
	private boolean isActive;
	private int emailTemplateCount;
	
	@XmlElement(name = "modifiedBy")
	UserServiceModel userServiceModel;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date modifiedDate;
	
	public EmailTemplateTypeSearch() {
		super();
	}
	
	public EmailTemplateTypeSearch(EmailTemplateType emailTemplateType) {
		this();
		this.setActive(emailTemplateType.isStatus());
		this.setDescription(emailTemplateType.getDescription());
		this.setId(emailTemplateType.getId());
		if(emailTemplateType.getEmailTemplates() != null && !emailTemplateType.getEmailTemplates().isEmpty()){
			this.setEmailTemplateCount(emailTemplateType.getEmailTemplates().size());
		}
		this.setName(emailTemplateType.getName());
		this.setModifiedDate(emailTemplateType.getModifiedDate());
		this.label = emailTemplateType.getLabel();
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getEmailTemplateCount() {
		return emailTemplateCount;
	}
	public void setEmailTemplateCount(int emailTemplateCount) {
		this.emailTemplateCount = emailTemplateCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
