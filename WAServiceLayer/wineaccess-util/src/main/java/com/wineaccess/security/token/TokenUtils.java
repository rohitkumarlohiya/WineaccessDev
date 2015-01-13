package com.wineaccess.security.token;

import java.util.List;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface TokenUtils {

	void removerToken(String token);

	String getToken(WineAccessUserDetails userDetails);

	boolean validate(String token);

	WineAccessUserDetails getUserFromToken(String token);

	void refreshLastAccessTimestamp(String token);
	
	List<String> markTokenExpired(long expirationTime);
	
	void recoverTokens(WineAccessUserDetails userDetails);
	
	public void logoutUser(String userName);
}
