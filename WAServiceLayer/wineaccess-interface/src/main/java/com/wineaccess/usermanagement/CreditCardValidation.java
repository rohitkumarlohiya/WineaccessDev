package com.wineaccess.usermanagement;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.wineaccess.third.party.ThirdPartySessionManager;

/**
 * @author gaurav.agarwal1
 * 
 */
public class CreditCardValidation {
	
	private static Log logger = LogFactory.getLog(CreditCardValidation.class);

	public static Map<String,String> createCreditCard(CreditCardModel creditCardModel) {
		
		BraintreeGateway gateway = ThirdPartySessionManager.getInstance().getBraintreeGatewaySession();
		
		CustomerRequest request = new CustomerRequest()
			.creditCard()
			.number(creditCardModel.getCreditCardNumber())
			.expirationDate(creditCardModel.getExpirationDate())
			.options()
				.verifyCard(true)
				.done()
			.done();

		Result<Customer> result = gateway.customer().create(request);
		Map<String, String> map = new HashMap<String, String>();
		if(result.isSuccess()){
			map.put("profileId",result.getTarget().getCreditCards().get(0).getToken());
			map.put("braintreeUID", result.getTarget().getCreditCards().get(0).getUniqueNumberIdentifier());
			map.put("customerId", result.getTarget().getId());
			return map;
		}
		logger.error("Error occured during the add credit card verification:"+result.getMessage());
		return null;

	}
	
	public static String editCreditCard(CreditCardModel creditCardModel,String profileId) {
		
		BraintreeGateway gateway = ThirdPartySessionManager.getInstance().getBraintreeGatewaySession();
		
		/*CreditCardRequest request = new CreditCardRequest()
			.number(creditCardModel.getCreditCardNumber())
			.expirationDate(creditCardModel.getExpirationDate())
			.options()
				.verifyCard(true)
				.done();*/
		
		CreditCardRequest request = new CreditCardRequest()
		.expirationDate(creditCardModel.getExpirationDate())
		.options()
			.verifyCard(true)
			.done();

		Result<CreditCard> result = gateway.creditCard().update(profileId, request);
		
		if(result.isSuccess()){
			return result.getTarget().getUniqueNumberIdentifier();
		}
		logger.error("Error occured during the update credit card verification:"+result.getMessage());
		return null;

	}
	
	public static String viewCreditCard(String profileId) {

		BraintreeGateway gateway = ThirdPartySessionManager.getInstance().getBraintreeGatewaySession();		
		
		try{
			CreditCard creditCard = gateway.creditCard().find(profileId);
			
			creditCard.getCardType();
			return creditCard.getLast4();
			
		
		}catch(Exception e){
			logger.error("No details found for credit card",e);
		}
		return null;	

	}
	
	public static boolean deleteCreditCard(String profileId) {

		BraintreeGateway gateway = ThirdPartySessionManager.getInstance().getBraintreeGatewaySession();		
	
		Result<CreditCard> result= gateway.creditCard().delete(profileId);
			
		if(result.isSuccess()){
			return true;
		}
		
		logger.error("Error occured during the delete credit card:"+result.getMessage());
		return false;
	}
	
	public static boolean deleteCreditCardByCustomer(String customerId) {
		
		BraintreeGateway gateway = ThirdPartySessionManager.getInstance().getBraintreeGatewaySession();		
	
		Result<Customer> result= gateway.customer().delete(customerId);
			
		if(result.isSuccess()){
			return true;
		}
		
		logger.error("Error occured during the delete credit card by customer id:"+result.getMessage());
		return false;
	}

}
