package com.wineaccess.response;

import java.io.Serializable;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class WineaccessError implements Serializable {

	private String errorCode;
	private String errorMessage;
	
	public WineaccessError() {
	}
	
	public WineaccessError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public int hashCode() {
		return this.errorCode.hashCode();
	}
	
	public boolean equals( Object obj ) {
		WineaccessError wineaccessError = (WineaccessError )obj;
		if( wineaccessError.getErrorCode().equals(this.errorCode)) {
			return true;
		} else {
			return false;
		}
	}

}
