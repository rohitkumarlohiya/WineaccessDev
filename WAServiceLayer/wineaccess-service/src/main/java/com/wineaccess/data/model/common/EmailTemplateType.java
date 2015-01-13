package com.wineaccess.data.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

/**
 * @author rohit.lohiya
 * 
 */
@Entity
@Table(name = "EMAIL_TEMPLATE_TYPE")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "EmailTemplateType.getById", query = "from EmailTemplateType where id = :emailTemplateTypeId"),
	@NamedQuery(name = "EmailTemplateType.getByName", query = "from EmailTemplateType where name = :emailTemplateTypeName and status = true"),
	@NamedQuery(name = "EmailTemplateType.getAllOnlyActive", query = "from EmailTemplateType where status = true"),
	@NamedQuery(name = "EmailTemplateType.getAllByName", query = "from EmailTemplateType where name = :name "),
	@NamedQuery(name = "EmailTemplateType.getAllByStatus", query = "from EmailTemplateType where status = :status"),
	@NamedQuery(name = "EmailTemplateType.getAll", query = "from EmailTemplateType") 
})
public class EmailTemplateType extends Persistent {

	private static final long serialVersionUID = 3159140206686274181L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", columnDefinition = "VARCHAR(255) NOT NULL", unique = true)
	private String name;

	@Column(name = "STATUS", columnDefinition = "TINYINT(1) NOT NULL")
	private boolean status;

	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(255) NULL")
	private String description;
	
	@Column(name = "LABEL")
	private String label;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST}, mappedBy = "emailTemplateType")	//
	Set<EmailTemplate> emailTemplates = new HashSet<EmailTemplate>();
	
	public EmailTemplateType(){
	}
	
	public EmailTemplateType(String name, String description, boolean status, String label){
		this.name = name;
		this.description = description;
		this.status = status;
		this.label = label;
	}
	
	
	public Set<EmailTemplate> getEmailTemplates() {
		return emailTemplates;
	}

	public void setEmailTemplates(Set<EmailTemplate> emailTemplates) {
		this.emailTemplates = emailTemplates;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
