package com.wineaccess.distributioncentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionSystemException;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;

/**
 * @author gaurav.agarwal1
 *
 */
public class DistributionCentreRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public static void save(DistributionCentre distributionCentre) {
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(distributionCentre);
	}
	
	public static void update(DistributionCentre distributionCentre) {
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(distributionCentre);
	}
	
	public static DistributionCentre getById(Long id) {
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		List<DistributionCentre> listDistributionCentre = genericDao.findByNamedQuery("DistributionCentre.getById", new String[] { "id" }, id);
		if (listDistributionCentre != null && listDistributionCentre.size() > 0)
			return listDistributionCentre.get(0);
		else
			return null;
	}
	
	public static DistributionCentre getByUniqueAddressHash(String uniqueHash) {
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		List<DistributionCentre> listDistributionCentre = genericDao.findByNamedQuery("DistributionCentre.getByUniqueAddressHash", new String[] { "uniqueHash" }, uniqueHash);
		if (listDistributionCentre != null && listDistributionCentre.size() > 0)
			return listDistributionCentre.get(0);
		else
			return null;
	}
	
	/**
	 * this method is used to bulk delete the locations
	 * @param ids which needs to delete
	 * @param isforceDelete, if need to perform the operation on success list
	 * @return
	 */
	public static BulkDeleteModel<DistributionCentre> delete(final List<Long> idList, final Boolean isforceDelete) {

		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>) CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<DistributionCentre> bulkDeleteModel = new BulkDeleteModel<DistributionCentre>();
		try {
			if (BooleanUtils.isFalse((Boolean) isforceDelete))
				bulkDeleteModel = genericDao.performBulkDelete(idList,DistributionCentre.class, "DistributionCentre", "isDeleted",true, "isDeleted", isforceDelete);
			else {
				BulkDeleteModel<DistributionCentre> bulkDistributionCentreDeleteModel = null;
				for (Serializable id : idList) {
					List<Long> ids = new ArrayList<Long>();
					try {
						ids.add((Long) id);
						bulkDistributionCentreDeleteModel = genericDao.performBulkDelete(ids,DistributionCentre.class, "DistributionCentre", "isDeleted",true, "isDeleted", isforceDelete);
					} catch (Exception e) {
						if (e instanceof TransactionSystemException) {
							bulkDistributionCentreDeleteModel = new BulkDeleteModel<DistributionCentre>();
							List<DistributionCentre> depenedentList = new ArrayList<DistributionCentre>();
							bulkDistributionCentreDeleteModel.setNotDeletedList(depenedentList);
							depenedentList.add(genericDao.get(DistributionCentre.class, id));
						}
						
						if (e instanceof PersistenceException) {
							PersistenceException persistenceException = (PersistenceException) e;
							bulkDistributionCentreDeleteModel = (BulkDeleteModel<DistributionCentre>) (persistenceException.getData());
						}
					}finally {
						ApplicationUtils.mergeDeleteResult(bulkDeleteModel.getDeletedList(),bulkDistributionCentreDeleteModel.getDeletedList());
						ApplicationUtils.mergeDeleteResult(bulkDeleteModel.getNotDeletedList(),bulkDistributionCentreDeleteModel.getNotDeletedList());
						ApplicationUtils.mergeDeleteResult(bulkDeleteModel.getNotExistsList(),bulkDistributionCentreDeleteModel.getNotExistsList());
					}
				}
			}
			genericDao.refresh(idList, DistributionCentre.class);
		} catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<DistributionCentre>) (persistenceException.getData());
			}
		}

		return bulkDeleteModel;
	}
	
	public static List<DistributionCentre> getAllByOffsetLimit(Long locationId,Integer offset, Integer limit) {
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		if(locationId == null){
			return genericDao.findByQuery("from DistributionCentre where isDeleted=false", offset, limit,null);
		}else{
			return genericDao.findByQuery("from DistributionCentre where stateId = "+locationId+ " and isDeleted=false", offset, limit,null);
		}
	}
	
	public static int getTotalCount() {
		String query = StringUtils.EMPTY;
		query = "select count(*) from DistributionCentre where isDeleted=false";
		
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		return (genericDao.executeCountQuery(query)).intValue();
	}
	
	public static DistributionCentre getByLocationId(Long locationId) {
		GenericDAO<DistributionCentre> genericDao = (GenericDAO<DistributionCentre>)  CoreBeanFactory.getBean("genericDAO");
		List<DistributionCentre> listDistributionCentre = genericDao.findByNamedQuery("DistributionCentre.getByLocationId", new String[] { "locationId" }, locationId);
		if (listDistributionCentre != null && listDistributionCentre.size() > 0)
			return listDistributionCentre.get(0);
		else
			return null;
	}

}
