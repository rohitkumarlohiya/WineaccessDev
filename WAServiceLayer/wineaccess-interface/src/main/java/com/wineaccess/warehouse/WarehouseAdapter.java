package com.wineaccess.warehouse;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;

public class WarehouseAdapter extends WineaccessBaseTask {
	public WarehouseAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		String operationName = getOperationName(pContext);

		switch (operationName) {

		/**
		 * Adding the Warehouse.
		 * */
		case "ADD":
			AddWarehousePO addWarehousePO = (AddWarehousePO) getObject(pContext, AddWarehousePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					WarehouseAdapterHelper.addWarehouse(addWarehousePO));
			break;
			
		/**
		 * Updating the Warehouse.
		 * */
		case "UPDATE":
			UpdateWarehousePO updateWarehousePO = (UpdateWarehousePO) getObject(pContext, UpdateWarehousePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					WarehouseAdapterHelper.updateWarehouse(updateWarehousePO));
			break;
		/**
		 * Getting the Warehouse Details.
		 * */
		case "GET_BY_ID":
			ViewWarehousePO viewWarehousePO = (ViewWarehousePO) getObject(pContext, ViewWarehousePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					WarehouseAdapterHelper.viewWarehouse(viewWarehousePO));
			break;
		/**
		 * Deleting the Warehouse Details.
		 * */
		case "DELETE":
			DeleteWarehousePO deleteWarehousePO = (DeleteWarehousePO) getObject(pContext, DeleteWarehousePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					WarehouseAdapterHelper.deleteWarehouse(deleteWarehousePO));
			break;
			
		case "LIST" :
			ListWarehousePO listWarehousePO = (ListWarehousePO) getObject(pContext, ListWarehousePO.class);
			getDataRepositoryManager().addOutput(pContext.getRequestId(), WarehouseAdapterHelper.generateWarehouseList(listWarehousePO));
			break;

		default:
			break;
		}
	}
}
