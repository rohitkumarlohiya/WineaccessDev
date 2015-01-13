package com.wineaccess.ApplicationUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.EnumTypes.EmailTemplateType;
import com.wineaccess.constants.EnumTypes.POStatus;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateRepository;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;
import com.wineaccess.util.notification.EmailNotifier;

public class ApplicationUtils {

	private static Log logger = LogFactory.getLog(ApplicationUtils.class);

	public static Response errorMessageGenerate(String erroCode, String erroCodeTxt, int status) {
		WineaccessError error = new WineaccessError(erroCode, erroCodeTxt);			
		Response failedResponse = new FailureResponse();
		failedResponse.setStatus(status);
		failedResponse.addError(error);
		return failedResponse;
	}
	public static javax.ws.rs.core.Response generateFailureResponse(String errorCode, String errorText, int status){

		return javax.ws.rs.core.Response.ok( errorMessageGenerate(errorCode, errorText, status)).build();

	}


	/**
	 * @param userName
	 * @param password
	 * @param userModel
	 * @return
	 */
	public static String generateToken(Long userId,String userName,String password, UserModel userModel) {
		WineAccessUserDetails wineUserDetail = new WineAccessUserDetails(userId, userName, password, new String [] {"USER"}, System.currentTimeMillis());
		TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		String token = tokenUtils.getToken(wineUserDetail);
		TokenModel tokenModel = new TokenModel(token, userModel.getId(), new Date());
		TokenModelRepository.save(tokenModel);
		return token;
	}

	/**
	 * @param obj
	 * @param property
	 * @param clazz
	 * @return
	 */
	public static boolean validateMandatoryFields(Object obj, String[] property, Class clazz) {
		try{
			for(String temp:property )
			{
				Object value = new PropertyDescriptor(temp, clazz).getReadMethod().invoke(obj);
				if(value instanceof String && StringUtils.isBlank((String)value))					
					return false;
				else if(value == null)
					return false;
				else
					continue;
			}

		}
		catch(Exception e)
		{
			if(e instanceof IllegalAccessException || e instanceof InvocationTargetException ||e instanceof IntrospectionException )
				return false;
		}
		return true;

	}



	/**
	 * @return
	 */
	public static Response invalidParamError() {
		WineaccessError error = new WineaccessError(SystemErrorCode.INVALID_PARAMS, SystemErrorCode.INVALID_PARAMS_TEXT);			
		Response failedResponse = new FailureResponse();
		failedResponse.setStatus(400);
		failedResponse.addError(error);	
		return failedResponse;
	}

	public static Date convertDateFromString(String strDate) 
	{
		return convertDate(strDate,PropertyholderUtils.getStringProperty("response.date.format"));
	}


	public static String convertDateToString(Date date){
		return covertString(date,PropertyholderUtils.getStringProperty("response.date.format"));
	}

	private static Date convertDate(String strDate, String format) {
		try 
		{
			if(!StringUtils.isEmpty(strDate)){
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date date = sdf.parse(strDate);	
				return date;
			}

		}
		catch (ParseException e) {
			return null;
		}
		return null;
	}



	private static String covertString(Date date, String format) {

		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String strDate = sdf.format(date);	
			return strDate;
		}
		return null;
	}

	public static void cleanLucenceIndex() {

		GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.indexLucene();

	}

	/**
	 * 
	 * @param data - data to which you want to convert in Hex 
	 * @return -the encrypted String in upper case.
	 */
	public static String getSHAHex(String data)
	{
		return DigestUtils.shaHex(data).toUpperCase();
	}

	/**
	 * 
	 * @param allowedFields - fields on which sorting is possible 
	 * @param sortField - sort field received through API
	 * @return -true/false based on the sort field passed.
	 */
	public static Boolean isValidSortField(String[] allowedFields, String sortField)
	{
		Boolean returnValue = false;
		if(null != allowedFields && allowedFields.length > 0){
			List<String> list = Arrays.asList(allowedFields);
			returnValue = list.contains(sortField);
		}
		return returnValue;
	}

	public static String genActivationCode(String email){
		String activationCode = null;
		try {
			activationCode = DigestUtils.md5Hex(email)+CryptoUtil.encrypt(Long.toString(new Date().getTime()));
		} catch (Exception e) {
			logger.error("Some error occured during the activation code generation", e);
		}
		return activationCode;
	}

	/**
	 * 
	 * Generating the random password during the create user
	 * Generated password will contain atleast one small letter, one capital letter,
	 *  one numeric and one special char 
	 */
	public static String genPasswordCreateUser(){
		String smallletters = "abcdefghjkmnpqrstuvwxyz";
		String capLetters = "ABCDEFGHJKMNPQRSTUVWXYZ";
		String charSpecial = "@#$";
		String numeric = "23456789";
		String pwd = "";
		Random random = new Random();
		for (int i=0; i<4; i++)
		{
			int index1 = (int)(random.nextDouble()*smallletters.length());
			pwd += smallletters.substring(index1, index1+1);
			int index2 = (int)(random.nextDouble()*capLetters.length());
			pwd += capLetters.substring(index2, index2+1);
			int index3 = (int)(random.nextDouble()*charSpecial.length());
			pwd += charSpecial.substring(index3, index3+1);
			int index4 = (int)(random.nextDouble()*numeric.length());
			pwd += numeric.substring(index4, index4+1);
		}
		return pwd;
	}

	//Send the activation mail to the user at the time of user creation
	public static void sendMailWithURL(String url,String email,String mailType,Persistent... persistents){

		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put(EmailNotifier.EMAIL_TO, email);
		EmailTemplate emailTemplate = null;
		if("activationURL".equals(mailType)){
			emailTemplate = EmailTemplateRepository.getEmailTemplateByName(EmailTemplateType.SEND_ACTIVATION.getEmailTemplateType());
		}else if("resetPassword".equals(mailType)){
			emailTemplate = EmailTemplateRepository.getEmailTemplateByName(EmailTemplateType.RESET_PASSWORD.getEmailTemplateType());
		}else if("activationURLWithPassword".equals(mailType)){
			emailTemplate = EmailTemplateRepository.getEmailTemplateByName(EmailTemplateType.CREATE_USER_PASSWORD.getEmailTemplateType());
		}else if("resendActivationURLWithPassword".equals(mailType)){
			emailTemplate = EmailTemplateRepository.getEmailTemplateByName(EmailTemplateType.RESEND_ACTIVATION_URL_PASSWORD.getEmailTemplateType());
		}else if("forgotPassword".equals(mailType)){
			emailTemplate = EmailTemplateRepository.getEmailTemplateByName(EmailTemplateType.FORGOT_PASSWORD_MAIL.getEmailTemplateType());
		}
		if(emailTemplate != null){
			attributes.put(EmailNotifier.EMAIL_FROM, emailTemplate.getFromEmail());
			String body = emailTemplate.getBody()+System.getProperty("line.separator")+url;

			try {
				EmailNotifier.notify(emailTemplate.getSubject(), body, attributes, persistents);
			} catch (Exception e) {
				logger.error("failed to send email -"+e.getMessage());
				logger.error(e);
			}
		}else{
			logger.info("email template not found,can not send the mail");
		}
	}

	//Generating the activation url at the time of user creation
	public static String generateURL(UserModel userModel,String urlType,String url){
		String generateURL = "";
		if("activationURL".equals(urlType)){
		    generateURL = url;
			if(StringUtils.contains(generateURL,"{activationCode}")){
				generateURL = generateURL.replace("{activationCode}", userModel.getUserActivationCode());
			}else{
				generateURL = url;
			}
			if (StringUtils.contains(generateURL,"{email}")) {
				String encryptEmail = StringUtils.EMPTY;
				try {
					encryptEmail = CryptoUtil.encrypt(userModel.getEmail());
				} catch (Exception e) {
					logger.error("Cannot encrypt the email during url generation", e);
				}
				generateURL = generateURL.replace("{email}", encryptEmail);
			}
			if (StringUtils.contains(generateURL,"{resetCode}")) {
				generateURL = generateURL.replace("{resetCode}",userModel.getResetCode());
			}
		}else if("resetPassword".equals(urlType)){
			generateURL = url;
			if(StringUtils.contains(generateURL,"{email}")){
				String encryptEmail = "";
				try {
					encryptEmail = CryptoUtil.encrypt(userModel.getEmail());
				} catch (Exception e) {
					logger.error("Cannot encrypt the email during url generation", e);
				}
				generateURL = generateURL.replace("{email}", encryptEmail);
			}
			if(StringUtils.contains(generateURL,"{resetCode}")){
				generateURL = generateURL.replace("{resetCode}", userModel.getResetCode());
			}
		} else if ("forgotPassword".equals(urlType)) {
			generateURL = url;
			if (StringUtils.contains(generateURL,"{email}")) {
				String encryptEmail = StringUtils.EMPTY;
				try {
					encryptEmail = CryptoUtil.encrypt(userModel.getEmail());
				} catch (Exception e) {
					logger.error("Cannot encrypt the email during url generation", e);
				}
				generateURL = generateURL.replace("{email}", encryptEmail);
			}
			if (StringUtils.contains(generateURL,"{resetCode}")) {
				generateURL = generateURL.replace("{resetCode}",userModel.getResetCode());
			}
		}
		return generateURL;
	}
	
	public static Date StringToDate1(String string){

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(string);
		} catch (Exception e) {
			logger.error("Error while converting the date.", e);
		}
		return date;
	}

	public static Date convertStringToDate(String string){

		Date date = null;
		try {
			date = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").parse(string);
		} catch (Exception e) {
			logger.error("Error while converting the date.", e);
		}
		return date;
	}
	
	/*
	 * Merge the bulk delete result
	 */
	public static void mergeDeleteResult(List bulkDeleteModel,List bulkObjDeleteModel) {
		if(bulkObjDeleteModel != null && !bulkObjDeleteModel.isEmpty()){
			for(Object obj : bulkObjDeleteModel){
				bulkDeleteModel.add(obj);
			}
		}
	}
	
	/**
	 * this method is used to generate the md5 to check the uniqueness
	 * @return
	 */
	public static String generateUniqueHash(Object ... objs){
		
		StringBuilder builder = new StringBuilder();
		for(Object obj : objs){
			if(obj != null){
				builder.append(obj);
			}
		}
		return DigestUtils.md5Hex(builder.toString()).toUpperCase();
	}
	
	public static Boolean isValidStatusChange(String fromStatus, POStatus toStatus){
		return true;
	}
	
	
}
