package com.wineaccess.response;


public class ValidationFailedError extends Exception {

	private Response failedResponse;

	public ValidationFailedError() {
	}

	public ValidationFailedError(Response failedResponse) {
		this.failedResponse = failedResponse;
	}

	public Response getFailedResponse() {
		return failedResponse;
	}
}
