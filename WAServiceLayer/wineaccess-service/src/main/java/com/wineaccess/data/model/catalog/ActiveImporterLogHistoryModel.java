package com.wineaccess.data.model.catalog;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "ACTIVE_IMPORTER_LOG_HISTORY")
@EntityListeners(EntityListener.class)
public class ActiveImporterLogHistoryModel extends Persistent {

	private static final long serialVersionUID = 3587048381376975376L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "WINERY_ID")
	private Long wineryId;

	@Column(name = "IMPORTER_ID")
	private Long importerId;	
	
	@Column(name = "DISASSOCIATION_DATE")
	private Date disassociationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDisassociationDate() {
		return disassociationDate;
	}

	public void setDisassociationDate(Date disassociationDate) {
		this.disassociationDate = disassociationDate;
	}

}