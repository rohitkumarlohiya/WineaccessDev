package com.wineaccess.security.logout;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;
import com.wineaccess.security.token.TokenUtils;

/**
 * @author jyoti.yadav@globallogic.com
 */ 
public class LogoutAdapter extends WineaccessBaseTask {

	public LogoutAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(pContext.getRequestId());
		String token = (String) inputParam.get("token");
		
		TokenUtils tokenUtils = (TokenUtils) CoreBeanFactory.getBean("tokenUtils");
		
		Map <String, Object> output = new HashMap<String, Object>();
		
		if (tokenUtils.getUserFromToken(token) == null) {
			WineaccessError error = new WineaccessError(SystemErrorCode.INVALID_TOKEN_ID, SystemErrorCode.INVALID_TOKEN_ID_TEXT);
			
			Response failedResponse = new FailureResponse();
			failedResponse.addError(error);
			failedResponse.setStatus(200);
			output.put("FINAL-RESPONSE", failedResponse);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
			return;
			
		}
		tokenUtils.removerToken(token);
		
		List<TokenModel> tokens = TokenModelRepository.getByToken(token);
		
		if (tokens.isEmpty()) {
			WineaccessError error = new WineaccessError(SystemErrorCode.INVALID_TOKEN_ID, SystemErrorCode.INVALID_TOKEN_ID_TEXT); 
			
			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);
			output.put("FINAL-RESPONSE", failedResponse);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
		}
		
		TokenModel tokenModel = tokens.get(0);
		tokenModel.setSessionEndTime(new Date());
		
		TokenModelRepository.update(tokenModel);
		
		Response response = new com.wineaccess.response.SuccessResponse(null, 200);
		
		output.put("FINAL-RESPONSE", response);
		getDataRepositoryManager().addOutput(pContext.getRequestId(), output);
	}

}
