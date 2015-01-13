package com.wineaccess.orchestration.workflow.pipeline.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.commad.context.RequestContext;
import com.wineaccess.orchestration.core.common.configuration.ServerConfiguration;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.manager.WorkflowManager;
import com.wineaccess.orchestration.workflow.pipeline.PipelineManager;
import com.wineaccess.orchestration.workflow.process.QueueUtil;
import com.wineaccess.orchestration.workflow.startup.service.ServerConfigurationConstants;
import com.wineaccess.orchestration.workflow.step.listener.ApiCompletionQueueListener;
import com.wineaccess.orchestration.workflow.step.listener.ApiRequestQueueListener;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class DefaultPipelineManager implements PipelineManager {
	
	private static Log logger = LogFactory.getLog(WorkflowManager.class);
	private ServerConfiguration serverConfiguration = ServerConfiguration.getInstance();
	private ApiRequestQueueListener apiRequestQueueListener = new ApiRequestQueueListener();
	/**
	 * @see PipelineManager#process(RequestContext)
	 */
	@Override
	public void process(RequestContext rContext) {
		long sTime = System.currentTimeMillis();
		apiRequestQueueListener.doWork(rContext);
		Object object = null;
		long startTime = System.currentTimeMillis();
		do {
			if (ApiCompletionQueueListener.apiCompletionStatus.get(rContext.getMessageHeader().getRequestId()) != null) {
				object = new Object();
				ApiCompletionQueueListener.apiCompletionStatus.remove(rContext.getMessageHeader().getRequestId());
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		} while ((System.currentTimeMillis() - startTime) < 60000);
		
		if (object == null) {
			
			WineaccessError error = new WineaccessError(SystemErrorCode.REQUEST_TIMED_OUT, SystemErrorCode.REQUEST_TIMED_OUT_TEXT); 
			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);
			
			Map <String, Object> output = new HashMap<String, Object>();
			output.put("FINAL-RESPONSE", failedResponse);
			
			DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
			dataRepositoryManager.addOutput(rContext.getMessageHeader().getRequestId(), output);
			try {
				QueueUtil.putMessgaeOnApiCompletionQueue(rContext.getMessageHeader().getRequestId());
			} catch (Exception ex) {
				logger.error("Exception while starting the processs..", ex);
				throw new RuntimeException("Exception while starting the processs..", ex);
			}
		}
		long eTime = System.currentTimeMillis();
		System.out.println("Time ---> " + (eTime - sTime));
	}
	
	public String getQueueJndi() {
		return serverConfiguration.getComponentConfig(ServerConfigurationConstants.COMPONENT_API_REQUEST_QUEUE).getProperty(ServerConfigurationConstants.QUEUE_JNDI);
	}
}
