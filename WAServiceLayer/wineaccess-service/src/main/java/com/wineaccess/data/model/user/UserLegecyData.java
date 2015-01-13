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
@Table(name = "USER_LEGACY_DATA")
@EntityListeners(EntityListener.class)
public class UserLegecyData extends Persistent {

	private static final long serialVersionUID = -1412933723126680954L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LEGACY_DATA1", columnDefinition = "VARCHAR(45) NULL")
	private String legecyData1;

	@Column(name = "LEGACY_DATA2", columnDefinition = "VARCHAR(45) NULL")
	private String legecyData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegecyData1() {
		return legecyData1;
	}

	public void setLegecyData1(String legecyData1) {
		this.legecyData1 = legecyData1;
	}

	public String getLegecyData() {
		return legecyData;
	}

	public void setLegecyData(String legecyData) {
		this.legecyData = legecyData;
	}
}
