package com.wineaccess.user.comments;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="listCommentsByUserId")
public class ListCommentsByUserIdPO extends AbstractSearchPO {

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
