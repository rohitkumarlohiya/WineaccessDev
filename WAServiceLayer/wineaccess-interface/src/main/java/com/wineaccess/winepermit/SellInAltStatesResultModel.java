/**
 * 
 */
package com.wineaccess.winepermit;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="wineSellInAltStatesResultModel")
public class SellInAltStatesResultModel {


    private Boolean isOptionSelectedKachinaAlt = Boolean.FALSE;
    private OptionSelectedAltStatesResult optionSelectedAltStates;
    private List<OptionSelectedNoPermitResult> optionSelectedNoPermit;
    private Boolean isOptionSelectedNoPermit = Boolean.FALSE;
    private Boolean isSelected = Boolean.FALSE;

    public Boolean getIsOptionSelectedKachinaAlt() {
	return isOptionSelectedKachinaAlt;
    }
    public void setIsOptionSelectedKachinaAlt(Boolean isOptionSelectedKachinaAlt) {
	this.isOptionSelectedKachinaAlt = isOptionSelectedKachinaAlt;
    }
    public OptionSelectedAltStatesResult getOptionSelectedAltStates() {
	return optionSelectedAltStates;
    }
    public void setOptionSelectedAltStates(
	    OptionSelectedAltStatesResult optionSelectedAltStates) {
	this.optionSelectedAltStates = optionSelectedAltStates;
    }

    
    public List<OptionSelectedNoPermitResult> getOptionSelectedNoPermit() {
        return optionSelectedNoPermit;
    }
    public void setOptionSelectedNoPermit(List<OptionSelectedNoPermitResult> optionSelectedNoPermit) {
        this.optionSelectedNoPermit = optionSelectedNoPermit;
    }
    public Boolean getIsSelected() {
	return isSelected;
    }
    public void setIsSelected(Boolean isSelected) {
	this.isSelected = isSelected;
    }
    public Boolean getIsOptionSelectedNoPermit() {
	return isOptionSelectedNoPermit;
    }
    public void setIsOptionSelectedNoPermit(Boolean isOptionSelectedNoPermit) {
	this.isOptionSelectedNoPermit = isOptionSelectedNoPermit;
    }





}
