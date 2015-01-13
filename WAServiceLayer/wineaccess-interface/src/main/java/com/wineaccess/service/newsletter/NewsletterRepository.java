package com.wineaccess.service.newsletter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.transaction.TransactionSystemException;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.user.NewsLetter;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 *
 */
public class NewsletterRepository {
	
	final static String[] searchFields = new String[]{"name", "webName"};
	final static String[] sortFields = new String[]{"id", "name", "webName", "effDate", "endDate","emailSubject", "fromEmail", "submitDate","title"};

	/**
	 * Method to get newsletters based on the newsletter id passed.
	 * */
	public static List<NewsLetter> getById(List<Long> newsletterIds) {
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.getNewsletterList(newsletterIds);
	}
	public static List<NewsLetter> getByName(String name) {
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>) CoreBeanFactory
				.getBean("genericDAO");
		
		return genericDao.findByNamedQuery("NewsLetter.getByName", new String[] {"name"}, name);
	}
	
	
	/**
	 * Method to get save the newsletter.
	 * */
	public static void save(NewsLetter newsletter) {
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(newsletter);
	}
	
	/**
	 * Method to update the newsletter.
	 * */
	public static void update(NewsLetter newsletter) {
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(newsletter);
	}
	
	/**
	 * Method to delete the newsletter.
	 * */
	public static BulkDeleteModel<NewsLetter> delete(List<? extends Serializable> newsletterIds, Boolean isforceDelete) {
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>)  CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<NewsLetter> bulkDeleteModel = new BulkDeleteModel<NewsLetter>();
		try {
			
				if(!(BooleanUtils.isTrue((Boolean) isforceDelete)))
					bulkDeleteModel = genericDao.performBulkDelete(newsletterIds,NewsLetter.class, "NewsLetter",null,null,"isDefault",isforceDelete);
				else{
					BulkDeleteModel<NewsLetter> bulkNewsLetterDeleteModel = null;
					 for(Serializable id : newsletterIds){
							try{
							  
								   List<Long> ids = new ArrayList<Long>(); 
								   ids.add((Long)id);
								  
								   bulkNewsLetterDeleteModel = genericDao.performBulkDelete(ids,NewsLetter.class, "NewsLetter",null,null,"isDefault",isforceDelete);
								   
							   
							   
						   }catch (Exception e) {
							   
							   if(e instanceof  TransactionSystemException){
								   bulkNewsLetterDeleteModel = new BulkDeleteModel<NewsLetter>();
								   List<NewsLetter> depenedentList = new ArrayList<NewsLetter>();
								   bulkNewsLetterDeleteModel.setNotDeletedList(depenedentList);
								   depenedentList.add(genericDao.get(NewsLetter.class, id));
							   }
							   
								if (e instanceof PersistenceException) {
									PersistenceException persistenceException = (PersistenceException) e;
									bulkNewsLetterDeleteModel = (BulkDeleteModel<NewsLetter>) (persistenceException.getData());
								}
							}finally{
								mergeDeleteResult(bulkDeleteModel.getDeletedList(),
										bulkNewsLetterDeleteModel.getDeletedList());
								mergeDeleteResult(bulkDeleteModel.getNotDeletedList(),
										bulkNewsLetterDeleteModel.getNotDeletedList());
								mergeDeleteResult(bulkDeleteModel.getNotExistsList(),
										bulkNewsLetterDeleteModel.getNotExistsList());
							}
					}
				}
		
		} 
		catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<NewsLetter>) (persistenceException.getData());
			}
		} finally {
			return bulkDeleteModel;
		}
	}

	private static void mergeDeleteResult(
			List bulkDeleteModel,
			List bulkNewsLetterDeleteModel) {
		if(bulkNewsLetterDeleteModel != null && !bulkNewsLetterDeleteModel.isEmpty()){
			for(Object newsletter : bulkNewsLetterDeleteModel){
				bulkDeleteModel.add(newsletter);
			}
		}
	}
	
	/**
	 * Method to get newsletters based on search using the keyword. If nothing is passed in keyword then list of all the newsletters is returned.
	 * */
	public static List<NewsLetter> search(final String fieldName, final String keyword, final String sortBy,
			final Integer offset, final Integer limit, final Integer sortOrder) {
		
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>)  CoreBeanFactory.getBean("genericDAO");
	
		return genericDao.getSearch(NewsLetter.class,fieldName, keyword, sortBy, sortOrder, offset, limit);	
		
	}
	
	/**
	 * Method to get all the newsletters from the database.
	 * */
	public static List<NewsLetter> getAll() {
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("NewsLetter.getAll", null, null);
	}
	
	
	public static int countAllRecords() {
		String query = "select count(*) from NewsLetter";
		
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>) CoreBeanFactory
				.getBean("genericDAO");
		//query += " " + ApplicationUtils.generateSortingCriteria(fieldName, order);
	
		return ((genericDao.executeCountQuery(query)).intValue());
		
	}
	
	public static int countRecordsForQuery(String keyword) {
		
		String query = "select count(*) from NewsLetter";
		
		//String query = "from EmailTemplateType";
		if(keyword != null && !keyword.isEmpty()){
			query += " where "; 
			boolean flag = true;
			for(String fieldName : searchFields){
				if(!flag){
					query +="OR "+fieldName + " like \'%" + keyword + "%\'";
				}else{
					flag = false;
					query +=fieldName + " like \'%" + keyword + "%\'";
				}
				
			}
			
			//query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
			}
		
		GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>) CoreBeanFactory
				.getBean("genericDAO");
		
		
		return ((genericDao.executeCountQuery(query)).intValue());
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<NewsLetter> getByKeyword(String keyword, String sortBy, int sortOrder, int offset, int limit) {
		
		
		String query = "from NewsLetter";
		if(keyword != null && !keyword.isEmpty()){
			query += " where "; 
			boolean flag = true;
			for(String fieldName : searchFields){
				if(!flag){
					query +="OR "+fieldName + " like \'%" + keyword + "%\'";
				}else{
					flag = false;
					query +=fieldName + " like \'%" + keyword + "%\'";
				}
				
			}
			
			//query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
			}
			
			GenericDAO<NewsLetter> genericDao = (GenericDAO<NewsLetter>) CoreBeanFactory
					.getBean("genericDAO");
			return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(sortBy, sortOrder));
		

		
	}
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
		
	}
}
