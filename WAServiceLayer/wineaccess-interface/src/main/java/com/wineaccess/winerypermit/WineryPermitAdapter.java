package com.wineaccess.winerypermit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.Response;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.persistence.exception.PersistenceException;


/**
 * @author abhishek.sharma1
 *
 */
public class WineryPermitAdapter extends WineaccessBaseTask { 

    public WineryPermitAdapter(String taskName, TaskConfiguration taskConfiguration) {
	super(taskName, taskConfiguration);
    }

    @Override
    protected void doExecute(ProcessContext pContext) throws Exception {

	String operationName = getOperationName(pContext);

	WineryPermitHelper wineryPermitHelper = (WineryPermitHelper) CoreBeanFactory.getBean("wineryPermitHelper");

	switch (operationName) {

	case "ADD_WINERY_PERMIT":
	    Map <String, Object> addPermit = new ConcurrentHashMap<String, Object>();
	    WineryPermitPO wineryPermitPO = (WineryPermitPO) getObject(pContext, WineryPermitPO.class);
	    try{
		addPermit = wineryPermitHelper.generateAddPermitResponse(wineryPermitPO);
		
	    }
	    catch (PersistenceException e) {
		Response response = ApplicationUtils.generateFailureResponse(SystemErrorCode.PERMIT_FAILURE_ERROR, SystemErrorCode.PERMIT_FAILURE_ERROR_TEXT, 200);
		addPermit.put("FINAL-RESPONSE", response);
	    }
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), addPermit);
	    break;

	case "VIEW_WINERY_PERMIT":
	    WineryPermitViewPO wineryPermitViewPO = (WineryPermitViewPO) getObject(pContext, WineryPermitViewPO.class);
	    Map <String, Object> viewPermit = wineryPermitHelper.generateViewPermitResponse(wineryPermitViewPO);			
	    //JAXB.marshal(wineryPermitViewPO, System.out);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), viewPermit);

	    break;

	default:
	    break; 
	}
    } 
}
