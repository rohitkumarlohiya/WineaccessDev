package com.wineaccess.data.model.user;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
public class CityRepository {

	@PersistenceContext
	EntityManager em;

	public static List<CityModel> getAll() {
		GenericDAO<CityModel> genericDao = (GenericDAO<CityModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("CityModel.getAll", null, null);
	}
	
	public static List<CityModel> getById(Long cityId) {
		GenericDAO<CityModel> genericDao = (GenericDAO<CityModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("CityModel.getById", new String [] {"cityId"}, cityId);
	}

	public static List<CityModel> getByStateId(Long stateId) {
		GenericDAO<CityModel> genericDao = (GenericDAO<CityModel>)  CoreBeanFactory.getBean("genericDAO");
		Set<CityModel> citis = genericDao.getCities(stateId);
		List<CityModel> citiList = new ArrayList<CityModel>();
		for(CityModel city : citis){
			citiList.add(city);
		}
			return citiList;
	}
	
	public static void save(CityModel cityModel) {
		GenericDAO<CityModel> genericDao = (GenericDAO<CityModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(cityModel);
	}
	
}
