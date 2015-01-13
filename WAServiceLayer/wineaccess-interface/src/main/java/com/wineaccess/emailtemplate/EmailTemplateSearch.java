/**
 * 
 */
package com.wineaccess.emailtemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * @author anurag.jain3
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"emailTemplateTypeId", "emailTemplateTypeName","emailTemplateTypeLabel","emailTemplates"})
@XmlSeeAlso(EmailTemplate.class)
public class EmailTemplateSearch implements Serializable
{
	
	private Long emailTemplateTypeId;
	private String emailTemplateTypeName;
	private String emailTemplateTypeLabel;
	@XmlElementWrapper(name="emailTemplates")
	@XmlElement(name="emailTemplate")
	List<EmailTemplate> emailTemplates;
	
	public EmailTemplateSearch(){
		emailTemplates = new ArrayList<EmailTemplate>();
	}
	
	public EmailTemplateSearch(long id, String name){
		this();
		this.emailTemplateTypeId = id;
		this.emailTemplateTypeName = name;
	}
	
	public EmailTemplateSearch(long id, String name, String label){
		this(id, name);
		this.emailTemplateTypeLabel= label;
		
		
	}
	
	public Long getEmailTemplateTypeId() {
		return emailTemplateTypeId;
	}
	public void setEmailTemplateTypeId(Long emailTemplateTypeId) {
		this.emailTemplateTypeId = emailTemplateTypeId;
	}
	public String getEmailTemplateTypeName() {
		return emailTemplateTypeName;
	}
	public void setEmailTemplateTypeName(String emailTemplateTypeName) {
		this.emailTemplateTypeName = emailTemplateTypeName;
	}
	public List<EmailTemplate> getEmailTemplates() {
		return emailTemplates;
	}
	public void setEmailTemplates(List<EmailTemplate> emailTemplates) {
		this.emailTemplates = emailTemplates;
	}

	public String getEmailTemplateTypeLabel() {
		return emailTemplateTypeLabel;
	}

	public void setEmailTemplateTypeLabel(String emailTemplateTypeLabel) {
		this.emailTemplateTypeLabel = emailTemplateTypeLabel;
	}
	
	
	

}
