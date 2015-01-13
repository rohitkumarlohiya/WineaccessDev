package com.wineaccess.user.activity.log;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement
public class SessionDetailsForUserPO extends AbstractSearchPO {
	private Long userid;


	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	
}
