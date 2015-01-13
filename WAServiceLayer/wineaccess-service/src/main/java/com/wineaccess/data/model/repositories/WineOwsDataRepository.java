package com.wineaccess.data.model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.wine.WineHistoricalOWSData;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author gaurav.agarwal1
 *
 */
public class WineOwsDataRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public static void save(WineHistoricalOWSData historicalOwsData) {
		GenericDAO<WineHistoricalOWSData> genericDao = (GenericDAO<WineHistoricalOWSData>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(historicalOwsData);
	}
	
	public static WineHistoricalOWSData getOwsDataByWineId(Long wineId) {
		GenericDAO<WineHistoricalOWSData> genericDao = (GenericDAO<WineHistoricalOWSData>) CoreBeanFactory.getBean("genericDAO");
		List<WineHistoricalOWSData> historicalOwsDatas = genericDao.findByNamedQuery("WineHistoricalOWSData.getByWineId", new String[] { "wineId" },wineId);
		if (historicalOwsDatas != null && historicalOwsDatas.size() > 0)
			return historicalOwsDatas.get(0);
		else
			return null;
	}
	
	public static WineHistoricalOWSData update(WineHistoricalOWSData historicalOwsData) {
		GenericDAO<WineHistoricalOWSData> genericDao = (GenericDAO<WineHistoricalOWSData>) CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(historicalOwsData);
	}

}
