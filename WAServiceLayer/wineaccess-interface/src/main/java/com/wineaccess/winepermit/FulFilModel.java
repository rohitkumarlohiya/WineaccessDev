
package com.wineaccess.winepermit;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author abhishek.sharma1
 *
 */
public class FulFilModel extends BasePO implements Serializable {

	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_PERMIT_ERROR_111")
	private String isStorageContact;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_PERMIT_ERROR_112")
	private String WAPlatformContract;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_PERMIT_ERROR_113")
	private String escrowContract;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_PERMIT_ERROR_114")
	private String isSelected;

	public String getIsStorageContact() {
		return isStorageContact;
	}

	public void setIsStorageContact(String isStorageContact) {
		this.isStorageContact = isStorageContact;
	}

	public String getWAPlatformContract() {
		return WAPlatformContract;
	}

	public void setWAPlatformContract(String wAPlatformContract) {
		WAPlatformContract = wAPlatformContract;
	}

	public String getEscrowContract() {
		return escrowContract;
	}

	public void setEscrowContract(String escrowContract) {
		this.escrowContract = escrowContract;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
