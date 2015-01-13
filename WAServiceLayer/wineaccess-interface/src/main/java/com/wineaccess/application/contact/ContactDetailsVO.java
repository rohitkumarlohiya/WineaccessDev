package com.wineaccess.application.contact;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="contact")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ContactDetailsVO implements Serializable{

	private static final long serialVersionUID = 8005152603879392696L;
	private Long importerId;
	private Long wineryId;
	
	private Long contactType;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private Boolean isDefault;
	
	public Long getImporterId() {
		return importerId;
	}
	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}
	public Long getWineryId() {
		return wineryId;
	}
	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}
	public Long getContactType() {
		return contactType;
	}
	public void setContactType(Long contactType) {
		this.contactType = contactType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
