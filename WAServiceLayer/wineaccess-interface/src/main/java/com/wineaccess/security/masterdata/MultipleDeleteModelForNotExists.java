package com.wineaccess.security.masterdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MultipleDeleteModelForNotExists implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6733206059834056089L;
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

	public MultipleDeleteModelForNotExists() {
		super();
		notExists = new ArrayList<Long>();
	}

	public MultipleDeleteModelForNotExists(int count, List<Long> notExists) {
		super();
		this.count = count;
		this.notExists = notExists;
	}

}
