package com.wineaccess.sampler;

import java.io.Serializable;

public class ProductComplianceDetails implements Serializable {

	private String complianceId;
	private String complianceName;
	private String complianceStatus;
	public String getComplianceId() {
		return complianceId;
	}
	public void setComplianceId(String complianceId) {
		this.complianceId = complianceId;
	}
	public String getComplianceName() {
		return complianceName;
	}
	public void setComplianceName(String complianceName) {
		this.complianceName = complianceName;
	}
	public String getComplianceStatus() {
		return complianceStatus;
	}
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	
}