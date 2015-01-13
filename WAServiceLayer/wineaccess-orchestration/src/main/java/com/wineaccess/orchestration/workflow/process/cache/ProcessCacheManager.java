package com.wineaccess.orchestration.workflow.process.cache;

import com.wineaccess.orchestration.workflow.process.ProcessInstance;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface ProcessCacheManager {
	
	public ProcessInstance getProcess(String processName, String versionId);
}
