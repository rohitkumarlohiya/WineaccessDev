/**
 * 
 */
package com.wineaccess.winepermit;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author abhishek.sharma1
 *
 */
public class PermitModel  extends BasePO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Pattern(regexp = RegExConstants.DIGITS, message = "WINE_PERMIT_ERROR_108")
	private String masterDataId;
	private String dtcPermitNumber;
	@Pattern(regexp = RegExConstants.DIGITS, message = "WINE_PERMIT_ERROR_109")
	private String permitDurationInMonths;
	//@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date dtcPermitStartDate;
	//@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date dtcPermitEndDate;
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_PERMIT_ERROR_110")
	private String isSelected;
	
	public String getMasterDataId() {
		return masterDataId;
	}
	public void setMasterDataId(String masterDataId) {
		this.masterDataId = masterDataId;
	}
	public String getDtcPermitNumber() {
		return dtcPermitNumber;
	}
	public void setDtcPermitNumber(String dtcPermitNumber) {
		this.dtcPermitNumber = dtcPermitNumber;
	}
	public String getPermitDurationInMonths() {
		return permitDurationInMonths;
	}
	public void setPermitDurationInMonths(String permitDurationInMonths) {
		this.permitDurationInMonths = permitDurationInMonths;
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
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
