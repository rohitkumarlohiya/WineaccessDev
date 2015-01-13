package com.wineaccess.importer;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 * PO of the view importer and take the importer id as parameter
 */
@XmlRootElement(name="viewImporter")
public class ViewImporterPO extends BasePO implements Serializable{

	private static final long serialVersionUID = -2847062607278382865L;
	
	@NotBlank(message="COMMON_01")
	@Pattern(regexp="\\d*", message="COMMON_01")
	private String importerId;

	public String getImporterId() {
		return importerId;
	}

	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}
}
