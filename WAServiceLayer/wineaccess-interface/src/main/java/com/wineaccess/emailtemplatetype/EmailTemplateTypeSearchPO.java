/**
 * 
 */
package com.wineaccess.emailtemplatetype;

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
public class EmailTemplateTypeSearchPO extends AbstractSearchPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8997246809043369048L;
	
	public EmailTemplateTypeSearchPO(){
		super();
		if(getKeyword() == null){
			setKeyword("");
		}
	}
	
	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_EMAIL_TEMPLATE_TYPE, message = "EMAILTEMPLATETYPE107")
	public String getSortBy() {
		return sortBy;
	}
}