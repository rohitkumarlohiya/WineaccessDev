package com.wineaccess.registration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.data.model.user.UserSSO;
import com.wineaccess.data.model.user.UserSSORepository;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;


/**
 * @author abhishek.sharma1
 * @since 30/06/2014
 * Command class for SSO sign up
 *
 */

public class RegistrationSSOHelper  {	

	/**
	 * @param registration
	 * @return
	 */
	public static Map<String, Object> populateInputParams(RegistrationPO registration) {
		Map<String, Object> inputParameters =new HashMap<String, Object>();
		inputParameters.put(ReqParamConstants.FIRSTNAME, registration.getFirstName());
		inputParameters.put(ReqParamConstants.LASTNAME, registration.getLastName());
		inputParameters.put(ReqParamConstants.EMAIL, registration.getEmail());
		//inputParameters.put(ReqParamConstants.SALUTATION, registration.getSalutation());
		inputParameters.put(ReqParamConstants.GENDER, registration.getGender());
		inputParameters.put(ReqParamConstants.ZIPCODE, registration.getZipcode());
		inputParameters.put(ReqParamConstants.STATE, registration.getState());
		inputParameters.put(ReqParamConstants.ACCESSTOKEN, registration.getAccessToken());
		inputParameters.put(ReqParamConstants.SSOSOURCE, registration.getSSOsource());
		inputParameters.put(ReqParamConstants.SSOId, registration.getSsoId());
		inputParameters.put("ipAddress", registration.getIpAddress());
		inputParameters.put("operatingSystem", registration.getOperatingSystem());
		inputParameters.put("platform", registration.getPlatform());
		inputParameters.put("browser", registration.getBrowser());
		inputParameters.put(ReqParamConstants.IS_OVERRIDE_DELETED_ENTRY, registration.getIsOverrideDelEntry());
		return inputParameters;
	}

	/**
	 * @param accessToken
	 * @param ssosource
	 * @param ssoID
	 * @param userModel
	 * @param token
	 * @return
	 */
	public static RegistrationSSOVO createSSOUser(String accessToken,String ssosource, String ssoID, UserModel userModel, String token) {
		RegistrationSSOVO registrationSSOVO;
		UserSSO userSSOModel = new UserSSO();
		userSSOModel.setSsoSource(ssosource);
		userSSOModel.setSsoToken(accessToken);
		userSSOModel.setSsoId(ssoID);
		userSSOModel.setUserModelID(userModel.getId());
		UserSSORepository.save(userSSOModel);
		registrationSSOVO = new RegistrationSSOVO(token, userModel.getFirstName(),userModel.getLastName(), userModel.getSalutation(),  "User created successfully",userModel.getEmail(),userModel.getId());
		return registrationSSOVO;
	}

	public static void saveUserSSO(String firstName, String lastName, String userName,String gender, String zipCode, String accessToken,String ssosource, String ssoID, UserModel userModel, boolean isEdit) {

		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setEmail(userName);
		userModel.setGender(gender);
		userModel.setEnabled(true);
		userModel.setZipCode(zipCode);
		if(!isEdit)
			UserRepository.save(userModel);
		else
			userModel = UserRepository.update(userModel);

		UserSSO userSSOModel = new UserSSO();
		userSSOModel.setSsoSource(ssosource);
		userSSOModel.setSsoToken(accessToken);
		userSSOModel.setSsoId(ssoID);
		userSSOModel.setUserModelID(userModel.getId());
		UserSSORepository.save(userSSOModel);
	}



	public static String generateToken(Long id, String userName,String password, String browser, String operatingSystem, String platform, String ipaddress){
		GenericDAO userRoleMapping = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		UserModel userModel = UserRepository.getByUserId(id);
		
		String [] roles = new String [userModel.getUserRoles().size()];
		int i =0;
		for (UserRoles userRole : userModel.getUserRoles()) {
			roles[i++] = userRole.getRoleName();
		}
		
		WineAccessUserDetails wineUserDetail = new WineAccessUserDetails(id, userName, password, roles, System.currentTimeMillis());

		TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		String token = tokenUtils.getToken(wineUserDetail);

		wineUserDetail.setToken(token);

		TokenModel tokenModel = new TokenModel(token, id, new Date(), browser, operatingSystem, platform, ipaddress);

		TokenModelRepository.save(tokenModel);

		wineUserDetail.setUserSessionId(tokenModel.getId());
		return token;
	}
}
