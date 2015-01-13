package com.wineaccess.wineryOWS;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.command.BasePO;
import com.wineaccess.common.JsonDateSerializer;

/**
 * @author gaurav.agarwal1
 * winery ows model
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryOwsModel extends BasePO implements Serializable {
	
	private static final long serialVersionUID = -943785817802777507L;
	
	protected String ceraLicenseNumber;
	protected String ceraCertNumber;
	@JsonSerialize(using=JsonDateSerializer.class)
	protected Date ceraCertStartDate;
	@JsonSerialize(using=JsonDateSerializer.class)
	protected Date ceraCertEndDate;
	protected String fullFillerWineryName;
	protected String fullFillerWineryCode;
	
	public String getCeraLicenseNumber() {
		return ceraLicenseNumber;
	}
	public void setCeraLicenseNumber(String ceraLicenseNumber) {
		this.ceraLicenseNumber = ceraLicenseNumber;
	}
	public String getCeraCertNumber() {
		return ceraCertNumber;
	}
	public void setCeraCertNumber(String ceraCertNumber) {
		this.ceraCertNumber = ceraCertNumber;
	}
	
	public Date getCeraCertStartDate() {
		return ceraCertStartDate;
	}
	public void setCeraCertStartDate(Date ceraCertStartDate) {
		this.ceraCertStartDate = ceraCertStartDate;
	}
	
	public Date getCeraCertEndDate() {
		return ceraCertEndDate;
	}
	public void setCeraCertEndDate(Date ceraCertEndDate) {
		this.ceraCertEndDate = ceraCertEndDate;
	}
	public String getFullFillerWineryName() {
		return fullFillerWineryName;
	}
	public void setFullFillerWineryName(String fullFillerWineryName) {
		this.fullFillerWineryName = fullFillerWineryName;
	}
	public String getFullFillerWineryCode() {
		return fullFillerWineryCode;
	}
	public void setFullFillerWineryCode(String fullFillerWineryCode) {
		this.fullFillerWineryCode = fullFillerWineryCode;
	}
}
