package com.wineaccess.util.notification;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGrid.Response;
import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateRepository;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.UserEmailLog;
import com.wineaccess.data.model.user.UserEmailLogRepository;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.third.party.ThirdPartySessionManager;

public class EmailNotifier {
	
	private static Log logger = LogFactory.getLog(EmailNotifier.class);
	
	public static final String EMAIL_TO = "TO";
	public static final String EMAIL_FROM = "FROM";
	public static final String EMAIL_CC = "CC";
	public static final String SPILITER = ",";
	public static final String[] IGNORE_TOKENS={"user.password"};
	
	public static Response notify(String subject, String message, Map<String, String> attributes, Persistent ... persistents) throws Exception {
		
		Map<String, String> placeholders = new HashMap<String, String>();
		UserEmailLog userEmailLog = new UserEmailLog();
		
		if (persistents != null) {
			for (Persistent persistent : persistents) {
				placeholders.putAll(PlaceHolderUtil.getMapFromObject(persistent, persistent.getClass()));
				for(String token : IGNORE_TOKENS){
	                placeholders.remove(token);
	            }
			}
		}
		
		IMessageFormatter messageFormater = new DefaultMessageFormatterImpl();
		subject = messageFormater.format(subject, placeholders);
		message = messageFormater.format(message, placeholders);
		String userId = messageFormater.format("{user.id}", placeholders);
		
		String toReceiverStr = (String) attributes.get(EmailNotifier.EMAIL_TO);
		
		String[] toReceivers = null;
		Email email = new Email();
		
		if (StringUtils.isEmpty(toReceiverStr)) {
			logger.error("No Receivers configured for this Notification");
			return null;
		}
		toReceivers = toReceiverStr.split(SPILITER);	
		
		for (String toReceiver : toReceivers) {
			email.addTo(toReceiver);			
			userEmailLog.setUserId(UserRepository.getByUserName(toReceiver).getId());
		}
		
		if (attributes.get(EmailNotifier.EMAIL_CC) != null) {
			String ccReceiverStr = (String) attributes.get(EmailNotifier.EMAIL_CC);
			String[] bccReceivers = null;
			
			if (!StringUtils.isEmpty(ccReceiverStr)) {
				bccReceivers = ccReceiverStr.split(SPILITER);
				for (String ccReceiver : bccReceivers) {
					email.addBcc(ccReceiver);
				}
			}
		}
		
		String fromAddress = StringUtils.EMPTY;
		if(StringUtils.isNotBlank((String) attributes.get(EMAIL_FROM))){
			fromAddress = (String) attributes.get(EMAIL_FROM);
		}else{
			fromAddress = PropertyholderUtils.getStringProperty(PropertyConstants.SUPPORT_EMAIL);
		}
			
		email.setFrom(fromAddress);
		email.setSubject(subject);
		email.setHtml(message);
		
		Response response = ThirdPartySessionManager.getInstance().getSendGridSession().send(email);

		userEmailLog.setSubject(subject);
		userEmailLog.setContent(message);
		
		/*try {
			userEmailLog.setUserId(Long.parseLong(userId));
		} catch (Exception e) {
			userEmailLog.setUserId(null);			
		}*/
		
		userEmailLog.setDeliveryStatus(response.getStatus());
		UserEmailLogRepository.save(userEmailLog);
		
		return response;
	}
	
	public static void SendEmail(String emailTemplateType, String emailTo, Persistent ... persistents)
	{		
		
		EmailTemplate emailTemplate = EmailTemplateRepository.getEmailTemplateByName(emailTemplateType);	
		
		if(emailTemplate != null)
		{			
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put(EmailNotifier.EMAIL_TO, emailTo);
			attributes.put(EmailNotifier.EMAIL_FROM, emailTemplate.getFromEmail());
			
			try {
				EmailNotifier.notify(emailTemplate.getSubject(), emailTemplate.getBody(), attributes, persistents);				
			} catch (Exception e) {
				logger.error("failed to send email -"+e.getMessage());
				logger.error(e);
			}
		}
		else
		{
			logger.info("email template type not found/No active email template found");
		}
		
		
	}
	
	
}
