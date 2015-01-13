/* Copyright 2003-2004 CyberSource Corporation */

package com.wineaccess.third.paymentgateway;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import com.cybersource.ws.client.*;

/**
 * Sample class that demonstrates how to call Credit Card Authorization and
 * a follow-on Credit Card Capture.  Note that in most cases, the follow-on
 * capture is not performed until after the goods are shipped, not right after
 * the authorization.
 */
public class CyberSourceExample
{
	/**
	 * Entry point.
	 *
	 * @param args	command-line arguments. The name of the property file
	 *              may be passed as a command-line argument.  If not passed, 
	 *				it will look for "cybs.properties" in the current
	 *				directory.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
    public static void main( String[] args ) throws FileNotFoundException, IOException
   	{   	
	   	// read in properties file.
	   //	Properties props = Utility.readProperties( args );
	   	
	   	Properties props = new Properties();
	   	props.load(new FileInputStream("C://cybs.properties"));
	   	String value = props.getProperty("merchantID");
	   	System.out.println(value);
	   	// run auth
   		String requestID = runAuth( props );
   		if (requestID != null)
   		{
   			System.out.println("requestID-------"+requestID);
	   		// if auth was successful, run capture
   			runCapture( props, requestID );
   		}
	}
	
	/**
	 * Runs Credit Card Authorization.
	 * 
	 * @param props	Properties object.
	 *
	 * @return the requestID.
	 */
    public static String runAuth( Properties props )
    {  	
	    String requestID = null;
	    
	   	HashMap request = new HashMap();
	   	
		request.put( "ccAuthService_run", "true" );
		
		// We will let the Client get the merchantID from props and insert it
		// into the request Map.
		
		// this is your own tracking number.  CyberSource recommends that you
		// use a unique one for each order.
		request.put( "merchantReferenceCode", "MRC-14344" );
		
		request.put( "billTo_firstName", "Abhishek" );
		request.put( "billTo_lastName", "Sharma" );
		request.put( "billTo_street1", "1295 Charleston Road" );
		request.put( "billTo_city", "Mountain View" );
		request.put( "billTo_state", "CA" );
		request.put( "billTo_postalCode", "94043" );
		request.put( "billTo_country", "US" );
		request.put( "billTo_email", "abhishek.sharma1@globallogic.com" );
		request.put( "billTo_ipAddress", "10.7.7.7" );
		request.put( "billTo_phoneNumber", "650-965-6000" );
		request.put( "shipTo_firstName", "Jane" );
		request.put( "shipTo_lastName", "Doe" );
		request.put( "shipTo_street1", "100 Elm Street" );
		request.put( "shipTo_city", "San Mateo" );
		request.put( "shipTo_state", "CA" );
		request.put( "shipTo_postalCode", "94401" );
		request.put( "shipTo_country", "US" );
		request.put( "card_accountNumber", "4111111111111111" );
		request.put( "card_expirationMonth", "12" );
		request.put( "card_expirationYear", "2020" );
		request.put( "purchaseTotals_currency", "USD" );

		// there are two items in this sample
		request.put( "item_0_unitPrice", "12.34" );
		request.put( "item_1_unitPrice", "56.78" );

		// add more fields here per your business needs
		
		try
		{
			displayMap( "CREDIT CARD AUTHORIZATION REQUEST:", request );
			
			// run transaction now
			HashMap reply = Client.runTransaction( request, props );	
			
			displayMap( "CREDIT CARD AUTHORIZATION REPLY:", reply );
			
			// if the authorization was successful, obtain the request id
			// for the follow-on capture later.
			String decision = (String) reply.get( "decision" );
			System.out.println("decision--"+decision);
			if ("ACCEPT".equalsIgnoreCase( decision ))
			{
				requestID = (String) reply.get( "requestID" );
			}
			
		}	
		catch (ClientException e)
		{
			System.out.println( e.getMessage() );
			if (e.isCritical())
			{
				handleCriticalException( e, request );
			}
		}
		catch (FaultException e)
		{
			System.out.println( e.getMessage() );
			if (e.isCritical())
			{
				handleCriticalException( e, request );
			}
		}
		
		return( requestID );
    }
    
	/**
	 * Runs Credit Card Capture.
	 * 
	 * @param props			Properties object.
	 * @param authRequestID	requestID returned by a previous authorization.
	 */
    public static void runCapture( Properties props, String authRequestID )
    {  	
	    String requestID = null;
	    
	   	HashMap request = new HashMap();
	   	
		request.put( "ccCaptureService_run", "true" );
		
		// We will let the Client get the merchantID from props and insert it
		// into the request Map.
		
		// so that you can efficiently track the order in the CyberSource
		// reports and transaction search screens, you should use the same
		// merchantReferenceCode for the auth and subsequent captures and
		// credits.
		request.put( "merchantReferenceCode", "MRC-14344" );
		
		// reference the requestID returned by the previous auth.
		request.put( "ccCaptureService_authRequestID", authRequestID );
		
		// this sample assumes only the first item has been shipped.
		request.put( "purchaseTotals_currency", "USD" );
		request.put( "item_0_unitPrice", "12.34" );
		request.put( "item_1_unitPrice", "56.78" );

		// add more fields here per your business needs
		
		try
		{
			displayMap( "FOLLOW-ON CAPTURE REQUEST:", request );
			
			// run transaction now
			HashMap reply = Client.runTransaction( request, props );	
			processReply(reply);
			displayMap( "FOLLOW-ON CAPTURE REPLY:", reply );			
		}	
		catch (ClientException e)
		{
			System.out.println( e.getMessage() );
			if (e.isCritical())
			{
				handleCriticalException( e, request );
			}
		}
		catch (FaultException e)
		{
			System.out.println( e.getMessage() );
			if (e.isCritical())
			{
				handleCriticalException( e, request );
			}
		}		
    }
    
	/**
	 * Displays the content of the Map object.
	 *
	 * @param header	Header text.
	 * @param map		Map object to display.
	 */
    private static void displayMap( String header, Map map )
    {
	    System.out.println( header );
	    
		StringBuffer dest = new StringBuffer();
		
		if (map != null && !map.isEmpty())
		{
			Iterator iter = map.keySet().iterator();
			String key, val;
			while (iter.hasNext())
			{
				key = (String) iter.next();
				val = (String) map.get( key );
				dest.append( key + "=" + val + "\n" );
			}
		}
		
		System.out.println( dest.toString() );		
    }
    
       
	/**
	 * An exception is considered critical if some type of disconnect occurs
	 * between the client and server and the client can't determine whether the
	 * transaction was successful. If this happens, you might have a
	 * transaction in the CyberSource system that your order system is not
	 * aware of. Because the transaction may have been processed by
	 * CyberSource, you should not resend the transaction, but instead send the
	 * exception information and the order information (customer name, order
	 * number, etc.) to the appropriate personnel at your company to resolve
	 * the problem. They should use the information as search criteria within
	 * the CyberSource Transaction Search Screens to find the transaction and
	 * determine if it was successfully processed. If it was, you should update
	 * your order system with the transaction information. Note that this is
	 * only a recommendation; it may not apply to your business model.
	 *
	 * @param e			Critical ClientException object.
	 * @param request	Request that was sent.
	 */
	private static void handleCriticalException(
		ClientException e, Map request )
	{
		// send the exception and order information to the appropriate
		// personnel at your company using any suitable method, e.g. e-mail,
		// multicast log, etc.
	}
	
	/**
	 * See header comment in the other version of handleCriticalException
	 * above.
	 *
	 * @param e			Critical ClientException object.
	 * @param request	Request that was sent.
	 */
	private static void handleCriticalException(
		FaultException e, Map request )
	{
		// send the exception and order information to the appropriate
		// personnel at your company using any suitable method, e.g. e-mail,
		// multicast log, etc.
	}    
	
	private static void processReply( HashMap reply )
			throws ClientException {
		MessageFormat template = new MessageFormat(
				getTemplate( (String) reply.get( "decision" ) ) );
		Object[] content = { getContent( reply ) };
		/*
		 * This example writes the message to the console. Choose an appropriate display
		 * method for your own application.
		 */
		System.out.println( "bottom"+template.format( content ) );
	}

	private static String getTemplate( String decision ) {
		// Retrieves the text that corresponds to the decision.
		if ("ACCEPT".equalsIgnoreCase( decision )) {
			return( "Your order was approved.{0}" );
		}
		if ("REJECT".equalsIgnoreCase( decision )) {
			return( "Your order was not approved.{0}" );
		}
		// ERROR
		return( "Your order cannot be completed at this time.{0}" +
				"\nPlease try again later." );
	}
	private static String getContent( HashMap reply )
			throws ClientException {
		/*
		 * Uses the reason code to retrieve more details to add to the template.
		 * The strings returned in this sample are meant to demonstrate how to retrieve
		 * the reply fields. Your application should display user-friendly messages.
		 */
		int reasonCode =
				Integer.parseInt( (String) reply.get( "reasonCode" ) );
		switch (reasonCode) {


		// Success
		case 100:
		return( "\nRequest ID: " + (String) reply.get( "requestID" ));
		// Missing field or fields
		case 101:
		return( "\nThe following required field(s) are missing:\n" +
		enumerateValues( reply, "missingField" ) );
		// Invalid field or fields
		case 102:
		return( "\nThe following field(s) are invalid:\n" +
		enumerateValues( reply, "invalidField" ) );
		// Insufficient funds
		case 204:
		return( "\nInsufficient funds in the account. Please use a different " +
		"card or select another form of payment." );
		// Add additional reason codes here that you must handle specifically.
		default:
		// For all other reason codes (for example, unrecognized reason codes, or
		// codes that do not require special handling), return an empty string.
		return( "" );
		}
		}
		private static String enumerateValues( Map reply, String fieldName ) {
		StringBuffer sb = new StringBuffer();
		String key, val = "";
		for (int i = 0; ; ++i) {
		key = fieldName + "_" + i;
		if (!reply.containsKey( key )) {
		break;
		}
		val = (String) reply.get( key );
		if (val != null) {
		sb.append( val + "\n" );
		}
		}
		return( sb.toString() ); 

}
}