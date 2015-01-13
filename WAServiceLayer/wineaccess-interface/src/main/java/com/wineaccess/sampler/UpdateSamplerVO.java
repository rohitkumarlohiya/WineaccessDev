package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * VO of the update Sampler
 */
@XmlRootElement
@XmlType(name="updateSampler")
public class UpdateSamplerVO extends BasePO implements Serializable {

	private static final long serialVersionUID = -5094858332226866722L;
	
	private long samplerId;
	private String message;
	
	public UpdateSamplerVO(){
		
	}
	
	public UpdateSamplerVO(long samplerId, String message){
		this.samplerId =samplerId;
		this.message = message;
	}

	public long getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(long samplerId) {
		this.samplerId = samplerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
