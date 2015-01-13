package com.wineaccess.filter.activiti;

import java.io.Serializable;
import java.util.Map;

import com.wineaccess.response.Response;

/**
 * @author jyoti.yadav@glolballogic.com
 */
public class UserActivityVO implements Serializable {

	private String token;
	private int userId;
	private Map<String, Object> parameter;
	private Response response;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
