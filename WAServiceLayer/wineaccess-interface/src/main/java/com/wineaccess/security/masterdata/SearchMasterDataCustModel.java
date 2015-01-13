package com.wineaccess.security.masterdata;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SearchMasterDataCustModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -177396237422119956L;
	/**
	 * 
	 */
	

	private Long id;
	private String name;
	//private MasterDataTypeResponse masterDataType;

	@XmlElement(name = "modifiedBy")
	UserServiceModel userServiceModel;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date modifyDate;

	public UserServiceModel getUserServiceModel() {
		return userServiceModel;
	}

	public void setUserServiceModel(UserServiceModel userServiceModel) {
		this.userServiceModel = userServiceModel;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

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

	

	

	public SearchMasterDataCustModel() {
		super();
	}

	public SearchMasterDataCustModel(Long id, String name,
			UserServiceModel userServiceModel, Date modifyDate) {
		super();
		this.id = id;
		this.name = name;
		
		this.userServiceModel = userServiceModel;
		this.modifyDate = modifyDate;
	}

}
