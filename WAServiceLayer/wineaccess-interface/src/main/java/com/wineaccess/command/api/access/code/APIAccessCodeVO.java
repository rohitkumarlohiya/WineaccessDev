package com.wineaccess.command.api.access.code;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class APIAccessCodeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlTransient
	@JsonIgnore
	private Integer siteId;
	@XmlTransient
	@JsonIgnore
	private Integer privateKey;

	private String apiAccessCode;
	
	public APIAccessCodeVO() {
	}
	
	public APIAccessCodeVO(Integer siteId, Integer privateKey, String apiAccessCode) {
		this.siteId = siteId;
		this.privateKey = privateKey;
		this.apiAccessCode = apiAccessCode;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(Integer privateKey) {
		this.privateKey = privateKey;
	}

	public String getApiAccessCode() {
		return apiAccessCode;
	}

	public void setApiAccessCode(String apiAccessCode) {
		this.apiAccessCode = apiAccessCode;
	}
}
