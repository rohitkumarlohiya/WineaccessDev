package com.wineaccess.application.contact;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="contactDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EditContactDetailsPO extends ContactDetailsPO implements Serializable {

	private static final long serialVersionUID = -7248690572731436369L;

	@NotNull(message="CONTACT118")
	private Long contactId;

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

}


