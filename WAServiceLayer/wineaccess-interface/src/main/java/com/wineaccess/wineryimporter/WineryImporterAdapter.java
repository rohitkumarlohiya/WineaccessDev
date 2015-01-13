package com.wineaccess.wineryimporter;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

/**
 * @author abhishek.sharma1
 *
 */
public class WineryImporterAdapter extends WineaccessBaseTask { 
	private static Log logger = LogFactory.getLog(WineryImporterAdapter.class);

	/**
	 * @param taskName
	 * @param taskConfiguration
	 */
	public WineryImporterAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	/* (non-Javadoc)
	 * @see com.wineaccess.orchestration.workflow.process.task.BaseTask#doExecute(com.wineaccess.orchestration.workflow.process.context.ProcessContext)
	 */
	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {		

		String operationName = getOperationName(pContext);

		switch (operationName) {

		case "ADD_WINERY_IMPORTER_ADDRESS":
			logger.info("Add address");
			
			WineryImporterAddressPO addressPO = (WineryImporterAddressPO) getObject(pContext, WineryImporterAddressPO.class);
			Map <String, Object> addaddress = WineryImporterAddressHelper.generateAddAddressResponse(addressPO);			

			getDataRepositoryManager().addOutput(pContext.getRequestId(), addaddress);
			break;

		case "EDIT_WINERY_IMPORTER_ADDRESS":
			logger.info("edit address");
			WineryImporterEditAddressPO editAddressPO = (WineryImporterEditAddressPO) getObject(pContext, WineryImporterEditAddressPO.class);


			Map <String, Object> editAddress = WineryImporterAddressHelper.generateUpdateAddressResponse(editAddressPO);

			getDataRepositoryManager().addOutput(pContext.getRequestId(), editAddress);
			break;

		case "VIEW_WINERY_IMPORTER_ADDRESS":
			logger.info("view address");
			WineryImporterViewAddressPO viewAddressPO = (WineryImporterViewAddressPO) getObject(pContext, WineryImporterViewAddressPO.class);
			
			Map <String, Object> viewAddress = WineryImporterAddressHelper.generateViewAddressResponse(viewAddressPO);

			getDataRepositoryManager().addOutput(pContext.getRequestId(), viewAddress);
			break;

		case "DELETE_WINERY_IMPORTER_ADDRESS":
			logger.info("delete address");
			WineryImporterDeleteAddressPO deleteAddressPO = (WineryImporterDeleteAddressPO) getObject(pContext, WineryImporterDeleteAddressPO.class);

			Map <String, Object> deleteAddress = WineryImporterAddressHelper.generateDeleteAddressReponse(deleteAddressPO);

			getDataRepositoryManager().addOutput(pContext.getRequestId(), deleteAddress);
			break;
			
		case "LIST_WINERY_IMPORTER_ADDRESS":
			logger.info("list address");
			WIAddressListingPO listAddressPO = (WIAddressListingPO) getObject(pContext, WIAddressListingPO.class);
           
			Map <String, Object> listAddress = WineryImporterAddressHelper.generateListAddressResponse(listAddressPO);

			getDataRepositoryManager().addOutput(pContext.getRequestId(), listAddress);
			break;

		}
	}

}
