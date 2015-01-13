/**
 * 
 */
package com.wineaccess.application.constants;


/**
 * @author rohit.lohiya
 *
 */
public interface RegExConstants {
	
	String DIGITS = "\\d*";									//This will only take digit. 
	String BOOLEAN = "(true|false)";						//This will only accept true or false value.
	String BOOLEAN_NOT_EMPTY_STRING = "(true|false)|(^$)";
	String DOUBLE = "(\\d+\\.\\d+)|(^$)";							//This will only take float value.
	String DIGITS_NOT_EMPTY_STRING = "(^\\d+$)|(^$)";				//This will only take digit but not empty string. 
	String STRING_NOT_EMPTY_STRING = "(^(?!\\s*$).+)|(^$)";		//This will accept all the string but not empty string as "" or "   "

	String ALPHA_NUMERIC_NOT_EMPTY_STRING = "(^[0-9a-zA-Z]+$)|(^$)";
	String SORT_FIELDS_FOR_IMPORTER = "id|importerName|"    //Sorting will be allowed only on these fields 
			+ "region|activeStatus|waContact|winery|wines|"
			+ "activeWines|totalRevenue|lastOfferDate";   
	String SEARCH_FIELDS_FOR_IMPORTER = "(importerName|"      //Searching will be allowed only on these fields
			+ "id|region|^$)";
	String SORT_ORDER = "(0|1)";
	String LIMIT = "(-1|^[0-9]+$)";
	String OFFSET = "^[1-9]\\d*$";
	String SEARCH_TYPE = "(A|B)";
	String SEARCH_FIELDS_FOR_WINERY = "(wineryName|"      //Searching will be allowed only on these fields
			+ "id|wineryCode|importerName|wineryRegion|^$)";
	String SORT_FIELDS_FOR_WINERY = "id|wineryCode|"    //Sorting will be allowed only on these fields 
			+ "wineryName|wineryRegion|importerName|waContact|wines|"
			+ "activeWines|totalRevenue|lastOfferDate";
	String SORT_FIELDS_FOR_EMAIL_TEMPLATE_TYPE = "modifiedDate|label|"    //Sorting will be allowed only on these fields 
			+ "description|modifiedBy";			
	String SORT_FIELDS_FOR_EMAIL_TEMPLATE = "modifiedDate|name|"    //Sorting will be allowed only on these fields 
			+ "fromEmail|subject|id|modifiedBy";		
	String SORT_FIELDS_FOR_LOGIN_HISTORY = "userid|browser|"    //Sorting will be allowed only on these fields 
			+ "ipAddress|operatingSystem|platformDevice|sessionStartTime|sessionEndTime";
	
	String SORT_FIELDS_FOR_ACTIVITY_LOG_HISTORY = "lastLogin|lastLogout|"    //Sorting will be allowed only on these fields 
			+ "totalSessions|userid|id";
	String SEARCH_FIELDS_FOR_EMAIL_LOG = "[(content|subject|userId|deliveryStatus)]+(,[(content|subject|userId|deliveryStatus)]+)*";
	String SORT_FIELDS_FOR_EMAIL_LOG = "content|subject|"    //Sorting will be allowed only on these fields 
			+ "userId|deliveryStatus|id|deliveryDate|^$";			
	//String SEARCH_FIELDS_FOR_WINE = "id|wineId|wineryName|wineName|importerName|region|vintage|wineStyle|varietal|totalRevenue|lastOfferDate|activeOffers|userReviews|userRatings|expertScore|expertRating|^$";//"wineId|wineName|vintage|wineryName|wineStyle|importerName|varietal|^$";
	String SEARCH_FIELDS_FOR_WINE = "id|wineName|vintage|wineryName|wineStyle|importerName|varietal|^$";
	String SORT_FIELDS_FOR_WINE = "id|wineId|wineryName|wineName|importerName|region|vintage|wineStyle|varietal|totalRevenue|lastOfferDate|activeOffers|userReviews|userRatings|expertScore|expertRating|^$";//WineSearchDAOImpl.getRegExSort();//"id|wineId|wineryName|name|vintage|varietal|importerName|region";
	String SORT_FIELDS_FOR_WINERY_IMPORTER_ADDRESS = "addressType|id|phone";
	String SORT_FIELDS_FOR_WINERY_IMPORTER_CONTACTS = "name|id|email|phone|contactType";
	String SORT_FIELDS_FOR_NEWSLETTER = "name|id|webName|effDate|endDate|emailSubject|fromEmail|submitDate|title";
	String SORT_FIELDS_FOR_WINE_OF_REQUISITION = "wineName|bottlesCount|unitPrice|costPerBox|^$";
	String PHONE = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
	String WINE_TYPE = "(AE|AD|^$)";						//This will only accept true or false or empty value.
	String WINERY_TYPE = "(AE|AD|^$)";
	String WINE_BULK_OPERATION = "(E|D)";		
	String ENABLE_DISABLE_STATUS = "((?i)enable|disable)|^$";
	String MANDATORY_STRING = "(^(?!\\s*$).+)";
	String MANDATORY_DIGITS = "(^\\d+$)";
	String MANDATORY_DOUBLE = "(\\d+\\.\\d+)";		
	String MANDATORY_ALPHA_NUMERIC = "(^[0-9a-zA-Z]+$)";
	String EMAIL = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)|(^$)";
	
	String REQUISITION_TYPE_FOR_POWT = "(0|1)"; 
	String REQUISITION_TYPE_FOR_IT = "(2)"; 
	
	//String REQUISITION_TYPE = "(PO|WT|IT)";
	String SOURCE_REQUISITION_TYPE = "(PO|WT)";
	String SOURCE_ADDRESS_TYPE = "(WH|DC)";
	
	String STATUS_TYPE = "(AAPP|ASUB|AREL|ASCH|AREC|^$)";
	
	String MANDATORY_DIGITS_LOGN_RANGE = "(^\\d{1,19}+$)";
	
	String SEARCH_FIELDS_FOR_SAMPLER = "(id|"      //Searching will be allowed only on these fields
			+ "name|wineName|^$)";
	
	String SORT_FIELDS_FOR_SAMPLER = "id|name|wineName|activeOffer|totalRevenue|totalSrp|lastOfferDate";
	String IMPORTER_TYPE = "(AE|AD|^$)";
	String PHONE_EMPTY_STRING="^$|"+PHONE;
	String REQUISITION_STATUS = "(0|1|2|3|4)";
	String DIGITS_EMPTY_STRING="^$|"+DIGITS;
	String SORT_FIELDS_FOR_JR = "requisitionId|typeOfPO|issuedDate|status|wineryNameSort|winesCount|bottlesCount";
	
	String ALPHA_NUMERIC_HIPEN_NOT_EMPTY_STRING = "(^[0-9a-zA-Z-]+$)|(^$)";
  String ALPHABETS_NOT_EMPTY_STRING = "(^[a-zA-Z]+$)|(^$)";
}
