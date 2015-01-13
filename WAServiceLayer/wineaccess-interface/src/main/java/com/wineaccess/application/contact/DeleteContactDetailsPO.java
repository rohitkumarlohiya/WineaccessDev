package com.wineaccess.application.contact;


import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
@XmlRootElement(name="deleteContacts")
public class DeleteContactDetailsPO extends BasePO implements Serializable{
	private static final long serialVersionUID = -2879057461758940654L;
	//@XmlElement(name="contactId")
	private List<Long> contacts;
	@NotNull(message = "CONTACT117")
	private Boolean isForceDelete;
	
	
	
	public List<Long> getContacts() {
		return contacts;
	}
	public void setContacts(List<Long> contacts) {
		this.contacts = contacts;
	}
	public Boolean getIsForceDelete() {
		return isForceDelete;
	}
	public void setIsForceDelete(Boolean isForceDelete) {
		this.isForceDelete = isForceDelete;
	}


}