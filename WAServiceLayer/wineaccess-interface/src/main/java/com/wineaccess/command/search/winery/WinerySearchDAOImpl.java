package com.wineaccess.command.search.winery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;

import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.common.DateRangeCriteriaPO;
import com.wineaccess.common.RangeSearchCriteriaPO;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.importer.ImporterRepository;
import com.wineaccess.lucene.comparator.LuceneSortComparator;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.util.SearchUtils;
import com.wineaccess.winery.WineryRepository;

public class WinerySearchDAOImpl <T extends Persistent> extends JPAGenericDAOImpl<T> implements WinerySearchDAO<T> {
	
	@PersistenceContext
	EntityManager em;
	 
	String[] searchFields = new String[] {"id" ,"wineryName", "region.stateCode" ,"waContact.name","wineCount","activeWineCount","revenue1","lastOfferDate","activeImporterId", "region.stateCode"};
	String[] sortFields = new String[] {"id","wineryCode", "wineryNameSort", "region.stateCode", "activeImporterId", "waContact.name", "wineCount", "activeWineCount", "revenue", "lastOfferDate"};
	
	static Map <String, String> props = new HashMap<String, String>();
	static Map <String, String> sortProp = new HashMap<String, String>();
	static {
		props.put("id", "id");
		props.put("wineryName", "wineryName"); 
		props.put("wineryCode", "wineryCode");
		props.put("wineryRegion", "region.stateCode");
		props.put("importerName", "activeImporterId");
		props.put("waContact", "waContact.name");
		props.put("wines", "wineCount");
		props.put("activeWines", "activeWineCount");
		props.put("totalRevenue", "revenue1");
		props.put("lastOfferDate", "lastOfferDate");
		
		
		sortProp.put("id", "id");
		sortProp.put("wineryName", "wineryNameSort"); 
		sortProp.put("wineryCode", "wineryCode");
		sortProp.put("wineryRegion", "region.stateCodeSort");
		sortProp.put("importerName", "activeImporterIdSort");
		sortProp.put("waContact", "waContact.nameSort");
		sortProp.put("wines", "wineCount");
		sortProp.put("activeWines", "activeWineCount");
		sortProp.put("totalRevenue", "revenue");
		sortProp.put("lastOfferDate", "lastOfferDate");
	}
	
	
	
	public String getWineryFieldName(String wineryType) {
		if (wineryType.equals("AE") || wineryType.equals("AD")) {
			return "isEnabled"; 
		} 
		return "";
	}
	
	private void deletedQuery(BooleanQuery luceneQuery) throws ParseException {
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"isDeleted"}, new StandardAnalyzer(Version.LUCENE_34));
		Query normalQuery = queryParser.parse("isDeleted:false");
		luceneQuery.add(normalQuery, Occur.MUST);
	}
	
	private String getQueryString(String keyword, String searchBy) {
		String queryString = "";
		
		for (int i = 0; i < searchFields.length; i++) {
			if(searchBy.equalsIgnoreCase(searchFields[i])) {
				queryString = "(" + searchFields[i] + ":*" + keyword + "*)";
				break;
			}
		}
		
		if (searchBy.isEmpty()) {
			for (int i = 0; i < searchFields.length; i++) {
				if (i == searchFields.length -1) {
					queryString = queryString + "(" + searchFields[i] + ":*" + keyword + "*)";
				} else {
					queryString = queryString + "(" + searchFields[i] + ":*" + keyword + "*)" + " OR ";
				}
				
			}
		}
		if(!queryString.isEmpty()){
			queryString = "( "+ queryString +" )";
		}
		return queryString;
	}
	
	/**
	 * this method is used to filter the result based on enable and disable
	 * @param queryString
	 * @param wineryType, AE or AD
	 * @return queryString
	 */
	private String filterTypeOfWinery(final String queryString,final String wineryType) {
		if (wineryType.equals("AE")) {
			return queryString + " AND " + "(" + getWineryFieldName(wineryType) + ":true)";
		} else if (wineryType.equals("AD")) {
			return queryString + " AND " + "(" + getWineryFieldName(wineryType) + ":false)";
		}
		return queryString;
	}
	

	/**
	 * this method is used to perform the basic search on winery
	 * @param winerySearchPO
	 * @return WinerySearchVO
	 */
	public WinerySearchVO normalSearch(final WinerySearchPO winerySearchPO) throws ParseException, UserSearchException {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		
		QueryParser queryParser = null;
		String wineryType = getWineryFieldName(winerySearchPO.getTypeOfWinery());
		
		String searchBy = props.get(winerySearchPO.getSearchBy()) == null ? "" : props.get(winerySearchPO.getSearchBy());
		
		if(searchBy.isEmpty())  {
			if (wineryType.isEmpty()) {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, searchFields, new StandardAnalyzer(Version.LUCENE_34));
			} else {
				List<String> winerySearchFields = new ArrayList<String>();
				for(String field : searchFields){
					winerySearchFields.add(field);
				}
				winerySearchFields.add(wineryType);
				String [] s = new String [searchFields.length];
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, (String[])winerySearchFields.toArray(s), new StandardAnalyzer(Version.LUCENE_34));
			}
			
		} else {
			for (int i = 0; i < searchFields.length; i++) {
				if(searchBy.equalsIgnoreCase(searchFields[i]))  {
					if (wineryType.isEmpty()) {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {searchFields[i]}, new StandardAnalyzer(Version.LUCENE_34));
					} else {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {searchFields[i], wineryType}, new StandardAnalyzer(Version.LUCENE_34));
					}
				}
			}
		}

		queryParser.setAllowLeadingWildcard(true);
		
		String keyword = SearchUtils.refineSearchKeyword(winerySearchPO.getKeyword());
		//fixed due to add option of searchBy on 17-07-2014
		String queryString = getQueryString(keyword, searchBy);
		
		queryString = filterTypeOfWinery(queryString, winerySearchPO.getTypeOfWinery());

		Query normalQuery = queryParser.parse(queryString);
		
		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(normalQuery, Occur.MUST);
		deletedQuery(luceneQuery);

		boolean orderDirect = Integer.parseInt(winerySearchPO.getSortOrder()) == 1 ? false : true;

		Sort sort = getSort(winerySearchPO.getSortBy(), orderDirect);
		
		SearchUtils.doExclusion(luceneQuery, winerySearchPO.getExclusions());

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, WineryModel.class, sort, Integer.parseInt(winerySearchPO.getOffSet()), Integer.parseInt(winerySearchPO.getLimit()));

		List<WineryModel> models = fullTextQuery.getResultList();
		int size = fullTextQuery.getResultSize();
		
		List<WineryModelVO> modelValues = new ArrayList<WineryModelVO>();

		for (WineryModel model : models) {
			WineryModelVO modelValue = new WineryModelVO(model); 
			modelValues.add(modelValue);
		}
		WinerySearchVO winerySearchVO= new WinerySearchVO(modelValues, winerySearchPO, getWineryCount(), size);
		return winerySearchVO;
	}
	
	public WineryAdvanceSearchVO advanceSearch(WineryAdvanceSearchPO winerySearchPO) throws ParseException, UserSearchException {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		BooleanQuery luceneQuery = new BooleanQuery();
		boolean isWinerySpecific = false;
		WineryAdvanceSearchCriteriaPO advanceSearchCriteria  = winerySearchPO.getAdvanceSearchCriteria();
		if (advanceSearchCriteria != null) {
			if (advanceSearchCriteria.getSearchCriterias() != null && !advanceSearchCriteria.getSearchCriterias().isEmpty()) {
				for (SearchCriteriaPO searchCriteriaPO : advanceSearchCriteria.getSearchCriterias()) {
					if (searchCriteriaPO.getSearchCriteriaId() == 1) {
						//String queryString =  "activeImporterById:*" +  ImporterRepository.getImporterById(Long.parseLong(searchCriteriaPO.getSearchCriteriaKeyword())).getImporterName() + "*";
					    	String queryString =  "activeImporterById:" +  searchCriteriaPO.getSearchCriteriaKeyword();
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"activeImporterById"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
						isWinerySpecific = true;
					}
					
					if (searchCriteriaPO.getSearchCriteriaId() == 2) {
						String searchCriteria = searchCriteriaPO.getSearchCriteriaKeyword().isEmpty() ? "*" : searchCriteriaPO.getSearchCriteriaKeyword();
						String queryString =  "region.id:" + searchCriteria;
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"region.id"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
					
					if (searchCriteriaPO.getSearchCriteriaId() == 3) {
						String searchCriteria =  "*" + searchCriteriaPO.getSearchCriteriaKeyword() + "*";
						String queryString =  "wineItems.wineName:" + searchCriteria;
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"wineItems.wineName"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
					
					if (searchCriteriaPO.getSearchCriteriaId() == 4) {
						String queryString =  "waContact.id:" + searchCriteriaPO.getSearchCriteriaKeyword() + "*";
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"waContact.id"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
				}
			} else {
				buildDefaultQuery(luceneQuery);
			}
			RangeSearchCriteriaPO<Integer> wineRange = advanceSearchCriteria.getWineRange();
			if (advanceSearchCriteria.getWineRange() != null && wineRange.getFromSearchCriteriaKeyword() != null && wineRange.getToSearchCriteriaKeyword() != null) {
				NumericRangeQuery<Integer> rangeQuery =  NumericRangeQuery.newIntRange("wineCount", wineRange.getFromSearchCriteriaKeyword() , wineRange.getToSearchCriteriaKeyword(), true, true);
				luceneQuery.add(rangeQuery, Occur.MUST);
			}
			
			RangeSearchCriteriaPO<Integer> activeWineRange = advanceSearchCriteria.getActiveWineRange();
			if (activeWineRange != null && activeWineRange.getFromSearchCriteriaKeyword() != null && activeWineRange.getToSearchCriteriaKeyword() != null) {
				NumericRangeQuery<Integer> rangeQuery =  NumericRangeQuery.newIntRange("activeWineCount", activeWineRange.getFromSearchCriteriaKeyword(), activeWineRange.getToSearchCriteriaKeyword(), true, true);
				luceneQuery.add(rangeQuery, Occur.MUST);
			}
			RangeSearchCriteriaPO<BigDecimal> revenueRange = advanceSearchCriteria.getRevenueRange();
			
			if (revenueRange != null && revenueRange.getFromSearchCriteriaKeyword() != null  && revenueRange.getToSearchCriteriaKeyword() != null) {
				NumericRangeQuery<Double> rangeQuery =  NumericRangeQuery.newDoubleRange("revenue", revenueRange.getFromSearchCriteriaKeyword().doubleValue() , revenueRange.getToSearchCriteriaKeyword().doubleValue(), true, true);
				luceneQuery.add(rangeQuery, Occur.MUST);
			}
			DateRangeCriteriaPO offerDateRange = advanceSearchCriteria.getOfferDateRange();
			
			if (offerDateRange != null && offerDateRange.getFromSearchCriteriaKeyword() != null && offerDateRange.getToSearchCriteriaKeyword() != null) {
				String startDate = DateTools.dateToString(offerDateRange.getFromSearchCriteriaKeyword(), DateTools.Resolution.MILLISECOND);
				String endDate = DateTools.dateToString(offerDateRange.getToSearchCriteriaKeyword(), DateTools.Resolution.MILLISECOND);
				TermRangeQuery rangeQuery = new TermRangeQuery("lastOfferDate", startDate, endDate, true, true);
				luceneQuery.add(rangeQuery,Occur.MUST);
			}
		} else {
			buildDefaultQuery(luceneQuery);
		}
		
		boolean orderDirect = Integer.parseInt(winerySearchPO.getSortOrder()) == 1 ? false : true;

		Sort sort = getSort(winerySearchPO.getSortBy(), orderDirect);
		
		deletedQuery(luceneQuery);
		
		SearchUtils.doExclusion(luceneQuery, winerySearchPO.getExclusions());
		

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery,  WineryModel.class, sort, Integer.parseInt(winerySearchPO.getOffSet()), Integer.parseInt(winerySearchPO.getLimit()));

		List<WineryModel> models = fullTextQuery.getResultList();
		int size = fullTextQuery.getResultSize();
		
		List<WineryModelVO> modelValues = new ArrayList<WineryModelVO>();

		for (WineryModel model : models) {
			WineryModelVO modelValue = new WineryModelVO(model);
			modelValues.add(modelValue);
		}
		
		return  new WineryAdvanceSearchVO(modelValues, winerySearchPO, getWineryCount(), size, isWinerySpecific ? size : 0);
	}
	
	
	public int getWineryCount() throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "id" }, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "id:" + "*" + " AND isDeleted:false";

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, WineryModel.class);

		int size = fullTextQuery.getResultSize();

		return size;
	}
	
	public void buildDefaultQuery(BooleanQuery luceneQuey) throws ParseException  {
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "wineryName" }, new StandardAnalyzer(Version.LUCENE_34));
		queryParser.setAllowLeadingWildcard(true);
		String queryString = "wineryName:" + "*";
		Query fieldQuery = queryParser.parse(queryString);
		luceneQuey.add(fieldQuery, Occur.MUST);
	}
	
	
	
	private Sort getSort(String sortBy, boolean orderDirect) {
		//String.CASE_INSENSITIVE_ORDER.compare(val1, val2);
		if (sortProp.get(sortBy).equals("id") || sortProp.get(sortBy).equals("lastOfferDate") ) {
			return new Sort(new SortField(sortProp.get("id"), SortField.LONG, orderDirect));
		}else if(sortProp.get(sortBy).equals("wineCount") || sortProp.get(sortBy).equals("activeWineCount") ){
			
			return new Sort(new SortField(sortProp.get(sortBy), SortField.INT, orderDirect));
		}else if (sortProp.get(sortBy).equals("revenue")) {
			//return new Sort(new SortField(sortProp.get(sortBy), SortField.DOUBLE, orderDirect));
			return new Sort(new SortField(sortProp.get("id"), SortField.LONG, orderDirect));
		} else {
			return new Sort(new SortField(sortProp.get(sortBy), new LuceneSortComparator(), orderDirect));
		}
	}
}
