package com.wineaccess.command.search.sampler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
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
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.lucene.comparator.LuceneSortComparator;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.util.SearchUtils;
/**
 * This class is used for basic and advance search of sampler
 * @author rohit.lohiya
 *
 * @param <T>
 */
public class SamplerSearchDAOImpl  <T extends Persistent> extends JPAGenericDAOImpl<T> implements SamplerSearchDAO<T> {
	
	@PersistenceContext
	EntityManager em;
	
	private static Log logger = LogFactory.getLog(SamplerSearchDAOImpl.class);
	
	String[] searchFields = new String[] {"id", "name", "wineName"};
	String[] sortFields = new String[] {"id", "nameSort", "wineNameSort", "activeOffer", "totalRevenue", "totalSrp", "lastOfferDate"};
	
	static Map <String, String> sortProp = new HashMap<String, String>();
	static Map <String, String> searchProp = new HashMap<String, String>();
	
	private Sort getSort(String sortBy, boolean orderDirect) {
		if (sortProp.get(sortBy).equals("id") || sortProp.get(sortBy).equals("activeOffer")) {
			return new Sort(new SortField(sortProp.get("id"), SortField.LONG, orderDirect));
		} else if (sortProp.get(sortBy).equals("totalRevenue") || sortProp.get(sortBy).equals("totalSrp")) {
			return new Sort(new SortField(sortProp.get(sortBy), SortField.DOUBLE, orderDirect));
		} else {
			return new Sort(new SortField(sortProp.get(sortBy), new LuceneSortComparator(), orderDirect));
		}
	}
	
	static {
		
		searchProp.put("id", "id");
		searchProp.put("name", "name");
		searchProp.put("wineName", "productSampler.wineName");
		
		
		sortProp.put("id", "id");
		sortProp.put("name", "nameSort"); 
		sortProp.put("wineName", "productSampler.wineNameSort");
		sortProp.put("activeOffer", "activeOffer");
		sortProp.put("totalRevenue", "totalRevenue");
		sortProp.put("totalSrp", "totalSrp");
		sortProp.put("lastOfferDate", "lastOfferDate");
	}
	
	private String getQueryString(String keyword, String searchBy) {
		String queryString = "";
		
		for (int i = 0; i < searchFields.length; i++) {
			if(searchBy.equalsIgnoreCase(searchFields[i])) {
				
				queryString = "(" + getActualSearchField(searchFields[i]) + ":*" + keyword + "*)";
				break;
			}
		}
		
		if (searchBy.isEmpty()) {
			for (int i = 0; i < searchFields.length; i++) {
				if (i == searchFields.length -1) {
					queryString = queryString + "(" + getActualSearchField(searchFields[i]) + ":*" + keyword + "*)";
				} else {
					queryString = queryString + "(" + getActualSearchField(searchFields[i]) + ":*" + keyword + "*)" + " OR ";
				}
				
			}
		}
		return queryString;
	}
	
	public String getActualSearchField(String key){
		return searchProp.get(key);
	}
	/**
	 * This is basic search for sampler
	 */
	@Override
	public SamplerSearchVO normalSearch(final SamplerSearchPO samplerSearchPO) throws ParseException, UserSearchException {
		
		logger.info("start of normalSearch method of sampler");
		
		try {
			FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
			
			QueryParser queryParser = null;
			
			String searchBy = samplerSearchPO.getSearchBy();
			
			if(searchBy.isEmpty())  {
				List<String> samplerSearchFields = new ArrayList<String>();
				for(String field : searchFields){
					samplerSearchFields.add(getActualSearchField(field));
				}
				String [] allSearchFields = new String [searchFields.length];
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, (String[])samplerSearchFields.toArray(allSearchFields), new StandardAnalyzer(Version.LUCENE_34));
			} else {
				for (int i = 0; i < searchFields.length; i++) {
					if(searchBy.equalsIgnoreCase(searchFields[i]))  {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {getActualSearchField(searchFields[i])}, new StandardAnalyzer(Version.LUCENE_34));
					}
				}
			}

			queryParser.setAllowLeadingWildcard(true);
			
			String keyword = SearchUtils.refineSearchKeyword(samplerSearchPO.getKeyword());
			//fixed due to add option of searchBy on 17-07-2014
			String queryString = getQueryString(keyword, searchBy);
			
			Query normalQuery = queryParser.parse(queryString);
			
			BooleanQuery luceneQuery = new BooleanQuery();
			luceneQuery.add(normalQuery, Occur.MUST);
			
			boolean orderDirect = Integer.parseInt(samplerSearchPO.getSortOrder()) == 1 ? false : true;

			Sort sort = getSort(samplerSearchPO.getSortBy(), orderDirect);
			
			SearchUtils.doExclusion(luceneQuery, samplerSearchPO.getExclusions());

			FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, SamplerModel.class, sort, Integer.parseInt(samplerSearchPO.getOffSet()), 
					Integer.parseInt(samplerSearchPO.getLimit()));

			List<SamplerModel> models = fullTextQuery.getResultList();
			int size = fullTextQuery.getResultSize();
			
			List<SamplerModelVO> modelValues = new ArrayList<SamplerModelVO>();

			for (SamplerModel model : models) {
				SamplerModelVO modelValue = new SamplerModelVO(model); 
				modelValues.add(modelValue);
			}
			SamplerSearchVO samplerSearchVO= new SamplerSearchVO(modelValues, samplerSearchPO, getSamplerCount(), size);
			
			logger.info("exit of normalSearch method of sampler");
			return samplerSearchVO;
			
		} catch (NumberFormatException e) {
			
			SamplerSearchVO samplerSearchVO = null;
			logger.error("Internal server occured while processing request " + e);
			return samplerSearchVO;
		}
	}
	
	public void buildDefaultQuery(BooleanQuery luceneQuey) throws ParseException  {
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "id" }, new StandardAnalyzer(Version.LUCENE_34));
		queryParser.setAllowLeadingWildcard(true);
		String queryString = "id:" + "*";
		Query fieldQuery = queryParser.parse(queryString);
		luceneQuey.add(fieldQuery, Occur.MUST);
	}
	
	/**
	 * This is advance search for sampler
	 */
	@Override
	public SamplerAdvSearchVO advanceSearch(final SamplerAdvSearchPO samplerSearchPO) throws ParseException, UserSearchException {
		
		logger.info("start of advanceSearch method of sampler");
		
		try {
			FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
			
			BooleanQuery luceneQuery = new BooleanQuery();
			
			SamplerAdaSearchCriteriaPO advanceSearchCriteria = samplerSearchPO.getAdvanceSearchCriteria();
			
			if (advanceSearchCriteria != null) {
				if (advanceSearchCriteria.getSearchCriterias() != null && !advanceSearchCriteria.getSearchCriterias().isEmpty()) {
					for (SearchCriteriaPO searchCriteriaPO : advanceSearchCriteria.getSearchCriterias()) {
						if (searchCriteriaPO.getSearchCriteriaId() == 1) {
							String queryString =  "name:*" + searchCriteriaPO.getSearchCriteriaKeyword() + "*";
							QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"name"} , new StandardAnalyzer(Version.LUCENE_34));
							queryParser.setAllowLeadingWildcard(true);
							Query fieldQuery = queryParser.parse(queryString);
							luceneQuery.add(fieldQuery, Occur.MUST);
						}
						
						if (searchCriteriaPO.getSearchCriteriaId() == 2) {
							String queryString =  "productSampler.wineName:*" + searchCriteriaPO.getSearchCriteriaKeyword() + "*";
							QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"productSampler.wineName"} , new StandardAnalyzer(Version.LUCENE_34));
							queryParser.setAllowLeadingWildcard(true);
							Query fieldQuery = queryParser.parse(queryString);
							luceneQuery.add(fieldQuery, Occur.MUST);
						}
					}
				} else {
					buildDefaultQuery(luceneQuery);
				}
				
				DateRangeCriteriaPO offerDateRange = advanceSearchCriteria.getLastOfferDateRange();
				if (offerDateRange != null && offerDateRange.getFromSearchCriteriaKeyword() != null && offerDateRange.getToSearchCriteriaKeyword() != null) {
					String startDate = DateTools.dateToString(offerDateRange.getFromSearchCriteriaKeyword(), DateTools.Resolution.MILLISECOND);
					String endDate = DateTools.dateToString(offerDateRange.getToSearchCriteriaKeyword(), DateTools.Resolution.MILLISECOND);
					TermRangeQuery rangeQuery = new TermRangeQuery("lastOfferDate", startDate, endDate, true, true);
					luceneQuery.add(rangeQuery,Occur.MUST);
				}
			} else {
				buildDefaultQuery(luceneQuery);
			}

			boolean orderDirect = Integer.parseInt(samplerSearchPO.getSortOrder()) == 1 ? false : true;

			Sort sort = getSort(samplerSearchPO.getSortBy(), orderDirect);
			
			SearchUtils.doExclusion(luceneQuery, samplerSearchPO.getExclusions());

			FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery,  SamplerModel.class, sort, 
					Integer.parseInt(samplerSearchPO.getOffSet()), Integer.parseInt(samplerSearchPO.getLimit()));

			List<SamplerModel> models = fullTextQuery.getResultList();
			int size = fullTextQuery.getResultSize();
			
			List<SamplerModelVO> modelValues = new ArrayList<SamplerModelVO>();

			for (SamplerModel model : models) {
				SamplerModelVO modelValue = new SamplerModelVO(model); 
				modelValues.add(modelValue);
			}
			SamplerAdvSearchVO samplerSearchVO= new SamplerAdvSearchVO(modelValues, samplerSearchPO, getSamplerCount(), size);
			logger.info("exit of advanceSearch method of sampler");
			return samplerSearchVO;
		} catch (NumberFormatException e) {
			
			SamplerAdvSearchVO samplerSearchVO = null;
			logger.error("Internal server occured while processing request " + e);
			return samplerSearchVO;
		}
	}
	
	public int getSamplerCount() throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "id" }, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "id:" + "*";

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, SamplerModel.class);

		int size = fullTextQuery.getResultSize();

		return size;
	}
}
