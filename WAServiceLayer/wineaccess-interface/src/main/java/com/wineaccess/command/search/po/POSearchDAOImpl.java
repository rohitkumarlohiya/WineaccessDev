package com.wineaccess.command.search.po;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;

import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.constants.EnumTypes.POStatus;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.lucene.comparator.LuceneSortComparator;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.util.SearchUtils;

public class POSearchDAOImpl  <T extends Persistent> extends JPAGenericDAOImpl<T> implements POSearchDAO<T> {
	
	@PersistenceContext
	EntityManager em;
	
	String[] searchFields = new String[] {"id" ,"typeOfPO", "status", "wineryName"};
	String[] sortFields = new String[] {"id", "typeOfPO", "submittedDate", "status", "wineryNameSort", "winesCount", "bottlesCount", 
			"expectedPickupDate", "actualPickupDate", "expectedArrivalDate", "actualArrivalDate", "expecteShippingDate", "actualShippingDate"};
	

	@Override	
	public POSearchVO normalSearch(POSearchPO poSearchPO) 	throws ParseException, UserSearchException {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		QueryParser queryParser = null;
		
		if (poSearchPO.getSearchBy().isEmpty()) {
			List<String> poSearchFields = new ArrayList<String>();
			for(String field : searchFields){
				poSearchFields.add(field);
			}
			poSearchFields.add("status");
			String [] s = new String [poSearchFields.size()];
			queryParser = new MultiFieldQueryParser(Version.LUCENE_34, (String[] ) poSearchFields.toArray(s), new StandardAnalyzer(Version.LUCENE_34));
		} else {
			for (int i = 0; i < searchFields.length; i++) {
				if(poSearchPO.getSearchBy().equalsIgnoreCase(searchFields[i]))  {
					queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {searchFields[i], "status"}, new StandardAnalyzer(Version.LUCENE_34));
				}
			}
		}
		
		queryParser.setAllowLeadingWildcard(true);
		
		String keyword = SearchUtils.refineSearchKeyword(poSearchPO.getKeyword());
		
		String queryString = getQueryString(keyword, poSearchPO.getSearchBy(), poSearchPO.getStatusType());
		
		Query normalQuery = queryParser.parse(queryString);
		
		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(normalQuery, Occur.MUST);
		
		boolean orderDirect = Integer.parseInt(poSearchPO.getSortOrder()) == 1 ? false : true;

		Sort sort = getSort(poSearchPO.getSortBy(), orderDirect);
		
		SearchUtils.doExclusion(luceneQuery, poSearchPO.getExclusions());

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, RequisitionModel.class, sort, Integer.parseInt(poSearchPO.getOffSet()), Integer.parseInt(poSearchPO.getLimit()));

		List<RequisitionModel> models = fullTextQuery.getResultList();
		int size = fullTextQuery.getResultSize();
		
		List<REQModelVO> modelsVOS = new ArrayList<REQModelVO>();
		
		for (RequisitionModel purchageOrderModel : models){
			REQModelVO poModelVO = new REQModelVO(purchageOrderModel);
			modelsVOS.add(poModelVO);
		}
		POSearchVO POSearchVO = new POSearchVO(poSearchPO, modelsVOS, getPurchageOrderCount(), size);
		return POSearchVO;
	}
	
	
	public int getPurchageOrderCount() throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "id" }, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "id:" + "*";

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, RequisitionModel.class);

		int size = fullTextQuery.getResultSize();

		return size;
	}
	
	private Sort getSort(String sortBy, boolean orderDirect) {
		
		if (sortBy.equals("wineryName")){
			sortBy = "wineryNameSort";
		}
		
		if (sortBy.equals("id") || sortBy.equals("winesCount") || sortBy.equals("bottlesCount")) {
			return new Sort(new SortField(sortBy, SortField.LONG, orderDirect));
		} else {
			return new Sort(new SortField(sortBy, new LuceneSortComparator(), orderDirect));
		}
	}
	
	private String getQueryString(String keyword, String searchBy, String statusType) {
		
		String stattusTypeQuery = "";
		
		/*if (statusType.equals("AOPN")){
		}*/
		if (statusType.equals("AAPP")){
			stattusTypeQuery = "(status:" + POStatus.APPROVED + ")";
		} else if (statusType.equals("ASUB")){
			stattusTypeQuery = "(status:" + POStatus.SUBMITTED + ")";
		} else if (statusType.equals("AREL")){
			stattusTypeQuery = "(status:" + POStatus.RELEASED + ")";
		} else if (statusType.equals("ASCH")){
			stattusTypeQuery = "(status:" + POStatus.SCHEDULED_FOR_PICKUP + ")";
		} else if (statusType.equals("AREC")){
			stattusTypeQuery = "(status:" + POStatus.RECEIVED + ")";
		} else {
			stattusTypeQuery = "(status:*" + ")";
		}

		String queryString = null;
		
		for (int i = 0; i < searchFields.length; i++) {
			if(searchBy.equalsIgnoreCase(searchFields[i])) {
				queryString = "(" + searchFields[i] + ":*" + keyword + "*) AND " + stattusTypeQuery;
				break;
			}
		}
		
		if (searchBy.isEmpty()) {
			queryString = "(" + searchFields[0] + ":*" + keyword + "*) AND  " + stattusTypeQuery;
		}
		return queryString;
	}
}
