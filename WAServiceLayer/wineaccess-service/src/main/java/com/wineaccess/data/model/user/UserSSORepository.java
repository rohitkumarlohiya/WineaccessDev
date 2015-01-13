package com.wineaccess.data.model.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;


public class UserSSORepository {
	
	@PersistenceContext
	EntityManager em;
	

	
	public static void save(UserSSO userSSOModel) {
		GenericDAO<UserSSO> genericDao = (GenericDAO<UserSSO>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(userSSOModel);
	}
}
