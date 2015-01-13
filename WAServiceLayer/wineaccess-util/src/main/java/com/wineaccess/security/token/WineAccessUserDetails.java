package com.wineaccess.security.token;

import java.io.Serializable;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class WineAccessUserDetails implements Serializable {

	private static final long serialVersionUID = 3418753893415386878L;

	private Long userId;
	private String userName;
	private String password;
	private String[] permissions;
	private long lastAccessTime;
	private String token;
	private Long userSessionId;
	
	public WineAccessUserDetails(){
	}
	
	public WineAccessUserDetails(Long userId, String username, String password, String[] permissions, long lastAccessTime){
		this.userId = userId;
		this.userName = username;
		this.password = password;
		this.permissions = permissions;
		this.lastAccessTime = lastAccessTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(Long userSessionId) {
		this.userSessionId = userSessionId;
	}
}
