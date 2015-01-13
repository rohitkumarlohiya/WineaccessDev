package com.wineaccess.third.party;

import javax.xml.ws.BindingProvider;

import org.ws.resp1.LoginResult;
import org.ws.resp1.ResponsysWS;
import org.ws.resp1.ResponsysWSService;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.sendgrid.SendGrid;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.property.utils.WebServicesPropertyHolderUtils;

public class ThirdPartySessionManager {

	private static ThirdPartySessionManager sessionManager = null;
	private SendGrid sendGrid = null;
	private LoginResult responsysLoginResult = null;
	private  static ResponsysWS port = null;
	private BraintreeGateway braintreeGateway = null;


	public static synchronized ThirdPartySessionManager getInstance() {
		if (sessionManager == null) {
			sessionManager = new ThirdPartySessionManager();
		}
		return sessionManager;
	}

	public void initSendGridSession() throws Exception {
		sendGrid = new SendGrid(CryptoUtil.decrypt(WebServicesPropertyHolderUtils.getStringProperty("sendgrid.account.username")), CryptoUtil.decrypt(WebServicesPropertyHolderUtils.getStringProperty("sendgrid.account.password")));
	}

	public SendGrid getSendGridSession() {
		if (sendGrid == null) {
			try {
				initSendGridSession();
			} catch (Exception e) {
			}
		}
		return sendGrid;
	}

	public void initResponsysLoginResult() throws Exception {
		responsysLoginResult = port.login(CryptoUtil.decrypt(WebServicesPropertyHolderUtils.getStringProperty("responsys.account.username")), CryptoUtil.decrypt(WebServicesPropertyHolderUtils.getStringProperty("responsys.account.password")));

	}

	public static synchronized ResponsysWS initiateResponsysPort() {
		if(port==null){
			port = new ResponsysWSService().getResponsysWS();
			((BindingProvider)port).getRequestContext().put(
					BindingProvider.SESSION_MAINTAIN_PROPERTY,true);
		}
		return port;
	}


	public LoginResult getResponsysLoginResult() {
		if (responsysLoginResult == null) {
			try {
				initResponsysLoginResult();
			} catch (Exception e) {
			}
		}
		return responsysLoginResult;
	}
	
	public void initBraintreeGatewaySession() throws Exception {
		braintreeGateway = new BraintreeGateway(Environment.SANDBOX,WebServicesPropertyHolderUtils.getStringProperty("braintree.merchant.id"), WebServicesPropertyHolderUtils.getStringProperty("braintree.public.key"),WebServicesPropertyHolderUtils.getStringProperty("braintree.private.key"));
	}
	
	public BraintreeGateway getBraintreeGatewaySession() {
		if (braintreeGateway == null) {
			try {
				initBraintreeGatewaySession();
			} catch (Exception e) {
			}
		}
		return braintreeGateway;
	}

}
