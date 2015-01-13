package com.wineaccess.orders.requisition;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * @author gaurav.agarwal1
 *
 */
public class RequisitionAdapter extends WineaccessBaseTask{

    public RequisitionAdapter(String taskName,TaskConfiguration taskConfiguration) {
	super(taskName, taskConfiguration);
    }

    @Override
    protected void doExecute(ProcessContext pContext) throws Exception {

	String operationName = getOperationName(pContext);
	RequisitionHelper requisitionHelper = (RequisitionHelper) CoreBeanFactory.getBean("requisitionHelper");
	switch (operationName) {
	case "ADD_REQ_POWT":
		
		AddRequisitionPOForTypePOWT addRequisitionPOForTypePOWT = (AddRequisitionPOForTypePOWT) getObject(pContext, AddRequisitionPOForTypePOWT.class);  	   
        getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.addRequisition(addRequisitionPOForTypePOWT));
	    break;
	    
	case "ADD_REQ_IT":		  
		
		AddRequisitionPOForTypeIT addRequisitionPOForTypeIT = (AddRequisitionPOForTypeIT) getObject(pContext, AddRequisitionPOForTypeIT.class);	   	    
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.addRequisition(addRequisitionPOForTypeIT));	    
	    break;
	    
	case "UPDATE_REQ_POWT":
		
		EditRequisitionPOForPOWT editRequisitionPOForPOWT = (EditRequisitionPOForPOWT) getObject(pContext, EditRequisitionPOForPOWT.class);  	   
        getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.editRequisition(editRequisitionPOForPOWT));
	    break;
	    
	case "UPDATE_REQ_IT":		  
		
		EditRequisitionPOForIT editRequisitionPOForIT = (EditRequisitionPOForIT) getObject(pContext, EditRequisitionPOForIT.class);	   	    
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.editRequisition(editRequisitionPOForIT));	    
	    break;

	case "ADD_WINE_TO_REQUISTION":
	    AddWineToRequisitionPO addWineToRequisitionPO = (AddWineToRequisitionPO) getObject(pContext, AddWineToRequisitionPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.addWineToRequisition(addWineToRequisitionPO));
	    break;	
	    
	case "EDIT_WINE_TO_REQUISTION":
	    EditWineToRequisitionPO editWineToRequisitionPO = (EditWineToRequisitionPO) getObject(pContext, EditWineToRequisitionPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.editWineToRequisition(editWineToRequisitionPO));
	    break;

	case "LIST_WINE_IN_REQUISTION":
	    ListWineInRequisitionPO listWineInRequisionPO = (ListWineInRequisitionPO) getObject(pContext, ListWineInRequisitionPO.class);
	   // System.out.print(new Gson().toJson(new ListWineInRequisitionPO()));
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), requisitionHelper.listWinesInRequistion(listWineInRequisionPO));
	 
	    break;
	    
	case "UPDATE":
	    EditRequisitionPO editRequisitionPO = (EditRequisitionPO) getObject(pContext, EditRequisitionPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.editRequisition(editRequisitionPO));
	    break;
	    
	case "VIEW_REQUISITION":
		ViewRequisitionPO viewRequisitionPO = (ViewRequisitionPO) getObject(pContext, ViewRequisitionPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.viewRequisition(viewRequisitionPO));
		break;
		
	case "REMOVE_WINE_FROM_REQUISTION":
		RemoveWineFromRequisitionPO removeWineFromRequisitionPO = (RemoveWineFromRequisitionPO) getObject(pContext, RemoveWineFromRequisitionPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), requisitionHelper.removeWineFromRequisition(removeWineFromRequisitionPO));
		break;
		
	case "DELETE":
		DeleteRequisitionPO deleteRequisitionPO = (DeleteRequisitionPO) getObject(pContext, DeleteRequisitionPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.deleteRequisition(deleteRequisitionPO));
		break;
		
	case "SEND_EMAIL_TO_WINERY":
		SendEmailToWineryPO sendEmailToWineryPO = (SendEmailToWineryPO) getObject(pContext, SendEmailToWineryPO.class);
	    getDataRepositoryManager().addOutput(pContext.getRequestId(), RequisitionHelper.sendEmailToWinery(sendEmailToWineryPO));
		break;
		
	default:
	    break;
	}

    }



}
