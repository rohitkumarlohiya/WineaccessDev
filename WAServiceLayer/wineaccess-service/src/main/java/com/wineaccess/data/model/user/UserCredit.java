package com.wineaccess.data.model.user;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "USER_CREDIT")
@EntityListeners(EntityListener.class)
public class UserCredit extends Persistent {

	private static final long serialVersionUID = -8777182781305794009L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private Long userId;

	@Column(name = "TRANSACTION_REASON", columnDefinition = "VARCHAR(255) NULL")
	private String trasactionReason;

	@Column(name = "TRANSACTION_AMOUNT", columnDefinition = "DECIMAL(10,4) NULL")
	private BigDecimal transactionAmount;

	@Column(name = "EXPIRATION_DATE", columnDefinition = "DATETIME NULL")
	private Date expirationDate;

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

	public String getTrasactionReason() {
		return trasactionReason;
	}

	public void setTrasactionReason(String trasactionReason) {
		this.trasactionReason = trasactionReason;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
