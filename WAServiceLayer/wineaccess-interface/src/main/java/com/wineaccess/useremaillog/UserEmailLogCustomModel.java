package com.wineaccess.useremaillog;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserEmailLogCustomModel {

	private Long id;
	private String content;
	private String subject;
	private Long userId;
	private boolean deliveryStatus;
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date deliveryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public UserEmailLogCustomModel(Long id, String content, String subject,
			Long userId, boolean deliveryStatus) {
		super();
		this.id = id;
		this.content = content;
		this.subject = subject;
		this.userId = userId;
		this.deliveryStatus = deliveryStatus;
	}
	
	public UserEmailLogCustomModel(Long id, String content, String subject,
			Long userId, boolean deliveryStatus, Date deliveryDate) {
		this(id,content,subject,userId,deliveryStatus);
		this.deliveryDate = deliveryDate;
	}


	public UserEmailLogCustomModel() {
		super();
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	

	
}
