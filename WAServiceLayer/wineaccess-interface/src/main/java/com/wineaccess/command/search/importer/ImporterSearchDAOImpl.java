package com.wineaccess.command.search.importer;

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
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.lucene.comparator.LuceneSortComparator;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.util.SearchUtils;

public class ImporterSearchDAOImpl<T extends Persistent> extends JPAGenericDAOImpl<T> implements ImporterSearchDAO<T> {
	
	@PersistenceContext
	EntityManager em;
	static Map <String, String> props = new HashMap<String, String>();
	static Map <String, String> sortProp = new HashMap<String, String>();
	
	static String[] searchFields = null;
	static String[] sortFields = null;
	
	static {
		props.put("id", "id");
		props.put("importerName", "importerName");
		props.put("region", "countryId.countryCode");
		props.put("activeStatus", "isActive");
		//sortProp.put("activeStatus", "isActive");
		props.put("waContact", "waContact.name");
		props.put("winery", "wineryCount");
		props.put("wines", "wineCount");
		props.put("activeWines", "activeWineCount");
		props.put("totalRevenue", "revenue1");
		props.put("lastOfferDate", "lastOfferDate");
		
		searchFields = new String[props.size()];
		int i = 0;
		for(Map.Entry<String, String> params:props.entrySet()){
			searchFields[i] = params.getValue();
			i++;
		}
		
		
		sortProp.put("id", "id");
		sortProp.put("importerName", "importerNameSort");
		sortProp.put("region", "countryId.countryCodeSort");
		sortProp.put("waContact", "waContact.nameSort");
		sortProp.put("winery", "wineryCount");
		sortProp.put("wines", "wineCount");
		sortProp.put("activeWines", "activeWineCount");
		sortProp.put("totalRevenue", "revenue");
		sortProp.put("lastOfferDate", "lastOfferDate");
		sortProp.put("activeStatus", "isActive");
		sortFields = new String[sortProp.size()];
		i = 0;
		for(Map.Entry<String, String> params:sortProp.entrySet()){
			sortFields[i] = params.getValue();
			i++;
		}
		
		
	}
	
	
	
	
	public int getImporterCount() throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "id" }, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "id:" + "*" + " AND isDeleted:false";;

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, ImporterModel.class);

		int size = fullTextQuery.getResultSize();

		return size;
	}
	
	private String getQueryString(String keyword, String searchBy) {
		
		String queryString = "";
		
		if(!searchBy.isEmpty()){
			queryString = "(" + searchBy + ":*" + keyword + "*)";
			//break;
		}else{
			for(Map.Entry<String, String> params:props.entrySet()){
				if(!queryString.isEmpty()){
					queryString += " OR (" + params.getValue() + ":*" + keyword + "*)";
				}else{
					queryString += "((" + params.getValue() + ":*" + keyword + "*)";
				}
			}
			if(!queryString.isEmpty()){
				queryString += ")";
			}
		}
		
		
			
		return queryString;
	}
	
	private void deletedQuery(BooleanQuery luceneQuery) throws ParseException {
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"isDeleted"}, new StandardAnalyzer(Version.LUCENE_34));
		Query normalQuery = queryParser.parse("isDeleted:false");
		luceneQuery.add(normalQuery, Occur.MUST);
	}
	
	private String filterTypeOfImporter(String queryString, String importerType) {
		if (importerType.equals("AA")) {
			return queryString + " AND " + "(isActive:true)";
		} else if (importerType.equals("AI")) {
			return queryString + " AND " + "(isActive:false)";
		} else if (importerType.equals("AE")) {
			return queryString + " AND " + "(isEnabled:true)";
		} else if (importerType.equals("AD")) {
			return queryString + " AND " + "(isEnabled:false)";
		}
		return queryString;
	}
	
	public String getImporterFieldName(String importerType) {
		if (importerType.equals("AA") || importerType.equals("AI")) {
			return "isActive";
		} else if (importerType.equals("AE") || importerType.equals("AD")) {
			return "isEnabled"; 
		} 
		return "";
	}
	
	public ImporterSearchVO normalSearch(ImporterSearchPO importerPO) throws ParseException, UserSearchException {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		
		QueryParser queryParser = null;
		String importerType = getImporterFieldName(importerPO.getTypeOfImporter());
		
		String searchBy = props.get(importerPO.getSearchBy()) == null ? "" : props.get(importerPO.getSearchBy());
		
		if(searchBy.equalsIgnoreCase(""))  {
			if (importerType.isEmpty()) {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, searchFields, new StandardAnalyzer(Version.LUCENE_34));
			} else {
				List<String> importerSearchFields = new ArrayList<String>();
				for(Map.Entry<String, String> params:props.entrySet()){
					importerSearchFields.add(params.getValue());
				}
				importerSearchFields.add(importerType);
						String[] importerSearchField = new String[importerSearchFields.size()]; 
						int index = 0;
								for(String field : importerSearchFields){
									importerSearchField[index]=field;
									index++;
								}
							//	(String[]) importerSearchFields.toArray();
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, importerSearchField, new StandardAnalyzer(Version.LUCENE_34));
	
			}
			
		} else {
			for(Map.Entry<String, String> params:props.entrySet()){
				if(searchBy.equalsIgnoreCase(params.getValue()))  {
					if (importerType.isEmpty()) {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {params.getValue()}, new StandardAnalyzer(Version.LUCENE_34));
					} else {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {params.getValue(), importerType}, new StandardAnalyzer(Version.LUCENE_34));
					}
				}
			}
		}
		
		queryParser.setAllowLeadingWildcard(true);
		String keyword = SearchUtils.refineSearchKeyword(importerPO.getKeyword());
		//fixed due to add option of searchBy on 17-07-2014
		String queryString = getQueryString(keyword, searchBy);
		
		queryString = filterTypeOfImporter(queryString, importerPO.getTypeOfImporter());

		Query queryNormal = queryParser.parse(queryString);

		boolean orderDirect = Integer.parseInt(importerPO.getSortOrder()) == 1 ? false : true;

		Sort sort = getSort(importerPO.getSortBy(), orderDirect);
		
		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(queryNormal, Occur.MUST);
		deletedQuery(luceneQuery);
		
		SearchUtils.doExclusion(luceneQuery, importerPO.getExclusions());

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, ImporterModel.class, sort, Integer.parseInt(importerPO.getOffSet()), Integer.parseInt(importerPO.getLimit()));

		List<ImporterModel> models = fullTextQuery.getResultList();
		
		int size = fullTextQuery.getResultSize();
		
		List<ImporterModelVO> modelValues = new ArrayList<ImporterModelVO>();

		for (ImporterModel model : models) {
			ImporterModelVO modelValue = new ImporterModelVO(model);
			modelValues.add(modelValue);
		}
		keyword = keyword.replace("%20", " ");
		
		ImporterSearchCriteriaVO i = new ImporterSearchCriteriaVO(importerPO, getImporterCount(), size);
		
		ImporterSearchVO importerSearchVO= new ImporterSearchVO(i, modelValues);
		return importerSearchVO;
	}
	
	public ImporterAdvanceSearchVO normalSearch(ImporterAdvanceSearchPO importerPO) throws ParseException, UserSearchException {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		BooleanQuery luceneQuery = new BooleanQuery();
		
		boolean isWinerySpecific = false;
		
		ImporterSearchCriteriaPO advanceSearchCriteria  = importerPO.getAdvanceSearchCriteria();
		if (advanceSearchCriteria != null) { 
			if ( advanceSearchCriteria.getSearchCriterias() != null) {
				if (advanceSearchCriteria.getSearchCriterias() != null && !advanceSearchCriteria.getSearchCriterias().isEmpty()) {
					for (SearchCriteriaPO searchCriteriaPO : advanceSearchCriteria.getSearchCriterias()) {
						if (searchCriteriaPO.getSearchCriteriaId() == 1) {
							String criteria = searchCriteriaPO.getSearchCriteriaKeyword().isEmpty() ? "*" : searchCriteriaPO.getSearchCriteriaKeyword();
							String queryString =  "countryId.id:" + criteria;
							QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"countryId.id"} , new StandardAnalyzer(Version.LUCENE_34));
							queryParser.setAllowLeadingWildcard(true);
							Query fieldQuery = queryParser.parse(queryString);
							luceneQuery.add(fieldQuery, Occur.MUST);
						}
						
						if (searchCriteriaPO.getSearchCriteriaId() == 2) {
						    	isWinerySpecific = true;
							String criteria = searchCriteriaPO.getSearchCriteriaKeyword().isEmpty() ? "*" : searchCriteriaPO.getSearchCriteriaKeyword();
							String queryString =  "wineries.id:" + criteria;
							QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"wineries.id"} , new StandardAnalyzer(Version.LUCENE_34));
							queryParser.setAllowLeadingWildcard(true);
							Query fieldQuery = queryParser.parse(queryString);
							luceneQuery.add(fieldQuery, Occur.MUST);
						}
						
						if (searchCriteriaPO.getSearchCriteriaId() == 3) {
							//wine NAme
						}
						
						if (searchCriteriaPO.getSearchCriteriaId() == 4) {
							String criteria = searchCriteriaPO.getSearchCriteriaKeyword().isEmpty() ? "*" : searchCriteriaPO.getSearchCriteriaKeyword();
							String queryString =  "waContact.id:" + criteria;
							QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"waContact.id"} , new StandardAnalyzer(Version.LUCENE_34));
							queryParser.setAllowLeadingWildcard(true);
							Query fieldQuery = queryParser.parse(queryString);
							luceneQuery.add(fieldQuery, Occur.MUST);
						}
					}
				} else {
					buildDefaultQuery(luceneQuery);
				}
			} else {
				buildDefaultQuery(luceneQuery);
			}
			
			RangeSearchCriteriaPO<String> wineRange = advanceSearchCriteria.getWineRange();
			if (advanceSearchCriteria.getWineRange() != null && wineRange.getFromSearchCriteriaKeyword() != null && wineRange.getToSearchCriteriaKeyword() != null 
					&& !wineRange.getFromSearchCriteriaKeyword().isEmpty() && !wineRange.getToSearchCriteriaKeyword().isEmpty()) {
				NumericRangeQuery<Integer> rangeQuery =  NumericRangeQuery.newIntRange("wineCount", Integer.parseInt(wineRange.getFromSearchCriteriaKeyword()) , 
						Integer.parseInt(wineRange.getToSearchCriteriaKeyword()), true, true);
				luceneQuery.add(rangeQuery, Occur.MUST);
			}
			
			RangeSearchCriteriaPO<String> activeWineRange = advanceSearchCriteria.getActiveWineRange();
			if (activeWineRange != null && activeWineRange.getFromSearchCriteriaKeyword() != null && activeWineRange.getToSearchCriteriaKeyword() != null
					&& !activeWineRange.getFromSearchCriteriaKeyword().isEmpty() && !activeWineRange.getToSearchCriteriaKeyword().isEmpty()) {
				NumericRangeQuery<Integer> rangeQuery =  NumericRangeQuery.newIntRange("activeWineCount", Integer.parseInt(activeWineRange.getFromSearchCriteriaKeyword()), 
						Integer.parseInt(activeWineRange.getToSearchCriteriaKeyword()), true, true);
				luceneQuery.add(rangeQuery, Occur.MUST);
			}
			
			RangeSearchCriteriaPO<String> revenueRange = advanceSearchCriteria.getRevenueRange();
			
			if (revenueRange != null && revenueRange.getFromSearchCriteriaKeyword() != null  && revenueRange.getToSearchCriteriaKeyword() != null
					&& !revenueRange.getFromSearchCriteriaKeyword().isEmpty() && !revenueRange.getToSearchCriteriaKeyword().isEmpty()) {
				NumericRangeQuery<Double> rangeQuery =  NumericRangeQuery.newDoubleRange("revenue", Double.parseDouble(revenueRange.getFromSearchCriteriaKeyword()) ,  Double.parseDouble(
						revenueRange.getToSearchCriteriaKeyword()), true, true);
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
		
		boolean orderDirect = Integer.parseInt(importerPO.getSortOrder()) == 1 ? false : true;

		Sort sort = getSort(importerPO.getSortBy(), orderDirect);
		
		deletedQuery(luceneQuery);
		SearchUtils.doExclusion(luceneQuery, importerPO.getExclusions());

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, ImporterModel.class, sort, Integer.parseInt(importerPO.getOffSet()), Integer.parseInt(importerPO.getLimit()));
		
		List<ImporterModel> models = fullTextQuery.getResultList();
		int size = fullTextQuery.getResultSize();
		
		List<ImporterModelVO> modelValues = new ArrayList<ImporterModelVO>();

		for (ImporterModel model : models) {
			ImporterModelVO modelValue = new ImporterModelVO(model);
			modelValues.add(modelValue);
		}
		
		if (isWinerySpecific) {
		    
		}
		
		ImporterAdvanceSearchVO importerSearchVO = new ImporterAdvanceSearchVO(modelValues, importerPO, getImporterCount(), size, isWinerySpecific ? size : 0);
		return importerSearchVO;
	}
	
	public void buildDefaultQuery(BooleanQuery luceneQuey) throws ParseException  {
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "id" }, new StandardAnalyzer(Version.LUCENE_34));
		queryParser.setAllowLeadingWildcard(true);
		String queryString = "id:" + "*";
		Query fieldQuery = queryParser.parse(queryString);
		luceneQuey.add(fieldQuery, Occur.MUST);
	}
	
	
	
	private Sort getSort(String sortBy, boolean orderDirect) {
		if (sortProp.get(sortBy).equals("id") || sortProp.get(sortBy).equals("revenue") || sortProp.get(sortBy).equals("lastOfferDate")) {
			return new Sort(new SortField(sortProp.get("id"), SortField.LONG, orderDirect));
		}else if(sortProp.get(sortBy).equals("wineryCount")){
			
			return new Sort(new SortField(sortProp.get(sortBy), SortField.LONG, orderDirect));
		} else if(sortProp.get(sortBy).equals("wineCount") || sortProp.get(sortBy).equals("activeWineCount")){
			
			return new Sort(new SortField(sortProp.get(sortBy), SortField.INT, orderDirect));
		}
		
		else if (sortProp.get(sortBy).equals("revenue")) {
			return new Sort(new SortField(sortProp.get(sortBy), SortField.DOUBLE, orderDirect));
		} else if (sortProp.get(sortBy).equals("isActive")) {
			return new Sort(new SortField(sortProp.get(sortBy), SortField.STRING, !orderDirect));
		}else {
			return new Sort(new SortField(sortProp.get(sortBy), new LuceneSortComparator(), orderDirect));
		}
	}
}
