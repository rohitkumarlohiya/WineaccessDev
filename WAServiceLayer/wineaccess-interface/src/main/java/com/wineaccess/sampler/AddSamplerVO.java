package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * VO of add sampler
 */
@XmlRootElement
@XmlType(name = "addSampler")
public class AddSamplerVO implements Serializable {

	private static final long serialVersionUID = -5807205884440583795L;

	private long samplerId;
	private String message;
	
	public AddSamplerVO(){
		
	}
	
	public AddSamplerVO(long samplerId, String message){
		this.samplerId =samplerId;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(long samplerId) {
		this.samplerId = samplerId;
	}

}
