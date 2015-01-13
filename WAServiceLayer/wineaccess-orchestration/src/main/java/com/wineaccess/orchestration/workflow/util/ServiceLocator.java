package com.wineaccess.orchestration.workflow.util;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class for EJB, JMS, JMX resource lookup.
 * 
 * @author ashish.jaiswal@induslogic.com
 */
public class ServiceLocator {

	static Log logger = LogFactory.getLog(ServiceLocator.class);

	private static ServiceLocator instance;

	private InitialContext ic;

	/** Field serviceCache */
	private Map<String, Object> serviceCache = new HashMap<String, Object>();
	private Map<String, QueueConnectionFactory> jndiServiceLocator = new HashMap<String, QueueConnectionFactory>();

	
	private ServiceLocator() {
		try {
			ic = new InitialContext();
		} catch (NamingException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * <p>
	 * Get instance of ServiceLocator.
	 * </p>
	 * 
	 * @return ServiceLocator
	 */
	public static synchronized ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	
	/**
	 * Mehtod looksup the QueueConnection Factory by its JndiName.
	 * 
	 * @param qcFactoryJndiName :
	 *            String
	 * @return QueueConnectionFactory
	 */
	public QueueConnectionFactory lookupQCFactory(String qcFactoryJndiName, String queueJndi) {
		QueueConnectionFactory queConnFactory = null;

		try {
			if (serviceCache.containsKey(qcFactoryJndiName)) {
				queConnFactory = (QueueConnectionFactory) serviceCache.get(qcFactoryJndiName);
			} else {
				queConnFactory = (QueueConnectionFactory) ic.lookup(qcFactoryJndiName);
				serviceCache.put(qcFactoryJndiName, queConnFactory);
			}
			jndiServiceLocator.put(queueJndi, queConnFactory);
		} catch (NamingException e) {
			throw new RuntimeException("Error locating QueueConnectionFactory : " + qcFactoryJndiName, e);
		}
		return queConnFactory;
	}
	
	
	public QueueConnectionFactory getQueueConnectionFactory(String queueJndi) {
		return this.jndiServiceLocator.get(queueJndi);
	}
	
	
	/**
	 * <p>
	 * Lookup queue.
	 * </p>
	 * 
	 * @param queueJndiName :
	 *            String
	 * @return Queue
	 * @throws ServiceLocatorException
	 */
	public Queue lookupQueue(String queueJndiName) {
		Queue que = null;
		try {
			if (serviceCache.containsKey(queueJndiName)) {
				que = (Queue) serviceCache.get(queueJndiName);
			} else {
				que = (Queue) ic.lookup(queueJndiName);
				serviceCache.put(queueJndiName, que);
			}
		} catch (NamingException e) {
			throw new RuntimeException("Error locating JMS Queue : " + queueJndiName, e);
		}
		return que;
	}
}
