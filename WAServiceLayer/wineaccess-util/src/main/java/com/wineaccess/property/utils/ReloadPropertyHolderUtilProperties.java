package com.wineaccess.property.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReloadPropertyHolderUtilProperties implements Runnable {
	
private static Log logger = LogFactory.getLog(ReloadUtilWebServiceProperties.class);
	
	public static String SYSTEM_PROPERTY_FILE = System.getenv("WINEACCESS_HOME") + "/SystemProperty/" + System.getenv("WINEACCESS_ENV") + ".app.properties" ;
	
	@Override
	public void run() {
		long lastModified = 0;
		do {
			
			try {
				File webServicesFile  = new File (SYSTEM_PROPERTY_FILE);
				if (webServicesFile.getAbsoluteFile().lastModified() > lastModified) {
					
					Map<Object, Object> propertiesMap = new HashMap<Object, Object>();
					Configuration config = new PropertiesConfiguration(webServicesFile);
					
					Iterator<Object> itr = config.getKeys();
					while (itr.hasNext()) {
						String key = String.valueOf(itr.next());
						String value = String.valueOf(config.getProperty(key));
						propertiesMap.put(key, value);
					}
					PropertyholderUtils.reloadProperties(propertiesMap);
					
					lastModified = webServicesFile.getAbsoluteFile().lastModified();
				}
				Thread.sleep(Long.parseLong(PropertyholderUtils.getStringProperty(PropertyConstants.SYSTEM_PROPERTY_RELOAD_TIME)));
			} catch(Exception ex) {
				logger.error("Error in reloading External Web Services Properties", ex);
			}
			
		} while(true);
	}
}
