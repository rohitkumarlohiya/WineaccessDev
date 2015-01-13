package com.wineaccess.security.login.history;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="loginHistory")
public class LoginHistoryByIdPO extends AbstractSearchPO {
	
	private String userId;
	
	public LoginHistoryByIdPO() {
		setSortOrder("0");
		setOffSet("1");
		setSortBy("userid");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_LOGIN_HISTORY, message = "USERHISTORY103")
	public String getSortBy() {
		return sortBy;
	}
}