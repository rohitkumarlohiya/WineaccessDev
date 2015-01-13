package com.wineaccess.data.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;

public class MasterDataRepository {

	public static void save(MasterData masterData) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(masterData);
	}

	public static void update(MasterData masterData) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.update(masterData);
	}

	public static void delete(MasterData masterData) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		// genericDao.delete(masterData);
		genericDao.delete(MasterData.class, masterData.getId());
	}

	public static List<MasterData> getMasterDatas() {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		return genericDao.findByNamedQuery("MasterData.getAll", null, null);

	}

	public static MasterData getMasterDataById(Long masterDataId) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		List<MasterData> masterDatas = genericDao.findByNamedQuery(
				"MasterData.getById", new String[] { "masterDataId" },
				new Long(masterDataId));
		if (masterDatas != null && masterDatas.size() > 0)
			return masterDatas.get(0);
		else
			return null;
	}

	public static MasterData getMasterDataLastUpdated(MasterDataType masterDataType) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		List<MasterData> masterDatas = genericDao.findByNamedQuery(
				"MasterData.getLastUpdated", new String[] { "masterDataType" },
				masterDataType);
		if (masterDatas != null && masterDatas.size() > 0)
			return masterDatas.get(0);
		else
			return null;
	}

	public static BulkDeleteModel<MasterData> multipleDeleteMasterData(
			String multipleMasterDataIds, boolean confirmStatus) {
		GenericDAO<MasterData> genericDAO = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");

		List<Serializable> id = new ArrayList<Serializable>();

		String[] masterDataIds = multipleMasterDataIds.split(",");
		for (int i = 0; i < masterDataIds.length; i++) {
			id.add(Long.parseLong(masterDataIds[i]));
		}

		BulkDeleteModel<MasterData> bulkDeleteModel = new BulkDeleteModel<MasterData>();
		try {
			bulkDeleteModel = genericDAO.performBulkDelete(id,
					MasterData.class, "MasterData", null, null, null,
					confirmStatus);
		} catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<MasterData>) (persistenceException
						.getData());
			}
		} finally {
			return bulkDeleteModel;
		}

	}

	public static List<MasterDataType> getMasterDataTypes() {
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		return genericDao.findByNamedQuery("MasterDataType.getAll", null, null);

	}

	public static void updateMasterDataType(MasterDataType masterDataType) {
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.update(masterDataType);
	}
	
	
	public static boolean isMasterDataExists(String masterDataTypeId, String masterDataName) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		MasterData dbData= genericDao.getFirstRecord("MasterData.getMasterData", new String[]{"masterDataTypeId","name"}, masterDataTypeId,masterDataName);
		boolean isExists = false;
		if(dbData != null){
			isExists = true;
		}
		return isExists;
	}
	
	
public static List<MasterData> getMasterData(String keyword, String fieldName, int sortOrder, int offset, int limit, String typeId, MasterDataType masterDataType) {
	
	
	
	//MasterDataMasterDataTypeRepository.getMasterDataTypeById((long) typeId);
		
		String query = "from MasterData";
		
		if(keyword != null && !keyword.isEmpty()){
			if(StringUtils.isNumeric(keyword)){
				query += " where id like \'%"+keyword+"%\' OR name like \'%" + keyword+"%\'";
				}else{
					query += " where name like \'%" + keyword+"%\'";
				}
			if(typeId != null){
				 
				query += " AND masterDataType=:masterDataType";
			}
			
		}else{
			if(typeId != null){
				 
				query += " where masterDataType=:masterDataType";
			}
		}
		 
		
		
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		
		
		if(typeId != null){
			
			return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(fieldName, sortOrder),new String[]{"masterDataType"},masterDataType);
			}else{
				return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(fieldName, sortOrder));
			}
		
		
		//return null;
	}

	public static int countRecordsForQuery(String keyword, String fieldName,
			int order, String typeId, MasterDataType masterDataType) {
		
		String query = "select count(*) from MasterData";
		
		if(keyword != null && !keyword.isEmpty()){
			if(StringUtils.isNumeric(keyword)){
				query += " where id like \'%"+keyword+"%\' OR name like \'%" + keyword+"%\'";
				}else{
					query += " where name like \'%" + keyword+"%\'";
				}
			if(typeId != null){
				 
				query += " AND masterDataType=:masterDataType";
			}
			
		}else{
			if(typeId != null){
				 
				query += " where masterDataType=:masterDataType";
			}
		}
		//String query = "select count(*) from MasterDataType where id like %"+keyword+"% OR name like %" + keyword+"% OR description like %"+keyword+"% AND status="+true;
		
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		query += " " + generateSortingCriteria(fieldName, order);
		if(typeId != null){
		return ((genericDao.executeCountQuery(query,new String[]{"masterDataType"},masterDataType).intValue()));
		}else{
			return (genericDao.executeCountQuery(query)).intValue();
		}
	}
	
	public static int countAllRecords() {
		String query = "select count(*) from MasterData";
		
		GenericDAO<MasterDataType> genericDao = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		//query += " " + ApplicationUtils.generateSortingCriteria(fieldName, order);
	
		return ((genericDao.executeCountQuery(query)).intValue());
		
	}
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
		
	}
	
	@SuppressWarnings("unchecked")
	public static MasterData getMasterDataByTypeAndName(
			String masterDataTypeName, String masterDataName) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		MasterData masterData = genericDao.getFirstRecord(
				"MasterData.getMasterData", new String[] { "masterDataTypeId",
						"name" }, masterDataTypeName, masterDataName);

		if (masterData != null) {
			return masterData;
		} else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static MasterData getMasterDataByTypeAndNameKeyword(
			String masterDataTypeName) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		MasterData masterData = genericDao.getFirstRecord(
				"MasterData.getMasterDataContainsKeyword", new String[] { "masterDataTypeId"}, masterDataTypeName);

		if (masterData != null) {
			return masterData;
		} else {
			return null;
		}
	}
}