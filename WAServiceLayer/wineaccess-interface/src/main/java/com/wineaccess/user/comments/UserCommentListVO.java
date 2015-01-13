package com.wineaccess.user.comments;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlType(name="userCommentList")
public class UserCommentListVO implements Serializable {

	private static final long serialVersionUID = 5676908935706654534L;
	private int totalResultsCount;
	private int offset;
	private int limit;
	private Long userId;
	private List<UserCommentModel> comments;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(int totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<UserCommentModel> getComments() {
		return comments;
	}

	public void setComments(List<UserCommentModel> comments) {
		this.comments = comments;
	}

}
