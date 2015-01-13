package com.wineaccess.service.newsletter;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;
import com.wineaccess.common.JaxbDateSerializer;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
@XmlRootElement(name="newsletter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class NewsletterPO extends BasePO implements Serializable{
	private static final long serialVersionUID = -2879057461758940654L;
	
	private Long id;
	
	@NotBlank(message = "NEWSLETTER101")
	@NotNull(message = "NEWSLETTER102")
	private String name;
	
	@NotBlank(message = "NEWSLETTER103")
	@NotNull(message = "NEWSLETTER104")
	private String webName;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date effDate;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date endDate;
	
	@NotBlank(message = "NEWSLETTER109")
	@NotNull(message = "NEWSLETTER110")
	private String emailSubject;
	
	@NotBlank(message = "NEWSLETTER111")
	@NotNull(message = "NEWSLETTER112")
	@Email(message = "INVALIDEMAIL101")
	private String fromEmail;
	
	private String submitDate;
	
	@NotBlank(message = "NEWSLETTER113")
	@NotNull(message = "NEWSLETTER114")
	private String title;
	
	private Boolean isDefault;
		
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
