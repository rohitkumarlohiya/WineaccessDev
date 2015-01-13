package com.wineaccess.command.search.importer;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface ImporterSearchDAO<T extends Persistent> extends GenericDAO<T> {
	
	public ImporterSearchVO normalSearch(ImporterSearchPO importerPO) throws ParseException, UserSearchException;
	
	public ImporterAdvanceSearchVO normalSearch(ImporterAdvanceSearchPO importerPO) throws ParseException, UserSearchException;
}
