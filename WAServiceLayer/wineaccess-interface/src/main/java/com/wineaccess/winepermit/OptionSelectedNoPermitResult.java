
package com.wineaccess.winepermit;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;

/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="wineOptionSelectedNoPermitResult")
public class OptionSelectedNoPermitResult {


    private String sc3TStatus;
    private WinePermitModelWithMasterData winePermit;
    @JsonSerialize(using=JsonDateSerializer.class)
    private Date priceFiled;

    private Boolean isSelected = false;

    public String getSc3TStatus() {
        return sc3TStatus;
    }

    public void setSc3TStatus(String sc3tStatus) {
        sc3TStatus = sc3tStatus;
    }

    public WinePermitModelWithMasterData getWinePermit() {
        return winePermit;
    }

    public void setWinePermit(WinePermitModelWithMasterData winePermit) {
        this.winePermit = winePermit;
    }

    public Date getPriceFiled() {
        return priceFiled;
    }

    public void setPriceFiled(Date priceFiled) {
        this.priceFiled = priceFiled;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }



}
