package com.wineaccess.orchestration.workflow.startup.service;

import com.wineaccess.orchestration.core.common.configuration.Config;
import com.wineaccess.orchestration.core.common.configuration.ServerConfiguration;
import com.wineaccess.orchestration.core.concurrent.util.DefaultPoolPolicy;
import com.wineaccess.orchestration.core.concurrent.util.IThreadPoolPolicy;
import com.wineaccess.orchestration.core.concurrent.util.ThreadPool;
import com.wineaccess.orchestration.core.concurrent.util.ThreadPoolFactory;
import com.wineaccess.orchestration.workflow.queue.listener.JMSQueueListener;
import com.wineaccess.utils.ResourceLoader;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class DefaultComponentStartup extends AbstractComponentStartupService {
	
	private  ServerConfiguration serverConfig = ServerConfiguration.getInstance();
	
	public static ThreadPool threadPoolApiRequest = null;  
	
	public static ThreadPool threadDefaultTaskExecutionQueue = null; 
	
	
	@Override
	public void startQueueListener() throws Exception {
		String [] qListeners = serverConfig.getProperty(ServerConfigurationConstants.QUEUE_COMPONENTS_PROPERTY).split("-");
		for (String qListener : qListeners) {
			
			Config config = serverConfig.getComponentConfig(qListener);
			
			
			IThreadPoolPolicy threadPoolPolicy = new DefaultPoolPolicy(getPoolIncrementSize(config));
			
			if (qListener.equals("ApiRequestQueue")) {
				ThreadPool threadPool = ThreadPoolFactory.getOrderedThreadExecutor(getCorePoolSize(config), getMaxPoolSize(config), ThreadPool.DEFAULT_KEEP_ALIVE_TIME, getMaxQueueSize(config));
				threadPool.setPolicy(threadPoolPolicy);
				threadPoolApiRequest = threadPool;
			} else if (qListener.equals("DefaultTaskExecutionQueue")) {
				ThreadPool threadPool = ThreadPoolFactory.getOrderedThreadExecutor(getCorePoolSize(config), getMaxPoolSize(config), ThreadPool.DEFAULT_KEEP_ALIVE_TIME, getMaxQueueSize(config));
				threadPool.setPolicy(threadPoolPolicy);
				threadDefaultTaskExecutionQueue = threadPool;
			} else if (qListener.equals("TaskCompletionQueue")) {
				ThreadPool threadPool = ThreadPoolFactory.getOrderedThreadExecutor(getCorePoolSize(config), getMaxPoolSize(config), ThreadPool.DEFAULT_KEEP_ALIVE_TIME, getMaxQueueSize(config));
				threadPool.setPolicy(threadPoolPolicy);
				//threadDefaultTaskExecutionQueue = threadPool;
			} else {
				ThreadPool threadPool = ThreadPoolFactory.getOrderedThreadExecutor(getCorePoolSize(config), getMaxPoolSize(config), ThreadPool.DEFAULT_KEEP_ALIVE_TIME, getMaxQueueSize(config));
				threadPool.setPolicy(threadPoolPolicy);
				
				String queueListenerClass = config.getProperty(ServerConfigurationConstants.QUEUE_LISTENER_CLASS);
				
				Object[] constructorArgs = new Object[] { getQueueConnectionFactory(config), getQueueJndi(config), threadPool};
				JMSQueueListener jMSQueueListener = (JMSQueueListener) ResourceLoader.instanciateClass(queueListenerClass, constructorArgs);
				if (jMSQueueListener == null) throw new RuntimeException("JMS Queue Listener is null");
			}
		}
	}
	
	public int getCorePoolSize(Config config) {
		return config.getAsInt(ServerConfigurationConstants.CORE_POOL_SIZE);
	}
	
	
	public int getMaxPoolSize(Config config) {
		return config.getAsInt(ServerConfigurationConstants.TOTAL_POOL_SIZE);
	}
	
	public int getMaxQueueSize(Config config) {
		return config.getAsInt(ServerConfigurationConstants.MAX_QUEUE_SIZE);
	}
	
	public int getPoolIncrementSize(Config config) {
		return config.getAsInt(ServerConfigurationConstants.POOL_INCREEMENT_SIZE);
	}
	
	public String getQueueJndi(Config config) {
		return config.getProperty(ServerConfigurationConstants.QUEUE_JNDI);
	}
	
	public String getQueueConnectionFactory(Config config) {
		return config.getProperty(ServerConfigurationConstants.QUEUE_CONECTION_FACTORY);
	}
	
	@Override
	public void stopQueueListener() {
	}
}
