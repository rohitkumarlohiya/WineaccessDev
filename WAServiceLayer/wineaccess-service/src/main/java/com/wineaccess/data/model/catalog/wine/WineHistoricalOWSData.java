package com.wineaccess.data.model.catalog.wine;

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
@Table(name = "WINE_HISTORICAL_OWS_DATA")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "WineHistoricalOWSData.getByWineId", query = "from WineHistoricalOWSData where wineId = :wineId")

})
public class WineHistoricalOWSData extends Persistent {

	private static final long serialVersionUID = -4554060358767841098L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "WINE_ID")
	private Long wineId;

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

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
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
