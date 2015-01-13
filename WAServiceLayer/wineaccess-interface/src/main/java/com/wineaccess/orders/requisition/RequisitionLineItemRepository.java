package com.wineaccess.orders.requisition;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.product.ProductItemRepository;
import com.wineaccess.data.model.catalog.requisition.REQLineItemsModel;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author abhishek.sharma1
 *
 */
public class RequisitionLineItemRepository {

	/*  final static String[] searchFields = new String[]{"addressType","phone"};*/


	final static String[] sortFields = new String[]{"id","wineName", "bottlesCount" ,"unitPrice", "costPerBox"};

	/**
	 * @param purchageOrderLineItemsModel
	 */
	public static void save(REQLineItemsModel purchageOrderLineItemsModel) {
		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(purchageOrderLineItemsModel);
	}
	
	@SuppressWarnings("unchecked")
	public static REQLineItemsModel update(REQLineItemsModel reqLineItemsModel) {
		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(reqLineItemsModel);
	}

	/**
	 * @param poMasterId
	 * @param wineId
	 * @return
	 */
	public static REQLineItemsModel findByIdProductId(Long poMasterId, Long wineId) {
		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>) CoreBeanFactory.getBean("genericDAO");
		List<REQLineItemsModel> poLineItemModel = genericDao.findByNamedQuery("REQLineItemsModel.getByIdProductId", new String[] { "poMasterId","wineId" }, poMasterId,wineId);
		if (poLineItemModel != null && poLineItemModel.size() > 0)
			return poLineItemModel.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public static REQLineItemsModel getByIdAndRequisitionId(Long requisitionId, Long reqLineItemId) {
		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>) CoreBeanFactory.getBean("genericDAO");
		List<REQLineItemsModel> reqLineItemsModel = genericDao.findByNamedQuery("REQLineItemsModel.getByIdAndRequisitionId", new String[] { "requisitionId","reqLineItemId" }, requisitionId,reqLineItemId);
		if (reqLineItemsModel != null && reqLineItemsModel.size() > 0)
			return reqLineItemsModel.get(0);
		else
			return null;
	}
	
	public static BulkDeleteModel<REQLineItemsModel> removeWineFromRequisition(
			List<? extends Serializable> productIds, Long requisitionId,
			Boolean isforceDelete, Map<String, String> dependantFieldsMap) {

		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>) CoreBeanFactory
				.getBean("genericDAO");
		BulkDeleteModel<REQLineItemsModel> bulkDeleteModel = new BulkDeleteModel<REQLineItemsModel>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<REQLineItemsModel> depenedentList = new ArrayList<REQLineItemsModel>();
		List<REQLineItemsModel> deletedList = new ArrayList<REQLineItemsModel>();

		bulkDeleteModel.setDeletedList(deletedList);
		bulkDeleteModel.setNotDeletedList(depenedentList);
		bulkDeleteModel.setNotExistsList(notExistsList);

		for (Serializable productId : productIds) {

			ProductItemModel productItemModel = ProductItemRepository
					.getProductItemById((Long) productId);
			if (productItemModel == null) {
				notExistsList.add(productId);
			} else {
				Long wineId = productItemModel.getItemId();
				REQLineItemsModel lineItemsModel = findByIdProductId(
						requisitionId, wineId);
				if (lineItemsModel == null) {
					notExistsList.add(productId);
				} else {
					Serializable existingProductId = lineItemsModel.getId();
					REQLineItemsModel itemsModel = genericDao.get(
							REQLineItemsModel.class, existingProductId);
					if (isDeletionValid(itemsModel.getRequisition(),
							dependantFieldsMap)) {
						depenedentList.add(itemsModel);
					} else {
						deletedList.add(itemsModel);
						if (isforceDelete) {
							genericDao
									.executeUpdateNamedQuery(
											"REQLineItemsModel.removeWineFromRequisition",
											new String[] { "requisitionId",
													"wineId" }, requisitionId,
											itemsModel.getWine().getId());
						}
					}

				}
			}
		}
		if ((!isforceDelete)
				&& ((depenedentList == null || depenedentList.isEmpty()) && (notExistsList == null || notExistsList
						.isEmpty()))) {
			for (REQLineItemsModel model : deletedList) {
				genericDao.executeUpdateNamedQuery(
						"REQLineItemsModel.removeWineFromRequisition",
						new String[] { "requisitionId", "wineId" },
						requisitionId, model.getWine().getId());
			}
		}
		return bulkDeleteModel;
	}
	
	private static Boolean isDeletionValid(RequisitionModel model,Map<String,?> dependantFieldsMap){
		boolean isDependent = true;

		for(String dependantField: dependantFieldsMap.keySet())
		{
			Object object = new Object();
			try {
				object = new PropertyDescriptor(dependantField, RequisitionModel.class).getReadMethod().invoke(model);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dependantFieldsMap.get(dependantField).equals(object))
				isDependent = false;
		}
		return isDependent;
	}

	/**
	 * @param requistionId 
	 * @return
	 */
	public static int countTotalRecords(Long  requisitionId) {

		String query = "select count(*) from REQLineItemsModel where requisition.id ="+ requisitionId;
		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>) CoreBeanFactory.getBean("genericDAO");

		return (genericDao.executeCountQuery(query).intValue());
	}

	/**
	 * @param requistionId 
	 * @param sortOrder
	 * @param offset
	 * @param limit
	 * @param sortBy
	 * @param exclusionsList 
	 * @return
	 */
	public static List<REQLineItemsModel> getWinesList(Long requisitionId, int sortOrder, int offset, int limit, List<Long> exclusionsList) {
		
		String query = "from REQLineItemsModel where requisition.id ="+ requisitionId;
		
		GenericDAO<REQLineItemsModel> genericDao = (GenericDAO<REQLineItemsModel>) CoreBeanFactory.getBean("genericDAO");

		if (CollectionUtils.isNotEmpty(exclusionsList)) {
			query += " and id not in :exclusionList"; 
			return genericDao.findByQuery(query, offset, limit, null, new String[]{"exclusionList"}, exclusionsList);
		}
		else
			return genericDao.findByQuery(query, offset, limit, null);
	}

	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		String orderBy = "";
		if (fieldName.equals(sortFields[0])) {
			orderBy = sortFields[0];
		} else if (fieldName.equals(sortFields[1])) {
			orderBy = sortFields[1];
		} else if (fieldName.equals(sortFields[2])) {
			orderBy = sortFields[2];
		} 
		else if (fieldName.equals(sortFields[3])) {
			orderBy = sortFields[3];
		} 
		else if (fieldName.equals(sortFields[4])) {
			orderBy = sortFields[4];
		} 
		else {
			orderBy = sortFields[0];
		}

		if(orderBy == "wineName")
			orderBy = "wineId.wineFullName";
		StringBuilder sortCriteria = new StringBuilder(" Order by " + orderBy);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();

	}


}
