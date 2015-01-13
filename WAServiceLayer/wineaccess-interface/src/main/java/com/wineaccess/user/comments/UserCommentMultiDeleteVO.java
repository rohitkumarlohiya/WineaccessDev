package com.wineaccess.user.comments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="userCommentMultiDelete")
public class UserCommentMultiDeleteVO implements Serializable{

	private static final long serialVersionUID = 3213634064509441712L;
	
	private MultiDeleteCommentsForNotExists notExists;
	private MultiDeleteCommentsForDependency dependency;
	private MultiDeleteCommentsForDependency canBeDeleted;
	
	public UserCommentMultiDeleteVO() {
		notExists = new MultiDeleteCommentsForNotExists();
		dependency = new MultiDeleteCommentsForDependency();
		canBeDeleted = new MultiDeleteCommentsForDependency();
	}

	public UserCommentMultiDeleteVO(MultiDeleteCommentsForNotExists notExists,MultiDeleteCommentsForDependency canBedeleted,MultiDeleteCommentsForDependency dependency) {
		this.notExists = notExists;
		this.dependency = dependency;
		this.canBeDeleted = canBedeleted;
	}

	public MultiDeleteCommentsForNotExists getNotExists() {
		return notExists;
	}

	public void setNotExists(MultiDeleteCommentsForNotExists notExists) {
		this.notExists = notExists;
	}

	public MultiDeleteCommentsForDependency getDependency() {
		return dependency;
	}

	public void setDependency(MultiDeleteCommentsForDependency dependency) {
		this.dependency = dependency;
	}

	public MultiDeleteCommentsForDependency getCanBeDeleted() {
		return canBeDeleted;
	}

	public void setCanBeDeleted(MultiDeleteCommentsForDependency canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}


}
