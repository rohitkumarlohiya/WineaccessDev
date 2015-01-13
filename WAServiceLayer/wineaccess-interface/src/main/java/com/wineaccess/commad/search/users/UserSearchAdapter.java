package com.wineaccess.commad.search.users;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class UserSearchAdapter extends WineaccessBaseTask	{

	public UserSearchAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
try {
			
			UserSearchPO searchPO = (UserSearchPO) getObject(pContext, UserSearchPO.class);
			
			if(!((ValidationUtil.validateContent(String.valueOf(searchPO.getOffSet()), "[1-9]((\\d)+)?")) && (ValidationUtil.validateContent(String.valueOf(searchPO.getLimit()), "[-1-9]((\\d)+)?")) &&	(ValidationUtil.validateContent(String.valueOf(searchPO.getSortOrder()), "[0,1]")))) {
				Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
				getOutput().put("FINAL-RESPONSE", response);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
				return;
			}
			if(searchPO.getSearchType() == null || !((ValidationUtil.validateContent(String.valueOf(searchPO.getSearchType()),"[A,B]")))){
				Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
				getOutput().put("FINAL-RESPONSE", response);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
				return;
			}
			/*if(searchPO.getUserType() != null && !(searchPO.getUserType().equals("")) && !((ValidationUtil.validateContent(String.valueOf(searchPO.getUserType()),"[Buyers,NonBuyers,OpenOrders]")))){
				Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
				getOutput().put("FINAL-RESPONSE", response);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
				return;
			}*/
			
			String additionFilter = searchPO.getUserType() == null ? null : searchPO.getUserType().toString();
			
			UserDAO<UserModel> userDAO = (UserDAO<UserModel>) CoreBeanFactory.getBean("userDAO");
			
			UserSearchVO userVO = userDAO.getNormalSearchUserModel(searchPO.getKeyword(), Integer.parseInt(searchPO.getOffSet()), 
					Integer.parseInt(searchPO.getLimit()),searchPO.getSortBy(), Integer.parseInt(searchPO.getSortOrder()), searchPO.getSearchBy(), additionFilter, searchPO.getExclusions());
			
			Response response = new com.wineaccess.response.SuccessResponse(userVO, 200);
			
			
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
		} catch (org.apache.lucene.queryParser.ParseException parserException) {
			luceneQueryParsingErrorResponse(pContext.getRequestId());
			return;
		}catch (UserSearchException userSearchException) {
			apiProcessingErrorResponse(pContext.getRequestId(),SystemErrorCode.USER_SEARCH_INTERNAL_SERVICE_ERROR,
					SystemErrorCode.USER_SEARCH_INTERNAL_SERVICE_ERROR_TEXT, 200);
			return;
		}
	}
}
