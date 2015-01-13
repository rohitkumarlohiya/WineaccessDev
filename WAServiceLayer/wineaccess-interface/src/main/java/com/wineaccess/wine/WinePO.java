package com.wineaccess.wine;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

@XmlRootElement(name = "wine")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WinePO extends BasePO {

	@NotNull(message = "WINE_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message = "WINE_ERROR_112")
	private String wineName;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.WINE_STYLE, message = MasterDataErrorCode.Constants.WINE_STYLE)
	@NotNull(message = "WINE_ERROR_102")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_ERROR_103")
	private String wineStyleId;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.VINTAGE, message = MasterDataErrorCode.Constants.VINTAGE)
	@NotNull(message = "WINE_ERROR_104")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_ERROR_105")
	private String vintageId;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.VARIETAL, message = MasterDataErrorCode.Constants.VARIETAL)
	@NotNull(message = "WINE_ERROR_106")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_ERROR_107")
	private String varietalId;

	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.WINE_SIZE,message=MasterDataErrorCode.Constants.WINE_SIZE)
	@NotNull(message = "WINE_ERROR_108")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_ERROR_109")
	private String bottleInMlId;

	@NotNull(message = "WINE_ERROR_110")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_ERROR_111")
	private String wineryId;

	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "WINE_ERROR_113")
	private String wineShortName;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.BOTTLE_PER_BOX, message = MasterDataErrorCode.Constants.BOTTLE_PER_BOX)
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "WINE_ERROR_144")
	private String bottlesPerBoxId;

	@Pattern(regexp = RegExConstants.DOUBLE, message = "WINE_ERROR_114")
	private String alcoholPercentage;

	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "WINE_ERROR_145")
	private String notes;

	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "WINE_ERROR_146")
	private String wineLabel;

	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "WINE_ERROR_147")
	private String privateLabel;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.SOURCING_STATUS, message = MasterDataErrorCode.Constants.SOURCING_STATUS)
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "WINE_ERROR_115")
	private String wineSourcingId;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_ERROR_116")
	private String sendToFullfillerOn;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date usaArrivalDate;

	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "WINE_ERROR_117")
	private String licenseFFPartnerId;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_ERROR_118")
	private String sellInMainStatesOnly;

	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "WINE_ERROR_148")
	private String nameIfNotSellInAltStates;

	@NotNull(message = "WINE_ERROR_149")
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_ERROR_150")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWineName() {
		return wineName;
	}

	public void setWineName(String wineName) {
		this.wineName = wineName;
	}

	public String getWineStyleId() {
		return wineStyleId;
	}

	public void setWineStyleId(String wineStyleId) {
		this.wineStyleId = wineStyleId;
	}

	public String getVintageId() {
		return vintageId;
	}

	public void setVintageId(String vintageId) {
		this.vintageId = vintageId;
	}

	public String getVarietalId() {
		return varietalId;
	}

	public void setVarietalId(String varietalId) {
		this.varietalId = varietalId;
	}

	public String getBottleInMlId() {
		return bottleInMlId;
	}

	public void setBottleInMlId(String bottleInMlId) {
		this.bottleInMlId = bottleInMlId;
	}

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		this.wineryId = wineryId;
	}

	public String getWineShortName() {
		return wineShortName;
	}

	public void setWineShortName(String wineShortName) {
		this.wineShortName = wineShortName;
	}

	public String getBottlesPerBoxId() {
		return bottlesPerBoxId;
	}

	public void setBottlesPerBoxId(String bottlesPerBoxId) {
		this.bottlesPerBoxId = bottlesPerBoxId;
	}

	public String getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(String alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWineLabel() {
		return wineLabel;
	}

	public void setWineLabel(String wineLabel) {
		this.wineLabel = wineLabel;
	}

	public String getPrivateLabel() {
		return privateLabel;
	}

	public void setPrivateLabel(String privateLabel) {
		this.privateLabel = privateLabel;
	}

	public String getWineSourcingId() {
		return wineSourcingId;
	}

	public void setWineSourcingId(String wineSourcingId) {
		this.wineSourcingId = wineSourcingId;
	}

	public String getSendToFullfillerOn() {
		return sendToFullfillerOn;
	}

	public void setSendToFullfillerOn(String sendToFullfillerOn) {
		this.sendToFullfillerOn = sendToFullfillerOn;
	}

	public Date getUsaArrivalDate() {
		return usaArrivalDate;
	}

	public void setUsaArrivalDate(Date usaArrivalDate) {
		this.usaArrivalDate = usaArrivalDate;
	}

	public String getLicenseFFPartnerId() {
		return licenseFFPartnerId;
	}

	public void setLicenseFFPartnerId(String licenseFFPartnerId) {
		this.licenseFFPartnerId = licenseFFPartnerId;
	}

	public String getSellInMainStatesOnly() {
		return sellInMainStatesOnly;
	}

	public void setSellInMainStatesOnly(String sellInMainStatesOnly) {
		this.sellInMainStatesOnly = sellInMainStatesOnly;
	}

	public String getNameIfNotSellInAltStates() {
		return nameIfNotSellInAltStates;
	}

	public void setNameIfNotSellInAltStates(String nameIfNotSellInAltStates) {
		this.nameIfNotSellInAltStates = nameIfNotSellInAltStates;
	}

}
