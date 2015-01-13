package com.wineaccess.service.newsletter;

import java.io.Serializable;

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
public class NewsletterVO implements Serializable {
	
	private static final long serialVersionUID = -2447308669831397125L;
	
	private Long id;
	private String name;
	private String webName;
	private String effDate;
	private String endDate;
	private String emailSubject;
	private String fromEmail;
	private String submitDate;
	private String title;
	public NewsletterVO(Long id, String name, String webName, String effDate,
			String endDate, String emailSubject, String fromEmail,
			String submitDate, String title) {
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
	}
	public NewsletterVO() {
		super();
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
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
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
