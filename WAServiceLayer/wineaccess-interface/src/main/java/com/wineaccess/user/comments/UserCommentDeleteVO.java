package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlType(name="userCommentDelete")
public class UserCommentDeleteVO implements Serializable {

	private static final long serialVersionUID = -6652014050833265354L;
	private String message;
	
	public UserCommentDeleteVO(){
		
	}
	
	public UserCommentDeleteVO(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
