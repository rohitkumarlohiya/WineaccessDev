package com.wineaccess.registration;

import java.util.HashMap;
import java.util.Map;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;

/**
 * @author gaurav.agarwal1
 * 
 */
public class SignupHelper {

	public static Map<String, Object> populateInputParams(SignupPO signup) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put(ReqParamConstants.SALUTATION,signup.getSalutation());
		inputParameters.put(ReqParamConstants.FIRSTNAME, signup.getFirstName());
		inputParameters.put(ReqParamConstants.LASTNAME, signup.getLastName());
		inputParameters.put(ReqParamConstants.EMAIL, signup.getEmail());
		inputParameters.put(ReqParamConstants.PASSWORD, signup.getPassword());
		inputParameters.put(ReqParamConstants.ZIPCODE, signup.getZipCode());
		inputParameters.put(ReqParamConstants.COUNTRY, signup.getCountryId());
		inputParameters.put(ReqParamConstants.ISRECEIVEDNEWSLETTER,signup.isReceivedNewsletter());
		inputParameters.put(ReqParamConstants.IS_OVERRIDE_DELETED_ENTRY,signup.getIsOverrideDelEntry());
		inputParameters.put(ReqParamConstants.GENDER,signup.getGender());
		inputParameters.put(ReqParamConstants.URL, signup.getUrl());
		return inputParameters;
	}

	public static SignupVO createSignupUser(Map<String, Object> inputParam, UserModel userModel) {
		SignupVO signupVO;
		userModel =populateUserModel(inputParam,userModel);
		UserRepository.save(userModel);
		String activationURL = ApplicationUtils.generateURL(userModel,"activationURL",(String) inputParam.get(ReqParamConstants.URL));
		ApplicationUtils.sendMailWithURL(activationURL, userModel.getEmail(),"activationURL", userModel);
		signupVO = new SignupVO(userModel.getId(), userModel.getSalutation(), userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getZipCode(), userModel.getCountryId(), userModel.isReceivedNewLetter(),activationURL,userModel.getUserActivationCode());
		return signupVO;
	}
	
	public static SignupVO updateSignupUser(Map<String, Object> inputParam, UserModel userModel) {
		SignupVO signupVO;
		userModel = populateUserModel(inputParam,userModel);
		UserRepository.update(userModel);
		String activationURL = ApplicationUtils.generateURL(userModel,"activationURL",(String) inputParam.get(ReqParamConstants.URL));
		ApplicationUtils.sendMailWithURL(activationURL, userModel.getEmail(),"activationURL", userModel);
		signupVO = new SignupVO(userModel.getId(), userModel.getSalutation(), userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getZipCode(), userModel.getCountryId(), userModel.isReceivedNewLetter(),activationURL,userModel.getUserActivationCode());
		return signupVO;
	}
	
	public static UserModel populateUserModel(Map<String, Object> inputParam,UserModel userModel){
		userModel.setSalutation((String) inputParam.get(ReqParamConstants.SALUTATION));
		userModel.setFirstName((String) inputParam.get(ReqParamConstants.FIRSTNAME));
		userModel.setLastName((String) inputParam.get(ReqParamConstants.LASTNAME));
		userModel.setEmail((String) inputParam.get(ReqParamConstants.EMAIL));
		//Change for JIRA WA-781 on 21-07-2014  
		userModel.setPassword(ApplicationUtils.getSHAHex((String) inputParam.get(ReqParamConstants.PASSWORD)));
		userModel.setZipCode((String) inputParam.get(ReqParamConstants.ZIPCODE));
		userModel.setCountryId((Long) inputParam.get(ReqParamConstants.COUNTRY));
		userModel.setReceivedNewLetter((boolean) inputParam.get(ReqParamConstants.ISRECEIVEDNEWSLETTER));
		userModel.setDeleted(false);
		//Generate the Activation URL and Activation Code
		userModel.setUserActivationCode(ApplicationUtils.genActivationCode((String) inputParam.get(ReqParamConstants.EMAIL)));
		return userModel;
	}

}
