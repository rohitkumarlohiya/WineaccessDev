package com.wineaccess.rest.client;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class HttpResponse {

	private int httpStatusCode;
	private String responseBody;
	
	public HttpResponse() {
	}
	
	public HttpResponse(int httpStatusCode, String responseBody) {
		this.httpStatusCode = httpStatusCode;
		this.responseBody = responseBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
