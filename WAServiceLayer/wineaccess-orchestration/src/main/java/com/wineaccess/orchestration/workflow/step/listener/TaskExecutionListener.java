package com.wineaccess.orchestration.workflow.step.listener;

import com.wineaccess.orchestration.workflow.process.work.ProcessWork;
import com.wineaccess.orchestration.workflow.startup.service.DefaultComponentStartup;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class TaskExecutionListener extends CustomQueueListener {
	
	public TaskExecutionListener() {
	}
	
	@Override
	public void doWork(Object pContext) {
		DefaultComponentStartup.threadDefaultTaskExecutionQueue.execute(new ProcessWork(pContext));
	}
}
