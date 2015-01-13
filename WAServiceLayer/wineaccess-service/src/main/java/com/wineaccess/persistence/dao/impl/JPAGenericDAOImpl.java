package com.wineaccess.persistence.dao.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.constants.EnumTypes.UserType;
import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.sampler.ProductSamplerModel;
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.NewsLetter;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.StateRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.util.SearchUtils;

/**
 * @see GenericDAO
 * @author jyoti.yadav@globallogic.com
 */
public class JPAGenericDAOImpl<T extends Persistent> implements GenericDAO<T> {

	@PersistenceContext
	EntityManager em;


	public List executeNativeCountQuery(String query, String[] paramNames, Object... paramValues) { 
		Query jpaQuery = em.createNativeQuery(query);

		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					jpaQuery.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}
		return jpaQuery.getResultList();
	}

	@Override
	public void create(Persistent t) {
		em.persist(t);
	}

	@Override
	public void saveAll(Collection<T> t) {
		for (T t1 : t) {
			em.persist(t1);
		}
	}

	@Override
	public void updateAll(Collection<T> t) {
		throw new UnsupportedOperationException(
				"Batch insert not supported by JPA.");
	}

	@Override
	public T update(T t) {
		return em.merge(t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(Class clas, Serializable primaryKeyId) {
		return (T) em.find(clas, primaryKeyId);
	}

	@Override
	public void delete(Class clas, Serializable primaryKeyId) {
		em.remove(get(clas, primaryKeyId));
	}

	@Override
	public void delete(T t) {
		em.remove(t);
	}

	@Override
	public List<T> findByNamedQuery(String queryName) {
		Query query = em.createNamedQuery(queryName);
		return query.getResultList();
	}

	@Override
	public List findByNamedQuery(String queryName, Object... params) {
		return null;
	}

	@Override
	public List<T> findByNamedQuery(String queryName, String[] paramNames,
			Object... paramValues) {

		Query query = em.createNamedQuery(queryName);

		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}
		return query.getResultList();
	}

	@Override
	public Object findObjectByNamedQuery(String queryName, String[] paramNames,
			Object... paramValues) {
		return null;
	}

	@Override
	public int executeUpdateNamedQuery(String queryName, Object... params) {
		return 0;
	}

	@Override
	public int executeUpdateNamedQuery(String queryName, String[] paramNames,
			Object... paramValues) {
		Query query = em.createNamedQuery(queryName);

		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}
		return query.executeUpdate();
		// return 0;
	}

	@Override
	public List findByExample(Persistent exampleCriteria) {
		return null;
	}

	public List getSearch(String searchText, Integer offset, Integer limit) {
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(em);

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
				new String[] { "firstName", "lastName", "email" },
				new StandardAnalyzer(Version.LUCENE_36));

		queryParser.setAllowLeadingWildcard(true);

		String queryString = "firstName:" + "Last99" + "* OR lastName:"
				+ "Last99" + "* OR email:" + "Last99" + "*";

		org.apache.lucene.search.Query q = null;
		try {
			q = queryParser.parse(queryString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sort sort = null;

		int sortBy = 1;

		if (sortBy == 1) {
			sort = new Sort(new SortField("firstName", SortField.STRING, false));
		} else if (sortBy == 2) {
			sort = new Sort(new SortField("lastName", SortField.STRING, false));
		} else if (sortBy == 3) {
			sort = new Sort(new SortField("email", SortField.STRING, false));
		}

		FullTextQuery fullTextQuery = SearchUtils.getFullTextQuery(fullTextEntityManager, q, UserModel.class, sort, 1, limit); 

		int size = fullTextQuery.getResultSize();
		List<UserModel> models = fullTextQuery.getResultList();
		return models;
	}

	public void indexLucene() {
		try {
			FullTextEntityManager fullTextEntityManager = Search
					.getFullTextEntityManager(em);
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean createAdminUserAndRole() {
		GenericDAO<UserRoles> genericDAO = (GenericDAO<UserRoles>) CoreBeanFactory
				.getBean("genericDAO");

		List<UserRoles> roles = genericDAO.findByNamedQuery(
				"UserRoles.getByRoleName", new String[] { "roleName" },
				ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name());
		if (roles.isEmpty()) {
			UserRoles superAdmin = new UserRoles(
					ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name(), true);
			genericDAO.create(superAdmin);

			UserModel userModel = new UserModel();
			userModel.setFirstName("Super");
			userModel.setLastName("Admin");
			if (PropertyholderUtils
					.getStringProperty(PropertyConstants.ADMIN_EMAIL) == null) {
				userModel.setEmail("admin@wineaccess.com");
			} else {
				userModel.setEmail(PropertyholderUtils
						.getStringProperty(PropertyConstants.ADMIN_EMAIL));
			}
			userModel.setUserType(UserType.Buyers);
			// Change for JIRA WA-781 on 21-07-2014
			if (PropertyholderUtils
					.getStringProperty(PropertyConstants.ADMIN_PASSWORD) == null) {
				userModel.setPassword((DigestUtils.shaHex("Global@123#"))
						.toUpperCase());
			} else {
				userModel.setPassword((PropertyholderUtils
						.getStringProperty(PropertyConstants.ADMIN_PASSWORD))
						.toUpperCase());
			}
			userModel.setEnabled(true);
			userModel.setReceivedNewLetter(true);
			userModel.setCountryId(1L);
			userModel.setZipCode("12345");
			userModel.getUserRoles().add(superAdmin);

			userModel.setGender("Male");
			userModel.setRegistered(true);
			userModel.setSalutation("Mr.");

			GenericDAO<UserModel> generic = (GenericDAO<UserModel>) CoreBeanFactory
					.getBean("genericDAO");
			generic.create(userModel);

			GenericDAO<UserRoles> genericRole = (GenericDAO<UserRoles>) CoreBeanFactory
					.getBean("genericDAO");
			genericRole.create(superAdmin);

			EntityListener.adminId = superAdmin.getId();
		} else {
			EntityListener.adminId = roles.get(0).getId();
		}

		roles = genericDAO.findByNamedQuery("UserRoles.getByRoleName",
				new String[] { "roleName" },
				ApplicationConstants.ROLES.ROLE_ADMIN.name());
		if (roles.isEmpty()) {
			UserRoles roleAdmin = new UserRoles(
					ApplicationConstants.ROLES.ROLE_ADMIN.name(), true);
			genericDAO.create(roleAdmin);
		}

		roles = genericDAO.findByNamedQuery("UserRoles.getByRoleName",
				new String[] { "roleName" },
				ApplicationConstants.ROLES.ROLE_RETAIL_USER.name());
		if (roles.isEmpty()) {
			UserRoles roleRetailUser = new UserRoles(
					ApplicationConstants.ROLES.ROLE_RETAIL_USER.name(), true);
			genericDAO.create(roleRetailUser);
		}

		roles = genericDAO.findByNamedQuery("UserRoles.getByRoleName",
				new String[] { "roleName" },
				ApplicationConstants.ROLES.ROLE_CUSTOMER_SUPPORT.name());
		if (roles.isEmpty()) {
			UserRoles roleCustomer = new UserRoles(
					ApplicationConstants.ROLES.ROLE_CUSTOMER_SUPPORT.name(),
					true);
			genericDAO.create(roleCustomer);
		}
		UserModel user = UserRepository.getByUserName("pgiangeruso@wineaccess.com");
		if(user == null){
			createUser("Paula","Giangeruso", ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name(), "pgiangeruso@wineaccess.com",(DigestUtils.shaHex("Global@123#")),"Mrs.","12345","Female", null);
		}else{
			if(BooleanUtils.isTrue(user.getIsDeleted())){
				createUser("Paula","Giangeruso", ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name(), "pgiangeruso@wineaccess.com",user.getPassword(),user.getSalutation(),user.getZipCode(),user.getGender(), user.getId());
			}

		}
		user = UserRepository.getByUserName("kclasby@wineacess.com");
		if(user == null){
			createUser("Kate","Clasby", ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name(), "kclasby@wineacess.com",(DigestUtils.shaHex("Global@123#")),"Mrs.","12345","Female", null);
		}else{
			if(BooleanUtils.isTrue(user.getIsDeleted())){
				createUser("Kate","Clasby", ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name(), "kclasby@wineacess.com",user.getPassword(),user.getSalutation(),user.getZipCode(),user.getGender(), user.getId());
			}
		}


		return true;
	}

	private void createUser(String firstName, String lastName, String role,
			String email, String password, String saluation, String zipcode, String gender, Long id) {
		GenericDAO<UserRoles> genericDAO = (GenericDAO<UserRoles>) CoreBeanFactory
				.getBean("genericDAO");
		UserModel userModel = new UserModel();
		userModel.setId(id);
		userModel.setCountryId(1L);
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setPassword(password);
		userModel.setEmail(email);
		userModel.setGender(gender);
		userModel.setEnabled(true);
		userModel.setDeleted(false);
		userModel.setReceivedNewLetter(true);
		userModel.setZipCode(zipcode);
		userModel.setUserType(UserType.Buyers);
		List<UserRoles> roles = genericDAO.findByNamedQuery(
				"UserRoles.getByRoleName", new String[] { "roleName" },
				ApplicationConstants.ROLES.ROLE_SUPER_ADMIN.name());

		if(!roles.isEmpty())
			userModel.getUserRoles().add(roles.get(0));


		userModel.setRegistered(true);

		GenericDAO<UserModel> generic = (GenericDAO<UserModel>) CoreBeanFactory
				.getBean("genericDAO");
		generic.update(userModel);

	}

	@Override
	public List<T> findByNamedQuery(String queryName, int offset, int limit) {
		Query query = em.createNamedQuery(queryName);
		query.setFirstResult(offset);
		if (limit != -1) {
			query.setMaxResults(limit);
		}
		return query.getResultList();
	}

	@Override
	public List<T> findByQuery(String query, int offset, int limit,
			String sortCriteria) {

		if (sortCriteria != null) {
			query = query + sortCriteria;
		}
		Query searchQuery = em.createQuery(query);
		searchQuery.setFirstResult(offset);
		if (limit != -1) {
			searchQuery.setMaxResults(limit);
		}
		return searchQuery.getResultList();
	}

	public T getFirstRecord(final String queryName, final String[] paramNames,
			final Object... paramValues) {

		Query query = em.createNamedQuery(queryName);

		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}

		query.setFirstResult(0);
		query.setMaxResults(1);
		List<T> resultList = query.getResultList();
		if (null == resultList || resultList.isEmpty())
			return null;
		else
			return (T) resultList.get(0);

	}

	public void refresh(List<? extends Serializable> idList, Class clazz) {
		org.hibernate.search.jpa.FullTextEntityManager fem = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		for (Serializable id : idList) {
			T model = (T) em.find(clazz, id);
			fem.index(model);
		}
		fem.flushToIndexes();
		fem.clear();
	}

	public BulkDeleteModel<T> performBulkDelete(
			List<? extends Serializable> idList, Class clazz,
			String entityName, String fieldName, Object fieldValue,
			String softDeleteFieldName, Boolean isforceDelete) {

		BulkDeleteModel<T> bulkDeleteResult = new BulkDeleteModel<T>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<T> depenedentList = new ArrayList<T>();
		List<T> deletedList = new ArrayList<T>();

		bulkDeleteResult.setDeletedList(deletedList);
		bulkDeleteResult.setNotDeletedList(depenedentList);
		bulkDeleteResult.setNotExistsList(notExistsList);

		for (Serializable id : idList) {
			T model = (T) em.find(clazz, id);

			if (model != null) {
				try {
					Query query = null;
					if (fieldName == null && fieldValue == null) {
						// add code for checking the dafault field
						if (softDeleteFieldName != null) {
							Object value = new PropertyDescriptor(
									softDeleteFieldName, clazz).getReadMethod()
									.invoke(model);
							if (BooleanUtils.isTrue((Boolean) value)) {
								depenedentList.add(model);
							} else {
								query = em.createQuery("delete from "
										+ entityName + " where id=:id");
								query.setParameter("id", id);
								int i = query.executeUpdate();
								deletedList.add(model);
							}
						} else {
							query = em.createQuery("delete from " + entityName
									+ " where id=:id");
							query.setParameter("id", id);
							int i = query.executeUpdate();
							deletedList.add(model);
						}
					} else {
						Object databaseValue = new PropertyDescriptor(
								fieldName, clazz).getReadMethod().invoke(model);
						Object valueOfIsDelField = new PropertyDescriptor(
								softDeleteFieldName, clazz).getReadMethod()
								.invoke(model);
						if (BooleanUtils.isTrue((Boolean) valueOfIsDelField)) {
							depenedentList.add(model);
						} else if (databaseValue != null
								&& fieldValue.equals(databaseValue)) {
							depenedentList.add(model);
						} else {
							query = em.createQuery("update " + entityName
									+ " set " + fieldName
									+ "=:softDeleteFieldValue"
									+ " where id=:id");
							query.setParameter("id", id);
							query.setParameter("softDeleteFieldValue",
									fieldValue);
							int i = query.executeUpdate();
							deletedList.add(model);
						}
					}

				} catch (Exception e) {

					depenedentList.add(model);
				}

			} else {
				notExistsList.add(id);
			}
		}

		/*
		 * if(fieldName != null && fieldValue != null){
		 * if(!notExistsList.isEmpty() || !depenedentList.isEmpty()){
		 * if(BooleanUtils.isNotTrue(isforceDelete)) throw new
		 * PersistenceException(bulkDeleteResult); } } else if(depenedentList !=
		 * null && !depenedentList.isEmpty()){
		 * if(BooleanUtils.isNotTrue(isforceDelete)) throw new
		 * PersistenceException(bulkDeleteResult); }
		 */

		if ((depenedentList != null && !depenedentList.isEmpty())
				|| (notExistsList != null && !notExistsList.isEmpty())) {
			/*if (deletedList != null && !deletedList.isEmpty()
					&& BooleanUtils.isNotTrue(isforceDelete)) {
				throw new PersistenceException(bulkDeleteResult);
			}*/
			if(BooleanUtils.isNotTrue(isforceDelete)){
				if(deletedList != null){
					throw new PersistenceException(bulkDeleteResult);
				}
			}

		}

		return bulkDeleteResult;

	}


	@Override
	public BulkDeleteModel<T> performBulkOpMultipleCondition(
			List<? extends Serializable> idList, Class clazz,
			String entityName, String fieldName, Object fieldValue,
			Map<String,Boolean> dependantFieldsMap, Boolean isforceDelete) {

		BulkDeleteModel<T> bulkDeleteResult = new BulkDeleteModel<T>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<T> depenedentList = new ArrayList<T>();
		List<T> deletedList = new ArrayList<T>();

		bulkDeleteResult.setDeletedList(deletedList);
		bulkDeleteResult.setNotDeletedList(depenedentList);
		bulkDeleteResult.setNotExistsList(notExistsList);

		for (Serializable id : idList) {
			T model = (T) em.find(clazz, id);

			if (model != null) {
				try {
					Query query = null;
					
					Object databaseValue = new PropertyDescriptor(
							fieldName, clazz).getReadMethod().invoke(model);
					List<Object> dependentFieldValueList = new ArrayList<Object>();
					boolean isDependent = false;
					for(String dependantField: dependantFieldsMap.keySet())
					{
						Object temp = new PropertyDescriptor(
								dependantField, clazz).getReadMethod()
								.invoke(model);
						if(dependantFieldsMap.get(dependantField).equals(temp))
							isDependent = true;
					}
					if(isDependent) {
						depenedentList.add(model);
					} else if (databaseValue != null
							&& fieldValue.equals(databaseValue)) {
						depenedentList.add(model);
					} else {
						query = em.createQuery("update " + entityName
								+ " set " + fieldName
								+ "=:softDeleteFieldValue"
								+ " where id=:id");
						query.setParameter("id", id);
						query.setParameter("softDeleteFieldValue",
								fieldValue);
						int i = query.executeUpdate();
						deletedList.add(model);
					}
					/*}*/

				} catch (Exception e) {

					depenedentList.add(model);
				}

			} else {
				notExistsList.add(id);
			}
		}

		

		if ((depenedentList != null && !depenedentList.isEmpty())
				|| (notExistsList != null && !notExistsList.isEmpty())) {
			
			if(BooleanUtils.isNotTrue(isforceDelete)){
				if(deletedList != null){
					throw new PersistenceException(bulkDeleteResult);
				}
			}

		}

		return bulkDeleteResult;

	}


	public void executeQuery(String query) {
		Query jpaQuery = em.createQuery(query);
		jpaQuery.executeUpdate();

	}

	public List<NewsLetter> getNewsletterList(List<Long> idList) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<NewsLetter> criteria = builder
				.createQuery(NewsLetter.class);

		Root<NewsLetter> scheduleRequest = criteria.from(NewsLetter.class);
		criteria = criteria.select(scheduleRequest);

		/*
		 * List<String> dummyList = new ArrayList<String>(); dummyList.add("1");
		 * dummyList.add("2"); dummyList.add("3"); dummyList.add("4");
		 */

		Expression<String> exp = scheduleRequest.get("id");
		Predicate predicate = exp.in(idList);
		criteria.where(predicate);

		List<NewsLetter> newsletterList = em.createQuery(criteria)
				.getResultList();
		return newsletterList;
	}

	public List<ProductItemModel> getProductItemList(List<Long> idList) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProductItemModel> criteria = builder
				.createQuery(ProductItemModel.class);

		Root<ProductItemModel> scheduleRequest = criteria.from(ProductItemModel.class);
		criteria = criteria.select(scheduleRequest);

		Expression<String> exp = scheduleRequest.get("id");
		Predicate predicate = exp.in(idList);
		criteria.where(predicate);

		List<ProductItemModel> productItemList = em.createQuery(criteria)
				.getResultList();
		return productItemList;
	}


	@Override
	public List<T> getSearch(Class clazz, String fieldName, String keyword,
			String sortBy, int sortOrder, int offset, int limit) {

		Query searchQuery = em.createQuery(generateQueryString(clazz,
				fieldName, keyword, sortBy, sortOrder));
		searchQuery.setFirstResult(offset);
		if (limit != -1) {
			searchQuery.setMaxResults(limit);
		}
		return searchQuery.getResultList();
	}

	private String generateQueryString(Class<?> clazz, String fieldName,
			String keyword, String sortBy, Integer sortOrder) {

		StringBuilder queryString = new StringBuilder();

		if (fieldName.equalsIgnoreCase("")) {

			queryString.append("from " + clazz.getName() + " where ");

			Field[] fields = clazz.getDeclaredFields();

			boolean firstTime = true;

			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getAnnotation(Column.class) != null) {
					if (firstTime) {
						queryString.append(fields[i].getName());
						queryString.append(" ");
						queryString.append("like '%");
						queryString.append(keyword);
						queryString.append("%' ");

						firstTime = false;

					} else {
						queryString.append("or ");
						queryString.append(fields[i].getName());
						queryString.append(" ");
						queryString.append("like '%");
						queryString.append(keyword);
						queryString.append("%' ");
					}
				}
			}
		}

		else {

			String fieldNames[] = fieldName.split(",");

			queryString.append("from " + clazz.getName() + " where ");

			boolean firstTime = true;

			for (int i = 0; i < fieldNames.length; i++) {

				if (firstTime) {
					queryString.append(fieldNames[i]);
					queryString.append(" ");
					queryString.append("like '%");
					queryString.append(keyword);
					queryString.append("%' ");

					firstTime = false;

				} else {
					queryString.append("or ");
					queryString.append(fieldNames[i]);
					queryString.append(" ");
					queryString.append("like '%");
					queryString.append(keyword);
					queryString.append("%' ");
				}
			}
		}

		if (sortBy != null && !sortBy.equalsIgnoreCase("")) {
			queryString.append(" Order by " + sortBy);
			queryString.append((sortOrder == 0) ? " desc" : " asc");
		}

		return queryString.toString();

	}

	@Override
	public List<T> findByNamedQueryPagination(String queryName, int offset,
			int limit, String[] paramNames, Object... paramValues) {

		Query query = em.createNamedQuery(queryName);
		query.setFirstResult(offset);
		if (limit != -1) {
			query.setMaxResults(limit);
		}
		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}
		return query.getResultList();
	}

	public Long executeCountQuery(String query) {
		Query jpaQuery = em.createQuery(query);
		return (Long) (jpaQuery.getResultList().get(0));
	}

	public Long executeCountQuery(String query, String[] paramNames,
			Object... paramValues) {
		Query jpaQuery = em.createQuery(query);

		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					if(paramValues[i]!=null)
						jpaQuery.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}
		return (Long) (jpaQuery.getResultList().get(0));
	}

	public List<T> findByQuery(String query, int offset, int limit,
			String sortCriteria, String[] paramNames, Object... paramValues) {
		if (sortCriteria != null) {
			query = query + sortCriteria;
		}
		Query jpaQuery = em.createQuery(query);
		if (paramNames != null) {
			if (paramNames != null && paramValues != null) {
				for (int i = 0; i < paramNames.length; i++) {
					if(paramValues[i]!=null)
						jpaQuery.setParameter(paramNames[i], paramValues[i]);
				}
			}
		}
		jpaQuery.setFirstResult(offset);
		if (limit != -1) {
			jpaQuery.setMaxResults(limit);
		}
		return jpaQuery.getResultList();
	}
	
	public BulkDeleteModel<ProductSamplerModel> delete(SamplerModel samplerModel, Set<Long> productIdSet, List<? extends Serializable> productIds, Boolean isforceDelete) {

		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<ProductSamplerModel> bulkDeleteModel = new BulkDeleteModel<ProductSamplerModel>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<ProductSamplerModel> depenedentList = new ArrayList<ProductSamplerModel>();
		List<ProductSamplerModel> deletedList = new ArrayList<ProductSamplerModel>();

		bulkDeleteModel.setDeletedList(deletedList);
		bulkDeleteModel.setNotDeletedList(depenedentList);
		bulkDeleteModel.setNotExistsList(notExistsList);

		if(null != productIdSet && productIdSet.size() > 0){
			Query query = null;
			for(Serializable productId : productIds){
				if(productIdSet.contains(productId)){
					ProductSamplerModel productSampler = getByProductAndSampler(samplerModel.getId(), Long.parseLong(productId.toString()));
					deletedList.add(productSampler);
					if(isforceDelete.booleanValue() && null != productSampler){
						
						query = em.createQuery("delete from ProductSamplerModel where id=:id");
						query.setParameter("id", productSampler.getId());
						query.executeUpdate();
					}
				} else{
					notExistsList.add(productId);	
				}
			}
		} else{
			for(Serializable productId: productIds){
				notExistsList.add(productId);	
			}
		}

		if((!isforceDelete.booleanValue()) && ((depenedentList == null  || depenedentList.isEmpty())
				&& (notExistsList == null || notExistsList.isEmpty())) ){
			Query query = null;
			for(ProductSamplerModel model : deletedList){
				query = em.createQuery("delete from ProductSamplerModel where id=:id");
				query.setParameter("id", model.getId());
				query.executeUpdate();
			}
		}		

		return bulkDeleteModel;
		
	}

	public ProductSamplerModel getByProductAndSampler(Long samplerId, Long productId) {
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		List<ProductSamplerModel> productSamplerModels = genericDao.findByNamedQuery("ProductSamplerModel.getByProductAndSampler", new String[] { "productId", "samplerId" }, productId, samplerId);
		if (productSamplerModels != null && productSamplerModels.size() > 0)
			return productSamplerModels.get(0);
		else
			return null;
	}
	
	public Set<CityModel> getCities(Long stateId){  
		GenericDAO<StateModel> genericDao = (GenericDAO<StateModel>)  CoreBeanFactory.getBean("genericDAO");
		StateModel state = StateRepository.getById(stateId).get(0);
		return state.getCities();
	}
	
	
	
	

}