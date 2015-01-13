package com.wineaccess.security.masterdatatype;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataTypeCustomModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5823219688713634268L;
	private Long id;
	private String name;
	private String description;
	private boolean isActive;
	private int masterDataCount;
	private String label;
	
	
	private List<MasterDataCustomModel> masterDatas;

	//private Long modifyBy;
	@XmlElement(name = "modifiedBy")
	UserServiceModel userServiceModel;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date modifyDate;

//	public Long getModifyBy() {
//		return modifyBy;
//	}
//
//	public void setModifyBy(Long modifyBy) {
//		this.modifyBy = modifyBy;
//	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	
	

	/*
	 * public MasterDataTypeCustomModel(Long id, String name, String
	 * description, boolean isActive, List<MasterDataCustomModel> masterDatas) {
	 * super(); this.id = id; this.name = name; this.description = description;
	 * this.isActive = isActive; this.masterDatas = masterDatas; }
	 */

	public UserServiceModel getUserServiceModel() {
		return userServiceModel;
	}

	public void setUserServiceModel(UserServiceModel userServiceModel) {
		this.userServiceModel = userServiceModel;
	}

	public MasterDataTypeCustomModel() {
		super();
	}

	public MasterDataTypeCustomModel(Long id, String name, String description,
			boolean isActive, List<MasterDataCustomModel> masterDatas,
			UserServiceModel userServiceModel, Date modifyDate, String label) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.masterDatas = masterDatas;
		this.userServiceModel = userServiceModel;
		this.modifyDate = modifyDate;
		this.label = label;
	}
	
	
	public MasterDataTypeCustomModel(Long id, String name, String description,
			boolean isActive, int masterDataCount,
			UserServiceModel userServiceModel, Date modifyDate, String label) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.masterDataCount = masterDataCount;
		this.userServiceModel = userServiceModel;
		this.modifyDate = modifyDate;
		this.label = label;
	}

	public int getMasterDataCount() {
		return masterDataCount;
	}

	public void setMasterDataCount(int masterDataCount) {
		this.masterDataCount = masterDataCount;
	}

	public List<MasterDataCustomModel> getMasterDatas() {
		return masterDatas;
	}

	public void setMasterDatas(List<MasterDataCustomModel> masterDatas) {
		this.masterDatas = masterDatas;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
