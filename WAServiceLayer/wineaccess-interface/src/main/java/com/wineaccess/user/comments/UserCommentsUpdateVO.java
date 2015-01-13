package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlRootElement
@XmlType(name="userCommentUpdate")
public class UserCommentsUpdateVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UserCommentsUpdateVO(){
		
	}
	
	public UserCommentsUpdateVO(String msg){
		this.message = msg;
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
