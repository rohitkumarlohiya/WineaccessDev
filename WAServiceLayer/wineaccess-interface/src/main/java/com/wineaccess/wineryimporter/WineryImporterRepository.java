package com.wineaccess.wineryimporter;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryImporterAddressModel;
import com.wineaccess.data.model.catalog.WineyImpoterModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.persistence.dao.GenericDAO;


/**
 * @author abhishek.sharma1
 *
 */
public class WineryImporterRepository {

	final static String[] searchFields = new String[]{"addressType.name","phone"};
	final static String[] sortFields = new String[]{"addressType", "id" ,"phone"};


	/**
	 * @param addressModel
	 */
	public static void saveAddr(WineryImporterAddressModel addressModel) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(addressModel);
	}

	/**
	 * @param addressModel
	 * @return
	 */
	public static void save(WineyImpoterModel wineyImpoterModel) {
		GenericDAO<WineyImpoterModel> genericDao = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
		genericDao.update(wineyImpoterModel);
	}
	
	
	public static WineryImporterAddressModel updateAddr(WineryImporterAddressModel addressModel) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(addressModel);
	}

	/**
	 * @param addressModel
	 * @return
	 */
	public static WineryImporterAddressModel viewAddr(WineryImporterAddressModel addressModel) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		return null;
	}

	/**
	 * @param wineryId
	 * @return
	 */
	public static List<Long> getByWineyIdIsdefault(Long wineryId) {
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<Long> addressModelModelList = genericDao.findByNamedQuery("getByWineyIdIsdefault", new String [] {"wineryId"}, wineryId);
		return addressModelModelList;
	}

	/**
	 * @param importerId
	 * @return
	 */
	public static List<Long> getByImporterIdIsdefault(Long importerId) {
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<Long> addressModelModelList = genericDao.findByNamedQuery("getByImporterIdIsdefault", new String [] {"importerId"}, importerId);
		return addressModelModelList;
	}

	/**
	 * @param idsList
	 */
	public static void updateIsDefaultIds(List<Long> idsList) {
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		int addressModelModelList = genericDao.executeUpdateNamedQuery("updateIsDefault", new String [] {"idsList"}, idsList);

	}

	/**
	 * @param id
	 * @return
	 */
	public static WineryImporterAddressModel findById(Long id) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelModelList = genericDao.findByNamedQuery("findById", new String [] {"id"}, id);
		if(addressModelModelList!=null && !addressModelModelList.isEmpty())
			return addressModelModelList.get(0);
		else 
			return null;
	}

	/**
	 * @param addrId
	 * @param wineryId
	 * @return
	 */
	public static WineryImporterAddressModel findByWineryIdAddrId(Long addrId, Long wineryId) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelModelList = genericDao.findByNamedQuery("findByWineryIdAddrId", new String [] {"addrId","wineryId"}, addrId,wineryId);
		if(addressModelModelList!=null && !addressModelModelList.isEmpty())
			return addressModelModelList.get(0);
		else 
			return null;
	}

	/**
	 * @param addrId
	 * @param importerId
	 * @return
	 */
	public static WineryImporterAddressModel findByImporterIdAddrId(Long addrId, Long importerId) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelModelList = genericDao.findByNamedQuery("findByImporterIdAddrId", new String [] {"addrId","importerId"}, addrId,importerId);
		if(addressModelModelList!=null && !addressModelModelList.isEmpty())
			return addressModelModelList.get(0);
		else 
			return null;
	}

	/**
	 * @param wineryId
	 * @return
	 */
	public static WineryImporterAddressModel findByWineryId( Long wineryId) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelModelList = genericDao.findByNamedQuery("findByWineryId", new String [] {"wineryId"},wineryId);
		if(addressModelModelList!=null && !addressModelModelList.isEmpty())
			return addressModelModelList.get(0);
		else 
			return null;
	}

	/**
	 * @param importerId
	 * @return
	 */
	public static WineryImporterAddressModel findByImporterId(Long importerId) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelModelList = genericDao.findByNamedQuery("findByImporterId", new String [] {"importerId"}, importerId);
		if(addressModelModelList!=null && !addressModelModelList.isEmpty())
			return addressModelModelList.get(0);
		else 
			return null;
	}

	/**
	 * @param keyword
	 * @param sortOrder
	 * @param offset
	 * @param limit
	 * @param sortBy
	 * @param wineryId
	 * @param importerId
	 * @param addressTypeId
	 * @return
	 */
	public static List<WineryImporterAddressModel> getAddressDetailList(String keyword, int sortOrder, int offset, int limit, String sortBy, Long wineryId, Long importerId, Long addressTypeId) {


		String query = "from WineryImporterAddressModel where isDeleted=false";


		if(keyword != null && !keyword.isEmpty()){
		
			query += " AND (";
			int index = 0;
			for(String fieldName : searchFields){
				if(index == 0)
					query += fieldName + " like \'%" + keyword + "%\'";
				else
					query += " OR " + fieldName + " like \'%" + keyword + "%\'";
				index++;
			}
			query += ") ";
		}
		String andOrWhere = "";
		if(query.contains( "where"))
			andOrWhere = " and ";
		if(wineryId!=null)
			query += andOrWhere+"  wineryId=:wineryId";


		else if(importerId!=null)
			query += andOrWhere+" importerId=:importerId";

		if(addressTypeId!=null)
			query += " AND addressType.id=:addressTypeId";

		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>) CoreBeanFactory
				.getBean("genericDAO");


		return genericDao.findByQuery(query, offset, limit, generateSortingCriteria(sortBy, sortOrder), new String[] { "wineryId","importerId" ,"addressTypeId"},wineryId,importerId,addressTypeId);
	}



	/**
	 * @param keyword
	 * @param wineryId
	 * @param importerId
	 * @param addressTypeId
	 * @return
	 */
	public static int countRecordsForQuery(String keyword, Long wineryId, Long importerId, Long addressTypeId) {

		String query = "select count(*) from WineryImporterAddressModel where isDeleted=false";


		if(keyword != null && !keyword.isEmpty()){
			query += " AND (";
			int index = 0;
			for(String fieldName : searchFields){
				if(index == 0)
					query += fieldName + " like \'%" + keyword + "%\'";
				else
					query += " OR " + fieldName + " like \'%" + keyword + "%\'";
				index++;
			}
			query += ") ";
		}
		String andOrWhere = "";
		if(query.contains( "where"))
			andOrWhere = " and ";
		if(wineryId!=null)
			query += andOrWhere+"  wineryId=:wineryId";


		else if(importerId!=null)
			query += andOrWhere+" importerId=:importerId";

		if(addressTypeId!=null)
			query += " AND addressType.id=:addressTypeId";

		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>) CoreBeanFactory
				.getBean("genericDAO");


		return (genericDao.executeCountQuery(query, new String[] { "wineryId","importerId","addressTypeId"},wineryId,importerId,addressTypeId).intValue());

	}

	/**
	 * @param wineryId
	 * @param importerId
	 * @param addressTypeId
	 * @return
	 */
	public static int countRecordsForQuery(Long wineryId, Long importerId, Long addressTypeId) {

		String query = "select count(*) from WineryImporterAddressModel where isDeleted=false";

		if(wineryId!=null)
			query += " AND wineryId=:wineryId";

		else if(importerId!=null)
			query += " AND importerId=:importerId";
		//query += " where emailTemplateType=:emailTemplateType";
		if(addressTypeId!=null)
			query += " AND addressType.id=:addressTypeId";

		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>) CoreBeanFactory
				.getBean("genericDAO");


		return (genericDao.executeCountQuery(query, new String[] { "wineryId","importerId","addressTypeId"},wineryId,importerId,addressTypeId).intValue());


	}


	/**
	 * @param fieldName
	 * @param sortOrder
	 * @return
	 */
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		String orderBy = "";
		if (fieldName.equals(sortFields[0])) {
			orderBy = sortFields[0];
		} else if (fieldName.equals(sortFields[1])) {
			orderBy = sortFields[1];
		} else if (fieldName.equals(sortFields[2])) {
			orderBy = sortFields[2];
		} else {
			orderBy = sortFields[0];
		}
		
		StringBuilder sortCriteria = new StringBuilder(" Order by " + orderBy);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();

	}

	@SuppressWarnings("unchecked")
	public static List<WineyImpoterModel> getWineyImpoterModelByImporterId(Long importerId) {
		GenericDAO<WineyImpoterModel> genericDao = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
		List<WineyImpoterModel> wineyImpoterModels = genericDao.findByNamedQuery("WineyImpoterModel.getWineryForImporter", new String[] { "importerId"},importerId);
		return wineyImpoterModels;
	}
	
	@SuppressWarnings("unchecked")
	public static List<WineyImpoterModel> getNonDeletedWineyImpoterModelByImporterId(Long importerId) {
		GenericDAO<WineyImpoterModel> genericDao = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
		List<WineyImpoterModel> wineyImpoterModels = genericDao.findByNamedQuery("WineyImpoterModel.getNonDeletedWineryForImporter", new String[] { "importerId"},importerId);
		return wineyImpoterModels;
	}
	
	
	@SuppressWarnings("unchecked")
	public static WineryImporterAddressModel getNonDeletedWineyImpoterAddressByAddressTypeOfWarehouseAddress() {
		
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>) CoreBeanFactory.getBean("genericDAO");
		
		MasterData masterData = MasterDataRepository.getMasterDataByTypeAndNameKeyword("AddressType");
		
				
		GenericDAO<WineryImporterAddressModel> genericDao2 = (GenericDAO<WineryImporterAddressModel>) CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> wineryImporterAddressModels = genericDao2.findByNamedQuery("WineryImporterAddressModel.getByAddressType", new String[] { "addressTypeId"},masterData.getId());
		
		if(wineryImporterAddressModels != null && wineryImporterAddressModels.size() > 0)
		{
			return wineryImporterAddressModels.get(0);	
		}
		else
		{
			return null;
		}
		
	}
	
	public static WineryImporterAddressModel findByIdNonDeleted(Long id) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelModelList = genericDao.findByNamedQuery("findByIdNonDeleted", new String [] {"id"}, id);
		if(addressModelModelList!=null && !addressModelModelList.isEmpty())
			return addressModelModelList.get(0);
		else 
			return null;
	}
	
}
 