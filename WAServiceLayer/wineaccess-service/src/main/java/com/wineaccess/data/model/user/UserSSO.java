package com.wineaccess.data.model.user;

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


/**
 * @author abhishek.sharma1
 *
 */
@Entity
@Table(name = "USER_SSO")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "findByIdSSO", query = "from UserSSO us where us.id = :id"),
	@NamedQuery(name = "getUserWithSSO", query = "FROM UserSSO WHERE userModelID = :id" )})
public class UserSSO extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SSO_SOURCE", columnDefinition = "VARCHAR(45) NULL")
	private String ssoSource;

	@Column(name = "SSO_TOKEN", columnDefinition = "VARCHAR(255) NULL")
	private String ssoToken;
	
	
	@Column(name = "SSO_ID",nullable=false, columnDefinition = "VARCHAR(45) NULL")
	private String ssoId;
	
	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
    private Long userModelID;

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSsoSource() {
		return ssoSource;
	}

	public void setSsoSource(String ssoSource) {
		this.ssoSource = ssoSource;
	}

	public String getSsoToken() {
		return ssoToken;
	}

	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}

	public Long getUserModelID() {
		return userModelID;
	}

	public void setUserModelID(Long userModelID) {
		this.userModelID = userModelID;
	}
}
