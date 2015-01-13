package com.wineaccess.data.model.common;

import java.util.Date;
import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

public class EmailTemplateTypeRepository {
	
	final static String[] searchFields = new String[]{"label","description"};
	final static String[] sortFields = new String[]{"modifiedDate","label","description","modifiedBy"};

	@SuppressWarnings("unchecked")
	public static List<EmailTemplateType> getEmailTemplateTypes(
			String status) {

		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory.getBean("genericDAO");

		/*if (isOnlyActive)
			return genericDao.findByNamedQuery(
					"EmailTemplateType.getAllOnlyActive", null, null);
		else
			return genericDao.findByNamedQuery("EmailTemplateType.getAll",
					null, null);*/
		
		if(status.equals("active"))
		{
			return genericDao.findByNamedQuery("EmailTemplateType.getAllByStatus", new String[] { "status" },true);
		}
		else if(status.equals("inactive"))
		{
			return genericDao.findByNamedQuery("EmailTemplateType.getAllByStatus", new String[] { "status" },false);
		}
		else
		{
			return genericDao.findByNamedQuery("EmailTemplateType.getAll", null, null);
		}

	}

	@SuppressWarnings("unchecked")
	public static EmailTemplateType getEmailTemplateTypeById(
			Long emailTemplateTypeId) {

		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
				.getBean("genericDAO");

		List<EmailTemplateType> emailTemplateTypes = genericDao
				.findByNamedQuery("EmailTemplateType.getById",
						new String[] { "emailTemplateTypeId" },
						emailTemplateTypeId);

		if (emailTemplateTypes != null && emailTemplateTypes.size() > 0)
			return emailTemplateTypes.get(0);
		else
			return null;
	}
	
	
	
	

	// fieldName -field names separated by comma if you want to apply search on
	// multiple fields
	@SuppressWarnings("unchecked")
	public static List<EmailTemplateType> getByKeyword(String keyword, String sortBy, int sortOrder, int offset, int limit) {
		
		
		String query = "from EmailTemplateType";
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
			
			GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
					.getBean("genericDAO");
			return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(sortBy, sortOrder));
		

		
	}

	@SuppressWarnings("unchecked")
	public static EmailTemplateType getEmailTemplateTypeByName(
			String emailTemplateTypeName) {

		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
				.getBean("genericDAO");

		List<EmailTemplateType> emailTemplateTypes = genericDao
				.findByNamedQuery("EmailTemplateType.getByName",
						new String[] { "emailTemplateTypeName" },
						emailTemplateTypeName);

		if (emailTemplateTypes != null && emailTemplateTypes.size() > 0)
			return emailTemplateTypes.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void update(EmailTemplateType emailTemplateType) {
		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
				.getBean("genericDAO");
		//This is because if we are not updating anything entity listener will not call.
		emailTemplateType.setModifiedDate(new Date());
		genericDao.update(emailTemplateType);
	}
	
	
	public static int countAllRecords() {
		String query = "select count(*) from EmailTemplateType";
		
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		//query += " " + ApplicationUtils.generateSortingCriteria(fieldName, order);
	
		return ((genericDao.executeCountQuery(query)).intValue());
		
	}
	
	public static int countRecordsForQuery(String keyword) {
		
		String query = "select count(*) from EmailTemplateType";
		
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
		
		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
				.getBean("genericDAO");
		
		
		return ((genericDao.executeCountQuery(query)).intValue());
		
	}
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
		
	}
}
