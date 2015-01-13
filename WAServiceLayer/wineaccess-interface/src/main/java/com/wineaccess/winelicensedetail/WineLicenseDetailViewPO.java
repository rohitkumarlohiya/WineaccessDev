package com.wineaccess.winelicensedetail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name = "viewWineLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineLicenseDetailViewPO extends BasePO {

	@NotNull(message = "VIEW_WINE_LICENSE_DETAIL_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "VIEW_WINE_LICENSE_DETAIL_102")
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
    
}
