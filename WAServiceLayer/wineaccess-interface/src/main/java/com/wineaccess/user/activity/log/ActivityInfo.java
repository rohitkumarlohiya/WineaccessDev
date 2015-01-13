package com.wineaccess.user.activity.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"totalCount","offset","limit", "activityList"})
public class ActivityInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4336393357717560452L;
	int totalCount;
	
	int limit;
	@XmlElement(name = "offSet")
	int offset;
	@XmlElementWrapper(name = "session_activity_list")
	@XmlElement(name = "session_activity")
	List<Activity> activityList;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<Activity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	
	public ActivityInfo(){
		super();
		activityList = new ArrayList<Activity>();
	}

}
