package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.sampler.ProductSamplerModel;
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author gaurav.agarwal1
 *
 */
public class SamplerRepository {

	@PersistenceContext
	EntityManager em;


	public static void save(SamplerModel samplerModel) {
		GenericDAO<SamplerModel> genericDao = (GenericDAO<SamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(samplerModel);
	}

	public static SamplerModel getSamplerByName(String name) {
		GenericDAO<SamplerModel> genericDao = (GenericDAO<SamplerModel>) CoreBeanFactory.getBean("genericDAO");
		List<SamplerModel> samplerModels = genericDao.findByNamedQuery("SamplerModel.getByName", new String[] { "name" }, name);
		if (samplerModels != null && samplerModels.size() > 0)
			return samplerModels.get(0);
		else
			return null;
	}

	public static SamplerModel getSamplerById(Long id) {
		GenericDAO<SamplerModel> genericDao = (GenericDAO<SamplerModel>) CoreBeanFactory.getBean("genericDAO");
		List<SamplerModel> samplerModels = genericDao.findByNamedQuery("SamplerModel.getById", new String[] { "id" }, id);
		if (samplerModels != null && samplerModels.size() > 0)
			return samplerModels.get(0);
		else
			return null;
	}

	public static List<SamplerModel> getAllSampler() {
		GenericDAO<SamplerModel> genericDao = (GenericDAO<SamplerModel>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("SamplerModel.getAll", null, null);
	}

	public static void update(SamplerModel samplerModel) {
		GenericDAO<SamplerModel> genericDao = (GenericDAO<SamplerModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(samplerModel);
	}

	@SuppressWarnings("unchecked")
	public static List<ProductSamplerModel> listSamplerProduct(Long samplerId, String sortBy, int sortOrder, int offset, int limit) {
		
		String query = "from ProductSamplerModel where samplerId.id="+samplerId;
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>) CoreBeanFactory.getBean("genericDAO");
		if(sortBy.equals("productName") || sortBy.equals("quantity")){
			sortBy = "id";
			limit = -1;
		}
		return genericDao.findByQuery(query, offset-1, limit, generateSortingCriteria(sortBy, sortOrder));
	}
	
	@SuppressWarnings("unchecked")
	public static Integer getSamplerProductCount(Long samplerId, int sortOrder) {
		
		String query = "from ProductSamplerModel where samplerId.id="+samplerId;
		GenericDAO<ProductSamplerModel> genericDao = (GenericDAO<ProductSamplerModel>) CoreBeanFactory.getBean("genericDAO");
		int limit = -1;
		int offset = 1;
		String sortBy = "id";
		List<ProductSamplerModel> productSamplerModelList = genericDao.findByQuery(query, offset-1, limit, generateSortingCriteria(sortBy, sortOrder));
		int count = 0;
		if(null != productSamplerModelList){
			count = productSamplerModelList.size();
		}
		return count;
	}

	public static String generateSortingCriteria(String fieldName, int sortOrder) {
	
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
	}

}
