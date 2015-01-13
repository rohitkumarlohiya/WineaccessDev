package com.wineaccess.wine;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 * PO of the view wine and take the wine id as parameter
 */
@XmlRootElement(name = "viewWineLogistic")
public class ViewWineLogisticPO extends BasePO implements Serializable {

    private static final long serialVersionUID = -5641462511616055269L;

    @NotEmpty(message = "LOGISTIC_ERROR_103")
	@Pattern(regexp = RegExConstants.DIGITS, message = "LOGISTIC_ERROR_104")
    private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

    
    
}
