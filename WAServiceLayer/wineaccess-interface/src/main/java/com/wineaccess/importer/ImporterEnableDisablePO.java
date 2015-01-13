package com.wineaccess.importer;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * PO for enable disable importer
 */
@XmlRootElement(name="importerEnableDisable")
public class ImporterEnableDisablePO extends BasePO implements Serializable {

	private static final long serialVersionUID = 6242501924599762749L;
	
	@Valid
	@NotNull(message = "ENABLE_DIASBLE_IMPORTER_ERROR_101")
	@Size(min = 1,message = "ENABLE_DIASBLE_IMPORTER_ERROR_102")
	private List<Long> importerIds;
	
	@NotEmpty(message="ENABLE_DIASBLE_IMPORTER_ERROR_103")
	@Pattern(regexp = RegExConstants.ENABLE_DISABLE_STATUS, message = "ENABLE_DIASBLE_IMPORTER_ERROR_104")
	private String status;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "ENABLE_DIASBLE_IMPORTER_ERROR_105")
	private String isForceDelete;

	public List<Long> getImporterIds() {
		return importerIds;
	}

	public void setImporterIds(List<Long> importerIds) {
		this.importerIds = importerIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

}
