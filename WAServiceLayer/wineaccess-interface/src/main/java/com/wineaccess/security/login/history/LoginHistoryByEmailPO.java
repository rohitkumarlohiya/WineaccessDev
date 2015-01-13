package com.wineaccess.security.login.history;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="loginHistory")
public class LoginHistoryByEmailPO extends AbstractSearchPO {

	private String email;

	public LoginHistoryByEmailPO() {
		setSortOrder("0");
		setOffSet("1");
		setSortBy("userid");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_LOGIN_HISTORY, message = "USERHISTORY103")
	public String getSortBy() {
		return sortBy;
	}
}
