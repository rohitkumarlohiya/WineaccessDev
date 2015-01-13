package com.wineaccess.third.party.api.winedirect;

import org.winedirect.AuthenticationInfo;
import org.winedirect.Dtc;
import org.winedirect.GetInventoryRequest;
import org.winedirect.GetInventoryResponse2;

public class SoapTest {
	
	public static void main(String[] args) {
		GetInventoryRequest inventoryRequest = new GetInventoryRequest();
		
		AuthenticationInfo authInfo = new AuthenticationInfo();
		authInfo.setPassword("WineDirect");
		authInfo.setUserName("test_account_4");
		inventoryRequest.setAuthenticationInfo(authInfo);
		
		inventoryRequest.setAccountNumber("657076");
		
	    GetInventoryResponse2 response = new Dtc().getDtcSoap().getInventory(inventoryRequest );
	    System.out.println(response.getOutcome().getResult());
	}

}
