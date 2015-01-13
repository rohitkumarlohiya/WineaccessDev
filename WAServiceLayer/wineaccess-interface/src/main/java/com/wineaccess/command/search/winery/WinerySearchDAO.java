package com.wineaccess.command.search.winery;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface WinerySearchDAO <T extends Persistent> extends GenericDAO<T> {
	
	public WinerySearchVO normalSearch(WinerySearchPO winerySearchPO) throws ParseException, UserSearchException; 
	
	public WineryAdvanceSearchVO advanceSearch(WineryAdvanceSearchPO winerySearchPO) throws ParseException, UserSearchException;
}

