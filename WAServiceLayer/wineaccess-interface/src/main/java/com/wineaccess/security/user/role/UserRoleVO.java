package com.wineaccess.security.user.role;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.wineaccess.data.model.user.UserRoles;

/***
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
public class UserRoleVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;

	private UserRoles userRoles;

	@JsonProperty("userRoles1")
	private List<UserRoles> userRoleList;

	public UserRoleVO() {
	}

}
