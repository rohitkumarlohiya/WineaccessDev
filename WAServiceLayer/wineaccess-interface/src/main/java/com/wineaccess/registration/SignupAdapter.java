package com.wineaccess.registration;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * @author gaurav.agarwal1
 * 
 */
public class SignupAdapter extends WineaccessBaseTask {

	public SignupAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		// Checking whether any email Id exists in DB already
		
		UserModel userModel = UserRepository.getByUserName((String) inputParam.get(ReqParamConstants.EMAIL));
		Boolean overrideDelEntry = (Boolean) inputParam.get(ReqParamConstants.IS_OVERRIDE_DELETED_ENTRY);
		Map<String, Object> output = new HashMap<String, Object>();
		Response response = null;
		SignupVO signupVO;
		String apiAccessCode = String.valueOf(inputParam.get("API_ACCESS_CODE"));
		Long channel = getDataRepositoryManager().getApiAccessCode(apiAccessCode).getId();
		// If any email Id is already in DB, throw error
		if (userModel!=null) {

			if(BooleanUtils.isTrue(userModel.isDeleted())){
				
				if(BooleanUtils.isNotTrue(overrideDelEntry))
				{ 
					
					signupVO = new SignupVO(userModel.getId(),true);
					response =  new com.wineaccess.response.SuccessResponse(signupVO,200);
				}
				else {
					
					signupVO = SignupHelper.updateSignupUser(inputParam, userModel);
					response =  new com.wineaccess.response.SuccessResponse(signupVO,200);
				}
			}
			else if (StringUtils.isBlank(userModel.getPassword())) {

				signupVO = SignupHelper.updateSignupUser(inputParam, userModel);
				response = new com.wineaccess.response.SuccessResponse(signupVO, 200);

			} else {
				response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ALREADY_REGISTERED, SystemErrorCode.USER_ALREADY_REGISTERED_TXT, 200);
			}

		} else {
			UserModel userModel1 = new UserModel();
			userModel1.setChannelId(getDataRepositoryManager().getApiAccessCode(apiAccessCode).getId());
			signupVO = SignupHelper.createSignupUser(inputParam, userModel1);
			response = new com.wineaccess.response.SuccessResponse(signupVO,200);
		}
		output.put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
	}
}