package com.wineaccess.orchestration.workflow.util;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class JMSExceptionListener implements javax.jms.ExceptionListener{
	/** logger. */
	private static Log logger = LogFactory.getLog(JMSExceptionListener.class);
	
	private QueueConnection mQueueConnection = null;
	
	public JMSExceptionListener(QueueConnection mQueueConnection){
		this.mQueueConnection = mQueueConnection;
	}

	public void onException(JMSException arg0) {
		try {
			if(mQueueConnection != null) {
				mQueueConnection.stop();
			}
		} catch (JMSException e) {
			logger.warn("unable to clean QueueConnection "+ e.getCause());	
		}
		logger.warn("Unable to connect: server may be down  "+ arg0.getCause());		
	}
}