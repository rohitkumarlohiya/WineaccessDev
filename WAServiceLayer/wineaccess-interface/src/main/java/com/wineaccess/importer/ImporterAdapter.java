package com.wineaccess.importer;


import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/** 
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class ImporterAdapter extends WineaccessBaseTask {

	public ImporterAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {
		    
			/**
		     * Adding the importer.
		     * */
			case "ADD":
			    AddImporterPO addImporterPO = (AddImporterPO) getObject(pContext, AddImporterPO.class);
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						ImporterAdapterHelper.addImporter(addImporterPO));
				break;
			
			//View the Importer details and Key Metrics
			case "VIEW_DETAIL":
			    ViewImporterPO viewImporterPO = (ViewImporterPO) getObject(pContext, ViewImporterPO.class);
				getDataRepositoryManager().addOutput(pContext.getRequestId(), ImporterAdapterHelper.viewImporterDetails(viewImporterPO));
				break;
			/**
		     * Updating the importer.
		     * */
			case "UPDATE":
			    EditImporterPO editImporterPO = (EditImporterPO) getObject(pContext, EditImporterPO.class);
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						ImporterAdapterHelper.updateImporter(editImporterPO));
				break;
			/**
		     * Updating the importer.
		     * */
			case "DELETE":
			    DeleteImporterPO deleteImporterPO = (DeleteImporterPO) getObject(pContext, DeleteImporterPO.class);
				getDataRepositoryManager().addOutput(pContext.getRequestId(),
						ImporterAdapterHelper.deleteImporter(deleteImporterPO));
				break;
				
			case "ENABLEDISABLE":
			    ImporterEnableDisablePO enableDisablePO = (ImporterEnableDisablePO) getObject(pContext, ImporterEnableDisablePO.class);
				getDataRepositoryManager().addOutput(pContext.getRequestId(),ImporterAdapterHelper.enableDisableImporter(enableDisablePO));
				break;
					
			default:
				break;
		}
		
	}

}

