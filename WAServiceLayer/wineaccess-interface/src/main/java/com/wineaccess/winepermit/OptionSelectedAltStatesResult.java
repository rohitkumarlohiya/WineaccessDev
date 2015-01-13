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
@XmlType(name="wineOptionSelectedAltStatesResult")
public class OptionSelectedAltStatesResult {

    private List<PermitModelResult> permit;

    private FulFillModel fulfill;
    
    private CustomWineryModel mappedWineryWithPermit;


    private Boolean fulfillDirectlyNotWA = false;


    public Boolean getFulfillDirectlyNotWA() {
	return fulfillDirectlyNotWA;
    }

    public void setFulfillDirectlyNotWA(Boolean fulfillDirectlyNotWA) {
	this.fulfillDirectlyNotWA = fulfillDirectlyNotWA;
    }

    public List<PermitModelResult> getPermit() {
	return permit;
    }

    public void setPermit(List<PermitModelResult> permit) {
	this.permit = permit;
    }

    public FulFillModel getFulfill() {
	return fulfill;
    }

    public void setFulfill(FulFillModel fulfill) {
	this.fulfill = fulfill;
    }

    public CustomWineryModel getMappedWineryWithPermit() {
        return mappedWineryWithPermit;
    }

    public void setMappedWineryWithPermit(CustomWineryModel mappedWineryWithPermit) {
        this.mappedWineryWithPermit = mappedWineryWithPermit;
    }



}
