package com.wineaccess.emailtemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.SearchVO;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class EmailTemplateSearchVO extends SearchVO<EmailTemplateSearch>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4396465844348346545L;


	public EmailTemplateSearchVO(int offSet, int limit, String keyword,	int count, int totalCount) {
		this();
		setOffSet(offSet);
		setLimit(limit);
		if(keyword == null){
			keyword ="";
		}
		setKeyword(keyword);
		setCount(count);
		setTotalCount(totalCount);
		setSearchResult(new EmailTemplateSearch());
	}

	public EmailTemplateSearchVO() {
		
	}

	
	@Override
	
	@XmlElement(name="emailTemplateSearchResult")
	public EmailTemplateSearch getSearchResult() {
		return searchResult;
	}
}
