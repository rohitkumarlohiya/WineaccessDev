package com.wineaccess.emailtemplatetype;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.SearchVO;

@XmlRootElement(name="EmailTemplateTypeSearchResult")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
//@JsonPropertyOrder({"count","totalCount","offSet","limit","searchResult"})
@XmlSeeAlso(EmailTemplateTypeSearch.class)
public class EmailTemplateTypeSearchVO extends SearchVO<List<EmailTemplateTypeSearch>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3772736940473027462L;
	
	public EmailTemplateTypeSearchVO(){
		super();
	}
	
	public EmailTemplateTypeSearchVO(int offSet, int limit, String keyword,	int count, int totalCount) {
		this();
		setOffSet(offSet);
		setLimit(limit);
		if(keyword == null){
			keyword ="";
		}
		setKeyword(keyword);
		setCount(count);
		setTotalCount(totalCount);
		setSearchResult(new ArrayList<EmailTemplateTypeSearch>());
	}

	@Override
	@XmlElementWrapper(name="emailTemplateTypes")
	@XmlElement(name="emailTemplateType")
	public List<EmailTemplateTypeSearch> getSearchResult() {
		return super.getSearchResult();
		
	}

	
}
