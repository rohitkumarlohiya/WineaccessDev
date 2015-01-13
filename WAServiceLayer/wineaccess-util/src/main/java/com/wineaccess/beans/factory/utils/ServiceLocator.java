package com.wineaccess.beans.factory.utils;

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

	private InitialContext ic = null;
	
	/**
	 * Constructor for creating ServiceLocator's instance for local lookups.
	 */
	private ServiceLocator() {
		try {
			this.ic = new InitialContext();
		} catch (NamingException e) {
			logger.error("Error creating jndi context ", e);
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
	 * <p>
	 * Lookup method for random Object ex Mailsession, Custom Parameters etc.
	 * </p>
	 * 
	 * @param jndiName :
	 *            String
	 * @return Object
	 * @throws ServiceLocatorException
	 */
	public Object lookupObject(String jndiName) {
		Object obj = null;
		try {
			obj = ic.lookup(jndiName);
		} catch (NamingException e) {
			throw new RuntimeException("Error occured while object lookup: "
					+ jndiName, e);
		}
		return obj;
	}
}
