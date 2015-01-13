package com.wineaccess.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.gson.Gson;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.WineaccessError;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,	AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		final PrintWriter writer = response.getWriter();
		
		WineaccessError wineAccessError = new WineaccessError(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED), authException.getMessage());
		Set<WineaccessError> errors = new HashSet<WineaccessError>();
		errors.add(wineAccessError);
		
		FailureResponse f = new FailureResponse(errors, HttpServletResponse.SC_OK);
		
		writer.println(new Gson().toJson(f));
		
		//writer.println("{\"status\":" + HttpServletResponse.SC_UNAUTHORIZED	+ ", \"message\":\"" + authException.getMessage() + "\"}");
		
	}
}
