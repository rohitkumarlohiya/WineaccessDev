package com.wineaccess.useremaillog;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class UserEmailLogAdapter extends WineaccessBaseTask {

    public UserEmailLogAdapter(String taskName,
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

	    getDataRepositoryManager().addOutput(pContext.getRequestId(),
		    UserEmailLogAdapterHelper.listUserEmailLogs());

	    break;


	case BASIC_SEARCH_BY_KEYWORD:

	    //String fieldName = (String) inputParam.get("fieldName");
	    String sortBy = (String) inputParam.get("sortBy");
	    String sortOrder = (String) inputParam.get("sortOrder");
	    String offSet = (String) inputParam.get("offSet");
	    String limit = (String) inputParam.get("limit");
	    String keyword = (String) inputParam.get("keyword");
	    if(StringUtils.isBlank(sortBy))
		sortBy = "userId";
	    if(sortBy.equals("deliveryDate")){
	    	sortBy="createdDate";
	    }
	    getDataRepositoryManager().addOutput(
		    pContext.getRequestId(),
		    UserEmailLogAdapterHelper.getUserEmailLogsByKeyword(
			    "userId", keyword, sortBy, Integer.valueOf(offSet), Integer.valueOf(limit),
			    Integer.valueOf(sortOrder)));
	    break;	

	default:
	    break;
	}

    }

}
