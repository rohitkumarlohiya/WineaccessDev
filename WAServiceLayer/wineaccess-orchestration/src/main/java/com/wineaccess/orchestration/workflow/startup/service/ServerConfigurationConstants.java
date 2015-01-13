package com.wineaccess.orchestration.workflow.startup.service;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface ServerConfigurationConstants {
	
	String QUEUE_COMPONENTS_PROPERTY = "QUEUE_COMPONENTS";
	String QUEUE_JNDI = "QueueJndi";
	String CORE_POOL_SIZE = "CorePoolSize";
	String TOTAL_POOL_SIZE = "TotalPoolSize";
	String MAX_QUEUE_SIZE = "MaxQueueSize";
	String POOL_INCREEMENT_SIZE = "PoolIncrementSize";
	String QUEUE_LISTENER_CLASS = "QueueListenerClass";
	String QUEUE_CONECTION_FACTORY = "QueueConnectionFactory";
	
	
	
	String COMPONENT_API_REQUEST_QUEUE = "ApiRequestQueue";
	String COMPONENT_DEFAULT_TASK_EXECUTION_QUEUE = "DefaultTaskExecutionQueue";
	String COMPONENT_TASK_COMPLETION_QUEUE = "TaskCompletionQueue";
	String COMPONENT_API_COMPLETION_QUEUE = "ApiCompletionQueue";
	
}
