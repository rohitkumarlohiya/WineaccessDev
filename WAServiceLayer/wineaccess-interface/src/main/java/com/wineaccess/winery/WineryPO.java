package com.wineaccess.winery;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.command.BasePO;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;

@XmlRootElement(name="winery")
public class WineryPO extends BasePO {

	@NotBlank(message = "WINERY_ERROR_101")
	private String wineryName;

	@NotNull(message = "WINERY_ERROR_103")
	@Min(value = 1, message = "WINERY_ERROR_104")
	private Long regionId;

	
	@Pattern(regexp=RegExConstants.ALPHA_NUMERIC_HIPEN_NOT_EMPTY_STRING,message="WINERY_ERROR_105")
	private String accountingCustomerNumber;

	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.SOURCING_STATUS, message = MasterDataErrorCode.Constants.SOURCING_STATUS)
	@NotNull(message = "WINERY_ERROR_106")
	@Min(value = 1, message = "WINERY_ERROR_107")
	private Long sourcingStatusId;

	private List<Long> importerIds;
	
	private Long activeImporterId;	
	
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.WA_CONTACT, message = MasterDataErrorCode.Constants.WA_CONTACT)
	@Min(value = 1, message = "WINERY_ERROR_128")
	private Long waContact;
	
	@MasterDataAnnotation(masterDataTypeName = MasterDataTypeEnum.Constants.FREIGHT_REGION, message = MasterDataErrorCode.Constants.FREIGHT_REGION)
	@Min(value = 1, message = "WINERY_ERROR_129")
	private Long freightRegion;
	
	@URL(message="WINERY_ERROR_140")
	private String wineryUrl;
	
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message="WINERY_ERROR_134")
	private String warehouseId;
	
	@NotNull(message = "WINERY_ERROR_136")
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINERY_ERROR_137")
	private String status;
	
	public String getWineryUrl() {
		return wineryUrl;
	}

	public void setWineryUrl(String wineryUrl) {
		this.wineryUrl = wineryUrl;
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

	public String getWineryName() {
		if(wineryName != null){
			this.wineryName = wineryName.trim();
		}
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		if(wineryName != null){
			this.wineryName = wineryName.trim();
		}
		this.wineryName = wineryName;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getAccountingCustomerNumber() {
		return accountingCustomerNumber;
	}

	public void setAccountingCustomerNumber(String accountingCustomerNumber) {
		this.accountingCustomerNumber = accountingCustomerNumber;
	}

	public Long getSourcingStatusId() {
		return sourcingStatusId;
	}

	public void setSourcingStatusId(Long sourcingStatusId) {
		this.sourcingStatusId = sourcingStatusId;
	}

	public List<Long> getImporterIds() {
		return importerIds;
	}

	public void setImporterIds(List<Long> importerIds) {
		this.importerIds = importerIds;
	}

	public Long getActiveImporterId() {
		return activeImporterId;
	}

	public void setActiveImporterId(Long activeImporterId) {
		this.activeImporterId = activeImporterId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
