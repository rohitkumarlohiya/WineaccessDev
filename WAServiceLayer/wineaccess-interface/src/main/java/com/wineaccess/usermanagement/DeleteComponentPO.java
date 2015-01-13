package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="deleteComponent")
@XmlAccessorType(XmlAccessType.FIELD)

public class DeleteComponentPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	@NotNull(message="USER164")
	@ValidatingListAnnotation(message="USER164")
	@XmlElementWrapper(name="deleteIdsList")
	@XmlElement(name="deleteId")
	private List<Long> deleteIdsList;
	
	@NotEmpty(message = "USER118")
	@Pattern(regexp = RegExConstants.DIGITS, message = "USER166")
	private String userId;
	
	public List<Long> getDeleteIdsList() {
		return deleteIdsList;
	}
	public void setDeleteIdsList(List<Long> deleteIdsList) {
		this.deleteIdsList = deleteIdsList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	


}
