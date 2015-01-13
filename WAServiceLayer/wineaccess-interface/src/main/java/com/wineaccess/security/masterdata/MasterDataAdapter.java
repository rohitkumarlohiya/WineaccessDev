package com.wineaccess.security.masterdata;

import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class MasterDataAdapter extends WineaccessBaseTask {

	public MasterDataAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam
				.get("operation");

		MasterDataPO masterDataPO = (MasterDataPO) inputParam.get("masterDataPO");
		Long id = (Long) inputParam.get("id");
		Long masterDataTypeId = (Long) inputParam.get("masterDataTypeId");
		String multipleMasterDataIds = (String) inputParam.get("multipleMasterDataIds");
		
		

		switch (operationNameEnum) {

		case ADD:
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.addMasterData(masterDataPO));

			break;

		case GET_BY_ID:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.getMasterDataById(id));

			break;

		case LIST:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.listMasterDatas());

			break;

		case UPDATE_BY_ID:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.updateMasterDataById(id,masterDataPO));
			break;

		case DELETE_BY_ID:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.deleteMasterDataById(id));
			break;
			
		case GET_LAST_UPDATED:

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.getMasterDataLastUpdated(masterDataTypeId));
			break;
			
		case MULTIPLEDELETE:
			
			boolean confirmStatus = (boolean) inputParam.get("confirmStatus");
			
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					MasterDataAdapterHelper.multipleDeleteMasterData(multipleMasterDataIds,confirmStatus));
			break;

		default:
			break;
		}

	}
}
