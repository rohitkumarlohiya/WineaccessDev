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
//@XmlType(propOrder={"roleName","isActive","message"}) 
public class UserRoleAddVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;

	/*@XmlAttribute(name="RoleName")
	private String roleName;
	
	@XmlAttribute(name="IsActive")
	private boolean isActive;
	
	@XmlAttribute(name="Message")
	private String message;
	
	
	
	
	public UserRoleAddVO(String roleName, boolean isActive, String message) {
		
		this.roleName = roleName;
		this.isActive = isActive;
		this.message = message;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}*/
	
	
	
	
	
	private UserRoles userRoles;

	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	public UserRoleAddVO(UserRoles userRoles) {
		super();
		this.userRoles = userRoles;
	}


	
	
	

}
