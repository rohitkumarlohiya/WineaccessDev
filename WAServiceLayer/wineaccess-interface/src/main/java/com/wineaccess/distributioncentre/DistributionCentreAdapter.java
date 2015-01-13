package com.wineaccess.distributioncentre;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * @author gaurav.agarwal1
 * 
 */
public class DistributionCentreAdapter extends WineaccessBaseTask {

	public DistributionCentreAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {
		case "ADD":
			AddDistributionCentrePO distributionCentrePO = (AddDistributionCentrePO) getObject(pContext, AddDistributionCentrePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),DistributionCentreAdapterHelper.addDistributionCentre(distributionCentrePO));
			break;

		case "UPDATE":
			UpdateDistributionCentrePO updateDistributionCentrePO = (UpdateDistributionCentrePO) getObject(pContext, UpdateDistributionCentrePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), DistributionCentreAdapterHelper.updateDistributionCentre(updateDistributionCentrePO));
			break;
			
		case "VIEW_DETAIL":
			ViewDistributionCentrePO viewDistributionCentrePO = (ViewDistributionCentrePO) getObject(pContext, ViewDistributionCentrePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), DistributionCentreAdapterHelper.viewDistributionCentre(viewDistributionCentrePO));
			break;
			
		case "DELETE":
			DeleteDistributionCentrePO deleteDistributionCentrePO = (DeleteDistributionCentrePO) getObject(pContext, DeleteDistributionCentrePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), DistributionCentreAdapterHelper.deleteDistributionCentre(deleteDistributionCentrePO));
			break;
			
		case "LIST":
			DistributionCentreListingPO listingPO = (DistributionCentreListingPO) getObject(pContext, DistributionCentreListingPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), DistributionCentreAdapterHelper.generateListDCWarehouseLocations(listingPO));
			
		default:
			break;
		}

	}

}
