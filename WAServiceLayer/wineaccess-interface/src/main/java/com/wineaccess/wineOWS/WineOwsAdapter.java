package com.wineaccess.wineOWS;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * @author gaurav.agarwal1
 * 
 */
public class WineOwsAdapter extends WineaccessBaseTask {

	public WineOwsAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {

		case "UPDATE":
			UpdateWineOwsPO updateWineOwsPO = (UpdateWineOwsPO) getObject(pContext, UpdateWineOwsPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineOwsAdapterHelper.updateWineOwsData(updateWineOwsPO));
			break;

		case "VIEW_DETAIL":
			ViewWineOwsPO viewWineOwsPO = (ViewWineOwsPO) getObject(pContext,ViewWineOwsPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineOwsAdapterHelper.viewWineOwsData(viewWineOwsPO));
			break;

		default:
			break;
		}
	}
}
