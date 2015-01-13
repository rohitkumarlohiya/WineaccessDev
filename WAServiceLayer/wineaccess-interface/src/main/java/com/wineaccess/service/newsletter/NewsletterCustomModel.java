package com.wineaccess.service.newsletter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "name", "webName", "effDate", "endDate",
		"emailSubject", "fromEmail", "submitDate", "title", "isDefault",
		"userServiceModel", "modifiedDate" })
@JsonPropertyOrder({ "id", "name", "webName", "effDate", "endDate",
		"emailSubject", "fromEmail", "submitDate", "title", "isDefault",
		"userServiceModel", "modifiedDate" })
public class NewsletterCustomModel {

	private Long id;

	private String name;

	private String webName;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date effDate;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date endDate;

	private String emailSubject;

	private String fromEmail;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date submitDate;

	private String title;

	private Boolean isDefault;

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

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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

	public NewsletterCustomModel() {
		super();
	}

	public NewsletterCustomModel(Long id, String name, String webName,
			Date effDate, Date endDate, String emailSubject, String fromEmail,
			Date submitDate, String title, Boolean isDefault,
			UserServiceModel userServiceModel, Date modifiedDate) {
		super();
		this.id = id;
		this.name = name;
		this.webName = webName;
		this.effDate = effDate;
		this.endDate = endDate;
		this.emailSubject = emailSubject;
		this.fromEmail = fromEmail;
		this.submitDate = submitDate;
		this.title = title;
		this.isDefault = isDefault;
		this.userServiceModel = userServiceModel;
		this.modifiedDate = modifiedDate;
	}

}
