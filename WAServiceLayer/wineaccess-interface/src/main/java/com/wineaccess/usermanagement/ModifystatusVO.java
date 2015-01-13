package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

public class ModifystatusVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	private List<Long> enableUsersList;
	private List<Long> disableUsersList;
	private String message;

	public ModifystatusVO() {
		
	}

	public ModifystatusVO( List<Long> enableUsersList,  List<Long> disableUsersList, String message) {
		this.disableUsersList = disableUsersList;
		this.enableUsersList = enableUsersList;
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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


}
