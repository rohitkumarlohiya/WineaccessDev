package com.wineaccess.importer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.WineyImpoterModel;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.wineryimporter.WineryImporterRepository;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 *
 */
public class ImporterRepository {
	
	
	public static void updateWineryCount(Long importerId) {
		ImporterModel importerModel = getImporterById(importerId);
		List<WineyImpoterModel> wineryImporterList = WineryImporterRepository.getNonDeletedWineyImpoterModelByImporterId(importerId);
		if(!wineryImporterList.isEmpty() && importerModel.getWineryCount() != wineryImporterList.size()){
			importerModel.setWineryCount(new Long(wineryImporterList.size()));
			update(importerModel);
		}else{
			if(wineryImporterList.isEmpty()){
				importerModel.setWineryCount(0L);
				update(importerModel);
			}
			
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static Integer getCountOfWarehouses(Long id) {
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<ImporterModel> importerModels = genericDao.findByNamedQuery(
				"ImporterModel.getCountOfWarehouses", new String[] { "id" },
				id);
		Integer count = 0;
		if (importerModels != null && importerModels.size() > 0){
			count = importerModels.size();
		}
					
		return count;
	}	
	
	/**
	 * Method to save the Importer.
	 * */
	public static void save(ImporterModel importerModel) {
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(importerModel);
	}
	
	/**
	 * Method to save the Importer.
	 * */
	public static void update(ImporterModel importerModel) {
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(importerModel);
	}
	
	//Get the Importer Contacts based on Importer Id
	public static WineryImporterContacts viewImporterContactDetails(Long importerId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> importerContacts = genericDao.findByNamedQuery("WineryImporterContacts.getByImporterId", new String[] {"importerId"},importerId);
		if(importerContacts!=null && !importerContacts.isEmpty())
			return importerContacts.get(0);
		else
			return null;
	}
	
	public static ImporterModel getImporterById(Long id) {
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory.getBean("genericDAO");
		List<ImporterModel> importerModels = genericDao.findByNamedQuery("ImporterModel.getById", new String[] { "id" },id);
		if (importerModels != null && importerModels.size() > 0)
			return importerModels.get(0);
		else
			return null;
	}
	
	public static ImporterModel getImporterByImporterCode(String importerCode) {
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory.getBean("genericDAO");
		List<ImporterModel> importerModels = genericDao.findByNamedQuery("ImporterModel.getByImporterCode", new String[] { "importerCode" },importerCode);
		if (importerModels != null && importerModels.size() > 0)
			return importerModels.get(0);
		else
			return null;
	}
	
	public static String getWAContactFreightById(Long id) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>)  CoreBeanFactory.getBean("genericDAO");
		List<MasterData> masterDatas =  genericDao.findByNamedQuery("MasterData.getById",  new String [] {"masterDataId"}, id);
		if(masterDatas != null && masterDatas.size() > 0){
			return masterDatas.get(0).getName();
		}
		return "";
	}
	
	/**
	 * 
	 * @return -deletes the importer details from database. If everything works fine then returns a success message: "Importer deleted successfully."
	 */
	public static  BulkDeleteModel<ImporterModel> delete(List<Long> importerIds, Boolean isForceDelete){
		BulkDeleteModel<ImporterModel> bulkDeleteModel = new BulkDeleteModel<ImporterModel>();
		
		for(Long importerId: importerIds){
			ImporterModel importer = ImporterRepository.getImporterById(importerId);
			if(null != importer){
				List<WineyImpoterModel> wineryImporterList = WineryImporterRepository.getNonDeletedWineyImpoterModelByImporterId(importerId);
				if(null == wineryImporterList || wineryImporterList.isEmpty()){
					bulkDeleteModel.getDeletedList().add(importer);
					/*if(isForceDelete){
						importer.setIsDeleted(true);
						ImporterRepository.update(importer);
					}*/
				} else{
					bulkDeleteModel.getNotDeletedList().add(importer);
				}	
			}
			else{
				((List<Long>)bulkDeleteModel.getNotExistsList()).add(importerId);
			}
		}
		
		if(isForceDelete || (bulkDeleteModel.getNotDeletedList().isEmpty() && bulkDeleteModel.getNotExistsList().isEmpty()))
		{
			List<ImporterModel> importerModelList = bulkDeleteModel.getDeletedList();
			for(ImporterModel importerModel : importerModelList)
			{
				GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory.getBean("genericDAO");
				importerModel.setIsDeleted(true);
				genericDao.update(importerModel);
				
			}
		}
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory.getBean("genericDAO");
		genericDao.refresh(importerIds, ImporterModel.class);
		return bulkDeleteModel;
	}

	public static ImporterModel getImporterByImporterName(String importerName) {
		
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory.getBean("genericDAO");
		List<ImporterModel> importerModels = genericDao.findByNamedQuery("ImporterModel.getByImporterName", new String[] { "importerName" },importerName);
		if (importerModels != null && importerModels.size() > 0)
			return importerModels.get(0);
		else
			return null;
	}
	
	public static BulkDeleteModel<ImporterModel> enableDisable(List<Long> importerIds,Boolean status, Boolean isForceDelete) {

		BulkDeleteModel<ImporterModel> bulkDeleteModel = new BulkDeleteModel<ImporterModel>();
		for (Long importerId : importerIds) {
			ImporterModel importer = getImporterById(importerId);
			if (importer != null) {
				List<WineyImpoterModel> wineryImporterList = WineryImporterRepository.getNonDeletedWineyImpoterModelByImporterId(importerId);
				if (!isImporterDependent(importer)) {
					bulkDeleteModel.getDeletedList().add(importer);
				} else {
					bulkDeleteModel.getNotDeletedList().add(importer);
				}
			} else {
				((List<Long>) bulkDeleteModel.getNotExistsList()).add(importerId);
			}
		}

		if (isForceDelete || (bulkDeleteModel.getNotDeletedList().isEmpty() && bulkDeleteModel.getNotExistsList().isEmpty())) {
			List<ImporterModel> importerModelList = bulkDeleteModel.getDeletedList();
			for (ImporterModel importerModel : importerModelList) {
				importerModel.setIsEnabled(status);
				update(importerModel);
				List<Long> id = new ArrayList<Long>();
				id.add(importerModel.getId());
				GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>) CoreBeanFactory.getBean("genericDAO");
				genericDao.refresh(id, ImporterModel.class);

			}
		}
		
		return bulkDeleteModel;

	}
	
	private static boolean isImporterDependent(final ImporterModel model) {
		Boolean isDependent = false;
		
		/*List<WineyImpoterModel> wineryImporterList = WineryImporterRepository.getNonDeletedWineyImpoterModelByImporterId(model.getId());
		if (wineryImporterList == null || wineryImporterList.isEmpty()) {
			isDependent = false;
		}else{
			isDependent = true;
		}*/
		return isDependent;
	}

}

