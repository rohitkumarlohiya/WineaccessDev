/**
 * 
 */
package com.wineaccess.user.activity.log;

import java.util.HashMap;
import java.util.Map;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * @author anurag.jain3
 * 
 * Adapter class for User Activity Logs
 *
 */
public class UserActivityLogAdapter extends WineaccessBaseTask {

	public UserActivityLogAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
		
	}

	
	
	
	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		final OperationNameEnum operationName = (OperationNameEnum) getDataRepositoryManager().getInput(pContext.getRequestId()).get("operationName");
		Response response = null;
		switch(operationName){
			case USERSSESSIONSSUMMARY:{
				
				final String fieldName = (String) getDataRepositoryManager().getInput(pContext.getRequestId()).get("sortBy");
				final int sortOrder = Integer.parseInt(String
						.valueOf(getDataRepositoryManager().getInput(
								pContext.getRequestId()).get("sortOrder"))); 
				final int offset = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("offset")));
				final int limit = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("limit")));
				Integer keyword = null;
				if(!(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("keyword"))).equals("")){
					keyword = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("keyword")));
				}
				 
				try {
					TokenVO userSessionSummary = ActivityLogHelper.getUserSessionSummary(fieldName,
							sortOrder, offset-1, limit,keyword);
						response = new com.wineaccess.response.SuccessResponse(userSessionSummary, 200);
				} catch (Exception e) {
					if(e instanceof ActivityLogException){
						response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.SESSION_SUMMARY_PROCCESSING_ERROR,WineaccessErrorCodes.SystemErrorCode.SESSION_SUMMARY_PROCCESSING_ERROR_TEXT, 200);
					}else{
						throw e;
					}
				}
				
				
				break;
			}
			case SESSIONINFO_FOR_USER:{
				final Long userId = (Long) getDataRepositoryManager().getInput(pContext.getRequestId()).get("userid");
				final int offset = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("offset")));
				final int limit = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("limit")));
				try {
					UserModel user = UserRepository.findUserByUserId(userId);
					if(null == user){
						throw new ActivityLogException(WineaccessErrorCodes.SystemErrorCode.USER_NOT_FOUND_ERROR,WineaccessErrorCodes.SystemErrorCode.USER_NOT_FOUND_ERROR_TEXT,200);
					}
					else{
						TokenModel model  = TokenModelRepository.getUserLatestLoginSession(userId);
						if(model == null){
							throw new ActivityLogException();
						}
						UserSessionVO userSession = ActivityLogHelper.getSessionForUser(userId,model,user, offset-1, limit);
						response = new com.wineaccess.response.SuccessResponse(userSession, 200);
					}					
				} catch (Exception e) {
					if(e instanceof ActivityLogException){
						ActivityLogException activityLogException = (ActivityLogException) e;
						if(null != activityLogException.getStatusErrorCode())
							response = ApplicationUtils.errorMessageGenerate(activityLogException.getStatusErrorCode(),activityLogException.getErrorMessage(), activityLogException.getStatusCode());
						else
							response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.LATEST_USER_SESSION_INFO_ERROR,WineaccessErrorCodes.SystemErrorCode.LATEST_USER_SESSION_INFO_ERROR_TEXT, 200);
					}else{
						throw e;
					}
				}
				
				break;
			}
			
			case SESSIONINFO_FOR_USER_SESSION:{
				final Long userId = (Long) getDataRepositoryManager().getInput(pContext.getRequestId()).get("userid");
				final Long sessionId = (Long) getDataRepositoryManager().getInput(pContext.getRequestId()).get("sessionId");
				final int offset = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("offset")));
				final int limit = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("limit")));
				try {
					UserModel user = UserRepository.getByUserId(userId);
					if(null == user){
						throw new ActivityLogException(WineaccessErrorCodes.SystemErrorCode.USER_NOT_FOUND_ERROR,WineaccessErrorCodes.SystemErrorCode.USER_NOT_FOUND_ERROR_TEXT,200);
					}
					else{
						
						TokenModel model  = TokenModelRepository.getUserSession(userId,sessionId);
						UserSessionVO userSession = ActivityLogHelper.getSessionForUser(userId,model,user, offset-1, limit);
						response = new com.wineaccess.response.SuccessResponse(userSession, 200);						
					}
					
				} catch (Exception e) {
					if(e instanceof ActivityLogException){
						ActivityLogException activityLogException = (ActivityLogException) e;
						if(null != activityLogException.getStatusErrorCode())
							response = ApplicationUtils.errorMessageGenerate(activityLogException.getStatusErrorCode(),activityLogException.getErrorMessage(), activityLogException.getStatusCode());
						else
							response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.USER_SESSION_INFO_ERROR,WineaccessErrorCodes.SystemErrorCode.USER_SESSION_INFO_ERROR_TEXT, 200);
					}else{
						throw e;
					}
				}
				
				break;
			}
			case BASIC_SEARCH_BY_KEYWORD:{
				
				final String fieldName = (String) getDataRepositoryManager().getInput(pContext.getRequestId()).get("sortBy");
				final int sortOrder = (Integer) getDataRepositoryManager().getInput(pContext.getRequestId()).get("sortOrder");
				final int offset = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("offset")));
				final int limit = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("limit")));
				final int keyword = Integer.parseInt(String.valueOf(getDataRepositoryManager().getInput(pContext.getRequestId()).get("keyword")));
				
				try {
					TokenVO userSessionSummary = ActivityLogHelper.getUserSessionSummary(fieldName,
							sortOrder, offset-1, limit, keyword);
						response = new com.wineaccess.response.SuccessResponse(userSessionSummary, 200);
				} catch (Exception e) {
					if(e instanceof ActivityLogException){
						response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.SESSION_SUMMARY_PROCCESSING_ERROR,WineaccessErrorCodes.SystemErrorCode.SESSION_SUMMARY_PROCCESSING_ERROR_TEXT, 200);
					}else{
						throw e;
					}
				}
				
				break;
			}
		default:
			break;
		}
		
		Map<String, Object> output = new HashMap<String, Object>();
		output.put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(),output);
		
	}




	




	

	
	
	
}
