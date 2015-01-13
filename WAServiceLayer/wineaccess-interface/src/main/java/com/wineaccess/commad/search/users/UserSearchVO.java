package com.wineaccess.commad.search.users;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jyoti.yadav@globallogic.com
 */
@XmlRootElement
public class UserSearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;
	
	private int totalRecordCount;
	
	private int offSet;

	private int limit;

	private String keyword;
	
	private List<UserModelVO> userModels;

	public UserSearchVO() {
	}

	public UserSearchVO(int count, int offSet, int limit, List<UserModelVO> userModels, String keyword) {
		this.count = count;
		this.offSet = offSet;
		this.limit = limit;
		this.userModels = userModels;
		this.keyword = keyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public List<UserModelVO> getUserModels() {
		return userModels;
	}

	public void setUserModels(List<UserModelVO> userModels) {
		this.userModels = userModels;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
}
