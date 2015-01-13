package com.wineaccess.emailtemplate;

import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class EmailTemplateAdapter extends WineaccessBaseTask {

	public EmailTemplateAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam
				.get("operation");

		EmailTemplatePO emailTemplatePO = (EmailTemplatePO) inputParam
				.get("emailTemplatePO");
		Long id = (Long) inputParam.get("id");

		String multipleEmailTemplateIds = (String) inputParam
				.get("multipleEmailTemplateIds");

		switch (operationNameEnum) {

		case LIST:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					EmailTemplateAdapterHelper.listEmailTemplates());

			break;

		case GET_BY_ID:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					EmailTemplateAdapterHelper.getEmailTemplateById(id));

			break;

		case BASIC_SEARCH_BY_KEYWORD:

			
			String sortBy = (String) inputParam.get("sortBy");
			
			int sortOrder = Integer.parseInt((String) inputParam.get("sortOrder"));
			int offSet = Integer.parseInt((String) inputParam.get("offSet"));
					
			int limit = Integer.parseInt((String) inputParam.get("limit"));
			String keyword = (String) inputParam.get("keyword");
			Long templateId = Long.parseLong((String.valueOf(inputParam.get("templateId"))));
			

			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateAdapterHelper.getEmailTemplatesByKeyword(
							keyword, sortBy, offSet, limit,
							sortOrder,templateId));
			break;

		case ADD:
			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateAdapterHelper
							.addEmailTemplate(emailTemplatePO));

			break;

		case UPDATE_BY_ID:

			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateAdapterHelper.updateEmailTemplateById(id,
							emailTemplatePO));
			break;

		case MULTIPLEDELETE:

			boolean confirmStatus = (boolean) inputParam.get("confirmStatus");

			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateAdapterHelper.multipleDeleteEmailTemplate(
							multipleEmailTemplateIds, confirmStatus));
			break;

		case CLONE:

			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateAdapterHelper.cloneEmailTemplateById(emailTemplatePO));
			break;

		default:
			break;
		}

	}
	
	
	

}
