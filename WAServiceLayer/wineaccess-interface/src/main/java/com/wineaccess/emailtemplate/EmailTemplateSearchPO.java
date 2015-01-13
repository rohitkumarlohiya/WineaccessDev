/**
 * 
 */
package com.wineaccess.emailtemplate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

/**
 * @author anurag.jain3
 *
 */
@XmlRootElement(name="EmailTemplateTypeSearchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateSearchPO extends AbstractSearchPO {

	private static final long serialVersionUID = -8997246809043369048L;
	@NotNull(message="EMAILTEMPLATETYPE103")
	Long emailTemplateTypeId;
	
	public EmailTemplateSearchPO(){
		super();
		if(getKeyword() == null){
			setKeyword("");
		}
		
	}

	public Long getEmailTemplateTypeId() {
		return emailTemplateTypeId;
	}

	public void setEmailTemplateTypeId(Long emailTemplateTypeId) {
		this.emailTemplateTypeId = emailTemplateTypeId;
	}
	
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_EMAIL_TEMPLATE, message = "EMAILTEMPLATE114")
	public String getSortBy() {
		return sortBy;
	}
}
