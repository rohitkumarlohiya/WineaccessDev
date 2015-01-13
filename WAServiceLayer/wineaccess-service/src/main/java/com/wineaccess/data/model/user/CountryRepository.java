package com.wineaccess.data.model.user;


	
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
public class CountryRepository {

	@PersistenceContext
	EntityManager em;

	public static List<CountryModel> getAll() {
		GenericDAO<CountryModel> genericDao = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("CountryModel.getAll", null, null);
	}

	public static List<CountryModel> getById(Long countryId) {
		GenericDAO<CountryModel> genericDao = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("CountryModel.getById",  new String [] {"countryId"}, countryId);
	}
	
	public static void save(CountryModel countryModel) {
		GenericDAO<CountryModel> genericDao = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(countryModel);
	}
	
	public static void update(CountryModel countryModel) {
		GenericDAO<CountryModel> genericDao = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(countryModel);
	}
	
	public static String getNameById(CountryModel countryModel) {
		GenericDAO<CountryModel> genericDao = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");
		List<CountryModel> countryModels =  genericDao.findByNamedQuery("CountryModel.getById",  new String [] {"countryId"}, countryModel.getId());
		if(countryModels != null && countryModels.size() > 0){
			return countryModels.get(0).getCountryName();
		}
		return "";
	}
	public static CountryModel getByCountryCode(String countryCode) {
		GenericDAO<CountryModel> genericDao = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");
		List<CountryModel> countryModelList =  genericDao.findByNamedQuery("CountryModel.getByCountryCode",  new String [] {"countryCode"}, countryCode);
		CountryModel countryModel = null;
		if(null != countryModelList && countryModelList.size() > 0){
			countryModel = countryModelList.get(0);
		}
		return countryModel;
	}
	
}
