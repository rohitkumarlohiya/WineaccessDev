package com.wineaccess.winerypermit;

import java.io.Serializable;

import javax.validation.Valid;
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
@XmlRootElement(name="WineryAddPermit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryPermitPO extends BasePO implements Serializable  { 

    private static final long serialVersionUID = -7248690572731436369L;

    @NotEmpty(message="WINERY_PERMIT_ERROR_101")
    @Pattern(regexp = RegExConstants.DIGITS, message = "WINERY_PERMIT_ERROR_102")
    private String wineryId;

    @Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_PERMIT_ERROR_103")
    private String isSellInMainStates;
    @Valid
    private SellInAltStatesModel sellInAltStates;


    public String getWineryId() {
	return wineryId;
    }

    public void setWineryId(String wineryId) {
	this.wineryId = wineryId;
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
