package com.wineaccess.property.store.manager.util;

import java.util.Map;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface PropertyStoreManager {
	
	/**
	 * Store the single property, Overwrite the value of existing property with notification.
	 * @param name
	 * @param value
	 */
	public void put(Object name, Object value);
	
	/**
	 * Store a collection of properties. Overwrite the value of existing property with notification.
	 * @param properties
	 */
	public void putAll(Map<Object, Object> properties);
	
	/**
	 * Return the value of single property if exist else return null.
	 * @param name
	 * @return
	 */
	public Object get(Object name);
	
	/**
	 * Return all the properties.
	 * @return
	 */
	public Map<Object, Object> getAll();
}
