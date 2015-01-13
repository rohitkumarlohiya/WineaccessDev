package com.wineaccess.command.search.po;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

public class POSearchDefinitionAdapter extends WineaccessBaseTask	{

	public POSearchDefinitionAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		POSearchPO pOSearchPO = (POSearchPO) getObject(pContext, POSearchPO.class);
		
		POSearchDAO<RequisitionModel> poDAO = (POSearchDAO<RequisitionModel>) CoreBeanFactory.getBean("poSearchDAO");
		POSearchVO poSearchVO = poDAO.normalSearch(pOSearchPO);
		
		Response response = new com.wineaccess.response.SuccessResponse(poSearchVO, 200);
		
		getOutput().put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
	}
}
