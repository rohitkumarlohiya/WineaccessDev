
package com.wineaccess.winepermit;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.JaxbDateSerializer;

/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="wineOptionSelectedNoPermit")
public class OptionSelectedNoPermit {

    @Pattern(regexp = RegExConstants.DIGITS, message = "WINE_PERMIT_ERROR_128")
    private String masterDataId;

    
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    private Date noPermitdate;

   
    private String sc3TStatus;
    
    @Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_PERMIT_ERROR_129")
    private String isSelected;


    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getMasterDataId() {
	return masterDataId;
    }

    public void setMasterDataId(String masterDataId) {
	this.masterDataId = masterDataId;
    }

    public Date getNoPermitdate() {
	return noPermitdate;
    }

    public void setNoPermitdate(Date noPermitdate) {
	this.noPermitdate = noPermitdate;
    }

    public String getSc3TStatus() {
	return sc3TStatus;
    }

    public void setSc3TStatus(String sc3tStatus) {
	sc3TStatus = sc3tStatus;
    }




}
