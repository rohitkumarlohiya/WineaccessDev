package com.wineaccess.orchestration.workflow.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import com.wineaccess.orchestration.core.common.configuration.ServerConfiguration;
import com.wineaccess.orchestration.workflow.startup.service.ServerConfigurationConstants;

/**
 * Utility class for sending JMS messages over any jms queue. This is a
 * singleton class which caches the connections to all the ConnectionFactory
 * used across the application and provide utility methods to send messages to
 * any queue via any ConnectionFactory.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class JMSUtils {
	
	/** Expiration time of the message sent to the queue is 1 week as a safety mechanism. */
	private static final long EXPIRATION_TIME_INFINITE = 7 * 24 * 60 * 1000;
	ServerConfiguration serverConfiguration = ServerConfiguration.getInstance();

	//public static final String QUEUE_CONNECTION_FACTORY_JNDI = "java:/ConnectionFactory";
	
	//public static final String TASK_COMPLETION_QUEUE_JNDI = "";

	
	/** Singletion instance for JMSUtil */
	private static JMSUtils instance = null;

	/** Map for caching different JMS ConnectionFactories */
	private static Map<String, QueueConnection> qConnectionsMap = new HashMap<String, QueueConnection>();
	
	private static Map<String, QueueSender> queueSenderMap = new HashMap<String, QueueSender>();
	private static Map<String, QueueSession> queueSessionMap = new HashMap<String, QueueSession>();

	/**
	 * Default private constructor.
	 */
	private JMSUtils() {
		
		try {
			String requestQueue = serverConfiguration.getComponentConfig(ServerConfigurationConstants.COMPONENT_API_REQUEST_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
			
			QueueSession qSession = getQueueConnection(requestQueue).createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = ServiceLocator.getInstance().lookupQueue(requestQueue);
			QueueSender qSender = qSession.createSender(queue);
			queueSessionMap.put(requestQueue, qSession);
			queueSenderMap.put(requestQueue, qSender);
			
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
		
		try {
			String requestQueue = serverConfiguration.getComponentConfig(ServerConfigurationConstants.COMPONENT_API_COMPLETION_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
			
			QueueSession qSession = getQueueConnection(requestQueue).createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = ServiceLocator.getInstance().lookupQueue(requestQueue);
			QueueSender qSender = qSession.createSender(queue);
			
			queueSessionMap.put(requestQueue, qSession);
			queueSenderMap.put(requestQueue, qSender);
			
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
		try {
			String requestQueue = serverConfiguration.getComponentConfig(ServerConfigurationConstants.COMPONENT_DEFAULT_TASK_EXECUTION_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
			
			QueueSession qSession = getQueueConnection(requestQueue).createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = ServiceLocator.getInstance().lookupQueue(requestQueue);
			QueueSender qSender = qSession.createSender(queue);
			
			queueSessionMap.put(requestQueue, qSession);
			queueSenderMap.put(requestQueue, qSender);
			
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
		try {
			String requestQueue = serverConfiguration.getComponentConfig(ServerConfigurationConstants.COMPONENT_TASK_COMPLETION_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
			
			QueueSession qSession = getQueueConnection(requestQueue).createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = ServiceLocator.getInstance().lookupQueue(requestQueue);
			QueueSender qSender = qSession.createSender(queue);
			
			queueSessionMap.put(requestQueue, qSession);
			queueSenderMap.put(requestQueue, qSender);
			
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}

	private static QueueConnection getQueueConnection(String queueJndi) throws JMSException {
		if (qConnectionsMap.get(queueJndi) == null) {
			QueueConnectionFactory qConnectionFactory = ServiceLocator.getInstance().getQueueConnectionFactory(queueJndi);
			if (qConnectionFactory == null) {
				String qcFactoryJndiName = ServerConfiguration.getInstance().getComponentConfig(ServerConfigurationConstants.COMPONENT_TASK_COMPLETION_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_CONECTION_FACTORY);
				qConnectionFactory = ServiceLocator.getInstance().lookupQCFactory(qcFactoryJndiName, queueJndi);
				QueueConnection qConnection = qConnectionFactory.createQueueConnection();
				qConnection.setExceptionListener(new JMSExceptionListener(qConnection) );
				qConnectionsMap.put(queueJndi, qConnection);
			} else {
				QueueConnection qConnection = qConnectionFactory.createQueueConnection();
				qConnection.setExceptionListener(new JMSExceptionListener(qConnection) );
				qConnectionsMap.put(queueJndi, qConnection);
			}
		}
		return qConnectionsMap.get(queueJndi);
	}

	/**
	 * This method returns instance of this class.
	 * 
	 * @return QueueMessageSender
	 */
	public static JMSUtils getInstance() {
		if (instance == null) {
			instance = new JMSUtils();
		}
		return instance;
	}
	
	/**
	 * Sends the message over the specified jms-queue using the given
	 * connectionFactory. The properties map is set in the jms-message being
	 * send.
	 * 
	 * @param messageObj -
	 *            serializable onject to be send over the queue
	 * @param propertyMap -
	 *            map for properties to be send as Message properties
	 * @param queueName -
	 *            name of the JMS queue (not the JNDI name). JNDI name is
	 *            lookedup using this name from the server configuration file
	 * @param conFactoryName -
	 *            connectionFactory name (not the JNDI name) via which the
	 *            message needs to be send over the queue.
	 * @param persistent -
	 *            true indicates that the message needs to be persistent on the
	 *            queue. This will not have any effect if the queue itself does
	 *            not support persistent messages.
	 * @throws JMSException
	 */
	/*public void sendMessage(Serializable messageObj, Map<String,Object> propertyMap, String queueName, QueueConnection qConnection, boolean persistent)	throws JMSException {
		QueueSession qSession = null;
		QueueSender qSender = null;
		try {
			qSession = qConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = ServiceLocator.getInstance().lookupQueue(queueName);
			qSender = qSession.createSender(queue);
			
			ObjectMessage queueMessage = qSession.createObjectMessage(messageObj);
			if (propertyMap != null) {
				// Set the Message Properties
				for (String key : propertyMap.keySet()) {
					queueMessage.setObjectProperty(key, propertyMap.get(key));
				}
			}
			int mode = persistent ? DeliveryMode.PERSISTENT	: DeliveryMode.NON_PERSISTENT;
			qSender.send(queueMessage, mode, Message.DEFAULT_PRIORITY, EXPIRATION_TIME_INFINITE);
		} catch (JMSException e) {
			throw e;
		} finally {
			if (qSender != null) {
				qSender.close();
			}

			if (qSession != null) {
				qSession.close();
			}
		}
	}*/

	/**
	 * Sends the message over the specified jms-queue using the given
	 * connectionFactory.
	 * 
	 * @param messageObj -
	 *            serializable onject to be send over the queue
	 * @param queueJNDI -
	 *            jndi name of the JMS queue.
	 * @param persistent -
	 *            true indicates that the message needs to be persistent on the
	 *            queue. This will not have any effect if the queue itself does
	 *            not support persistent messages.
	 * @throws JMSException
	 */
	public void sendMessage(Serializable messageObj, String queueJNDI, boolean persistent) throws JMSException {
		sendMessage(messageObj, null, queueJNDI, persistent);
	}

	/**
	 * Sends the message over the specified jms-queue using the given
	 * connectionFactory. Just overloaded to receive JMS Message properties in
	 * the API Call.
	 * 
	 * @param messageObj -
	 *            serializable onject to be send over the queue
	 * @param propertyMap -
	 *            Map of properties, to be passed as JMS Message.
	 * @param queueJNDI -
	 *            jndi name of the JMS queue.
	 * @param persistent -
	 *            true indicates that the message needs to be persistent on the
	 *            queue. This will not have any effect if the queue itself does
	 *            not support persistent messages.
	 * @throws JMSException
	 */
	public void sendMessage(Serializable messageObj, Map propertyMap, String queueJNDI, boolean persistent) throws JMSException {
		try {
			ObjectMessage queueMessage = queueSessionMap.get(queueJNDI).createObjectMessage(messageObj);
			if (propertyMap != null) {
				for (Object key : propertyMap.keySet()) {
					queueMessage.setObjectProperty((String) key, propertyMap.get(key));
				}
			}
			int mode = persistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT;
			long sTime = System.currentTimeMillis();
			queueSenderMap.get(queueJNDI).send(queueMessage, mode, Message.DEFAULT_PRIORITY, EXPIRATION_TIME_INFINITE);
			long eTime = System.currentTimeMillis();
			System.out.println("JMS Queue Time - " + queueJNDI + " - " + (eTime -sTime));
		} catch (JMSException e) {
			throw e;
		} finally {
			/*if (qSender != null) {
				qSender.close();
			}

			if (qSession != null) {
				qSession.close();
			}*/
			
		}
	}
}
