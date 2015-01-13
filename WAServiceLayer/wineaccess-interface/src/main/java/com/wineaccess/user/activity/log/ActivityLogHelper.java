/**
 * 
 */
package com.wineaccess.user.activity.log;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.data.model.user.ActivityLogsRepository;
import com.wineaccess.data.model.user.UserActivityLogs;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;

/**
 * @author anurag.jain3
 *
 */
public class ActivityLogHelper {
	
	
	
	
	public static boolean isValidSessionSummaryRequest(String sortBy, Integer sortOrder, String keyword){
		boolean isValidData = false;
		
		String[] sortRegex = new String[]{"lastLogin","lastLogout","totalSessions","userid"};
		//final String sortOrderRegex = "0|1";
		Arrays.sort(sortRegex);
		
		if(((Arrays.binarySearch(sortRegex, sortBy) >= 0) && (sortOrder==0 || sortOrder ==1))){
			
			if(!keyword.equals("") && !StringUtils.isNumeric(keyword)){
				isValidData = false;
			}else{
				isValidData = true;
			}
			
		}
		
		
		
		
		return isValidData;
	}
	
	public static String generateSearchCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" Order by " + fieldName);
		sortCriteria.append((sortOrder == 0) ? " asc" :" desc");
		return sortCriteria.toString();
		
	}
	
	
	/*public static TokenVO getUserSessionSummary(final String fieldName,
			final int sortOrder, final int offset, final int limit)
			throws ActivityLogException {
		
		String searchCriteria = ActivityLogHelper.generateSearchCriteria(fieldName, sortOrder);
		TokenVO userSessionVO = new TokenVO();
		userSessionVO.setOffset(offset);
		userSessionVO.setLimit(limit);
		
		
		
		
		try {
			List<Object[]> userSessionList = TokenModelRepository.getUserAndSessionInfo(offset,limit,searchCriteria);
			
			
			
			userSessionVO.setTotalResultsCount(TokenModelRepository.getUserSessionCount());
			userSessionVO.setSearchResultsCount(Long.valueOf(userSessionList.size()));
			
			for(Object[] userSession : userSessionList){
				UserToken sessionModel = new UserToken();
				
				UserModel user = UserRepository.findUserById((Long)userSession[0]);
				com.wineaccess.user.activity.log.UserServiceModel sessionUser = new com.wineaccess.user.activity.log.UserServiceModel();
				BeanUtils.copyProperties(sessionUser, user);
				
				sessionModel.setTokenUser(sessionUser);
				sessionModel.setTotalSessions((long) (userSession[1]));
				if(userSession.length > 1)
				sessionModel.setLastLogin((Date)userSession[2]);
				if(userSession.length > 2)
				sessionModel.setLastLogout((Date)userSession[3]);
				userSessionVO.getUserTokens().add(sessionModel);
			}
		} catch (Exception  e) {
			throw new ActivityLogException();
		}
		return userSessionVO;
	}*/
	
	
	public static TokenVO getUserSessionSummary(final String fieldName,
			final int sortOrder, final int offset, final int limit, final Object keyword)
			throws ActivityLogException {
		
		String searchCriteria = ActivityLogHelper.generateSearchCriteria(fieldName, sortOrder);
		TokenVO userSessionVO = new TokenVO();
		userSessionVO.setOffset(offset+1);
		userSessionVO.setLimit(limit);
		
		try {
			List<Object[]> userSessionList = null;
			if(keyword != null){
				 userSessionList = TokenModelRepository.getUserAndSessionInfo(offset,limit,searchCriteria,(Integer)keyword);
			}else{
				 userSessionList = TokenModelRepository.getUserAndSessionInfo(offset,limit,searchCriteria);
			}
			
			
			
			
			userSessionVO.setTotalResultsCount(TokenModelRepository.getUserSessionCount());
			userSessionVO.setSearchResultsCount(Long.valueOf(userSessionList.size()));
			
			for(Object[] userSession : userSessionList){
				UserToken sessionModel = new UserToken();
				
				UserModel user = UserRepository.findUserByUserId((Long)userSession[0]);
				com.wineaccess.user.activity.log.UserServiceModel sessionUser = new com.wineaccess.user.activity.log.UserServiceModel();
				BeanUtils.copyProperties(sessionUser, user);
				
				sessionModel.setTokenUser(sessionUser);
				sessionModel.setTotalSessions((long) (userSession[1]));
				if(userSession.length > 1)
				sessionModel.setLastLogin((Date)userSession[2]);
				if(userSession.length > 2)
				sessionModel.setLastLogout((Date)userSession[3]);
				userSessionVO.getUserTokens().add(sessionModel);
			}
		} catch (Exception  e) {
			throw new ActivityLogException();
		}
		return userSessionVO;
	}

	public static UserSessionVO getSessionForUser(final Long userId, TokenModel model,UserModel user, int offset, int limit)
			throws ActivityLogException{
		UserSessionVO userSession = null;
		try {
			
			
			List<UserActivityLogs> activityLogs = ActivityLogsRepository.getSessionActivityLogs(model.getId(), offset,limit);
			List<TokenModel> otherSessions = TokenModelRepository.getUserOtherSessions(userId,"");
			
			userSession = new UserSessionVO();
			
			com.wineaccess.user.activity.log.UserServiceModel sessionUser = new com.wineaccess.user.activity.log.UserServiceModel();
			
			BeanUtils.copyProperties(sessionUser, user);
			
			userSession.setTokenUser(sessionUser);
			
			SessionModel latestSession = new SessionModel();
			ActivityInfo activityInfo = new ActivityInfo();
			activityInfo.setTotalCount(ActivityLogsRepository.getSessionActivityLogs(model.getId()).size());
			activityInfo.setLimit(limit);
			activityInfo.setOffset(offset);
			
			if(model.getSessionEndTime() == null){
				model.setSessionEndTime(new Date());
			}
			BeanUtils.copyProperties(latestSession, model);
			
			for(UserActivityLogs activityLog : activityLogs){
				
				Activity activityLogDetails = new Activity();
				
				BeanUtils.copyProperties(activityLogDetails, activityLog);
				
				activityInfo.getActivityList().add(activityLogDetails);
				/*if(activityLog.getEventName() != null){
					latestSession.getActivityList().add(activityLog.getEventName());
				}*/
			}
			latestSession.setActivityInfo(activityInfo);
			userSession.setLatestSession(latestSession);
			
			for(TokenModel otherSession : otherSessions){
				SessionModel session = new SessionModel();
				session.setId(otherSession.getId());
				session.setToken(otherSession.getToken());
				session.setSessionStartTime(otherSession.getSessionStartTime());
				
				userSession.getOtherSessions().add(session);
				
			}
		} catch (Exception  e) {
			throw new ActivityLogException();
		}
		return userSession;
	}




	
	
}
