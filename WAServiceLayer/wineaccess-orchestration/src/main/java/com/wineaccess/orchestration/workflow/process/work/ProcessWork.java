package com.wineaccess.orchestration.workflow.process.work;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.workflow.process.cache.ProcessCacheManager;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class ProcessWork extends Work{
	private static Log logger = LogFactory.getLog(ProcessWork.class);
	ProcessCacheManager processCacheManager = (ProcessCacheManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.PROCESS_CACHE_MANAGER));
	
	public ProcessWork(Object qMessage) {
		super(qMessage);
	}

	@Override
	protected void doWork() throws Exception {
		ProcessContext pContext = ((ProcessContext) this.qMessage);
		com.wineaccess.orchestration.workflow.process.ProcessInstance process = processCacheManager.getProcess(pContext.getProcessName(), pContext.getVersionId());
		process.executeTask(pContext);
	}
}
