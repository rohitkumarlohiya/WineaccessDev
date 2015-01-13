package com.wineaccess.security.masterdata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataTypeResponse implements Serializable{
	
	private Long id;
	private String name;
	private String description;
	private String label;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MasterDataTypeResponse(Long masterDataTypeId, String name,
			String description, String label) {
		super();
		this.id = masterDataTypeId;
		this.name = name;
		this.description = description;
		this.label = label;
	}
	
	public MasterDataTypeResponse() {
		super();
	
	}
	
	

}
