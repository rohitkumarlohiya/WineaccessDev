package com.wineaccess.winepermit;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;


/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="WineViewPermit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WinePermitViewPO extends BasePO implements Serializable  { 

    private static final long serialVersionUID = -7248690572731436369L;

    @NotEmpty(message="WINE_PERMIT_ERROR_101")
    @Pattern(regexp = RegExConstants.DIGITS, message = "WINE_PERMIT_ERROR_102")
    private String productId;

    public String getProductId() {
	return productId;
    }

    public void setProductId(String productId) {
	this.productId = productId;
    }



}
