package com.wineaccess.winepermit;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineLicenseFullfillAltStates;
import com.wineaccess.data.model.catalog.WineLicenseNoPermit;
import com.wineaccess.data.model.catalog.WineLicensePermitAltStates;
import com.wineaccess.persistence.dao.GenericDAO;

public class WinePermitRepository {

    @SuppressWarnings("unchecked")

    public static void saveWineLicensePermitAltStates(WineLicensePermitAltStates wineLicensePermitAltStates) {
	GenericDAO<WineLicensePermitAltStates> genericDao = (GenericDAO<WineLicensePermitAltStates>) CoreBeanFactory.getBean("genericDAO");
	genericDao.update(wineLicensePermitAltStates);
    }
    
    public static void saveWineLicenseNoPermit(WineLicenseNoPermit wineLicenseNoPermit) {
	GenericDAO<WineLicenseNoPermit> genericDao = (GenericDAO<WineLicenseNoPermit>) CoreBeanFactory.getBean("genericDAO");
	genericDao.update(wineLicenseNoPermit);
    }

    public static void saveWineFulfilModel(WineLicenseFullfillAltStates wineLicenseFullfillAltStates) {
	GenericDAO<WineLicenseFullfillAltStates> genericDao = (GenericDAO<WineLicenseFullfillAltStates>) CoreBeanFactory.getBean("genericDAO");
	genericDao.update(wineLicenseFullfillAltStates);
    }

    public static WineLicenseFullfillAltStates findFulfilModelByWineId( Long wineId) {
	GenericDAO<WineLicenseFullfillAltStates> genericDao = (GenericDAO<WineLicenseFullfillAltStates>)  CoreBeanFactory.getBean("genericDAO");
	List<WineLicenseFullfillAltStates> fulfilModel = genericDao.findByNamedQuery("getFulFilByWineId", new String [] {"wineId"}, wineId);
	if(fulfilModel!=null && !fulfilModel.isEmpty())
	    return fulfilModel.get(0);
	else return null;
    }

    public static List<WineLicensePermitAltStates> findWineLicensePermitAltStates(Long wineId) {
	GenericDAO<WineLicensePermitAltStates> genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<WineLicensePermitAltStates> permitModelList = genericDao.findByNamedQuery("getPermitByWineId", new String [] {"wineId"}, wineId);
	return permitModelList;
    }

    public static List findWinePermitIdByWineId(Long wineId) {
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List permitModelList = genericDao.findByNamedQuery("getPermitIdByWineId", new String [] {"wineId"}, wineId);
	return permitModelList;
    }
    
    public static List findWineNoPermitIdByWineId(Long wineId) {
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List permitModelList = genericDao.findByNamedQuery("getNoPermitIdByWineId", new String [] {"wineId"}, wineId);
	return permitModelList;
    }

    public static List<String> findDTCPermitNumberByWineId(Long wineId) {
	GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	List<String> permitModelList = genericDao.findByNamedQuery("getDTCPermitNumberByWineId", new String [] {"wineId"}, wineId);
	return permitModelList;
    }

    public static List<WineLicenseNoPermit> findWineLicenseNoPermitAltStates(Long wineId) {
   	GenericDAO<WineLicenseNoPermit> genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
   	List<WineLicenseNoPermit> permitModelList = genericDao.findByNamedQuery("getNoPermitByWineId", new String [] {"wineId"}, wineId);
   	return permitModelList;
       }


}
