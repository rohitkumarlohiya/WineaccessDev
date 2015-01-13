package com.wineaccess.user.activity.log;

public class ActivityLogException extends Exception {
	String statusErrorCode;
	String errorMessage;
	Integer statusCode;
	
	public String getStatusErrorCode() {
		return statusErrorCode;
	}
	public void setStatusErrorCode(String statusErrorCode) {
		this.statusErrorCode = statusErrorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public ActivityLogException(String statusErrorCode, String errorMessage,
			Integer statusCode) {
		super();
		this.statusErrorCode = statusErrorCode;
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}
	public ActivityLogException() {
		super();
	}
	
}
