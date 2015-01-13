package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;
@XmlRootElement
public class CloneUserPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	private String toEmailId;
	private String fromEmailId;
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	public String getFromEmailId() {
		return fromEmailId;
	}
	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
	}



}
