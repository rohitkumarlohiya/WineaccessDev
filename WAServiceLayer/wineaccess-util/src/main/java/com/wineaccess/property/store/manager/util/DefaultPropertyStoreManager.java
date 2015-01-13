package com.wineaccess.property.store.manager.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @see PropertyStoreManager
 * 
 * @author jyoti.yadav@globallogic.com
 *
 */
public class DefaultPropertyStoreManager implements PropertyStoreManager {
	
	private static Map<Object, Object> propertiesMap = new HashMap<Object, Object>();
	
	
	@Override
	public void put(Object name, Object value) {
		propertiesMap.put(name, value);
	}

	@Override
	public void putAll(Map<Object, Object> properties) {
		propertiesMap.putAll(properties);
	}

	@Override
	public Object get(Object name) {
		return propertiesMap.get(name);
	}

	@Override
	public Map<Object, Object> getAll() {
		return propertiesMap;
	}
}
