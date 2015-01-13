package com.wineaccess.importer;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="importerDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EditImporterPO extends AddImporterPO implements Serializable {

	private static final long serialVersionUID = -7248690572731436369L;

	@NotNull(message="IMPORTER119")
	@Min(value = 1, message="IMPORTER119")
	private Long importerId;

	public Long getImporterId() {
		return importerId;
	}

	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}

}
