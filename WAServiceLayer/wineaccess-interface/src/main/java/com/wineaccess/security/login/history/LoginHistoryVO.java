package com.wineaccess.security.login.history;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginHistoryVO implements Serializable {

	private static final long serialVersionUID = -8661707728132362588L;
	private Long userId;
	
	
	int count;
	int totalRecordsCount;
	int limit = 10;
	int offSet =0;
	
	@XmlElementWrapper(name = "sessionHistory")
	@XmlElement(name = "loginHistory")
	private List<SessionHistoryVO> sessions;
	
	public LoginHistoryVO(){
		
	}
	
	public LoginHistoryVO(Long userId,List<SessionHistoryVO> sessionHistoryVOs){
		this.userId = userId;
		this.sessions = sessionHistoryVOs;
	}
	
	


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<SessionHistoryVO> getSessionsHistory() {
		return sessions;
	}

	public void setSessionsHistory(List<SessionHistoryVO> sessionsHistory) {
		this.sessions = sessionsHistory;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(int totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offSet;
	}

	public void setOffset(int offSet) {
		this.offSet = offSet;
	}
	
	
	

}
