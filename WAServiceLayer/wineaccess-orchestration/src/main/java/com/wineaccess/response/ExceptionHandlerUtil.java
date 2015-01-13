package com.wineaccess.response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.hibernate.exception.ConstraintViolationException;

public class ExceptionHandlerUtil {
	
	public static ValidationFailedError analyzeJsonObjectException(Exception ex) {
		Set<WineaccessError> errors =  new HashSet<WineaccessError>();
		if (ex instanceof UnrecognizedPropertyException) {
			
			JsonMappingException jsonMappingException = (JsonMappingException) ex;
			for (org.codehaus.jackson.map.JsonMappingException.Reference ref : jsonMappingException.getPath()) {
				WineaccessError error = new WineaccessError("MAPPING-1",  "Unrecognized field \"" + ref.getFieldName() + "\"");
				errors.add(error);
			}
		} else if (ex instanceof JsonMappingException){
			JsonMappingException jsonMappingException = (JsonMappingException) ex;
			for (org.codehaus.jackson.map.JsonMappingException.Reference ref : jsonMappingException.getPath()) {
				WineaccessError error = new WineaccessError("MAPPING-2",  "Invalid Value for the field \"" + ref.getFieldName() + "\"");
				errors.add(error);
			}
		} else if (ex instanceof JsonParseException) {
			JsonParseException jsonParseException = (JsonParseException) ex;
			WineaccessError error = new WineaccessError("MAPPING-3",  "Invalid JSON (Excetion We Got In Parsing - " + jsonParseException.getMessage().split("\n")[0] + ")");
			errors.add(error);
		} else if (ex instanceof org.apache.lucene.queryParser.ParseException)  {
			WineaccessError error = new WineaccessError("LUCENE-PARSE-ERROR", "Invalid keyword entered" );
			errors.add(error);
		}
		else if(ex.getCause() instanceof ConstraintViolationException)
		{	
			WineaccessError error = new WineaccessError("CONSTRAINT-ERROR",getErrorMessage(ex));
			errors.add(error);
		}
		else {
			WineaccessError error = new WineaccessError("SYSTEM-ERROR", ex != null && ex.getMessage() != null ? ex.getMessage().split("\n")[0] : "" );
			errors.add(error);
		}
		Response response = new FailureResponse(errors, 200);
		ValidationFailedError validationFailedError = new ValidationFailedError(response);
		return validationFailedError;
	}
	
	private static String getErrorMessage(Exception e) {
		
    	Map<String,String> mapPoMaster = new HashMap<String, String>();
    	    	
    	mapPoMaster.put("WINERY_ID", "wineryId");
    	mapPoMaster.put("WAREHOUSE_ADDRESS_ID", "addressId");
    	mapPoMaster.put("WINERY_ADDRESS_ID_OF_WH_TYPE", "addressId");
    	mapPoMaster.put("PERFORMANCE_CENTER_ADDRESS_ID", "performanceCenterId");
    	
    	String msg = ((ConstraintViolationException)(e.getCause())).getMessage();
    	String tableName = msg.substring(msg.indexOf("`wineaccess`.`") + ("`wineaccess`.`").length(), msg.indexOf("`, CONSTRAINT"));
    	
    	if(tableName.equalsIgnoreCase("po_master"))
    		return "Invalid value of "+mapPoMaster.get(msg.substring(msg.indexOf("FOREIGN KEY (`") + ("FOREIGN KEY (`").length(), msg.indexOf("`) REFERENCES")));
    	else
    		return msg;    
	}
}
