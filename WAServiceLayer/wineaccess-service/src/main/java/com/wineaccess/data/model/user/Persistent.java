package com.wineaccess.data.model.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author jyoti.yadav@globallogic.com
 */
@MappedSuperclass
public abstract class Persistent implements Serializable {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "DATETIME NOT NULL", updatable = false)
	private Date createdDate;
	
	@Column(name = "CREATED_BY", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL",updatable = false)
	private Long createdBy = 1L;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE", columnDefinition = "DATETIME DEFAULT NULL")
	private Date modifiedDate;
	
	@Column(name = "UPDATED_BY", columnDefinition = "BIGINT(20) UNSIGNED DEFAULT NULL")
	private Long modifiedBy = 1L;

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getCreatedDate(){
		return this.createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
