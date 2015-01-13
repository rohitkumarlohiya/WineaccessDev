package com.wineaccess.user.comments;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserCommentModel implements Serializable{

	private static final long serialVersionUID = -4146226766771588842L;
	private Long commentId;
	private String comment;
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date createdDate;
	private UserServiceModel createdBy;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedtDate(Date createdtDate) {
		this.createdDate = createdtDate;
	}

	public UserServiceModel getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserServiceModel createdBy) {
		this.createdBy = createdBy;
	}
}
