package com.wineaccess.orchestration.workflow.process.work;


/**
 * @author jyoti.yadav@globallogic.com
 */
public abstract class Work implements Runnable {
	
	protected Object qMessage = null;
	
	public Work(Object qMessage) {
		this.qMessage = qMessage;
	}

	@Override
	public void run() {
		try {
			doWork();
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected abstract void doWork() throws Exception;
}
