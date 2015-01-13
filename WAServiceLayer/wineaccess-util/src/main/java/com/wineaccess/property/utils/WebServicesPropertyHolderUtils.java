package com.wineaccess.property.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class WebServicesPropertyHolderUtils extends PropertyPlaceholderConfigurer {
	
	private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;
	private static Map<Object, Object> propertiesMap = new HashMap<Object, Object>();
	
	@Override
	public void setSystemPropertiesMode(int systemPropertiesMode) {
		super.setSystemPropertiesMode(systemPropertiesMode);
		springSystemPropertiesMode = systemPropertiesMode;
	}
	
	
	public static void reloadProperties(Map<Object, Object> reloadProperties) {
		WebServicesPropertyHolderUtils.propertiesMap.putAll(reloadProperties);
	}
	
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		
		try {
			for (Object key : props.keySet()) {
				propertiesMap.put(key.toString(), resolvePlaceholder(key.toString(), props, springSystemPropertiesMode));
			}
			new Thread(new ReloadUtilWebServiceProperties(), "Reload Web Services Properties Job").start();
		} catch(Exception ex) {
			throw new RuntimeException("Error in setting properties..", ex);
		}
	}
	
	public static boolean getBooleanProperty(String name) {
		return propertiesMap.get(name) == null ? false : Boolean.parseBoolean(String.valueOf(propertiesMap.get(name)));
	}

	public static String getStringProperty(String name) {
		return propertiesMap.get(name) == null ? null : String.valueOf(propertiesMap.get(name));
	}

	public static String getProperty(String name, String defaultValue) {
		return propertiesMap.get(name) == null ? defaultValue : String.valueOf(propertiesMap.get(name));
	}
}