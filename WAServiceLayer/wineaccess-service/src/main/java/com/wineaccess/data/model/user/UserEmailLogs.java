package com.wineaccess.data.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;

@Entity
@Table(name = "USER_EMAIL_LOG")
@EntityListeners(EntityListener.class)
public class UserEmailLogs extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private Long userId;

	@Column(name = "SUBJECT", columnDefinition = "VARCHAR(45) NULL")
	private String subject;

	@Column(name = "CONTENT", columnDefinition = "VARCHAR(45) NULL")
	private String content;

	@Column(name = "DELIVERY_STATUS", columnDefinition = "VARCHAR(45) NULL")
	private String deliveryStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
}
