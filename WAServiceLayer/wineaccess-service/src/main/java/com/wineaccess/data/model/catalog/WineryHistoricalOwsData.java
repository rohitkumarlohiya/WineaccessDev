package com.wineaccess.data.model.catalog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "WINERY_HISTORICAL_OWS_DATA")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "WineryHistoricalOwsData.getByWineryId", query = "from WineryHistoricalOwsData where wineryid = :wineryid")

})
public class WineryHistoricalOwsData extends Persistent {

	private static final long serialVersionUID = 4032962552043460526L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "WINERY_ID")
	private Long wineryid;

	@Column(name = "CERA_LICENSE_NUMBER")
	private String ceraLicenseNumber;

	@Column(name = "CERA_CERTIFICATE_NUMBER")
	private String ceraCertNumber;

	@Column(name = "CERA_START_DATE")
	private Date ceraCertStartDate;

	@Column(name = "CERA_EXPIRATION_DATE")
	private Date ceraCertEndDate;

	@Column(name = "FULLFILLER_WINERY_NAME")
	private String fullFillerWineryName;

	@Column(name = "FULLFILLER_WINERY_CODE")
	private String fullFillerWineryCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWineryid() {
		return wineryid;
	}

	public void setWineryid(Long wineryid) {
		this.wineryid = wineryid;
	}

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
