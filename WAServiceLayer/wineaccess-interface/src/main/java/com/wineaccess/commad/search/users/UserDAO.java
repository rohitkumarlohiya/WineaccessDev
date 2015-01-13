package com.wineaccess.commad.search.users;


import java.util.List;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.util.SearchExclusionPO;

public interface UserDAO<T extends Persistent> extends GenericDAO<T> {
	
	public UserSearchVO getNormalSearchUserModel(String keyword, int offSet, int limit, String sortBy, int order, String searchBy, String additonalFilter, List<SearchExclusionPO> exclusions)  throws ParseException,UserSearchException;
	
	public int getUserCount(List<SearchExclusionPO> exclusions) throws ParseException;
	
	public UserAdavanceSearchVO getAdvanceSearchUserModel(UserSearchAdvancePO userSearchAdvancePO, int offSet, int limit, String sortBy, int order, String keyword, String searchBy, List<SearchExclusionPO> exclusions) throws ParseException,UserSearchException;
}
