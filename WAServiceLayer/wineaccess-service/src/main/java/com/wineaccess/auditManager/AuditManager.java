package com.wineaccess.auditManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.Gson;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.user.UserActivityLogs;
import com.wineaccess.security.token.TokenUtils;
import com.wineaccess.security.token.WineAccessUserDetails;
import com.wineaccess.security.token.WineaccessSecurityUserDetails;

public class AuditManager {
	
	private MongoTemplate mongoTemplate = (MongoTemplate)  CoreBeanFactory.getBean("mongoTemplate");
	
	public static enum APIEvent {
		LOGIN_EVENT, LOGOUT_EVENT, USER_NORMAL_SEARCH_EVENT, USER_ADVANCE_SEARCH_EVENT, SSO_REGISTRATION_EVENT, SIGNUP_EVENT, ADD_ROLE, UPDATE_ROLE, DELETE_ROLE,MULTIPLE_DELETE_ROLE,
		ROLE_LIST,MASTER_DATA_TYPE_LIST,MASTER_DATA_TYPE_GET_BY_ID,MASTER_DATA_TYPE_NARMAL_SEARCH_EVENT,MASTER_DATA_LIST,MASTER_DATA_GET_BY_ID,MASTER_DATA_NARMAL_SEARCH_EVENT,ADD_MASTER_DATA,
		MASTER_DATA_UPDATE_BY_ID,MASTER_DATA_DELETE_BY_ID,MASTER_DATA_DELETE_ALL,COUNTRY_LIST,STATE_LIST,CITY_LIST,LOGIN_HISTORY,CREATE_USER,UPDATE_USER,CLONE_USER,MODIFY_STATUS,DELETE_USER,
		DELETE_ADDRESS,DELETE_CC,UPDATE_USER_PASSWORD,GET_USER_DETAILS,UPDATE_USER_DETAILS,MASTER_DATA_GET_LAST_UPDATED,
		MASTER_DATA_MULTIPLE_DELETE,ENABLE_USER,DISABLE_USER,USER_DETAIL,MERGE_USER,NEWSLETTER_LIST,NEWSLETTER_LIST_BY_ID,ADD_NEWSLETTER,UPDATE_NEWSLETTER,DELETE_NEWSLETTER,SEARCH_NEWSLETTER,
		VIEW_USER_COMMENTS,VIEW_USER_COMMENTS_BY_ID,ADD_USER_COMMENT,EDIT_USER_COMMENTS_BY_ID,DELETE_USER_COMMENTS_BY_ID,MULTI_DELETE_USER_COMMENTS,LIST_EMAIL_TEMPLATE_TYPE,GET_BY_ID_EMAIL_TEMPLATE_TYPE,
		SEARCH_BY_KEYWORD_EMAIL_TEMPLATE_TYPE,LIST_EMAIL_TEMPLATE,GET_BY_ID_EMAIL_TEMPLATE,SEARCH_BY_KEYWORD_EMAIL_TEMPLATE,ADD_EMAIL_TEMPLATE,EMAIL_TEMPLATE_UPDATE_BY_ID,EMAIL_TEMPLATE_MULTIPLE_DELETE,
		EMAIL_TEMPLATE_CLONE,LIST_USER_EMAIL_LOG,SEARCH_BY_KEYWORD_USER_EMAIL_LOG,USER_ACTIVATION,RESPONSYS,RESEND_ACTIVATION_MAIL,RESET_PASSWORD_EMAIL,RESET_PASSWORD,ADDRESS_DETAIL,ADD_ADDRESS,EDIT_ADDRESS,
		ADD_CREDIT_CARD,EDIT_CREDIT_CARD,CREDIT_CARD_DETAIL,ADD_WINERY,UPDATE_WINERY,VIEW_IMPORTER,ADD_WINERY_IMPORTER_ADDRESS,EDIT_WINERY_IMPORTER_ADDRESS,VIEW_WINERY_IMPORTER_ADDRESS,
		DELETE_WINERY_IMPORTER_ADDRESS,VIEW_WINERY,ADD_CONTACT,UPDATE_CONTACT,DELETE_CONTACT,VIEW_CONTACTS,VIEW_CONTACT_DETAILS,LIST_CONTACTS,UPDATE_IMPORTER,ADD_IMPORTER,DELETE_IMPORTER,ADD_WINE
	}
	
	private static Log logger = LogFactory.getLog(AuditManager.class);
	
	static Collection<UserActivityLogs> userLogs = new CopyOnWriteArrayList<UserActivityLogs>();
	
	static {
		new Thread(new AuditManager(). new AuditLogPersister()).start();
	}
	
	public static void doAudit(Map<String,Object> input, Map<String,Object> output, String event) {
		
		Gson gson = new Gson();
		if(input != null && !input.isEmpty()){
			input.remove("currentUser");
		}
		
		String inputString = gson.toJson(input);
		String outString = gson.toJson(output);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			if (!String.valueOf(authentication.getPrincipal()).equals("anonymousUser")) {
				String token = ((WineaccessSecurityUserDetails) authentication.getPrincipal()).getToken();
				TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
				WineAccessUserDetails wineAccessUserDetails = tokenUtils.getUserFromToken(token);
				String activiti = "{" + "input" + ":" + inputString + "," + "output" + ":" + outString + "}";
				UserActivityLogs userActivityLog = new UserActivityLogs(wineAccessUserDetails.getUserId(), activiti, wineAccessUserDetails.getUserSessionId());
				userActivityLog.setCreatedBy(wineAccessUserDetails.getUserId());
				userActivityLog.setCreatedDate(Calendar.getInstance().getTime());
				userActivityLog.setEventName(event);
				userLogs.add(userActivityLog);
			} else {
				//tracking for Anonymous User
			}
		} 
	}
	
	
	private class AuditLogPersister implements Runnable {
		
		@Override
		public void run() {
			
			while (true) {
				
				List<UserActivityLogs> removeList = new ArrayList<UserActivityLogs>();
				
				Iterator<UserActivityLogs> itr = userLogs.iterator();
				
				while (itr.hasNext()) {
					UserActivityLogs userActivityLogs = itr.next();
					try {
						mongoTemplate.save(userActivityLogs);
					} catch(Exception ex) {
					}
					removeList.add(userActivityLogs);
				}
				userLogs.removeAll(removeList);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					logger.error("Error...", e);
				}
			}
		}
		
	}
}
