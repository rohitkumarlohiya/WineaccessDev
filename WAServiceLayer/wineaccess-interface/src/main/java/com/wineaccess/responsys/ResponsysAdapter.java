package com.wineaccess.responsys;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ws.resp1.InteractObject;
import org.ws.resp1.ListMergeRule;
import org.ws.resp1.LoginResult;
import org.ws.resp1.MatchOperator;
import org.ws.resp1.MergeListMembers;
import org.ws.resp1.MergeResult;
import org.ws.resp1.PermissionStatus;
import org.ws.resp1.Record;
import org.ws.resp1.RecordData;
import org.ws.resp1.ResponsysWS;
import org.ws.resp1.SessionHeader;
import org.ws.resp1.UpdateOnMatch;

import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.WebServicesPropertyHolderUtils;
import com.wineaccess.third.party.ThirdPartySessionManager;


public class ResponsysAdapter extends WineaccessBaseTask {	

	private static Log logger = LogFactory.getLog(ResponsysAdapter.class);

	public ResponsysAdapter(String taskName,TaskConfiguration taskConfiguration) {
		super(taskName, taskConfiguration);
	}



	@Override
	protected void doExecute(ProcessContext pContext) throws Exception {
		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
		Map<String, Object> inputParam = getDataRepositoryManager().getInput(pContext.getRequestId());
		String frequency = (String) inputParam.get("frequency");
		try{
			ResponsysWS port = ThirdPartySessionManager.initiateResponsysPort();
			LoginResult loginResult = ThirdPartySessionManager.getInstance().getResponsysLoginResult();
			SessionHeader header = new SessionHeader();
			header.setSessionId(loginResult.getSessionId());


			/*header.setSessionId(loginResult.getSessionId());*/
			MergeListMembers mergeListMembers = new MergeListMembers();
			InteractObject interactObject = populateInteractObject();
			//mergeListMembers.setList(interactObject);
			ListMergeRule listMergeRule = populateListMergeRule();		
			mergeListMembers.setMergeRule(listMergeRule);
			RecordData recordData = populateRecordData();
			MergeResult mergeResult = port.mergeListMembers(interactObject, recordData, listMergeRule, header);
			logger.info("inserted count"+mergeResult.getInsertCount());
			logger.info("rejected count"+mergeResult.getRejectedCount());
			System.out.println("inserted count"+mergeResult.getInsertCount());
			System.out.println("rejected count"+mergeResult.getRejectedCount());
		}
		catch (Exception e) {

		}

	}



	private RecordData populateRecordData() {
		List userModelList = UserRepository.findUsersNewsLetterResponsys();
		RecordData recordData = new RecordData();
		recordData.getFieldNames().add("EMAIL_ADDRESS_");
		recordData.getFieldNames().add("EMAIL_PERMISSION_STATUS_");
         logger.info("no of user to be updated in list"+userModelList);
		for(Object userList:userModelList)
		{
			Record record = new Record();
			record.getFieldValues().add(userList.toString());
			record.getFieldValues().add("Opt_In");
			recordData.getRecords().add(record);
		}


		return recordData;
	}



	private ListMergeRule populateListMergeRule() {

		ListMergeRule listMergeRule = new ListMergeRule();
		listMergeRule.setInsertOnNoMatch(true);
		listMergeRule.setUpdateOnMatch(UpdateOnMatch.REPLACE_ALL);
		listMergeRule.setMatchColumnName1("EMAIL_ADDRESS_");
		listMergeRule.setMatchColumnName2("EMAIL_PERMISSION_STATUS_");
		listMergeRule.setMatchOperator(MatchOperator.NONE);
		listMergeRule.setOptinValue("Opt In");
		listMergeRule.setOptinValue("Opt Out");
		listMergeRule.setHtmlValue("html");
		listMergeRule.setTextValue("text");
		listMergeRule.setRejectRecordIfChannelEmpty("false");
		listMergeRule.setDefaultPermissionStatus(PermissionStatus.OPTIN);
		return listMergeRule;
	}



	private InteractObject populateInteractObject() {
		InteractObject interactObject = new InteractObject();
		///Never ever ever ever change this value
		
		String folderName = WebServicesPropertyHolderUtils.getStringProperty(PropertyConstants.RESPONSYS_FOLDER_NAME);
		String fileName = WebServicesPropertyHolderUtils.getStringProperty(PropertyConstants.RESPONSYS_FILE_NAME);
	
		try{
		interactObject.setFolderName(CryptoUtil.decrypt(folderName));
		interactObject.setObjectName(CryptoUtil.decrypt(fileName));
		}
		catch (Exception e) {
			logger.warn("folder/file name not correct, ERROR in responsys");
			System.out.println("folder/file name not correct, ERROR in responsys");
		}
		return interactObject;
	}
	
}

