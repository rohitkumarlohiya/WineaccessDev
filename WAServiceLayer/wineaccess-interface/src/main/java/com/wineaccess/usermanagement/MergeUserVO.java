package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="MergeUser")
public class MergeUserVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
 
	@XmlElement
	private Long mergedUserId;
	@XmlElement
	private Long userInWhichMergedId;
	@XmlElement
	private String message;
	
	public MergeUserVO() {
	}
	public MergeUserVO(Long toBeMergedUser, Long userInWhichTobeMerged, String message){
		this.mergedUserId = toBeMergedUser;
		this.userInWhichMergedId = userInWhichTobeMerged;
		this.message = message;
	}



	public Long getMergedUserId() {
		return mergedUserId;
	}
	public void setMergedUserId(Long mergedUserId) {
		this.mergedUserId = mergedUserId;
	}
	public Long getUserInWhichMergedId() {
		return userInWhichMergedId;
	}
	public void setUserInWhichMergedId(Long userInWhichMergedId) {
		this.userInWhichMergedId = userInWhichMergedId;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
