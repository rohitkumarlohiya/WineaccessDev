package com.wineaccess.winery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryLicensePermitAltStates;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.WineyImpoterModel;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.importer.ImporterRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.security.masterdatatype.MasterDataTypeAdapterHelper;
import com.wineaccess.winerypermit.WineryPermitRepository;

/**
 * @author gaurav.agarwal1
 *
 */
public class WineryRepository {

	private static Log logger = LogFactory.getLog(WineryRepository.class);
	@SuppressWarnings("unchecked")
	public static void save(WineryModel wineryModel) {
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(wineryModel);
	}

	@SuppressWarnings("unchecked")
	public static Integer getCountOfWarehouses(Long id) {
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<WineryModel> wineryModels = genericDao.findByNamedQuery(
				"WineryModel.getCountOfWarehouses", new String[] { "id" },
				id);
		Integer count = 0;
		if (wineryModels != null && wineryModels.size() > 0){
			count = wineryModels.size();
		}
					
		return count;
	}	
	
	@SuppressWarnings("unchecked")
	public static void saveWinery(WineryModel wineryModelTemp,WineryPO wineryPO) {
		
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
		
		wineryModelTemp = genericDao.update(wineryModelTemp);		
		
		if(wineryModelTemp.getId()!=null)
		{
			try{
				

				Set<MasterData> wineryPermitMasterDataList = MasterDataTypeAdapterHelper.getMasterDataListByMasterTypeName("WineryLicencePermit");

				for(MasterData masterDataFromDB: wineryPermitMasterDataList)
				{
					WineryLicensePermitAltStates wineryLicensePermitAltStates = new WineryLicensePermitAltStates();
					wineryLicensePermitAltStates.setWineryPermit(masterDataFromDB);
					wineryLicensePermitAltStates.setWineryId(wineryModelTemp);
					WineryPermitRepository.saveWineryLicensePermitAltStates(wineryLicensePermitAltStates);
				}
			}

			catch (Exception e) {
				logger.info("permit coudn't be created");
				logger.error(e);
			}
		}
		
		//WineryModel wineryModel = getWineryModelByWineryCode(wineryPO.getWineryCode());
		WineryModel wineryModel = getWineryModelByWineryName(wineryPO.getWineryName());
		
		GenericDAO<WineyImpoterModel> genericDao2 = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
		
		Set<ImporterModel> importerModels= wineryModel.getImporters();		
		
		List<Long> importerIds = wineryPO.getImporterIds(); 		
		
		if(importerIds == null)
		{
			importerIds = new ArrayList<Long>();
		}					
		
		List<Long> existingImporterIds = new ArrayList<Long>();
		for(ImporterModel im : importerModels)
		{
			existingImporterIds.add(im.getId());
		}		
		
		Set<Long> allids = new HashSet<Long>(existingImporterIds);			
		allids.addAll(importerIds);					
		List<Long> allidsList = new ArrayList<Long>(allids);				
			
		
		
		
		
		for (Long importerId : allidsList) {			
			
			WineyImpoterModel wineyImpoterModel = WineryRepository.getWineyImpoterModelByImporterIdAndWineryId(importerId,wineryModel.getId());
			
			if(wineyImpoterModel != null)
			{
				if(importerIds.contains(wineyImpoterModel.getImporterId()))
				{	
					//wineyImpoterModel.setIsActiveImpoter(true);
					if(wineryModelTemp.getActiveImporterId() != null && wineryModelTemp.getActiveImporterId().getId() == importerId){
						ImporterModel importerModel = ImporterRepository.getImporterById(wineryPO.getActiveImporterId());
						if(importerModel.getWineryCount() == 1){
							importerModel.setIsActive(false);
							importerModel.setWineryCount(0L);
							ImporterRepository.update(importerModel);
						}
								
						
						
					}
					wineyImpoterModel.setIsDeleted(false);
				}
				else
				{
					//wineyImpoterModel.setIsActiveImpoter(false);						
					wineyImpoterModel.setIsDeleted(true);
				}
				genericDao2.update(wineyImpoterModel);	
				
			}
			else
			{
				wineyImpoterModel = new WineyImpoterModel();	
				
				wineyImpoterModel.setWineryId(wineryModel.getId());
				
				wineyImpoterModel.setImporterId(importerId);			
								
				if(importerId == wineryPO.getActiveImporterId())
					wineyImpoterModel.setIsActiveImpoter(true);
				else
					wineyImpoterModel.setIsActiveImpoter(false);
				
				wineyImpoterModel.setIsDeleted(false);				
								
				genericDao2.create(wineyImpoterModel);
			}
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void update(WineryModel wineryModel) {
		
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
		genericDao.update(wineryModel);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void updateWinery(WineryModel wineryModelTemp,WineryUpdatePO wineryUpdatePO) {
		
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
		
		genericDao.update(wineryModelTemp);		
		
		//WineryModel wineryModel = getWineryModelByWineryCode(wineryUpdatePO.getWineryCode());
		WineryModel wineryModel = getWineryById(wineryUpdatePO.getWineryId());
		
		GenericDAO<WineyImpoterModel> genericDao2 = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
		
		Set<ImporterModel> importerModels= wineryModel.getImporters();		
		
		List<Long> importerIds = wineryUpdatePO.getImporterIds();	
		
		if(importerIds == null)
		{
			importerIds = new ArrayList<Long>();
		}	
		
		List<Long> existingImporterIds = new ArrayList<Long>();
		for(ImporterModel im : importerModels)
		{
			existingImporterIds.add(im.getId());
		}		
		
		Set<Long> allids = new HashSet<Long>(existingImporterIds);			
		allids.addAll(importerIds);					
		List<Long> allidsList = new ArrayList<Long>(allids);				
			
		for (Long importerId : allidsList) {			
			
			WineyImpoterModel wineyImpoterModel = WineryRepository.getWineyImpoterModelByImporterIdAndWineryId(importerId,wineryModel.getId());
			
			if(wineyImpoterModel != null)
			{
				/*if(wineyImpoterModel.getIsDeleted())
				{
					
				}
				else
				{*/
					if(importerIds.contains(wineyImpoterModel.getImporterId()))
					{	
						//wineyImpoterModel.setIsActiveImpoter(true);
						if(wineyImpoterModel.getImporterId() == wineryUpdatePO.getActiveImporterId())
							wineyImpoterModel.setIsActiveImpoter(true);
						
						wineyImpoterModel.setIsDeleted(false);
					}
					else
					{
						//wineyImpoterModel.setIsActiveImpoter(false);						
						wineyImpoterModel.setIsDeleted(true);
						
						if(wineryModelTemp.getActiveImporterId() != null && wineryModelTemp.getActiveImporterId().getId() == importerId){
							ImporterModel importerModel = ImporterRepository.getImporterById(wineryUpdatePO.getActiveImporterId());
							if(importerModel.getWineryCount() == 1){
								importerModel.setIsActive(false);
								importerModel.setWineryCount(0L);
								ImporterRepository.update(importerModel);
							}
									
							
							
						}
					}
					genericDao2.update(wineyImpoterModel);	
				//}
				
			}
			else
			{
				wineyImpoterModel = new WineyImpoterModel();	
				
				wineyImpoterModel.setWineryId(wineryModel.getId());
				
				wineyImpoterModel.setImporterId(importerId);			
								
				if(importerId == wineryUpdatePO.getActiveImporterId())
					wineyImpoterModel.setIsActiveImpoter(true);
				else
					wineyImpoterModel.setIsActiveImpoter(false);
				
				wineyImpoterModel.setIsDeleted(false);				
								
				genericDao2.create(wineyImpoterModel);
			}
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static BulkDeleteModel<WineryModel> delete(List<Long> wineryIds, Boolean isforceDelete) {		
		
		BulkDeleteModel<WineryModel> bulkDeleteModel = new BulkDeleteModel<WineryModel>();
		
		for(Long wineryId : wineryIds)
		{
			WineryModel wineryModel = getWineryById(wineryId);
			if(wineryModel != null)
			{
				GenericDAO<WineyImpoterModel> genericDao = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
				List<WineyImpoterModel> wineyImpoterModels = genericDao.findByNamedQuery(
						"WineyImpoterModel.getImportersForWinery", new String[] {"wineryId"},wineryId);
				
				boolean isDeleted = false;
				
				if (wineyImpoterModels != null && wineyImpoterModels.size() > 0)
				{					
					for(WineyImpoterModel wineyImpoterModel : wineyImpoterModels)
					{				
						if(!wineyImpoterModel.getIsDeleted())
						{								
							isDeleted = true;
							break;
						}
						else
						{								
							isDeleted = false;								
						}											
					}						
					
					if(isDeleted)
					{
						bulkDeleteModel.getNotDeletedList().add(wineryModel);
					}
					else
					{
						bulkDeleteModel.getDeletedList().add(wineryModel);
					}									
				}
				else
				{				
					bulkDeleteModel.getDeletedList().add(wineryModel);
				}
			}
			else
			{
				((List<Long>)bulkDeleteModel.getNotExistsList()).add(wineryId);
			}
		}	
		
		if(isforceDelete || (bulkDeleteModel.getNotDeletedList().isEmpty() && bulkDeleteModel.getNotExistsList().isEmpty()))
		{
			List<WineryModel> wineryModels = bulkDeleteModel.getDeletedList();
			for(WineryModel wineryModel : wineryModels)
			{
				GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
				wineryModel.setIsDeleted(true);
				genericDao.update(wineryModel);
				
			}
		}
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
		genericDao.refresh(wineryIds, WineryModel.class);
		return bulkDeleteModel;
	}
	
	@SuppressWarnings("unchecked")
	public static WineryModel getWineryModelByWineryCode(String wineryCode) {
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<WineryModel> wineryModels = genericDao.findByNamedQuery(
				"WineryModel.getByWineryCode", new String[] { "wineryCode" },
				wineryCode);
		if (wineryModels != null && wineryModels.size() > 0)
			return wineryModels.get(0);
		else
			return null;
	}	
	
	@SuppressWarnings("unchecked")
	public static WineryModel getWineryModelByWineryName(String wineryName) {
		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<WineryModel> wineryModels = genericDao.findByNamedQuery(
				"WineryModel.getByWineryName", new String[] { "wineryName" },
				wineryName);
		if (wineryModels != null && wineryModels.size() > 0)
			return wineryModels.get(0);
		else
			return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public static WineyImpoterModel getWineyImpoterModelByImporterIdAndWineryId(Long importerId,Long wineryId) {
		GenericDAO<WineyImpoterModel> genericDao = (GenericDAO<WineyImpoterModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<WineyImpoterModel> wineyImpoterModels = genericDao.findByNamedQuery(
				"WineyImpoterModel.getByImporterId", new String[] { "importerId","wineryId"},
				importerId,wineryId);
		if (wineyImpoterModels != null && wineyImpoterModels.size() > 0)
			return wineyImpoterModels.get(0);
		else
			return null;
	}
	
	public static WineryModel getWineryById(Long id) {
				GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
				List<WineryModel> wineryModels = genericDao.findByNamedQuery("WineryModel.getById", new String[] { "id" },id);
				if (wineryModels != null && wineryModels.size() > 0)
					return wineryModels.get(0);
				else
					return null;
	}
	
	
	public static List<WineyImpoterModel> getImporters(Long wineryId) {
		GenericDAO<WineyImpoterModel> genericDao = (GenericDAO<WineyImpoterModel>) CoreBeanFactory.getBean("genericDAO");
		List<WineyImpoterModel> wineryModels = genericDao.findByNamedQuery("WineryModel.getImporters", new String[] { "wineryId" },wineryId);
		return wineryModels;
		
	}	
	
	//Get the Winery Contacts based on Winery Id
	public static WineryImporterContacts viewWineryContactDetails(Long wineryId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> wineryContacts = genericDao.findByNamedQuery("WineryImporterContacts.getDefaultContactByWineryId", new String[] {"wineryId"},wineryId);
		if(wineryContacts!=null && !wineryContacts.isEmpty())
			return wineryContacts.get(0);
		else
			return null;
	}
		
	//Get the Importer Name based on Importer Id
	public static String getImporterName(ImporterModel importerModel) {
		GenericDAO<ImporterModel> genericDao = (GenericDAO<ImporterModel>)  CoreBeanFactory.getBean("genericDAO");
		List<ImporterModel> importerModels = genericDao.findByNamedQuery("ImporterModel.getById", new String[] {"id"},importerModel.getId());
		if(importerModels!=null && !importerModels.isEmpty())
			return importerModels.get(0).getImporterName();
		else
			return "";
	}
	
	public static String getWAContactFreightById(Long id) {
		GenericDAO<MasterData> genericDao = (GenericDAO<MasterData>)  CoreBeanFactory.getBean("genericDAO");
		List<MasterData> masterDatas =  genericDao.findByNamedQuery("MasterData.getById",  new String [] {"masterDataId"}, id);
		if(masterDatas != null && masterDatas.size() > 0){
			return masterDatas.get(0).getName();
		}
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public static WineryModel getWineryModelById(Long wineryId) {
	GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory
	.getBean("genericDAO");
	List<WineryModel> wineryModels = genericDao.findByNamedQuery(
	"WineryModel.getById", new String[] { "id" },
	wineryId);
	if (wineryModels != null && wineryModels.size() > 0)
	return wineryModels.get(0);
	else
	return null;
	}
	
	
	/**
	 * this method is used to bulk enable/disable the wineries
	 * @param wineryIds which needs to enable or disable
	 * @param status, need to enable or disable
	 * @param isforceDelete, if need to perform the operation on success list
	 * @return
	 */
	public static BulkDeleteModel<WineryModel> enableDisable(final List<Long> wineryIds, final Boolean status, final Boolean isforceDelete) {

		GenericDAO<WineryModel> genericDao = (GenericDAO<WineryModel>) CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<WineryModel> bulkDeleteModel = new BulkDeleteModel<WineryModel>();
		try {
			if (BooleanUtils.isFalse((Boolean) isforceDelete))
				bulkDeleteModel = genericDao.performBulkDelete(wineryIds,WineryModel.class, "WineryModel", "isEnabled",status, "isDeleted", isforceDelete);
			else {
				BulkDeleteModel<WineryModel> bulkWineryDeleteModel = null;
				for (Serializable id : wineryIds) {
					List<Long> ids = new ArrayList<Long>();
					try {
						ids.add((Long) id);
						bulkWineryDeleteModel = genericDao.performBulkDelete(ids, WineryModel.class, "WineryModel","isEnabled", status, "isDeleted",isforceDelete);
					} catch (Exception e) {

						if (e instanceof PersistenceException) {
							PersistenceException persistenceException = (PersistenceException) e;
							bulkWineryDeleteModel = (BulkDeleteModel<WineryModel>) (persistenceException.getData());
						}
					}finally {
						ApplicationUtils.mergeDeleteResult(bulkDeleteModel.getDeletedList(),bulkWineryDeleteModel.getDeletedList());
						ApplicationUtils.mergeDeleteResult(bulkDeleteModel.getNotDeletedList(),bulkWineryDeleteModel.getNotDeletedList());
						ApplicationUtils.mergeDeleteResult(bulkDeleteModel.getNotExistsList(),bulkWineryDeleteModel.getNotExistsList());
					}
				}
			}
		} catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<WineryModel>) (persistenceException.getData());
			}
		}finally {
			
			genericDao.refresh(wineryIds, WineryModel.class);
			
		}

		return bulkDeleteModel;
	}

}
