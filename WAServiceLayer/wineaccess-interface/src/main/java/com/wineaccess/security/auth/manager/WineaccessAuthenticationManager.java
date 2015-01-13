package com.wineaccess.security.auth.manager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;
import com.wineaccess.security.token.WineaccessSecurityUserDetails;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class WineaccessAuthenticationManager extends AbstractUserDetailsAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetail, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String arg0,	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		
		TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		
		tokenUtils.refreshLastAccessTimestamp(String.valueOf(usernamePasswordAuthenticationToken.getCredentials()));
		WineAccessUserDetails user = tokenUtils.getUserFromToken(String.valueOf(usernamePasswordAuthenticationToken.getCredentials()));
		
		Collection<GrantedAuthority> dbAuth = new ArrayList<GrantedAuthority>();
		
		for (String permission : user.getPermissions()) {
	 		dbAuth.add(new SimpleGrantedAuthority(permission));
		}
		WineaccessSecurityUserDetails userDetails = new WineaccessSecurityUserDetails(user.getUserName(), user.getPassword(), true, true, true, true, dbAuth, user.getToken(), user.getUserId());
		return userDetails;
	}
}
