package com.wineaccess.util.command;

import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class CountryListAdapter extends WineaccessBaseTask {

	public CountryListAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");

		switch (operationNameEnum) {
		case ADD:

			break;
		case LIST:
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					CountryListAdapterHelper.listCountry());
			break;
		case LISTBYID:	
			
			String countryId = (String) inputParam.get("countryId");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					CountryListAdapterHelper.listCountryById(countryId));
			break;
		default:
			break;
		}
		
	}
}


