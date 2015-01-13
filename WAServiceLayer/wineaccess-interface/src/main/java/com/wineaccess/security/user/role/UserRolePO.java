package com.wineaccess.security.user.role;

import java.io.Serializable;

/**
 * @author rohit.lohiya
 */
public class UserRolePO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String roleName;
	private int roleId;
	private String updatedRoleName;
	private Boolean status;	
	
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUpdatedRoleName() {
		return updatedRoleName;
	}
	public void setUpdatedRoleName(String updatedRoleName) {
		this.updatedRoleName = updatedRoleName;
	}

	
	

	
	
}
