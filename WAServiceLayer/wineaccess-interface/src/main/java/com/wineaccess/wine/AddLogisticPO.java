package com.wineaccess.wine;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

/**
 * @author abhishek.sharma1 PO to add/update logistic PO
 * 
 */
@XmlRootElement(name = "wineLogistic")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class AddLogisticPO extends BasePO {

	@NotBlank(message = "LOGISTIC_ERROR_101")
	@Pattern(regexp = RegExConstants.DIGITS, message = "LOGISTIC_ERROR_102")
	private String wineryId;

	@NotBlank(message = "LOGISTIC_ERROR_103")
	@Pattern(regexp = RegExConstants.DIGITS, message = "LOGISTIC_ERROR_104")
	private String productId;

	@NotBlank(message = "LOGISTIC_ERROR_107")
	@Pattern(regexp = RegExConstants.DIGITS, message = "LOGISTIC_ERROR_108")
	private String contactId;

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "LOGISTIC_ERROR_109")
	private String isFullCaseOnly;

	@NotBlank(message = "LOGISTIC_ERROR_110")
	@Pattern(regexp = RegExConstants.DIGITS, message = "LOGISTIC_ERROR_111")
	private String bottleWeightInLBS;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.BOTTLE_PER_BOX, message = MasterDataErrorCode.Constants.BOTTLE_PER_BOX)
	@NotBlank(message = "LOGISTIC_ERROR_112")
	@Pattern(regexp = RegExConstants.DIGITS, message = "LOGISTIC_ERROR_113")
	private String bottlePerBox;

	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "LOGISTIC_ERROR_106")
	private String warehouseId;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		if (wineryId != null) {
			wineryId = wineryId.trim();
		}
		this.wineryId = wineryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		if (productId != null) {
			productId = productId.trim();
		}

		this.productId = productId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		if (contactId != null) {
			contactId = contactId.trim();
		}
		this.contactId = contactId;
	}

	public String getIsFullCaseOnly() {
		return isFullCaseOnly;
	}

	public void setIsFullCaseOnly(String isFullCaseOnly) {
		if (isFullCaseOnly != null) {
			isFullCaseOnly = isFullCaseOnly.trim();
		}
		this.isFullCaseOnly = isFullCaseOnly;
	}

	public String getBottleWeightInLBS() {
		return bottleWeightInLBS;
	}

	public void setBottleWeightInLBS(String bottleWeightInLBS) {
		if (bottleWeightInLBS != null) {
			bottleWeightInLBS = bottleWeightInLBS.trim();
		}
		this.bottleWeightInLBS = bottleWeightInLBS;
	}

	public String getBottlePerBox() {
		return bottlePerBox;
	}

	public void setBottlePerBox(String bottlePerBox) {
		if (bottlePerBox != null) {
			bottlePerBox = bottlePerBox.trim();
		}
		this.bottlePerBox = bottlePerBox;
	}

}
