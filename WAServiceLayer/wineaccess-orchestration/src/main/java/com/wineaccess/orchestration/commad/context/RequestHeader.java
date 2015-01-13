package com.wineaccess.orchestration.commad.context;

import java.io.Serializable;
import java.util.UUID;

/**
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class RequestHeader implements Serializable {

	/**
	 * A way to identify every unique request. Useful to integrate artifacts
	 * such as log records.
	 */
	protected String requestId = UUID.randomUUID().toString();

	protected String requestingIpAddress;


	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestingIpAddress() {
		return requestingIpAddress;
	}

	public void setRequestingIpAddress(String requestingIpAddress) {
		this.requestingIpAddress = requestingIpAddress;
	}
}
