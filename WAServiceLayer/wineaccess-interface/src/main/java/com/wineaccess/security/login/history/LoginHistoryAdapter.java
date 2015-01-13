package com.wineaccess.security.login.history;

import java.util.Map;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.application.constants.ReqParamConstants;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * @author gaurav.agarwal1
 * 
 */
public class LoginHistoryAdapter extends WineaccessBaseTask {

	public LoginHistoryAdapter(String taskName,
			TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);

	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());
		Long userId = 0L;
		OperationNameEnum opsName = (OperationNameEnum) inputParam.get("ops");
		Response response = null;
		LoginHistoryVO historyVO = null;
		/**
		 * Getting the parameters from request to create criteria.
		 * */
		final String fieldName = (String) getDataRepositoryManager().getInput(
				pContext.getRequestId()).get("sortBy");
		final int sortOrder = Integer.parseInt(String
				.valueOf(getDataRepositoryManager().getInput(
						pContext.getRequestId()).get("sortOrder"))); 
				
		final int offset = Integer.parseInt(String
				.valueOf(getDataRepositoryManager().getInput(
						pContext.getRequestId()).get("offset")));
		final int limit = Integer.parseInt(String
				.valueOf(getDataRepositoryManager().getInput(
						pContext.getRequestId()).get("limit")));
		UserModel userModel = null;
		switch (opsName) {
		case USERID:
			
			userModel = UserRepository.getByUserId(Long.parseLong((String) inputParam
					.get(ReqParamConstants.USERID)));
		
			break;
		case EMAIL:
			userModel = UserRepository.getByUserName((String) inputParam
					.get(ReqParamConstants.EMAIL));
			
			break;
		default:
			break;
		}
		if(null != userModel){
			userId = userModel.getId();
		}
		
		
		/**
		 * Creating the sort criteria based on parameters - field name and sort order
		 * */
		String sortingCriteria = LoginHistoryHelper.generateSortingCriteria(
				fieldName, sortOrder);
		/**
		 * Creating the paging criteria based on parameters - offset and limit
		 * */
		/*String pagingCriteria = LoginHistoryHelper.generatePagingCriteria(
				offset, limit);*/

		/**
		 * Creating a common criteria that contains the sort criteria and paging criteria
		 * */
		//String criteria = LoginHistoryHelper.createCriteria(sortingCriteria,pagingCriteria);
		historyVO = LoginHistoryHelper.populateLoginHistory(userId,
				sortingCriteria, offset, limit, historyVO);

		if (historyVO == null) {
			response = ApplicationUtils.errorMessageGenerate(
					SystemErrorCode.LOGIN_HISTORY_NOT_EXISTS,
					SystemErrorCode.LOGIN_HISTORY_NOT_EXISTS_TXT, 200);
		} else {
			historyVO.setOffset(offset);
			historyVO.setLimit(limit);
			
			response = new com.wineaccess.response.SuccessResponse(historyVO,
					200);
		}
		getOutput().put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(),
				getOutput());

	}
}
