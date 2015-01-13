package com.wineaccess.security.user.role;

import com.wineaccess.data.model.user.UserRoles;

public class UserRoleUpdateVO {
	
	private UserRoles userRoles;

	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	public UserRoleUpdateVO(UserRoles userRoles) {
		super();
		this.userRoles = userRoles;
	}
	
	

}
