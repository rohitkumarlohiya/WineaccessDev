package com.wineaccess.security.user.role;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.data.model.user.UserRoles;

/***
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
public class UserRoleMultipleDeleteVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;

	private List<UserRoles> userRolesList;

	public List<UserRoles> getUserRolesList() {
		return userRolesList;
	}

	public void setUserRolesList(List<UserRoles> userRolesList) {
		this.userRolesList = userRolesList;
	}

	public UserRoleMultipleDeleteVO(List<UserRoles> userRolesList) {
		super();
		this.userRolesList = userRolesList;
	}
	
	

	


	
	
	

}
