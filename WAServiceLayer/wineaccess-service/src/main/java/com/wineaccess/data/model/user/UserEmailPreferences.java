/*package com.wineaccess.data.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;

*//**
 * @author jyoti.yadav@globallogic.com
 *//*
@Entity
@Table(name = "USER_EMAIL_PREFERENCES")
@EntityListeners(EntityListener.class)
public class UserEmailPreferences extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private Long userId;

	@ManyToOne
    @JoinColumn(name="NEWS_LETTER_ID",referencedColumnName="ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
    private NewsLetter newsLetterId;
	
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

	public NewsLetter getNewsLetterId() {
		return newsLetterId;
	}

	public void setNewsLetterId(NewsLetter newsLetterId) {
		this.newsLetterId = newsLetterId;
	}
	
}
*/