package com.wineaccess.commad.search.masterdatatype;


import java.util.List;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface MasterDataTypeDAO<T extends Persistent> extends GenericDAO<T> {
	
	public MasterDataTypeSearchVO getNormalSearchMasterDataType(String keyword, int offSet, int limit, String sortBy, int order, List<String> exclusionsList) throws ParseException;	
}
