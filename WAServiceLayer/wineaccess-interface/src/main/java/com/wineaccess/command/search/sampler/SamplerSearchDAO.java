package com.wineaccess.command.search.sampler;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.commad.search.users.UserSearchException;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.persistence.dao.GenericDAO;

public interface SamplerSearchDAO<T extends Persistent> extends GenericDAO<T> {

	public SamplerSearchVO normalSearch(SamplerSearchPO samplerSearchPO)
			throws ParseException, UserSearchException;

	public SamplerAdvSearchVO advanceSearch(SamplerAdvSearchPO samplerSearchPO)
			throws ParseException, UserSearchException;
}
