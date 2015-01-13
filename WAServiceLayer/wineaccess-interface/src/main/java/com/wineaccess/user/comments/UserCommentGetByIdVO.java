package com.wineaccess.user.comments;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="userCommentGetById")
public class UserCommentGetByIdVO implements Serializable {

	private static final long serialVersionUID = 6786548997421463231L;
	private Long userId;
	private Long commentId;
	private String comment;
	
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date createdDate;
	
	private UserServiceModel createdBy;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public UserServiceModel getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserServiceModel createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
