package com.wineaccess.orchestration.workflow.startup.service;

/**
 * The Class will be responsible for starting different components in the system.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public interface ComponentsStartupService {
	
	public void start() throws Exception;
	
	public void stop() throws Exception;
}
