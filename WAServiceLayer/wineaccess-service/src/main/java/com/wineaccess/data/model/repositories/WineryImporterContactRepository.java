package com.wineaccess.data.model.repositories;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;

/**
 * @author gaurav.agarwal1
 *
 */
public class WineryImporterContactRepository {

	final static String[] searchFields = new String[]{"name","email","phone","contactType"};
	
	@PersistenceContext
	EntityManager em;

	public static WineryImporterContacts getImporterContactById(Long importerId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> importerContacts = genericDao.findByNamedQuery("WineryImporterContacts.getByImporterId", new String[] { "importerId" },importerId);
		if (importerContacts != null && importerContacts.size() > 0)
			return importerContacts.get(0);
		else
			return null;
	}

	public static WineryImporterContacts getWineryContactById(Long wineryId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> wineryContacts = genericDao.findByNamedQuery("WineryImporterContacts.getDefaultContactByWineryId", new String[] { "wineryId" },wineryId);
		if (wineryContacts != null && wineryContacts.size() > 0)
			return wineryContacts.get(0);
		else
			return null;
	}

	public static WineryImporterContacts getContactById(Long id) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> contacts = genericDao.findByNamedQuery("WineryImporterContacts.getById", new String[] { "id" },id);
		if (contacts != null && contacts.size() > 0)
			return contacts.get(0);
		else
			return null;
	}

	public static List<WineryImporterContacts> getContactByWineryId(Long wineryId, int offset, int limit) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> contacts = genericDao.findByNamedQueryPagination("WineryImporterContacts.getByWineryId", offset, limit,  new String [] {"wineryId"}, wineryId);

		return contacts;
	}
	
	public static WineryImporterContacts getContactByContactIdWineryId(Long wineryId, Long contactId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> contacts = genericDao.findByNamedQuery("WineryImporterContacts.findByWineryIdContactId", new String [] {"contactId","wineryId"}, contactId,wineryId);
		if(contacts!=null && !contacts.isEmpty())
			return contacts.get(0);
		else 
			return null;
		//List<WineryImporterContacts> contacts = genericDao.findByNamedQueryPagination("WineryImporterContacts.getByWineryId", offset, limit,  new String [] {"wineryId"}, wineryId);

	
	}

	public static List<WineryImporterContacts> getContactByImporterId(Long importerId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> contacts = genericDao.findByNamedQuery("WineryImporterContacts.getAllByImporterId", new String[] { "importerId" },importerId);
		return contacts;
	}

	public static void save(WineryImporterContacts wineryImporterContacts) {

		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(wineryImporterContacts);
	}

	public static BulkDeleteModel<WineryImporterContacts> delete(List<Serializable> contactIds, Boolean isForceDelete) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<WineryImporterContacts> bulkDeleteModel = new BulkDeleteModel<WineryImporterContacts>();
		try {

			bulkDeleteModel = genericDao.performBulkDelete(contactIds, WineryImporterContacts.class, "WineryImporterContacts","isDeleted", true,"isDefault",isForceDelete);

		} 
		catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<WineryImporterContacts>) (persistenceException.getData());
			}
		} finally {
			return bulkDeleteModel;
		}
	}	

	public static List<WineryImporterContacts> getContactDetailList(String keyword, int sortOrder, int offset, int limit, String sortBy, Long wineryId, Long importerId, Long contactTypeId) {


		String query = "from WineryImporterContacts where isDeleted=false";


		if(keyword != null && !keyword.isEmpty()){
			query += " AND "; 
			boolean flag = true;
			for(String fieldName : searchFields){
				if(!flag){
					query +=" OR "+fieldName + " like \'%" + keyword + "%\'";
				}else{
					flag = false;
					query +=fieldName + " like \'%" + keyword + "%\'";
				}

			}
			//query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
		}
		String andOrWhere = " ";
		if(query.contains( "where"))
			andOrWhere = " and ";
		if(wineryId!=null)
			query += andOrWhere+"  wineryId=:wineryId";


		else if(importerId!=null)
			query += andOrWhere+" importerId=:importerId";

		if(contactTypeId!=null)
			query += " AND contactType.id=:contactTypeId";

		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory
				.getBean("genericDAO");


		return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(sortBy, sortOrder), new String[] { "wineryId","importerId" ,"contactTypeId"},wineryId,importerId,contactTypeId);
	}



	public static int countRecordsForQuery(String keyword, Long wineryId, Long importerId, Long contactTypeId) {

		String query = "select count(*) from WineryImporterContacts where isDeleted=false";


		if(keyword != null && !keyword.isEmpty()){
			query += " "; 
			boolean flag = true;
			for(String fieldName : searchFields){
				if(!flag){
					query +=" OR "+fieldName + " like \'%" + keyword + "%\'";
				}else{
					flag = false;
					query +=fieldName + " like \'%" + keyword + "%\'";
				}

			}

			//query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
		}
		String andOrWhere = " ";
		if(query.contains( "where"))
			andOrWhere = " AND ";
		if(wineryId!=null)
			query += andOrWhere+"  wineryId=:wineryId";


		else if(importerId!=null)
			query += andOrWhere+" importerId=:importerId";

		if(contactTypeId!=null)
			query += " AND contactType.id=:contactTypeId";

		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory
				.getBean("genericDAO");


		return (genericDao.executeCountQuery(query, new String[] { "wineryId","importerId","contactTypeId"},wineryId,importerId,contactTypeId).intValue());



	}

	public static int countRecordsForQuery(Long wineryId, Long importerId, Long contactTypeId) {

		String query = "select count(*) from WineryImporterContacts where isDeleted=false";

		if(wineryId!=null)
			query += " AND wineryId=:wineryId";


		else if(importerId!=null)
			query += " AND importerId=:importerId";
		//query += " where emailTemplateType=:emailTemplateType";
		if(contactTypeId!=null)
			query += " AND contactType.id=:contactTypeId";


		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>) CoreBeanFactory
				.getBean("genericDAO");



		return (genericDao.executeCountQuery(query, new String[] { "wineryId","importerId","contactTypeId"},wineryId,importerId,contactTypeId).intValue());




	}

	final static String[] sortFields = new String[]{"name","id", "email","phone","contactType"};
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		String sortField = "";
		if (fieldName.equals("name")) {
			sortField = sortFields[0];
		} else if (fieldName.equals("id")) {
			sortField = sortFields[1];
		} else if (fieldName.equals("email")) {
			sortField = sortFields[2];
		} else if (fieldName.equals("phone")) {
			sortField = sortFields[3];
		} else if (fieldName.equals("contactType")) {
			sortField = sortFields[4];
		} else {
			sortField = sortFields[1];
		}
		StringBuilder sortCriteria = new StringBuilder(" Order by " + sortField);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();

	}

	public static void update(WineryImporterContacts wineryImporterContacts) {

		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(wineryImporterContacts);
	}


}
