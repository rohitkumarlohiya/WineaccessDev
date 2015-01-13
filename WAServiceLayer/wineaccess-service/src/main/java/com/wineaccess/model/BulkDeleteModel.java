/**
 * 
 */
package com.wineaccess.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anurag.jain3
 *
 */
public class BulkDeleteModel<T> {
	
	List<? extends Serializable> notExistsList;
	List<T> deletedList;
	List<T> notDeletedList;
		
	public BulkDeleteModel() {
		notExistsList = new ArrayList<Serializable>();
		deletedList = new ArrayList<T>();
		notDeletedList = new ArrayList<T>();
	}
	public List<? extends Serializable> getNotExistsList() {
		return notExistsList;
	}
	public void setNotExistsList(List<? extends Serializable> notExistsList) {
		this.notExistsList = notExistsList;
	}
	public List<T> getDeletedList() {
		return deletedList;
	}
	public void setDeletedList(List<T> deletedList) {
		this.deletedList = deletedList;
	}
	public List<T> getNotDeletedList() {
		return notDeletedList;
	}
	public void setNotDeletedList(List<T> notDeletedList) {
		this.notDeletedList = notDeletedList;
	}
	
	
	
}
