/**
 * 
 */
package com.wineaccess.user.activity.log;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;

/**
 * @author anurag.jain3
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"eventName", "createdDate","activity"})
public class Activity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7989754580450192002L;
	@XmlElement(name="activityName")
	String eventName;
	@XmlElement(name="activityDate")
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	Date createdDate;
	@XmlElement(name="activityData")
	String activity;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
