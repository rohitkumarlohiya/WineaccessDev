package com.wineaccess.service.newsletter;


import java.util.List;
import java.util.Map;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/** 
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class NewsletterAdapter extends WineaccessBaseTask {

	public NewsletterAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");

		switch (operationNameEnum) {
		    /**
		     * Case of getting the list of newsletters based on newsletter id.
		     * */
			case GET_BY_ID:
				List<Long> newsletterId = (List<Long>) inputParam.get("newsletterId");
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						NewsletterAdapterHelper.listNewslettersById(newsletterId));
				break;
			/**
		     * Case of adding the newsletter.
		     * */
			case ADD:
				NewsletterPO newsletterPO = (NewsletterPO) inputParam.get("newsletterPO");
				newsletterPO.setName(newsletterPO.getName().trim());
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						NewsletterAdapterHelper.addNewsletters(newsletterPO));
				break;
			/**
		     * Case of updating the newsletter.
		     * */
			case UPDATE:
				NewsletterPO updateNewsletterPO = (NewsletterPO) inputParam.get("newsletterPO");
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						NewsletterAdapterHelper.updateNewsletters(updateNewsletterPO));
				break;
			/**
		     * Case of deleting the newsletter based on the newsletter id passed.
		     * */
			case DELETE:
				
				NewsletterDeletePO newsletterDeletePO = (NewsletterDeletePO) inputParam.get("newsletterDeletePO");
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						NewsletterAdapterHelper.deleteNewsletters(newsletterDeletePO));
				break;
			/**
		     * Case of getting list of all the newsletters or searching for a newsletter based on a keyword passed.
		     * */
			case BASIC_SEARCH_BY_KEYWORD:
				
				String sortBy = (String) inputParam.get("sortBy");
				int sortOrder = Integer.parseInt((String) inputParam.get("sortOrder"));
				int offSet = Integer.parseInt((String) inputParam.get("offSet"));
				int limit = Integer.parseInt((String) inputParam.get("limit"));
				String keyword = (String) inputParam.get("keyword");
								
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						NewsletterAdapterHelper.searchNewslettersByKeyword(keyword,offSet,limit,sortBy,sortOrder));			
				
				break;	
			default:
				break;
		}
		
	}

}
