package com.wineaccess.wine;

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

@XmlRootElement(name = "updateWine")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineUpdatePO extends BasePO {

	@NotNull(message = "UPDATE_WINE_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "UPDATE_WINE_ERROR_102")
	private String id;

	@NotNull(message = "UPDATE_WINE_ERROR_103")
	@Pattern(regexp = RegExConstants.MANDATORY_STRING, message = "UPDATE_WINE_ERROR_150")
	private String wineName;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.VINTAGE, message = MasterDataErrorCode.Constants.VINTAGE)
	@NotNull(message = "UPDATE_WINE_ERROR_104")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "UPDATE_WINE_ERROR_105")
	private String vintageId;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.WINE_SIZE, message = MasterDataErrorCode.Constants.WINE_SIZE)
	@NotNull(message = "UPDATE_WINE_ERROR_106")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "UPDATE_WINE_ERROR_107")
	private String bottleInMlId;
	
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.WINE_STYLE, message = MasterDataErrorCode.Constants.WINE_STYLE)
	@NotNull(message = "UPDATE_WINE_ERROR_114")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "UPDATE_WINE_ERROR_115")
	private String wineStyleId;
	
	@Pattern(regexp = RegExConstants.DOUBLE, message = "WINE_ERROR_114")
	private String alcoholPercentage;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "UPDATE_WINE_ERROR_113")
	private String status;
	
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.VARIETAL, message = MasterDataErrorCode.Constants.VARIETAL)
	@NotNull(message = "WINE_ERROR_106")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_ERROR_107")
	private String varietalId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String wineId) {
		this.id = wineId;
	}

	public String getWineName() {
		return wineName;
	}

	public void setWineName(String wineName) {
		this.wineName = wineName;
	}

	public String getVintageId() {
		return vintageId;
	}

	public void setVintageId(String vintageId) {
		this.vintageId = vintageId;
	}

	public String getBottleInMlId() {
		return bottleInMlId;
	}

	public void setBottleInMlId(String bottleInMlId) {
		this.bottleInMlId = bottleInMlId;
	}

	

	public String getWineStyleId() {
		return wineStyleId;
	}

	public void setWineStyleId(String wineStyleId) {
		this.wineStyleId = wineStyleId;
	}

	public String getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(String alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getVarietalId() {
		return varietalId;
	}

	public void setVarietalId(String varietalId) {
		this.varietalId = varietalId;
	}
	
	

}
