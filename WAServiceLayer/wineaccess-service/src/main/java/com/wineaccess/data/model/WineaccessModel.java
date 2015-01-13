package com.wineaccess.data.model;

import java.util.Date;

/**
 * Base Model For all the WineAccess Models
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class WineaccessModel {

	private String id;

	private Date createdTime = new Date();

	private String createdBy = "Jboss";

	private Date updatedTime = new Date();

	private String updatedBy = "Jboss";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
