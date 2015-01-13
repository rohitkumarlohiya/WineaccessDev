package com.wineaccess.wineryOWS;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class WineryOwsAdapter extends WineaccessBaseTask { 

	public WineryOwsAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {

		case "ADD_UPDATE":
			AddUpdateWineryOwsPO addUpdateWineryOwsPO = (AddUpdateWineryOwsPO) getObject(pContext, AddUpdateWineryOwsPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineryOwsAdapterHelper.addUpdateWineryOwsData(addUpdateWineryOwsPO));
			break;

		case "VIEW_DETAIL":
			ViewWineryOwsPO viewWineryOwsPO = (ViewWineryOwsPO) getObject(pContext, ViewWineryOwsPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineryOwsAdapterHelper.viewWineryOwsData(viewWineryOwsPO));
			break;

		default:
			break;
		}
	}
}