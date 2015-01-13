package com.wineaccess.service.user.preference;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="subscriptionDetails")
public class UserPreferenceByIdPO extends BasePO implements Serializable{

	private static final long serialVersionUID = -2879057461758940654L;
	private List<Long> userid;
	public List<Long> getUserid() {
		return userid;
	}
	public void setUserid(List<Long> userid) {
		this.userid = userid;
	}
	

}
