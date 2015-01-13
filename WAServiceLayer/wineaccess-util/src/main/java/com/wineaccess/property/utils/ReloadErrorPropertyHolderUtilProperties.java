package com.wineaccess.property.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReloadErrorPropertyHolderUtilProperties implements Runnable {
	
private static Log logger = LogFactory.getLog(ReloadErrorPropertyHolderUtilProperties.class);
	
	public static String ERROR_CODE_PROPERTY_FILE = System.getenv("WINEACCESS_HOME") + "/ErrorCodes/" + "errorCodeLabels.properties" ;
	
	@Override
	public void run() {
		long lastModified = 0;
		do {
			
			try {
				File webServicesFile  = new File (ERROR_CODE_PROPERTY_FILE);
				if (webServicesFile.getAbsoluteFile().lastModified() > lastModified) {
					
					Map<Object, Object> propertiesMap = new HashMap<Object, Object>();
					Configuration config = new PropertiesConfiguration(webServicesFile);
					
					Iterator<Object> itr = config.getKeys();
					while (itr.hasNext()) {
						String key = String.valueOf(itr.next());
						String value = String.valueOf(config.getProperty(key));
						propertiesMap.put(key, value);
					}
					//PropertyholderUtils.reloadProperties(propertiesMap);
					ErrorPropertyHolderUtil.reloadProperties(propertiesMap);
					
					lastModified = webServicesFile.getAbsoluteFile().lastModified();
				}
				Thread.sleep(Long.parseLong(PropertyholderUtils.getStringProperty(PropertyConstants.ERROR_CODE_PROPERTY_RELOAD_TIME)));
			} catch(Exception ex) {
				logger.error("Error in reloading External ErrorCodes Properties", ex);
			}
			
		} while(true);
	}
}
