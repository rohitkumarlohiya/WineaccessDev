package com.wineaccess.responsys;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.BindingProvider;
import org.ws.resp1.AccountFault_Exception;
import org.ws.resp1.CampaignFault_Exception;
import org.ws.resp1.FolderResult;
import org.ws.resp1.GetJobRunStatus;
import org.ws.resp1.GetJobRunStatusResponse;
import org.ws.resp1.InteractObject;
import org.ws.resp1.LaunchPreferences;
import org.ws.resp1.LaunchResult;
import org.ws.resp1.LaunchStatusResult;
import org.ws.resp1.ListMergeRule;
import org.ws.resp1.LoginResult;
import org.ws.resp1.MatchOperator;
import org.ws.resp1.PermissionStatus;
import org.ws.resp1.ProgressChunk;
import org.ws.resp1.ProofLaunchOptions;
import org.ws.resp1.ProofLaunchType;
import org.ws.resp1.QueryColumn;
import org.ws.resp1.Record;
import org.ws.resp1.RecordData;
import org.ws.resp1.ResponsysWS;
import org.ws.resp1.ResponsysWSService;
import org.ws.resp1.SessionHeader;
import org.ws.resp1.CampaignType;
import org.ws.resp1.UnexpectedErrorFault_Exception;
import org.ws.resp1.UpdateOnMatch;



/**
 * @author abhishek.sharma1
 *
 */
public class SOAPRequest {
	public static void main(String[] args) throws CampaignFault_Exception{
		LoginResult result;
		try {
			ResponsysWS port = new ResponsysWSService().getResponsysWS();
			((BindingProvider)port).getRequestContext().put(
					BindingProvider.SESSION_MAINTAIN_PROPERTY,true);
			result = port.login("api@wineaccs", "Ri1618pin");

			SessionHeader header = new SessionHeader();
			header.setSessionId(result.getSessionId());
			List<FolderResult> folderList = port.listFolders(header);
			InteractObject interactObject = new InteractObject();
			interactObject.setFolderName("!GL_Development");
			interactObject.setObjectName("!GL_Development");
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("EMAIL_ADDRESS_");
			RecordData recordData = new RecordData();
			List<String> ids = new ArrayList<String>();
			ids.add("1468278309");

			port.retrieveListMembers(interactObject, QueryColumn.EMAIL_ADDRESS, fieldList, ids, header);
			List<Record> records = new ArrayList<Record>();
			Record record = new Record();
			List<String> fieldValues = new ArrayList<String>();


			ListMergeRule listMergeRule = new ListMergeRule();
			listMergeRule.setDefaultPermissionStatus(PermissionStatus.OPTIN);
			listMergeRule.setInsertOnNoMatch(true);
			listMergeRule.setMatchColumnName1("EMAIL_ADDRESS_");
			listMergeRule.setMatchColumnName2("EMAIL_FORMAT_");
			listMergeRule.setMatchColumnName3("MOBILE_NUMBER_");
			listMergeRule.setMatchOperator(MatchOperator.OR);
			listMergeRule.setOptinValue("true");
			listMergeRule.setOptoutValue("false");
			listMergeRule.setRejectRecordIfChannelEmpty("true");
			listMergeRule.setTextValue("text");
			listMergeRule.setUpdateOnMatch(UpdateOnMatch.REPLACE_ALL);



			//port.mergeListMembers(interactObject, recordData, listMergeRule, header);
			//campaign(port, header);
			/*	for(FolderResult folder:folderList)
			{
				System.out.println(folder.getName());
			}*/



			//System.out.println(port.logout(header));
		}
		catch (AccountFault_Exception e) {
			System.out.println("error-------");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnexpectedErrorFault_Exception e) {
			System.out.println("error1-------");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("sdvfdvdf");
			e.printStackTrace();
		}
	}

	private static void campaign(ResponsysWS port, SessionHeader header)
			throws CampaignFault_Exception, UnexpectedErrorFault_Exception {
		InteractObject interactObject = new InteractObject();
		interactObject.setFolderName("!GL_Development");
		interactObject.setObjectName("testcampiagn");
		ProofLaunchOptions proofLaunchOptions = new ProofLaunchOptions();
		proofLaunchOptions.setProofEmailAddress("amit.a@globallogic.com");
		proofLaunchOptions.setProofLaunchType(ProofLaunchType.LAUNCH_TO_ADDRESS);
		LaunchPreferences launchPreferences = new LaunchPreferences();
		launchPreferences.setEnableLimit(true);
		launchPreferences.setProgressEmailAddresses("amit.a@globallogic.com");
		launchPreferences.setRecipientLimit(new Long(1));
		launchPreferences.setEnableNthSampling(false);
		launchPreferences.setEnableProgressAlerts(false);
		launchPreferences.setSamplingNthInterval(1);
		launchPreferences.setSamplingNthOffset(1);
		launchPreferences.setSamplingNthSelection(1);
		launchPreferences.setProgressChunk(ProgressChunk.CHUNK_10_K);
		LaunchResult launchresult = port.launchCampaign(interactObject, proofLaunchOptions, launchPreferences, header);
		System.out.println(launchresult.getLaunchId());
		Long launchId = launchresult.getLaunchId();
		List<Long> launchIdList = new ArrayList<Long>();
		launchIdList.add(launchId);
		List<LaunchStatusResult> launchIdList1 = port.getLaunchStatus(launchIdList, header);
		for(LaunchStatusResult launchStatusResult: launchIdList1)
		{
			System.out.println("campaign---"+launchStatusResult.getCampaign());
			System.out.println("launch state---"+launchStatusResult.getLaunchState());
			System.out.println("launch type---"+launchStatusResult.getLaunchType());
			System.out.println("launch id---"+launchStatusResult.getLaunchId());
		}
	}




}