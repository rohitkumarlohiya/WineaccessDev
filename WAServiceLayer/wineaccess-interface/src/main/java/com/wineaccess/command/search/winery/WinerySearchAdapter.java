package com.wineaccess.command.search.winery;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

public class WinerySearchAdapter extends WineaccessBaseTask {

	public WinerySearchAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		WinerySearchPO winerySearchPO = (WinerySearchPO) getObject(pContext, WinerySearchPO.class);
		
		if (!winerySearchPO.getSearchType().equals("B")) {
			Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}
		
		WinerySearchDAO wineryDAO = (WinerySearchDAO) CoreBeanFactory.getBean("wineryDAO");
		
		Response response = new com.wineaccess.response.SuccessResponse(wineryDAO.normalSearch(winerySearchPO), 200);
		getOutput().put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
	}
}
