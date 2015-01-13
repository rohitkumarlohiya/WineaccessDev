package com.wineaccess.application.contact;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="contact")
public class ViewContactDetailsPO extends BasePO implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	@NotNull(message="CONTACT118")
	private Long contactId;
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

}