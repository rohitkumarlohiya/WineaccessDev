package com.wineaccess.data.model.user;

import java.util.Date;

import com.wineaccess.data.model.WineaccessModel;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class CreditCard extends WineaccessModel {

	private String creditCartNumber;
	private String creditCartType;
	private Date expirationDate;
	private String cvvCode;
	private String pgProfileId;

	public String getCreditCartNumber() {
		return creditCartNumber;
	}

	public void setCreditCartNumber(String creditCartNumber) {
		this.creditCartNumber = creditCartNumber;
	}

	public String getCreditCartType() {
		return creditCartType;
	}

	public void setCreditCartType(String creditCartType) {
		this.creditCartType = creditCartType;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}

	public String getPgProfileId() {
		return pgProfileId;
	}

	public void setPgProfileId(String pgProfileId) {
		this.pgProfileId = pgProfileId;
	}
}
