package com.wineaccess.cmd;

import java.util.Properties;

/**
 * Encapsulates the default properties for CIGcmd.
 * Enabling it to run assumning CIG Server installation on a localhost
 */
public class CIDPcmdDefaultProperties {
	private static Properties defaultProperties = new Properties();
	// initialize default properties
	static {
		defaultProperties.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		defaultProperties.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
		//defaultProperties.setProperty("Context.PROVIDER_URL", "localhost:8080");
		defaultProperties.setProperty("java.naming.provider.url", "localhost:1099");
	}

	static public Properties getProperties() {
		return defaultProperties;
	}
}
