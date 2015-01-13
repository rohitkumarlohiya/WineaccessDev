package com.wineaccess.command.search.importer;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

public class ImporterAdvanceSearchAdapter extends WineaccessBaseTask {
	
	public ImporterAdvanceSearchAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		ImporterAdvanceSearchPO importerSearchPO = (ImporterAdvanceSearchPO) getObject(pContext, ImporterAdvanceSearchPO.class);
		
		if (!importerSearchPO.getSearchType().equals("A")) {
			Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}
		
		ImporterSearchDAO importerDAO = (ImporterSearchDAO) CoreBeanFactory.getBean("importerDAO");
		
		Response response = new com.wineaccess.response.SuccessResponse(importerDAO.normalSearch(importerSearchPO), 200);
		getOutput().put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
	}
}
