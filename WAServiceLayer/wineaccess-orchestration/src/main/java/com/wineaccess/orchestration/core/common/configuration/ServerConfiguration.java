package com.wineaccess.orchestration.core.common.configuration;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * This class parses the server configuration file. It loads all the server and
 * components properties once and caches locally. Every component config class
 * contains server properties and its own propeties both. If a property does not
 * exists in component then it will be searched in server properties.
 * <p>
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class ServerConfiguration {
 
	/** SERVER_CONFIG_FILE_LOCATION. */
	public static String SERVER_CONFIG_FILE = System.getenv("WINEACCESS_HOME") + "/serverConfig.xml";

	/** SERVER_PROPS_VALUE_KEY. */
	private static final String SERVER_PROPS_VALUE_KEY = "orch:properties.orch:prop.value";

	/** SERVER_PROPS_NAME_KEY. */
	private static final String SERVER_PROPS_NAME_KEY = "orch:properties.orch:prop.name";

	/** COMPONENTS_KEY. */
	private static final String COMPONENTS_KEY = "orch:component[@name]";

	private static ServerConfiguration instance = null;

	private static Log logger = LogFactory.getLog(ServerConfiguration.class);

	private Config configHolder = new Config();

	private ServerConfiguration() {
		try {
			XMLConfiguration config = new XMLConfiguration(new File(SERVER_CONFIG_FILE));
			populateConfigHolder(config, ServerConfiguration.COMPONENTS_KEY,
					ServerConfiguration.SERVER_PROPS_NAME_KEY,
					ServerConfiguration.SERVER_PROPS_VALUE_KEY);
		} catch (ConfigurationException e) {
			logger.error("Unable to load server configuration file.", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Returns an instance of Serverconfiguration.
	 */
	public synchronized static ServerConfiguration getInstance() {
		if (instance == null) {
			instance = new ServerConfiguration();
		}
		return instance;
	}

	/**
	 * This method populate config object recursively.
	 */
	@SuppressWarnings("unchecked")
	private Config populateConfigHolder(Configuration config,
			String componentKey, String propKey, String propValue) {
		Config tempConfig = new Config();
		List<String> propNameList = config.getList(propKey);
		List<String> propValueList = config.getList(propValue);
		int listSize = propNameList.size();
		String propNameStr = null;
		String propValueStr = null;
		for (int i = 0; i < listSize; i++) {
			propNameStr = propNameList.get(i);
			propValueStr = propValueList.get(i);
			if (propNameStr != null && propValueStr != null) {
				tempConfig.setProperty(propNameStr, propValueStr);
			}
		}
		List<String> nameList = config.getList(componentKey);
		String nextCompKey;
		int index = 0;
		for (String nameStr : nameList) {
			nextCompKey = componentKey.substring(0, componentKey.length() - 7)
					+ "(" + index + ")." + "orch:component[@name]";
			propKey = componentKey.substring(0, componentKey.length() - 7)
					+ "(" + index + ")." + "orch:properties.orch:prop.name";
			propValue = componentKey.substring(0, componentKey.length() - 7)
					+ "(" + index + ")." + "orch:properties.orch:prop.value";
			tempConfig.setComponentConfig(nameStr, populateConfigHolder(config,
					nextCompKey, propKey, propValue));
			configHolder = tempConfig;
			index++;
		}
		return tempConfig;
	}

	/**
	 * <p>
	 * This method returns component configuration of the given component name.
	 * <p>
	 * 
	 * @param componentName
	 *            Name of the component for configuration
	 * @return config object of given component.
	 */
	public Config getComponentConfig(String componentName) {
		return (Config) configHolder.get(componentName);
	}

	/**
	 * <p>
	 * This method returns property of particular component. If property will
	 * not found in component then will search in server properties.
	 * <p>
	 * 
	 * @param componentName
	 *            Name of the component in which property name will be searched.
	 * @param propertyName
	 *            Name of the property
	 * @return returns value of the property.
	 */
	public String getComponentProperty(String componentName, String propertyName) {
		Config compConfig = getComponentConfig(componentName);
		String property = null;
		if (compConfig != null) {
			property = compConfig.getProperty(propertyName);
		}
		return property;
	}

	/**
	 * <p>
	 * This method returns server property of a given property name.
	 * <p>
	 * 
	 * @param propertyName
	 *            Property name of the server
	 * @return Value of the server property
	 */
	public String getProperty(String propertyName) {
		return configHolder.getProperty(propertyName);
	}
}
