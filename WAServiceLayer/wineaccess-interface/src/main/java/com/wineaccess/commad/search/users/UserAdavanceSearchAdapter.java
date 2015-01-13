package com.wineaccess.commad.search.users;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;

public class UserAdavanceSearchAdapter extends WineaccessBaseTask {
	
	//User Role, Rrgistration start date from and to, revenu start from and to
	public static String [] advanceSearchFields = new String [] {"firstName", "lastName", "email","id", "stateId","regStatus","isReceivedNewLetter","userType"};
	/*public static String [] rangeAdvanceSearchFields = new String [] {"registrationDate", "revenue"};*/
	public UserAdavanceSearchAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		UserSearchPO searchPO = (UserSearchPO) getObject(pContext, UserSearchPO.class);
		
		/*if(!((ValidationUtil.validateContent(String.valueOf(searchPO.getOffSet()), "[1-9]((\\d)+)?")) && (ValidationUtil.validateContent(String.valueOf(searchPO.getLimit()), "[-1-9]((\\d)+)?")) &&	(ValidationUtil.validateContent(String.valueOf(searchPO.getSortOrder()), "[0,1]")))) {
			Response response = ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}*/
		/*if(searchPO.getSearchType() == null || !((ValidationUtil.validateContent(String.valueOf(searchPO.getSearchType()),"[A,B]")))){
			Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}*/
		/*if(searchPO.getUserType() != null && !(searchPO.getUserType().equals("")) && !((ValidationUtil.validateContent(String.valueOf(searchPO.getUserType()),"[Buyers,NonBuyers,OpenOrders]")))){
			Response response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR, SystemErrorCode.USER_SEARCH_INVALID_PARAM_ERROR_TEXT, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}*/
		if(null != searchPO.getAdvanceSearchParams() && null != searchPO.getAdvanceSearchParams().getSearchCriterias()){
			if (searchPO.getAdvanceSearchParams().getSearchCriterias().isEmpty()) {
				WineaccessError error = new WineaccessError("USER502", "No Criteria Specify");
				getErrors().add(error);
			}	
		
			for (SearchCriteriaPO searchCriteria : searchPO.getAdvanceSearchParams().getSearchCriterias()) {
				try {
					String s = UserAdavanceSearchAdapter.advanceSearchFields[searchCriteria.getSearchCriteriaId() - 1];
				} catch(ArrayIndexOutOfBoundsException ex) {
					WineaccessError error = new WineaccessError("USER501", "Invalid Criteria ID");
					getErrors().add(error);
				}
			}
		}	
		if (!getErrors().isEmpty()) {
			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.setErrors(getErrors());
			
			getOutput().put("FINAL-RESPONSE", failedResponse);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
			return;
		}
		
		
		UserDAO<UserModel> userDAO = (UserDAO<UserModel>) CoreBeanFactory.getBean("userDAO");
		
		try {
			UserAdavanceSearchVO userVO = userDAO.getAdvanceSearchUserModel(searchPO.getAdvanceSearchParams(), Integer.parseInt(searchPO.getOffSet()), 
					Integer.parseInt(searchPO.getLimit()), searchPO.getSortBy(), Integer.parseInt(searchPO.getSortOrder()), searchPO.getKeyword(), searchPO.getSearchBy(), searchPO.getExclusions());
			
			userVO.setTotalRecordCount(userDAO.getUserCount(searchPO.getExclusions()));
			
			Response response = new com.wineaccess.response.SuccessResponse(userVO, 200);
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
		} catch (org.apache.lucene.queryParser.ParseException parserException) {
			luceneQueryParsingErrorResponse(pContext.getRequestId());
			return;
		}
		catch (UserSearchException userSearchException) {
			apiProcessingErrorResponse(pContext.getRequestId(),SystemErrorCode.USER_SEARCH_INTERNAL_SERVICE_ERROR,
					SystemErrorCode.USER_SEARCH_INTERNAL_SERVICE_ERROR_TEXT, 200);
			return;
		}
	}
		
}
