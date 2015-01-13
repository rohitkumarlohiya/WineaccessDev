package com.wineaccess.util.command;


	
import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class CityListAdapter extends WineaccessBaseTask {

	public CityListAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");
		CityListAdapterHelper helper = (CityListAdapterHelper) CoreBeanFactory.getBean("cityListAdapterHelper");
		
		switch (operationNameEnum) {
		case LIST:
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					CityListAdapterHelper.listCity());
			break;
		case LISTBYID:
			String cityId = (String) inputParam.get("cityId");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					CityListAdapterHelper.listCityById(cityId));
			break;
		case LISTBYSTATEID:
			String stateId = (String) inputParam.get("stateId");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					helper.listCityByStateId(stateId));
			break;
		default:
			break;
		}
		
	}
}

