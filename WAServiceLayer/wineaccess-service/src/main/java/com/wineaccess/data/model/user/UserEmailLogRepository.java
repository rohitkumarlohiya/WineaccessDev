/**
 * 
 */
package com.wineaccess.data.model.user;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author rohit.lohiya
 *
 */
public class UserEmailLogRepository {
	
	@SuppressWarnings("unchecked")
	public static void save(UserEmailLog userEmailLog) {
		GenericDAO<UserEmailLog> genericDao = (GenericDAO<UserEmailLog>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(userEmailLog);
	}
	
	@SuppressWarnings("unchecked")
	public static List<UserEmailLog> getUserEmailLogs() {

		GenericDAO<UserEmailLog> genericDao = (GenericDAO<UserEmailLog>) CoreBeanFactory
				.getBean("genericDAO");

		return genericDao.findByNamedQuery("UserEmailLog.getAll", null, null);

	}
	
	@SuppressWarnings("unchecked")
	public static List<UserEmailLog> getByKeyword(Class clazz,
			final String fieldName, final String keyword, final String sortBy,
			final Integer offset, final Integer limit, final Integer sortOrder) {

		GenericDAO<UserEmailLog> genericDao = (GenericDAO<UserEmailLog>) CoreBeanFactory
				.getBean("genericDAO");

		List<UserEmailLog> userEmailLogs = genericDao.getSearch(clazz,
				fieldName, keyword, sortBy, sortOrder, offset, limit);

		return userEmailLogs;
	}

}
