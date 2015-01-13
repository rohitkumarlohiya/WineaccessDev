package com.wineaccess.user.activity.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"totalResultsCount","searchResultsCount","offset", "limit", "userTokens"})
public class TokenVO implements Serializable{
	
	



	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1978638543150168662L;

	public TokenVO(){
		userTokens = new ArrayList<UserToken>();
	}
	
	Long totalResultsCount;
	Long searchResultsCount;
	int offset;
	int limit;
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@XmlElementWrapper(name = "UserSessions")
	@XmlElement(name = "UserSession")
	List<UserToken> userTokens;
	
	
	public Long getTotalResultsCount() {
		return totalResultsCount;
	}
	public void setTotalResultsCount(Long totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}
	
	
	
	public Long getSearchResultsCount() {
		return searchResultsCount;
	}
	public void setSearchResultsCount(Long searchResultsCount) {
		this.searchResultsCount = searchResultsCount;
	}
	public List<UserToken> getUserTokens() {
		return userTokens;
	}
	public void setUserTokens(List<UserToken> userTokens) {
		this.userTokens = userTokens;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
