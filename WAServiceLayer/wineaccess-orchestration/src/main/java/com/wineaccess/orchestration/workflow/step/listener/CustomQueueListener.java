package com.wineaccess.orchestration.workflow.step.listener;


public abstract class CustomQueueListener {
		
	public abstract void doWork(Object rContext);
	
	public CustomQueueListener() {
	}
}
