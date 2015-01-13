package com.wineaccess.data.model.system;

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

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "SYS_API_KEY_VERSION_MAPPING")
@EntityListeners(EntityListener.class)
@NamedQueries({@NamedQuery(name = "getByApiKeyAndVersion", query = "from ApplicationKeyVersion p where p.apiKey = :apiKey and p.apiVersion = :apiVersion")})
public class ApplicationKeyVersion extends Persistent {

	private static final long serialVersionUID = -7768149376944544055L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "API_KEY", columnDefinition = "VARCHAR(255) NULL")
	private String apiKey;
	
	@Column(name = "API_VERSION",columnDefinition = "VARCHAR(255) NULL")
	private String apiVersion;

	public ApplicationKeyVersion(){
	}
	
	public ApplicationKeyVersion(String apiKey, String apiVersion) {
		this.apiKey = apiKey;
		this.apiVersion =  apiVersion;
	}
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
