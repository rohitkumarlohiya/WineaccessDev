package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name="userCommentMultiDelete")
public class UserCommentMultiDeletePO extends BasePO implements Serializable {

	private static final long serialVersionUID = -5125570771699998239L;
	@NotNull(message = "USER114")
	private Long userId;
	@NotNull(message = "USERCOMMENT104")
	private String commentIds;
	private Boolean confirmStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(String commentIds) {
		this.commentIds = commentIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Boolean confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

}
