package com.wineaccess.wine;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

public class WineAdapter extends WineaccessBaseTask { 

	public WineAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		 
		String operationName = getOperationName(pContext);
		
		switch (operationName) {

		case "ADD":
			WinePO winePO = (WinePO) getObject(pContext, WinePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.addWine(winePO));			
			break;
			
		case "BASIC_SEARCH":
			
			WineBasicSearchPO wineBasicSearchPO = (WineBasicSearchPO) getObject(pContext, WineBasicSearchPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.basicWineSearch(wineBasicSearchPO));
		    break;
		    
		case "ADVANCE_SEARCH":
			
			WineAdvanceSearchPO wineAdvanceSearchPO = (WineAdvanceSearchPO) getObject(pContext, WineAdvanceSearchPO.class);
			
			if (wineAdvanceSearchPO.getAdvanceSearchCriteria() != null) {
				for (SearchCriteriaPO searchCriteria : wineAdvanceSearchPO.getAdvanceSearchCriteria().getSearchCriterias()) {
					if (!(searchCriteria.getSearchCriteriaId() == 1 || searchCriteria.getSearchCriteriaId() == 2 || searchCriteria.getSearchCriteriaId() == 3 
							|| searchCriteria.getSearchCriteriaId() == 4 || searchCriteria.getSearchCriteriaId() == 5)) {
						Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_SEARCH_CRITERIA_ID, SystemErrorCode.INVALID_SEARCH_CRITERIA_ID, 200);
						getOutput().put("FINAL-RESPONSE", response);
						getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
						return;
					}
				}
			}
			
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.advanceWineSearch(wineAdvanceSearchPO));
		    break;

		case "VIEW_DETAIL":
		    ViewWinePO viewWinePO = (ViewWinePO) getObject(pContext, ViewWinePO.class);
		    getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.viewWineDetails(viewWinePO));
		    break;

		    
		case "UPDATE_BY_ID":
			WineUpdatePO wineUpdatePO = (WineUpdatePO) getObject(pContext, WineUpdatePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.updateWine(wineUpdatePO));
			 break;
		    
		case "ADD_WINE_LOGISTIC":
			AddLogisticPO addLogisticPO = (AddLogisticPO) getObject(pContext, AddLogisticPO.class);
		    getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.addLogistic(addLogisticPO));
		    break;
		    
		case "VIEW_WINE_LOGISTIC":
			ViewWineLogisticPO viewWineLogisticPO = (ViewWineLogisticPO) getObject(pContext, ViewWineLogisticPO.class);
		    getDataRepositoryManager().addOutput(pContext.getRequestId(),WineAdapterHelper.viewLogistic(viewWineLogisticPO));
		    break;

		case "DELETE":
			WineDeletePO deletePO = (WineDeletePO) getObject(pContext, WineDeletePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), WineAdapterHelper.deleteWine(deletePO));
			break;		
			
		case "BULK_OPERATION":
			ChangeWineStatusPO changeWineStatusPO = (ChangeWineStatusPO) getObject(pContext, ChangeWineStatusPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), WineAdapterHelper.performBulkOperation(changeWineStatusPO));
			break;		
		
		    
		default:
			break; 
		}
	} 
}
