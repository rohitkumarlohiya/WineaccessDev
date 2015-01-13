package com.wineaccess.data.model.profile;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement
public class ListUserByIdPO extends BasePO {
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
