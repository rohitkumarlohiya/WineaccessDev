package com.wineaccess.winery;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class WineryAdapter extends WineaccessBaseTask { 

	public WineryAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);
		
		WineryPO wineryPO = null;
		WineryUpdatePO wineryUpdatePO = null;
		WineryDeletePO wineryDeletePO = null;
		
		switch (operationName) {

		case "ADD":
			wineryPO = (WineryPO) getObject(pContext, WineryPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					WineryAdapterHelper.addWinery(wineryPO));

			break;
			
		case "UPDATE_BY_ID":
			wineryUpdatePO = (WineryUpdatePO) getObject(pContext, WineryUpdatePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					WineryAdapterHelper.updateWinery(wineryUpdatePO));

			break;	
			
		case "DELETE":
			wineryDeletePO = (WineryDeletePO) getObject(pContext, WineryDeletePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineryAdapterHelper.deleteWinery(wineryDeletePO));

			break;	
			
		case "VIEW_DETAIL":
			ViewWineryPO viewWineryPO = (ViewWineryPO) getObject(pContext, ViewWineryPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineryAdapterHelper.viewWineryDetails(viewWineryPO));

			break;
			
		case "ENABLEDISABLE":
			WineryEnableDisablePO enableDisablePO = (WineryEnableDisablePO) getObject(pContext, WineryEnableDisablePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), WineryAdapterHelper.enableDisableWinery(enableDisablePO));
			break;
			
		default:
			break;
		}
	}
}
