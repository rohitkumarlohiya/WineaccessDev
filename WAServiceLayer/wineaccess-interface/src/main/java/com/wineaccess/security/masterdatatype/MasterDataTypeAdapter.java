package com.wineaccess.security.masterdatatype;

import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class MasterDataTypeAdapter extends WineaccessBaseTask {

	public MasterDataTypeAdapter(String taskName,
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

		case GET_BY_ID:
			
			Long id = (Long) inputParam.get("id");	

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataTypeAdapterHelper.getMasterDataTypeById(id));

			break;

		case LIST:

			
			String status = (String) inputParam.get("status");
			
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataTypeAdapterHelper.listMasterDataTypes(status));

			break;
			
		case GET_BY_NAME:
			
			String name = String.valueOf(inputParam.get("name"));	

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataTypeAdapterHelper.getMasterDataTypeByName(name));
			
		default:
			break;
		}

	}
}
