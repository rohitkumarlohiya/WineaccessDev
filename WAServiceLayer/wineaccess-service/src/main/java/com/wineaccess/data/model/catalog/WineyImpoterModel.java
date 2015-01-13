package com.wineaccess.data.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "WINERY_IMPORTER")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "WineyImpoterModel.getByImporterId", query = "from WineyImpoterModel where importerId = :importerId and wineryId = :wineryId"),
	@NamedQuery(name = "WineyImpoterModel.getImportersForWinery", query = "from WineyImpoterModel where wineryId = :wineryId"),
	@NamedQuery(name = "WineyImpoterModel.getWineryForImporter", query = "from WineyImpoterModel where importerId = :importerId"),
	@NamedQuery(name = "WineyImpoterModel.getNonDeletedWineryForImporter", query = "from WineyImpoterModel where importerId = :importerId and isDeleted = false")
})
public class WineyImpoterModel extends Persistent {

	private static final long serialVersionUID = 3587048381376975376L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "WINERY_ID")
	private Long wineryId;

	@Column(name = "IMPORTER_ID")
	private Long importerId;	
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActiveImpoter;
	
	@Column(name = "WORKFLOW_STATUS")
	private String workflowStatus;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

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

	public Boolean getIsActiveImpoter() {
		return isActiveImpoter;
	}

	public void setIsActiveImpoter(Boolean isActiveImpoter) {
		this.isActiveImpoter = isActiveImpoter;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	
}
