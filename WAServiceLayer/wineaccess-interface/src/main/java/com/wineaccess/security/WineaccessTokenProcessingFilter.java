package com.wineaccess.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;
import com.wineaccess.security.token.WineaccessSecurityUserDetails;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class WineaccessTokenProcessingFilter extends GenericFilterBean  {
	
	TokenUtils tokenUtils;
    AuthenticationManager authManager;
    
    public WineaccessTokenProcessingFilter (AuthenticationManager authManager, TokenUtils tokenUtils){
    	this.authManager = authManager;
    	this.tokenUtils = tokenUtils;
    }
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String token = httpRequest.getHeader("WINEACCESS_TOKEN");
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
		
		
		if(token != null) {
            // validate the token
            if (tokenUtils.validate(token)) {
                // determine the user based on the (already validated) token
                WineAccessUserDetails userDetails = tokenUtils.getUserFromToken(token);
                // build an Authentication object with the user's info
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUserName(), token);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
                // set the authentication into the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authentication));   
                System.out.println((WineaccessSecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
        }
        // continue thru the filter chain
        chain.doFilter(request, response);
	}
} 
