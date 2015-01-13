package com.wineaccess.persistence.exception;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class PersistenceException extends SystemException{
	
	Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public PersistenceException(Object data) {
		super();
		this.data = data;
	}

	public PersistenceException() {
		super();
	}
}
