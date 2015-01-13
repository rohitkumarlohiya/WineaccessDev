package com.wineaccess.security.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class LoginAdapter extends WineaccessBaseTask { 

	public LoginAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		long stime = System.currentTimeMillis();
		Map <String, Object> output = new HashMap<String, Object>();
		
		LoginPO userPO = (LoginPO) getObject(pContext, LoginPO.class);
		
		String [] property = {"username","password"};
		boolean isValidInput = ApplicationUtils.validateMandatoryFields(userPO, property, LoginPO.class);

		if(!isValidInput) {
			output.put("FINAL-RESPONSE", javax.ws.rs.core.Response.ok(ApplicationUtils.invalidParamError()));
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;
		}
		
		//Change for JIRA WA-781 on 21-07-2014  
		UserModel userModel = UserRepository.getByUserNamePassword(userPO.getUsername(), ApplicationUtils.getSHAHex(userPO.getPassword()));

		Response response = null;

		if (userModel == null) {
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_USER_PASSWORD, SystemErrorCode.INVALID_USER_PASSWORD_TEXT, 200);

		} else if(userModel != null && BooleanUtils.isFalse(userModel.isEnabled())){
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DISABLED_USER, SystemErrorCode.DISABLED_USER_TEXT, 200);

		} else if(userModel != null && BooleanUtils.isNotTrue(userModel.getRegistered())){
			response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_USER_PASSWORD, SystemErrorCode.INVALID_USER_PASSWORD_TEXT, 200);

		} else{
			
			String [] roles = new String [userModel.getUserRoles().size()];
			int i =0;
			for (UserRoles userRole : userModel.getUserRoles()) {
				roles[i++] = userRole.getRoleName();
			}
			
			WineAccessUserDetails wineUserDetail = new WineAccessUserDetails(userModel.getId(), userPO.getUsername(), userPO.getPassword(), roles, System.currentTimeMillis());

			TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
			String token = tokenUtils.getToken(wineUserDetail);

			wineUserDetail.setToken(token);

			TokenModel tokenModel = new TokenModel(token, userModel.getId(), new Date(), userPO.getBrowser(), userPO.getOperatingSystem(), userPO.getPlatform(), userPO.getIpAddress());

			TokenModelRepository.save(tokenModel);

			wineUserDetail.setUserSessionId(tokenModel.getId());

			LoginVO loginVO = new LoginVO(userModel.getId(),userModel.getEmail(),token, userModel.getFirstName(), userModel.getLastName(), userModel.getSalutation());

			response = new com.wineaccess.response.SuccessResponse(loginVO, 200);
		}
		output.put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
		System.out.println("Time to Long " + (System.currentTimeMillis() - stime));
	}
}
