package com.wineaccess.sampler;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

/**
 * @author gaurav.agarwal1
 * PO of add sampler product
 */
@XmlRootElement(name="addSamplerProduct")
public class AddSamplerProductPO extends BasePO implements Serializable {

	private static final long serialVersionUID = -1253737023327016041L;
	
	@NotNull(message="ADD_SAMPLER_PRODUCT_ERROR_101")
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING, message="ADD_SAMPLER_PRODUCT_ERROR_102")
	private String samplerId;
	
	@NotNull(message="ADD_SAMPLER_PRODUCT_ERROR_103")
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING, message="ADD_SAMPLER_PRODUCT_ERROR_104")
	private String productId;
	
	@NotNull(message="ADD_SAMPLER_PRODUCT_ERROR_105")
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.PRODUCT_SAMPLER_QUANTITY, message=MasterDataErrorCode.Constants.PRODUCT_SAMPLER_QUANTITY)
	private String qtyOfProducts;
	
	@NotNull(message="ADD_SAMPLER_PRODUCT_ERROR_106")
	@Pattern(regexp=RegExConstants.DOUBLE ,message="ADD_SAMPLER_PRODUCT_ERROR_107")
	private String srpPrice;

	public String getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(String samplerId) {
		this.samplerId = samplerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQtyOfProducts() {
		return qtyOfProducts;
	}

	public void setQtyOfProducts(String qtyOfProducts) {
		this.qtyOfProducts = qtyOfProducts;
	}

	public String getSrpPrice() {
		return srpPrice;
	}

	public void setSrpPrice(String srpPrice) {
		this.srpPrice = srpPrice;
	}

}
