package com.wineaccess.service.newsletter;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.SearchVO;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsletterSearchVO extends SearchVO<List<NewsletterCustomModel>> {

	private static final long serialVersionUID = 1422010525885656908L;

	public NewsletterSearchVO(){
		
	}
	public NewsletterSearchVO(int offSet, int limit, String keyword, int count,int totalCount,List<NewsletterCustomModel> newsletterCustomModels) {

		setOffSet(offSet);
		setLimit(limit);
		if (keyword == null) {
			keyword = "";
		}
		setKeyword(keyword);
		setCount(count);
		setTotalCount(totalCount);
		setSearchResult(newsletterCustomModels);
	}
	
	@Override
	@XmlElementWrapper(name = "newsletterList")	
	public List<NewsletterCustomModel> getSearchResult() {
	
		return super.getSearchResult();
	}

}
