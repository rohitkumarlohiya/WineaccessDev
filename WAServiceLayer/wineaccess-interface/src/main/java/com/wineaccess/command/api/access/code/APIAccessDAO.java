package com.wineaccess.command.api.access.code;

import java.util.List;

import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface APIAccessDAO<T extends Persistent> extends GenericDAO<T> {
	
	public boolean isEmailExist(String email);
	
	public List<APIAccessCode> listAPIAccessCodes();
}
