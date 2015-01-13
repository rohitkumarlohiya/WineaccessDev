package com.wineaccess.wine;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.importer.ImporterRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.winery.WineryRepository;

public class WineRepository {

    @SuppressWarnings("unchecked")
    public static void save(WineModel wineModel) {
	GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory.getBean("genericDAO");
	genericDao.create(wineModel);
    }

    public static WineModel getWineById(Long id) {
	GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory.getBean("genericDAO");
	List<WineModel> wineModels = genericDao.findByNamedQuery("WineModel.getById", new String[] { "id" }, id);
	if (wineModels != null && wineModels.size() > 0)
	    return wineModels.get(0);
	else
	    return null;
    }

    public static Integer getCountOfWarehouses(Long id) {
	GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory.getBean("genericDAO");
	List<WineModel> wineModels = genericDao.findByNamedQuery("WineModel.getByWarehouseId", new String[] { "warehouseId" }, id);
	Integer count = 0;
	if(null != wineModels){
	    count = wineModels.size();
	}
	return count;
    }

    @SuppressWarnings("unchecked")
    public static WineModel update(WineModel wineModel) {
	GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory
		.getBean("genericDAO");
	return genericDao.update(wineModel);
    }

    /*
     * this method is used to bulk soft delete of wines
     * returns the bulkDeleteModel 
     */
    public static BulkDeleteModel<WineModel> delete(List<? extends Serializable> productIds,Boolean isForceDelete) {

	GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>)  CoreBeanFactory.getBean("genericDAO");
	BulkDeleteModel<WineModel> bulkDeleteModel = new BulkDeleteModel<WineModel>();

	List<Serializable> notExistsList = new ArrayList<Serializable>();
	List<WineModel> depenedentList = new ArrayList<WineModel>();
	List<WineModel> deletedList = new ArrayList<WineModel>();

	bulkDeleteModel.setDeletedList(deletedList);
	bulkDeleteModel.setNotDeletedList(depenedentList);
	bulkDeleteModel.setNotExistsList(notExistsList);

	for(Serializable productId : productIds){
	    ProductItemModel product = ProductItemRepository.getProductItemById((Long)productId);
	    if(product == null){
		notExistsList.add(productId);
		continue;
	    }else{
		Serializable wineId = product.getItemId();
		WineModel model = genericDao.get(WineModel.class, wineId);
		if(BooleanUtils.isTrue(model.getIsDeleted())){
		    notExistsList.add(wineId);
		    continue;
		}else{

		    if(isWineDependent(model)){
			depenedentList.add(model);
		    }else{
			deletedList.add(model);
			if(isForceDelete){
			    model.setIsDeleted(true);
			    WineRepository.update(model);
			    List<Serializable> id = new ArrayList<Serializable>();
			    id.add(wineId);
			    genericDao.refresh(id, WineModel.class);
			}
		    }
		}
	
		WineRepository.updateWCAndActiveWCInWinery(model.getWineryId().getId());
		
		if(BooleanUtils.isTrue(model.getIsImported()))
		{
			WineRepository.updateWCAndActiveWCInImporter(model.getImporterId().getId());
		}
		
		
	    }

	}
	    if((!isForceDelete) && ((depenedentList == null  || depenedentList.isEmpty())
		    && (notExistsList == null || notExistsList.isEmpty())) ){
		for(WineModel model : deletedList){
		    model.setIsDeleted(true);
		    WineRepository.update(model);
		    List<Serializable> id = new ArrayList<Serializable>();
		    id.add(model.getId());
		    genericDao.refresh(id, WineModel.class);
		}


	    
	}


	return bulkDeleteModel;
    }

    private static boolean isWineDependent(WineModel model) {
	// TODO add logic for dependency check
	return false;
    }

   

    public static BulkDeleteModel<WineModel> enableDisable(List<? extends Serializable> productIds,Boolean isForceDelete,Map<String,Boolean> dependantFieldsMap, Boolean status) {

	GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>)  CoreBeanFactory.getBean("genericDAO");
	BulkDeleteModel<WineModel> bulkDeleteModel = new BulkDeleteModel<WineModel>();

	List<Serializable> notExistsList = new ArrayList<Serializable>();
	List<WineModel> depenedentList = new ArrayList<WineModel>();
	List<WineModel> deletedList = new ArrayList<WineModel>();

	bulkDeleteModel.setDeletedList(deletedList);
	bulkDeleteModel.setNotDeletedList(depenedentList);
	bulkDeleteModel.setNotExistsList(notExistsList);

	for(Serializable productId : productIds){
	    ProductItemModel product = ProductItemRepository.getProductItemById((Long)productId);
	    if(product == null){
		notExistsList.add(productId);
		continue;
	    }else{
		Serializable wineId = product.getItemId();
		WineModel model = genericDao.get(WineModel.class, wineId);
		if(BooleanUtils.isTrue(model.getIsDeleted())){
		    notExistsList.add(wineId);
		    continue;
		}else{

		    if(isWineDependent(model)|| isInvalidScenarioEnableDisable(model,dependantFieldsMap)){
			depenedentList.add(model);
		    }else{
			deletedList.add(model);
			if(isForceDelete){
			    model.setIsEnabled(status);
			    WineRepository.update(model);
			    List<Serializable> id = new ArrayList<Serializable>();
			    id.add(wineId);
			    genericDao.refresh(id, WineModel.class);
			}
		    }
		}
	    }
	}

	    if((!isForceDelete) && ((depenedentList == null  || depenedentList.isEmpty())
		    && (notExistsList == null || notExistsList.isEmpty())) ){
		for(WineModel model : deletedList){
		    model.setIsEnabled(status);
		    WineRepository.update(model);
		    List<Serializable> id = new ArrayList<Serializable>();
		    id.add(model.getId());
		    genericDao.refresh(id, WineModel.class);
		}


	    }
	


	return bulkDeleteModel;
    }
    private static Boolean isInvalidScenarioEnableDisable(WineModel wineModel,Map<String,Boolean> dependantFieldsMap){
	boolean isDependent = false;
	for(String dependantField: dependantFieldsMap.keySet())
	{
	    Object temp = new Object();
	    try {
		temp = new PropertyDescriptor(
			dependantField, WineModel.class).getReadMethod()
			.invoke(wineModel);
	    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    if(dependantFieldsMap.get(dependantField).equals(temp))
		isDependent = true;
	}
	return isDependent;
    }
    
    
	@SuppressWarnings("unchecked")
	public static int getWineCountByWineryId(Long wineryId) {
		GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory
				.getBean("genericDAO");

		String query = "select count(*) from WineModel where wineryId.id = :wineryId and isDeleted= false";

		return ((genericDao.executeCountQuery(query,
				new String[] { "wineryId" }, wineryId).intValue()));

	}

	@SuppressWarnings("unchecked")
	public static int getActiveWineCountByWineryId(Long wineryId) {
		GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory
				.getBean("genericDAO");

		String query = "select count(*) from WineModel where wineryId.id = :wineryId and isEnabled= true and isDeleted= false";

		return ((genericDao.executeCountQuery(query,
				new String[] { "wineryId" }, wineryId).intValue()));

	}
	
	 public static void updateWCAndActiveWCInWinery(final Long wineryId) {    	
	    	
	    	WineryModel wineryModel = WineryRepository.getWineryById(wineryId);
	    	
	    	try {
	    		wineryModel.setWineCount(WineRepository.getWineCountByWineryId(wineryId));
		    	wineryModel.setActiveWineCount(WineRepository.getActiveWineCountByWineryId(wineryId));
		    	
		    	WineryRepository.update(wineryModel); 
	    	} catch(Exception ex) {
	    	}
	    }
	 
	@SuppressWarnings("unchecked")
	public static int getWineCountByImporterId(Long importerId) {
		GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory
				.getBean("genericDAO");

		String query = "select count(*) from WineModel where importerId.id = :importerId and isDeleted= false";

		return ((genericDao.executeCountQuery(query,
				new String[] { "importerId" }, importerId).intValue()));

	}

	@SuppressWarnings("unchecked")
	public static int getActiveWineCountByImporterId(Long importerId) {
		GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory
				.getBean("genericDAO");

		String query = "select count(*) from WineModel where importerId.id = :importerId and isEnabled= true and isDeleted= false";

		return ((genericDao.executeCountQuery(query,
				new String[] { "importerId" }, importerId).intValue()));

	}
	
	public static void updateWCAndActiveWCInImporter(final Long importerId) {    	
    	try {
    		ImporterModel importerModel = ImporterRepository.getImporterById(importerId);
        	
        	importerModel.setWineCount(WineRepository.getWineCountByImporterId(importerId));
        	importerModel.setActiveWineCount(WineRepository.getActiveWineCountByImporterId(importerId));
        	
        	ImporterRepository.update(importerModel); 
    	} catch (Exception ex) {
    		
    	}
    	 	
    }
}
