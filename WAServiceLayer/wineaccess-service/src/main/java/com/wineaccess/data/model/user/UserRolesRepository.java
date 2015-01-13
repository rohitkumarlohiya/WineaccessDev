package com.wineaccess.data.model.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * 
 * @author rohit.lohiya
 *
 */
public class UserRolesRepository {

	
	@PersistenceContext
	EntityManager em;
	
	
	public static List<UserRoles> getByRoleId(int roleId) {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");		
		return genericDao.findByNamedQuery("UserRoles.getByRoleId", new String [] {"roleId"}, new Long(roleId));		
	}
		
	public static List<UserRoles> getByRoleName(String roleName) {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("UserRoles.getByRoleName", new String [] {"roleName"}, roleName);
	}
	
	public static List<UserRoles> getAll() {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("UserRoles.getAll", null, null);
	}
	public static void save(UserRoles userRoles) {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(userRoles);
	}
	
	public static void update(UserRoles userRoles) {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(userRoles);
	}
	
	public static void delete(UserRoles userRoles) {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
		userRoles.setStatus(false);
		genericDao.update(userRoles);
	}
	
	public static List<UserRoles> getByUserId(Long userId) {
		GenericDAO<UserRoles> genericDao = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("UserRoles.getByUserId", new String [] {"userId"}, userId);
	}
	
	
	public static boolean isUserExistForRole(Long roleId){
		GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getByRoleId", new String [] {"roleId"}, roleId).size() > 0 ? true : false;
	}
}
