package com.wineaccess.service.newsletter;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="newsletter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class NewsletterGetByIdPO extends BasePO  implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	
	@XmlElement(name="newsletterId")
	List<Long> newsletterIds;

	public List<Long> getNewsletterIds() {
		return newsletterIds;
	}

	public void setNewsletterIds(List<Long> newsletterIds) {
		this.newsletterIds = newsletterIds;
	}
	
	
}
