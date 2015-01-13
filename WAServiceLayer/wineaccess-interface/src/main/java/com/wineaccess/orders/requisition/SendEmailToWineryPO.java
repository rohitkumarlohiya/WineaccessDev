package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SendEmailToWineryPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "SEND_EMAIL_TO_WINERY_ERROR_101")
	@Pattern(regexp = RegExConstants.DIGITS, message = "SEND_EMAIL_TO_WINERY_ERROR_102")
	private String requisitionId;

	@NotEmpty(message = "SEND_EMAIL_TO_WINERY_ERROR_103")
	@Pattern(regexp = RegExConstants.EMAIL, message = "SEND_EMAIL_TO_WINERY_ERROR_104")
	private String email;

	@NotEmpty(message = "SEND_EMAIL_TO_WINERY_ERROR_105")
	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "SEND_EMAIL_TO_WINERY_ERROR_106")
	private String subject;

	@NotEmpty(message = "SEND_EMAIL_TO_WINERY_ERROR_107")
	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "SEND_EMAIL_TO_WINERY_ERROR_108")
	private String content;

	public String getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
