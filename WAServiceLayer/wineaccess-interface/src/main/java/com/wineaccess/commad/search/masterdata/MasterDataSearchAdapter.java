package com.wineaccess.commad.search.masterdata;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * 
 * @author rohit.lohiya
 *
 */
public class MasterDataSearchAdapter extends WineaccessBaseTask	{

	public MasterDataSearchAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		MasterDataSearchPO masterDataSearchPO = (MasterDataSearchPO) getObject(pContext, MasterDataSearchPO.class);
		try {
			MasterDataDAO<MasterData> masterDataDAO = (MasterDataDAO<MasterData>) CoreBeanFactory.getBean("masterDataDAO");
			Response response =  masterDataDAO.getNormalSearchMasterData(masterDataSearchPO.getKeyword(), Integer.parseInt(masterDataSearchPO.getOffSet()), Integer.parseInt(masterDataSearchPO.getLimit()), masterDataSearchPO.getSortBy(), Integer.parseInt(masterDataSearchPO.getSortOrder()), masterDataSearchPO.getMasterDataTypeName());			
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
		} catch (org.apache.lucene.queryParser.ParseException parserException) {
			luceneQueryParsingErrorResponse(pContext.getRequestId());
			return;
		}
		
	}
}
