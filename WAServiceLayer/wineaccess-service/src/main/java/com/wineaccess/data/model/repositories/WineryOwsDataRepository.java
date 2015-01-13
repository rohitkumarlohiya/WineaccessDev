package com.wineaccess.data.model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryHistoricalOwsData;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author gaurav.agarwal1
 *
 */
public class WineryOwsDataRepository {
	@PersistenceContext
	EntityManager em;
	
	public static void save(WineryHistoricalOwsData historicalOwsData) {
		GenericDAO<WineryHistoricalOwsData> genericDao = (GenericDAO<WineryHistoricalOwsData>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(historicalOwsData);
	}
	
	public static WineryHistoricalOwsData getOwsDataByWineryId(Long wineryId) {
		GenericDAO<WineryHistoricalOwsData> genericDao = (GenericDAO<WineryHistoricalOwsData>) CoreBeanFactory.getBean("genericDAO");
		List<WineryHistoricalOwsData> historicalOwsDatas = genericDao.findByNamedQuery("WineryHistoricalOwsData.getByWineryId", new String[] { "wineryid" },wineryId);
		if (historicalOwsDatas != null && historicalOwsDatas.size() > 0)
			return historicalOwsDatas.get(0);
		else
			return null;
	}
	
	public static WineryHistoricalOwsData update(WineryHistoricalOwsData historicalOwsData) {
		GenericDAO<WineryHistoricalOwsData> genericDao = (GenericDAO<WineryHistoricalOwsData>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(historicalOwsData);
	}

}
