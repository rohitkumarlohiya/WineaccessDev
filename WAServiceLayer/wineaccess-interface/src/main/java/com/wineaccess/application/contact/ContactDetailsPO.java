package com.wineaccess.application.contact;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

@XmlRootElement(name="contact")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ContactDetailsPO extends BasePO implements Serializable{

	private static final long serialVersionUID = 8005152603879392696L;
	private Long importerId;
	private Long wineryId;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CONTACT_TYPE,message=MasterDataErrorCode.Constants.CONTACT_TYPE)
	@NotNull(message="CONTACT111")
	private Long contactType;
	
	@NotEmpty(message="CONTACT112")
	private String name;
	@NotEmpty(message="CONTACT113")
	private String phone;
	@NotEmpty(message="CONTACT115")
	@Email(message="CONTACT114")
	private String email;
	//@NotNull(message="CONTACT116")
	private Boolean isDefault;
	
	
	@Pattern(regexp = "[1-9](\\d{2})[-]\\d{3}[-]\\d{4}", message="CONTACT120")
	private String faxNumber;
	
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
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	
	
}
