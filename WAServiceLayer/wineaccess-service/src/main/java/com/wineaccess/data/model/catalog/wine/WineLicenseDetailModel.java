package com.wineaccess.data.model.catalog.wine;

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
@Table(name = "WINE_LICENSE_DETAIL")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "WineLicenseDetailModel.getByWineId", query = "from WineLicenseDetailModel where wine = :wine") })
public class WineLicenseDetailModel extends Persistent {

	private static final long serialVersionUID = -2968423786495964304L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WINE_ID")
	private WineModel wine;

	@ManyToOne
	@JoinColumn(name = "CA_LICENSE_TYPE")
	private MasterData caLicenseType;

	@Column(name = "CONTRACT_EXECUTED")
	private Boolean contractExecuted;

	@Column(name = "SHIP_COMPLIANT")
	private Boolean shipCompliant;

	@Column(name = "SHIP_ESCROW_NO")
	private String shipEscrowNo;

	@Column(name = "SHIP_COMPLIANT_PRODUCT_KEY")
	private String shipCompliantProductKey;

	@Column(name = "COLA_NUMBER")
	private String colaNumber;

	@Column(name = "SC_3T_PRICE_TO_RETAILER")
	private Double priceToRetailer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineModel getWine() {
		return wine;
	}

	public void setWine(WineModel wine) {
		this.wine = wine;
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

	public void setContractExecuted(Boolean contractExecuted) {
		this.contractExecuted = contractExecuted;
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

	public String getShipCompliantProductKey() {
		return shipCompliantProductKey;
	}

	public void setShipCompliantProductKey(String shipCompliantProductKey) {
		this.shipCompliantProductKey = shipCompliantProductKey;
	}

	public String getColaNumber() {
		return colaNumber;
	}

	public void setColaNumber(String colaNumber) {
		this.colaNumber = colaNumber;
	}

	public Double getPriceToRetailer() {
		return priceToRetailer;
	}

	public void setPriceToRetailer(Double priceToRetailer) {
		this.priceToRetailer = priceToRetailer;
	}

}
