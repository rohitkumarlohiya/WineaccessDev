package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name="userCommentEdit")
public class UserCommentEditPO extends BasePO implements Serializable {

	private static final long serialVersionUID = -4390217728045311309L;
	@NotNull(message="USER114")
	private Long userId;
	@NotEmpty(message="USERCOMMENT105")
	@Pattern(regexp=RegExConstants.DIGITS, message="USERCOMMENT106")
	private String commentId;
	@NotBlank(message="USERCOMMENT101")
	private String comment;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
