package com.wineaccess.wine;


import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface WineSearchDAO <T extends Persistent> extends GenericDAO<T> {
	
	public WineBasicSearchVO normalSearch(WineBasicSearchPO winerySearchPO) throws ParseException, UserSearchException; 
	
	public WineAdvanceSearchVO advanceSearch(WineAdvanceSearchPO winerySearchPO) throws ParseException, UserSearchException;
}