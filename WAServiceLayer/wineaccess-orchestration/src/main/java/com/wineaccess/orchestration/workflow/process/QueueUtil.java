package com.wineaccess.orchestration.workflow.process;

import com.wineaccess.orchestration.workflow.step.listener.ApiCompletionQueueListener;


/**
 * @author jyoti.yadav@globallogic.com
 */
public class QueueUtil {
	
	public static void putMessgaeOnApiCompletionQueue(String initialRequestId) throws Exception {
		ApiCompletionQueueListener.apiCompletionStatus.put(initialRequestId, new Object());
		/*String qJndi = ServerConfiguration.getInstance().getComponentConfig(ServerConfigurationConstants.COMPONENT_API_COMPLETION_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
		
		Map <String, Object> properties = new HashMap<String, Object>();
		properties.put("WORK_COMPLETION_TOKEN", initialRequestId);
		
		
		JMSUtils.getInstance().sendMessage(new ProcessContext(), properties, qJndi, true);*/
	}

}
