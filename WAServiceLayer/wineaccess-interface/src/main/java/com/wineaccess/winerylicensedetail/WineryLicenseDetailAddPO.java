package com.wineaccess.winerylicensedetail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

@XmlRootElement(name = "addWineryLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryLicenseDetailAddPO extends BasePO {

	@NotNull(message = "WINERY_LICENSE_DETAIL_101")
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "WINERY_LICENSE_DETAIL_102")
	private String wineryId;

	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CA_LICENSE_TYPE,message=MasterDataErrorCode.Constants.CA_LICENSE_TYPE)
	@Pattern(regexp = RegExConstants.DIGITS_EMPTY_STRING, message = "WINERY_LICENSE_DETAIL_103")
	private String caLicenseTypeId;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_LICENSE_DETAIL_104")
	private String contractExecuted;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_LICENSE_DETAIL_105")
	private String shipCompliant;
	
	@Pattern(regexp = RegExConstants.ALPHA_NUMERIC_NOT_EMPTY_STRING, message = "WINERY_LICENSE_DETAIL_106")
	private String shipEscrowNo;

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		this.wineryId = wineryId;
	}

	public String getCaLicenseTypeId() {
		return caLicenseTypeId;
	}

	public void setCaLicenseTypeId(String caLicenseTypeId) {
		this.caLicenseTypeId = caLicenseTypeId;
	}

	public String getContractExecuted() {
		return contractExecuted;
	}

	public void setContractExecuted(String contractExecuted) {
		this.contractExecuted = contractExecuted;
	}

	public String getShipCompliant() {
		return shipCompliant;
	}

	public void setShipCompliant(String shipCompliant) {
		this.shipCompliant = shipCompliant;
	}

	public String getShipEscrowNo() {
		return shipEscrowNo;
	}

	public void setShipEscrowNo(String shipEscrowNo) {
		this.shipEscrowNo = shipEscrowNo;
	}

}
