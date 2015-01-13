/**
 * 
 */
package com.wineaccess.winepermit;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author abhishek.sharma1
 *
 */
public class SellInAltStatesModel extends BasePO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_PERMIT_ERROR_104")
    private String isOptionSelectedKachinaAlt;
    @Valid
    private OptionSelectedAltStates optionSelectedAltStates;
    @Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_PERMIT_ERROR_105")
    private String isSelected;
    @Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_PERMIT_ERROR_106")
    private String isSelectedNoPermit;
    @Valid
    private List<OptionSelectedNoPermit> optionSelectedNoPermit;


    public String getIsOptionSelectedKachinaAlt() {
	return isOptionSelectedKachinaAlt;
    }
    public void setIsOptionSelectedKachinaAlt(String isOptionSelectedKachinaAlt) {
	this.isOptionSelectedKachinaAlt = isOptionSelectedKachinaAlt;
    }
    public OptionSelectedAltStates getOptionSelectedAltStates() {
	return optionSelectedAltStates;
    }
    public void setOptionSelectedAltStates(
	    OptionSelectedAltStates optionSelectedAltStates) {
	this.optionSelectedAltStates = optionSelectedAltStates;
    }

    public String getIsSelected() {
	return isSelected;
    }
    public void setIsSelected(String isSelected) {
	this.isSelected = isSelected;
    }
    public List<OptionSelectedNoPermit> getOptionSelectedNoPermit() {
	return optionSelectedNoPermit;
    }
    public void setOptionSelectedNoPermit(List<OptionSelectedNoPermit> optionSelectedNoPermit) {
	this.optionSelectedNoPermit = optionSelectedNoPermit;
    }
    public String getIsSelectedNoPermit() {
	return isSelectedNoPermit;
    }
    public void setIsSelectedNoPermit(String isSelectedNoPermit) {
	this.isSelectedNoPermit = isSelectedNoPermit;
    }


}
