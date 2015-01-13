package com.wineaccess.service.user.preference;

import java.io.Serializable;

public class NewsletterList implements Serializable{

	private static final long serialVersionUID = -7060983258610066202L;
	private Long[] newsletterId;

	public Long[] getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(Long[] newsletterId) {
		this.newsletterId = newsletterId;
	}
	
	
}
