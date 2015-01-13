package com.wineaccess.registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserSSO;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;




/**
 * @author abhishek.sharma1
 * @since 30/06/2014
 * Adapter class for SSO sign up
 *
 */
public class RegistrationSSOAdapter extends WineaccessBaseTask {	

	public RegistrationSSOAdapter(String taskName,TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration); 
	}

	
	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		//Creating and populating input param map
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(pContext.getRequestId());
		String firstName = (String) inputParam.get(ReqParamConstants.FIRSTNAME);
		String lastName = (String) inputParam.get(ReqParamConstants.LASTNAME);
		String userName = (String) inputParam.get(ReqParamConstants.EMAIL);
		String gender = (String) inputParam.get(ReqParamConstants.GENDER);
		String zipCode = (String) inputParam.get(ReqParamConstants.ZIPCODE);
		String state = (String) inputParam.get(ReqParamConstants.STATE);
		String accessToken = (String) inputParam.get(ReqParamConstants.ACCESSTOKEN);		
		String ssosource = (String) inputParam.get(ReqParamConstants.SSOSOURCE);
		String ssoID = (String) inputParam.get(ReqParamConstants.SSOId);	
		String ipAddress = (String)inputParam.get("ipAddress");
		String browser = (String)inputParam.get("browser");
		String operatingSystem = (String)inputParam.get("operatingSystem");
		String platform = (String)inputParam.get("platform");
		Boolean overrideDelEntry  = (Boolean) inputParam.get(ReqParamConstants.IS_OVERRIDE_DELETED_ENTRY);
		String apiAccessCode = String.valueOf(inputParam.get("API_ACCESS_CODE"));
		//Checking whether any email Id exists in DB already
		UserModel userModel = UserRepository.getByUserName(userName);		
		RegistrationSSOVO registrationSSOVO ;
		Map <String, Object> output = new HashMap<String, Object>();

		//Check If email Id is already in DB
		if (userModel!=null ) {
			if(BooleanUtils.isTrue(userModel.isDeleted())){

				if(BooleanUtils.isNotTrue(overrideDelEntry))
				{ 
					registrationSSOVO = new RegistrationSSOVO(userModel.getId(),true);
					generateResponse(pContext.getRequestId(), registrationSSOVO, output);
					return;

				}
				else {
					userModel.setPassword(null);
					userModel.setDeleted(false);
					userModel.setChannelId(getDataRepositoryManager().getApiAccessCode(apiAccessCode).getId());
					RegistrationSSOHelper.saveUserSSO(firstName, lastName, userName, gender, zipCode, accessToken,ssosource, ssoID, userModel,true);
					String token = RegistrationSSOHelper.generateToken(userModel.getId(),userName, "",browser,operatingSystem,platform,ipAddress);
					registrationSSOVO = new RegistrationSSOVO(token, userModel.getFirstName(),userModel.getLastName(), userModel.getSalutation(),  "User created successfully",userModel.getEmail(),userModel.getId());

					generateResponse(pContext.getRequestId(), registrationSSOVO, output);
					return;
				}
			}
			if(!BooleanUtils.isTrue(userModel.isEnabled())){
				Object res = ApplicationUtils.errorMessageGenerate(SystemErrorCode.DISABLED_USER, SystemErrorCode.DISABLED_USER_TEXT, 200);				
				output.put("FINAL-RESPONSE", res);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
				return;
			}

			String token = RegistrationSSOHelper.generateToken(userModel.getId(),userName, "",browser,operatingSystem,platform,ipAddress);
			List<UserSSO> userSSOModelList = UserRepository.getUserWithSSO(userModel.getId());
			//If user is not registered with SSO login, create User and generate token
			if (userSSOModelList.isEmpty())
			{
				userModel.setChannelId(getDataRepositoryManager().getApiAccessCode(apiAccessCode).getId());
				registrationSSOVO = RegistrationSSOHelper.createSSOUser(accessToken, ssosource,ssoID, userModel, token);
			}
			//else authenticate User and generate token
			else {
				registrationSSOVO = new RegistrationSSOVO(token, userModel.getFirstName(),userModel.getLastName(), userModel.getSalutation(),  "User authenticated successfully",userModel.getEmail(),userModel.getId());
			}
			generateResponse(pContext.getRequestId(), registrationSSOVO, output);

			return;

		}
		//If email Id is not already in DB, create  user and generate token
		else{
			//TODO Implement role when its been implemented fully
			/*UserRoles userRole = new UserRoles();
			userRole.setStatus(false);
			userRole.setRoleName("USER");*/	

			userModel = new UserModel();
			userModel.setChannelId(getDataRepositoryManager().getApiAccessCode(apiAccessCode).getId());
			RegistrationSSOHelper.saveUserSSO(firstName, lastName, userName, gender, zipCode, accessToken,ssosource, ssoID, userModel,false);

			String token =RegistrationSSOHelper.generateToken(userModel.getId(),userName, "",browser,operatingSystem,platform,ipAddress);
			registrationSSOVO = new RegistrationSSOVO(token, userModel.getFirstName(),userModel.getLastName(), userModel.getSalutation(),  "User created successfully",userModel.getEmail(),userModel.getId());

			generateResponse(pContext.getRequestId(), registrationSSOVO, output);
			return;

		}

	}
	
	
	private void generateResponse(String  requestId,RegistrationSSOVO registrationSSOVO, Map<String, Object> output) {
		Response response = new com.wineaccess.response.SuccessResponse(registrationSSOVO, 200);

		output.put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(requestId, output);

		return;
	}
	
}
