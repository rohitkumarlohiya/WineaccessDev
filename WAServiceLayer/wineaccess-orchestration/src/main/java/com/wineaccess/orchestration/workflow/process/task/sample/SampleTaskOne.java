package com.wineaccess.orchestration.workflow.process.task.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.process.task.BaseTask;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * Sample Task One
 * 
 * @author jyoti.yadav@globallogic.com
 * 
 */
public class SampleTaskOne extends BaseTask {

	public SampleTaskOne(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		List<SampleTaskOneResponse> samples = new ArrayList<SampleTaskOneResponse>();

		for (int i = 1; i < 10; i++) {
			samples.add(new SampleTaskOneResponse("Test" + i, 100 + i));
		}

		Map<String, Object> output = new HashMap<String, Object>();
		output.put("FINAL-RESPONSE", samples);

		getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
		System.out.println("************SampleTaskOne**************");
	}
}
