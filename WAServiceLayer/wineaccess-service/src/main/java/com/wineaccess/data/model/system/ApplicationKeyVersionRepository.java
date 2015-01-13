package com.wineaccess.data.model.system;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

public class ApplicationKeyVersionRepository {
	
	public static List<ApplicationKeyVersion> getByApiKeyAndVersion(String apiKey, String apiVerion) {
		GenericDAO<ApplicationKeyVersion> genericDao = (GenericDAO<ApplicationKeyVersion>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getByApiKeyAndVersion", new String [] {"apiKey", "apiVersion"}, apiKey, apiVerion);
	}
	
	public static void create(ApplicationKeyVersion applicationKeyVersion) {
		GenericDAO<ApplicationKeyVersion> genericDao = (GenericDAO<ApplicationKeyVersion>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(applicationKeyVersion);
	}
}
