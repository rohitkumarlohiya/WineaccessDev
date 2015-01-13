package com.wineaccess.data.model.catalog.wine;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "WINE_LICENSE_PERMIT_ALT_STATES")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "getPermitByWineId", query = "from WineLicensePermitAltStates wia where wineId.id= :wineId")
	
	
})
public class WineLicensePermitAltStates extends Persistent {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WINE_ID")
	private WineModel wineId;

	@ManyToOne
	@JoinColumn(name = "WINERY_PERMIT")
	private MasterData wineryPermit;

	@Column(name = "DTC_PERMIT_NUMBER")
	private String dtcPermitNumber;

	@Column(name = "DTC_PERMISSION_DURATION")
	private Integer dtcPermitDurationInMonths;

	@Column(name = "DTC_PERMIT_START_DATE")
	private Date dtcPermitStartDate;

	@Column(name = "DTC_PERMIT_END_DATE")
	private Date dtcPermitEndDate;
	
	@Column(name = "IS_SELECTED")
	private Boolean isSelected;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineModel getWineId() {
		return wineId;
	}

	public void setWineId(WineModel wineId) {
		this.wineId = wineId;
	}

	public MasterData getWineryPermit() {
		return wineryPermit;
	}

	public void setWineryPermit(MasterData wineryPermit) {
		this.wineryPermit = wineryPermit;
	}

	public String getDtcPermitNumber() {
		return dtcPermitNumber;
	}

	public void setDtcPermitNumber(String dtcPermitNumber) {
		this.dtcPermitNumber = dtcPermitNumber;
	}

	public int getDtcPermitDurationInMonths() {
		return dtcPermitDurationInMonths;
	}

	public void setDtcPermitDurationInMonths(int dtcPermitDurationInMonths) {
		this.dtcPermitDurationInMonths = dtcPermitDurationInMonths;
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
