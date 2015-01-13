package com.wineaccess.command.search.sampler;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

public class SamplerAdvSearchAdapter extends WineaccessBaseTask {

	public SamplerAdvSearchAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		SamplerAdvSearchPO samplerSearchPO = (SamplerAdvSearchPO) getObject(pContext, SamplerAdvSearchPO.class);
		
		if (!samplerSearchPO.getSearchType().equals("A")) {
			Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}
		
		SamplerSearchDAO samplerDAO = (SamplerSearchDAO) CoreBeanFactory.getBean("samplerSearchDAO");
		
		Response response = new com.wineaccess.response.SuccessResponse(samplerDAO.advanceSearch(samplerSearchPO), 200);
		getOutput().put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
	}
}
