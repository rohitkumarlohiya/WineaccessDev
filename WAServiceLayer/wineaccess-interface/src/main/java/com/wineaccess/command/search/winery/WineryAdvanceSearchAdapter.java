package com.wineaccess.command.search.winery;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.Response;

public class WineryAdvanceSearchAdapter extends WineaccessBaseTask {

	public WineryAdvanceSearchAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		WineryAdvanceSearchPO winerySearchPO = (WineryAdvanceSearchPO) getObjectWithDateFormat(pContext, WineryAdvanceSearchPO.class, PropertyholderUtils.getStringProperty("response.date.format"));
		
		if (!winerySearchPO.getSearchType().equals("A")) {
			Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}
		
		WinerySearchDAO wineryDAO = (WinerySearchDAO) CoreBeanFactory.getBean("wineryDAO");
		
		Response response = new com.wineaccess.response.SuccessResponse(wineryDAO.advanceSearch(winerySearchPO), 200);
		getOutput().put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
	}
}
