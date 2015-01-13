package com.wineaccess.warehouse;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

/**
 * @author arpit.vijayvargiya
 *
 */
@XmlRootElement(name="warehouse")
public class AddWarehousePO extends BasePO implements Serializable{

	private static final long serialVersionUID = -5533955929788401899L;
	
	@NotNull(message="WAREHOUSE101")
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message="WAREHOUSE132")
	private String warehouseName;
	
	@NotNull(message="WAREHOUSE102")
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message="WAREHOUSE133")
	private String addressLine1;
	private String addressLine2;
	
	@NotNull(message="WAREHOUSE103")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message="WAREHOUSE104")
	private String cityId;
	
	@NotNull(message="WAREHOUSE105")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message="WAREHOUSE106")
	private String stateId;
	
	@NotNull(message="WAREHOUSE107")
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message="WAREHOUSE134")
	private String zipcode;

	//@NotNull(message="WAREHOUSE108")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="WAREHOUSE154")
	private String firstName;
	
	//@NotNull(message="WAREHOUSE109")
	@Pattern(regexp=RegExConstants.ALPHABETS_NOT_EMPTY_STRING,message="WAREHOUSE155")
	private String lastName;
	
	//@NotNull(message="WAREHOUSE110")
	@Email(message="WAREHOUSE111")
	private String emailId;
	
	//@NotNull(message="WAREHOUSE112")
	@Pattern(regexp = RegExConstants.PHONE, message="WAREHOUSE113")
	private String phone;
	
	@Pattern(regexp = RegExConstants.PHONE, message="WAREHOUSE114")
	private String faxNumber;
	
	@NotNull(message="WAREHOUSE130")
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.FREIGHT_REGION, message = MasterDataErrorCode.Constants.FREIGHT_REGION)
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message="WAREHOUSE128")
	@Min(value = 1, message = "WAREHOUSE128")
	private String freightRegion;
	
	@NotNull(message="WAREHOUSE150")
	@Pattern(regexp = RegExConstants.BOOLEAN, message="WAREHOUSE135")
	private String isNonWSTransportCarrier = "false";

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.CARRIER, message = MasterDataErrorCode.Constants.CARRIER)
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message="WAREHOUSE151")
	private String carrierId;	
	
	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getFreightRegion() {
		return freightRegion;
	}

	public void setFreightRegion(String freightRegion) {
		this.freightRegion = freightRegion;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFirstName() {
		
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName == null || firstName.trim().isEmpty()){
			firstName = "";
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName == null || lastName.trim().isEmpty()){
			lastName = "";
		}
		
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getIsNonWSTransportCarrier() {
		return isNonWSTransportCarrier;
	}

	public void setIsNonWSTransportCarrier(String isWineShippingAddress) {
		this.isNonWSTransportCarrier = isWineShippingAddress;
	}
		
}
