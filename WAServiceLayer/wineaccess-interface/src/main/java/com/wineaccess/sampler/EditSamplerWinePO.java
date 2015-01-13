package com.wineaccess.sampler;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

@XmlRootElement(name = "editSamplerWine")
public class EditSamplerWinePO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1236569444783654436L;

    @NotEmpty(message="EDIT_SAMPLER_WINE_ERROR_108")
    @Pattern(regexp=RegExConstants.DIGITS, message="EDIT_SAMPLER_WINE_ERROR_109")
    private String samplerId;

    @NotEmpty(message="EDIT_SAMPLER_WINE_ERROR_101")
    @Pattern(regexp=RegExConstants.DIGITS, message="EDIT_SAMPLER_WINE_ERROR_102")
    private String productId;

    @NotEmpty(message="EDIT_SAMPLER_WINE_ERROR_103")
    @MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.PRODUCT_SAMPLER_QUANTITY, message = MasterDataErrorCode.Constants.PRODUCT_SAMPLER_QUANTITY)
    @Pattern(regexp=RegExConstants.DIGITS,message="EDIT_SAMPLER_WINE_ERROR_104")
    private String quantity;

    @NotEmpty(message="EDIT_SAMPLER_WINE_ERROR_105")
    @Pattern(regexp=RegExConstants.DOUBLE,message="EDIT_SAMPLER_WINE_ERROR_106")
    private String srpPrice;

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

    public String getSrpPrice() {
	return srpPrice;
    }

    public void setSrpPrice(String srpPrice) {
	this.srpPrice = srpPrice;
    }

    public String getSamplerId() {
	return samplerId;
    }

    public void setSamplerId(String samplerId) {
	this.samplerId = samplerId;
    }


}
