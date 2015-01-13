package com.wineaccess.data.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;

@Entity
@Table(name = "USER_EMAIL_LOG")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "UserEmailLog.getAll", query = "from UserEmailLog") })
public class UserEmailLog extends Persistent {

	private static final long serialVersionUID = 7786638427034262701L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_ID", columnDefinition = "BIGINT(20)  NULL")
	private Long userId;

	@Column(name = "SUBJECT", columnDefinition = "VARCHAR(500) NULL")
	private String subject;

	@Column(name = "CONTENT", columnDefinition = "VARCHAR(5000) NULL")
	private String content;

	@Column(name = "DELIVERY_STATUS", columnDefinition = "TINYINT(1) NOT NULL")
	private boolean deliveryStatus;

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

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	
}
