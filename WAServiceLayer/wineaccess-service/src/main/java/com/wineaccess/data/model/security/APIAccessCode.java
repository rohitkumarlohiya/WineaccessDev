package com.wineaccess.data.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "API_ACCESS_CODE")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "getAccessCodeByEmailId", query = "from APIAccessCode p where p.emailId = :emailId"),
	@NamedQuery(name = "getAccessCode", query = "from APIAccessCode"),
})

public class APIAccessCode extends Persistent {

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SITE_ID", columnDefinition = "VARCHAR(255) NULL")
	private String siteId;

	@Column(name = "PRIVATE_KEY", columnDefinition = "VARCHAR(255) NULL")
	private String privateKey;

	@Email(message = "loginpo.username.email")
	@Column(name = "EMAIL_ID", columnDefinition = "VARCHAR(255) NULL")
	private String emailId;
	
	public APIAccessCode(){
	}
	
	public APIAccessCode(String siteId, String privateKey, String emailId) {
		this.siteId = siteId;
		this.privateKey = privateKey;
		this.emailId = emailId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
