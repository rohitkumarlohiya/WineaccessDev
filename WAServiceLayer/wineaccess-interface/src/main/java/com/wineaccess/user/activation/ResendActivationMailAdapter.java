package com.wineaccess.user.activation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserSSO;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * @author gaurav.agarwal1
 *
 */
public class ResendActivationMailAdapter extends WineaccessBaseTask{
    public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
    public ResendActivationMailAdapter(String taskName,TaskConfiguration taskConfiguration) {
	super(taskName, taskConfiguration);
    }

    @Override
    protected void doExecute(ProcessContext pContext) throws Exception {
	Map<String, Object> inputParam = getDataRepositoryManager().getInput(pContext.getRequestId());
	Map<String, Object> output = new HashMap<String, Object>();
	Response response = null;

	String email = (String) inputParam.get(ReqParamConstants.EMAIL);
	String url = (String) inputParam.get(ReqParamConstants.URL);

	UserModel userModel = UserRepository.getByUserName(email);
	List<UserSSO> userSSO = null;
	if(null != userModel){
	    userSSO = UserRepository.getUserWithSSO(userModel.getId());
	}
	if(userModel!=null){
	    if(BooleanUtils.isTrue(userModel.isDeleted()) || BooleanUtils.isNotTrue(userModel.isEnabled())){
		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);
	    }else{
		if(BooleanUtils.isTrue(userModel.getRegistered())){
		    response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ALREADY_REGISTERED, SystemErrorCode.USER_ALREADY_REGISTERED_TXT, 200);
		}

		if(null != userModel.getPassword() && !("").equals(userModel.getPassword())){
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.RESEND_ACTIVATION_ERROR,SystemErrorCode.RESEND_ACTIVATION_ERROR_TEXT, SUCCESS_CODE);
		} else if (null != userSSO && !userSSO.isEmpty() && userSSO.size() > 0) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.RESEND_ACTIVATION_ERROR,SystemErrorCode.RESEND_ACTIVATION_ERROR_TEXT, SUCCESS_CODE);

		} 


		else{

		    userModel.setUserActivationCode(ApplicationUtils.genActivationCode(userModel.getEmail()));

		    UserRepository.update(userModel);
		    ResendActivationMailVO resendActivationMailVO = new ResendActivationMailVO(userModel.getId(),userModel.getEmail(),"Mail Sent Successfully");

		    String activationURL = ApplicationUtils.generateURL(userModel,"activationURL",url);

		    ApplicationUtils.sendMailWithURL(activationURL, userModel.getEmail(),"resendActivationURLWithPassword", userModel);
		    response = new com.wineaccess.response.SuccessResponse(resendActivationMailVO, 200);
		}

	    }
	}else{
	    response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);
	}
	output.put("FINAL-RESPONSE", response);
	getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
    }

}
