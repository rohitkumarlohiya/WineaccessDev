package com.wineaccess.wine;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 * PO of the view wine and take the wine id as parameter
 */
@XmlRootElement(name = "viewWine")
public class ViewWinePO extends BasePO implements Serializable {

    private static final long serialVersionUID = -5641462511616055269L;

    @NotNull(message = "VIEW_WINE_101")
    @Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "VIEW_WINE_104")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String wineId) {
        this.id = wineId;
    }

}
