package com.wineaccess.importer;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 * VO of view importer and will show the importer details and key metrics
 */
@XmlRootElement
@XmlType(name = "viewImporter")
public class ViewImporterVO implements Serializable {

	private static final long serialVersionUID = 5017111483136919886L;

	private ImporterDetails importerDetails;
	private ImporterKeyMetrics keyMetrics;

	public ImporterDetails getImporterDetails() {
		return importerDetails;
	}

	public void setImporterDetails(ImporterDetails importerDetails) {
		this.importerDetails = importerDetails;
	}

	public ImporterKeyMetrics getKeyMetrics() {
		return keyMetrics;
	}

	public void setKeyMetrics(ImporterKeyMetrics keyMetrics) {
		this.keyMetrics = keyMetrics;
	}

}
