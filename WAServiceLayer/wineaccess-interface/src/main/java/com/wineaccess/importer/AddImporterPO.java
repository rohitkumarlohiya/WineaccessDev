package com.wineaccess.importer; 


import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;



/**
 * @author arpit.vijayvargiya
 *
 */
@XmlRootElement(name="importer")
public class AddImporterPO extends BasePO implements Serializable{

	private static final long serialVersionUID = -5533955929788401899L;
	
	
	@NotEmpty(message="IMPORTER101")
	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "IMPORTER133")
	private String importerName;
	
	@NotEmpty(message="IMPORTER102")
	@Pattern(regexp = RegExConstants.DIGITS, message = "IMPORTER134")
	private String countryId;
	
	//@NotBlank(message="IMPORTER103")
	@Pattern(regexp=RegExConstants.ALPHA_NUMERIC_HIPEN_NOT_EMPTY_STRING,message="IMPORTER_ERROR_133")
	private String accountingCustNumber;
	
	@NotEmpty(message="IMPORTER104")
	@Pattern(regexp = RegExConstants.STRING_NOT_EMPTY_STRING, message = "IMPORTER135")
	/*@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.SOURCING_STATUS,message=MasterDataErrorCode.Constants.SOURCING_STATUS)*/
	private String sourcingStatus;
	
	private List<Long> wineryId;
	
	
	@URL(message="IMPORTER136")
	private String importerUrl;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.WA_CONTACT,message=MasterDataErrorCode.Constants.WA_CONTACT)
//	@Min(value = 1, message = "IMPORTER122")
	private Long waContact;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.FREIGHT_REGION,message=MasterDataErrorCode.Constants.FREIGHT_REGION)
//	@Min(value = 1, message = "IMPORTER123")
	private Long freightRegion;
	
	@NotEmpty(message = "IMPORTER128")
	@Pattern(regexp = RegExConstants.BOOLEAN_NOT_EMPTY_STRING, message = "IMPORTER129")
	private String status;
	
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message="IMPORTER132")
	private String warehouseId;	
	
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public Long getWaContact() {
		return waContact;
	}
	public void setWaContact(Long waContact) {
		this.waContact = waContact;
	}
	public Long getFreightRegion() {
		return freightRegion;
	}
	public void setFreightRegion(Long freightRegion) {
		this.freightRegion = freightRegion;
	}
	public String getImporterName() {
		
		if(importerName != null){
			importerName = importerName.trim();
		}
		return importerName;
	}
	public void setImporterName(String importerName) {
		if(importerName != null){
			importerName = importerName.trim();
		}
		this.importerName = importerName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getAccountingCustNumber() {
		return accountingCustNumber;
	}
	public void setAccountingCustNumber(String accountingCustNumber) {
		this.accountingCustNumber = accountingCustNumber;
	}
	public String getSourcingStatus() {
		return sourcingStatus;
	}
	public void setSourcingStatus(String sourcingStatus) {
		this.sourcingStatus = sourcingStatus;
	}
	@XmlElementWrapper(name="wineries")
	@XmlElement(name="winery")
	public List<Long> getWineryId() {
		return wineryId;
	}
	public void setWineryId(List<Long> wineryId) {
		this.wineryId = wineryId;
	}
	
	public String getImporterUrl() {
		return importerUrl;
	}
	public void setImporterUrl(String importerUrl) {
		this.importerUrl = importerUrl;
	}
	
	public AddImporterPO(String importerName, String countryId,
			String accountingCustNumber, String sourcingStatus,
			List<Long> wineryId, String importerCode, String importerUrl) {
		super();
		this.importerName = importerName;
		this.countryId = countryId;
		this.accountingCustNumber = accountingCustNumber;
		this.sourcingStatus = sourcingStatus;
		this.wineryId = wineryId;
		//this.importerCode = importerCode;
		this.importerUrl = importerUrl;
	}
	public AddImporterPO() {
		super();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}