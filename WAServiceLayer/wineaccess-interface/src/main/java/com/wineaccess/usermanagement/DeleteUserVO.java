package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DeleteUserVO.class,UsersDescriptionListVO.class})
public class DeleteUserVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	UsersDescriptionListVO usersDescriptionListVO = null;

	
	public UsersDescriptionListVO getUsersDescriptionListVO() {
		return usersDescriptionListVO;
	}


	public void setUsersDescriptionListVO(
			UsersDescriptionListVO usersDescriptionListVO) {
		this.usersDescriptionListVO = usersDescriptionListVO;
	}


	public DeleteUserVO() {
		
		super();
		usersDescriptionListVO = new UsersDescriptionListVO();
	}


	public DeleteUserVO(UsersDescriptionListVO usersDescriptionListVO) {
		super();
		this.usersDescriptionListVO = usersDescriptionListVO;
	}

}
