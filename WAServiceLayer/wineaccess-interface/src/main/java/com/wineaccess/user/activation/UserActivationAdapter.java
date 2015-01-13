package com.wineaccess.user.activation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.property.utils.ErrorPropertyHolderUtil;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.Response;

/**
 * @author gaurav.agarwal1
 *
 */
public class UserActivationAdapter extends WineaccessBaseTask {
	
	private static Log logger = LogFactory.getLog(UserActivationAdapter.class);
	
	public UserActivationAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		Map<String, Object> output = new HashMap<String, Object>();
		UserActivationPO activationPO = (UserActivationPO) getObject(pContext, UserActivationPO.class);
		Response response = null;
		
		String activationCode = activationPO.getActivationCode();
		
		UserModel userModel = UserRepository.getByActivationCode(activationCode);
		if(userModel!=null){
			if(BooleanUtils.isTrue(userModel.isDeleted()) || BooleanUtils.isNotTrue(userModel.isEnabled())){
				response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE, SystemErrorCode.USER_ACTIVATION_CODE_TEXT, 200);
			}else{
				if((userModel.getUserActivationCode().substring(0, 32)).equalsIgnoreCase(DigestUtils.md5Hex(userModel.getEmail()))){
						if(checkActivation(userModel)){
							userModel.setRegistered(true);
							userModel.setUserActivationCode(null);
							try{
								UserRepository.update(userModel);
							}catch(Exception e){
								logger.error("Update the activation in user model", e);
							}
							UserActivationVO activationVO = new UserActivationVO(userModel.getId(),userModel.getEmail(),ErrorPropertyHolderUtil.getStringProperty("USERACTIVATION101"));
							response = new com.wineaccess.response.SuccessResponse(activationVO, 200);
						}else{
							userModel.setUserActivationCode(null);
							try{
								UserRepository.update(userModel);
							}catch(Exception e){
								logger.error("Update the activation in user model", e);
							}
							response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE, SystemErrorCode.USER_ACTIVATION_CODE_TEXT, 200);
							
						}	
				}else{
					response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE, SystemErrorCode.USER_ACTIVATION_CODE_TEXT, 200);
				}
				
			}
		}else{
			response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE, SystemErrorCode.USER_ACTIVATION_CODE_TEXT, 200);
		}
		output.put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
	}

	private boolean checkActivation(UserModel userModel) {
		
		final String userActivationCode = userModel.getUserActivationCode();
		
		try {
			final Long endActivationTime = new Date().getTime();
			final Long startActivationTime = Long.parseLong(CryptoUtil.decrypt(userActivationCode.substring(userActivationCode.length()-24, userActivationCode.length())));
			final long totalTime = endActivationTime-startActivationTime;
			final long expirationTime = Long.parseLong(PropertyholderUtils.getStringProperty(PropertyConstants.URL_EXPIRATION_TIME));
			if(expirationTime>totalTime){
				return true;
			}
		} catch (Exception e) {
			logger.error("Some error occured during the decyprtion of activation code", e);
		}
		return false;
	}

}
