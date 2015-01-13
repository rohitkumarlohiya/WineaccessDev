package com.wineaccess.orders.requisition;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

/**
 * 
 * @author rohit.lohiya
 *
 */
@XmlRootElement(name = "addRequisition")
public class AddRequisitionPOForTypePOWT extends AddRequisitionPO {	

	private static final long serialVersionUID = 2455174702181782928L;

	@NotNull(message = "REQUISITION_ERROR_105")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "REQUISITION_ERROR_106")
	private String wineryId;

	@NotNull(message = "REQUISITION_ERROR_107")
	@Pattern(regexp = RegExConstants.SOURCE_ADDRESS_TYPE, message = "REQUISITION_ERROR_108")
	private String sourceAddressType;

	@NotNull(message = "REQUISITION_ERROR_109")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "REQUISITION_ERROR_110")
	private String sourceAddressId;

	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CARRIER, message=MasterDataErrorCode.Constants.CARRIER)
	//@NotNull(message = "REQUISITION_ERROR_111")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "REQUISITION_ERROR_112")
	private String inboundTransportId;

	@Pattern(regexp = RegExConstants.REQUISITION_TYPE_FOR_POWT, message = "REQUISITION_ERROR_102")
	public String getRequisitionType() {	
		return super.getRequisitionType();
	}

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		this.wineryId = wineryId;
	}

	public String getSourceAddressType() {
		return sourceAddressType;
	}

	public void setSourceAddressType(String sourceAddressType) {
		this.sourceAddressType = sourceAddressType;
	}

	public String getSourceAddressId() {
		return sourceAddressId;
	}

	public void setSourceAddressId(String sourceAddressId) {
		this.sourceAddressId = sourceAddressId;
	}

	public String getInboundTransportId() {
		return inboundTransportId;
	}

	public void setInboundTransportId(String inboundTransportId) {
		this.inboundTransportId = inboundTransportId;
	}
}
