package com.wineaccess.commad.search.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
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

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.lucene.comparator.LuceneSortComparator;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.util.SearchExclusionPO;
import com.wineaccess.util.SearchUtils;

/**
 * @author jyoti.yadav@globallogic.com
 * @param <T>
 */
public class UserDAOImpl<T extends Persistent> extends JPAGenericDAOImpl<T>
		implements UserDAO<T> {

	@PersistenceContext
	EntityManager em;
	
	String[] searchFields = new String[] { "firstName", "lastName", "email", "id", "isDeleted"};

	public int getUserCount(List<SearchExclusionPO> exclusions) throws ParseException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34,
				new String[] { "firstName" }, new StandardAnalyzer(
						Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "firstName:" + "*" + " AND isDeleted:false";

		Query luceneQuery = queryParser.parse(queryString);

		FullTextQuery fullTextQuery = fullTextEntityManager
				.createFullTextQuery(luceneQuery, UserModel.class);

		int size = fullTextQuery.getResultSize();
		if(!CollectionUtils.isEmpty(exclusions)){
			size -= exclusions.size();
		}

		return size;
	}
	
	
	private Sort getSort(String sortBy, boolean orderDirect) {
		if (sortBy.equals("id")) {
			return new Sort(new SortField("id", SortField.LONG, orderDirect));
		} else if (sortBy.equals("firstName")) {
			return new Sort(new SortField("firstName", new LuceneSortComparator(), orderDirect));
		} else if (sortBy.equals("lastName")) {
			return new Sort(new SortField("lastName", new LuceneSortComparator(), orderDirect));
		} else if (sortBy.equals("email")) {
			return new Sort(new SortField("email", new LuceneSortComparator(), orderDirect));
		} else if (sortBy.equals("stateCode")) {
			return new Sort(new SortField("stateModel.stateNameSort", new LuceneSortComparator(), orderDirect));
		} else {
			return new Sort(new SortField("id", SortField.LONG, orderDirect));
		}
	}


	private String getQueryString(String keyword, String searchBy) {
		String queryString = null;
		if(searchBy != null){
			if(searchBy.equalsIgnoreCase("firstName")) {
				queryString = "(firstName:*" + keyword + "*)";
			} else if(searchBy.equalsIgnoreCase("lastName")) {
				queryString = "(lastName:*" + keyword + "*)";
			} else if(searchBy.equalsIgnoreCase("email")) {
				queryString = "(email:*" + keyword + "*)";
			} else if(searchBy.equalsIgnoreCase("id")) {
				queryString = "(id:*" + keyword + "*)";
			} else {
				queryString = "(firstName:*" + keyword + "* OR lastName:*" + keyword + "* OR email:*" + keyword + "* OR id:*" + keyword + "*)";
			}	
		}else{
			queryString = "(firstName:*" + keyword + "* OR lastName:*" + keyword + "* OR email:*" + keyword + "* OR id:*" + keyword + "*)";
		}
			
		
		queryString = queryString + " AND (isDeleted:false)";
		return queryString;
	}

	@Override
	public UserSearchVO getNormalSearchUserModel(String keyword, int offSet,
			int limit, String sortBy, int order, String searchBy, String additonalFilter, List<SearchExclusionPO> exclusions)
			throws ParseException, UserSearchException {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		
		QueryParser queryParser = null;
		if(null != searchBy){
			if(searchBy.equalsIgnoreCase("firstName"))  {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"firstName", "isDeleted"}, new StandardAnalyzer(Version.LUCENE_34));
			} else if(searchBy.equalsIgnoreCase("lastName")) {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"lastName", "isDeleted"}, new StandardAnalyzer(Version.LUCENE_34));
			} else if(searchBy.equalsIgnoreCase("email")) {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"email", "isDeleted"}, new StandardAnalyzer(Version.LUCENE_34));
			} else if(searchBy.equalsIgnoreCase("id")) {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"id", "isDeleted"}, new StandardAnalyzer(Version.LUCENE_34));
			} else {
				queryParser = new MultiFieldQueryParser(Version.LUCENE_34, searchFields, new StandardAnalyzer(Version.LUCENE_34));
			}	
		} else {
			queryParser = new MultiFieldQueryParser(Version.LUCENE_34, searchFields, new StandardAnalyzer(Version.LUCENE_34));
		}
		
		
		queryParser.setAllowLeadingWildcard(true);
		
		keyword = SearchUtils.refineSearchKeyword(keyword);
		
		//fixed due to add option of searchBy on 17-07-2014
		String queryString = getQueryString(keyword, searchBy);
		Query luceneQueryNormal = queryParser.parse(queryString);
		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(luceneQueryNormal, Occur.MUST);
		boolean orderDirect = order == 1 ? false : true;

		Sort sort = getSort(sortBy, orderDirect);

		UserSearchVO userSearchVO = null;
		
		SearchUtils.doExclusion(luceneQuery, exclusions);
		
		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, luceneQuery, UserModel.class, sort, offSet, limit);
		
		int size = fullTextQuery.getResultSize();

		List<UserModel> models = fullTextQuery.getResultList();
		
		if(additonalFilter != null && !additonalFilter.equals("")){
			queryString="";
			queryString = queryString
					 + "userType:"
					+ additonalFilter + "*";
			luceneQuery.add(queryParser.parse(queryString),Occur.MUST);
		}

		List<UserModelVO> modelValues = new ArrayList<UserModelVO>();

		for (UserModel model : models) {
			UserModelVO modelValue = new UserModelVO();
			try {
				BeanUtils.copyProperties(modelValue, model);
				if (model.getStateModel() != null){
					modelValue.setStateCode(model.getStateModel().getStateCode());
					modelValue.setStateName(model.getStateModel().getStateName());
				}
			} catch (Exception e) {

				throw new UserSearchException();

			}
			modelValues.add(modelValue);
		}
		keyword = keyword.replace("%20", " ");
		userSearchVO = new UserSearchVO(size, offSet, limit, modelValues,
				keyword);
	
		
			userSearchVO.setTotalRecordCount(getUserCount(exclusions));
		
		
		
			

		return userSearchVO;
	}

	private void filterData(List<UserModel> models, String additonalFilter) {
		List<UserModel> filterModels = new ArrayList<UserModel>();
		 models = filterModels;
	}

	public UserAdavanceSearchVO getAdvanceSearchUserModel(UserSearchAdvancePO userSearchAdvancePO, int offSet, int limit, String sortBy, int order,String keyword,String searchBy, List<SearchExclusionPO> exclusions) throws ParseException, UserSearchException {
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34,
				searchFields, new StandardAnalyzer(Version.LUCENE_34));

		queryParser.setAllowLeadingWildcard(true);
		
		keyword = keyword.replace(" ", "%20");
		String queryString = getQueryString(keyword, searchBy);
		Query luceneQueryNormal = queryParser.parse(queryString);


		
		BooleanQuery luceneQuery = new BooleanQuery();
		
		if(null != userSearchAdvancePO){
			
		
			for (SearchCriteriaPO searchCriteriaPOs : userSearchAdvancePO
					.getSearchCriterias()) {
				queryString = "";
			
	
				queryString = queryString
						+ UserAdavanceSearchAdapter.advanceSearchFields[searchCriteriaPOs
								.getSearchCriteriaId() - 1] + ":"
						+ searchCriteriaPOs.getSearchCriteriaKeyword() + "*";
				
				Query fieldQuery = queryParser.parse(queryString);
				luceneQuery.add(fieldQuery,Occur.MUST);
			}
			
			RangeSearchCriteriaPO rangeSearchCriteriaPO = userSearchAdvancePO.getRegistrationRangeCriteria();
			
			
			if(rangeSearchCriteriaPO != null && rangeSearchCriteriaPO.getFromSearchCriteriaKeyword() != null && rangeSearchCriteriaPO.getToSearchCriteriaKeyword() != null) {
				String sDate = DateTools.dateToString(ApplicationUtils.StringToDate1(userSearchAdvancePO.getRegistrationRangeCriteria().getFromSearchCriteriaKeyword()), DateTools.Resolution.MILLISECOND);
				String eDate = DateTools.dateToString(ApplicationUtils.StringToDate1(userSearchAdvancePO.getRegistrationRangeCriteria().getToSearchCriteriaKeyword()), DateTools.Resolution.MILLISECOND);
				TermRangeQuery rangeQuery = new TermRangeQuery("registrationDate", sDate, eDate, true, true);
				
				luceneQuery.add(rangeQuery,Occur.MUST);
			}
			
			rangeSearchCriteriaPO = userSearchAdvancePO.getRevenueRangeCriteria(); 
			
			if(rangeSearchCriteriaPO != null && rangeSearchCriteriaPO.getFromSearchCriteriaKeyword() != null && rangeSearchCriteriaPO.getToSearchCriteriaKeyword() != null){
				Query floatRangeQuery = NumericRangeQuery.newFloatRange("revenue",Float.parseFloat(userSearchAdvancePO.getRevenueRangeCriteria().getFromSearchCriteriaKeyword()), Float.parseFloat(userSearchAdvancePO.getRevenueRangeCriteria().getToSearchCriteriaKeyword()),true, true);
				luceneQuery.add(floatRangeQuery,Occur.MUST);
			}
			
			if(userSearchAdvancePO.getRoleIds() != null && !userSearchAdvancePO.getRoleIds().isEmpty()){
				
				String roleQuery = "(";
				
				int i=0;
				for(String roleId : userSearchAdvancePO.getRoleIds()){
					if (userSearchAdvancePO.getRoleIds().size() == i+1) {
						roleQuery = roleQuery + "userRoles.id:"+roleId;
					} else {
						roleQuery = roleQuery + "userRoles.id:"+roleId + " OR ";
					}
					i++;
				}
				Query fieldQuery = queryParser.parse(roleQuery + ")");
				luceneQuery.add(fieldQuery,Occur.MUST);
			}
		}
		Query fieldQuery = queryParser.parse("isDeleted:false");
		luceneQuery.add(fieldQuery,Occur.MUST);
		
		
		BooleanQuery lucene = new BooleanQuery();

		lucene.add(luceneQuery,  Occur.MUST);

		boolean orderDirect = order == 1 ? false : true;

		Sort sort = getSort(sortBy, orderDirect);
		
		SearchUtils.doExclusion(lucene, exclusions);

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, lucene, UserModel.class, sort, offSet, limit);

		int size = fullTextQuery.getResultSize();

		List<UserModel> models = fullTextQuery.getResultList();

		List<UserModelVO> modelValues = new ArrayList<UserModelVO>();

		for (UserModel model : models) {
			UserModelVO modelValue = new UserModelVO();
			try {
				BeanUtils.copyProperties(modelValue, model);
				if (model.getStateModel() != null){
					modelValue.setStateCode(model.getStateModel().getStateCode());
					modelValue.setStateName(model.getStateModel().getStateName());
				}
			} catch (Exception e) {

				throw new UserSearchException();

			}
			modelValues.add(modelValue);
		}
		UserAdavanceSearchVO userSearchVO = null;
		if(null != userSearchAdvancePO){
			userSearchVO = new UserAdavanceSearchVO(size,
					offSet, limit, modelValues,
					userSearchAdvancePO.getSearchCriterias());	
		} else{
			userSearchVO = new UserAdavanceSearchVO(size,
					offSet, limit, modelValues,
					null);
		}
		

		return userSearchVO;
	}
}
