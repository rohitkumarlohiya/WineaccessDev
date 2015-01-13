package com.wineaccess.useremaillog;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserEmailLogListVO implements Serializable {

	private static final long serialVersionUID = -3068169115996732924L;
	
	@XmlElement
	List<UserEmailLogCustomModel> userEmailLogs;

	public List<UserEmailLogCustomModel> getUserEmailLogs() {
		return userEmailLogs;
	}

	public void setUserEmailLogs(List<UserEmailLogCustomModel> userEmailLogs) {
		this.userEmailLogs = userEmailLogs;
	}

	public UserEmailLogListVO(List<UserEmailLogCustomModel> userEmailLogs) {
		super();
		this.userEmailLogs = userEmailLogs;
	}

	public UserEmailLogListVO() {
		super();
	}

}
