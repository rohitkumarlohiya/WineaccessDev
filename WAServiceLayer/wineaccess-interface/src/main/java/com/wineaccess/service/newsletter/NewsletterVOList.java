package com.wineaccess.service.newsletter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsletterVOList implements Serializable {
	
	private static final long serialVersionUID = 2447308669831397125L;

	@XmlElementWrapper(name = "newsletterList")
	@XmlElement(name = "newsletter")
	private List<NewsletterCustomModel> newsletter = new ArrayList<NewsletterCustomModel>();	
	
	public NewsletterVOList() {
		super();
	}
	
	public NewsletterVOList(List<NewsletterCustomModel> newsletter) {
		super();
		this.newsletter = newsletter;
	}

	public List<NewsletterCustomModel> getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(List<NewsletterCustomModel> newsletter) {
		this.newsletter = newsletter;
	}

	
	
	
}