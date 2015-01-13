package com.wineaccess.application.contact;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.common.AbstractSearchPO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

@XmlRootElement(name="contactList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactsDetailListingPO extends AbstractSearchPO implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	@XmlElement(name="wineryId")
	private Long wineryId;
	@XmlElement(name="importerId")
	private Long importerId;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CONTACT_TYPE,message=MasterDataErrorCode.Constants.CONTACT_TYPE)
	private Long contactType;
	public Long getWineryId() {
		return wineryId;
	}
	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}
	public Long getImporterId() {
		return importerId;
	}
	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}
	public Long getContactType() {
		return contactType;
	}
	public void setContactType(Long contactType) {
		this.contactType = contactType;
	}
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_WINERY_IMPORTER_CONTACTS, message = "CONTACT120")
	public String getSortBy() {
		return sortBy;
	}
}
