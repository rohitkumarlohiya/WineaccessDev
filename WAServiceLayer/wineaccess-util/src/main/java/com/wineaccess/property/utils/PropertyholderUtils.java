package com.wineaccess.property.utils;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.property.store.manager.util.DefaultPropertyStoreManager;
import com.wineaccess.property.store.manager.util.PropertyStoreManager;

/**
 * @author jyoti.yadav
 */
public class PropertyholderUtils extends PropertyPlaceholderConfigurer {

	private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;
	private static String EXTERNAL_STORE_MANAGER = "externalPropertyHolder"; 
	private static String DEFAULTL_STORE_MANAGER = "defaultPropertyHolder"; 

	@Override
	public void setSystemPropertiesMode(int systemPropertiesMode) {
		super.setSystemPropertiesMode(systemPropertiesMode);
		springSystemPropertiesMode = systemPropertiesMode;
	}

	public void refreshMap(Properties props) {
		
		PropertyStoreManager propertyStoreManager = getPropertyStoreManager();
		
		for (Object key : props.keySet()) {
			propertyStoreManager.put(key.toString(), resolvePlaceholder(key.toString(), props, springSystemPropertiesMode));
		}
	}
	
	public static void reloadProperties(Map<Object, Object> reloadProperties) {
		PropertyStoreManager propertyStoreManager = getPropertyStoreManager();
		propertyStoreManager.putAll(reloadProperties);
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		
		PropertyStoreManager propertyStoreManager = getPropertyStoreManager();
		
		for (Object key : props.keySet()) {
			propertyStoreManager.put(key.toString(), resolvePlaceholder(key.toString(), props, springSystemPropertiesMode));
		}
		new Thread(new ReloadPropertyHolderUtilProperties(), "Reload System Property").start();
	}

	public static boolean getBooleanProperty(String name) {
		PropertyStoreManager propertyStoreManager = getPropertyStoreManager();
		return propertyStoreManager.get(name) == null ? false : Boolean.parseBoolean(String.valueOf(propertyStoreManager.get(name)));
	}

	public static String getStringProperty(String name) {
		PropertyStoreManager propertyStoreManager = getPropertyStoreManager();
		return propertyStoreManager.get(name) == null ? null : String.valueOf(propertyStoreManager.get(name));
	}

	public static String getProperty(String name, String defaultValue) {
		PropertyStoreManager propertyStoreManager = getPropertyStoreManager();
		return propertyStoreManager.get(name) == null ? defaultValue : String.valueOf(propertyStoreManager.get(name));
	}
	
	private static PropertyStoreManager getPropertyStoreManager() {
		try {
			if ((CoreBeanFactory.getBean(EXTERNAL_STORE_MANAGER)) != null) {
				return (PropertyStoreManager) CoreBeanFactory.getBean(EXTERNAL_STORE_MANAGER);
			} else {
				return (PropertyStoreManager) CoreBeanFactory.getBean(DEFAULTL_STORE_MANAGER);
			}
		} catch(Exception ex) {
			return new DefaultPropertyStoreManager();
		}
		
	}
}
