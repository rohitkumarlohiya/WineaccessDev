package com.wineaccess.importer;

public class ImporterCustomException extends Exception {

	private String exceptionType;

	public ImporterCustomException(){
		
	}
	
	public ImporterCustomException(String exceptionType){
		this.exceptionType = exceptionType;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	
}
