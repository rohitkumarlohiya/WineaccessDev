package com.wineaccess.service.newsletter;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
@XmlRootElement(name="newsletter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class NewsletterDeletePO extends BasePO implements Serializable{
	private static final long serialVersionUID = -2879057461758940654L;
	@NotBlank(message = "MANDATORYFIELDS101")
	@NotNull(message = "MANDATORYFIELDS101")
	private String id;
	
	@NotNull(message = "MANDATORYFIELDS101")
	private Boolean isForceDelete;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsForceDelete() {
		return isForceDelete;
	}
	public void setIsForceDelete(Boolean isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

}