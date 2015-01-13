package com.wineaccess.winelicensedetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "wineLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineLicenseDetailViewVO implements Serializable {

	private static final long serialVersionUID = 3431857750878328287L;

	private Long id;
	private Long wineId;
	private Long caLicenseTypeId;
	private Boolean contractExecuted;
	private Boolean shipCompliant;
	private String shipEscrowNo;
	private String shipCompliantProductKey;
	private String colaNumber;
	private Double priceToRetailer;
	private Long productId;

	public WineLicenseDetailViewVO() {
		super();
	}

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

	public Long getCaLicenseTypeId() {
		return caLicenseTypeId;
	}

	public void setCaLicenseTypeId(Long caLicenseTypeId) {
		this.caLicenseTypeId = caLicenseTypeId;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
