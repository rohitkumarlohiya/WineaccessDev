package com.wineaccess.winelicensedetail;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class WineLicenseDetailAdapter extends WineaccessBaseTask { 

	public WineLicenseDetailAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		 
		String operationName = getOperationName(pContext);
		
		switch (operationName) {	
		
		case "GET_BY_ID":
			WineLicenseDetailViewPO wineLicenseDetailViewPO = (WineLicenseDetailViewPO) getObject(pContext, WineLicenseDetailViewPO.class);
		    getDataRepositoryManager().addOutput(pContext.getRequestId(),WineLicenseDetailAdapterHelper.viewWineLicenseDetail(wineLicenseDetailViewPO));
		    break;

		    
		case "UPDATE_BY_ID":
			WineLicenseDetailUpdatePO wineLicenseDetailUpdatePO = (WineLicenseDetailUpdatePO) getObject(pContext, WineLicenseDetailUpdatePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineLicenseDetailAdapterHelper.updateWineLicenseDetail(wineLicenseDetailUpdatePO));
			 break;   		
		    
		default:
			break; 
		}
	} 
}
