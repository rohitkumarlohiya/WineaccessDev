package com.wineaccess.data.model.user;

	
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
public class StateRepository {

	@PersistenceContext
	EntityManager em;

	public static List<StateModel> getAll() {
		GenericDAO<StateModel> genericDao = (GenericDAO<StateModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("StateModel.getAll", null, null);
	}

	public static List<StateModel> getById(Long stateId) {
		GenericDAO<StateModel> genericDao = (GenericDAO<StateModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("StateModel.getById",  new String [] {"stateId"}, stateId);
	}
	
	public static List<StateModel> getByCountryId(Long countryId) {
		GenericDAO<StateModel> genericDao = (GenericDAO<StateModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("StateModel.getByCountryId",  new String [] {"countryId"}, countryId);
	}
	
	public static void save(StateModel stateModel) {
		GenericDAO<StateModel> genericDao = (GenericDAO<StateModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(stateModel);
	}
	
	public static String getNameById(StateModel stateModel) {
		GenericDAO<StateModel> genericDao = (GenericDAO<StateModel>)  CoreBeanFactory.getBean("genericDAO");
		List<StateModel> stateModels =  genericDao.findByNamedQuery("StateModel.getById",  new String [] {"stateId"}, stateModel.getId());
		if(stateModels != null && stateModels.size() > 0){
			return stateModels.get(0).getStateName();
		}
		return "";
	}
}

