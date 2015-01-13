package com.wineaccess.data.model.profile;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class UserProfileAdapter extends WineaccessBaseTask {

	

	public UserProfileAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {
		case "UPDATEPASSWORDBYID":
						
			UserProfilePasswordPO userProfilePasswordPO = (UserProfilePasswordPO) getObject(pContext,UserProfilePasswordPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserProfileAdapterHelper.updateUserPasswordById(userProfilePasswordPO));
			
			break;
		case "UPDATEPASSWORDBYEMAIL":
			
			UserProfilePO userProfilePOObj = (UserProfilePO) getObject(pContext,UserProfilePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserProfileAdapterHelper.updateUserPasswordByEmail(userProfilePOObj));
			
			break;
		case "LISTBYID":
			
			ListUserByIdPO listUserByIdPO = (ListUserByIdPO) getObject(pContext,ListUserByIdPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserProfileAdapterHelper.getUserDetails(listUserByIdPO.getUserId()));
			break;
		case "LISTBYEMAIL":
			
			ListUserByEmailPO listUserByEmailPO = (ListUserByEmailPO) getObject(pContext,ListUserByEmailPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserProfileAdapterHelper.getUserDetailsByEmail(listUserByEmailPO.getEmail()));
			break;	
		case "UPDATEBYID":
			
			UserProfilePO userDetailsPO = (UserProfilePO) getObject(pContext,UserProfilePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserProfileAdapterHelper.updateUserDetails(userDetailsPO));
			break;
		case "UPDATEBYEMAIL":
			
			UserProfilePO userDetails = (UserProfilePO) getObject(pContext,UserProfilePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					UserProfileAdapterHelper.updateUserDetailsByEmail(userDetails));
			break;
			
		case "RESET_PASSWORD":
			
			ResetPasswordPO resetPasswordPO = (ResetPasswordPO) getObject(pContext,ResetPasswordPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),UserProfileAdapterHelper.resetUserPasswordByEmail(resetPasswordPO));
			break;
			
		case "FORGOT_PASSWORD_EMAIL":
			
			ForgotPasswordMailPO forgotPasswordMailPO = (ForgotPasswordMailPO) getObject(pContext,ForgotPasswordMailPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),UserProfileAdapterHelper.forgotPasswordEmail(forgotPasswordMailPO));
			break;
			
		case "UPDATE_FORGOT_PASSWORD":
			
			UpdateForgotPasswordPO updateForgotPasswordPO = (UpdateForgotPasswordPO) getObject(pContext,UpdateForgotPasswordPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),UserProfileAdapterHelper.updateForgotPasswordByEmail(updateForgotPasswordPO));
			break;
			
		case "REGISTER_USER_PASSWORD":
			
			UpdateForgotPasswordPO updateForgotPasswordRequest = (UpdateForgotPasswordPO) getObject(pContext,UpdateForgotPasswordPO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),UserProfileAdapterHelper.registerUserPassword(updateForgotPasswordRequest));
			break;
		
		default:
			break;
		}
		
	}

}

