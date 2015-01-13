package com.wineaccess.sampler;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * @author gaurav.agarwal1
 * 
 */
public class SamplerAdapter extends WineaccessBaseTask {

	public SamplerAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {

		case "ADD":
			AddSamplerPO addSamplerPO = (AddSamplerPO) getObject(pContext, AddSamplerPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),SamplerAdapterHelper.addSampler(addSamplerPO));
			break;
			
		case "VIEW_DETAIL":
			ViewSamplerPO viewSamplerPO = (ViewSamplerPO) getObject(pContext, ViewSamplerPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.viewSampler(viewSamplerPO));
			break;
			
		case "VIEW_SAMPLER_LOGISTICS_DETAIL":
			
			ViewSamplerLogisticsDetailPO viewSamplerLogisticsDetailPO = (ViewSamplerLogisticsDetailPO) getObject(pContext, ViewSamplerLogisticsDetailPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.viewSamplerLogisticsDetail(viewSamplerLogisticsDetailPO));
			
			break;
			
		case "VIEW_SAMPLER_COMPLIENCE_DETAIL":
			
			ViewSamplerComplienceDetailPO viewSamplerComplienceDetailPO = (ViewSamplerComplienceDetailPO) getObject(pContext, ViewSamplerComplienceDetailPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.viewSamplerComplienceDetail(viewSamplerComplienceDetailPO));
			
			break;
			
		case "EDIT_SAMPLER_WINE":
			
			EditSamplerWinePO editSamplerWinePO = (EditSamplerWinePO) getObject(pContext, EditSamplerWinePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.editSamplerWine(editSamplerWinePO));
			
			break;		
			
		case "UPDATE":
			UpdateSamplerPO updateSamplerPO = (UpdateSamplerPO) getObject(pContext, UpdateSamplerPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.updateSampler(updateSamplerPO));
			break;
		case "REMOVE_WINE_FROM_SAMPLER":
			DeleteSamplerWinePO deleteSamplerWinePO = (DeleteSamplerWinePO) getObject(pContext, DeleteSamplerWinePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.deleteWineFromSampler(deleteSamplerWinePO));
			break;			
		case "ADD_SAMPLER_PRODUCT":
			AddSamplerProductPO addSamplerProductPO = (AddSamplerProductPO) getObject(pContext, AddSamplerProductPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.addSamplerProduct(addSamplerProductPO));
			break;
		case "LIST_SAMPLER_PRODUCT":
			ListSamplerProductPO listSamplerProductPO = (ListSamplerProductPO) getObject(pContext, ListSamplerProductPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), SamplerAdapterHelper.listSamplerProducts(listSamplerProductPO));
			break;	

		}

	}
}
