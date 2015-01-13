package com.wineaccess.data.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@Entity
@Table(name = "EMAIL_TEMPLATE")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "EmailTemplate.getById", query = "from EmailTemplate where id = :emailTemplateId"),
		@NamedQuery(name = "EmailTemplate.getByAciveStatusAndEmailTemplateTypeId", query = "from EmailTemplate where isActive = true and emailTemplateType = :emailTemplateType"),
		@NamedQuery(name = "EmailTemplate.getAll", query = "from EmailTemplate"),
		@NamedQuery(name = "EmailTemplate.getAllByName", query = "from EmailTemplate where name = :name ")})
public class EmailTemplate extends Persistent {

	private static final long serialVersionUID = 7572062981068835758L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "EMAIL_TEMPLATE_TYPE_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private EmailTemplateType emailTemplateType;

	@Column(name = "NAME", columnDefinition = "VARCHAR(255) NOT NULL")
	private String name;

	@Column(name = "SUBJECT", columnDefinition = "VARCHAR(255) NULL")
	private String subject;

	@Column(name = "FROM_EMAIL", columnDefinition = "VARCHAR(255) NOT NULL")
	private String fromEmail;

	@Column(name = "IS_ACTIVE", columnDefinition = "TINYINT(1) NOT NULL")
	private boolean isActive;

	@Lob
	@Column(name = "BODY")
	private String body;
	
	public EmailTemplate(){
	}
	
	public EmailTemplate(String name, String subject, boolean isActive, String body,String fromEmail, EmailTemplateType emailTemplateType){
		this.name = name;
		this.subject = subject;
		this.isActive = isActive;
		this.body = body;
		this.fromEmail = fromEmail;
		this.emailTemplateType = emailTemplateType;
	}
	
	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/*
	 * public boolean isDeleted() { return isDeleted; }
	 * 
	 * public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
	 * 
	 * public void setIsDeleted(boolean isDeleted) { this.isDeleted = isDeleted;
	 * }
	 * 
	 * public boolean isIsDeleted() { return isDeleted; }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmailTemplateType getEmailTemplateType() {
		return emailTemplateType;
	}

	public void setEmailTemplateType(EmailTemplateType emailTemplateType) {
		this.emailTemplateType = emailTemplateType;
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

}
