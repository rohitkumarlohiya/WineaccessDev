package com.wineaccess.common;

import java.io.Serializable;
import java.util.List;

public abstract class BulkDeleteVO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3333859741694718177L;
	List<Long> nonExistsList;
	DeleteVO<T> failureList;
	DeleteVO<T> successList;
	
	public BulkDeleteVO(){
		super();
	}
	
	public List<Long> getNonExistsList() {
		return nonExistsList;
	}
	public void setNonExistsList(List<Long> nonExistsList) {
		this.nonExistsList = nonExistsList;
	}
	public DeleteVO<T> getFailureList() {
		return failureList;
	}
	public void setFailureList(DeleteVO<T> failureList) {
		this.failureList = failureList;
	}
	public DeleteVO<T> getSuccessList() {
		return successList;
	}
	public void setSuccessList(DeleteVO<T> successList) {
		this.successList = successList;
	}
	
	
	
}
