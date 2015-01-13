package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name="userCommentAdd")
public class UserCommentAddPO extends BasePO implements Serializable {

	private static final long serialVersionUID = -8053253086302563397L;
	@NotNull(message="USER114")
	@Digits(fraction=0,integer=32,message="USERCOMMENT102")
	private Long userId;
	
	@NotBlank(message="USERCOMMENT101")
	private String comment;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
