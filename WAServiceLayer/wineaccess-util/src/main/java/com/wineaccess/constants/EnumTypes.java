package com.wineaccess.constants;


/**
 * @author abhishek.sharma1
 * Enum class
 *
 */
public class EnumTypes {

	/**
	 * @author abhishek.sharma1
	 *Enum to enumerate address type values
	 */

	public enum AddressType {
		Billing("0"), Shipping("1"), ShippingBilling("2");

		private String statusCode;

		private AddressType(String s) {
			statusCode = s;
		}

		public  String getAddressType() {
			return statusCode;
		}


		public static String fetchAddressTypeFromCode(String string) {

			for(AddressType addressType: AddressType.values())
			{
				if(addressType.getAddressType().equalsIgnoreCase(string))
					return addressType.name();
			}
			return null;
		}

		public static String fetchAddressCode(String string) {

			for(AddressType addressType: AddressType.values())
			{
				if(addressType.toString().equalsIgnoreCase(string))
					return addressType.getAddressType();
			}
			return null;
		}



	}

	/**
	 * @author Abhishek.Sharma1
	 *Added according to story-WA-654 
	 *Enhance the service layer APIs of sprint 2 so that Full and fast type can be captured
	 */
	public enum RegType {
		Full("0"), Fast("1");

		private String regTypeCode;

		private RegType(String s) {
			regTypeCode = s;
		}

		public String getRegType() {
			return regTypeCode;
		}


		public static String fetchRegTypeFromCode(String string) {

			for(RegType regType: RegType.values())
			{
				if(regType.getRegType().equalsIgnoreCase(string))
					return regType.name();
			}
			return null;
		}

		public static String fetchRegCode(String string) {

			for(RegType regType: RegType.values())
			{
				if(regType.toString().equalsIgnoreCase(string))
					return regType.getRegType();
			}
			return null;
		}



	}
	
	//For notification frequency
	public enum EmailType {
		ONCE("0"), DAILY("1"), WEEKLY("2"), MONTHLY("3");

		private String emailTypeCode;

		private EmailType(String s) {
			emailTypeCode = s;
		}

		public String getEmailType() {
			return emailTypeCode;
		}


		public static String fetchEmailTypeFromCode(String string) {

			for(EmailType emailType: EmailType.values())
			{
				if(emailType.getEmailType().equalsIgnoreCase(string))
					return emailType.name();
			}
			return null;
		}

		public static String fetchEmailTypeCode(String string) {

			for(EmailType emailType: EmailType.values())
			{
				if(emailType.toString().equalsIgnoreCase(string))
					return emailType.getEmailType();
			}
			return null;
		}

	}
	

	public enum DefaultAddressType {
		Billing("0"), Shipping("1"), ShippingBilling("2");

		private String defaultAddressTypeCode;

		private DefaultAddressType(String s) {
			defaultAddressTypeCode = s;
		}

		public String getDefaultAddressType() {
			return defaultAddressTypeCode;
		}


		public static String fetchDefaultAddressTypeFromCode(String string) {

			for(DefaultAddressType defaultAddressType: DefaultAddressType.values())
			{
				if(defaultAddressType.getDefaultAddressType().equalsIgnoreCase(string))
					return defaultAddressType.name();
			}
			return null;
		}

		public static String fetchDefaultAddressTypeCode(String string) {

			for(DefaultAddressType defDefaultAddressType: DefaultAddressType.values())
			{
				if(defDefaultAddressType.toString().equalsIgnoreCase(string))
					return defDefaultAddressType.getDefaultAddressType();
			}
			return null;
		}


	}

	public enum DesiredDeliveryDays {
		Mon("0"), Tue("1"), Wed("2"), Thu("3"), Fri("4"), Sat("5"), Sun("6");

		private String desiredDeliveryDaysCode;

		private DesiredDeliveryDays(String s) {
			desiredDeliveryDaysCode = s;
		}

		public String getDesiredDeliveryDays() {
			return desiredDeliveryDaysCode;
		}


		public static String fetchDesiredDeliveryDaysFromCode(String[] daysArray) {
			StringBuilder commaSeparatedDays = new StringBuilder();
			for(String days:daysArray){
				for(DesiredDeliveryDays desiredDeliveryDays: DesiredDeliveryDays.values())
				{
					if(desiredDeliveryDays.getDesiredDeliveryDays().equalsIgnoreCase(days)){
						commaSeparatedDays.append(desiredDeliveryDays.name());
						commaSeparatedDays.append(",");
					}

				}
			}
			return commaSeparatedDays.toString();
		}

		public static String fetchDesiredDeliveryDaysCode(String days) {
			
				for(DesiredDeliveryDays desiredDeliveryDays: DesiredDeliveryDays.values())
				{
					if(desiredDeliveryDays.toString().equalsIgnoreCase(days)){
						return desiredDeliveryDays.getDesiredDeliveryDays();
						
					}
				}
			return null;
			
		}


	}

	public enum UserType {
		
		Buyers, NonBuyers, OpenOrders;
		
	}
	
	public enum REQType {
		PO("0"), WT("1"), IT("2");
		
		private String reqTypeCode;

		private REQType(String s) {
			reqTypeCode = s;
		}

		public String getREQType() {
			return reqTypeCode;
		}
		
		public static REQType fetchPoType(String s){
			for(REQType type: REQType.values())
			{
				if(type.getREQType().equalsIgnoreCase(s))
					return type;
			}
			return null;
		}
	}
	
	public enum POStatus {
		APPROVED(0), 
		SUBMITTED(1), 
		RELEASED(2), 
		SCHEDULED_FOR_PICKUP(3), 
		RECEIVED(4);

		private int reqTypeCode;

		private POStatus(int s) {
			reqTypeCode = s;
		}

		public int getPOStatus() {
			return reqTypeCode;
		}

		public static POStatus fetchPoStatus(int s){
			for(POStatus status: POStatus.values())
			{
			if(status.getPOStatus()== s)
				return status;
			}
			return null;
		}

	}
	
	
	public enum EmailTemplateType {
		SEND_ACTIVATION("SEND_ACTIVATION"),
		RESEND_ACTIVATION("RESEND_ACTIVATION"),
		NORMAL_REGISTRATION("NORMAL_REGISTRATION"),
		SSO_REGISTRATION("SSO_REGISTRATION"),
		SUCCESS_AUTHENTICATION("SUCCESS_AUTHENTICATION"),
		SIGNUP_THEN_SSO("SIGNUP_THEN_SSO"),
		SSO_THEN_SIGNUP("SSO_THEN_SIGNUP"),
		USER_EMAIL_EDIT("USER_EMAIL_EDIT"),
		REGISTRATION_USING_ADMIN_SEND_PASSWORD("REGISTRATION_USING_ADMIN_SEND_PASSWORD"),
		ENABLE_USER("ENABLE_USER"),
		RESET_PASSWORD("RESET_PASSWORD"),
		RESET_PASSWORD_SUCCESS("RESET_PASSWORD_SUCCESS"),
		RESEND_ACTIVATION_URL_PASSWORD("RESEND_ACTIVATION_URL_PASSWORD"),
		FORGOT_PASSWORD_MAIL("FORGOT_PASSWORD_MAIL"),
		UPDATE_FORGOT_PASSWORD_SUCCESS("UPDATE_FORGOT_PASSWORD_SUCCESS"),
		CREATE_USER_PASSWORD_SUCCESS("CREATE_USER_PASSWORD_SUCCESS"),
		REGISTRATION_MAIL("REGISTRATION_MAIL"),
		CREATE_USER_PASSWORD("CREATE_USER_PASSWORD");
		
		private String emailTemplateType;

		public String getEmailTemplateType() {
		return emailTemplateType;
		}	

		private EmailTemplateType(String emailTemplateType) {
		this.emailTemplateType = emailTemplateType;
		}	
	}
	
	/**
	 * Author: arpit.vijayvargiya
	 * Method Purpose: This enum is a set of constants for 
	 * defining the Workflow status for Winery and Importer.
	 * */
	public enum WorkflowStatusTypes {
		PENDING("0"), APPROVED("1"), DISAPPROVED("2");
		
		private String workflowStatus; 
		private WorkflowStatusTypes(String workflowString){
			this.workflowStatus = workflowString; 
		}
		
		public String getWorkflowStatus(){
			return workflowStatus;
		}
	}   
	
	/**
	 * Author: arpit.vijayvargiya
	 * Method Purpose: This enum is a set of constants for 
	 * defining the Workflow names for Winery and Importer.
	 * */
	public enum WorkflowNames {
		IMPORTER_WITH_WINERY,
		WINERY_WITH_IMPORTER,
		WINERY_WITHOUT_IMPORTER;
	}   
	
	/**
	 * Author: arpit.vijayvargiya
	 * Method Purpose: This enum is a set of constants for 
	 * resolving the type of error while creating the new Importer.
	 * */
	public enum ImporterErrorEnum {
		COUNTRY_NOT_FOUND("COUNTRY_NOT_FOUND"),
		SOURCING_STATUS_NOT_FOUND("SOURCING_STATUS_NOT_FOUND"),
		WINERY_NOT_FOUND("WINERY_NOT_FOUND"),
		WA_CONTACT_NOT_FOUND("WA_CONTACT_NOT_FOUND"),
		FREIGHT_REGION_NOT_FOUND("FREIGHT_REGION_NOT_FOUND"),
		WAREHOUSE_NOT_FOUND("WAREHOUSE_NOT_FOUND");
		
		private String errorType;

		public String getErrorType() {
			return errorType;
		}	

		private ImporterErrorEnum(String errorType) {
			this.errorType = errorType;
		}	
	}   
	
	/**
	 * @author abhishek.sharma1
	 *
	 */
	public enum OptionSelectedAltStatesEnum {		
		KACHINA_ALT(0),
		PERMIT_FOR_ALT_STATES(1),
		NO_PERMIT_FOR_ALT_STATES(2);
		private Integer optionSelected; 
		OptionSelectedAltStatesEnum(Integer optionSelected){
			this.optionSelected = optionSelected;
		}
		
		public Integer getOptionSelectedaltStates() {
			return optionSelected;
		}
		
	}   
	
	/**
	 * Used for adding wine logistics base on winery address of warehouse type or warehouse type
	 * 
	 * @author rohit.lohiya
	 *
	 */
	public enum AddressTypeMasterData {
		WINERY_ADDRESS_OF_WAREHOUSE_TYPE("1"), WAREHOUSE_ADDRESS("2"), BOTH_NOT_PRESENT("3");
	 
		private String addressCode;
	 
		private AddressTypeMasterData(String s) {
			addressCode = s;
		}
	 
		public Long getAddressTypeMasterData() {
			return Long.parseLong(addressCode);
		}
	 
	}
		
	/**
	 * This enumn is used while adding validation in PO while taking input for master data - for master data annotation processor
	 * @author rohit.lohiya
	 *
	 */
	public enum MasterDataTypeEnum {
	
		Varietal(Constants.VARIETAL),
		Vintage(Constants.VINTAGE),
		CardType(Constants.CARD_TYPE),
		ShippingMethod(Constants.SHIPPING_METHOD),
		ShippingCarrier(Constants.SHIPPING_CARRIER),
		PackageType(Constants.PACKAGE_TYPE),
		ContactType(Constants.CONTACT_TYPE),
		AddressType(Constants.ADDRESS_TYPE),
		SourcingStatus(Constants.SOURCING_STATUS),
		BottleinMl(Constants.BOTTLE_IN_ML),
		WineStyle(Constants.WINE_STYLE),
		CALicenseType(Constants.CA_LICENSE_TYPE),
		WineryLicencePermit(Constants.WINERY_LICENCE_PERMIT),
		WineryLicenceNoPermit(Constants.WINERY_LICENCE_NO_PERMIT),
		WAContact(Constants.WA_CONTACT),
		FreightRegion(Constants.FREIGHT_REGION),
		WineSize(Constants.WINE_SIZE),
		ProductType(Constants.PRODUCT_TYPE),
		WineShippingWarehouseLocation(Constants.WINE_SHIPPING_WAREHOUSE_LOCATION),
		BottlePerBox(Constants.BOTTLE_PER_BOX),
//		DistributionCentreWarehouseLocation(Constants.DISTRIBUTION_CENTRE_WAREHOUSE_LOCATION),
		ProductSamplerQuantity(Constants.PRODUCT_SAMPLER_QUANTITY),
		Carrier(Constants.CARRIER);
		
		MasterDataTypeEnum(String masterDataType) {
	    }

	    public static class Constants {
	    	public static final String VARIETAL = "Varietal";
	    	public static final String VINTAGE = "Vintage";
	    	public static final String CARD_TYPE = "CardType";
	    	public static final String SHIPPING_METHOD = "ShippingMethod";
	    	public static final String SHIPPING_CARRIER = "ShippingCarrier";
	    	public static final String PACKAGE_TYPE = "PackageType";
	    	public static final String CONTACT_TYPE = "ContactType";
	    	public static final String ADDRESS_TYPE = "AddressType";
	    	public static final String SOURCING_STATUS = "SourcingStatus";
	    	public static final String BOTTLE_IN_ML = "BottleinMl";
	    	public static final String WINE_STYLE = "WineStyle";
	    	public static final String CA_LICENSE_TYPE = "CALicenseType";
	    	public static final String WINERY_LICENCE_PERMIT = "WineryLicencePermit";
	    	public static final String WINERY_LICENCE_NO_PERMIT = "WineryLicenceNoPermit";
	    	public static final String WA_CONTACT = "WAContact";
	    	public static final String FREIGHT_REGION = "FreightRegion";
	    	public static final String WINE_SIZE = "WineSize";
	    	public static final String PRODUCT_TYPE = "ProductType";
	    	public static final String WINE_SHIPPING_WAREHOUSE_LOCATION = "WineShippingWarehouseLocation";
	    	public static final String BOTTLE_PER_BOX = "BottlePerBox";
//	    	public static final String DISTRIBUTION_CENTRE_WAREHOUSE_LOCATION = "DistributionCentreWarehouseLocation";
	    	public static final String PRODUCT_SAMPLER_QUANTITY = "ProductSamplerQuantity";
	    	public static final String CARRIER = "Carrier";
	    	
	    }
	}
	
	
	public enum MasterDataErrorCode{
		
		MASTER_DATA_ID_INVALID_VARIETAL(Constants.VARIETAL),
		MASTER_DATA_ID_INVALID_VINTAGE(Constants.VINTAGE),
		MASTER_DATA_ID_INVALID_CARDTYPE(Constants.CARD_TYPE),
		MASTER_DATA_ID_INVALID_SHIPPINGMETHOD(Constants.SHIPPING_METHOD),
		MASTER_DATA_ID_INVALID_SHIPPINGCARRIER(Constants.SHIPPING_CARRIER),
		MASTER_DATA_ID_INVALID_PACKAGETYPE(Constants.PACKAGE_TYPE),
		MASTER_DATA_ID_INVALID_CONTACTTYPE(Constants.CONTACT_TYPE),
		MASTER_DATA_ID_INVALID_ADDRESSTYPE(Constants.ADDRESS_TYPE),
		MASTER_DATA_ID_INVALID_SOURCINGSTATUS(Constants.SOURCING_STATUS),
		MASTER_DATA_ID_INVALID_BOTTLEINML(Constants.BOTTLE_IN_ML),
		MASTER_DATA_ID_INVALID_WINESTYLE(Constants.WINE_STYLE),
		MASTER_DATA_ID_INVALID_CALICENSETYPE(Constants.CA_LICENSE_TYPE),
		MASTER_DATA_ID_INVALID_WINERYLICENCEPERMIT(Constants.WINERY_LICENCE_PERMIT),
		MASTER_DATA_ID_INVALID_WINERYLICENCENOPERMIT(Constants.WINERY_LICENCE_NO_PERMIT),
		MASTER_DATA_ID_INVALID_WACONTACT(Constants.WA_CONTACT),
		MASTER_DATA_ID_INVALID_FREIGHTREGION(Constants.FREIGHT_REGION),
		MASTER_DATA_ID_INVALID_WINESIZE(Constants.WINE_SIZE),
		MASTER_DATA_ID_INVALID_PRODUCTTYPE(Constants.PRODUCT_TYPE),
		MASTER_DATA_ID_INVALID_WINESHIPPINGWAREHOUSELOCATION(Constants.WINE_SHIPPING_WAREHOUSE_LOCATION),
		MASTER_DATA_ID_INVALID_BOTTLEPERBOX(Constants.BOTTLE_PER_BOX),
//		MASTER_DATA_ID_INVALID_DISTRIBUTIONCENTREWAREHOUSELOCATION(Constants.DISTRIBUTION_CENTRE_WAREHOUSE_LOCATION),
		MASTER_DATA_ID_INVALID_PRODUCTSAMPLERQUANTITY(Constants.PRODUCT_SAMPLER_QUANTITY),
		MASTER_DATA_ID_INVALID_CARRIER(Constants.CARRIER);
		
		MasterDataErrorCode(String masterData) {
	    }

	    public static class Constants {
	    	public static final String VARIETAL = "MASTER_DATA_ID_INVALID_VARIETAL";
	    	public static final String VINTAGE = "MASTER_DATA_ID_INVALID_VINTAGE";
	    	public static final String CARD_TYPE = "MASTER_DATA_ID_INVALID_CARDTYPE";
	    	public static final String SHIPPING_METHOD = "MASTER_DATA_ID_INVALID_SHIPPINGMETHOD";
	    	public static final String SHIPPING_CARRIER = "MASTER_DATA_ID_INVALID_SHIPPINGCARRIER";
	    	public static final String PACKAGE_TYPE = "MASTER_DATA_ID_INVALID_PACKAGETYPE";
	    	public static final String CONTACT_TYPE = "MASTER_DATA_ID_INVALID_CONTACTTYPE";
	    	public static final String ADDRESS_TYPE = "MASTER_DATA_ID_INVALID_ADDRESSTYPE";
	    	public static final String SOURCING_STATUS = "MASTER_DATA_ID_INVALID_SOURCINGSTATUS";
	    	public static final String BOTTLE_IN_ML = "MASTER_DATA_ID_INVALID_BOTTLEINML";
	    	public static final String WINE_STYLE = "MASTER_DATA_ID_INVALID_WINESTYLE";
	    	public static final String CA_LICENSE_TYPE = "MASTER_DATA_ID_INVALID_CALICENSETYPE";
	    	public static final String WINERY_LICENCE_PERMIT = "MASTER_DATA_ID_INVALID_WINERYLICENCEPERMIT";
	    	public static final String WINERY_LICENCE_NO_PERMIT = "MASTER_DATA_ID_INVALID_WINERYLICENCENOPERMIT";
	    	public static final String WA_CONTACT = "MASTER_DATA_ID_INVALID_WACONTACT";
	    	public static final String FREIGHT_REGION = "MASTER_DATA_ID_INVALID_FREIGHTREGION";
	    	public static final String WINE_SIZE = "MASTER_DATA_ID_INVALID_WINESIZE";
	    	public static final String PRODUCT_TYPE = "MASTER_DATA_ID_INVALID_PRODUCTTYPE";
	    	public static final String WINE_SHIPPING_WAREHOUSE_LOCATION = "MASTER_DATA_ID_INVALID_WINESHIPPINGWAREHOUSELOCATION";
	    	public static final String BOTTLE_PER_BOX = "MASTER_DATA_ID_INVALID_BOTTLEPERBOX";
//	    	public static final String DISTRIBUTION_CENTRE_WAREHOUSE_LOCATION = "MASTER_DATA_ID_INVALID_DISTRIBUTIONCENTREWAREHOUSELOCATION";
	    	public static final String PRODUCT_SAMPLER_QUANTITY = "MASTER_DATA_ID_INVALID_PRODUCTSAMPLERQUANTITY";
	    	public static final String CARRIER = "MASTER_DATA_ID_INVALID_CARRIER";
	    }
		
		
	}
	
	
	
	/**
	 * @author abhishek.sharma1
	 *
	 */
	public enum MasterDataEnum {
		Wine(0);

		
		private int masterData;
	 
		private MasterDataEnum(int s) {
			masterData = s;
		}
	 
		public int getMasterData() {
			return masterData;
		}
	 
	}
	
	public enum CountryCode {
		
		USA;
		
	}
	

}
