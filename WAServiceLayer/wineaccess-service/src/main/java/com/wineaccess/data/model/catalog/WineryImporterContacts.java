package com.wineaccess.data.model.catalog;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "WINERY_IMPORTER_CONTACTS")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "WineryImporterContacts.getByImporterId", query = "from WineryImporterContacts where importerId = :importerId and isDefault=1"),
	@NamedQuery(name = "WineryImporterContacts.getById", query = "from WineryImporterContacts where id = :id and isDeleted = false"),
	@NamedQuery(name = "WineryImporterContacts.getByWineryId", query = "from WineryImporterContacts where wineryId = :wineryId and isDeleted = false"),
	@NamedQuery(name = "WineryImporterContacts.getAllByImporterId", query = "from WineryImporterContacts where importerId = :importerId and isDeleted = false"),
	@NamedQuery(name = "WineryImporterContacts.getDefaultContactByWineryId", query = "from WineryImporterContacts where wineryId = :wineryId and isDefault = true"),
	@NamedQuery(name = "WineryImporterContacts.findByWineryIdContactId", query = "from WineryImporterContacts wic where wic.id= :contactId and wic.wineryId= :wineryId "),
})

public class WineryImporterContacts extends Persistent {
	private static final long serialVersionUID = 8005152603879392696L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "FAX_NUMBER")
	private String faxNumber;

	@OneToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name="CONTACT_TYPE")
	private MasterData contactType;

	@Column(name = "WINERY_ID")
	private Long wineryId;

	@Column(name = "IMPORTER_ID")
	private Long importerId;
	
	@Column(name = "IS_DEFAULT")
	private Boolean isDefault = false;
	
	@Column(name = "IS_DELETED")
	private Boolean isDeleted = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MasterData getContactType() {
		return contactType;
	}

	public void setContactType(MasterData contactType) {
		this.contactType = contactType;
	}

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

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	
	
}
