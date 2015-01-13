package com.wineaccess.winerypermit;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryLicenseFullfillAltStates;
import com.wineaccess.data.model.catalog.WineryLicensePermitAltStates;
import com.wineaccess.persistence.dao.GenericDAO;

public class WineryPermitRepository {

	@SuppressWarnings("unchecked")
	
	public static void saveWineryLicensePermitAltStates(WineryLicensePermitAltStates wineryLicensePermitAltStates) {
		GenericDAO<WineryLicensePermitAltStates> genericDao = (GenericDAO<WineryLicensePermitAltStates>) CoreBeanFactory.getBean("genericDAO");
		genericDao.update(wineryLicensePermitAltStates);
	}
	
	public static void saveWineryFulfilModel(WineryLicenseFullfillAltStates wineryLicenseFullfillAltStates) {
		GenericDAO<WineryLicenseFullfillAltStates> genericDao = (GenericDAO<WineryLicenseFullfillAltStates>) CoreBeanFactory.getBean("genericDAO");
		genericDao.update(wineryLicenseFullfillAltStates);
	}
	
	public static WineryLicenseFullfillAltStates findFulfilModelByWineryId( Long wineryId) {
		GenericDAO<WineryLicenseFullfillAltStates> genericDao = (GenericDAO<WineryLicenseFullfillAltStates>)  CoreBeanFactory.getBean("genericDAO");
		List<WineryLicenseFullfillAltStates> fulfilModel = genericDao.findByNamedQuery("getFulFilByWineryId", new String [] {"wineryId"}, wineryId);
		if(fulfilModel!=null && !fulfilModel.isEmpty())
			return fulfilModel.get(0);
		else return null;
	}
	
	public static List<WineryLicensePermitAltStates> findWineryLicensePermitAltStates(Long wineryId) {
		GenericDAO<WineryLicensePermitAltStates> genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<WineryLicensePermitAltStates> permitModelList = genericDao.findByNamedQuery("getPermitByWineryId", new String [] {"wineryId"}, wineryId);
		return permitModelList;
	}
	
	public static List findWineryPermitIdByWineryId(Long wineryId) {
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List permitModelList = genericDao.findByNamedQuery("getPermitIdByWineryId", new String [] {"wineryId"}, wineryId);
		return permitModelList;
	}
	
	public static List<String> findDTCPermitNumberByWineryId(Long wineryId) {
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<String> permitModelList = genericDao.findByNamedQuery("getDTCPermitNumberByWineryId", new String [] {"wineryId"}, wineryId);
		return permitModelList;
	}
   
	/*public static WineryLicensePermitAltStates findWineryLicensePermitAltStatesByMasterId(Long wineryId , Long masterDataId) {
		GenericDAO<WineryLicensePermitAltStates> genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<WineryLicensePermitAltStates> permitModelList = genericDao.findByNamedQuery("getPermitByWineryId", new String [] {"wineryId"}, wineryId);
		if(permitModelList!=null && !permitModelList.isEmpty())
		return permitModelList.get(0);
		else
		return null;
	}*/
	

}
