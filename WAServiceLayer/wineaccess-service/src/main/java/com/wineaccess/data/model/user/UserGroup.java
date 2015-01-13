package com.wineaccess.data.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "USER_GROUP")
@EntityListeners(EntityListener.class)
public class UserGroup extends Persistent {
	
	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", columnDefinition = "VARCHAR(45) NULL")
	private String groupName;
	
	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(255) NULL")
	private String groupDescription;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
