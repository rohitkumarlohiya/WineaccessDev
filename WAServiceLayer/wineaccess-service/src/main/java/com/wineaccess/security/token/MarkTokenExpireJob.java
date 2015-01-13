package com.wineaccess.security.token;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * The Job Will Mark the login token expired if the difference between current time stamp and last access time
 * stamp is more then configured value.
 */
public class MarkTokenExpireJob implements Runnable {
	
	private static Log logger = LogFactory.getLog(MarkTokenExpireJob.class);
	
	@Override
	public void run() {
		TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		do {
			try {
				List<String> tokensRemoved = tokenUtils.markTokenExpired(Long.parseLong(PropertyholderUtils.getStringProperty(PropertyConstants.TOKEN_EXPIRATION_IDLE_TIMEOUT_TIME)));
				TokenModelRepository.updateEndtime(tokensRemoved);
				Thread.sleep(60000);
			} catch(Exception ex) {
				logger.error("Error in making the token expired", ex);
			}
		} while(true);
	}
}
