package com.wineaccess.usermanagement;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;
/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="mergeUser")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MergeUserPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;

	@NotNull(message="MERGEUSER101")	
	private Long toBeMergedUser;
	
	@NotNull(message="MERGEUSER102")
	private Long userInWhichTobeMerged;
	
	public Long getToBeMergedUser() {
		return toBeMergedUser;
	}
	public void setToBeMergedUser(Long toBeMergedUser) {
		this.toBeMergedUser = toBeMergedUser;
	}
	public Long getUserInWhichTobeMerged() {
		return userInWhichTobeMerged;
	}
	public void setUserInWhichTobeMerged(Long userInWhichTobeMerged) {
		this.userInWhichTobeMerged = userInWhichTobeMerged;
	}


}
