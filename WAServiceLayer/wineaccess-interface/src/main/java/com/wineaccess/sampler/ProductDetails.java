package com.wineaccess.sampler;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

/**
 * @author gaurav.agarwal1
 * details of add sampler products
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ProductDetails extends BasePO implements Serializable {

	private static final long serialVersionUID = -7232542537299696542L;
	
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.PRODUCT_TYPE, message = MasterDataErrorCode.Constants.PRODUCT_TYPE)
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING, message="ADD_SAMPLER_ERROR_105")
	private String productType;
	
	@NotEmpty(message="ADD_SAMPLER_ERROR_106")
	@Pattern(regexp=RegExConstants.DIGITS, message="ADD_SAMPLER_ERROR_107")
	private String productId;
	
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "ADD_SAMPLER_ERROR_108")
	@NotNull(message="ADD_SAMPLER_ERROR_108")
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.PRODUCT_SAMPLER_QUANTITY, message = MasterDataErrorCode.Constants.PRODUCT_SAMPLER_QUANTITY)
	private String quantity;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
