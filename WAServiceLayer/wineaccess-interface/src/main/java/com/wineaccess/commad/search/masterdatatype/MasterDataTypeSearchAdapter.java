package com.wineaccess.commad.search.masterdatatype;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.common.MasterTypeSearchPO;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.response.Response;

/**
 * 
 * @author rohit.lohiya
 *
 */
public class MasterDataTypeSearchAdapter extends WineaccessBaseTask	{

	public MasterDataTypeSearchAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		
		MasterTypeSearchPO searchPO = (MasterTypeSearchPO) getObject(pContext, MasterTypeSearchPO.class);
		
		try {
			MasterDataTypeDAO<MasterDataType> masterDataTypeDAO = (MasterDataTypeDAO<MasterDataType>) CoreBeanFactory.getBean("masterDataTypeDAO");
			
			MasterDataTypeSearchVO masterDataTypeVO = masterDataTypeDAO.getNormalSearchMasterDataType(searchPO.getKeyword(), Integer.parseInt(searchPO.getOffSet()), 
					Integer.parseInt(searchPO.getLimit()), searchPO.getSortBy(), Integer.parseInt(searchPO.getSortOrder()), searchPO.getExclusions());
			
			Response response = new com.wineaccess.response.SuccessResponse(masterDataTypeVO, 200);			
			
			getOutput().put("FINAL-RESPONSE", response);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), getOutput());
		} catch (org.apache.lucene.queryParser.ParseException parserException) {
			luceneQueryParsingErrorResponse(pContext.getRequestId());
			return;
		}
		
	}
}
