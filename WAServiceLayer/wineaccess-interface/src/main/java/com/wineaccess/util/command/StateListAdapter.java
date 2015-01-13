package com.wineaccess.util.command;



import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class StateListAdapter extends WineaccessBaseTask {

	public StateListAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");

		switch (operationNameEnum) {
		
		case LIST:
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					StateListAdapterHelper.listState());
			break;
		case LISTBYID:
			String stateId = (String) inputParam.get("stateId");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					StateListAdapterHelper.listStateById(stateId));
			break;
		case LISTBYCOUNTRYID:
			String countryId = (String) inputParam.get("countryId");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					StateListAdapterHelper.listStateByCountryId(countryId));
			break;
		default:
			break;
		}
		
	}
}

