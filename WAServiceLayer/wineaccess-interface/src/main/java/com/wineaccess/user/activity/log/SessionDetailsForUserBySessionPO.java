package com.wineaccess.user.activity.log;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="SessionDetailsForUser")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SessionDetailsForUserBySessionPO extends SessionDetailsForUserPO {

	@NotNull(message="SESSIONINFOFORUSER104")	
	private Long sessionId;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	@NotNull(message="SESSIONINFOFORUSER103")	
	public Long getUserid() {
		return super.getUserid();
	}
}
