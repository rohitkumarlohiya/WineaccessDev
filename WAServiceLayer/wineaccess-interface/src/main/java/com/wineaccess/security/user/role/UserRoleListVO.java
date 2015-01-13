package com.wineaccess.security.user.role;

import java.util.List;

import com.wineaccess.data.model.user.UserRoles;

public class UserRoleListVO {

	//@JsonProperty("userRoles")
	private List<UserRoles> userRoleList;

	public List<UserRoles> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRoles> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public UserRoleListVO(List<UserRoles> userRoleList) {
		super();
		this.userRoleList = userRoleList;
	}
	
	
	
}
