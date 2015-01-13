package com.wineaccess.orchestration.workflow.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.commad.context.RequestContext;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.process.ProcessInstance;
import com.wineaccess.orchestration.workflow.process.QueueUtil;
import com.wineaccess.orchestration.workflow.process.cache.ProcessCacheManager;
import com.wineaccess.persistence.exception.ProcessDefinitionNotFoundException;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class WorkflowManager implements Runnable {
	
	private static Log logger = LogFactory.getLog(WorkflowManager.class);
	private ProcessCacheManager processCacheManager = (ProcessCacheManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.PROCESS_CACHE_MANAGER));
	private RequestContext rContext = null;
	private DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
	
	public WorkflowManager(RequestContext rContext) {
		this.rContext = rContext;
	}
	
	@Override
	public void run() {
		try {
			ProcessInstance process = processCacheManager.getProcess(rContext.getServiceCommand(), rContext.getVersionId());
			process.startProcess(rContext.getMessageHeader().getRequestId(), rContext.getContextParameters());
		} catch (Exception e) {
			logger.error("Exception while starting the processs..", e);
			if (e instanceof ProcessDefinitionNotFoundException) {
				WineaccessError error = new WineaccessError(SystemErrorCode.INVALID_PROCESS_DEF_NAME_VERSION, SystemErrorCode.INVALID_PROCESS_DEF_NAME_VERSION_TEXT); 
				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				failedResponse.addError(error);
				
				Map <String, Object> output = new HashMap<String, Object>();
				output.put("FINAL-RESPONSE", failedResponse);
				
				dataRepositoryManager.addOutput(rContext.getMessageHeader().getRequestId(), output);
				
				try {
					QueueUtil.putMessgaeOnApiCompletionQueue(rContext.getMessageHeader().getRequestId());
				} catch (Exception e1) {
					logger.error("Exception while starting the processs..", e);
					throw new RuntimeException("Exception while starting the processs..", e);
				}
			} else {
				throw new RuntimeException("Exception while starting the processs..", e);
			}
		}
	}
}
