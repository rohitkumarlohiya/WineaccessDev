package com.wineaccess.data.connection.manager;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface ConnectionManager {
	
	public void start();
	
	public void stop();
	
	public Object getConnection();
}
