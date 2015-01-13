package com.wineaccess.inventory;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.inventory.InventoryModel;
import com.wineaccess.persistence.dao.GenericDAO;

public class InventoryRepository {

	@SuppressWarnings("unchecked")
	public static void save(InventoryModel inventoryModel) {
		GenericDAO<InventoryModel> genericDao = (GenericDAO<InventoryModel>) CoreBeanFactory.getBean("genericDAO");
		genericDao.create(inventoryModel);
	}

	@SuppressWarnings("unchecked")
	public static InventoryModel getInventoryById(Long id) {
		GenericDAO<InventoryModel> genericDao = (GenericDAO<InventoryModel>) CoreBeanFactory.getBean("genericDAO");
		List<InventoryModel> inventoryModels = genericDao.findByNamedQuery("InventoryModel.getById", new String[] { "id" }, id);
		if (inventoryModels != null && inventoryModels.size() > 0)
			return inventoryModels.get(0);
		else
			return null;
	}	
	
	@SuppressWarnings("unchecked")
	public static InventoryModel update(InventoryModel inventoryModel) {
		GenericDAO<InventoryModel> genericDao = (GenericDAO<InventoryModel>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(inventoryModel);
	}	
	
	@SuppressWarnings("unchecked")
	public static InventoryModel getInventoryByWineId(Long wineId) {
		GenericDAO<InventoryModel> genericDao = (GenericDAO<InventoryModel>) CoreBeanFactory.getBean("genericDAO");
		List<InventoryModel> inventoryModels = genericDao.findByNamedQuery("InventoryModel.getByWineId", new String[] { "wineId" }, wineId);
		if (inventoryModels != null && inventoryModels.size() > 0)
			return inventoryModels.get(0);
		else
			return null;
	}	
}
