package com.wineaccess.useremaillog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "count", "totalRecordCount", "offSet", "limit",
		"keyword", "userEmailLogs" })
@JsonPropertyOrder({ "count", "totalRecordCount", "offSet", "limit", "keyword",
		"userEmailLogs" })
public class UserEmailLogSearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;

	private int totalRecordCount;

	private int offSet;

	private int limit;

	private String keyword;

	private List<UserEmailLogCustomModel> userEmailLogs = new ArrayList<UserEmailLogCustomModel>();

	public UserEmailLogSearchVO() {
		super();
	}

	public UserEmailLogSearchVO(int count, int totalRecordCount, int offSet,
			int limit, String keyword,
			List<UserEmailLogCustomModel> userEmailLogs) {
		super();
		this.count = count;
		this.totalRecordCount = totalRecordCount;
		this.offSet = offSet;
		this.limit = limit;
		this.keyword = keyword;
		this.userEmailLogs = userEmailLogs;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<UserEmailLogCustomModel> getUserEmailLogs() {
		return userEmailLogs;
	}

	public void setUserEmailLogs(List<UserEmailLogCustomModel> userEmailLogs) {
		this.userEmailLogs = userEmailLogs;
	}

}
