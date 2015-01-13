package com.wineaccess.winerylicensedetail;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class WineryLicenseDetailAdapter extends WineaccessBaseTask { 

	public WineryLicenseDetailAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		 
		String operationName = getOperationName(pContext);
		
		switch (operationName) {
	
		case "GET_BY_ID":
			WineryLicenseDetailViewPO wineryLicenseDetailViewPO = (WineryLicenseDetailViewPO) getObject(pContext, WineryLicenseDetailViewPO.class);
		    getDataRepositoryManager().addOutput(pContext.getRequestId(),WineryLicenseDetailAdapterHelper.viewWineryLicenseDetail(wineryLicenseDetailViewPO));
		    break;

		    
		case "UPDATE_BY_ID":
			WineryLicenseDetailUpdatePO wineryLicenseDetailUpdatePO = (WineryLicenseDetailUpdatePO) getObject(pContext, WineryLicenseDetailUpdatePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineryLicenseDetailAdapterHelper.updateWineryLicenseDetail(wineryLicenseDetailUpdatePO));
			 break;   		
		    
		default:
			break; 
		}
	} 
}
