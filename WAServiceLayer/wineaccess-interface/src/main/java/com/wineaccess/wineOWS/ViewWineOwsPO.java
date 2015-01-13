package com.wineaccess.wineOWS;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * view PO of the wine historical OWS data
 */

@XmlRootElement(name="viewWineOws")
public class ViewWineOwsPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1102849174922656657L;
	
	@NotNull(message="WINE_OWS_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_OWS_ERROR_102")
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	


}
