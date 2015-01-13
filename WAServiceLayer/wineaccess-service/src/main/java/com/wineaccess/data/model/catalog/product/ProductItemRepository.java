package com.wineaccess.data.model.catalog.product;

import java.util.ArrayList;
import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

public class ProductItemRepository {
	
	@SuppressWarnings("unchecked")
	public static void save(ProductItemModel productItemModel) {
		GenericDAO<ProductItemModel> genericDao = (GenericDAO<ProductItemModel>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(productItemModel);
	}

	@SuppressWarnings("unchecked")
	public static ProductItemModel getProductItemById(Long id) {
		GenericDAO<ProductItemModel> genericDao = (GenericDAO<ProductItemModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<ProductItemModel> productItemModels = genericDao.findByNamedQuery(
				"ProductItemModel.getById", new String[] { "id" }, id);
		if (productItemModels != null && productItemModels.size() > 0)
			return productItemModels.get(0);
		else
			return null;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Long> getWineIds(List<Long> productIds,List<Long> notExistProductIds) {
		
		GenericDAO<ProductItemModel> genericDao = (GenericDAO<ProductItemModel>) CoreBeanFactory
				.getBean("genericDAO");		
				
		List<ProductItemModel> productItemModels = genericDao.getProductItemList(productIds);
		
		List<Long> wineIds = new ArrayList<Long>();
		//notExistProductIds = new ArrayList<Long>(productIds);
		
		for(ProductItemModel productItemModel : productItemModels)
		{
			wineIds.add(productItemModel.getItemId());	
			if(notExistProductIds != null)
			{
				notExistProductIds.remove(productItemModel.getId());
			}
		}
		return wineIds;
	}
	
	public static ProductItemModel getByIdAndProduct(Long id, Long productId) {
		GenericDAO<ProductItemModel> genericDao = (GenericDAO<ProductItemModel>) CoreBeanFactory.getBean("genericDAO");
		List<ProductItemModel> productItemModels = genericDao.findByNamedQuery("ProductItemModel.getByIdAndProduct", new String[] { "id","productId" }, id, productId);
		if (productItemModels != null && productItemModels.size() > 0)
			return productItemModels.get(0);
		else
			return null;
	}
}
