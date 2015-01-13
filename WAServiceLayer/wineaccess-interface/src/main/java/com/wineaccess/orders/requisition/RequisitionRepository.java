package com.wineaccess.orders.requisition;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryImporterAddressModel;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author gaurav.agarwal1
 *
 */
public class RequisitionRepository {

	public static void save(RequisitionModel requisitionModel) {
		GenericDAO<RequisitionModel> genericDao = (GenericDAO<RequisitionModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(requisitionModel);
	}

	public static RequisitionModel findById(Long id) {
		GenericDAO<RequisitionModel> genericDao = (GenericDAO<RequisitionModel>) CoreBeanFactory.getBean("genericDAO");
		List<RequisitionModel> poModel = genericDao.findByNamedQuery("RequisitionModel.getById", new String[] {"id"}, id);
		if (poModel != null && poModel.size() > 0)
			return poModel.get(0);
		else
			return null;
	}

	public static Integer getWarehouseCount(Long id) {
		GenericDAO<RequisitionModel> genericDao = (GenericDAO<RequisitionModel>) CoreBeanFactory.getBean("genericDAO");
		List<RequisitionModel> poModel = genericDao.findByNamedQuery("RequisitionModel.getWarehouseCount", new String[] { "id" }, id);
		Integer count = 0;
		if(poModel != null){
			count = poModel.size(); 
		}
		return count;
	}	

	@SuppressWarnings("unchecked")
	public static RequisitionModel update(RequisitionModel requisitionModel) {
		GenericDAO<RequisitionModel> genericDao = (GenericDAO<RequisitionModel>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(requisitionModel);
	}

	public static WineryImporterAddressModel getByWineryIdIsDefault(Long wineryId) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelList = genericDao.findByNamedQuery("findByWineryId", new String [] {"wineryId"}, wineryId);
		if(addressModelList!=null && !addressModelList.isEmpty()){
			for(WineryImporterAddressModel addressModel : addressModelList){
				if(addressModel.getIsDefault()){
					return addressModel;
				}
			}
		}	
		return null;
	}

	public static WineryImporterAddressModel getByImporterIdIsDefault(Long importerId) {
		GenericDAO<WineryImporterAddressModel> genericDao = (GenericDAO<WineryImporterAddressModel>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterAddressModel> addressModelList = genericDao.findByNamedQuery("findByImporterId", new String [] {"importerId"}, importerId);
		if(addressModelList!=null && !addressModelList.isEmpty()){
			for(WineryImporterAddressModel addressModel : addressModelList){
				if(addressModel.getIsDefault()){
					return addressModel;
				}
			}
		}	
		return null;
	}

	public static WineryImporterContacts getContactByWineryIdIsDefault(Long wineryId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> contactModelList = genericDao.findByNamedQuery("WineryImporterContacts.getDefaultContactByWineryId", new String [] {"wineryId"}, wineryId);
		if(contactModelList!=null && !contactModelList.isEmpty())
			return contactModelList.get(0);
		else 
			return null;
	}

	public static WineryImporterContacts getContactByImporterIdIsDefault(Long importerId) {
		GenericDAO<WineryImporterContacts> genericDao = (GenericDAO<WineryImporterContacts>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryImporterContacts> contactModelList = genericDao.findByNamedQuery("WineryImporterContacts.getByImporterId", new String [] {"importerId"}, importerId);
		if(contactModelList!=null && !contactModelList.isEmpty())
			return contactModelList.get(0);
		else 
			return null;
	}


	public static BulkDeleteModel<RequisitionModel> delete(List<? extends Serializable> requisitionIds,Boolean isForceDelete,Map<String,String> dependantFieldsMap) {

		GenericDAO<RequisitionModel> genericDao = (GenericDAO<RequisitionModel>)  CoreBeanFactory.getBean("genericDAO");
		BulkDeleteModel<RequisitionModel> bulkDeleteModel = new BulkDeleteModel<RequisitionModel>();

		List<Serializable> notExistsList = new ArrayList<Serializable>();
		List<RequisitionModel> depenedentList = new ArrayList<RequisitionModel>();
		List<RequisitionModel> deletedList = new ArrayList<RequisitionModel>();

		bulkDeleteModel.setDeletedList(deletedList);
		bulkDeleteModel.setNotDeletedList(depenedentList);
		bulkDeleteModel.setNotExistsList(notExistsList);

		for(Serializable requisitionId : requisitionIds){
			RequisitionModel requisition = RequisitionRepository.findById((Long)requisitionId);
			if(requisition == null){
				notExistsList.add(requisitionId);
				continue;
			}else{
				if(BooleanUtils.isTrue(requisition.getIsDeleted())){
					notExistsList.add(requisition.getId());
					continue;
				}else{

					if(!isDeletionValid(requisition,dependantFieldsMap)){
						depenedentList.add(requisition);
					} else{
						deletedList.add(requisition);
						if(isForceDelete){
							requisition.setIsDeleted(true);
							RequisitionRepository.update(requisition);
						}
					}
				}
			}
		}

		if((!isForceDelete) && ((depenedentList == null  || depenedentList.isEmpty())
				&& (notExistsList == null || notExistsList.isEmpty())) ){
			for(RequisitionModel model : deletedList){
				model.setIsDeleted(true);
				RequisitionRepository.update(model);
			}
		}
		return bulkDeleteModel;
	}

	private static Boolean isDeletionValid(RequisitionModel model,Map<String,?> dependantFieldsMap){
		boolean isDependent = false;

		for(String dependantField: dependantFieldsMap.keySet())
		{
			Object object = new Object();
			try {
				object = new PropertyDescriptor(
						dependantField, RequisitionModel.class).getReadMethod()
						.invoke(model);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dependantFieldsMap.get(dependantField).equals(object))
				isDependent = true;
		}
		return isDependent;
	}
}