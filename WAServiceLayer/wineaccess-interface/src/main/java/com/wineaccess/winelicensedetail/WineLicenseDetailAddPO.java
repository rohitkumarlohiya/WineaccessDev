package com.wineaccess.winelicensedetail;

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

@XmlRootElement(name = "addWineLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineLicenseDetailAddPO extends BasePO {

	@NotNull(message = "WINE_LICENSE_DETAIL_101")
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "WINE_LICENSE_DETAIL_102")
	private String wineId;

	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.CA_LICENSE_TYPE,message=MasterDataErrorCode.Constants.CA_LICENSE_TYPE)
	@Pattern(regexp = RegExConstants.DIGITS_EMPTY_STRING, message = "WINE_LICENSE_DETAIL_103")
	private String caLicenseTypeId;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_LICENSE_DETAIL_104")
	private String contractExecuted;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_LICENSE_DETAIL_105")
	private String shipCompliant;

	@Pattern(regexp = RegExConstants.ALPHA_NUMERIC_NOT_EMPTY_STRING, message = "WINE_LICENSE_DETAIL_106")
	private String shipEscrowNo;

	@Pattern(regexp = RegExConstants.ALPHA_NUMERIC_NOT_EMPTY_STRING, message = "WINE_LICENSE_DETAIL_107")
	private String shipCompliantProductKey;

	@Pattern(regexp = RegExConstants.DOUBLE, message = "WINE_LICENSE_DETAIL_108")
	private String priceToRetailer;

	@Pattern(regexp = RegExConstants.ALPHA_NUMERIC_NOT_EMPTY_STRING, message = "WINE_LICENSE_DETAIL_109")
	private String colaNumber;

	public String getWineId() {
		return wineId;
	}

	public void setWineId(String wineId) {
		this.wineId = wineId;
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

	public String getShipCompliantProductKey() {
		return shipCompliantProductKey;
	}

	public void setShipCompliantProductKey(String shipCompliantProductKey) {
		this.shipCompliantProductKey = shipCompliantProductKey;
	}

	public String getPriceToRetailer() {
		return priceToRetailer;
	}

	public void setPriceToRetailer(String priceToRetailer) {
		this.priceToRetailer = priceToRetailer;
	}

	public String getColaNumber() {
		return colaNumber;
	}

	public void setColaNumber(String colaNumber) {
		this.colaNumber = colaNumber;
	}

}
