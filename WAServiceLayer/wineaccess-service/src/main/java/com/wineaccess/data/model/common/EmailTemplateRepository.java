package com.wineaccess.data.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;

public class EmailTemplateRepository {


	final static String[] searchFields = new String[]{"name","fromEmail","subject"};
	final static String[] sortFields = new String[]{"modifiedDate","id","name","fromEmail","subject","modifiedBy"};

	@SuppressWarnings("unchecked")
	public static void save(EmailTemplate emailTemplate) {
		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(emailTemplate);
	}

	@SuppressWarnings("unchecked")
	public static void update(EmailTemplate emailTemplate) {
		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.update(emailTemplate);
	}

	@SuppressWarnings("unchecked")
	public static void delete(EmailTemplate emailTemplate) {
		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.delete(EmailTemplate.class, emailTemplate.getId());
	}

	@SuppressWarnings("unchecked")
	public static List<EmailTemplate> getEmailTemplates() {

		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");

		return genericDao.findByNamedQuery("EmailTemplate.getAll", null, null);

	}

	@SuppressWarnings("unchecked")
	public static EmailTemplate getEmailTemplateById(Long emailTemplateId) {

		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");

		List<EmailTemplate> emailTemplates = genericDao.findByNamedQuery(
				"EmailTemplate.getById", new String[] { "emailTemplateId" },
				emailTemplateId);

		if (emailTemplates != null && emailTemplates.size() > 0)
			return emailTemplates.get(0);
		else
			return null;
	}

	// fieldName -field names separated by comma if you want to apply search on
	// multiple fields
	@SuppressWarnings("unchecked")
	public static List<EmailTemplate> getByKeyword(Class clazz,
			final String fieldName, final String keyword, final String sortBy,
			final Integer offset, final Integer limit, final Integer sortOrder) {

		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");

		List<EmailTemplate> emailTemplates = genericDao.getSearch(clazz,
				fieldName, keyword, sortBy, sortOrder, offset, limit);

		return emailTemplates;
	}

	
	@SuppressWarnings("unchecked")
	public static BulkDeleteModel<EmailTemplate> multipleDeleteEmailTemplate(
			String multipleEmailTemplateIds, boolean confirmStatus) {
		GenericDAO<EmailTemplate> genericDAO = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");

		List<Serializable> id = new ArrayList<Serializable>();

		String[] emailTemplateIds = multipleEmailTemplateIds.split(",");
		for (int i = 0; i < emailTemplateIds.length; i++) {
			id.add(Long.parseLong(emailTemplateIds[i]));
		}

		BulkDeleteModel<EmailTemplate> bulkDeleteModel = new BulkDeleteModel<EmailTemplate>();
		try {
			
			bulkDeleteModel = genericDAO.performBulkDelete(id,EmailTemplate.class, "EmailTemplate", null, null, "isActive", confirmStatus);						
			
		} catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<EmailTemplate>) (persistenceException
						.getData());
			}
		} finally {
			return bulkDeleteModel;
		}

	}
	
	@SuppressWarnings("unchecked")
	public static List<EmailTemplate> getByAciveStatusAndEmailTemplateTypeId(Long emailTemplateTypeId) {

		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");

		return genericDao.findByNamedQuery("EmailTemplate.getByAciveStatusAndEmailTemplateTypeId", new String[] { "emailTemplateType" },
				EmailTemplateTypeRepository.getEmailTemplateTypeById(emailTemplateTypeId));

	}
	
	public static void updateEmailTemplateType(EmailTemplateType emailTemplateType) {
		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.update(emailTemplateType);
	}

	@SuppressWarnings("unchecked")
	public static EmailTemplate getEmailTemplateByName(
			String emailTemplateTypeName) {

		GenericDAO<EmailTemplateType> genericDao = (GenericDAO<EmailTemplateType>) CoreBeanFactory
				.getBean("genericDAO");

		List<EmailTemplateType> emailTemplateTypes = genericDao
				.findByNamedQuery("EmailTemplateType.getByName",
						new String[] { "emailTemplateTypeName" },
						emailTemplateTypeName);
		
		EmailTemplate activeEmailTemplate = null;
	
		if (emailTemplateTypes != null && emailTemplateTypes.size() > 0)
		{
			Set<EmailTemplate> emailTemplates = emailTemplateTypes.get(0).getEmailTemplates();
						
			for(EmailTemplate emailTemplate : emailTemplates)
			{
				if(emailTemplate.isActive())
				{
					activeEmailTemplate = emailTemplate;
					
					break;
				}
			}
		}
		return activeEmailTemplate;		
	}


	public static List<EmailTemplate> getEmailTemplate(String keyword,int sortOrder,int offset, int limit, String sortBy, EmailTemplateType emailTemplateType) {



		

		String query = "from EmailTemplate";


		if(keyword != null && !keyword.isEmpty()){
			query += " where ("; 
			boolean flag = true;
			for(String fieldName : searchFields){
				if(!flag){
					query +=" OR "+fieldName + " like \'%" + keyword + "%\'";
				}else{
					flag = false;
					query +=fieldName + " like \'%" + keyword + "%\'";
				}

			}
			query += " ) AND emailTemplateType=:emailTemplateType";
			//query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
		}else{
			query += " where emailTemplateType=:emailTemplateType";

		}






		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");


		return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(sortBy, sortOrder),new String[]{"emailTemplateType"},emailTemplateType);





		//return null;
	}

	public static int countRecordsForQuery(String keyword, EmailTemplateType emailTemplateType) {

		String query = "select count(*) from EmailTemplate";


		if(keyword != null && !keyword.isEmpty()){
			query += " where("; 
			boolean flag = true;
			for(String fieldName : searchFields){
				if(!flag){
					query +=" OR "+fieldName + " like \'%" + keyword + "%\'";
				}else{
					flag = false;
					query +=fieldName + " like \'%" + keyword + "%\'";
				}

			}
			query += " ) AND emailTemplateType=:emailTemplateType";
			//query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
		}else{
			query += " where emailTemplateType=:emailTemplateType";

		}



		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");


		return ((genericDao.executeCountQuery(query,new String[]{"emailTemplateType"},emailTemplateType).intValue()));

	}

	public static int countRecordsForQuery(EmailTemplateType emailTemplateType) {

		String query = "select count(*) from EmailTemplate where emailTemplateType=:emailTemplateType";

		//query += " where emailTemplateType=:emailTemplateType";



		GenericDAO<EmailTemplate> genericDao = (GenericDAO<EmailTemplate>) CoreBeanFactory
				.getBean("genericDAO");


		return ((genericDao.executeCountQuery(query,new String[]{"emailTemplateType"},emailTemplateType).intValue()));

	}


	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();

	}


}
