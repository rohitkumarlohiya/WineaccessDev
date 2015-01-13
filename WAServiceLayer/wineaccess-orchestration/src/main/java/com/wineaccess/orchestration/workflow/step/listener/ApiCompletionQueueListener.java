package com.wineaccess.orchestration.workflow.step.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class ApiCompletionQueueListener {
	
	public static Map <String, Object> apiCompletionStatus = new ConcurrentHashMap<String, Object>();
}
