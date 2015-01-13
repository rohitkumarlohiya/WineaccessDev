package com.wineaccess.service.newsletter;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class NewsletterDeleteVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;
	
	@XmlElementWrapper(name="successList")
	private List<NewsletterVO> canBeDeleted = null;
	@XmlElementWrapper(name="failureList")
	private List<NewsletterVO> canNotBeDeleted = null;
	@XmlElementWrapper(name="nonExistingList")
	private List<Long> nonExisting = null;
	public NewsletterDeleteVO(List<NewsletterVO> canBeDeleted,
			List<NewsletterVO> canNotBeDeleted, List<Long> nonExisting) {
		super();
		this.canBeDeleted = canBeDeleted;
		this.canNotBeDeleted = canNotBeDeleted;
		this.nonExisting = nonExisting;
	}
	public NewsletterDeleteVO() {
		super();
	}
	public List<NewsletterVO> getCanBeDeleted() {
		return canBeDeleted;
	}
	public void setCanBeDeleted(List<NewsletterVO> canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	public List<NewsletterVO> getCanNotBeDeleted() {
		return canNotBeDeleted;
	}
	public void setCanNotBeDeleted(List<NewsletterVO> canNotBeDeleted) {
		this.canNotBeDeleted = canNotBeDeleted;
	}
	public List<Long> getNonExisting() {
		return nonExisting;
	}
	public void setNonExisting(List<Long> nonExisting) {
		this.nonExisting = nonExisting;
	}
	
}
