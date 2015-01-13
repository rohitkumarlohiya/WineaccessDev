package com.wineaccess.security.user.role;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.data.model.user.UserRoles;

/***
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
public class UserRoleDeleteVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;

	private UserRoles userRoles;

	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	public UserRoleDeleteVO(UserRoles userRoles) {
		super();
		this.userRoles = userRoles;
	}


	
	
	

}
