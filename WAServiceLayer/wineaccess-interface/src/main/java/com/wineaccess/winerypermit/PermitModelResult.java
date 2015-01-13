/**
 * 
 */
package com.wineaccess.winerypermit;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;

/**
 * @author abhishek.sharma1
 *
 */
public class PermitModelResult {

	
	private WineryPermitModelWithMasterData wineryPermit;
	private String dtcPermitNumber;
	private Integer permitDurationInMonths;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dtcPermitStartDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dtcPermitEndDate;
	private Boolean isSelected = false;
	
	
	public WineryPermitModelWithMasterData getWineryPermit() {
		return wineryPermit;
	}
	public void setWineryPermit(WineryPermitModelWithMasterData wineryPermit) {
		this.wineryPermit = wineryPermit;
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
