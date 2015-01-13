/**
 * 
 */
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
@XmlType(name="WinePermitModelResult")
public class PermitModelResult {

	
	private WinePermitModelWithMasterData winePermit;
	private String dtcPermitNumber;
	private Integer permitDurationInMonths;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dtcPermitStartDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dtcPermitEndDate;
	private Boolean isSelected = false;
	public WinePermitModelWithMasterData getWinePermit() {
	    return winePermit;
	}
	public void setWinePermit(WinePermitModelWithMasterData winePermit) {
	    this.winePermit = winePermit;
	}
	public String getDtcPermitNumber() {
	    return dtcPermitNumber;
	}
	public void setDtcPermitNumber(String dtcPermitNumber) {
	    this.dtcPermitNumber = dtcPermitNumber;
	}
	public Integer getPermitDurationInMonths() {
	    return permitDurationInMonths;
	}
	public void setPermitDurationInMonths(Integer permitDurationInMonths) {
	    this.permitDurationInMonths = permitDurationInMonths;
	}
	public Date getDtcPermitStartDate() {
	    return dtcPermitStartDate;
	}
	public void setDtcPermitStartDate(Date dtcPermitStartDate) {
	    this.dtcPermitStartDate = dtcPermitStartDate;
	}
	public Date getDtcPermitEndDate() {
	    return dtcPermitEndDate;
	}
	public void setDtcPermitEndDate(Date dtcPermitEndDate) {
	    this.dtcPermitEndDate = dtcPermitEndDate;
	}
	public Boolean getIsSelected() {
	    return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
	    this.isSelected = isSelected;
	}
	
	
	
	
	
	
}
