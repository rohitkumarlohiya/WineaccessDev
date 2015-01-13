package com.wineaccess.user.comments;

import java.io.Serializable;
import java.util.ArrayList;
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
public class MultiDeleteCommentsForNotExists implements Serializable {

	private static final long serialVersionUID = 7378839877712971823L;
	private int count;
	private List<Long> notExists;

	public List<Long> getNotExists() {
		return notExists;
	}

	public void setNotExists(List<Long> notExists) {
		this.notExists = notExists;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public MultiDeleteCommentsForNotExists() {
		notExists = new ArrayList<Long>();
	}

	public MultiDeleteCommentsForNotExists(int count, List<Long> notExists) {
		this.count = count;
		this.notExists = notExists;
	}

}
