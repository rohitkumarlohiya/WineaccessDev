package com.wineaccess.winerypermit;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.command.BasePO;



/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WineryPermitDetail")
public class WineryPermitDetailVO extends BasePO implements Serializable  { 

	private static final long serialVersionUID = -7248690572731436369L;

	
	private Long wineryId;

	
	private Boolean isSellInMainStates;

	private SellInAltStatesResultModel sellInAltStates;


	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public Boolean getIsSellInMainStates() {
		return isSellInMainStates;
	}

	public void setIsSellInMainStates(Boolean isSellInMainStates) {
		this.isSellInMainStates = isSellInMainStates;
	}

	public SellInAltStatesResultModel getSellInAltStates() {
		return sellInAltStates;
	}

	public void setSellInAltStates(SellInAltStatesResultModel sellInAltStates) {
		this.sellInAltStates = sellInAltStates;
	}


}
