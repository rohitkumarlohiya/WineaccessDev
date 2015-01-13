package com.wineaccess.command.search.po;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface POSearchDAO  <T extends Persistent> extends GenericDAO<T> {
	
	public POSearchVO normalSearch(POSearchPO poSearchPO)  throws ParseException, UserSearchException;
}
