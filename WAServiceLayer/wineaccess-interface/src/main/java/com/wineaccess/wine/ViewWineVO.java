package com.wineaccess.wine;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 * VO of view wine and will show the wine details and key metrics
 */
@XmlRootElement
@XmlType(name = "viewWine")
public class ViewWineVO implements Serializable {

    private static final long serialVersionUID = 5666217559373995148L;

    private WineDetails wineDetails;
    private WineKeyMetrics keyMetrics;
    
    

    public WineDetails getWineDetails() {
        return wineDetails;
    }

    public void setWineDetails(WineDetails wineDetails) {
        this.wineDetails = wineDetails;
    }

    public WineKeyMetrics getKeyMetrics() {
        return keyMetrics;
    }

    public void setKeyMetrics(WineKeyMetrics keyMetrics) {
        this.keyMetrics = keyMetrics;
    }

}
