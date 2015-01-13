package com.wineaccess.command.api.access.code;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;

public class APIAccessDAOImpl<T extends Persistent> extends JPAGenericDAOImpl<T> implements APIAccessDAO<T> {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean isEmailExist(String email) {
		List<T> results = findByNamedQuery("getAccessCodeByEmailId", new String[] {"emailId"}, email);
		return results.isEmpty() ? false : true; 
	}

	@Override
	public List<APIAccessCode> listAPIAccessCodes() {
		return (List<APIAccessCode>) findByNamedQuery("getAccessCode", null, null);
	}
}
