package com.wineaccess.user.comments;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MultiDeleteCommentsForDependency implements Serializable {

	private static final long serialVersionUID = -3934539387947205376L;
	private int count;
	private List<Long> deletedList;
	
	public MultiDeleteCommentsForDependency() {
		
	}

	public MultiDeleteCommentsForDependency(int count,List<Long> deletedList) {
		this.count = count;
		this.deletedList = deletedList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count,List<Long> notExists) {
		this.count = count;
	}

	public List<Long> getDeletedList() {
		return deletedList;
	}

	public void setDeletedList(List<Long> deletedList) {
		this.deletedList = deletedList;
	}

}
