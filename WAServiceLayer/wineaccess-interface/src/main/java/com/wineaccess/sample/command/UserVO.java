package com.wineaccess.sample.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.data.model.user.UserModel;

/**
 * @author jyoti.yadav
 */
@XmlRootElement
public class UserVO implements Serializable{
	
	private List<UserModel> users = new ArrayList<UserModel>();
	
	public UserVO(List<UserModel> users) {
		this.users = users;
	}
	
	public UserVO() {
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
