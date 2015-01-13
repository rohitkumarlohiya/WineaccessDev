package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlRootElement
@XmlType(name="addSamplerProduct")
public class AddSamplerProductVO implements Serializable {

	private static final long serialVersionUID = 7807996828620330267L;
	
	private long id;
	private String message;
	
	public AddSamplerProductVO(){
		
	}
	
	public AddSamplerProductVO(long id,String message){
		this.id = id;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
