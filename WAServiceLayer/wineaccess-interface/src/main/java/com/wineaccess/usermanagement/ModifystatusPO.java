package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="modifyStatus")
@XmlAccessorType(XmlAccessType.FIELD)

public class ModifystatusPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	@XmlElementWrapper(name="enableUsersList")
	@XmlElement(name="userId")
	private List<Long> enableUsersList;
	@XmlElementWrapper(name="disableUsersList")
	@XmlElement(name="userId")
	private List<Long> disableUsersList;
	@XmlElementWrapper(name="deleteUsersList")
	
	@XmlElement(name="userId")
	private List<Long> deleteUsersList;
	
	private Boolean isforceDelete;
	
	public List<Long> getDeleteUsersList() {
		return deleteUsersList;
	}
	public void setDeleteUsersList(List<Long> deleteUsersList) {
		this.deleteUsersList = deleteUsersList;
	}
	public List<Long> getEnableUsersList() {
		return enableUsersList;
	}
	public void setEnableUsersList(List<Long> enableUsersList) {
		this.enableUsersList = enableUsersList;
	}
	public List<Long> getDisableUsersList() {
		return disableUsersList;
	}
	public void setDisableUsersList(List<Long> disableUsersList) {
		this.disableUsersList = disableUsersList;
	}
	public Boolean getIsforceDelete() {
		return isforceDelete;
	}
	public void setIsforceDelete(Boolean isforceDelete) {
		this.isforceDelete = isforceDelete;
	}


}
