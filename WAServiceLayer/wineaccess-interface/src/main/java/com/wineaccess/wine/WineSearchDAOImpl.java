package com.wineaccess.wine;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
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
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.lucene.comparator.LuceneSortComparator;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.util.SearchExclusionPO;
import com.wineaccess.util.SearchUtils;

public class WineSearchDAOImpl <T extends Persistent> extends JPAGenericDAOImpl<T> implements WineSearchDAO<T> {

	@PersistenceContext
	EntityManager em;

	//String[] searchFields = new String[] { "id", "wineName", "vintage.name", "wineryId.wineryName", "wineType.name", "wineryId.activeImporterId","verietal.name"};
	
	/*String[] searchFields = new String[] {"id", "wineryId.wineryName", "wineName", "wineryId.activeImporterId", "wineryId.activeImporterId.countryId.countryName",
			"vintage.name", "wineType.name", "verietal.name","totalRevenue", "lastOfferDate", "activeOffer", "userReview", "userRatings", 
			"expertScore", "expertRating"};*/
	

	
	
	
	String[] searchFields = new String[] {"product.id","wineryId.wineryName" ,"wineName", "vintage.name", "wineryId.wineryName", "wineType.name", "importerName","verietal.name", "region","totalRevenue","lastOfferDate","activeOffer", "userReview", "userRatings","expertScore","expertRating"};
	
	public static String[] sortFields = new String[] {"product.id", "wineNameSort","wineryId.wineryNameSort", "vintage.nameSort", "verietal.nameSort", "wineType.nameSort",
			"importerNameSort", "region", "totalRevenue", "lastOfferDate", "activeOffer", "userReview",
			"userRatings", "expertScore", "expertRating"};

	
	public static String getRegExSort() {
		Set<String> keys =	sortProp.keySet();
		StringBuffer buffer = new StringBuffer();
		int counter = 0;
		for (String key :keys ) {	 
			counter++;
			if (keys.size() == counter) {
				buffer.append(key).append("|");
			} else {
				buffer.append(key).append("|");
			}

		}
		return buffer.toString();
		
	}

	static Map <String, String> props = new HashMap<String, String>();
	static Map <String, String> sortProp = new HashMap<String, String>();
	static {
		props.put("id", "product.id");
		//props.put("wineId", "id");
		props.put("wineryName", "wineryId.wineryName");
		props.put("wineName", "wineName");
		props.put("importerName", "importerName");
		props.put("region", "region");
		props.put("vintage", "vintage.name");
		props.put("wineStyle", "wineType.name");
		props.put("varietal", "verietal.name");
		
		props.put("totalRevenue", "totalRevenue");
		props.put("lastOfferDate", "lastOfferDate");
		
		props.put("activeOffers", "activeOffer");
		props.put("userReviews", "userReview");
		props.put("userRatings", "userRatings");
		props.put("expertScore", "expertScore");
		props.put("expertRating", "expertRating");
		
		/** Sort Map */
		
		sortProp.put("id", "product.id");
		//sortProp.put("wineId", "id");
		sortProp.put("wineryName", "wineryId.wineryNameSort");
		sortProp.put("wineName", "wineNameSort");
		sortProp.put("importerName", "importerNameSort");
		sortProp.put("region", "region");
		sortProp.put("vintage", "vintage.nameSort");
		sortProp.put("wineStyle", "wineType.nameSort");
		sortProp.put("varietal", "verietal.nameSort");
		
		sortProp.put("totalRevenue", "totalRevenue");
		sortProp.put("lastOfferDate", "lastOfferDate");
		
		sortProp.put("activeOffers", "activeOffer");
		sortProp.put("userReviews", "userReview");
		sortProp.put("userRatings", "userRatings");
		sortProp.put("expertScore", "expertScore");
		sortProp.put("expertRating", "expertRating");
	}
	
	/**
	 *  Code abbreviations: 
	 *  "AAP" - ALL APPROVED
	 *	"ADA" - ALL DISAPPROVED
	 * 	"AEN" - ALL ENABLED
	 *	"ADS" - ALL DISABLED
	 * 	"AAC" - ALL ACTIVE
	 *	"AIN" - ALL INACTIVE 
	 **/
	public String getWineFieldName(String wineType) {
		if (wineType.equals("AE") || wineType.equals("AD")) {
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
				queryString ="(";
				queryString += "(" + searchFields[i] + ":*" + keyword + "*)";
				break;
			}
		}
		if (searchBy.isEmpty()) {
			queryString = "((";
			for (int i = 0; i < searchFields.length; i++) {
				if (i == searchFields.length -1) {
					queryString = queryString + "(" + searchFields[i] + ":*" + keyword + "*)";
				} else {
					queryString = queryString + "(" + searchFields[i] + ":*" + keyword + "*)" + " OR ";
				}
				
			}
			queryString += ")";
		}
		if(queryString != null){
			queryString = queryString + " AND (isDeleted:false))";
		}
		return queryString;
	}
	
	

	private String filterTypeOfWine(String queryString, String wineType) {
		if (wineType.equals("AE")) {
			return queryString + " AND " + "(" + getWineFieldName(wineType) + ":true)";
		} else if (wineType.equals("AD")) {
			return queryString + " AND " + "(" + getWineFieldName(wineType) + ":false)";
		} 
		return queryString;
	}


	public WineBasicSearchVO normalSearch(WineBasicSearchPO wineBasicSearchPO) throws ParseException, UserSearchException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);


		QueryParser queryParser = null;
		String wineType = getWineFieldName(wineBasicSearchPO.getTypeOfWine());
		
		String searchBy = props.get(wineBasicSearchPO.getSearchBy()) == null ? "" :  props.get(wineBasicSearchPO.getSearchBy());
		
		if(searchBy.equalsIgnoreCase(""))  {
			if (wineType.isEmpty()) {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, searchFields, new StandardAnalyzer(Version.LUCENE_34));
			} else {
				List<String> wineSearchFields = new ArrayList<String>();
				for(String field : searchFields){
					wineSearchFields.add(field);
				}
				wineSearchFields.add(wineType);
				String [] s = new String [wineSearchFields.size()];
				//queryParser = new MultiFieldQueryParser(Version.LUCENE_34, (String[])winerySearchFields.toArray(s), new StandardAnalyzer(Version.LUCENE_34));
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, (String[])wineSearchFields.toArray(s), new StandardAnalyzer(Version.LUCENE_34));
			}
		} else {
			for (int i = 0; i < searchFields.length; i++) {
				if(searchBy.equalsIgnoreCase(searchFields[i]))  {
					if (wineType.isEmpty()) {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {searchFields[i]}, new StandardAnalyzer(Version.LUCENE_34));
					} else {
						queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {searchFields[i], wineType}, new StandardAnalyzer(Version.LUCENE_34));
					}
				}
			}
		}
		
		queryParser.setAllowLeadingWildcard(true);
		
		String keyword = SearchUtils.refineSearchKeyword(wineBasicSearchPO.getKeyword());
		//fixed due to add option of searchBy on 17-07-2014
		String queryString = getQueryString(keyword, searchBy);

		queryString = filterTypeOfWine(queryString, wineBasicSearchPO.getTypeOfWine());

		Query queryNormal = queryParser.parse(queryString);

		boolean orderDirect = Integer.parseInt(wineBasicSearchPO.getSortOrder()) == 1 ? false : true;

		Sort sort = getSort(wineBasicSearchPO.getSortBy(), orderDirect);

		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(queryNormal, Occur.MUST);
		deletedQuery(luceneQuery);
		
		//exclusion based on product id	
		List<SearchExclusionPO> productExclisionIds = wineBasicSearchPO.getExclusions();
		List<SearchExclusionPO> wineExclisionIds = new ArrayList<SearchExclusionPO>();
		
		List<Long> productIds = new ArrayList<Long>();
		for(SearchExclusionPO searchExclusionPO : productExclisionIds)
		{
			productIds.add(searchExclusionPO.getId());
		}
		
		List<Long> wineIds = new ArrayList<Long>();
		if(productIds != null && productIds.size() > 0)	
		{
			wineIds = ProductItemRepository.getWineIds(productIds, null);
		}
		for(Long wineId : wineIds)
		{
			SearchExclusionPO exclusionId = new SearchExclusionPO();
			exclusionId.setId(wineId);
			wineExclisionIds.add(exclusionId);
		}					
		
		SearchUtils.doExclusion(luceneQuery,wineExclisionIds);
		
		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, WineModel.class, sort, Integer.parseInt(wineBasicSearchPO.getOffSet()), Integer.parseInt(wineBasicSearchPO.getLimit()));

		List<WineModel> models = fullTextQuery.getResultList();
		int size = fullTextQuery.getResultSize();

		List<WineSearchVO> modelValues = new ArrayList<WineSearchVO>();

		for (WineModel model : models) {
			WineSearchVO modelValue = new WineSearchVO(model); 
			modelValues.add(modelValue);
		}
		WineBasicSearchVO wineSearchVO= new WineBasicSearchVO(getWineCount(), size, modelValues, wineBasicSearchPO);
		return wineSearchVO;
	}

	public WineAdvanceSearchVO advanceSearch(WineAdvanceSearchPO wineSearchPO) throws ParseException, UserSearchException {
	    
	    String wineryId = null;
	    String importerId = null;

			FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

			BooleanQuery luceneQuery = new BooleanQuery();

			WineAdvanceSearchCriteriaPO advanceSearchCriteria  = wineSearchPO.getAdvanceSearchCriteria();
			if (advanceSearchCriteria != null) {
				for (SearchCriteriaPO searchCriteriaPO : advanceSearchCriteria.getSearchCriterias()) {
					
					if (searchCriteriaPO.getSearchCriteriaId() == 1) {
					    	wineryId = searchCriteriaPO.getSearchCriteriaKeyword();
						String queryString =  "wineryId.id:" + searchCriteriaPO.getSearchCriteriaKeyword();
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"wineryId.id"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
					
					if (searchCriteriaPO.getSearchCriteriaId() == 2) {
						String queryString =  "wineName:*" + searchCriteriaPO.getSearchCriteriaKeyword() + "*";
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"wineName"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}

					if (searchCriteriaPO.getSearchCriteriaId() == 3) {
						String queryString =  "vintage.id:" + searchCriteriaPO.getSearchCriteriaKeyword();
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"vintage.id"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
					
					if (searchCriteriaPO.getSearchCriteriaId() == 4) {
						String queryString =  "verietal.id:" + searchCriteriaPO.getSearchCriteriaKeyword();
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"verietal.id"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
					
					if (searchCriteriaPO.getSearchCriteriaId() == 5) {
						String queryString =  "importerIdentifier:" + searchCriteriaPO.getSearchCriteriaKeyword();
						importerId = searchCriteriaPO.getSearchCriteriaKeyword();
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"importerIdentifier"} , new StandardAnalyzer(Version.LUCENE_34));
						queryParser.setAllowLeadingWildcard(true);
						Query fieldQuery = queryParser.parse(queryString);
						luceneQuery.add(fieldQuery, Occur.MUST);
					}
				}

				RangeSearchCriteriaPO<Float> userRatingRange = advanceSearchCriteria.getUserRatingRange();
				if (advanceSearchCriteria.getUserRatingRange() != null && userRatingRange.getFromSearchCriteriaKeyword() != null && userRatingRange.getToSearchCriteriaKeyword() != null) {
					NumericRangeQuery<Float> rangeQuery =  NumericRangeQuery.newFloatRange("userRatings", userRatingRange.getFromSearchCriteriaKeyword() , userRatingRange.getToSearchCriteriaKeyword(), true, true);
					luceneQuery.add(rangeQuery, Occur.SHOULD);
				}

				RangeSearchCriteriaPO<BigDecimal> activeRevenueRange = advanceSearchCriteria.getRevenueRange();
				if (activeRevenueRange != null && activeRevenueRange.getFromSearchCriteriaKeyword() != null && activeRevenueRange.getToSearchCriteriaKeyword() != null) {
					NumericRangeQuery<Double> rangeQuery =  NumericRangeQuery.newDoubleRange("totalRevenue", activeRevenueRange.getFromSearchCriteriaKeyword().doubleValue(), activeRevenueRange.getToSearchCriteriaKeyword().doubleValue(), true, true);
					luceneQuery.add(rangeQuery, Occur.SHOULD);
				}
			
				DateRangeCriteriaPO offerDateRange = advanceSearchCriteria.getOfferDateRange();
				if (offerDateRange != null && offerDateRange.getFromSearchCriteriaKeyword() != null && offerDateRange.getToSearchCriteriaKeyword() != null) {
					String startDate = DateTools.dateToString(offerDateRange.getFromSearchCriteriaKeyword(), DateTools.Resolution.MILLISECOND);
					String endDate = DateTools.dateToString(offerDateRange.getToSearchCriteriaKeyword(), DateTools.Resolution.MILLISECOND);
					TermRangeQuery rangeQuery = new TermRangeQuery("lastOfferDate", startDate, endDate, true, true);
					luceneQuery.add(rangeQuery,Occur.MUST);
				}
			}

			boolean orderDirect = Integer.parseInt(wineSearchPO.getSortOrder()) == 1 ? false : true;

			Sort sort = getSort(wineSearchPO.getSortBy(), orderDirect);
			
			deletedQuery(luceneQuery);
			
			//exclusion based on product id	
			List<SearchExclusionPO> productExclisionIds = wineSearchPO.getExclusions();
			List<SearchExclusionPO> wineExclisionIds = new ArrayList<SearchExclusionPO>();
			
			List<Long> productIds = new ArrayList<Long>();
			for(SearchExclusionPO searchExclusionPO : productExclisionIds)
			{
				productIds.add(searchExclusionPO.getId());
			}
			
			List<Long> wineIds = new ArrayList<Long>();
			if(productIds != null && productIds.size() > 0)	
			{
				wineIds = ProductItemRepository.getWineIds(productIds, null);
			}
			for(Long wineId : wineIds)
			{
				SearchExclusionPO exclusionId = new SearchExclusionPO();
				exclusionId.setId(wineId);
				wineExclisionIds.add(exclusionId);
			}					
			
			
			SearchUtils.doExclusion(luceneQuery, wineExclisionIds);
			
			FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery,  WineModel.class, sort, Integer.parseInt(wineSearchPO.getOffSet()), Integer.parseInt(wineSearchPO.getLimit()));

			List<WineModel> models = fullTextQuery.getResultList();
			int size = fullTextQuery.getResultSize();

			List<WineSearchVO> modelValues = new ArrayList<WineSearchVO>();

			for (WineModel model : models) {
				WineSearchVO modelValue = new WineSearchVO(model);
				modelValues.add(modelValue);
			}
                    int wineCount = 0;
                    if(wineryId==null && importerId == null)
                	wineCount = 0;
                    else if(wineryId!=null)
                	wineCount = getWineCount(wineryId,"W");
                    else if(importerId!=null)
                	wineCount = getWineCount(importerId,"I");
			return  new WineAdvanceSearchVO(getWineCount(), size,  wineCount, wineSearchPO, modelValues);
		}
	
	
	public int getWineCount(String wineryId, String type) throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		String toBeReplacedString = "";
		if(StringUtils.equalsIgnoreCase(type, "W"))
		    toBeReplacedString = "wineryId.id";
		else if(StringUtils.equalsIgnoreCase(type, "I"))
		    toBeReplacedString = "importerIdentifier";
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] {toBeReplacedString}, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = toBeReplacedString+":" + wineryId  + " AND isDeleted:false";

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, WineModel.class);

		int size = fullTextQuery.getResultSize();

		return size;
	}
	
	


	public int getWineCount() throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String[] { "wineName" }, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "wineName:" + "*"  + " AND isDeleted:false";

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, WineModel.class);

		int size = fullTextQuery.getResultSize();

		return size;
	}



	private Sort getSort(String sortBy, boolean orderDirect) {
		if(sortBy.isEmpty())
		{
			sortBy = "id";
		}
		
	
		if (sortProp.get(sortBy).equals("product.id")|| sortProp.get(sortBy).equals("userRatings") || sortProp.get(sortBy).equals("expertScore") || sortProp.get(sortBy).equals("expertRating")||sortProp.get(sortBy).equals("activeOffer") || sortProp.get(sortBy).equals("userReview") || sortProp.get(sortBy).equals("totalRevenue") || sortProp.get(sortBy).equals("lastOfferDate")) {
			return new Sort(new SortField(sortProp.get("id"), SortField.LONG, orderDirect));
		} /*else if (sortProp.get(sortBy).equals("totalRevenue")) {
			return new Sort(new SortField(sortProp.get(sortBy), SortField.DOUBLE, orderDirect));
		} else if (sortProp.get(sortBy).equals("userRatings")) {
			return new Sort(new SortField(sortProp.get(sortBy), SortField.FLOAT, orderDirect));
		}*/ else {
			return new Sort(new SortField(sortProp.get(sortBy), new LuceneSortComparator(), orderDirect));
		}
	}
	
	
}

