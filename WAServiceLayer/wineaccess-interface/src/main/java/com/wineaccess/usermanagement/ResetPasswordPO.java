package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name = "resetPassword")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResetPasswordPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 3575196697988294103L;
	
	private Boolean isforceDelete;
	@XmlElementWrapper(name = "userIds")
	@XmlElement(name = "userId")
	private List<Long> userIds;
	private String url;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public Boolean getIsforceDelete() {
		return isforceDelete;
	}

	public void setIsforceDelete(Boolean isforceDelete) {
		this.isforceDelete = isforceDelete;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
