/**
 * 
 */
package com.wineaccess.winerypermit;

import java.util.List;

/**
 * @author abhishek.sharma1
 *
 */
public class OptionSelectedAltStatesResult {

	private List<PermitModelResult> permit;
	
	private FulFillModel fulfill;
	
	
	private Boolean fulfillDirectlyNotWA = false;


	public Boolean getFulfillDirectlyNotWA() {
		return fulfillDirectlyNotWA;
	}

	public void setFulfillDirectlyNotWA(Boolean fulfillDirectlyNotWA) {
		this.fulfillDirectlyNotWA = fulfillDirectlyNotWA;
	}

	public List<PermitModelResult> getPermit() {
		return permit;
	}

	public void setPermit(List<PermitModelResult> permit) {
		this.permit = permit;
	}

	public FulFillModel getFulfill() {
		return fulfill;
	}

	public void setFulfill(FulFillModel fulfill) {
		this.fulfill = fulfill;
	}

	
	
}
