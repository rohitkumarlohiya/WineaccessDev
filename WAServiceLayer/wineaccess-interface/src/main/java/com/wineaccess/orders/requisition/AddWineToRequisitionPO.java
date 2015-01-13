package com.wineaccess.orders.requisition;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

/**
 * @author abhishek.sharma1
 * 
 */
@XmlRootElement(name = "addWineToRequisition")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class AddWineToRequisitionPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "ADD_WINE_REQUISITION_ERROR_101")
	@Pattern(regexp = RegExConstants.DIGITS, message = "ADD_WINE_REQUISITION_ERROR_102")
	private String requistionId;

	@NotEmpty(message = "ADD_WINE_REQUISITION_ERROR_103")
	@Pattern(regexp = RegExConstants.DIGITS, message = "ADD_WINE_REQUISITION_ERROR_104")
	private String productId;

	@NotEmpty(message = "ADD_WINE_REQUISITION_ERROR_105")
	@Pattern(regexp = RegExConstants.DIGITS, message = "ADD_WINE_REQUISITION_ERROR_106")
	private String qtyOfBottles;

	@NotEmpty(message = "ADD_WINE_REQUISITION_ERROR_107")
	@Pattern(regexp = RegExConstants.DIGITS, message = "ADD_WINE_REQUISITION_ERROR_108")
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.BOTTLE_PER_BOX, message = MasterDataErrorCode.Constants.BOTTLE_PER_BOX)
	private String bottlePerBox;

	@NotEmpty(message = "ADD_WINE_REQUISITION_ERROR_109")
	@Pattern(regexp = RegExConstants.DOUBLE, message = "ADD_WINE_REQUISITION_ERROR_110")
	private String costPerBox;

	public String getRequistionId() {
		return requistionId;
	}

	public void setRequistionId(String requistionId) {
		this.requistionId = requistionId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQtyOfBottles() {
		return qtyOfBottles;
	}

	public void setQtyOfBottles(String qtyOfBottles) {
		this.qtyOfBottles = qtyOfBottles;
	}

	public String getBottlePerBox() {
		return bottlePerBox;
	}

	public void setBottlePerBox(String bottlePerBox) {
		this.bottlePerBox = bottlePerBox;
	}

	public String getCostPerBox() {
		return costPerBox;
	}

	public void setCostPerBox(String costPerBox) {
		this.costPerBox = costPerBox;
	}

}
