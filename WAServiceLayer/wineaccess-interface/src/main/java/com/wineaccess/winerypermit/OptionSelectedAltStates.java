/**
 * 
 */
package com.wineaccess.winerypermit;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author abhishek.sharma1
 *
 */
public class OptionSelectedAltStates extends BasePO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	private List<PermitModel> permit;
	
	@Valid
	private FulFillModel fulfill;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_PERMIT_ERROR_107")
	private String fulfillDirectlyNotWA;

	

	public String getFulfillDirectlyNotWA() {
		return fulfillDirectlyNotWA;
	}

	public void setFulfillDirectlyNotWA(String fulfillDirectlyNotWA) {
		this.fulfillDirectlyNotWA = fulfillDirectlyNotWA;
	}

	public List<PermitModel> getPermit() {
		return permit;
	}

	public void setPermit(List<PermitModel> permit) {
		this.permit = permit;
	}

	public FulFillModel getFulfill() {
		return fulfill;
	}

	public void setFulfill(FulFillModel fulfill) {
		this.fulfill = fulfill;
	}

	

	
	
	
	
}
