package com.wineaccess.data.model.profile;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.constants.ApplicationConstants.RESPONSECODES;
import com.wineaccess.constants.EnumTypes.EmailTemplateType;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserSSO;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.registration.UserProfileVO;
import com.wineaccess.response.Response;
import com.wineaccess.util.notification.EmailNotifier;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class UserProfileAdapterHelper {

    private static Log logger = LogFactory.getLog(UserProfileAdapter.class);
    public static final int SUCCESS_CODE = RESPONSECODES.SUCCESSCODE.getResponseCodes();
    public static final String OUPUT_PARAM_KEY="FINAL-RESPONSE";

    /**
     * 
     * @param userRolePO -input parameter contain roleId,updatedRoleName and status
     * @return success if successfully  updated otherwise error code
     */
    public static Map<String, Object> updateUserPasswordById(UserProfilePasswordPO userProfilePasswordPO) {
	Map<String, Object> output = new HashMap<String, Object>();

	boolean isValidContent = ValidationUtil.validateContentFormat(userProfilePasswordPO.getNewPassword(), "(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]|.*\\s).*");
	if (!isValidContent) {
	    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_INVALID_PASSWORD, SystemErrorCode.USER_PROFILE_INVALID_PASSWORD_TEXT, SUCCESS_CODE);
	    output.put("FINAL-RESPONSE", response);

	}else{ 
	    /**
	     * Getting the User's details based on User Id.
	     */
	    UserModel userDetails = UserRepository.getByUserId(userProfilePasswordPO.getUserId());

	    boolean passwordVerified = false;
	    /**
	     * Verifying entered password matches with the saved password.
	     */
	    if(userDetails != null){
		if(null != userDetails.getPassword() && null != userProfilePasswordPO.getCurrentPassword())
		    passwordVerified = (userDetails.getPassword()).equals(ApplicationUtils.getSHAHex(userProfilePasswordPO.getCurrentPassword())) ? true : false;

		/**
		 * Updating the table with the new password.
		 */
		if(passwordVerified){

		    userDetails.setPassword(ApplicationUtils.getSHAHex(userProfilePasswordPO.getNewPassword()));
		    UserRepository.update(userDetails);
		    Response response = new com.wineaccess.response.SuccessResponse("Password Changed Successfully.", 200);
		    output.put("FINAL-RESPONSE", response);
		}
		else{

		    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PASSWORD_NOT_MATCHED, SystemErrorCode.PASSWORD_NOT_MATCHED_TEXT, 200);
		    output.put("FINAL-RESPONSE", response);		
		}
	    }else{
		Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_ERROR, SystemErrorCode.USER_PROFILE_ERROR_TEXT, 200);
		output.put("FINAL-RESPONSE", response);	
	    }
	}

	return output;
    }


    /**
     * 
     * @param userRolePO -input parameter contain roleId,updatedRoleName and status
     * @return success if successfully  updated otherwise error code
     */
    public static Map<String, Object> updateUserPasswordByEmail(UserProfilePO userProfilePO) {
	Map<String, Object> output = new HashMap<String, Object>();
	/**
	 * Getting the User's details based on User Id.
	 */
	boolean isValidContent = ValidationUtil.validateContentFormat(userProfilePO.getNewPassword(), "(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]|.*\\s).*");
	if (!isValidContent) {
	    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_INVALID_PASSWORD, SystemErrorCode.USER_PROFILE_INVALID_PASSWORD_TEXT, SUCCESS_CODE);
	    output.put("FINAL-RESPONSE", response);

	}else{ 
	    UserModel userDetails = UserRepository.getByUserName(userProfilePO.getEmail());

	    boolean passwordVerified = false;
	    /**
	     * Verifying entered password matches with the saved password.
	     */
	    if(null != userDetails.getPassword() && null != userProfilePO.getCurrentPassword())
		passwordVerified = (userDetails.getPassword()).equals(ApplicationUtils.getSHAHex(userProfilePO.getCurrentPassword())) ? true : false;

	    /**
	     * Updating the table with the new password.
	     */
	    if(passwordVerified){

		userDetails.setPassword(ApplicationUtils.getSHAHex(userProfilePO.getNewPassword()));
		UserRepository.update(userDetails);
		Response response = new com.wineaccess.response.SuccessResponse("Password Changed Successfully.", 200);
		output.put("FINAL-RESPONSE", response);
	    }
	    else{

		Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PASSWORD_NOT_MATCHED, SystemErrorCode.PASSWORD_NOT_MATCHED_TEXT, 200);
		output.put("FINAL-RESPONSE", response);		
	    }
	}	
	return output;
    }


    /**
     * 
     * @param userRolePO -input parameter contain roleId,updatedRoleName and status
     * @return success if successfully  updated otherwise error code
     */
    public static Map<String, Object> updateUserDetails(UserProfilePO userProfilePO) {
	Map<String, Object> output = new HashMap<String, Object>();
	/**
	 * Getting the User's details based on User Id.
	 */
	if(null != userProfilePO.getUserId() && !("").equals(userProfilePO.getUserId())){
	    /**
	     * Only following fields will be modified:
	     * 	> First_Name
	     * 	> Last_Name
	     * 	> Gender
	     * 	> Is_Received_News_Letter
	     * 	> Salutation
	     * 	> Country_Id
	     * 	> State_Id
	     * 	> ZipCode
	     */	
	    UserModel userDetails = UserRepository.getByUserId(userProfilePO.getUserId());
	    Response response = null;
	    if(null != userDetails){
		UserRepository.update(returnModelObject(userDetails,userProfilePO));
		response = new com.wineaccess.response.SuccessResponse("User Details Updated Successfully.", 200);
	    }				
	    else{
		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_ERROR, SystemErrorCode.USER_PROFILE_ERROR_TEXT, 200);
	    }

	    output.put("FINAL-RESPONSE", response);
	}
	else{
	    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PROFILE_NOT_UPDATED, SystemErrorCode.PROFILE_NOT_UPDATED_TEXT, 200);
	    output.put("FINAL-RESPONSE", response);
	}

	return output;
    }	


    /**
     * 
     * @param userRolePO -input parameter contain roleId,updatedRoleName and status
     * @return success if successfully  updated otherwise error code
     */
    public static Map<String, Object> updateUserDetailsByEmail(UserProfilePO userProfilePO) {
	Map<String, Object> output = new HashMap<String, Object>();
	/**
	 * Getting the User's details based on Email.
	 */
	if(null != userProfilePO.getEmail() && !("").equals(userProfilePO.getEmail())){
	    /**
	     * Only following fields will be modified:
	     * 	> First_Name
	     * 	> Last_Name
	     * 	> Gender
	     * 	> Is_Received_News_Letter
	     * 	> Salutation
	     * 	> Country_Id
	     * 	> State_Id
	     * 	> ZipCode
	     */	
	    UserModel userDetails = UserRepository.getByUserName(userProfilePO.getEmail());
	    UserRepository.update(returnModelObject(userDetails,userProfilePO));
	    Response response = new com.wineaccess.response.SuccessResponse("User Details Updated Successfully.", 200);
	    output.put("FINAL-RESPONSE", response);
	}
	else{
	    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PROFILE_NOT_UPDATED, SystemErrorCode.PROFILE_NOT_UPDATED_TEXT, 200);
	    output.put("FINAL-RESPONSE", response);
	}

	return output;
    }


    /**
     * 
     * @param userRolePO -input parameter contain roleId,updatedRoleName and status
     * @return success if successfully  updated otherwise error code
     */
    public static Map<String, Object> getUserDetails(String userId) {
	Map<String, Object> output = new HashMap<String, Object>();
	Response response = null;
	if(StringUtils.isNumeric(userId)){
	    /**
	     * Getting the User's details based on User Id.
	     */
	    UserModel userDetails = UserRepository.getByUserId(Long.parseLong(userId));


	    if(null != userDetails){

		response = new com.wineaccess.response.SuccessResponse(returnUserDetails(userDetails), 200);

	    }
	    else{

		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_USER_DETAIL_FOUND, SystemErrorCode.NO_USER_DETAIL_FOUND_TEXT, 200);
	    }
	}
	else{
	    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_PARAM_ERROR, SystemErrorCode.INVALID_PARAM_ERROR_TEXT, 200);
	}

	output.put("FINAL-RESPONSE", response);
	return output;
    }

    /**
     * 
     * @param userRolePO -input parameter contain roleId,updatedRoleName and status
     * @return success if successfully  updated otherwise error code
     */
    public static Map<String, Object> getUserDetailsByEmail(String userId) {
	Map<String, Object> output = new HashMap<String, Object>();
	/**
	 * Getting the User's details based on User Email.
	 */
	UserModel userDetails = UserRepository.getByUserName(userId);


	if(null != userDetails){

	    Response response = new com.wineaccess.response.SuccessResponse(returnUserDetails(userDetails), 200);
	    output.put("FINAL-RESPONSE", response);
	}
	else{

	    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_USER_DETAIL_FOUND, SystemErrorCode.NO_USER_DETAIL_FOUND_TEXT, 200);
	    output.put("FINAL-RESPONSE", response);		
	}

	return output;
    }

    private static UserProfileVO returnUserDetails(UserModel userDetails) {

	UserProfileVO userProfileVO = new UserProfileVO();
	userProfileVO.setCountryId(userDetails.getCountryId());
	userProfileVO.setEmail(userDetails.getEmail());
	userProfileVO.setFirstName(userDetails.getFirstName());
	userProfileVO.setGender(userDetails.getGender());
	userProfileVO.setId(userDetails.getId());
	userProfileVO.setLastName(userDetails.getLastName());
	if(null != userDetails.isReceivedNewLetter())
	    userProfileVO.setReceivedNewsletter(userDetails.isReceivedNewLetter());
	else
	    userProfileVO.setReceivedNewsletter(false);
	if(null != userDetails.getSalutation()){
		userProfileVO.setSalutation(userDetails.getSalutation());	
	}
	
	if(null != userDetails.getStateId())
	    userProfileVO.setStateId(userDetails.getStateId());
	else
	    userProfileVO.setStateId(null);
	if(null != userDetails.getZipCode())
	    userProfileVO.setZipCode(userDetails.getZipCode());
	else
	    userProfileVO.setZipCode("0");

	return userProfileVO;
    }


    private static UserModel returnModelObject(UserModel userDetails, UserProfilePO userProfilePO){

	userDetails.setCountryId(userProfilePO.getCountryId());
	userDetails.setEmail(userProfilePO.getEmail());
	userDetails.setFirstName(userProfilePO.getFirstName());
	userDetails.setLastName(userProfilePO.getLastName());
	userDetails.setIsReceivedNewLetter(userProfilePO.isReceivedNewsLetter());
	if(null != userProfilePO.getSalutation()){
		userDetails.setSalutation(userProfilePO.getSalutation());	
	}
	
	if(userProfilePO.getStateId() != null){
	    userDetails.setStateId(userProfilePO.getStateId());
	}else{
	    userDetails.setStateId(null);
	}

	userDetails.setZipCode(userProfilePO.getZipCode());
	userDetails.setGender(userProfilePO.getGender());

	return userDetails;
    }

    public static Map<String, Object> resetUserPasswordByEmail(ResetPasswordPO resetPasswordPO) {
	Map<String, Object> output = new HashMap<String, Object>();
	//Decrypt the email getting from the url 
	boolean isValidContent = ValidationUtil.validateContentFormat(resetPasswordPO.getPassword(), "(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]|.*\\s).*");
	if (!isValidContent){
	    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_INVALID_PASSWORD,	SystemErrorCode.USER_PROFILE_INVALID_PASSWORD_TEXT,SUCCESS_CODE);
	    output.put("FINAL-RESPONSE", response);
	}else{ 
	    String userEmail = "";
	    try {
		userEmail = CryptoUtil.decrypt(resetPasswordPO.getEmail());
	    } catch (Exception e) {
		logger.error("Not able to decrypt the email during reset password", e);
	    }

	    //Getting the User's details based on email.
	    UserModel userDetails = UserRepository.getByUserName(userEmail);

	    if (!userDetails.isEnabled()) {
		//Disable User Can not reset his password
	    }
	    if(userDetails != null){
		if(!resetPasswordPO.getResetCode().equals(userDetails.getResetCode())){
		    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE, SystemErrorCode.USER_ACTIVATION_CODE_TEXT, 200);
		    output.put("FINAL-RESPONSE", response);
		}else if(!checkResetPasswpordURLExpiry(userDetails)){
		    Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE, SystemErrorCode.USER_ACTIVATION_CODE_TEXT, 200);
		    output.put("FINAL-RESPONSE", response);
		}else{
		    if(!resetPasswordPO.getPassword().equals(resetPasswordPO.getConfirmPassword()))
		    {
			Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PASSWORD_NOT_MATCHED, SystemErrorCode.PASSWORD_NOT_MATCHED_TEXT, 200);
			output.put("FINAL-RESPONSE", response);
		    }else{
			userDetails.setPassword(ApplicationUtils.getSHAHex(resetPasswordPO.getPassword()));
			userDetails.setResetCode(null);
			userDetails.setRegistered(true);
			UserRepository.update(userDetails);
			//Send the confirmation Mail after reset the password, WA-1444
			EmailNotifier.SendEmail(EmailTemplateType.RESET_PASSWORD_SUCCESS.getEmailTemplateType(),userDetails.getEmail() , userDetails);
			Response response = new com.wineaccess.response.SuccessResponse("Password Changed Successfully.", 200);
			output.put("FINAL-RESPONSE", response);
		    }
		}
	    }else{
		Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_NOT_FOUND_ERROR, SystemErrorCode.USER_NOT_FOUND_ERROR_TEXT, 200);
		output.put("FINAL-RESPONSE", response);
	    }
	}
	return output;
    }

    private static boolean checkResetPasswpordURLExpiry(UserModel userModel) {
	final String userResetCode = userModel.getResetCode();

	try {
	    final Long endActivationTime = new Date().getTime();
	    final Long startActivationTime = Long.parseLong(CryptoUtil.decrypt(userResetCode.substring(userResetCode.length()-24, userResetCode.length())));
	    final long totalTime = endActivationTime-startActivationTime;
	    final long expirationTime = Long.parseLong(PropertyholderUtils.getStringProperty(PropertyConstants.URL_EXPIRATION_TIME));
	    if(expirationTime>totalTime){
		return true;
	    }
	} catch (Exception e) {
	    logger.error("Some error occured during the decyprtion of reset code", e);
	}
	return false;
    }	

    /**
     * this method is used to send the forgot password email
     * @param resetPasswordPO
     * @return output
     */
    public static Map<String, Object> forgotPasswordEmail(final ForgotPasswordMailPO forgotPasswordMailPO) {

	logger.info("Sending the mail with the URL after click on forgot password.");
	Map<String, Object> output = new ConcurrentHashMap<String, Object>();
	Response response = null;

	// Getting the User's details based on email.
	final UserModel userModel = UserRepository.getByUserName(forgotPasswordMailPO.getEmail());
	if (userModel != null) {
	    if(BooleanUtils.isNotFalse(userModel.getIsDeleted())){
		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_DELETED_ERROR,SystemErrorCode.USER_DELETED_ERROR_TEXT, SUCCESS_CODE);

	    }else if(BooleanUtils.isNotTrue(userModel.getIsEnabled())){
		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_DISABLED_ERROR,SystemErrorCode.USER_DISABLED_ERROR_TEXT, SUCCESS_CODE);

	    }else if(BooleanUtils.isNotTrue(userModel.getRegistered())){
		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_NOT_REGISTERED_ERROR,SystemErrorCode.USER_NOT_REGISTERED_ERROR_TEXT, SUCCESS_CODE);

	    }else{
		userModel.setResetCode(ApplicationUtils.genActivationCode(userModel.getEmail()));
		UserRepository.update(userModel);
		String activationURL = ApplicationUtils.generateURL(userModel, "forgotPassword",forgotPasswordMailPO.getUrl());
		ApplicationUtils.sendMailWithURL(activationURL, userModel.getEmail(),"forgotPassword" , userModel);
		response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.SEND_MAIL_TEXT,SUCCESS_CODE);
	    }

	} else {
	    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID,SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, SUCCESS_CODE);	
	}
	logger.info("Generating the forgot password mail response.");
	output.put(OUPUT_PARAM_KEY, response);
	return output;
    }

    /**
     * this method will update the forgot password
     * @param updateForgotPasswordPO
     * @return output
     */
    public static Map<String, Object> updateForgotPasswordByEmail(final UpdateForgotPasswordPO updateForgotPasswordPO) {

	logger.info("updating the forgot password");
	Map<String, Object> output = new ConcurrentHashMap<String, Object>();
	Response response = null;

	// Decrypt the email getting from the url
	boolean isValidContent = ValidationUtil.validateContent(updateForgotPasswordPO.getPassword(),"(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]|.*\\s).*");
	if (!isValidContent) {
	    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_INVALID_PASSWORD,SystemErrorCode.USER_PROFILE_INVALID_PASSWORD_TEXT,SUCCESS_CODE);

	} else if (!updateForgotPasswordPO.getPassword().equals(updateForgotPasswordPO.getConfirmPassword())) {
	    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PASSWORD_NOT_MATCHED,SystemErrorCode.PASSWORD_NOT_MATCHED_TEXT, SUCCESS_CODE);

	} else {
	    String userEmail = StringUtils.EMPTY;
	    try {
		userEmail = CryptoUtil.decrypt(updateForgotPasswordPO.getEmail());
	    } catch (Exception e) {
		logger.error("Not able to decrypt the email during update forgot password",e);
	    }

	    // Getting the User's details based on email.
	    final UserModel userDetails = UserRepository.getByUserName(userEmail);
	    if (userDetails != null) {

		if (!updateForgotPasswordPO.getResetCode().equals(userDetails.getResetCode())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE,SystemErrorCode.USER_ACTIVATION_CODE_TEXT, SUCCESS_CODE);

		} else if (!checkResetPasswpordURLExpiry(userDetails)) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE,SystemErrorCode.USER_ACTIVATION_CODE_TEXT, SUCCESS_CODE);

		} else if (BooleanUtils.isNotFalse(userDetails.getIsDeleted())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.FORGOT_PASSWORD_DELETED_ERROR,SystemErrorCode.FORGOT_PASSWORD_DELETED_ERROR_TEXT,SUCCESS_CODE);

		} else if (BooleanUtils.isNotTrue(userDetails.getIsEnabled())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.FORGOT_PASSWORD_DISABLED_ERROR,SystemErrorCode.FORGOT_PASSWORD_DISABLED_ERROR_TEXT,SUCCESS_CODE);

		} else if (BooleanUtils.isNotTrue(userDetails.getRegistered())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.FORGOT_PASSWORD_NOT_REGISTERED_ERROR,SystemErrorCode.FORGOT_PASSWORD_NOT_REGISTERED_ERROR_TEXT,SUCCESS_CODE);

		} else {
		    userDetails.setPassword(ApplicationUtils.getSHAHex(updateForgotPasswordPO.getPassword()));
		    userDetails.setResetCode(null);
		    UserRepository.update(userDetails);

		    // Send the confirmation Mail after reset the password,
		    EmailNotifier.SendEmail(EmailTemplateType.UPDATE_FORGOT_PASSWORD_SUCCESS.getEmailTemplateType(), userDetails.getEmail(), userDetails);
		    response = new com.wineaccess.response.SuccessResponse(SystemErrorCode.UPDATE_PASSWORD_TEXT, SUCCESS_CODE);
		}

	    } else {
		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_NOT_FOUND_ERROR,SystemErrorCode.USER_NOT_FOUND_ERROR_TEXT,SUCCESS_CODE);
	    }
	}
	logger.info("generating the response of update forgot password");
	output.put(OUPUT_PARAM_KEY, response);
	return output;
    }

    /**
     * this method will update the forgot password
     * @param updateForgotPasswordRequest
     * @return output
     */
    public static Map<String, Object> registerUserPassword(final UpdateForgotPasswordPO updateForgotPasswordPO) {

	logger.info("registering the forgot password");
	Map<String, Object> output = new ConcurrentHashMap<String, Object>();
	Response response = null;
	CreateUserPasswordVO createPasswordVO = new CreateUserPasswordVO();
	boolean isPasswordSet = false;
	// Decrypt the email getting from the url
	boolean isValidContent = ValidationUtil.validateContent(updateForgotPasswordPO.getPassword(),"(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]|.*\\s).*");
	if (!isValidContent) {
	    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PROFILE_INVALID_PASSWORD,SystemErrorCode.USER_PROFILE_INVALID_PASSWORD_TEXT,SUCCESS_CODE);

	} else if (!updateForgotPasswordPO.getPassword().equals(updateForgotPasswordPO.getConfirmPassword())) {
	    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.PASSWORD_NOT_MATCHED,SystemErrorCode.PASSWORD_NOT_MATCHED_TEXT, SUCCESS_CODE);

	} else {
	    String userEmail = StringUtils.EMPTY;
	    try {
		userEmail = CryptoUtil.decrypt(updateForgotPasswordPO.getEmail());
	    } catch (Exception e) {
		logger.error("Not able to decrypt the email during registering password",e);
	    }

	    // Getting the User's details based on email.
	    final UserModel userDetails = UserRepository.getByUserName(userEmail);
	    List<UserSSO> userSSO = new ArrayList<UserSSO>();
	    if(null != userDetails){
		userSSO = UserRepository.getUserWithSSO(userDetails.getId());
	    }			
	    if (userDetails != null) {
		if(StringUtils.isNotBlank(userDetails.getPassword()) || CollectionUtils.isNotEmpty(userSSO)){
		    isPasswordSet = true;
		    createPasswordVO.setPasswordSet(true);
		    response = new com.wineaccess.response.SuccessResponse(createPasswordVO, SUCCESS_CODE);
		    //response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PASSWORD_ERROR_102,SystemErrorCode.USER_PASSWORD_ERROR_102_TEXT, SUCCESS_CODE);
		} else if (BooleanUtils.isNotTrue(isPasswordSet) && BooleanUtils.isTrue(userDetails.getRegistered())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_PASSWORD_ERROR_101,SystemErrorCode.USER_PASSWORD_ERROR_101_TEXT, SUCCESS_CODE);

		} else if (BooleanUtils.isNotTrue(isPasswordSet) && !updateForgotPasswordPO.getResetCode().equals(userDetails.getResetCode())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE,SystemErrorCode.USER_ACTIVATION_CODE_TEXT, SUCCESS_CODE);

		} else if (BooleanUtils.isNotTrue(isPasswordSet) && !checkResetPasswpordURLExpiry(userDetails)) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_ACTIVATION_CODE,SystemErrorCode.USER_ACTIVATION_CODE_TEXT, SUCCESS_CODE);

		} else if (BooleanUtils.isNotTrue(isPasswordSet) && BooleanUtils.isNotFalse(userDetails.getIsDeleted())) {
		    response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.FORGOT_PASSWORD_DELETED_ERROR,SystemErrorCode.FORGOT_PASSWORD_DELETED_ERROR_TEXT,SUCCESS_CODE);

		} else {
		    userDetails.setPassword(ApplicationUtils.getSHAHex(updateForgotPasswordPO.getPassword()));
		    userDetails.setResetCode(null);
		    userDetails.setUserActivationCode(null);
		    userDetails.setRegistered(true);
		    UserRepository.update(userDetails);

		    // Send the confirmation Mail after registering the password,
		    EmailNotifier.SendEmail(EmailTemplateType.CREATE_USER_PASSWORD_SUCCESS.getEmailTemplateType(), userDetails.getEmail(), userDetails);
		    createPasswordVO.setMessage(SystemErrorCode.UPDATE_PASSWORD_TEXT_101);
		    response = new com.wineaccess.response.SuccessResponse(createPasswordVO, SUCCESS_CODE);
		}

	    } else {
		response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_NOT_FOUND_ERROR,SystemErrorCode.USER_NOT_FOUND_ERROR_TEXT,SUCCESS_CODE);
	    }
	}
	logger.info("generating the response of register password");
	output.put(OUPUT_PARAM_KEY, response);
	return output;
    }
}

