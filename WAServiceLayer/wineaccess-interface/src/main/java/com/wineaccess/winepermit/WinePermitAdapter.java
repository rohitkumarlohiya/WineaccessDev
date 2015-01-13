package com.wineaccess.winepermit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXB;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.persistence.exception.PersistenceException;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;


/**
 * @author abhishek.sharma1
 *
 */
public class WinePermitAdapter extends WineaccessBaseTask { 

    public WinePermitAdapter(String taskName, TaskConfiguration taskConfiguration) {
	super(taskName, taskConfiguration);
    }

    @Override
    protected void doExecute(ProcessContext pContext) throws Exception {

	String operationName = getOperationName(pContext);

	WinePermitHelper winePermitHelper = (WinePermitHelper) CoreBeanFactory.getBean("winePermitHelper");

	switch (operationName) {

	case "ADD_WINE_PERMIT":
	    Map <String, Object> addPermit = new ConcurrentHashMap<String, Object>();
	    WinePermitPO winePermitPO = (WinePermitPO) getObject(pContext, WinePermitPO.class);
	    JAXB.marshal(winePermitPO, System.out);
	    try{
		addPermit = winePermitHelper.generateAddPermitResponse(winePermitPO);
		
	    }
	    catch (PersistenceException e) {
		Response response = new FailureResponse();
		response.addError(new WineaccessError(SystemErrorCode.PERMIT_FAILURE_ERROR,SystemErrorCode.PERMIT_FAILURE_ERROR_TEXT));
		//Response response = ApplicationUtils.generateFailureResponse(SystemErrorCode.PERMIT_FAILURE_ERROR, SystemErrorCode.PERMIT_FAILURE_ERROR_TEXT, 200);
		addPermit.put("FINAL-RESPONSE", response);
	    }
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), addPermit);
	    break;

	case "VIEW_WINE_PERMIT":
	    WinePermitViewPO winePermitViewPO = (WinePermitViewPO) getObject(pContext, WinePermitViewPO.class);
	    Map <String, Object> viewPermit = winePermitHelper.generateViewPermitResponse(winePermitViewPO);			
	    //JAXB.marshal(winePermitViewPO, System.out);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), viewPermit);

	    break;

	default:
	    break; 
	}
    } 
}
