package com.wineaccess.winerylicensedetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "wineryLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryLicenseDetailViewVO implements Serializable {

	private static final long serialVersionUID = 3431857750878328287L;

	private Long id;
	private Long wineryId;
	private Long caLicenseTypeId;
	private Boolean contractExecuted;
	private Boolean shipCompliant;
	private String shipEscrowNo;

	public WineryLicenseDetailViewVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
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
