package com.wineaccess.orchestration.workflow.step.listener;

import com.wineaccess.orchestration.commad.context.RequestContext;
import com.wineaccess.orchestration.workflow.manager.WorkflowManager;
import com.wineaccess.orchestration.workflow.startup.service.DefaultComponentStartup;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class ApiRequestQueueListener extends CustomQueueListener {
	
	public ApiRequestQueueListener() {
	}

	public void doWork(Object rContext) {
		DefaultComponentStartup.threadPoolApiRequest.execute(new WorkflowManager((RequestContext) rContext));
	}
}
