package com.wineaccess.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionSystemException;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.orders.requisition.RequisitionRepository;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.winery.WineryRepository;

public class WarehouseRepository {


	@SuppressWarnings("unchecked")
	public static void save(WarehouseModel warehouseModel) {
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		genericDao.create(warehouseModel);
	}

	public static WarehouseModel getWarehouseById(Long id) {
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		List<WarehouseModel> warehouseModels = genericDao.findByNamedQuery("WarehouseModel.getById", new String[] { "id" }, id);
		if (warehouseModels != null && warehouseModels.size() > 0)
			return warehouseModels.get(0);
		else
			return null;
	}

	public static WarehouseModel getNonDeletedWarehouseById(Long id) {
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		List<WarehouseModel> warehouseModels = genericDao.findByNamedQuery("WarehouseModel.getNonDeletedWarehouseById", new String[] { "id" }, id);
		if (warehouseModels != null && warehouseModels.size() > 0)
			return warehouseModels.get(0);
		else
			return null;
	}

	public static WarehouseModel getWarehouseByName(String name) {
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		List<WarehouseModel> warehouseModels = genericDao.findByNamedQuery("WarehouseModel.getByName", new String[] { "name" }, name);
		if (warehouseModels != null && warehouseModels.size() > 0)
			return warehouseModels.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public static WarehouseModel update(WarehouseModel warehouseModel) {
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory
				.getBean("genericDAO");
		return genericDao.update(warehouseModel);
	}

	/**
	 * Method to delete the warehouse.
	 * */
	public static BulkDeleteModel<WarehouseModel> delete(List<? extends Serializable> warehouseIds, Boolean isforceDelete) {

		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>)  CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<WarehouseModel> bulkDeleteModel = new BulkDeleteModel<WarehouseModel>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<WarehouseModel> depenedentList = new ArrayList<WarehouseModel>();
		List<WarehouseModel> deletedList = new ArrayList<WarehouseModel>();

		bulkDeleteModel.setDeletedList(deletedList);
		bulkDeleteModel.setNotDeletedList(depenedentList);
		bulkDeleteModel.setNotExistsList(notExistsList);

		for(Serializable warehouseId : warehouseIds){
			WarehouseModel warehouse = WarehouseRepository.getWarehouseById((Long)warehouseId);
			if(warehouse == null){
				notExistsList.add(warehouseId);
			}else{
				Serializable existingWarehouseId = warehouse.getId();
				WarehouseModel model = genericDao.get(WarehouseModel.class, existingWarehouseId);
				if(!BooleanUtils.isFalse(model.getIsDeleted())){
					notExistsList.add(existingWarehouseId);
				}else{

					if(isWarehouseDependent(model)){
						depenedentList.add(model);
					}else{
						deletedList.add(model);
						if(isforceDelete){
							model.setIsDeleted(true);
							WarehouseRepository.update(model);
							List<Serializable> id = new ArrayList<Serializable>();
							id.add(existingWarehouseId);
						}
					}
				}
			}
		}
		
		if((!isforceDelete) && ((depenedentList == null  || depenedentList.isEmpty())
				&& (notExistsList == null || notExistsList.isEmpty())) ){
			for(WarehouseModel model : deletedList){
				model.setIsDeleted(true);
				WarehouseRepository.update(model);
				List<Serializable> id = new ArrayList<Serializable>();
				id.add(model.getId());
			}
		}
		return bulkDeleteModel;
	}

	private static void mergeDeleteResult(List bulkDeleteModel, List bulkWarehouseDeleteModel) {
		if(bulkWarehouseDeleteModel != null && !bulkWarehouseDeleteModel.isEmpty()){
			for(Object warehouse : bulkWarehouseDeleteModel){
				bulkDeleteModel.add(warehouse);
			}
		}
	}
	private static Boolean isWarehouseDependent(WarehouseModel warehouse){

		Boolean isDependent = false;

		Integer wineCount = WineRepository.getCountOfWarehouses(warehouse.getId());
		if(wineCount > 0){
			isDependent = true;
		}

		if(!isDependent){
			Integer wineryCount = WineryRepository.getCountOfWarehouses(warehouse.getId());
			if(wineryCount > 0){
				isDependent = true;
			}
		}

		if(!isDependent){
			Integer poCount = RequisitionRepository.getWarehouseCount(warehouse.getId());
			if(poCount > 0){
				isDependent = true;
			}
		}

		return isDependent;
	}	
	
	public static List<WarehouseModel> getAllByOffsetLimit(Integer offset,Integer limit,String fieldName, int sortOrder,List<Long> exclusionList, String keyword) {
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		if("".equals(fieldName) || "wineryCount".equals(fieldName)){
			fieldName = "id";
		}
		List<WarehouseModel> resultList = null;
		String searchField = "name";
		String query = "from WarehouseModel where isDeleted=false";
		Boolean flag = false;
		if(keyword != null && !keyword.isEmpty()){
			flag = true;
			query += " And " + searchField + " like \'%" + keyword + "%\'"; 
		}
		if (CollectionUtils.isNotEmpty(exclusionList)) {
			if(flag){
				query += " and id not in :exclusionList";	
			} else{
				query += " and id not in :exclusionList";
			}
			resultList = genericDao.findByQuery(query, offset, limit, generateSortingCriteria(fieldName, sortOrder), new String[]{"exclusionList"}, exclusionList); 
		}else{
			resultList = genericDao.findByQuery(query, offset, limit, generateSortingCriteria(fieldName, sortOrder)); 
		}
		
		
		return resultList;
	}

	public static int getTotalCount() {
		String query = StringUtils.EMPTY;
		query = "select count(*) from WarehouseModel where isDeleted=false";

		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		return (genericDao.executeCountQuery(query)).intValue();
	}

	public static int getWineryCount(Long warehouseId) {
		String query = StringUtils.EMPTY;
		query = "select count(*) from WineryModel where warehouseId = "+ warehouseId + " and isDeleted=false";

		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
		return (genericDao.executeCountQuery(query)).intValue();
	}
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
		
	}

	public static int countRecords(String fieldName,String keyword) {
		
		if("".equals(fieldName) || "wineryCount".equals(fieldName)){
			fieldName = "id";
		}
	//	List<WarehouseModel> resultList = null;
		String searchField = "name";
		
		String query = StringUtils.EMPTY;
		query = "select count(*) from WarehouseModel where isDeleted=false";
		
		
		if(keyword != null && !keyword.isEmpty()){
		//	flag = true;
			query += " And " + searchField + " like \'%" + keyword + "%\'"; 
		}
		
		GenericDAO<WarehouseModel> genericDao = (GenericDAO<WarehouseModel>) CoreBeanFactory.getBean("genericDAO");
		return (genericDao.executeCountQuery(query)).intValue();
		// TODO Auto-generated method stub
		//return 0;
	}}
