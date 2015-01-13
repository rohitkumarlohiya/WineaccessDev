package com.wineaccess.persistence.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.sampler.ProductSamplerModel;
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.NewsLetter;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.exception.PersistenceException;

public interface GenericDAO <T extends Persistent> {

	public List executeNativeCountQuery(String query, String[] paramNames, Object... paramValues);

	public void refresh(List<? extends Serializable> idList, Class clazz);

	/**
	 * Insert T into the database
	 * 
	 * @param t
	 *            the persistable Object which needs to be created
	 */
	public void create(T t) throws PersistenceException;


	/**
	 * Insert collection T into the database in batch mode.
	 * 
	 * @param t
	 *            the persistable Object which needs to be created
	 */
	public void saveAll(Collection<T> t);


	/**
	 * Updates T
	 * 
	 * @param t
	 *            the persistable object which needs to be updated
	 * 
	 * @return the updated object
	 */
	public void updateAll(Collection<T> t);

	/**
	 * Updates T
	 * 
	 * @param t
	 *            the persistable object which needs to be updated
	 * 
	 * @return the updated object
	 */
	public T update(T t);

	/**
	 * Gets the object by primary key
	 * 
	 * @param clas
	 *            the class of persistable object
	 * @param id
	 *            the primary key of the object
	 * 
	 * @return the t
	 */
	public T get(Class clas, Serializable primaryKeyId);

	/**
	 * Deletes the object by primary key and class.
	 * 
	 * @param clas
	 *            the class
	 * @param id
	 *            the id
	 */
	public void delete(Class clas, Serializable id);

	/**
	 * Deletes the given object instance
	 * 
	 * @param t
	 *            the object which needs to be deleted
	 */
	public void delete(T t);

	/**
	 * Find by named query.
	 * 
	 * @param queryName
	 *            the query name
	 * 
	 * @return the list of persistable
	 */
	public List<T> findByNamedQuery(String queryName);

	/**
	 * Find by named query and parameters
	 * 
	 * @param queryName -
	 *            the query name
	 * @param params -
	 *            the params which needs to be filled in query, should be in the
	 *            same order as given in query
	 * 
	 * @return the list< t>
	 */
	public List<T> findByNamedQuery(final String queryName,
			final Object... params);

	/**
	 * Find by named query and named params
	 * 
	 * @param queryName -
	 *            the query with named params
	 * @param paramNames -
	 *            the param names as mentioned in query.
	 * @param paramValues -
	 *            the param values, should be in same order as the order of
	 *            paramNames.
	 * 
	 * @return the list< t>
	 */
	public List<T> findByNamedQuery(final String queryName,
			final String[] paramNames, final Object... paramValues);

	/**
	 * Find object by named query and named params.
	 * 
	 * @param queryName -
	 *            the query with named params
	 * @param paramNames -
	 *            the param names as mentioned in query.
	 * @param paramValues -
	 *            the param values, should be in same order as the order of
	 *            paramNames.
	 * 
	 * @return the object
	 */
	public Object findObjectByNamedQuery(final String queryName,
			final String[] paramNames, final Object... paramValues);

	/**
	 * Execute update by named query and params
	 * 
	 * @param queryName -
	 *            the query name
	 * @param params -
	 *            the params which needs to be filled in query, should be in the
	 *            same order as given in query
	 * 
	 * 
	 * @return the int
	 */
	public int executeUpdateNamedQuery(String queryName, final Object... params);

	/**
	 * Execute update by named query and params
	 * 
	 * @param queryName -
	 *            the query with named params
	 * @param paramNames -
	 *            the param names as mentioned in query.
	 * @param paramValues -
	 *            the param values, should be in same order as the order of
	 *            paramNames.
	 * 
	 * @return the int
	 */
	public int executeUpdateNamedQuery(String queryName,
			final String[] paramNames, final Object... paramValues);

	/**
	 * Load all.
	 * 
	 * @param input
	 *            the input
	 * 
	 * @return the search result
	 */
	//public SearchResult loadAll(SearchInput input);

	/**
	 * Find by example.
	 * 
	 * @param exampleCriteria
	 *            the example criteria
	 * 
	 * @return the list< t>
	 */
	public List<T> findByExample(T exampleCriteria);


	/**
	 * @param searchText
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<T> getSearch(String searchText, Integer offset, Integer limit);


	public void indexLucene();

	public boolean createAdminUserAndRole();

	public List<T> findByNamedQuery(String queryName, int offset, int limit);

	public List<T> getSearch(Class clazz,  String fieldName, String keyword, String sortBy,  int sortOrder, int offset, int limit);

	public List<T> findByQuery(String queryName, int offset, int limit, String searchCriteria);

	public T getFirstRecord(final String queryName,
			final String[] paramNames, final Object... paramValues);

	public BulkDeleteModel<T> performBulkDelete(List<? extends Serializable> idList, Class clazz, String entityName,String fieldName, Object fieldValue, 
			String softDeleteFieldName, Boolean isforceDelete);

	public void executeQuery(String query);
	public List<NewsLetter> getNewsletterList(List<Long> list);
	public List<ProductItemModel> getProductItemList(List<Long> idList);
	public List<T> findByNamedQueryPagination(String queryName,int offset, int limit, String[] paramNames,Object... paramValues); 
	public Long executeCountQuery(String query);


	public Long executeCountQuery(String query, String[] params,
			Object... paramValue);


	public List<T> findByQuery(String query, int offset, int limit,
			String generateSortingCriteria, String[] params,
			Object... paramValue);

	

	BulkDeleteModel<T> performBulkOpMultipleCondition(
			List<? extends Serializable> idList, Class clazz,
			String entityName, String fieldName, Object fieldValue,
			Map<String, Boolean> dependantFieldsMap, Boolean isforceDelete);
	
	public BulkDeleteModel<ProductSamplerModel> delete(SamplerModel samplerModel, Set<Long> productIdSet, List<? extends Serializable> productIds, Boolean isforceDelete);
	public Set<CityModel> getCities(Long stateId);
}
