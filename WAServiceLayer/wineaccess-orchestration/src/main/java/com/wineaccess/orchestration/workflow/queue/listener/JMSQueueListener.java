package com.wineaccess.orchestration.workflow.queue.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import com.wineaccess.orchestration.core.concurrent.util.ThreadPool;
import com.wineaccess.orchestration.workflow.util.ServiceLocator;

/**
 * Base Class for all the Queue Message Listener in the system.
 * @author jyoti.yadav@globallogic.com
 */
public abstract class JMSQueueListener implements MessageListener {
	
	
	/* Field mSession - JMS QueueSession for this Listener */
	protected QueueSession mSession;

	/* Field mQueueReceiver - JMS QueueReceiver for this Listener */
	protected QueueReceiver mQueueReceiver;

	/* mQueueConnection. */
	protected QueueConnection mQueueConnection;
	
	private ThreadPool threadPool = null;
	
	
	public JMSQueueListener(String qConFactory, String queueJndi, ThreadPool threadPool) {
		doConnectTheQueue(qConFactory, queueJndi);
		this.threadPool = threadPool;
	}
	
	@Override
	public void onMessage(Message qMessage) {
		try {
			doWork(qMessage);
		} catch(Exception ex) {
			throw new RuntimeException("Error in Queue On Message", ex);
		}
	}
	
	private void doConnectTheQueue(String qConFactory, String queueJndi) {
		try {
			ServiceLocator sLocator = ServiceLocator.getInstance();

			QueueConnectionFactory queueConnectionFactory = sLocator.lookupQCFactory(qConFactory, queueJndi);

			Queue queue = sLocator.lookupQueue(queueJndi);

			this.mQueueConnection = queueConnectionFactory.createQueueConnection();
			this.mQueueConnection.start();
			mSession = this.mQueueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			if (this.createMessageSelector() != null) {
				mQueueReceiver = mSession.createReceiver(queue, this.createMessageSelector());
			} else {
				mQueueReceiver = mSession.createReceiver(queue);
			}
			mQueueReceiver.setMessageListener(this);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public abstract void doWork(Message qMessage) throws Exception;
	
	protected abstract String createMessageSelector();

	public ThreadPool getThreadPool() {
		return threadPool;
	}
}
