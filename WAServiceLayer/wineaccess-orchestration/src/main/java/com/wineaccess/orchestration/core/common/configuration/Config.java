package com.wineaccess.orchestration.core.common.configuration;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class is the main class which holds the configuration of
 * application/module. This configuration structure is nested in nature (as shown
 * below) and this this class is capable for keeping nested configuration. <br>
 * <b>
 * Config <br>
 * 		- key1 : value1<br>
 * 		- key2 : value2<br>
 * 		- module1 : Config<br>
 * 		- module2 : Config<br>
 * </b>
 * @author jyoti.yadav@globallogic.com
 */
@SuppressWarnings("serial")
public class Config extends Hashtable {

	/* Field logger */
	private static Log logger = LogFactory.getLog(Config.class);

	/**
	 * Property is custom property object. This method add the name, value in this Property
	 * object to the config object.
	 * 
	 * @param property
	 *            Custom property object
	 */
	@SuppressWarnings("unchecked")
	public void setProperty(Property property) {
		this.put(property.getName(), property.getValue());
	}

	/**
	 * Set the property.
	 * @param name : prop name.
	 * @param value : prop value.
	 */
	@SuppressWarnings("unchecked")
	public void setProperty(String name, String value) {
		this.put(name, value);
	}

	/**
	 * This method returns value as String.
	 * 
	 * @param key
	 *            Key of the property
	 * @return String Value of the property
	 */
	public String getProperty(String key) {
		return (String) this.get(key);
	}

	/**
	 * <p>
	 * This method returns property as Boolean.
	 * <p>
	 * 
	 * @param propertyName
	 *            Key of the property
	 * @return boolean Value of the property
	 * 
	 */
	public boolean getAsBoolean(String propertyName) {
		return Boolean.parseBoolean(getProperty(propertyName));
	}

	/**
	 * <p>
	 * This method returns property as int.It gets the property as string and
	 * parses to int.
	 * <p>
	 * 
	 * @param propertyName
	 *            Key of the property
	 * @return Value of the propery as int
	 * 
	 */
	public int getAsInt(String propertyName) throws NumberFormatException {
		int value = 0;
		try {
			value = Integer.parseInt(getProperty(propertyName));
		} catch (NumberFormatException nfe) {
			logger.error("INVALID INT VALUE for property " + propertyName
					+ " value= " + getProperty(propertyName));
			throw nfe;
		}
		return value;
	}

	/**
	 * <p>
	 * This Method returns property as Float.
	 * <p>
	 * 
	 * @param propertyName
	 *            Key of the property
	 * @return Value of the property as Float.
	 * 
	 */
	public float getAsFloat(String propertyName) throws NumberFormatException {
		return Float.parseFloat(getProperty(propertyName));
	}

	/**
	 * <p>
	 * This method returns property as Double.
	 * <p>
	 * 
	 * @param propertyName
	 *            Key of the of the property
	 * @return Value of the property
	 */
	public double getAsDouble(String propertyName) throws NumberFormatException {
		return Double.parseDouble(getProperty(propertyName));
	}

	/**
	 * @param module :  String
	 * @param argConfig :
	 *            Config
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setComponentConfig(String module, Config argConfig) {
		this.put(module, argConfig);
	}

	/**
	 * @param module :
	 *            String
	 * @return Hashtable
	 * 
	 */
	public Config getComponentConfig(String module) {
		return (Config) this.get(module);
	}
}
