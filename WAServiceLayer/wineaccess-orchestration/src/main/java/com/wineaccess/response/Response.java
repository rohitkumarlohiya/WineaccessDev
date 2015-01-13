package com.wineaccess.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;

/**
 * The response object that to be return to the client.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public abstract class Response implements Serializable {

	private Set<WineaccessError> errors = new HashSet<WineaccessError>();

	private int status = RESPONSECODES.SUCCESSCODE.getResponseCodes();
	
	
	public Response(){
	}
	
	public Response(int status, Set<WineaccessError> errors){
		this.status = status;
		this.errors = errors;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<WineaccessError> getErrors() {
		return errors;
	}

	public void setErrors(Set<WineaccessError> errors) {
		this.errors = errors;
	}
	
	public void addError(WineaccessError error) {
		this.getErrors().add(error);
	}

}
