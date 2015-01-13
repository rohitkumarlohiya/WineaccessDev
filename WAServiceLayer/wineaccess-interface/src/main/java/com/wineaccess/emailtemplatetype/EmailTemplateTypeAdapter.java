package com.wineaccess.emailtemplatetype;

import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class EmailTemplateTypeAdapter extends WineaccessBaseTask {

	public EmailTemplateTypeAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam
				.get("operation");

		switch (operationNameEnum) {

		case LIST:

			String status = (String) inputParam.get("status");

			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateTypeAdapterHelper
							.listEmailTemplateTypes(status));

			break;

		case GET_BY_ID:

			Long id = (Long) inputParam.get("id");

			getDataRepositoryManager()
					.addOutput(
							pContext.getRequestId(),
							EmailTemplateTypeAdapterHelper
									.getEmailTemplateTypeById(id));

			break;
		
		case GET_BY_NAME:

			String name = String.valueOf(inputParam.get("name"));

			getDataRepositoryManager()
					.addOutput(
							pContext.getRequestId(),
							EmailTemplateTypeAdapterHelper
									.getEmailTemplateTypeByName(name));

			break;

		case BASIC_SEARCH_BY_KEYWORD:
			
			
			String sortBy = (String) inputParam.get("sortBy");
			int sortOrder = Integer.parseInt((String)inputParam.get("sortOrder"));
			
			int offSet = Integer.parseInt((String)inputParam.get("offSet"));
			int limit = Integer.parseInt((String) inputParam.get("limit"));
			String keyword = (String) inputParam.get("keyword");

			getDataRepositoryManager().addOutput(
					pContext.getRequestId(),
					EmailTemplateTypeAdapterHelper
							.getEmailTemplateTypesByKeyword(keyword,
									sortBy, offSet, limit, sortOrder));

		default:
			break;
		}

	}

}
