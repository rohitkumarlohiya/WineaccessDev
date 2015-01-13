package com.wineaccess.data.model.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * 
 * @author rohit.lohiya
 * 
 */
public class MasterDataTypeRepository {

	@SuppressWarnings("unchecked")
	public static List<MasterDataType> getMasterDataTypes(String status) {
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		
		if(status.equals("active"))
		{
			return genericDao.findByNamedQuery("MasterDataType.getAllByStatus", new String[] { "status" },true);
		}
		else if(status.equals("inactive"))
		{
			return genericDao.findByNamedQuery("MasterDataType.getAllByStatus", new String[] { "status" },false);
		}
		else
		{
			return genericDao.findByNamedQuery("MasterDataType.getAll", null, null);
		}
	}

	@SuppressWarnings("unchecked")
	public static MasterDataType getMasterDataTypeById(Long masterDataTypeId) {
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		List<MasterDataType> masterDataTypes = genericDao.findByNamedQuery(
				"MasterDataType.getById", new String[] { "masterDataTypeId" },
				new Long(masterDataTypeId));
		if (masterDataTypes != null && masterDataTypes.size() > 0)
			return masterDataTypes.get(0);
		else
			return null;
	}	
	
	
	@SuppressWarnings("unchecked")
	public static MasterDataType getMasterDataTypeByName(String masterDataTypename) {
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		List<MasterDataType> masterDataTypes = genericDao.findByNamedQuery("MasterDataType.getMasterDataByName", new String[] { "name" }, masterDataTypename);
		if (masterDataTypes != null && masterDataTypes.size() > 0)
			return masterDataTypes.get(0);
		else
			return null;
	}	
	
	@SuppressWarnings("unchecked")
	public static List<MasterDataType> getMasterDataTypes(String keyword, String fieldName, int sortOrder, int offset, int limit, List<String> exclusionList) {
		
		String query = "from MasterDataType";
		
		/*if(keyword != null && !keyword.isEmpty()){
			if(StringUtils.isNumeric(keyword)){
				query += " where id like \'%"+keyword+"%\' OR name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
				}else{
					query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
				}
		}*/
		boolean keywordPresent = false;
		if(keyword != null && !keyword.isEmpty()){
			keywordPresent = true;
			query += " where (label like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\')";
			
		}
		if (CollectionUtils.isNotEmpty(exclusionList)) {
			if (keywordPresent) {
				query += " and ";
			} else {
				query += " where ";
			}
			query += " name not in :exclusionList"; 
		}
		 
		
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		if (CollectionUtils.isNotEmpty(exclusionList)) {
			return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(fieldName, sortOrder), new String[]{"exclusionList"}, exclusionList); 
		} else {
			return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(fieldName, sortOrder));
		}
	}

	@SuppressWarnings("unchecked")
	public static int countRecordsForQuery(String keyword, String fieldName,
			int order, List<String> exclusionList) {
		
		String query = "select count(*) from MasterDataType";
		
		boolean keywordPresent = false;
		if(keyword != null && !keyword.isEmpty()){
			/*if(StringUtils.isNumeric(keyword)){
			query += " where id like \'%"+keyword+"%\' OR name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
			}else{
				query += " where name like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\'";
			}*/
			query += " where (label like \'%" + keyword+"%\' OR description like \'%"+keyword+"%\') ";
			keywordPresent = true;
		}
		//String query = "select count(*) from MasterDataType where id like %"+keyword+"% OR name like %" + keyword+"% OR description like %"+keyword+"% AND status="+true;
		
		if (CollectionUtils.isNotEmpty(exclusionList)) {
			if (keywordPresent) {
				query += " and ";
			} else {
				query += " where ";
			}
			query += " name not in :exclusionList"; 
		}
		
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		query += " " + generateSortingCriteria(fieldName, order);
		if (CollectionUtils.isNotEmpty(exclusionList)) {
			return ((genericDao.executeCountQuery(query, new String[]{"exclusionList"}, exclusionList)).intValue());
		} else {
			return ((genericDao.executeCountQuery(query)).intValue());
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static int countAllRecords(List<String> exclusionList) {
		String query = "select count(*) from MasterDataType";
		
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		//query += " " + ApplicationUtils.generateSortingCriteria(fieldName, order);
	
		int count = ((genericDao.executeCountQuery(query)).intValue());
		
		if(!CollectionUtils.isEmpty(exclusionList)){
			count = count-exclusionList.size();
		}
		return count;
	}
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
		
	}

	
}
