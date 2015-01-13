package com.wineaccess.response;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;


/**
 * @author jyoti.yadav@globallogic.com
 */
@XmlRootElement
public class FailureResponse extends Response {
	
	public FailureResponse(){
	}

	public FailureResponse(Set<WineaccessError> errors, int status) {
		this.setStatus(RESPONSECODES.SUCCESSCODE.getResponseCodes());
		this.setErrors(errors);
	}
}
