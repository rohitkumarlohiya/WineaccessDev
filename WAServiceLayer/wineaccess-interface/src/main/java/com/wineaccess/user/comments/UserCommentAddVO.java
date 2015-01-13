package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlType(name="userCommentAdd")
public class UserCommentAddVO implements Serializable {

	private static final long serialVersionUID = 2330992626087406347L;
	private String message;
	
	public UserCommentAddVO(){
		
	}
	
	public UserCommentAddVO(String msg){
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
