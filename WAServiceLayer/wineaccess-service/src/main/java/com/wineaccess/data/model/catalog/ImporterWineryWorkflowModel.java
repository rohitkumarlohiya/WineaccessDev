package com.wineaccess.data.model.catalog;


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
@Table(name = "IMPORTER_WINERY_WORKFLOW")
@EntityListeners(EntityListener.class)
public class ImporterWineryWorkflowModel extends Persistent {

	private static final long serialVersionUID = 3587048381376975376L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "WORKFLOW_NAME")
	private String workflowName;

	@Column(name = "ENTITY_ID")
	private Long entityId;	

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SUB_ENTITY_ID")
	private Long subEntityId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSubEntityId() {
		return subEntityId;
	}

	public void setSubEntityId(Long subEntityId) {
		this.subEntityId = subEntityId;
	}	

}


