package com.wineaccess.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.google.gson.Gson;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.WineaccessError;

public class AccessDeniedHandler extends AccessDeniedHandlerImpl {
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)  throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		final PrintWriter writer = response.getWriter();
		
		WineaccessError wineAccessError = new WineaccessError(String.valueOf(HttpServletResponse.SC_FORBIDDEN), accessDeniedException.getMessage());
		Set<WineaccessError> errors = new HashSet<WineaccessError>();
		errors.add(wineAccessError);
		
		FailureResponse f = new FailureResponse(errors, HttpServletResponse.SC_OK);
		
		writer.println(new Gson().toJson(f));
	}
}
