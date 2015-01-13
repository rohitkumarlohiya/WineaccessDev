package com.wineaccess.data.model.profile;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;


/**
 * @author arpit.vijayvargiya@globallogic.com
 */
@XmlRootElement
public class UserProfilePasswordPO extends BasePO implements Serializable {
	private static final long serialVersionUID = 85783916826497792L;

	@NotNull(message="USERPROFILE103")
	private Long userId;
	
	@NotNull(message="USERPROFILE104")
	@NotBlank(message="USERPROFILE104")
	private String currentPassword;
	
	@NotNull(message="USERPROFILE107")
	@NotBlank(message="USERPROFILE107")
	private String newPassword;
	

	public UserProfilePasswordPO() {
		super();
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getCurrentPassword() {
		return currentPassword;
	}


	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}


