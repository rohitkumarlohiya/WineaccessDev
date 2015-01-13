package com.wineaccess.winepermit;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;


/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement(name="WineAddPermit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WinePermitPO extends BasePO implements Serializable  { 

    private static final long serialVersionUID = -7248690572731436369L;

    @NotNull(message="WINE_PERMIT_ERROR_101")
    @Pattern(regexp = RegExConstants.DIGITS, message = "WINE_PERMIT_ERROR_102")
    private String productId;

    @Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_PERMIT_ERROR_103")
    private String isSellInMainStates;
    @Valid
    private SellInAltStatesModel sellInAltStates;


  

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getIsSellInMainStates() {
	return isSellInMainStates;
    }

    public void setIsSellInMainStates(String isSellInMainStates) {
	this.isSellInMainStates = isSellInMainStates;
    }

    public SellInAltStatesModel getSellInAltStates() {
	return sellInAltStates;
    }

    public void setSellInAltStates(SellInAltStatesModel sellInAltStates) {
	this.sellInAltStates = sellInAltStates;
    }


}
