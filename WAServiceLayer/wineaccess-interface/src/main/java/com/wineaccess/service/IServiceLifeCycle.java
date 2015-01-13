package com.wineaccess.service;
	
/**
 * @author jyoti.yadav@globallogic.com
 */
public interface IServiceLifeCycle {
	
	void start() throws Exception;

	void stop() throws Exception;
}
