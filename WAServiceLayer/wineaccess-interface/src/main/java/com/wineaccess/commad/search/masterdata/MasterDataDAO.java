package com.wineaccess.commad.search.masterdata;


import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.response.Response;

public interface MasterDataDAO<T extends Persistent> extends GenericDAO<T> {
	
	public Response getNormalSearchMasterData(String keyword, int offSet, int limit, String sortBy, int order, String name) throws ParseException;	
}
