package com.wineaccess.data.model.catalog;

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
@Table(name = "WINERY_LICENSE_DETAIL")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "WineryLicenseDetailModel.getById", query = "from WineryLicenseDetailModel where id = :id"),
		@NamedQuery(name = "WineryLicenseDetailModel.getByWineryId", query = "from WineryLicenseDetailModel where winery = :winery") })
public class WineryLicenseDetailModel extends Persistent {

	private static final long serialVersionUID = 3158630383880374902L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WINERY_ID")
	private WineryModel winery;

	@ManyToOne
	@JoinColumn(name = "CA_LICENSE_TYPE")
	private MasterData caLicenseType;

	@Column(name = "CONTRACT_EXECUTED")
	private Boolean contractExecuted;

	@Column(name = "SHIP_COMPLIANT")
	private Boolean shipCompliant;

	@Column(name = "SHIP_ESCROW_NO")
	private String shipEscrowNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineryModel getWinery() {
		return winery;
	}

	public void setWinery(WineryModel wineryId) {
		this.winery = wineryId;
	}

	public MasterData getCaLicenseType() {
		return caLicenseType;
	}

	public void setCaLicenseType(MasterData caLicenseType) {
		this.caLicenseType = caLicenseType;
	}

	public Boolean getContractExecuted() {
		return contractExecuted;
	}

	public void setContractExecuted(Boolean contactExecuted) {
		this.contractExecuted = contactExecuted;
	}

	public Boolean getShipCompliant() {
		return shipCompliant;
	}

	public void setShipCompliant(Boolean shipCompliant) {
		this.shipCompliant = shipCompliant;
	}

	public String getShipEscrowNo() {
		return shipEscrowNo;
	}

	public void setShipEscrowNo(String shipEscrowNo) {
		this.shipEscrowNo = shipEscrowNo;
	}

}
