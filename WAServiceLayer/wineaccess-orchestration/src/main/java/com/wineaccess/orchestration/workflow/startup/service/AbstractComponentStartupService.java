package com.wineaccess.orchestration.workflow.startup.service;


/**
 * @author jyoti.yadav@globallogic.com
 */
public abstract class AbstractComponentStartupService implements ComponentsStartupService {

	@Override
	public final void start() throws Exception {
		startQueueListener();
	}

	@Override
	public final void stop() throws Exception {
		stopQueueListener();
	}
	
	public abstract void startQueueListener() throws Exception;
	
	public abstract void stopQueueListener();
}
