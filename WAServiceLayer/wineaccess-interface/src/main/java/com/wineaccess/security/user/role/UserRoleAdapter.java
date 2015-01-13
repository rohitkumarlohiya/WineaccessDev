package com.wineaccess.security.user.role;

import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/** 
 * 
 * @author rohit.lohiya
 * 
 */
public class UserRoleAdapter extends WineaccessBaseTask {

	public UserRoleAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");

		switch (operationNameEnum) {
		case ADD:

			UserRolePO userRolePO = (UserRolePO) inputParam.get("userRolePO");

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserRoleAdapterHelper.addUserRole(userRolePO,(String)inputParam.get("wineAccessToken")));

			break;
		case UPDATE:
						
			UserRolePO userRolePO2 = (UserRolePO) inputParam.get("userRolePO");

			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserRoleAdapterHelper.updateUserRole(userRolePO2,(String)inputParam.get("wineAccessToken")));
			
			break;
		case DELETE:
			
			int roleId = (int) inputParam.get("roleId");
			
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserRoleAdapterHelper.deleteUserRole(roleId,(String)inputParam.get("wineAccessToken")));
			break;
		case MULTIPLEDELETE:
			
			String multipleRoleIds = (String) inputParam.get("multipleRoleIds");
			
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserRoleAdapterHelper.multipleDeleteUserRole(multipleRoleIds,(String)inputParam.get("wineAccessToken")));
			
			break;
		case LIST:
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserRoleAdapterHelper.listUserRole());
			break;
		default:
			break;
		}
		
	}

}
