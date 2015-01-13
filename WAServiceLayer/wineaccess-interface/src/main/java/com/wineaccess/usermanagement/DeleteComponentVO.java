package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="deleteComponent")
@XmlAccessorType(XmlAccessType.FIELD)

public class DeleteComponentVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	
	@XmlElement(name="Ids")
	private List<Long> deleteIdsList;
	@XmlElement(name="message")
	private String message;
	
	public DeleteComponentVO(List<Long> deleteIdsList, String message)
	{
		this.deleteIdsList = deleteIdsList;
		this.message = message;
	}
	public DeleteComponentVO(){
		
	}
	
	public List<Long> getDeleteIdsList() {
		return deleteIdsList;
	}
	public void setDeleteIdsList(List<Long> deleteIdsList) {
		this.deleteIdsList = deleteIdsList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	


}
