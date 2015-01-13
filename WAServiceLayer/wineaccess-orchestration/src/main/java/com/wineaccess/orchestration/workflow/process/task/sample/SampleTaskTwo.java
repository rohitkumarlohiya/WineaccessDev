package com.wineaccess.orchestration.workflow.process.task.sample;

import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.process.task.BaseTask;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * Sample Task Two
 * @author jyoti.yadav@globallogic.com
 */
public class SampleTaskTwo extends BaseTask {
	
	public SampleTaskTwo(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName,taskConfiguration);
	}
	
	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		System.out.println("*********SampleTaskTwo************");
	}
}
