package com.wineaccess.application.contact;



import java.util.HashMap;
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
public class ContactDetailsAdapter extends WineaccessBaseTask {

	public ContactDetailsAdapter(String taskName, TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}

	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {

		Map<String, Object> inputParam = getDataRepositoryManager().getInput(
				pContext.getRequestId());

		OperationNameEnum operationNameEnum = (OperationNameEnum) inputParam.get("operation");

		switch (operationNameEnum) {
		case ADD_CONTACT:
			ContactDetailsPO contactDetailsPO = (ContactDetailsPO) inputParam.get("contactDetailsPO");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					ContactDetailsHelper.addContact(contactDetailsPO));
			break;
		case UPDATE_CONTACT:
			EditContactDetailsPO editContactDetailsPO = (EditContactDetailsPO) inputParam.get("editContactDetailsPO");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					ContactDetailsHelper.updateContact(editContactDetailsPO));
			break;
		case VIEW_CONTACT_DETAIL:
			ViewContactDetailsPO viewContactDetailsPO = (ViewContactDetailsPO) inputParam.get("viewContactDetailsPO");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					ContactDetailsHelper.viewContactDetail(viewContactDetailsPO));
			break;
		case DELETE:
			DeleteContactDetailsPO deleteContactDetailsPO = (DeleteContactDetailsPO) inputParam.get("deleteContactDetailsPO");
			getDataRepositoryManager().addOutput(pContext.getRequestId(),
					ContactDetailsHelper.deleteContact(deleteContactDetailsPO));
			break;
			
		case LIST_CONTACTS:
			ContactsDetailListingPO listContactDetailsPO = (ContactsDetailListingPO) inputParam.get("contactListPO");
			Map <String, Object> listAddress = new HashMap<String, Object>();			

			listAddress = ContactDetailsHelper.getContactList(listContactDetailsPO);

			getDataRepositoryManager().addOutput(pContext.getRequestId(), listAddress);
			break;

			
		default:
			break;
		}

	}

}
