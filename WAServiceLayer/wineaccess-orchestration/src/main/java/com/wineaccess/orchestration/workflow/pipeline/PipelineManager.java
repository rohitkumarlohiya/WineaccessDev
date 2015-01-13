package com.wineaccess.orchestration.workflow.pipeline;

import com.wineaccess.orchestration.commad.context.RequestContext;

/**
 * @author jyoti.yadav@globallogic.com
 */
public interface PipelineManager {
	
	public void process(RequestContext rContext);
}
