package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.sampler.ProductSamplerModel;
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.data.model.user.UserComments;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author gaurav.agarwal1
 *
 */
public class ProductSamplerRepository {


	public static void save(ProductSamplerModel productSamplerModel) {
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(productSamplerModel);
	}

	public static ProductSamplerModel getByProductAndSampler(Long samplerId, Long productId) {
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		List<ProductSamplerModel> productSamplerModels = genericDao.findByNamedQuery("ProductSamplerModel.getByProductAndSampler", new String[] { "productId", "samplerId" }, productId, samplerId);
		if (productSamplerModels != null && productSamplerModels.size() > 0)
			return productSamplerModels.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public static ProductSamplerModel update(ProductSamplerModel productSamplerModel) {
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(productSamplerModel);
	}	

	@SuppressWarnings("unchecked")
	public static List<ProductSamplerModel> getBySamplerId(Long samplerId) {
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>) CoreBeanFactory.getBean("genericDAO");
		List<ProductSamplerModel> productSamplerModels = genericDao.findByNamedQuery("ProductSamplerModel.getBySamplerId", new String[] { "samplerId" },samplerId);
		if (productSamplerModels != null && productSamplerModels.size() > 0)
			return productSamplerModels;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public static ProductSamplerModel getById(Long id) {
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>) CoreBeanFactory.getBean("genericDAO");
		List<ProductSamplerModel> productSamplerModels = genericDao.findByNamedQuery("ProductSamplerModel.getById", new String[] { "id" },id);
		if (productSamplerModels != null && productSamplerModels.size() > 0)
			return productSamplerModels.get(0);
		else
			return null;
	}

	/*public static  BulkDeleteModel<ProductSamplerModel> delete(SamplerModel samplerModel, Set<Long> productIdSet, List<? extends Serializable> productIds, Boolean isforceDelete) {

		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<ProductSamplerModel> bulkDeleteModel = new BulkDeleteModel<ProductSamplerModel>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<ProductSamplerModel> depenedentList = new ArrayList<ProductSamplerModel>();
		List<ProductSamplerModel> deletedList = new ArrayList<ProductSamplerModel>();

		bulkDeleteModel.setDeletedList(deletedList);
		bulkDeleteModel.setNotDeletedList(depenedentList);
		bulkDeleteModel.setNotExistsList(notExistsList);

		if(null != productIdSet && productIdSet.size() > 0){
			Query query = null;
			for(Serializable productId : productIds){
				if(productIdSet.contains(productId)){
					List<ProductSamplerModel> temp = new ArrayList<ProductSamplerModel>();
					
					temp.addAll(samplerModel.getProductSampler());
					
					
					ProductSamplerModel productSampler = ProductSamplerRepository.getByProductAndSampler(samplerModel.getId(), Long.parseLong(productId.toString()));
					if(isforceDelete.booleanValue()){
						//ProductSamplerRepository.delete(productSampler);
						ProductSamplerModel product = ProductSamplerRepository.getById(productSampler.getId());
						genericDao.delete(product);
						//genericDao.findByQuery("delete from ProductSamplerModel where id= "+productSampler.getId(), 0, 0,null);
						//						query = em.createQuery("delete from product_sampler " + "where id=:id");
						//						query.setParameter("id", productSampler.getId());
						//						query.executeUpdate();
					}
					deletedList.add(productSampler);

				} else{
					notExistsList.add(productId);	
				}
			}
		} else{
			for(Serializable productId: productIds){
				notExistsList.add(productId);	
			}
		}

		if((!isforceDelete.booleanValue()) && ((depenedentList == null  || depenedentList.isEmpty())
				&& (notExistsList == null || notExistsList.isEmpty())) ){
			Query query = null;
			for(ProductSamplerModel model : deletedList){
			//	ProductSamplerRepository.delete(model);
				genericDao.delete(model);
				//				query = em.createQuery("delete from product_sampler " + "where id=:id");
				//				query.setParameter("id", model.getId());
				//				query.executeUpdate();
			}
		}		

		return bulkDeleteModel;
		
	}*/
	
}